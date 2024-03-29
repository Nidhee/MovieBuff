package com.assignment.moviebuff.movierepo;

import android.util.Log;

import com.assignment.moviebuff.BuildConfig;
import com.assignment.moviebuff.movierepo.local.MovieRoomDatabase;
import com.assignment.moviebuff.movierepo.local.entity.Movie;
import com.assignment.moviebuff.movierepo.remote.MovieService;
import com.assignment.moviebuff.movierepo.remote.remotedatasource.MoviesParser;
import com.assignment.moviebuff.movierepo.remote.remotedatasource.ResultParser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MovieRepository {


    private MovieRoomDatabase movieDatabase;
    private MovieService movieService;

    @Inject
    public MovieRepository(MovieRoomDatabase movieDatabase, MovieService movieService) {
        this.movieDatabase = movieDatabase;
        this.movieService = movieService;
    }

    public Observable<List<Movie>> getPopularMoviesFromRepo() {
        Log.e("tag", "getPopularMovies ");

        final Maybe<List<Movie>> movieObservable = readFromDB();
        return movieObservable.flatMapObservable(new Function<List<Movie>, Observable<List<Movie>>>() {
            @Override
            public Observable<List<Movie>> apply(List<Movie> movies) {
                if (movies.isEmpty()) {
                    return callAPI();
                } else {
                    return movieObservable.toObservable();
                }
            }
        });
    }

    private Observable<List<Movie>> callAPI() {
        Log.e("tag", "callAPI ");
        return movieService.getPopularMoviesFromRemote(BuildConfig.API_KEY, "en-US", "1")
                .flatMap(new Function<MoviesParser, Observable<List<Movie>>>() {
                    @Override
                    public Observable<List<Movie>> apply(MoviesParser moviesParser) {
                        saveToDB(moviesParser);
                        return readFromDB().toObservable();
                    }
                });
    }

    private void saveToDB(MoviesParser moviesParser) {
        Log.e("tag", "saveToDB");
        List<Movie> movieList = new ArrayList<>();
        for (ResultParser resultParser : moviesParser.getResultParsers()) {
            Movie movie = new Movie();
            movie.setMovieId(resultParser.getId());
            movie.setTitle(resultParser.getTitle());
            movie.setVoteAverage(resultParser.getVoteAverage());
            movie.setPosterPath(resultParser.getPosterPath());
            movie.setReleaseDate(resultParser.getReleaseDate());
            movie.setBackdropPath(resultParser.getBackdropPath());
            movie.setOverview(resultParser.getOverview());
            movieList.add(movie);
        }
        movieDatabase.movieDAO().insert(movieList);
    }

    private Maybe<List<Movie>> readFromDB() {
        Log.e("tag", "readFromDB");
        return movieDatabase.movieDAO().getMovies();
    }
}
