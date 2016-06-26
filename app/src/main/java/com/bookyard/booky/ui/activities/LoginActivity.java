package com.bookyard.booky.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bookyard.booky.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Rishabh Bhatia on 6/12/2016.
 */
public class LoginActivity extends BookyActivity {


    @BindView(R.id.et_login_phone_num)
    EditText etLoginPhoneNum;
    @BindView(R.id.et_login_pass)
    EditText etLoginPass;
    @BindView(R.id.bt_login_action)
    FancyButton btLoginAction;
    @BindView(R.id.tv_login_sign_up)
    TextView tvLoginSignUp;
    @BindView(R.id.tv_login_forgot_pass)
    TextView tvLoginForgotPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        measureLoginButton();
    }

    private void measureLoginButton()
    {
        etLoginPhoneNum.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        etLoginPass.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        etLoginPass.setWidth(etLoginPhoneNum.getMeasuredWidth());
        etLoginPass.setHeight(etLoginPhoneNum.getMeasuredHeight());

        btLoginAction.setMinimumWidth(etLoginPhoneNum.getMeasuredWidth());
        //btLoginAction.setMinimumHeight(etLoginPhoneNum.getMeasuredHeight());
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

    @OnClick(R.id.bt_login_action)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login_action:
                launchActivity();
                break;
        }
    }

    private void launchActivity() {
        Intent intent = new Intent(LoginActivity.this, MainLandingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
        finish();
    }

}
