package com.acebisli.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by acebisli on 18/02/2017.
 */

public class GetLocationService extends Service {
    private static final String TAG = GetLocationService.class.getName();
    MyLocationHelper locationHelper;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (locationHelper == null)
            locationHelper = new MyLocationHelper(getApplicationContext());
        locationHelper.start();
        Log.d(TAG, "Service created!");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationHelper.destroy();
        Log.d(TAG, "Service destroyed!");
    }
}
