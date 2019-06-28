package com.assignment.moviebuff.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.moviebuff.movierepo.MovieRepository;
import com.assignment.moviebuff.movierepo.local.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> movieArrayList;
    private MovieRepository movieRepository;

    MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MutableLiveData<ArrayList<Movie>> getMovieArrayList(){
        if(movieArrayList==null) {
            movieArrayList = new MutableLiveData<>();
            fetchMovieList();
        }
        return movieArrayList;
    }

    private void fetchMovieList() {

        Observable<List<Movie>> observable = movieRepository.getPopularMoviesFromRepo();
         observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("tag","onSubscribe");
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        Log.e("tag","onNext " + movies.size());
                         movieArrayList.setValue((ArrayList<Movie>) movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag","onError " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tag","onComplete");
                    }
                });
    }
}
