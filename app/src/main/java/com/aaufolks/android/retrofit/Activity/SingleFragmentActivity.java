package com.aaufolks.android.retrofit.Activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.aaufolks.android.retrofit.R;

/**
 * Created by michalisgratsias on 01/11/18.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();               // abstract method not implemented here

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());              // view inflated from xml layout
        FragmentManager fm = getSupportFragmentManager();       // responsible for managing Fragments and adding their Views
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);   // using Support Library - give frmt to mgr.
        if (fragment == null) {                                 // maybe this ID is saved on rotation by fr.mgr, and is not null
            fragment = createFragment();                        // abstract method called
            fm.beginTransaction()                               // create a new FT (Fragment Transaction Object)
                    .add(R.id.fragment_container, fragment)     // include an ADD operation on it (identified by resource ID of container view)
                    .commit(); }                                // and commit the fragment transaction to the list of the mgr
    }

}
