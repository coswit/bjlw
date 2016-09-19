package com.bxlwt.www.bxlwt.Utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context context,CharSequence message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
