package com.example.ashi.acm_study;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.concurrent.ExecutionException;

public class video_view extends AppCompatActivity {
    int lastOrientation=0;
    VideoView vidView;
    String vidAddress;
    MediaController vidControl;
    ProgressBar progressBar;
    ProgressBar progressBar2;
    Uri vidUri;
    String video_name;
    String video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video_view);
        vidView = (VideoView) findViewById(R.id.myVideo);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar2);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        video_name = b.getString("VIDEO_NAME");
        video_name=video_name.replaceAll(" ","%20");
        MyAsync my = new MyAsync(getBaseContext(), progressBar2);
        my.execute("https://ashiagarwal73.000webhostapp.com/new/videolink.php?name="+video_name);
        try {

            video = my.get();
            vidAddress=video;
            //videos = video.split("#111#");
            //vidAddress = "https://ashiagarwal73.000webhostapp.com/new/Tune%20Jo%20Na%20kaha,%20Mein%20Woh%20Sunta%20Raha%20HD.flv.mp4";
            vidUri = Uri.parse(vidAddress);
            vidView.setVideoURI(vidUri);
            vidControl = new MediaController(this);
            vidControl.setAnchorView(vidView);
            vidView.setMediaController(vidControl);
            vidView.start();
            if(savedInstanceState!=null)
            {
                int seekvalue=savedInstanceState.getInt("where");
                vidView.seekTo(seekvalue);
            }
            vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    progressBar.setVisibility(View.INVISIBLE);

                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


   // @Override
    /*public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(lastOrientation !=newConfig.orientation)
        {

            lastOrientation= newConfig.orientation;
            vidView = (VideoView) findViewById(R.id.myVideo);
            vidAddress = "https://ashiagarwal73.000webhostapp.com/new/Tune%20Jo%20Na%20kaha,%20Mein%20Woh%20Sunta%20Raha%20HD.flv.mp4";
            vidUri = Uri.parse(vidAddress);
            vidView.setVideoURI(vidUri);
            vidControl = new MediaController(this);
            vidControl.setAnchorView(vidView);
            vidView.setMediaController(vidControl);
            vidView.start();
        }
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        int seekvalue=vidView.getCurrentPosition();
        outState.putInt("where",seekvalue);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}