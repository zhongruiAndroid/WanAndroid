package com.android.basecore.tools;

import android.util.SparseLongArray;
import android.view.View;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ClickTools {
    private static ClickTools clickTools;
    private Map<Long, ConcurrentHashMap> map;
    private static final int MIN_CLICK_DELAY_TIME = 900;

    private ClickTools() {
        map = new ConcurrentHashMap<>();
    }

    public static ClickTools get() {
        if (clickTools == null) {
            synchronized (ClickTools.class) {
                if (clickTools == null) {
                    clickTools = new ClickTools();
                }
            }
        }
        return clickTools;
    }

    public boolean isFastClick(Long key, View view) {
        return isFastClick(key, view, MIN_CLICK_DELAY_TIME);
    }

    public boolean isFastClick(Long key, View view, long time) {
        return isFastClickById(key, view.getId(), time);
    }

    public boolean isFastClickById(Long key, int itemId) {
        return isFastClickById(key, itemId, MIN_CLICK_DELAY_TIME);
    }

    public boolean isFastClickById(Long key, int itemId, long time) {
        ConcurrentHashMap<Integer, Long> sparseLongArray = map.get(key);
        if (sparseLongArray == null) {
            sparseLongArray = new ConcurrentHashMap();
            map.put(key, sparseLongArray);
        }
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - sparseLongArray.get(itemId) > time) {
            sparseLongArray.put(itemId, currentTime);
            return false;
        }
        return true;
    }


    public void removeLastClickTime(Long key) {
        if (map != null) {
            ConcurrentHashMap sparseLongArray = map.get(key);
            if (sparseLongArray != null) {
                sparseLongArray.clear();
                sparseLongArray = null;
                map.remove(key);
            }
        }
    }

    public void clearLastClickTime(Long key) {
        if (map != null) {
            ConcurrentHashMap sparseLongArray = map.get(key);
            if (sparseLongArray != null) {
                sparseLongArray.clear();
            }
        }
    }
}
