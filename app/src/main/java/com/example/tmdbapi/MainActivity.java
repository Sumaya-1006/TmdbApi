package com.example.tmdbapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tmdbapi.adapter.MovieAdapter;
import com.example.tmdbapi.models.MovieModel;
import com.example.tmdbapi.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MovieModel> list;
    MovieAdapter adapter;
    MovieViewModel movieViewModel;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        imageView = findViewById(R.id.imageId);
        textView = findViewById(R.id.textView);

        recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        adapter = new MovieAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager managaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(managaer);

        movieViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null){
                    for(MovieModel movieModel : movieModels){
                        Log.d("Tag","The Title "+movieModel.getTitle())  ;
                        list.add(movieModel);
                        adapter.setList((ArrayList<MovieModel>) movieModels);

                    }
                }
            }
        });
    }
}