package com.assignment.moviebuff.movierepo.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.moviebuff.movierepo.local.entity.Movie;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieDAO {

    @Query("select * from movie_table")
    List<Movie> getMovies();

    @Insert(onConflict = REPLACE)
    void insert(Movie movie);

    @Query("UPDATE movie_table SET geners = :geners, production_companies = :productionCompanies WHERE movie_id =:movieId")
    void update(String movieId,String geners,String productionCompanies);

}
