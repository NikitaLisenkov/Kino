package com.example.kino;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReveiwViewHolder> {

    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEGATIVE = "Негативный";
    private static final String TYPE_NEUTRAL = "Нейтральный";
    private static final String TYPE_ISNULL = "Пустой";
    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReveiwViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReveiwViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReveiwViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewAutor.setText(review.getAutor());
        holder.textViewReview.setText(review.getReview());

        String type = review.getType();
        if (type == null) {
            type = TYPE_ISNULL;
        }
        int colorResId = android.R.color.holo_red_light;
        switch (type) {
            case TYPE_POSITIVE:
                colorResId = android.R.color.holo_green_light;
                break;
            case TYPE_NEUTRAL:
                colorResId = android.R.color.holo_orange_light;
                break;
            case TYPE_ISNULL:
                colorResId = android.R.color.holo_blue_dark;
                break;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.linearLayoutContainer.setBackgroundColor(color);

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReveiwViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout linearLayoutContainer;
        private final TextView textViewAutor;
        private final TextView textViewReview;

        public ReveiwViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutContainer = itemView.findViewById(R.id.linearLayoutContainer);
            textViewAutor = itemView.findViewById(R.id.textViewAutor);
            textViewReview = itemView.findViewById(R.id.textViewReview);
        }
    }
}
