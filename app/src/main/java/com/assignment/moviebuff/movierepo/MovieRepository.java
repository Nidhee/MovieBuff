package com.assignment.moviebuff.movierepo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.assignment.moviebuff.BuildConfig;
import com.assignment.moviebuff.MyApplication;
import com.assignment.moviebuff.movierepo.local.MovieRoomDatabase;
import com.assignment.moviebuff.movierepo.remote.MovieService;
import com.assignment.moviebuff.movierepo.remote.remotedatasource.MoviesParser;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    @Inject
    MovieRoomDatabase movieDatabase;

    @Inject
    MovieService movieService;

    public MovieRepository(MyApplication myApplication) {
        myApplication.getMovieComponent().inject(this);
    }

    public void getPopularMovies() {
        callAPI();
    }
    private void callAPI(){

        Observable<MoviesParser> observable
                = movieService.getPopularMoviesFromRemote(BuildConfig.API_KEY, "en-US", "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<MoviesParser>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MoviesParser moviesParser) {
                Log.e("movie", "len " + moviesParser.getResultParsers().size());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("movie", "len " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
