package com.example.archon.teachmatequestions;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity  {

    ViewPager viewPager;
    public ListView list;
    Boolean flag = true;

    EditText etsearch;
    ImageView imageView;

    public ArrayList<Question_Model> questionlist;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Question");

        //actionBar.setDisplayHomeAsUpEnabled(true) ;


        viewPager=(ViewPager)findViewById(R.id.pager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        viewPager.setAdapter(new myadapter(fragmentManager));
        PagerTitleStrip titleStrip = (PagerTitleStrip) findViewById(R.id.title);
        titleStrip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        titleStrip.setNonPrimaryAlpha(0.09f);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.testmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            *//*case R.id.new_question:
                *//**//**//**//* Intent i = new Intent(this.getActivity(), Ask_question.class);
                startActivity(i);*//**//**//**//*
                break;*//*
            case R.id.refresh:
                //Fragment fragment=new mainlistview();



                questionlist.clear();
                Fragment fragment=new mainlistview();



                // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                break;
            case R.id.search_button:
                if(flag){
                    etsearch.setVisibility(View.VISIBLE);
                    flag=false;}
                else if (!flag){
                    etsearch.setVisibility(View.GONE);
                    etsearch.setText("");
                    flag=true;
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }*/



}
