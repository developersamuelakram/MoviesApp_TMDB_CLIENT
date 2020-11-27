package com.example.moviesapp.View;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moviesapp.Adapter.MovieAdapter;
import com.example.moviesapp.Di.App;
import com.example.moviesapp.Di.MovieFactoryViewModel;
import com.example.moviesapp.R;
import com.example.moviesapp.Result;
import com.example.moviesapp.ViewModel.MovieViewModel;
import com.example.moviesapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding activityMainBinding;
    MovieViewModel movieViewModel;
    ArrayList<Result> resultArrayList= new ArrayList<>();
    ArrayList<Result> offlinemovies = new ArrayList<>();

    RecyclerView recyclerView;
    MovieAdapter mApdater;
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    MovieFactoryViewModel movieFactoryViewModel;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        App.getApp().getMoviesComponent().inject(this);


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        movieViewModel = new ViewModelProvider(this, movieFactoryViewModel).get(MovieViewModel.class);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo!=null) {


                    getPopularMovies();
                    swipeRefreshLayout.setRefreshing(false);

                } else {

                    getDatabaseMovies();
                    swipeRefreshLayout.setRefreshing(false);

                }
            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null) {


            getPopularMovies();

        } else {

            getDatabaseMovies();
        }
    }

    private void getDatabaseMovies() {

        movieViewModel.getMoviesfromDB().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                offlinemovies = (ArrayList<Result>) results;

                showOfflineShit();

            }
        });
    }

    private void showOfflineShit() {

        recyclerView = activityMainBinding.RVMOVIES;
        mApdater = new MovieAdapter(this, offlinemovies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mApdater);
        mApdater.notifyDataSetChanged();

    }

    private void getPopularMovies() {

        movieViewModel.getMoviesfromAPI().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {

                resultArrayList = (ArrayList<Result>) results;
                for (Result rs: resultArrayList) {

                    movieViewModel.addMoviesinDb(rs);



                }

                Log.i("TAG", "onChanged: " + resultArrayList);
                showRecyclerView();

            }
        });
    }

    private void showRecyclerView() {

        recyclerView = activityMainBinding.RVMOVIES;
        mApdater = new MovieAdapter(this, resultArrayList);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mApdater);
        mApdater.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.about) {

            startActivity(new Intent(MainActivity.this, com.example.moviesapp.View.AboutActivity.class));


        } else if (item.getItemId() == R.id.favactivity) {

            startActivity(new Intent(MainActivity.this, com.example.moviesapp.View.FavoriteActivity.class));


        }
        return super.onOptionsItemSelected(item);
    }
}