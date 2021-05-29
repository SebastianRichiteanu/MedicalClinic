package com.example.medicalclinic2.ui.main;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import android.content.SharedPreferences;
import com.example.medicalclinic2.R;
import com.example.medicalclinic2.Tab1Patient;
import com.example.medicalclinic2.Tab2Patient;
import com.example.medicalclinic2.Tab3Patient;
import com.example.medicalclinic2.Tab1Doctor;
import com.example.medicalclinic2.Tab2Doctor;



/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES_PATIENT = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private static final int[] TAB_TITLES_DOCTOR = new int[]{R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    private SharedPreferences sp;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;

        sp = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);
        String role = sp.getString("role", "aaaaa");

        if (role.equals("Patient")) {

            switch (position) {
                case 0:
                    fragment = new Tab1Patient();
                    break;
                case 1:
                    fragment = new Tab2Patient();
                    break;
                case 2:
                    fragment = new Tab3Patient();
                    break;
            }
        } else if (role.equals("Doctor")) {
            switch (position) {
                case 0:
                    fragment = new Tab1Doctor();
                    break;
                case 1:
                    fragment = new Tab2Doctor();
                    break;
            }
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        sp = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);
        String role = sp.getString("role", "aaaaa");
        if (role.equals("Patient"))
            return mContext.getResources().getString(TAB_TITLES_PATIENT[position]);
        return mContext.getResources().getString(TAB_TITLES_DOCTOR[position]);
    }

    @Override
    public int getCount() {
        sp = mContext.getSharedPreferences("login", Context.MODE_PRIVATE);
        String role = sp.getString("role", "aaaaa");
        if (role.equals("Patient")) {
            return 3;
        } else if (role.equals("Doctor")) {
            return 2;
        }
        return 2;
    }
}