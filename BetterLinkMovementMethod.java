package com.woadec.ibcr.ural.ambulance2;

import android.graphics.RectF;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

public class BetterLinkMovementMethod extends LinkMovementMethod {

    private static BetterLinkMovementMethod singleInstance;

    private OnLinkClickListener onLinkClickListener;
    private OnLinkLongClickListener onLinkLongClickListener;
    private final RectF touchedLineBounds = new RectF();
    private boolean isUrlHighlighted;
    private ClickableSpan clickableSpanUnderTouchOnActionDown;
    private int activeTextViewHashcode;
    private LongPressTimer ongoingLongPressTimer;
    private boolean wasLongPressRegistered;

    public interface OnLinkClickListener {

        boolean onClick(TextView textView, String url);
    }

    public interface OnLinkLongClickListener {
        /**
         * @param textView The TextView on which a long-click was registered.
         * @param url      The long-clicked URL.
         * @return True if this long-click was handled. False to let Android handle the URL (as a short-click).
         */
        boolean onLongClick(TextView textView, String url);
    }

    /**
     * Return a new instance of BetterLinkMovementMethod.
     */
    public static BetterLinkMovementMethod newInstance() {
        return new BetterLinkMovementMethod();
    }





    /**
     * Get a static instance of BetterLinkMovementMethod. Do note that registering a click listener on the returned
     * instance is not supported because it will potentially be shared on multiple TextViews.
     */
    @SuppressWarnings("unused")
    public static BetterLinkMovementMethod getInstance() {
        if (singleInstance == null) {
            singleInstance = new BetterLinkMovementMethod();
        }
        return singleInstance;
    }

    protected BetterLinkMovementMethod() {
    }

    /**
     * Set a listener that will get called whenever any link is clicked on the TextView.
     */
    public BetterLinkMovementMethod setOnLinkClickListener(OnLinkClickListener clickListener) {
        if (this == singleInstance) {
            throw new UnsupportedOperationException("Setting a click listener on the instance returned by getInstance() is not supported to avoid memory " +
                    "leaks. Please use newInstance() or any of the linkify() methods instead.");
        }

        this.onLinkClickListener = clickListener;
        return this;
    }

    /**
     * Set a listener that will get called whenever any link is clicked on the TextView.
     */
    public BetterLinkMovementMethod setOnLinkLongClickListener(OnLinkLongClickListener longClickListener) {
        if (this == singleInstance) {
            throw new UnsupportedOperationException("Setting a long-click listener on the instance returned by getInstance() is not supported to avoid " +
                    "memory leaks. Please use newInstance() or any of the linkify() methods instead.");
        }

        this.onLinkLongClickListener = longClickListener;
        return this;
    }



    @Override
    public boolean onTouchEvent(final TextView textView, Spannable text, MotionEvent event) {
        if (activeTextViewHashcode != textView.hashCode()) {
            // Bug workaround: TextView stops calling onTouchEvent() once any URL is highlighted.
            // A hacky solution is to reset any "autoLink" property set in XML. But we also want
            // to do this once per TextView.
            activeTextViewHashcode = textView.hashCode();
            textView.setAutoLinkMask(0);
        }

        final ClickableSpan clickableSpanUnderTouch = findClickableSpanUnderTouch(textView, text, event);

          System.out.println(clickableSpanUnderTouch);

          if(clickableSpanUnderTouch !=null){

              //clickableSpanUnderTouch


             ClickableSpanWithText tex= ClickableSpanWithText.ofSpan(textView,clickableSpanUnderTouch);
             System.out.println(tex.text());
              return true;
          }






        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickableSpanUnderTouchOnActionDown = clickableSpanUnderTouch;
        }
        final boolean touchStartedOverAClickableSpan = clickableSpanUnderTouchOnActionDown != null;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (clickableSpanUnderTouch != null) {





                    highlightUrl(textView, clickableSpanUnderTouch, text);
                }

