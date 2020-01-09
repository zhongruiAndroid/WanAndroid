//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.android.basecore.listener;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Calendar;

public abstract class NoDoubleClickListener implements OnClickListener {
    private static long MIN_CLICK_DELAY_TIME = 900;
    private long lastClickTime;

    public NoDoubleClickListener() {
        this(900);
    }

    public NoDoubleClickListener(long interval) {
        this.lastClickTime = 0L;
        MIN_CLICK_DELAY_TIME = interval;
    }

    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - this.lastClickTime > MIN_CLICK_DELAY_TIME) {
            this.lastClickTime = currentTime;
            this.onNoDoubleClick(v);
        }

    }

    protected abstract void onNoDoubleClick(View var1);
}
