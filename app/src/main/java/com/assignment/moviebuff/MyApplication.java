package com.assignment.moviebuff;

import android.app.Application;

import com.assignment.moviebuff.di.DaggerMovieComponent;
import com.assignment.moviebuff.di.MovieComponent;
import com.assignment.moviebuff.di.MovieModule;

public class MyApplication extends Application {

    private MovieComponent movieComponent;

    public MovieComponent getMovieComponent(){
        return movieComponent;
    }

    @Override public void onCreate() {
        super.onCreate();
        movieComponent = DaggerMovieComponent.builder()
                .movieModule(new MovieModule(this))
                .build();
       }
}
