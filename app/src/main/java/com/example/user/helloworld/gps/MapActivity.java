package com.example.user.helloworld.gps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.helloworld.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment smFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mMap = smFragment.getMap();

        // 구글맵에 현재위치 아이콘 + 나침반을 활성화 시킨다.
        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);



    }
}
