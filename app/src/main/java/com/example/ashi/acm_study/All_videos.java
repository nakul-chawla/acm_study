package com.example.ashi.acm_study;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class All_videos extends AppCompatActivity {
    ListView topics_list;
    String sub,topic;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_videos);
        progressBar= (ProgressBar) findViewById(R.id.progress4);
        //if (isOnline()) {
            Intent i = getIntent();
            Bundle b = i.getExtras();
            sub = b.getString("sub");
            sub=sub.replaceAll(" ","%20");
            // Toast.makeText(this, sub, Toast.LENGTH_SHORT).show();
            MyAsync my = new MyAsync(getBaseContext(),progressBar);
            my.execute("https://ashiagarwal73.000webhostapp.com/new/videos.php?name="+sub);
            String[] topics  = null;
            try {
                topic = my.get();
                topics = topic.split("#111#");
                topics_list = (ListView) findViewById(R.id.videoslist);
                ArrayAdapter<String> aA = new ArrayAdapter<String>(All_videos.this, android.R.layout.simple_list_item_1, topics);
                topics_list.setAdapter(aA);
                topics_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String t = adapterView.getItemAtPosition(i).toString();
                        progressBar.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(All_videos.this, video_view.class);
                        Bundle b = new Bundle();
                        b.putString("VIDEO_NAME",t);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        //}
    }
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}