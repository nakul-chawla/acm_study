package com.example.ashi.acm_study;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class new1 extends AppCompatActivity {
    TextView t1,t2; int NUM=3;
    ViewPager vPagerMain;
    CustomPageAdapter cpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new1);
      /*  t1= (TextView) findViewById(R.id.t1);
        t2= (TextView) findViewById(R.id.t2);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        t1.setText("your semester is: "+b.getString("sem"));
        t2.setText("your subject  is: "+b.getString("sub"));*/
        vPagerMain=(ViewPager)findViewById(R.id.vPagerMain);
        cpa=new CustomPageAdapter(getSupportFragmentManager());
        vPagerMain.setAdapter(cpa);

        }
        public class CustomPageAdapter extends FragmentPagerAdapter
        {

            CustomPageAdapter(FragmentManager fragmentManager){
                super(fragmentManager);
            }
            @Override
            public Fragment getItem(int position) {
                switch (position)
                {
                    case 0: return new Topic_one();
                    case 1: return new Questions();
                    case 2: return new Solutions();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return NUM;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch(position){
                    case 0: return "Study Material";
                    case 1: return "Questions";
                    case 2: return "Solutions";
                    default: return null;
                }

            }

        }

    }

