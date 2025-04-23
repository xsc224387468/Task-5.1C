package com.example.task51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.task51c.databinding.FragmentItubeBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

public class iTubeFragment extends Fragment {
    private FragmentItubeBinding binding;
    private YouTubePlayer youTubePlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItubeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        getLifecycle().addObserver(binding.youtubePlayer);
        
        binding.youtubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer player) {
                youTubePlayer = player;
                // Load a default video
                player.loadVideo("dQw4w9WgXcQ", 0);
            }
        });

        binding.btnAddToPlaylist.setOnClickListener(v -> {
            String videoUrl = binding.etVideoUrl.getText().toString();
            if (!videoUrl.isEmpty()) {
                // Extract video ID and add to playlist
                String videoId = extractYouTubeId(videoUrl);
                if (videoId != null) {
                    // TODO: Add to Room database
                    binding.etVideoUrl.setText("");
                }
            }
        });
    }

    private String extractYouTubeId(String url) {
        if (url != null && url.trim().length() > 0) {
            String videoId = url;
            if (url.contains("youtu.be/")) {
                videoId = url.substring(url.lastIndexOf("/") + 1);
            } else if (url.contains("watch?v=")) {
                videoId = url.substring(url.indexOf("watch?v=") + 8);
            }
            int ampersandPosition = videoId.indexOf('&');
            if (ampersandPosition != -1) {
                videoId = videoId.substring(0, ampersandPosition);
            }
            return videoId;
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 