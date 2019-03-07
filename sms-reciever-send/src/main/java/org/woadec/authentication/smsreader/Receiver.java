package org.woadec.authentication.smsreader;

import android.content.Context;
import android.content.Intent;

public interface Receiver {
    public void onReceive(Context context, Intent intent);
}
