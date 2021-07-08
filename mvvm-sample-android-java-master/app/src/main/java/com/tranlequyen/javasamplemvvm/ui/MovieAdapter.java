package com.tranlequyen.javasamplemvvm.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
//import com.tranlequyen.javasamplemvvm.databinding.ItemMovieBinding;
import com.tranlequyen.javasamplemvvm.databinding.ItemMovieBinding;
import com.tranlequyen.javasamplemvvm.model.MovieList;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieList> movieList = new ArrayList<>();
    private Context context;

    MovieAdapter() {
    }

    private ArrayList<MovieList> getMovieList() {
        return movieList;
    }

    void setMovieList(ArrayList<MovieList> items) {
        movieList.clear();
        movieList.addAll(items);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding view = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.bindItem(getMovieList().get(position));

        holder.binding.itemmovie.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "onClick: clicked on: " + movieList.get(position));
//                Toast.makeText ( context,movieList.get ( position).getOriginalTitle (),Toast.LENGTH_LONG ).show ();
                Intent intent =  new Intent(v.getContext(), MainActivity2.class);
                intent.putExtra("title",movieList.get(position).getOriginalTitle ());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMovieList().size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding binding;

        MovieViewHolder(ItemMovieBinding movieBinding) {
            super(movieBinding.getRoot());
            binding = movieBinding;

        }

        void bindItem(MovieList movieList) {
            String voteValue = String.valueOf(movieList.getVoteAverage());
            String urlImg = "https://image.tmdb.org/t/p/w220_and_h330_face" + movieList.getPosterPath();

            binding.tvTitle.setText(movieList.getOriginalTitle());
            binding.tvOverview.setText(movieList.getOverview());
            binding.tvVote.setText(voteValue);
            Picasso.get()
                    .load(urlImg)
                    .into(binding.imgPoster);
        }
    }
}
