package com.example.tmdbapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tmdbapi.DetailsActivity;
import com.example.tmdbapi.R;
import com.example.tmdbapi.models.MovieModel;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    ArrayList<MovieModel> list;
    Context context;

    public MovieAdapter(ArrayList<MovieModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    String url = "https://image.tmdb.org/t/p/w500/";

    /* public ResultAdapter(  MovieListener listener) {
         this.list = list;
         this.listener = listener;
     }
 */
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item,parent,false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final  MovieModel model = list.get(position);

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+model.getPoster_path()).fitCenter().into(((MovieViewHolder)holder).movie_img);
        ((MovieViewHolder)holder).id.setText("title : "+model.getTitle());
        ((MovieViewHolder)holder).title.setText(model.getOverview());
       ((MovieViewHolder)holder).ratingBar.setRating((float) (model.getVoteAverage()/2));


    }

    @Override
    public int getItemCount() {

        if(list !=null){
            return list.size();
        }
        return 0;
    }

    public void setList(ArrayList<MovieModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_img;
        TextView id,title,date;
        RatingBar ratingBar;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);


            movie_img = itemView.findViewById(R.id.movie_banner);
            id = itemView.findViewById(R.id.movie_id);
            title = itemView.findViewById(R.id.movie_title);
          //  date = itemView.findViewById(R.id.date_text);
            ratingBar = itemView.findViewById(R.id.rating);
        }
    }

}
