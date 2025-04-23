package com.example.task51c;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.task51c.databinding.ItemRelatedNewsBinding;
import java.util.ArrayList;
import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.RelatedNewsViewHolder> {
    private List<News> newsList = new ArrayList<>();
    private OnNewsClickListener onNewsClickListener;

    public interface OnNewsClickListener {
        void onNewsClick(News news);
    }

    public void setOnNewsClickListener(OnNewsClickListener listener) {
        this.onNewsClickListener = listener;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RelatedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRelatedNewsBinding binding = ItemRelatedNewsBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        return new RelatedNewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedNewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.bind(news);
        
        holder.itemView.setOnClickListener(v -> {
            if (onNewsClickListener != null) {
                onNewsClickListener.onNewsClick(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class RelatedNewsViewHolder extends RecyclerView.ViewHolder {
        private final ItemRelatedNewsBinding binding;

        RelatedNewsViewHolder(ItemRelatedNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(News news) {
            binding.tvTitle.setText(news.getTitle());
            binding.tvDescription.setText(news.getContent());
            
            Glide.with(binding.getRoot().getContext())
                .load(news.getImageUrl())
                .centerCrop()
                .into(binding.ivNews);
        }
    }
} 