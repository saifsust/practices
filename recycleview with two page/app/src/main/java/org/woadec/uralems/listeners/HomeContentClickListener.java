package org.woadec.uralems.listeners;

import android.view.View;

public interface HomeContentClickListener {

    public void onClick(View view, String linke);

    public void onLongClick(View view, String link);

    public void onCancel(View view);

    public void onLike(View view, int position);

    public void onShare(View view, String link, int position);

}
