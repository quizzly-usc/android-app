package edu.usc.clicker.util;

import android.app.Application;

import com.urbanairship.UAirship;

/**
 * Created by crawl_000 on 2/16/2016.
public class launchAirship extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("HERE");
        UAirship.takeOff(this, new UAirship.OnReadyCallback() {
            @Override
            public void onAirshipReady(UAirship airship) {

                // Enable user notifications
                airship.getPushManager().setUserNotificationsEnabled(true);
            }
        });
    }
}
*/