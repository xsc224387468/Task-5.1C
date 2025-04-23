package com.example.task51c;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class VideoPlayerActivity extends AppCompatActivity {
    private EditText etVideoUrl;
    private Button btnPlay;
    private Button btnAddToPlaylist;
    private Button btnMyPlaylist;
    private List<String> playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        // Initialize views
        etVideoUrl = findViewById(R.id.et_video_url);
        btnPlay = findViewById(R.id.btn_play);
        btnAddToPlaylist = findViewById(R.id.btn_add_to_playlist);
        btnMyPlaylist = findViewById(R.id.btn_my_playlist);

        // Initialize playlist
        playlist = new ArrayList<>();

        // Setup click listeners
        setupClickListeners();
    }

    private void setupClickListeners() {
        btnPlay.setOnClickListener(v -> {
            if (playlist.isEmpty()) {
                Toast.makeText(this, "Playlist is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            // Navigate to video playback activity
            Intent intent = new Intent(VideoPlayerActivity.this, VideoPlaybackActivity.class);
            intent.putStringArrayListExtra("playlist", new ArrayList<>(playlist));
            startActivity(intent);
        });

        btnAddToPlaylist.setOnClickListener(v -> {
            String videoUrl = etVideoUrl.getText().toString().trim();
            if (TextUtils.isEmpty(videoUrl)) {
                Toast.makeText(this, "Please enter a video URL", Toast.LENGTH_SHORT).show();
                return;
            }

            String videoId = extractVideoId(videoUrl);
            if (videoId != null) {
                playlist.add(videoId);
                etVideoUrl.setText("");
                Toast.makeText(this, "Video added to playlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid YouTube URL", Toast.LENGTH_SHORT).show();
            }
        });

        btnMyPlaylist.setOnClickListener(v -> {
            // Navigate to playlist page
            Intent intent = new Intent(VideoPlayerActivity.this, PlaylistActivity.class);
            intent.putStringArrayListExtra("playlist", new ArrayList<>(playlist));
            startActivity(intent);
        });
    }

    private String extractVideoId(String url) {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        try {
            if (url.contains("youtu.be/")) {
                return url.substring(url.lastIndexOf("/") + 1);
            } else if (url.contains("youtube.com/watch?v=")) {
                return url.substring(url.indexOf("=") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
} 