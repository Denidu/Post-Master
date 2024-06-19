package com.example.postmaster;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {
    private final PostDao postDao;

    public PostRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        postDao = db.postDao();
    }

    public MutableLiveData<List<Post>> getPosts() {
        MutableLiveData<List<Post>> data = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
        Call<List<Post>> call = jsonPlaceholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    data.setValue(posts);
                    new Thread(() -> postDao.insertAll(posts)).start();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                data.setValue(postDao.getAllPosts());
            }
        });

        return data;
    }

    public Post getPostById(int postId) {
        return postDao.getPostById(postId);
    }
}
