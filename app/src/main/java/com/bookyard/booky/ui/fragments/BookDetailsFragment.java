package com.bookyard.booky.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bookyard.booky.R;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rishabh Bhatia on 6/22/2016.
 */
public class BookDetailsFragment extends BookyFragment implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {


    @BindView(R.id.sl_book_details)
    SliderLayout slBookDetails;
    @BindView(R.id.sl_indicator_book_details)
    PagerIndicator slIndicatorBookDetails;
    @BindView(R.id.rl_book_details_slider)
    RelativeLayout rlBookDetailsSlider;
    @BindView(R.id.rb_book_details_rating)
    RatingBar rbBookDetailsRating;
    @BindView(R.id.tv_book_details_name)
    TextView tvBookDetailsName;
    @BindView(R.id.tv_book_details_price)
    TextView tvBookDetailsPrice;
    @BindView(R.id.tv_book_details_summary)
    TextView tvBookDetailsSummary;
    @BindView(R.id.tv_book_details_show_more)
    TextView tvBookDetailsShowMore;
    @BindView(R.id.prl_book_details_holder)
    RelativeLayout prlBookDetailsHolder;
    @BindView(R.id.sv_book_details_holder)
    ScrollView svBookDetailsHolder;

    public static BookDetailsFragment newInstance() {
        return new BookDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadBookSlider();
    }

    private void loadBookSlider() {
        try {

            HashMap<String, String> url_maps = new HashMap<String, String>();
            url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
            url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
            url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
            url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

            for (String name : url_maps.keySet()) {
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
                        .putString("extra", name);

                slBookDetails.addSlider(textSliderView);

                slBookDetails.setPresetTransformer(SliderLayout.Transformer.Accordion);
                slBookDetails.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                slBookDetails.setCustomAnimation(new DescriptionAnimation());
                slBookDetails.setDuration(10000);
                slBookDetails.addOnPageChangeListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public String onBackPressed() {
        return super.onBackPressed();
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

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
