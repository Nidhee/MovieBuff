package com.assignment.moviebuff.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.assignment.moviebuff.MyApplication;
import com.assignment.moviebuff.R;
import com.assignment.moviebuff.di.MovieComponent;
import com.assignment.moviebuff.movierepo.MovieRepository;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    MovieRepository movieRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication)getApplication()).getMovieComponent().inject(this);

        movieRepository.getPopularMovies();
    }
}
