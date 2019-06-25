package com.assignment.moviebuff.movierepo.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.assignment.moviebuff.movierepo.local.dao.MovieDAO;
import com.assignment.moviebuff.movierepo.local.entity.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieRoomDatabase  extends RoomDatabase {

    public abstract MovieDAO movieDAO();

    private static volatile MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            MovieRoomDatabase.class,
                            "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
