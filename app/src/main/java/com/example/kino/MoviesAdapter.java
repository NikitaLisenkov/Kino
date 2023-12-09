package com.example.kino;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnReachEndListener onReachEndListener;
    private OnMovieClickListener onMovieClickListener;


    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Log.d("MoviesAdapter", "onBindViewHolder " + position);
        Movie movie = movies.get(position);
        holder.bind(
                movie,
                position,
                movies.size(),
                onReachEndListener,
                onMovieClickListener
        );
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface OnReachEndListener {
        void onReachEnd();
    }

    interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }

        public void bind(
                Movie movie,
                int position,
                int size,
                OnReachEndListener onReachEndListener,
                OnMovieClickListener onMovieClickListener
        ) {
            Glide.with(itemView)
                    .load(movie.getPoster().getUrl())
                    .into(imageViewPoster);
            double rating = movie.getRating().getKinopoisk();
            int drawbleID;
            if (rating > 7) {
                drawbleID = R.drawable.circle_green;
            } else if (rating > 5) {
                drawbleID = R.drawable.circle_orange;
            } else {
                drawbleID = R.drawable.circle_red;
            }
            Drawable background = ContextCompat.getDrawable(itemView.getContext(), drawbleID);
            textViewRating.setBackground(background);
            textViewRating.setText(String.format("%.1f", rating));

            if (position >= size - 10 && onReachEndListener != null) {
                onReachEndListener.onReachEnd();
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onMovieClickListener != null) {
                        onMovieClickListener.onMovieClick(movie);
                    }
                }
            });
        }
    }
}
