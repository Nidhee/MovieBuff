package com.assignment.moviebuff.movierepo;

import android.util.Log;

import com.assignment.moviebuff.BuildConfig;
import com.assignment.moviebuff.MyApplication;
import com.assignment.moviebuff.movierepo.local.MovieRoomDatabase;
import com.assignment.moviebuff.movierepo.local.entity.Movie;
import com.assignment.moviebuff.movierepo.remote.MovieService;
import com.assignment.moviebuff.movierepo.remote.remotedatasource.MoviesParser;
import com.assignment.moviebuff.movierepo.remote.remotedatasource.ResultParser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class MovieRepository {

    @Inject
    MovieRoomDatabase movieDatabase;

    @Inject
    MovieService movieService;

    public MovieRepository(MyApplication myApplication) {
        myApplication.getMovieComponent().inject(this);
    }

    public Observable<List<Movie>> getPopularMoviesFromRepo() {
        Log.e("tag","getPopularMovies ");

        final Observable<List<Movie>> movieObservable = readFromDB();

        return movieObservable.flatMap(new Function<List<Movie>, Observable<List<Movie>>>() {
            @Override
            public Observable<List<Movie>> apply(List<Movie> movies) {
                Log.e("tag","movieListFlowable.flatMap - > apply : " + movies.isEmpty()  + " movies " + movies.size());
                if (movies.isEmpty()) {
                    return callAPI();
                } else {
                    return Observable.empty();
                }
            }
        });
    }

    private Observable<List<Movie>> callAPI() {
        Log.e("tag","callAPI ");
        return movieService.getPopularMoviesFromRemote(BuildConfig.API_KEY, "en-US", "1")
                .flatMap(new Function<MoviesParser, Observable<List<Movie>>>() {
                    @Override
                    public Observable<List<Movie>> apply(MoviesParser moviesParser)  {
                        return  Observable.just(saveToDB(moviesParser));
                    }
                });
    }

    private List<Movie> saveToDB(MoviesParser moviesParser) {
        Log.e("tag","saveToDB ");
        List<Movie> movieList = new ArrayList<>();
        for (ResultParser resultParser : moviesParser.getResultParsers()) {
            Movie movie = new Movie();
            movie.setMovieId(resultParser.getId());
            movie.setTitle(resultParser.getTitle());
            movie.setVoteAverage(resultParser.getVoteAverage());
            movie.setPosterPath(resultParser.getPosterPath());
            movie.setReleaseDate(resultParser.getReleaseDate());
            movieList.add(movie);
        }
        movieDatabase.movieDAO().insert(movieList);
        return movieList;
    }


    private Observable<List<Movie>> readFromDB() {
        Log.e("tag","readFromDB ");
        Observable<List<Movie>> observable = movieDatabase.movieDAO().getMovies();
        Log.e("tag","readFromDB observable ");
        return observable;
    }
}
