package com.example.postmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> posts;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public PostAdapter(List<Post> posts, OnItemClickListener listener) {
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.titleTextView.setText(post.getTitle());
        holder.bodyTextView.setText(post.getBody());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(post));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView bodyTextView;

        public ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.postTitle);
            bodyTextView = view.findViewById(R.id.postBody);
        }
    }
}
