package com.bookyard.booky.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookyard.booky.R;

/**
 * Created by Rishabh Bhatia on 6/22/2016.
 */
public class BookDetailsFragment extends BookyFragment {


    public static BookDetailsFragment newInstance() {
        return new BookDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public String onBackPressed() {
        return super.onBackPressed();
    }
}
