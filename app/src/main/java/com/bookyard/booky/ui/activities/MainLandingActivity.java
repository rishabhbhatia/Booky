package com.bookyard.booky.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.bookyard.booky.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rishabh Bhatia on 6/12/2016.
 */
public class MainLandingActivity extends BookyActivity {

    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tv_toolbar_label)
    TextView tvToolbarLabel;
    @BindView(R.id.iv_toolbar_search)
    SearchView ivToolbarSearch;
    @BindView(R.id.iv_toolbar_refresh)
    ImageView ivToolbarRefresh;
    @BindView(R.id.rl_toolbar_holder)
    RelativeLayout rlToolbarHolder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_category_left)
    TextView tvCategoryLeft;
    @BindView(R.id.tv_category_right)
    TextView tvCategoryRight;
    @BindView(R.id.card_category)
    CardView cardCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.tv_category_left, R.id.tv_category_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_category_left:
                break;
            case R.id.tv_category_right:
                break;
        }
    }
}
