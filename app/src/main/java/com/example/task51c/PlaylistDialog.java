package com.example.task51c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlaylistDialog extends DialogFragment {
    private List<String> playlist;
    private PlaylistAdapter adapter;

    public PlaylistDialog(List<String> playlist) {
        this.playlist = playlist;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_playlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        
        adapter = new PlaylistAdapter(playlist);
        recyclerView.setAdapter(adapter);
    }
} 