package com.example.user.helloworld;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ScrollActivity extends AppCompatActivity {

    private ImageView mImgView;
    private int mImageCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);


        Toast.makeText(this, R.string.btnImageChange, Toast.LENGTH_LONG).show();
//        String aa = getResources().getString(R.string.btnImageChange);

        Button btnChange = (Button) findViewById(R.id.btnChange);
        mImgView = (ImageView) findViewById(R.id.imageView);

        btnChange.setOnClickListener(btnClickMeClick2);
    }

    /**
     * 큰용량 이미지 버튼
     */
    private View.OnClickListener btnClickMeClick2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            try {
//                BitmapDrawable bmp = null;
//                int bmpW = 0, bmpH = 0;
//
//                if(bmp != null) {
//                    bmp = null;
//                }
//
//                ImageView largeImage = (ImageView) findViewById(R.id.img_big2);
//                Display display = getWindowManager().getDefaultDisplay();
//                int displayWidth = display.getWidth();
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                BitmapFactory.decodeResource(getResources(), R.drawable.largeimage, options);
//                int width = options.outWidth;
//                if (width > displayWidth) {
//                    int widthRatio = Math.round((float) width / (float) displayWidth);
//                    options.inSampleSize = widthRatio;
//                }
//                options.inJustDecodeBounds = false;
//                Bitmap scaledBitmap =  BitmapFactory.decodeResource(getResources(),
//                        R.drawable.largeimage, options);
//                largeImage.setImageBitmap(scaledBitmap);
//
//
//                switch (mImageCounter) {
//                    case 0:
//                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big1);
//                        break;
//                    case 1:
//                        // 숙제
//                        // bitmap 이미지를 에러 나지 않도록 수정해봐라
//                        //
//                        /**
//
//                         */
//                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big3);
//                        break;
//                    case 2:
//                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big3);
//                        break;
//                    case 3:
//                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big4);
//                        break;
//                    case 4:
//                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big5);
//                        break;
//                    default:
//                        break;
//                }
//
//                // 불러온 이미지의 가로, 세로 너비를 구한다.
//                bmpW = bmp.getIntrinsicWidth();
//                bmpH = bmp.getIntrinsicHeight();
//
//                mImageCounter++;
//
//                if (mImageCounter > 4) {
//                    mImageCounter = 0;
//                }
//
//                mImgView.setImageDrawable(bmp);
//                mImgView.getLayoutParams().width = bmpW;
//                mImgView.getLayoutParams().height = bmpH;
//
//
//            } catch (OutOfMemoryError error) {
//                Log.e("TEST", error.toString());
//            }
        }

    };


    private View.OnClickListener btnClickMeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                BitmapDrawable bmp = null;
                int bmpW = 0, bmpH = 0;

                if(bmp != null) {
                    bmp = null;
                }

                switch (mImageCounter) {
                    case 0:
                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big1);
                        break;
                    case 1:
                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big3);
                        break;
                    case 2:
                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big3);
                        break;
                    case 3:
                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big4);
                        break;
                    case 4:
                        bmp = (BitmapDrawable) getResources().getDrawable(R.drawable.img_big5);
                        break;
                    default:
                        break;
                }

                // 불러온 이미지의 가로, 세로 너비를 구한다.
                bmpW = bmp.getIntrinsicWidth();
                bmpH = bmp.getIntrinsicHeight();

                mImageCounter++;

                if (mImageCounter > 4) {
                    mImageCounter = 0;
                }

                mImgView.setImageDrawable(bmp);
                mImgView.getLayoutParams().width = bmpW;
                mImgView.getLayoutParams().height = bmpH;


            } catch (OutOfMemoryError error) {
                Log.e("TEST", error.toString());
            }
        }

    };

}
