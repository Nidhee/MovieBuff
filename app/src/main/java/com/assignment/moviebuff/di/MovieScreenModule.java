package com.assignment.moviebuff.di;

import android.content.Context;

import com.assignment.moviebuff.view.MovieAdapter;

import dagger.Module;
import dagger.Provides;


@Module
public class MovieScreenModule {

    private Context mContext;
    public MovieScreenModule(Context context){
        mContext = context;
    }
    @Provides
    @MovieScope
    MovieAdapter getMovieAdapter(){
        return new MovieAdapter(mContext);
    }
}
