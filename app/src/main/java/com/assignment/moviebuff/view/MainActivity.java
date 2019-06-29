package com.assignment.moviebuff.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.moviebuff.MyApplication;
import com.assignment.moviebuff.R;
import com.assignment.moviebuff.di.MovieScreenModule;
import com.assignment.moviebuff.movierepo.MovieResult;
import com.assignment.moviebuff.movierepo.local.entity.Movie;
import com.assignment.moviebuff.viewmodel.MovieViewModel;
import com.assignment.moviebuff.viewmodel.MovieViewModelFactory;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    MovieViewModelFactory movieViewModelFactory;

    private static final String MOVIE_ARG = "Movie";

    MovieViewModel movieViewModel;
    LinearLayout llProgress, llError;
    RecyclerView rvMovieList;
    Button btnRetry;
    TextView txtErrorMessage;

    @Inject
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       ((MyApplication) getApplication()).getMovieComponent().addMovieScreenComponent(new MovieScreenModule(MainActivity.this)).inject(MainActivity.this);
        llProgress = findViewById(R.id.movieListprogressLayout);
        rvMovieList = findViewById(R.id.rvMovieList);
        llError = findViewById(R.id.errorLayout);
        btnRetry = findViewById(R.id.btnRetry);
        txtErrorMessage = findViewById(R.id.errorMessage);

        movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieViewModel.class);
        rvMovieList.setLayoutManager(new GridLayoutManager(this, 2));
        // movieAdapter = new MovieAdapter(this);

        movieAdapter.setListener(new MovieAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Item click navigate to detail activity
                Movie movie = movieViewModel.getMovieResult().getValue().getMovieList().get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(MOVIE_ARG, movie);
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        rvMovieList.setAdapter(movieAdapter);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovies();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("tag", "onResume ");

        getMovies();
    }
    private void getMovies(){
        llProgress.setVisibility(View.VISIBLE);
        rvMovieList.setVisibility(View.GONE);
        llError.setVisibility(View.GONE);

        movieViewModel.getMovieResult().observe(this, new Observer<MovieResult>() {
            @Override
            public void onChanged(MovieResult movieResult) {
                if (movieResult.getMovieList() != null) {
                    Log.e("tag", "onChanged() called with: movies = [" + movieResult.getMovieList().size() + "]");
                    // Update UI
                    rvMovieList.setVisibility(View.VISIBLE);
                    llProgress.setVisibility(View.GONE);
                    llError.setVisibility(View.GONE);
                    movieAdapter.setData(movieResult.getMovieList());
                } else {
                    rvMovieList.setVisibility(View.GONE);
                    llProgress.setVisibility(View.GONE);
                    llError.setVisibility(View.VISIBLE);
                    switch (movieResult.getApiErrorParser().getErrorCode()){
                        case 0:
                            txtErrorMessage.setText(getString(R.string.str_error_cant_reach_server));
                            break;
                        case 1:
                            txtErrorMessage.setText(getString(R.string.str_error_something_went_wrong));
                            break;
                    }
                }
            }
        });
    }
}
