package com.simonekarani.moraliq.util;

import android.content.Context;
import android.content.res.Configuration;

public class ActivityHelper {
    public static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE: // Configuration.SCREENLAYOUT_SIZE_XLARGE is API >= 9
                return "xlarge";
            default:
                return "undefined";
        }
    }
}
