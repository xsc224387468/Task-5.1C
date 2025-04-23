package com.example.task51c;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.ArrayList;

public class VideoPlaybackActivity extends AppCompatActivity {
    private YouTubePlayerView youTubePlayerView;
    private Button btnPrevious;
    private Button btnNext;
    private YouTubePlayer youTubePlayer;
    private ArrayList<String> playlist;
    private int currentVideoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);

        // Get playlist from intent
        playlist = getIntent().getStringArrayListExtra("playlist");
        if (playlist == null || playlist.isEmpty()) {
            finish();
            return;
        }

        // Initialize views
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        btnPrevious = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);

        // Setup YouTube Player
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer player) {
                youTubePlayer = player;
                // Play first video
                playCurrentVideo();
            }
        });

        // Setup click listeners
        setupClickListeners();
    }

    private void setupClickListeners() {
        btnPrevious.setOnClickListener(v -> {
            if (currentVideoIndex > 0) {
                currentVideoIndex--;
                playCurrentVideo();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentVideoIndex < playlist.size() - 1) {
                currentVideoIndex++;
                playCurrentVideo();
            }
        });
    }

    private void playCurrentVideo() {
        if (youTubePlayer != null) {
            youTubePlayer.loadVideo(playlist.get(currentVideoIndex), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
} 