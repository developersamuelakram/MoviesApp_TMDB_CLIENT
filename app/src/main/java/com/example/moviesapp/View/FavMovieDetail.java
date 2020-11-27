package com.example.moviesapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviesapp.Favorites;
import com.example.moviesapp.R;
import com.example.moviesapp.Result;
import com.example.moviesapp.ViewModel.MovieViewModel;
import com.example.moviesapp.databinding.ActivityFavMovieDetailBinding;
import com.example.moviesapp.databinding.ActivityFavoriteBinding;
import com.example.moviesapp.databinding.ActivityMovieBinding;

public class FavMovieDetail extends AppCompatActivity {

    Favorites favorites;
    ActivityFavMovieDetailBinding activityFavoriteBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        activityFavoriteBinding  = DataBindingUtil.setContentView(this, R.layout.activity_fav_movie_detail);


        favorites = getIntent().getParcelableExtra("favresult");
        String moviename =  favorites.getOriginalTitle();

        getSupportActionBar().setTitle(moviename);

        activityFavoriteBinding.setFavorites(favorites);








    }
}