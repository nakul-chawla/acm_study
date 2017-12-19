package com.example.ashi.acm_study;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.net.URL;

public class Topic_one extends Fragment {
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_topic_one,container,false);
        // Inflate the layout for this fragment
        VideoView videoView=(VideoView)view.findViewById(R.id.videovie);
        //setting media content
        final MediaController mediaController=new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        mediaController.setBackgroundColor(Color.TRANSPARENT);
        mediaController.setPadding(0, 0, 0, 825);
        progressBar=view.findViewById(R.id.progressbar);
        //set the url of the video
        Uri uri=Uri.parse("https://ashiagarwal73.000webhostapp.com/new/Tune%20Jo%20Na%20kaha,%20Mein%20Woh%20Sunta%20Raha%20HD.flv.mp4");
        //setting the media player
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
        return view;
    }

}
