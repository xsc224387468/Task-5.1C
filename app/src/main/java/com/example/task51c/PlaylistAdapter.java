package com.example.task51c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {
    private List<String> playlist;

    public PlaylistAdapter(List<String> playlist) {
        this.playlist = playlist;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        String videoId = playlist.get(position);
        holder.textView.setText("Video " + (position + 1) + ": " + videoId);
    }

    @Override
    public int getItemCount() {
        return playlist.size();
    }

    static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        PlaylistViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
} 