package com.example.moviesapp.FavoriteMovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.View.FavMovieDetail;
import com.example.moviesapp.View.MovieActivity;
import com.example.moviesapp.Favorites;
import com.example.moviesapp.R;

import com.example.moviesapp.databinding.FavliststyleBinding;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.MyHolder> {

    FavliststyleBinding favliststyleBinding;
    Context context;
    ArrayList<Favorites> favoritesArrayList;

    public FavoriteMovieAdapter(Context context, ArrayList<Favorites> favoritesArrayList) {
        this.context = context;
        this.favoritesArrayList = favoritesArrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        favliststyleBinding = DataBindingUtil.inflate(LayoutInflater.
                from(parent.getContext()), R.layout.favliststyle, parent, false);

        return new MyHolder(favliststyleBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieAdapter.MyHolder holder, int position) {

        Favorites favorites = favoritesArrayList.get(position);

        holder.favliststyleBinding.setFav(favorites);

    }

    @Override
    public int getItemCount() {
        return favoritesArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        FavliststyleBinding favliststyleBinding;

        public MyHolder(FavliststyleBinding favliststyleBinding) {
            super(favliststyleBinding.getRoot());
            this.favliststyleBinding = favliststyleBinding;


            favliststyleBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if (position!= RecyclerView.NO_POSITION) {

                        Favorites result = favoritesArrayList.get(position);

                        Intent intent = new Intent(context, FavMovieDetail.class);
                        intent.putExtra("favresult", result);
                        context.startActivity(intent);


                    }
                }
            });


        }




    }
}
