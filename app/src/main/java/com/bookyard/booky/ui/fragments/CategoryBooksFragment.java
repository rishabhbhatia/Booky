package com.bookyard.booky.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bookyard.booky.R;
import com.bookyard.booky.adapters.CategoryBooksAdapter;
import com.bookyard.booky.interfaces.RecyclerItemClickListener;
import com.bookyard.booky.models.Book;
import com.bookyard.booky.models.BookCategory;
import com.bookyard.booky.utils.BookyUtils;
import com.bookyard.booky.utils.Const;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 6/21/2016.
 */
public class CategoryBooksFragment extends BookyFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_category_books)
    CollapsingToolbarLayout collapsingCategoryBooks;
    @BindView(R.id.appbar_category_books)
    AppBarLayout appbarCategoryBooks;
    @BindView(R.id.rv_category_books)
    SuperRecyclerView rvCategoryBooks;
    @BindView(R.id.coordinator_category_books)
    CoordinatorLayout coordinatorCategoryBooks;
    @BindView(R.id.sl_book_details)
    SliderLayout slBookDetails;
    @BindView(R.id.sl_indicator_book_details)
    PagerIndicator slIndicatorBookDetails;

    public static CategoryBooksFragment newInstance() {
        return new CategoryBooksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_books, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CategoryBooksAdapter adapter = new CategoryBooksAdapter(getActivity(), generateRandomBooks());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvCategoryBooks.setLayoutManager(gridLayoutManager);
        rvCategoryBooks.setAdapter(adapter);

        Log.d(Const.TAG, "recycler view is setup");

        rvCategoryBooks.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(Const.TAG, "hello to item at pos: " + position);
            }
        }));

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            slBookDetails.addSlider(textSliderView);

            slBookDetails.setPresetTransformer(SliderLayout.Transformer.Accordion);
            slBookDetails.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            slBookDetails.setCustomAnimation(new DescriptionAnimation());
            slBookDetails.setDuration(10000);
            slBookDetails.addOnPageChangeListener(this);
        }
    }

    @Override
    public String onBackPressed() {
        return super.onBackPressed();
    }

    private ArrayList<Book> generateRandomBooks() {
        ArrayList<Book> books = new ArrayList<>();

        DisplayMetrics metrics = BookyUtils.getScreenResolution(getActivity());

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        for (int i = 0; i < 15; i++) {
            Book book = new Book();
            book.setName("Half Girlfriend");
            book.setPrice("Rs 125");
            book.setImageUrl(BookCategory.getDemoImage(String.valueOf(width / 3), String.valueOf(height / 3),
                    "https://pixabay.com/static/uploads/photo/2016" +
                            "/03/11/02/08/speed-1249610_960_720.jpg"));

            books.add(book);
        }

        return books;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
