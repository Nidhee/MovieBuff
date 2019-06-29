package com.assignment.moviebuff;

import android.app.Application;

import com.assignment.moviebuff.di.DaggerMovieAppComponent;
import com.assignment.moviebuff.di.MovieAppComponent;
import com.assignment.moviebuff.di.MovieAppModule;

public class MyApplication extends Application {

    private MovieAppComponent movieComponent;

    public MovieAppComponent getMovieComponent(){
        return movieComponent;
    }

    @Override public void onCreate() {
        super.onCreate();
        movieComponent = DaggerMovieAppComponent.builder()
                .movieAppModule(new MovieAppModule(this))
                .build();
       }
}
