package com.example.archon.teachmatequestions;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by archon on 11-01-2015.
 */
public class myadapter extends FragmentStatePagerAdapter {



    public myadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        //      Toast.makeText(,"get item called",Toast.LENGTH_LONG).show();
        Fragment fragment=null;
        if(i==0){
            fragment=new mainlistview();
        }
        if(i==1){
            fragment=new Ask_question();
        }/*
        if(i==2){
            fragment=new FragmentC();
        }*/
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        if(position==0){
            return "Question Feed";
        }
        if(position==1){
            return "Ask a Question";
        }
        if(position==2){
            return "fragment3";
        }
        return null;

    }
}