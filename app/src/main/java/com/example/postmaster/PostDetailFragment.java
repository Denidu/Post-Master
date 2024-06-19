package com.example.postmaster;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.navArgs;

public class PostDetailFragment extends Fragment {

    private PostDetailViewModel postDetailViewModel;
    private final PostDetailFragmentArgs args;

    public PostDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);

        TextView postDetailTitle = view.findViewById(R.id.postDetailTitle);
        TextView postDetailBody = view.findViewById(R.id.postDetailBody);

        postDetailViewModel = new ViewModelProvider(this).get(PostDetailViewModel.class);
        postDetailViewModel.getPost(args.getPostId()).observe(getViewLifecycleOwner(), post -> {
            postDetailTitle.setText(post.getTitle());
            postDetailBody.setText(post.getBody());
        });

        return view;
    }
}

