package com.assignment.moviebuff.view;

import android.os.Bundle;

import com.assignment.moviebuff.BuildConfig;
import com.assignment.moviebuff.movierepo.local.entity.Movie;
import com.assignment.moviebuff.viewmodel.MovieViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.moviebuff.R;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String MOVIE_ARG = "Movie";

    ImageView ivBackdrop,ivPoster;
    TextView tvTitle,tvVoteAverage,tvOverview;
    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ivBackdrop = findViewById(R.id.ivBackdropImage);
        ivPoster = findViewById(R.id.ivPosterImage);
        tvTitle = findViewById(R.id.tvMovieTitle);
        tvVoteAverage = findViewById(R.id.tvVoteAverage);
        tvOverview = findViewById(R.id.tvOverview);

        movie = getIntent().getExtras().getParcelable(MOVIE_ARG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        if(movie!=null) {

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
            Glide.with(this)
                    .load(BuildConfig.IMAGE_URL + "w500" + movie.getBackdropPath())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(ivBackdrop);

            Glide.with(this)
                    .load(BuildConfig.IMAGE_URL + "w500" + movie.getPosterPath())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(ivPoster);
        }
    }
}
