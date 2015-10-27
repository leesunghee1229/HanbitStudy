package com.example.user.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mListView = (ListView) findViewById(R.id.listView);

        List<ListDataBean> list = new ArrayList<ListDataBean>();
        ListDataBean data1 = new ListDataBean();
        ListDataBean data2 = new ListDataBean();
        ListDataBean data3 = new ListDataBean();
        ListDataBean data4 = new ListDataBean();
        ListDataBean data5 = new ListDataBean();
        ListDataBean data6 = new ListDataBean();
        ListDataBean data7 = new ListDataBean();
        ListDataBean data8 = new ListDataBean();
        ListDataBean data9 = new ListDataBean();
        ListDataBean data10 = new ListDataBean();
        ListDataBean data11 = new ListDataBean();
        ListDataBean data12 = new ListDataBean();

        data1.setImgSrc(R.mipmap.ic_launcher);
        data1.setTitle("타이틀 1");
        data1.setContents("컨텐츠 1");
        data2.setImgSrc(R.drawable.img_big1);
        data2.setTitle("타이틀 2");
        data2.setContents("컨텐츠 2");
        data3.setImgSrc(R.mipmap.ic_launcher);
        data3.setTitle("타이틀 3");
        data3.setContents("컨텐츠 3");
        data4.setImgSrc(R.drawable.img_big1);
        data4.setTitle("타이틀 4");
        data4.setContents("컨텐츠 4");
        data5.setImgSrc(R.mipmap.ic_launcher);
        data5.setTitle("타이틀 5");
        data5.setContents("컨텐츠 5");
        data6.setImgSrc(R.drawable.img_big1);
        data6.setTitle("타이틀 6");
        data6.setContents("컨텐츠 6");
        data7.setImgSrc(R.mipmap.ic_launcher);
        data7.setTitle("타이틀 7");
        data7.setContents("컨텐츠 7");
        data8.setImgSrc(R.drawable.img_big1);
        data8.setTitle("타이틀 8");
        data8.setContents("컨텐츠 8");
        data9.setImgSrc(R.mipmap.ic_launcher);
        data9.setTitle("타이틀 9");
        data9.setContents("컨텐츠 9");
        data10.setImgSrc(R.drawable.img_big1);
        data10.setTitle("타이틀 10");
        data10.setContents("컨텐츠 10");
        data11.setImgSrc(R.mipmap.ic_launcher);
        data11.setTitle("타이틀 11");
        data11.setContents("컨텐츠 11");
        data12.setImgSrc(R.drawable.img_big1);
        data12.setTitle("타이틀 12");
        data12.setContents("컨텐츠 12");

        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        list.add(data6);
        list.add(data7);
        list.add(data8);
        list.add(data9);
        list.add(data10);
        list.add(data11);
        list.add(data12);

        ListAdapter listAdapter = new ListAdapter(this,list);
        mListView.setAdapter(listAdapter);
    }

}
