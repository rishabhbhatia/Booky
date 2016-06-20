package com.bookyard.booky.ui.activities;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bookyard.booky.interfaces.BookyActivityInterface;
import com.bookyard.booky.utils.Const;

import java.util.List;

public class BookyActivity extends AppCompatActivity implements BookyActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void switchFragment(FragmentManager fm, Fragment fragment, String name, String switchType)
    {
        try {
            if (fm != null) {

                if (fm.getBackStackEntryCount() != 0) {
                    fm.popBackStackImmediate(name, 0);
                }

                Fragment fragmentExisting = fm.findFragmentByTag(name);
                boolean currentlyRunningFrag = false;

                if (fragmentExisting != null) {
                    currentlyRunningFrag = fragmentExisting.isVisible();
                }

                if (!currentlyRunningFrag)
                {
                    FragmentTransaction ft = fm.beginTransaction();

                    if(switchType.equalsIgnoreCase(Const.FRAGMENT_SWITCH_ADD))
                    {
                        ft.add(android.R.id.content, fragment, name).addToBackStack(name);
                    }else
                    {
                        ft.replace(android.R.id.content, fragment, name).addToBackStack(name);
                    }

                    try {
                        ft.commit();
                    }catch (Exception e)
                    {
                        ft.commitAllowingStateLoss();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(Const.TAG, "exception aagayi");
        }
    }

    @Override
    public void removeFragment(FragmentManager fm, Fragment fragment) {
        try {
            fm.beginTransaction().remove(fragment).commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeAllFragments(BookyActivity activity) {
        try {
            List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    activity.getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void switchToPortrait(BookyActivity activity) {
        try {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void switchToLandscape(BookyActivity activity) {
        try {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }catch (Exception e) {e.printStackTrace();}
    }
}
