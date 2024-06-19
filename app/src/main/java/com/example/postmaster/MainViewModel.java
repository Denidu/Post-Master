package com.example.postmaster;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Post>> posts;
    private final PostRepository postRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        posts = postRepository.getPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }
}

