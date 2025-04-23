package com.example.task51c;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlaylistAdapter adapter;
    private List<String> playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        // Get playlist from VideoPlayerActivity
        playlist = getIntent().getStringArrayListExtra("playlist");
        if (playlist == null) {
            playlist = new ArrayList<>();
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rv_playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new PlaylistAdapter(playlist);
        recyclerView.setAdapter(adapter);
    }
} 