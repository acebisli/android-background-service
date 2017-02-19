package com.acebisli.sampleservice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


import java.text.DecimalFormat;

/**
 * Created by acebisli on 18/02/2017.
 */

public class MyLocationHelper implements LocationListener {
    private static final String TAG = MyLocationHelper.class.getName();


    public LocationManager locationManager;
    private Context context;

    public MyLocationHelper(Context ctx) {
        this.context = ctx;
    }


    public void start() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        createLocationRequest();
    }

    public void destroy() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG,"You need to give permission");
            return;
        }
        locationManager.removeUpdates(this);

    }

    private void createLocationRequest() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "To find location u need to give permission!");
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 10f, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 10f, this);
    }


    @Override
    public void onLocationChanged(Location location) {
        String _location = ("location \n" +
                "Latitude =>" + (new DecimalFormat("#.######").format(location.getLatitude())) +
                ", " +
                "Longitude =>" + (new DecimalFormat("#.######").format(location.getLongitude())));
        Log.d(TAG, _location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
