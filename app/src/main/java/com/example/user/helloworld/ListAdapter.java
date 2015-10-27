package com.example.user.helloworld;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by user on 2015-10-24.
 */
public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ListDataBean> mList;

    public ListAdapter(Context context, List<ListDataBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_inflate1, null);
        }

        ImageView imgTitle = (ImageView) convertView.findViewById(R.id.imgTitle);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView txtContents = (TextView) convertView.findViewById(R.id.txtContents);
        Button btnView = (Button) convertView.findViewById(R.id.btnView);

        ListDataBean bean = mList.get(position);


        imgTitle.setImageResource(bean.getImgSrc());
        txtTitle.setText(bean.getTitle());
        txtContents.setText(bean.getContents());

        btnView.setTag(position);
        btnView.setOnClickListener(btnClick); // 버튼클릭

        convertView.setTag(position);
        convertView.setOnClickListener(btnClick);

        return convertView;
    }

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            Toast.makeText(mContext, "리스트 : " + position, Toast.LENGTH_LONG).show();

            ListDataBean bean = mList.get(position);
            Intent intent = new Intent(mContext,ScrollActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("listData",bean);

            mContext.startActivity(intent);


        }
    };
}
