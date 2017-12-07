package com.iakmovieapp.krenzfer.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iakmovieapp.krenzfer.movieapp.Adapter.VideoAdapter;
import com.iakmovieapp.krenzfer.movieapp.Data.Constants;
import com.iakmovieapp.krenzfer.movieapp.DatabaseHandler.DBHandler;
import com.iakmovieapp.krenzfer.movieapp.Model.Movie;
import com.iakmovieapp.krenzfer.movieapp.Model.MovieMini;
import com.iakmovieapp.krenzfer.movieapp.Model.Video;
import com.iakmovieapp.krenzfer.movieapp.Utils.MovieJsonUtils;
import com.iakmovieapp.krenzfer.movieapp.Utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class DetailActivity extends AppCompatActivity implements VideoAdapter.VideoHolderOnClickListener{

    TextView titleOriginal;
    LinearLayout linearBackDrop;
    RelativeLayout detailLayout;
    ImageView imagePoster;
    TextView releaseDate;
    TextView runTime;
    TextView userRating;
    TextView overView;
    RecyclerView videosRecycler;
    ProgressBar progressBar;

    VideoAdapter videoAdapter;

    DBHandler dbMovie;

    Movie movieClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleOriginal = (TextView) findViewById(R.id.titleOriginalDetail);
        linearBackDrop = (LinearLayout) findViewById(R.id.linearBackdrop);
        imagePoster = (ImageView) findViewById(R.id.imagePosterDetail);
        releaseDate = (TextView) findViewById(R.id.releaseDate);
        runTime = (TextView) findViewById(R.id.runTime);
        userRating = (TextView) findViewById(R.id.userRatingDetail);
        overView = (TextView) findViewById(R.id.overView);
        progressBar = (ProgressBar) findViewById(R.id.detailProgressBar);
        detailLayout = (RelativeLayout) findViewById(R.id.containerData);

        videosRecycler = (RecyclerView) findViewById(R.id.youtubeTrailer);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        videosRecycler.setLayoutManager(linearLayoutManager);

        videosRecycler.setHasFixedSize(false);

        videoAdapter = new VideoAdapter(this);

        videosRecycler.setAdapter(videoAdapter);

        dbMovie = new DBHandler(this);

        Intent intent = getIntent();

        loadMovie(intent.getIntExtra(Constants.DATA_MOVIE_INTENT, 0));
    }

    public void loadMovie(int idMovie){
        detailLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        new FetchMovieAsyncTask().execute(idMovie);
    }

    public void updateDataDetails(Movie movie){
        movieClass = movie;

        detailLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        titleOriginal.setText(movie.getOriginalTitle());
        Picasso.with(this)
                .load(NetworkUtils.TMDB_IMAGE_CONFIGURATION_URL+movie.getImgThumbnailpath())
                .placeholder(R.mipmap.broken_image)
                .into(imagePoster);
        releaseDate.setText(movie.getReleaseDate());
        overView.setText(movie.getSinopsisMovie());
        runTime.setText(String.valueOf(movie.getRunTime())+" min");
        userRating.setText(String.valueOf(movie.getUserRating())+"/10");
        videoAdapter.setVideos(movie.getVideoList());
    }

    @Override
    public void onClick(Video video) {
        Uri youtube = Uri.parse(NetworkUtils.YOUTUBE_PlAY_URL_BASE)
                .buildUpon()
                .appendQueryParameter(NetworkUtils.VIDEO_YOUTUBE_PARAM, video.getKeyVideo())
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW, youtube);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void addFavorite(View view) {
        MovieMini movieMini = new MovieMini(
                movieClass.getMovieId(),
                movieClass.getOriginalTitle(),
                movieClass.getUserRating(),
                movieClass.getImgThumbnailpath()
        );
        dbMovie.addMovieFavorite(movieMini);
    }


    public class FetchMovieAsyncTask extends AsyncTask<Integer, Void, Movie> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);
            updateDataDetails(movie);
        }

        @Override
        protected Movie doInBackground(Integer... params) {

            if(params.length == 0){
                return null;
            }

            int idMovie = params[0];

            URL url = NetworkUtils.buildURLForgetDetails(idMovie);
            Movie temp = null;

            try {
                String json = NetworkUtils.getResponseFromHTTPUrl(url);

                temp = MovieJsonUtils.getMovieFromJson(DetailActivity.this, json);

                return temp;

            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
    }
}
