package com.bookyard.booky.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bookyard.booky.BookyApplication;
import com.bookyard.booky.R;
import com.bookyard.booky.utils.BookyUtils;
import com.bookyard.booky.utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 6/12/2016.
 */
public class SplashScreenActivity extends BookyActivity {

    @BindView(R.id.iv_splash_logo)
    ImageView ivSplashLogo;
    @BindView(R.id.tv_splash_tagline)
    TextView tvSplashTagline;
    @BindView(R.id.tv_splash_author)
    TextView tvSplashAuthor;
    @BindView(R.id.progress_splash)
    ProgressBar progressSplash;
    private Handler handler = null;
    private Runnable runnable = null;
    private final int SPLASH_TIME = 10000;
    private AsyncTask<Void, Void, Void> quoteOfDayAsync = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        quoteOfDayAsync = new getQuoteOfDay().execute();
    }

    @Override
    protected void onDestroy() {
        try {
            if (quoteOfDayAsync != null) {
                quoteOfDayAsync.cancel(true);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    class getQuoteOfDay extends AsyncTask<Void, Void, Void>
    {

        private String quoteOfTheDayText = "";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(Const.QUOTE_OF_DAY_URL);
                Log.d(Const.TAG, "hello url: " + url.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(30 * 1000);
                connection.connect();

                Log.d(Const.TAG, "hello hello mr. connection, ur here");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                if (stringBuilder.toString() != null && !stringBuilder.toString().equalsIgnoreCase(""))
                {
                    quoteOfTheDayText = stringBuilder.toString();
                    Log.d(Const.TAG, "quote json resp: "+quoteOfTheDayText);
                }
            }catch (Exception e) {
                Log.d(Const.TAG, "exception aagayi");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(SplashScreenActivity.this.isFinishing()) return;

            try {

                if(quoteOfTheDayText == null || quoteOfTheDayText.trim().equalsIgnoreCase(""))
                {
                    tvSplashTagline.setVisibility(View.VISIBLE);
                }else
                {
                    JSONObject quotesResp = new JSONObject(quoteOfTheDayText);
                    parseQuotesResponse(quotesResp);
                }

            }catch (Exception e) {
                e.printStackTrace();
                tvSplashTagline.setVisibility(View.VISIBLE);
            }
        }
    }

    private void parseQuotesResponse(JSONObject quotesResp)
    {
        try {
            if(quotesResp.has(Const.QUOTE_CONTENTS_KEY) && !quotesResp.isNull(Const.QUOTE_CONTENTS_KEY))
            {
                JSONObject quoteContentsObject = quotesResp.getJSONObject(Const.QUOTE_CONTENTS_KEY);

                if(quoteContentsObject.has(Const.QUOTE_QUOTES_KEY) && !quoteContentsObject.isNull(Const.QUOTE_QUOTES_KEY))
                {
                    JSONArray quotesArray = quoteContentsObject.getJSONArray(Const.QUOTE_QUOTES_KEY);

                    JSONObject quoteObject = quotesArray.getJSONObject(0);

                    if(quoteObject != null && quoteObject.has(Const.QUOTE_QUOTE_KEY) && !quoteObject.isNull(Const.QUOTE_QUOTE_KEY))
                    {
                        String quote = quoteObject.getString(Const.QUOTE_QUOTE_KEY);

                        if(quote != null && !quote.equalsIgnoreCase(""))
                        {
                            tvSplashTagline.setText(quote);

                            BookyApplication.quoteOfDay = quote;
                        }
                    }

                    if(quoteObject != null && quoteObject.has(Const.QUOTE_AUTHOR_KEY) && !quoteObject.isNull(Const.QUOTE_AUTHOR_KEY))
                    {
                        String author = quoteObject.getString(Const.QUOTE_AUTHOR_KEY);

                        if(author != null && !author.equalsIgnoreCase(""))
                        {
                            tvSplashAuthor.setVisibility(View.VISIBLE);
                            tvSplashAuthor.setText("- "+author+ " -");
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {

        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
            handler = null;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler == null) {
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

            handler.postDelayed(runnable, SPLASH_TIME);
        }
    }

    private void launchActivity() {
        Intent intent;
        boolean isUserLoggedIn = BookyUtils.getSharedPrefs(SplashScreenActivity.this).getBoolean
                (Const.USER_LOGIN_STATUS, false);

        if (isUserLoggedIn) {
            intent = new Intent(SplashScreenActivity.this, MainLandingActivity.class);
        } else {
            intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
        finish();
    }

}
