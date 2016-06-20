package com.bookyard.booky.interfaces;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bookyard.booky.ui.activities.BookyActivity;

/**
 * Created by Rishabh Bhatia on 6/11/2016.
 */
public interface BookyActivityInterface {

    void switchFragment(FragmentManager fm, Fragment fragment, String name, String switchType);

    void removeFragment(FragmentManager fm, Fragment fragment);

    void removeAllFragments(BookyActivity activity);

    void switchToPortrait(BookyActivity activity);

    void switchToLandscape(BookyActivity activity);
}
