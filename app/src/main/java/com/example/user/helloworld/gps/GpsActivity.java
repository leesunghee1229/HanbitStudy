package com.example.user.helloworld.gps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.example.user.helloworld.R;

public class GpsActivity extends Activity {

    private TextView mTxtPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        mTxtPos = (TextView) findViewById(R.id.txtPos);
    }

    //버튼 이벤트
    public void onBtnStartGpsClick(View view) {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        long refreshTime = 1000; // 업데이트 주기 : 1초

        float minDistance = 0; // 최소 거리 : 미터

        // GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER
                , refreshTime
                , minDistance,
                mGpsListener
        );
        // NETWORK wifi
        manager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER
                , refreshTime
                , minDistance,
                mGpsListener
        );

        try {
            Location lastLoc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(lastLoc != null) {

                Double latitude = lastLoc.getLatitude(); // 위도
                Double longitude = lastLoc.getLongitude(); // 경도

                mTxtPos.setText("마지막 위치 : " + latitude + "\n" + longitude);


            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        manager.removeUpdates(mGpsListener);
    }

    private LocationListener mGpsListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            // 위치 정보 취득
            Double lat = location.getLatitude();
            Double longt = location.getLongitude();

            String msg = "Provider : " + location.getProvider() + "\n"
                    + "위도 : " + lat + "\n"
                    + "경도 : " + longt;

            mTxtPos.setText(msg);
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
    };
}
