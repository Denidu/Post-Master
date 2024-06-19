package com.example.postmaster;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private PostAdapter postAdapter;
    private MainViewModel mainViewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        postAdapter = new PostAdapter(new ArrayList<>(), post -> {
            MainFragmentDirections.ActionMainFragmentToPostDetailFragment action =
                    MainFragmentDirections.actionMainFragmentToPostDetailFragment(post.getId());
            Navigation.findNavController(view).navigate(action);
        });
        recyclerView.setAdapter(postAdapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            postAdapter.setPosts(posts);
            postAdapter.notifyDataSetChanged();
        });

        return view;
    }
}

