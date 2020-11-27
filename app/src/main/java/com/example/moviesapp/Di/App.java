package com.example.moviesapp.Di;

import android.app.Application;

public class App extends Application {

    private static App app;
    MoviesComponent moviesComponent;



    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        moviesComponent = DaggerMoviesComponent
                .builder().applicationModule
                        (new ApplicationModule(this)).
                        build();


    }

    public static App getApp() {
        return app;
    }


    public MoviesComponent getMoviesComponent() {
        return moviesComponent;
    }


}
