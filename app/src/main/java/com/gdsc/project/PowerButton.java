package com.gdsc.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PowerButton extends BroadcastReceiver {

    public static boolean wasScreenOn = true;
    public static int count = 0;
    public static long firstTime = 0;
    public static long lastTime = 0;
    Home main = new Home();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            count++;
            if (count >= 4) {
                count = 4;
            }
            wasScreenOn = false;

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            count++;
        }
        if (count == 1) {
            firstTime = System.currentTimeMillis();
        } else if (count <= 3) {
            long currentTime = System.currentTimeMillis();
            if   (currentTime - firstTime >= 3000) {
                count = 0;
            }
        }
        if (count == 4) {
            lastTime = System.currentTimeMillis();
            if (lastTime - firstTime <= 4000) {
                main.onSend(null);
            }
        }
    }
}
