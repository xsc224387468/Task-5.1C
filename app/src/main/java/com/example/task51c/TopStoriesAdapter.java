package com.example.task51c;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.task51c.databinding.ItemNewsBinding;

public class TopStoriesAdapter extends NewsAdapter {
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        // We can customize the view for top stories here if needed
        return new NewsViewHolder(binding);
    }
} 