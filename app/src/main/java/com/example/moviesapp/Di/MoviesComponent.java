package com.example.moviesapp.Di;


import com.example.moviesapp.View.FavoriteActivity;
import com.example.moviesapp.View.MainActivity;
import com.example.moviesapp.View.MovieActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface MoviesComponent {



    void inject(MainActivity mainActivity);



    void inject(MovieActivity movieActivity);


    void inject(FavoriteActivity favoriteActivity);
}
