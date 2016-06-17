package com.bookyard.booky.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.bookyard.booky.R;
import com.bookyard.booky.adapters.MainLandingCategoryAdapter;
import com.bookyard.booky.models.BookCategory;
import com.bookyard.booky.utils.Const;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

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
    @BindView(R.id.rv_main_landing)
    SuperRecyclerView rvMainLanding;

    private final int PADDING_LARGE = 25;
    private final int PADDING_MEDIUM = 15;

    //Cloudinary setup for cdn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);
        ButterKnife.bind(this);

        populateDummyCategories();
    }

    private void populateDummyCategories()
    {
        ArrayList<String> categoryNames = new ArrayList<>();
        categoryNames.add("Thriller");
        categoryNames.add("Drama");
        categoryNames.add("Romance");
        categoryNames.add("Misery");
        categoryNames.add("Comic");
        categoryNames.add("Animes");
        categoryNames.add("Biography");
        categoryNames.add("Discovery");
        categoryNames.add("Travel");
        categoryNames.add("Fashion");
        categoryNames.add("Magazines");

        ArrayList<BookCategory> bookCategories = new ArrayList<>();

        for(int i =0; i<categoryNames.size(); i++)
        {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setName(categoryNames.get(i));
            bookCategory.setPopularAuthors("Daniel Judson, James Patterson, Marc McCluskey");
            bookCategory.setImageUrl(bookCategory.getDemoImage("60","60","https://pixabay.com/static/uploads/photo/2016" +
                    "/03/11/02/08/speed-1249610_960_720.jpg"));

            bookCategories.add(bookCategory);

            if(i == 0)  Log.d(Const.TAG, bookCategory.getImageUrl());

        }

        MainLandingCategoryAdapter adapter = new MainLandingCategoryAdapter(MainLandingActivity.this, bookCategories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainLandingActivity.this);
        rvMainLanding.setLayoutManager(layoutManager);

        rvMainLanding.setAdapter(adapter);
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
