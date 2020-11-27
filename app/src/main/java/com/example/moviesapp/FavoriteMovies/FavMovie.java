package com.example.moviesapp.FavoriteMovies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviesapp.Favorites;

import java.util.List;

@Dao
public interface FavMovie {



    @Insert
    void addFavorite(Favorites favorites);

    @Query("select * from favmovies")
    public LiveData<List<Favorites>> getFavMovies();
}
