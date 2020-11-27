package com.example.moviesapp.Di;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesapp.Repository.Repository;
import com.example.moviesapp.ViewModel.MovieViewModel;

import javax.inject.Inject;

public class MovieFactoryViewModel implements ViewModelProvider.Factory {

    Repository repository;


    @Inject
    public MovieFactoryViewModel(Repository repository) {
        this.repository = repository;
    }




    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(repository);
    }
}
