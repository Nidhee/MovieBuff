package com.assignment.moviebuff.movierepo.remote;

import com.assignment.moviebuff.movierepo.remote.remotedatasource.MovieParser;
import com.assignment.moviebuff.movierepo.remote.remotedatasource.MoviesParser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular?")
    Observable<MoviesParser> getPopularMoviesFromRemote(@Query("api_key") String api_key,
                                              @Query("language") String language,
                                                        @Query("page") String page);

    @GET("movie")
    Observable<MovieParser> getMovieDetailFromRemote(@Path("movie_id") String movie_id,
                                           @Query("api_key") String api_key,
                                           @Query("language") String language);
}
