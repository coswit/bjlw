package com.bxlwt.www.bxlwt.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

   private static SharedPreferences sp;

    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(Keys.CONFIG_FILE,Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(Keys.CONFIG_FILE,Context.MODE_PRIVATE);
        }
        return  sp.getString(key,value);
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(Keys.CONFIG_FILE,Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(Keys.CONFIG_FILE,Context.MODE_PRIVATE);
        }
        return  sp.getBoolean(key,value);
    }

    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(Keys.CONFIG_FILE,Context.MODE_PRIVATE);

        }
        sp.edit().putInt(key,value).commit();
    }
    public static int getInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(Keys.CONFIG_FILE,Context.MODE_PRIVATE);

        }
        return  sp.getInt(key,value);
    }


}
