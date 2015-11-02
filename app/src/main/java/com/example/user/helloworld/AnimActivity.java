package com.example.user.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends Activity {

    private ImageView mImgIcon;
    private View mLayMenu;
    private boolean mIsOpenMunu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_anim);

        mImgIcon = (ImageView) findViewById(R.id.imgIcon);
        mLayMenu = findViewById(R.id.layMenu); // 캐스팅이 굳이 필요 없다.
        mLayMenu.findViewById(R.id.layMenuTouch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mIsOpenMunu) {
                    Animation slideLeft = AnimationUtils.loadAnimation(AnimActivity.this,R.anim.slide_left);
                    slideLeft.setAnimationListener(slideLeftAnim);
                    mLayMenu.startAnimation(slideLeft);
//                }
            }
        });
//        Animation flow = AnimationUtils.loadAnimation(this, R.anim.flow);
    }

    private Animation.AnimationListener slideLeftAnim = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mLayMenu.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    public void btnStartClick(View view) {
        Animation flow = AnimationUtils.loadAnimation(this,R.anim.flow);
        Animation blink = AnimationUtils.loadAnimation(this,R.anim.blink);
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        Animation slideRight = AnimationUtils.loadAnimation(this,R.anim.slide_right);

        AnimationSet animSet = new AnimationSet(false);
        animSet.addAnimation(flow);
        animSet.addAnimation(blink);
        animSet.addAnimation(shake);

        mLayMenu.setVisibility(View.VISIBLE);
        mLayMenu.startAnimation(slideRight);

        mIsOpenMunu = true;
//        mImgIcon.startAnimation(animSet); // 여러가지를 실행할대
    }
}