                if (touchStartedOverAClickableSpan && onLinkLongClickListener != null) {
                    LongPressTimer.OnTimerReachedListener longClickListener = new LongPressTimer.OnTimerReachedListener() {
                        @Override
                        public void onTimerReached() {
                            wasLongPressRegistered = true;
                            textView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                            removeUrlHighlightColor(textView);
                            dispatchUrlLongClick(textView, clickableSpanUnderTouch);
                        }
                    };
                    startTimerForRegisteringLongClick(textView, longClickListener);
                }
                return touchStartedOverAClickableSpan;

            case MotionEvent.ACTION_UP:
                // Register a click only if the touch started and ended on the same URL.
                if (!wasLongPressRegistered && touchStartedOverAClickableSpan && clickableSpanUnderTouch == clickableSpanUnderTouchOnActionDown) {
                    dispatchUrlClick(textView, clickableSpanUnderTouch);
                }
                cleanupOnTouchUp(textView);

                // Consume this event even if we could not find any spans to avoid letting Android handle this event.
                // Android's TextView implementation has a bug where links get clicked even when there is no more text
                // next to the link and the touch lies outside its bounds in the same direction.
                return touchStartedOverAClickableSpan;

            case MotionEvent.ACTION_CANCEL:
                cleanupOnTouchUp(textView);
                return false;

            case MotionEvent.ACTION_MOVE:
                // Stop listening for a long-press as soon as the user wanders off to unknown lands.
                if (clickableSpanUnderTouch != clickableSpanUnderTouchOnActionDown) {
                    removeLongPressCallback(textView);
                }

                if (!wasLongPressRegistered) {
                    // Toggle highlight.
                    if (clickableSpanUnderTouch != null) {
                        highlightUrl(textView, clickableSpanUnderTouch, text);
                    } else {
                        removeUrlHighlightColor(textView);
                    }
                }

                return touchStartedOverAClickableSpan;

