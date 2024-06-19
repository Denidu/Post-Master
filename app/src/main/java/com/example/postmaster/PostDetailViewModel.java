package com.example.postmaster;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PostDetailViewModel extends AndroidViewModel {

    private final MutableLiveData<Post> post;
    private final PostRepository postRepository;

    public PostDetailViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(application);
        post = new MutableLiveData<>();
    }

    public LiveData<Post> getPost(int postId) {
        post.setValue(postRepository.getPostById(postId));
        return post;
    }
}

