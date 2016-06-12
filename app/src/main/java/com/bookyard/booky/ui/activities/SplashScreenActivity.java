package com.bookyard.booky.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bookyard.booky.R;
import com.bookyard.booky.utils.BookyUtils;
import com.bookyard.booky.utils.Const;

/**
 * Created by Rishabh Bhatia on 6/12/2016.
 */
public class SplashScreenActivity extends BookyActivity {

    private Handler handler = null;
    private Runnable runnable = null;
    private final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onPause() {

        if(handler != null && runnable != null)
        {
            handler.removeCallbacks(runnable);
            handler = null;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(handler == null)
        {
            handler = new Handler();

            runnable = new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            launchActivity();
                        }
                    });
                }
            };

            handler.postDelayed(runnable,SPLASH_TIME);
        }
    }

    private void launchActivity()
    {
        Intent intent;
        boolean isUserLoggedIn = BookyUtils.getSharedPrefs(SplashScreenActivity.this).getBoolean
                (Const.USER_LOGIN_STATUS, false);

        if(isUserLoggedIn)
        {
            intent = new Intent(SplashScreenActivity.this, MainLandingActivity.class);
        }else
        {
            intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
        finish();
    }

}
