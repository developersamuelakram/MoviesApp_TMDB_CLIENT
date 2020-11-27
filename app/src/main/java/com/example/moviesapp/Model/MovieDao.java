package com.example.moviesapp.Model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviesapp.Favorites;
import com.example.moviesapp.Result;

import java.util.List;


@Dao
public interface MovieDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovies(Result result);

    @Query("select * from moviestable")
    public LiveData<List<Result>> getDbmovies();







}
