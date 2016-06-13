package com.bookyard.booky.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
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
 * Created by Rishabh Bhatia on 6/PADDING_MEDIUM/2016.
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

    private final int PADDING_LARGE = 25;
    private final int PADDING_MEDIUM = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCategoryLeft.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                PADDING_LARGE, PADDING_MEDIUM);
        tvCategoryRight.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                PADDING_LARGE, PADDING_MEDIUM);
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
                tvCategoryLeft.setBackgroundResource(R.drawable.category_left_black_bg);
                tvCategoryRight.setBackgroundResource(R.drawable.category_right_white_bg);
                tvCategoryLeft.setTextColor(Color.parseColor("#FFFFFF"));
                tvCategoryRight.setTextColor(Color.parseColor("#000000"));
                tvCategoryLeft.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                tvCategoryRight.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                break;
            case R.id.tv_category_right:
                tvCategoryRight.setBackgroundResource(R.drawable.category_right_black_bg);
                tvCategoryLeft.setBackgroundResource(R.drawable.category_left_white_bg);
                tvCategoryRight.setTextColor(Color.parseColor("#FFFFFF"));
                tvCategoryLeft.setTextColor(Color.parseColor("#000000"));
                tvCategoryLeft.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                tvCategoryRight.setPadding(PADDING_LARGE, PADDING_MEDIUM,
                        PADDING_LARGE, PADDING_MEDIUM);
                break;
        }
    }
}
