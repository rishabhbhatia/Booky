package com.bookyard.booky.interfaces;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Rishabh Bhatia on 6/11/2016.
 */
public interface BookyActivityInterface {

    void switchFragment(FragmentManager fm, Fragment fragment, String name, String switchType);

    void removeFragment(FragmentManager fm, Fragment fragment);
}
