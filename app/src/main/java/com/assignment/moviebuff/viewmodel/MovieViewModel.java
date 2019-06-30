package com.assignment.moviebuff.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.moviebuff.movierepo.APIErrorParser;
import com.assignment.moviebuff.movierepo.MovieRepository;
import com.assignment.moviebuff.movierepo.MovieResult;
import com.assignment.moviebuff.movierepo.local.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<MovieResult> movieResultMutableLiveData;

    MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        movieResultMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<MovieResult> getMovieResult() {
        fetchMovieList();
        return movieResultMutableLiveData;
    }

    public Movie getMovieAtPostion(int position) {
        if(movieResultMutableLiveData.getValue().getMovieList()!=null)
         return movieResultMutableLiveData.getValue().getMovieList().get(position);
        return null;
    }

    private void fetchMovieList() {
        final MovieResult movieResult = new MovieResult();
        Observable<List<Movie>> observable = movieRepository.getPopularMoviesFromRepo();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("tag", "onSubscribe");
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        Log.e("tag", "onNext " + movies.size());
                        movieResult.setMovieList((ArrayList<Movie>) movies);
                        movieResultMutableLiveData.setValue(movieResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        APIErrorParser apiErrorParser = new APIErrorParser();
                        if (e instanceof SSLHandshakeException) {
                            apiErrorParser.setErrorCode(0);
                        } else {
                            apiErrorParser.setErrorCode(1);
                        }
                        movieResult.setApiErrorParser(apiErrorParser);
                        movieResultMutableLiveData.setValue(movieResult);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tag", "onComplete");
                    }
                });
    }
}
