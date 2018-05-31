package com.example.rx;

import android.util.Log;

/**
 * @author : Administrator
 * @time : 17:32
 * @for :
 */
public class MyLog {
    public static String TAG = "===RX====";

    public static void log(String string){
        Log.i(TAG, "log: "+string);
    }
}
