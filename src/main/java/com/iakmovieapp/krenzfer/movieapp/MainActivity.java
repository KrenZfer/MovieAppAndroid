package com.iakmovieapp.krenzfer.movieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iakmovieapp.krenzfer.movieapp.Adapter.MovieAdapter;
import com.iakmovieapp.krenzfer.movieapp.Data.Constants;
import com.iakmovieapp.krenzfer.movieapp.DatabaseHandler.DBHandler;
import com.iakmovieapp.krenzfer.movieapp.Model.ListMovie;
import com.iakmovieapp.krenzfer.movieapp.Model.MovieMini;
import com.iakmovieapp.krenzfer.movieapp.Utils.MovieJsonUtils;
import com.iakmovieapp.krenzfer.movieapp.Utils.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MoviePosteronClickHandler{

    RecyclerView recyclerView;

    MovieAdapter movieAdapter;

    ProgressBar progressBar;

    TextView errorMessage;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        errorMessage = (TextView) findViewById(R.id.errorMessage);

        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(false);

        movieAdapter = new MovieAdapter(this);

        recyclerView.setAdapter(movieAdapter);

        dbHandler = new DBHandler(this);

        loadImage(NetworkUtils.POPULAR_MOVIE_END_POINT);
    }

    public void showMovieData(){
        recyclerView.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
    }

    public void hideMovieData(){
        recyclerView.setVisibility(View.INVISIBLE);
    }

    public void showErrorMessage(){
        errorMessage.setVisibility(View.VISIBLE);
    }

    public void loadImage(final String end_point){
        hideMovieData();
        new LoadMovieDataAsync().execute(end_point);
    }

    public void loadImage(MovieMini[] minis){
        hideMovieData();
        movieAdapter.setMovieMinis(minis);
        showMovieData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.idpopularmovie:
                loadImage(NetworkUtils.POPULAR_MOVIE_END_POINT);
                break;
            case R.id.idtopratedmovie:
                loadImage(NetworkUtils.TOP_RATED_MOVIE_END_POINT);
                break;
//            case R.id.idFavorit:
//                loadImage(dbHandler.getAllMovies());
//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(MovieMini movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.DATA_MOVIE_INTENT, movie.getIdMovie());
        startActivity(intent);
    }

    public class LoadMovieDataAsync extends AsyncTask<String, Void, ListMovie>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ListMovie listMovie) {
            super.onPostExecute(listMovie);

            progressBar.setVisibility(View.INVISIBLE);
            if(listMovie != null) {
                showMovieData();
                movieAdapter.setMovieMinis(listMovie.getMovieMinis());
            }else{
                hideMovieData();
                showErrorMessage();
            }
        }

        @Override
        protected ListMovie doInBackground(String... params) {

            if(params.length == 0){
                return null;
            }

            String endpoint = params[0];

            URL url = NetworkUtils.buildURLForListMovie(endpoint);
            ListMovie listMovie = null;

            try{
                String jsonMovies = NetworkUtils.getResponseFromHTTPUrl(url);
                listMovie = MovieJsonUtils.getListMovie(MainActivity.this, jsonMovies);
                return listMovie;
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
    }
}
