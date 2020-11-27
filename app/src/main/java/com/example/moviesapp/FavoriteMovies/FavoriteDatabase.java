package com.example.moviesapp.FavoriteMovies;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviesapp.Favorites;
import com.example.moviesapp.Model.MovieDao;

@Database(entities = {Favorites.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract com.example.moviesapp.FavoriteMovies.FavMovie getFavDao();

    public static FavoriteDatabase instance;

    public static synchronized FavoriteDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context, FavoriteDatabase.class, "dbfav").fallbackToDestructiveMigration().build();
        }

        return instance;
    }



}
