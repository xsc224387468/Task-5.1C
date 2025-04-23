package com.example.task51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment {
    private ImageView newsImage;
    private TextView newsTitle;
    private TextView newsContent;
    private RecyclerView relatedNewsRecyclerView;
    private RelatedNewsAdapter relatedNewsAdapter;
    private News currentNews;

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("news", news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentNews = getArguments().getParcelable("news");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        
        // Initialize views
        newsImage = view.findViewById(R.id.iv_news_image);
        newsTitle = view.findViewById(R.id.tv_news_title);
        newsContent = view.findViewById(R.id.tv_news_content);
        relatedNewsRecyclerView = view.findViewById(R.id.rv_related_news);

        // Setup RecyclerView
        relatedNewsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        relatedNewsAdapter = new RelatedNewsAdapter();
        relatedNewsRecyclerView.setAdapter(relatedNewsAdapter);

        // Set click listener for related news
        relatedNewsAdapter.setOnNewsClickListener(news -> {
            // Replace current fragment with new instance
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, NewsDetailFragment.newInstance(news))
                    .addToBackStack(null)
                    .commit();
        });

        populateNewsDetail();
        loadRelatedNews();

        return view;
    }

    private void populateNewsDetail() {
        if (currentNews != null) {
            // Load image using Glide
            Glide.with(requireContext())
                    .load(currentNews.getImageUrl())
                    .into(newsImage);

            newsTitle.setText(currentNews.getTitle());
            newsContent.setText(currentNews.getContent());
        }
    }

    private void loadRelatedNews() {
        // TODO: Replace with actual related news loading logic
        List<News> relatedNews = new ArrayList<>();
        // Add dummy related news
        relatedNews.add(new News("Related News 1", "Content 1", "https://picsum.photos/300/200"));
        relatedNews.add(new News("Related News 2", "Content 2", "https://picsum.photos/300/200"));
        relatedNews.add(new News("Related News 3", "Content 3", "https://picsum.photos/300/200"));
        
        relatedNewsAdapter.setNewsList(relatedNews);
    }
} 