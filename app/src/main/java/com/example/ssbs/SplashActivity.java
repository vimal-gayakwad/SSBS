package com.example.ssbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssbs.login.SignInActivity;

/*s
 * Created By Vimal
 * */
public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    private ImageView imageView;

    //After completion of 2000 milliseconds,the next activity will get Started.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method used to cover the flash screen
        //to entire activity
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.imageView);
        Animation mAnim = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        imageView.setAnimation(mAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}