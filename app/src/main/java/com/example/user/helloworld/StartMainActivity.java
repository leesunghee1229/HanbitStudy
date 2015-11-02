package com.example.user.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StartMainActivity extends Activity {


    private ListView mListView;
    private List<ListBean> mlistDate;
    private ActivityInfo[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_main);

        mListView = (ListView) findViewById(R.id.listView2);

        mlistDate = new ArrayList<ListBean>();

        try {
            list = new ActivityInfo[0];
            list = getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_ACTIVITIES).activities;
            for (int i = 0; i < list.length; i++) {
                String[] ActiName = list[i].name.split("\\.");
                mlistDate.add(new ListBean(R.mipmap.ic_launcher, ActiName[(ActiName.length)-1], "", ""));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ActivityListAdapter adapter = new ActivityListAdapter(this, mlistDate, mListClick);

        mListView.setAdapter(adapter);

    }

    private View.OnClickListener mListClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //이벤ㅌ 취득
            int position = (int) v.getTag();

            try {
                Class t = Class.forName(list[position].name);
                Intent i = new Intent(getApplicationContext(), t);
                startActivity(i);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

}
