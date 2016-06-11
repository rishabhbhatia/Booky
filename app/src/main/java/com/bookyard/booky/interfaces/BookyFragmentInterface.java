package com.bookyard.booky.interfaces;

import com.bookyard.booky.BookyActivity;
import com.bookyard.booky.BookyFragment;

/**
 * Created by Rishabh Bhatia on 6/11/2016.
 */
public interface BookyFragmentInterface {

    String onBackPressed();

    void removeSelf(BookyActivity activity, BookyFragment fragment);

    void popFragmentBackStackImmediate(BookyActivity activity);
}
