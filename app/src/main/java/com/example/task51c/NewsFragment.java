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
        topStories.add(new News("Global Tech Summit 2024", "World leaders gather to discuss the future of AI and digital transformation", "https://picsum.photos/300/200?random=1"));
        topStories.add(new News("Climate Change Breakthrough", "Scientists discover new method to reduce carbon emissions by 50%", "https://picsum.photos/300/200?random=2"));
        topStories.add(new News("Space Exploration Milestone", "First manned mission to Mars successfully launched", "https://picsum.photos/300/200?random=3"));
        topStoriesAdapter.setNewsList(topStories);

        List<News> news = new ArrayList<>();
        news.add(new News("New Medical Discovery", "Researchers develop revolutionary treatment for rare genetic diseases", "https://picsum.photos/200/120?random=4"));
        news.add(new News("Economic Growth Report", "Global economy shows signs of recovery with 3.5% growth", "https://picsum.photos/200/120?random=5"));
        news.add(new News("Sports Championship", "Underdog team wins national championship in dramatic final", "https://picsum.photos/200/120?random=6"));
        news.add(new News("Cultural Festival", "Annual international arts festival attracts record attendance", "https://picsum.photos/200/120?random=7"));
        news.add(new News("Education Reform", "New teaching methods show significant improvement in student performance", "https://picsum.photos/200/120?random=8"));
        news.add(new News("Environmental Protection", "Community initiative successfully restores local wildlife habitat", "https://picsum.photos/200/120?random=9"));
        newsAdapter.setNewsList(news);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 