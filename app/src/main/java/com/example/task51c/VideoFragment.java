package com.example.task51c;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.example.task51c.databinding.FragmentVideoBinding;
import com.example.task51c.databinding.DialogAddVideoBinding;

public class VideoFragment extends Fragment {
    private static final String TAG = "VideoFragment";
    private FragmentVideoBinding binding;
    private YouTubePlayerView youTubePlayerView;
    private String currentVideoId = "dQw4w9WgXcQ"; // Default video ID

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            binding = FragmentVideoBinding.inflate(inflater, container, false);
            return binding.getRoot();
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreateView", e);
            Toast.makeText(requireContext(), "Error loading video interface", Toast.LENGTH_SHORT).show();
            return inflater.inflate(R.layout.fragment_error, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            setupYouTubePlayer();
            setupViewPager();
            setupFab();
        } catch (Exception e) {
            Log.e(TAG, "Error in onViewCreated", e);
            Toast.makeText(requireContext(), "Error initializing video player", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupYouTubePlayer() {
        try {
            youTubePlayerView = binding.youtubePlayerView;
            getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    try {
                        youTubePlayer.loadVideo(currentVideoId, 0);
                    } catch (Exception e) {
                        Log.e(TAG, "Error loading video", e);
                        Toast.makeText(requireContext(), "Error loading video", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error setting up YouTube player", e);
            Toast.makeText(requireContext(), "Error setting up video player", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupViewPager() {
        try {
            ViewPager2 viewPager = binding.viewPager;
            TabLayout tabLayout = binding.tabLayout;

            viewPager.setAdapter(new FragmentStateAdapter(this) {
                @NonNull
                @Override
                public Fragment createFragment(int position) {
                    // Return appropriate fragment based on position
                    return position == 0 ? new VideoListFragment() : new PlaylistFragment();
                }

                @Override
                public int getItemCount() {
                    return 2;
                }
            });

            new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
                tab.setText(position == 0 ? R.string.tab_videos : R.string.tab_playlists);
            }).attach();
        } catch (Exception e) {
            Log.e(TAG, "Error setting up ViewPager", e);
            Toast.makeText(requireContext(), "Error setting up video tabs", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupFab() {
        try {
            binding.fabAdd.setOnClickListener(v -> showAddVideoDialog());
        } catch (Exception e) {
            Log.e(TAG, "Error setting up FAB", e);
        }
    }

    private void showAddVideoDialog() {
        try {
            DialogAddVideoBinding dialogBinding = DialogAddVideoBinding.inflate(getLayoutInflater());

            new MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.add_video)
                .setView(dialogBinding.getRoot())
                .setPositiveButton(R.string.add, (dialog, which) -> {
                    String videoUrl = dialogBinding.etVideoUrl.getText().toString();
                    String videoId = extractVideoId(videoUrl);
                    if (videoId != null) {
                        currentVideoId = videoId;
                        youTubePlayerView.getYouTubePlayerWhenReady(player -> 
                            player.loadVideo(videoId, 0));
                    } else {
                        Toast.makeText(requireContext(), "Invalid YouTube URL", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
        } catch (Exception e) {
            Log.e(TAG, "Error showing add video dialog", e);
            Toast.makeText(requireContext(), "Error showing dialog", Toast.LENGTH_SHORT).show();
        }
    }

    private String extractVideoId(String videoUrl) {
        if (videoUrl == null || videoUrl.trim().isEmpty()) {
            return null;
        }
        try {
            if (videoUrl.contains("youtu.be/")) {
                return videoUrl.substring(videoUrl.lastIndexOf("/") + 1);
            } else if (videoUrl.contains("youtube.com/watch?v=")) {
                return videoUrl.substring(videoUrl.indexOf("=") + 1);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error extracting video ID", e);
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (youTubePlayerView != null) {
            youTubePlayerView.release();
        }
        binding = null;
    }
} 