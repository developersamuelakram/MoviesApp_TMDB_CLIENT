package com.example.moviesapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviesapp.Di.App;
import com.example.moviesapp.Di.MovieFactoryViewModel;
import com.example.moviesapp.Favorites;
import com.example.moviesapp.R;
import com.example.moviesapp.Result;
import com.example.moviesapp.ViewModel.MovieViewModel;
import com.example.moviesapp.databinding.ActivityMovieBinding;

import javax.inject.Inject;

public class MovieActivity extends AppCompatActivity {



    Result result;
    ActivityMovieBinding activityMovieBinding;
    ImageView favbutton;
    Favorites favorites;
    MovieViewModel movieViewModel;

    @Inject
    MovieFactoryViewModel movieFactoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        App.getApp().getMoviesComponent().inject(this);

        movieViewModel = new ViewModelProvider(this, movieFactoryViewModel).get(MovieViewModel.class);
        activityMovieBinding  = DataBindingUtil.setContentView(this, R.layout.activity_movie);


            favbutton = findViewById(R.id.fav);

            favbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    result = getIntent().getParcelableExtra("movieresult");

                    String posterpath = result.getPosterPath();
                    String titleofmovie = result.getOriginalTitle();
                    String overrivew = result.getOverview();
                    double rating = result.getVoteAverage();

                    favorites = new Favorites(posterpath, titleofmovie, overrivew, rating);


                    movieViewModel.adddFav(favorites);
                    Toast.makeText(MovieActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                    Intent newintent = new Intent(MovieActivity.this, com.example.moviesapp.View.FavoriteActivity.class);
                    startActivity(newintent);
                }
            });


            result = getIntent().getParcelableExtra("movieresult");

            String moviename = result.getOriginalTitle();

        getSupportActionBar().setTitle(moviename);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        activityMovieBinding.setResult(result);




    }
}