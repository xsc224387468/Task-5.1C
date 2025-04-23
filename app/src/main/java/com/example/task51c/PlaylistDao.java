package com.example.task51c;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PlaylistDao {
    @Query("SELECT * FROM playlist")
    LiveData<List<PlaylistItem>> getAllItems();

    @Insert
    void insert(PlaylistItem item);

    @Delete
    void delete(PlaylistItem item);

    @Query("DELETE FROM playlist")
    void deleteAll();
} 