package com.example.user.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InflateActivity extends AppCompatActivity {

    private FrameLayout mLayFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflate);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        mLayFrame = (FrameLayout) findViewById(R.id.layFrame);
    }

    // btnAdd 클릭 이벤트
    public void btnAddClick(View view) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewInfla1 = inflater.inflate(R.layout.view_inflate1,null);

        ImageView imgTitle = (ImageView) viewInfla1.findViewById(R.id.imgTitle);
        TextView txtTitle = (TextView) viewInfla1.findViewById(R.id.txtTitle);
        TextView txtContents = (TextView) viewInfla1.findViewById(R.id.txtContents);
        Button btnView = (Button) viewInfla1.findViewById(R.id.btnView);

        imgTitle.setImageDrawable(getResources().getDrawable(R.drawable.img_big1));
        txtTitle.setText("인플레이트 타이틀 1");
        txtContents.setText("인플레이트 콘텐츠 1");
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InflateActivity.this, "인플레이트 성공!", Toast.LENGTH_LONG).show();
            }
        });


        mLayFrame.addView(viewInfla1);
    }
}
