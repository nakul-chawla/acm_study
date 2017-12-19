package com.example.ashi.acm_study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

public class splash_screen extends AppCompatActivity {
    ProgressBar progressBar;
    Thread myThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        myThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(500);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        };
        myThread.start();
    }

}
