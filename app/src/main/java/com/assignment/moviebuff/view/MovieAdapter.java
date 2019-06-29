package com.assignment.moviebuff.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.moviebuff.BuildConfig;
import com.assignment.moviebuff.R;
import com.assignment.moviebuff.movierepo.local.entity.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> movieArrayList;
    private Context context;
    private RecyclerViewClickListener listener;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    void setData(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }
    void setListener(RecyclerViewClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View movieView = layoutInflater.inflate(R.layout.movie_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(movieView, listener);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        Glide.with(context)
                .load(BuildConfig.IMAGE_URL + "w300" + movie.getPosterPath())
                .placeholder(R.drawable.placeholder_poster)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(holder.imgMoviePoster);

    }

    @Override
    public int getItemCount() {
        return this.movieArrayList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMovieTitle;
        TextView tvVoteAverage;
        ImageView imgMoviePoster;
        private RecyclerViewClickListener listener;

        MovieViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            tvMovieTitle = view.findViewById(R.id.tvMovieTitle);
            tvVoteAverage = view.findViewById(R.id.tvVoteAverage);
            imgMoviePoster = view.findViewById(R.id.imgMoviePoster);
            this.listener = listener;
            imgMoviePoster.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }
}
