package com.example.postmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void insertAll(List<Post> posts);

    @Query("SELECT * FROM posts")
    List<Post> getAllPosts();
}