            default:
                return false;
        }
    }

    private void cleanupOnTouchUp(TextView textView) {
        wasLongPressRegistered = false;
        clickableSpanUnderTouchOnActionDown = null;
        removeUrlHighlightColor(textView);
        removeLongPressCallback(textView);
    }

    /**
     * Determines the touched location inside the TextView's text and returns the ClickableSpan found under it (if any).
     *
     * @return The touched ClickableSpan or null.
     */
    protected ClickableSpan findClickableSpanUnderTouch(TextView textView, Spannable text, MotionEvent event) {
        // So we need to find the location in text where touch was made, regardless of whether the TextView
        // has scrollable text. That is, not the entire text is currently visible.
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        // Ignore padding.
        touchX -= textView.getTotalPaddingLeft();
        touchY -= textView.getTotalPaddingTop();

        // Account for scrollable text.
        touchX += textView.getScrollX();
        touchY += textView.getScrollY();

        final Layout layout = textView.getLayout();
        final int touchedLine = layout.getLineForVertical(touchY);
        final int touchOffset = layout.getOffsetForHorizontal(touchedLine, touchX);

        touchedLineBounds.left = layout.getLineLeft(touchedLine);
        touchedLineBounds.top = layout.getLineTop(touchedLine);
        touchedLineBounds.right = layout.getLineWidth(touchedLine) + touchedLineBounds.left;
        touchedLineBounds.bottom = layout.getLineBottom(touchedLine);

        if (touchedLineBounds.contains(touchX, touchY)) {
            // Find a ClickableSpan that lies under the touched area.
            final Object[] spans = text.getSpans(touchOffset, touchOffset, ClickableSpan.class);

            for (final Object span : spans) {
                if (span instanceof ClickableSpan) {
                    return (ClickableSpan) span;
                }
            }
            // No ClickableSpan found under the touched location.
            return null;

        } else {
            // Touch lies outside the line's horizontal bounds where no spans should exist.
            return null;
        }
    }

    /**
     * Adds a background color span at <var>clickableSpan</var>'s location.
     */
    protected void highlightUrl(TextView textView, ClickableSpan clickableSpan, Spannable text) {
        if (isUrlHighlighted) {
            return;
        }
        isUrlHighlighted = true;

        int spanStart = text.getSpanStart(clickableSpan);
        int spanEnd = text.getSpanEnd(clickableSpan);
        BackgroundColorSpan highlightSpan = new BackgroundColorSpan(textView.getHighlightColor());
        text.setSpan(highlightSpan, spanStart, spanEnd, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        textView.setTag(R.id.bettermovementmethod_highlight_background_span, highlightSpan);

        Selection.setSelection(text, spanStart, spanEnd);
    }

    /**
     * Removes the highlight color under the Url.
     */
    protected void removeUrlHighlightColor(TextView textView) {
        if (!isUrlHighlighted) {
            return;
        }
        isUrlHighlighted = false;

        Spannable text = (Spannable) textView.getText();
        BackgroundColorSpan highlightSpan = (BackgroundColorSpan) textView.getTag(R.id.bettermovementmethod_highlight_background_span);
        text.removeSpan(highlightSpan);

        Selection.removeSelection(text);
    }

    protected void startTimerForRegisteringLongClick(TextView textView, LongPressTimer.OnTimerReachedListener longClickListener) {
        ongoingLongPressTimer = new LongPressTimer();
        ongoingLongPressTimer.setOnTimerReachedListener(longClickListener);
        textView.postDelayed(ongoingLongPressTimer, ViewConfiguration.getLongPressTimeout());
    }

    /**
     * Remove the long-press detection timer.
     */
    protected void removeLongPressCallback(TextView textView) {
        if (ongoingLongPressTimer != null) {
            textView.removeCallbacks(ongoingLongPressTimer);
            ongoingLongPressTimer = null;
        }
    }

    protected void dispatchUrlClick(TextView textView, ClickableSpan clickableSpan) {
        ClickableSpanWithText clickableSpanWithText = ClickableSpanWithText.ofSpan(textView, clickableSpan);
        boolean handled = onLinkClickListener != null && onLinkClickListener.onClick(textView, clickableSpanWithText.text());

        if (!handled) {
            // Let Android handle this click.
            clickableSpanWithText.span().onClick(textView);
        }
    }

    protected void dispatchUrlLongClick(TextView textView, ClickableSpan clickableSpan) {
        ClickableSpanWithText clickableSpanWithText = ClickableSpanWithText.ofSpan(textView, clickableSpan);
        boolean handled = onLinkLongClickListener != null && onLinkLongClickListener.onLongClick(textView, clickableSpanWithText.text());

        if (!handled) {
            // Let Android handle this long click as a short-click.
            clickableSpanWithText.span().onClick(textView);
        }
    }

    protected static final class LongPressTimer implements Runnable {
        private OnTimerReachedListener onTimerReachedListener;

        protected interface OnTimerReachedListener {
            void onTimerReached();
        }

        @Override
        public void run() {
            onTimerReachedListener.onTimerReached();
        }

        public void setOnTimerReachedListener(OnTimerReachedListener listener) {
            onTimerReachedListener = listener;
        }
    }

    /**
     * A wrapper to support all {@link ClickableSpan}s that may or may not provide URLs.
     */
    protected static class ClickableSpanWithText {
        private ClickableSpan span;
        private String text;

        protected static ClickableSpanWithText ofSpan(TextView textView, ClickableSpan span) {
            Spanned s = (Spanned) textView.getText();
            String text;
            if (span instanceof URLSpan) {
                text = ((URLSpan) span).getURL();
            } else {
                int start = s.getSpanStart(span);
                int end = s.getSpanEnd(span);
                text = s.subSequence(start, end).toString();
            }
            return new ClickableSpanWithText(span, text);
        }

        protected ClickableSpanWithText(ClickableSpan span, String text) {
            this.span = span;
            this.text = text;
        }

        protected ClickableSpan span() {
            return span;
        }

        protected String text() {
            return text;
        }
    }
}