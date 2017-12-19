package com.example.ashi.acm_study;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    String sub;
    String str,s;int c=0;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar= (ProgressBar) findViewById(R.id.progress2);
        progressBar.setVisibility(View.VISIBLE);
        if (isOnline()) {
            //Intent i = getIntent();
            Spinner spin = (Spinner) findViewById(R.id.spin2);
            //s = i.getStringExtra("sem");
            MyAsync my = new MyAsync(getBaseContext(),progressBar);
            my.execute("https://ashiagarwal73.000webhostapp.com/new/domain.php?name=");
            try {
                sub = my.get();
            String[] semester = null;
            sub = "Choose Your Domain#111#" + sub;
            semester = sub.split("#111#");
            ArrayAdapter<String> aA = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, semester);
            spin.setAdapter(aA);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    str = adapterView.getItemAtPosition(i).toString();
                    c = i;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            Button proceed = (Button) findViewById(R.id.proceed2);
            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (c == 0) {
                        Toast.makeText(MainActivity.this, "Please choose valid Domain", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(MainActivity.this, topic.class);
                        Bundle b = new Bundle();
                        b.putString("sub", str);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
            });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    public void onBackPressed() {
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setTitle("Really Exit?");
        ad.setMessage("Are you sure you want to exit?");
        ad.setNegativeButton(android.R.string.no, null);
        ad.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }
        ).create().show();
    }
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder ad=new AlertDialog.Builder(this);
            ad.setTitle("No Internet!");
            ad.setMessage("Check your Internet connection and restart the app");
            ad.setNegativeButton(android.R.string.cancel, null);
            ad.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
                    }
            ).create().show();

            return false;
        }
        return true;
    }

    private void hello(){}
}