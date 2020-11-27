package com.example.moviesapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.moviesapp.Adapter.MovieAdapter;
import com.example.moviesapp.Di.App;
import com.example.moviesapp.Di.MovieFactoryViewModel;
import com.example.moviesapp.FavoriteMovies.FavoriteMovieAdapter;
import com.example.moviesapp.Favorites;
import com.example.moviesapp.R;
import com.example.moviesapp.ViewModel.MovieViewModel;
import com.example.moviesapp.databinding.ActivityFavoriteBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoriteActivity extends AppCompatActivity {


    FavoriteMovieAdapter mAdapter;
    MovieViewModel viewModel;
    RecyclerView recyclerView;
    ArrayList<Favorites> favoritesArrayList = new ArrayList<>();
    ActivityFavoriteBinding favoriteBinding;

    @Inject
    MovieFactoryViewModel movieFactoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setTitle("Favorites");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        App.getApp().getMoviesComponent().inject(this);


        viewModel = new ViewModelProvider(this, movieFactoryViewModel).get(MovieViewModel.class);

        favoriteBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite);


        viewModel.getFavFromDb().observe(this, new Observer<List<Favorites>>() {
            @Override
            public void onChanged(List<Favorites> favorites) {

                favoritesArrayList = (ArrayList<Favorites>) favorites;

                showRecyclerView();
            }
        });


    }

    private void showRecyclerView() {

        recyclerView = favoriteBinding.RVFAV;
        mAdapter = new FavoriteMovieAdapter(this, favoritesArrayList);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}