package com.bookyard.booky.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bookyard.booky.R;
import com.bookyard.booky.utils.Const;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

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
    @BindView(R.id.bt_facebook_login)
    LoginButton btFacebookLogin;
    @BindView(R.id.ll_login_input_holder)
    LinearLayout llLoginInputHolder;

    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        measureLoginButton();

        setupFacebookLogin();
    }

    private void setupFacebookLogin()
    {
        btFacebookLogin.setReadPermissions(Arrays.asList("public_profile","email"));

        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        btFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Log.d(Const.TAG, "facebook login success: "+loginResult.getAccessToken());

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response)
                            {
                                //TODO fetch user info here
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel()
            {
                Log.d(Const.TAG, "facebook login cancelled");
            }

            @Override
            public void onError(FacebookException exception)
            {
                Log.d(Const.TAG, "facebook login exception");
                exception.printStackTrace();
            }
        });
    }

    private void measureLoginButton()
    {
        etLoginPhoneNum.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        etLoginPass.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        etLoginPass.setWidth(etLoginPhoneNum.getMeasuredWidth());
        etLoginPass.setHeight(etLoginPhoneNum.getMeasuredHeight());

        btLoginAction.setMinimumWidth(etLoginPhoneNum.getMeasuredWidth());
        //btLoginAction.setMinimumHeight(etLoginPhoneNum.getMeasuredHeight());

        btFacebookLogin.setMinimumWidth(etLoginPhoneNum.getMeasuredWidth());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
