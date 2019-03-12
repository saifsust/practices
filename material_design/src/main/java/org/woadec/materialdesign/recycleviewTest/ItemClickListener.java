package org.woadec.materialdesign.recycleviewTest;

import android.view.View;

public interface ItemClickListener {

    public void onClick(View view, int position, String link);

    public void onLongPress(View view, int position, String link);
}
