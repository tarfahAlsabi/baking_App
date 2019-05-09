package com.example.bakingapp.Utilites;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

public class PlayerFragmetn extends Fragment {

    Context context;
    String url ;
    SimpleExoPlayer player;
    public PlayerFragmetn() {}

    public  void setVaribels(Context context , String url){
        this.context = context;
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.player_fragment, container, false);
        PlayerView playerView = rootView.findViewById(R.id.step_player_view);
        TextView noVideo = rootView.findViewById(R.id.NO_VIDEO);
        if(url == null || url.equals("")){
            noVideo.setVisibility(View.VISIBLE);
            playerView.setVisibility(View.INVISIBLE);
            return rootView;
        }
        if(context == null){
            Log.i("Error","No context in player fragment");
            return rootView;
        }
        player = ExoPlayerFactory.newSimpleInstance(context);
        playerView.setPlayer(player);
        Uri uri = Uri.parse(url);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.getPackageName()));

            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri);


            player.prepare(videoSource);
            player.setPlayWhenReady(true);
            return rootView;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player != null){
            player.stop();
            player.release();
        }

    }
}
