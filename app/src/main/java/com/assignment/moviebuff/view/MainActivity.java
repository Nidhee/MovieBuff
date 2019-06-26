package com.assignment.moviebuff.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.assignment.moviebuff.MyApplication;
import com.assignment.moviebuff.R;
import com.assignment.moviebuff.movierepo.local.entity.Movie;
import com.assignment.moviebuff.viewmodel.MovieViewModel;
import com.assignment.moviebuff.viewmodel.MovieViewModelFactory;


import java.util.ArrayList;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    MovieViewModel movieViewModel;

    @Inject
    MovieViewModelFactory movieViewModelFactory;

     LinearLayout llProgress;
     RecyclerView rvMovieList;

     MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("tag","onCreate ");
        llProgress = findViewById(R.id.movieListprogressLayout);
        rvMovieList = findViewById(R.id.rvMovieList);

        ((MyApplication) getApplication()).getMovieComponent().inject(this);

        movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieViewModel.class);

        rvMovieList.setLayoutManager(new GridLayoutManager(this,2));
        movieAdapter  = new MovieAdapter(this);
        rvMovieList.setAdapter(movieAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("tag","onResume ");
        movieViewModel.getMovieArrayList().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                Log.e(TAG, "onChanged() called with: movies = [" + movies.size() + "]");
                // Update UI
                rvMovieList.setVisibility(View.VISIBLE);
                llProgress.setVisibility(View.GONE);
                movieAdapter.setData(movies);
            }
        });
    }
}
