package com.example.task51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.task51c.databinding.FragmentNewsBinding;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private TopStoriesAdapter topStoriesAdapter;
    private NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerViews();
        loadDummyData();
    }

    private void setupRecyclerViews() {
        // Setup Top Stories RecyclerView
        binding.rvTopStories.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        topStoriesAdapter = new TopStoriesAdapter();
        binding.rvTopStories.setAdapter(topStoriesAdapter);

        // Set click listener for top stories
        topStoriesAdapter.setOnNewsClickListener(news -> {
            // Navigate to NewsDetailFragment
            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, NewsDetailFragment.newInstance(news))
                .addToBackStack(null)
                .commit();
        });

        // Setup News RecyclerView with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvNews.setLayoutManager(gridLayoutManager);
        newsAdapter = new NewsAdapter();
        binding.rvNews.setAdapter(newsAdapter);

        // Set click listener for regular news
        newsAdapter.setOnNewsClickListener(news -> {
            // Navigate to NewsDetailFragment
            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, NewsDetailFragment.newInstance(news))
                .addToBackStack(null)
                .commit();
        });
    }

    private void loadDummyData() {
        // Load dummy data for testing
        List<News> topStories = new ArrayList<>();
        topStories.add(new News("Top Story 1", "Description 1", "https://picsum.photos/300/200"));
        topStories.add(new News("Top Story 2", "Description 2", "https://picsum.photos/300/200"));
        topStories.add(new News("Top Story 3", "Description 3", "https://picsum.photos/300/200"));
        topStoriesAdapter.setNewsList(topStories);

        List<News> news = new ArrayList<>();
        news.add(new News("News 1", "This is a detailed description of News 1", "https://picsum.photos/200/120"));
        news.add(new News("News 2", "This is a detailed description of News 2", "https://picsum.photos/200/120"));
        news.add(new News("News 3", "This is a detailed description of News 3", "https://picsum.photos/200/120"));
        news.add(new News("News 4", "This is a detailed description of News 4", "https://picsum.photos/200/120"));
        news.add(new News("News 5", "This is a detailed description of News 5", "https://picsum.photos/200/120"));
        news.add(new News("News 6", "This is a detailed description of News 6", "https://picsum.photos/200/120"));
        newsAdapter.setNewsList(news);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 