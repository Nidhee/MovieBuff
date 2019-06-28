package com.assignment.moviebuff.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.moviebuff.BuildConfig;
import com.assignment.moviebuff.R;
import com.assignment.moviebuff.movierepo.local.entity.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String MOVIE_ARG = "Movie";

    ImageView ivBackdrop, ivPoster;
    TextView tvTitle, tvVoteAverage, tvOverview;
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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = bundle.getParcelable(MOVIE_ARG);
        }

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateViews();
    }

    private void updateViews() {
        if (movie != null) {

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
            Glide.with(this)
                    .load(BuildConfig.IMAGE_URL + "w500" + movie.getBackdropPath())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.placeholder_backdrop)
                    .into(ivBackdrop);

            Glide.with(this)
                    .load(BuildConfig.IMAGE_URL + "w300" + movie.getPosterPath())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .placeholder(R.drawable.placeholder_poster)
                    .into(ivPoster);
        }
    }
}
