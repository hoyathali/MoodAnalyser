package com.havap.moodanalyser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyAdapter extends FragmentStatePagerAdapter {
    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Fragment f1 = new singin();
            Bundle b = new Bundle();
            b.putInt("pagenumber", position + 1);
            f1.setArguments(b);
            return f1;
        }
        else {
            Fragment f1 = new login();
            Bundle b = new Bundle();
            b.putInt("pagenumber", position + 1);
            f1.setArguments(b);
            return f1;

        }
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String s="";
        switch (position)
        {
            case 0:
                s="Login";
                break;
            case 1:
                s="Sign in";
                break;
        }
        return s;
    }
}
