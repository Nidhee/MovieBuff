package com.assignment.moviebuff.movierepo.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.moviebuff.movierepo.local.entity.Movie;

import java.util.List;

import io.reactivex.Maybe;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieDAO {

    @Query("select * from movie_table order by movie_id")
    Maybe<List<Movie>> getMovies();

    @Insert(onConflict = REPLACE)
    void insert(List<Movie> movie);

}
