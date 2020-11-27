package com.example.moviesapp.Di;


import android.app.Application;

import com.example.moviesapp.Repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {


    @Provides
    @Singleton
    Repository providesRepository(Application application) {
        return new Repository(application);
    }
}
