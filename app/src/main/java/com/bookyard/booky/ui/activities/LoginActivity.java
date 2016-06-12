package com.bookyard.booky.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bookyard.booky.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rishabh Bhatia on 6/12/2016.
 */
public class LoginActivity extends BookyActivity {


    @BindView(R.id.et_login_phone_num)
    EditText etLoginPhoneNum;
    @BindView(R.id.et_login_pass)
    EditText etLoginPass;
    @BindView(R.id.bt_login_action)
    Button btLoginAction;
    @BindView(R.id.bt_login_sign_up)
    Button btLoginSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.bt_login_action, R.id.bt_login_sign_up})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login_action:
                launchActivity();
                break;
            case R.id.bt_login_sign_up:
                launchActivity();
                break;
        }
    }

    private void launchActivity()
    {
        Intent intent = new Intent(LoginActivity.this, MainLandingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
        finish();
    }

}
