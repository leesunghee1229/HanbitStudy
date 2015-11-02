package com.example.user.helloworld;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 2015-10-31.
 */
public class ActivityListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ListBean> mList;
    private View.OnClickListener mClickListener;

    public ActivityListAdapter(Context context, List<ListBean> list,
                       View.OnClickListener clickListener) {
        mContext = context;
        mList = list;
        mClickListener = clickListener;
    }

    @Override
    public int getCount() {
        //리스트에 표시될 총 갯수를 반환한다.
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        //현재 위치에서 리스트에 표시하기 위해서 반환되는 아이템객체를 반환한다.
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //화면에 표시될 리스트 항목의 고유번호를 반환한다.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("TEST", "listAdapater==> postion:" + position + ", convertView:" + convertView);

        if(convertView == null) {
            LayoutInflater inflater  =
                    (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_list, null);
        }

        ImageView imgIcon = (ImageView)convertView.findViewById(R.id.imgIcon);
        TextView txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView txtDown = (TextView)convertView.findViewById(R.id.txtDown);
        TextView txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);

        //데이터를 뽑아온다.
        ListBean bean = mList.get(position);
        //각 뷰에 맞는 데이터를 셋팅한다.
        imgIcon.setImageResource( bean.getImgIcon() );
        txtTitle.setText(bean.getTitle());
        txtDown.setText(bean.getDownInfo());
        txtPrice.setText(bean.getPrice());

        convertView.setTag(position);

        convertView.setOnClickListener(mClickListener);


        return convertView;
    }


}
