package com.bookyard.booky.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookyard.booky.interfaces.BookyFragmentInterface;
import com.bookyard.booky.ui.activities.BookyActivity;

/**
 * Created by Rishabh Bhatia on 6/11/2016.
 */
public class BookyFragment extends Fragment implements BookyFragmentInterface {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
        return "";
    }

    @Override
    public void removeSelf(BookyActivity activity, BookyFragment fragment) {
        try {
            activity.removeFragment(activity.getSupportFragmentManager(), fragment);
        }catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void popFragmentBackStackImmediate(BookyActivity activity) {
        try {
            activity.getSupportFragmentManager().popBackStackImmediate();
        }catch (Exception e) {e.printStackTrace();}
    }

}
