package com.example.guilhermedeconto.appextrato.splashScreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guilhermedeconto.appextrato.R;
import com.example.guilhermedeconto.appextrato.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    protected ImageView tvIcoded;
    protected TextView tvMobileSolutions;
    protected TextView tvAllRights;
    protected boolean onPause = false;
    protected boolean onStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loadComponents();
    }

    private void loadComponents() {
        tvIcoded = findViewById(R.id.tvIcoded);
        tvMobileSolutions = findViewById(R.id.tvMobileSolutions);
        tvAllRights = findViewById(R.id.tvAllRights);
        run();
    }

    private void run() {
        new Handler().postDelayed(() -> {
            animate(tvIcoded);
            animate(tvAllRights);
            animate(tvMobileSolutions);
        }, 2000);
    }

    private void animate(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 1, 0);
        animator.setDuration(300);
        animator.setRepeatCount(0);
        animator.addListener(listener());
        animator.start();
    }

    private AnimatorListenerAdapter listener() {
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finishScreen();
            }
        };
    }

    private void finishScreen() {
        if (this.isFinishing() || this.onPause || this.onStop) return;

        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setInterpolator(new AccelerateDecelerateInterpolator());
        alpha.setDuration(250);
        tvAllRights.startAnimation(alpha);
        tvIcoded.startAnimation(alpha);
        tvMobileSolutions.startAnimation(alpha);
        tvIcoded.setVisibility(View.VISIBLE);
        tvMobileSolutions.setVisibility(View.VISIBLE);
        tvAllRights.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onStop || onPause)
            run();

        onStop = false;
        onPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        onStop = true;
    }
}