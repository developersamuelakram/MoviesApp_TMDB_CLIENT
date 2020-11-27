package com.example.moviesapp.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviesapp.Favorites;
import com.example.moviesapp.Model.MovieDao;
import com.example.moviesapp.Result;


@Database(entities = {Result.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();

    public static MovieDatabase instance;


    public static synchronized MovieDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context, MovieDatabase.class, "dbmovies")
                    .fallbackToDestructiveMigration().build();

        }

       return instance;
    }
}
