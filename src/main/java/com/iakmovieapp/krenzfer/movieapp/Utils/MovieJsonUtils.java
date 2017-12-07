package com.iakmovieapp.krenzfer.movieapp.Utils;

import android.content.Context;
import android.widget.Toast;

import com.iakmovieapp.krenzfer.movieapp.Model.ListMovie;
import com.iakmovieapp.krenzfer.movieapp.Model.Movie;
import com.iakmovieapp.krenzfer.movieapp.Model.MovieMini;
import com.iakmovieapp.krenzfer.movieapp.Model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by krenzfer on 03/09/17.
 */

public final class MovieJsonUtils {

    public static ListMovie getListMovie(Context context, String rawJsonMovie) throws JSONException{

        final String OWN_RESULTS = "results";

        final String OWN_PAGE = "page";

        final String OWN_TOTAL_PAGE = "total_pages";

        final String OWN_TOTAL_RESULTS = "total_results";

        final String OWN_ORIGINAL_TITLE = "original_title";

        final String OWN_POSTER_PATH = "poster_path";

//        final String OWN_OVERVIEW = "overview";

        final String OWN_VOTE_AVERAGE = "vote_average";

//        final String OWN_RELEASE_DATE = "release_date";

        final String OWN_STATUS_CODE = "status_code";

        final String OWN_STATUS_MESSAGE = "status_message";

//        final String OWN_BACKDROP_PATH = "backdrop_path";

//        final String OWN_RUNTIME = "runtime";

        final String OWN_MOVIE_ID = "id";

        ListMovie listMovie = new ListMovie();
        MovieMini[] tempMovies = null;

        JSONObject movies = new JSONObject(rawJsonMovie);

        if(movies.has(OWN_STATUS_CODE)){
            Toast.makeText(context, movies.getString(OWN_STATUS_CODE)+"::"+movies.getString(OWN_STATUS_MESSAGE), Toast.LENGTH_LONG).show();
            return null;
        }

        listMovie.setPage(movies.getInt(OWN_PAGE));
        listMovie.setTotalPage(movies.getInt(OWN_TOTAL_PAGE));
        listMovie.setTotalResult(movies.getInt(OWN_TOTAL_RESULTS));

        JSONArray list = movies.getJSONArray(OWN_RESULTS);
        tempMovies = new MovieMini[list.length()];

        for (int i = 0; i < tempMovies.length; i++) {

            JSONObject object = list.getJSONObject(i);

            MovieMini tempMovie = new MovieMini(
                    object.getInt(OWN_MOVIE_ID),
                    object.getString(OWN_ORIGINAL_TITLE),
                    object.getDouble(OWN_VOTE_AVERAGE),
                    object.getString(OWN_POSTER_PATH)
            );

            tempMovies[i] = tempMovie;
        }

        listMovie.setMovieMinis(tempMovies);

        return listMovie;

    }

    public static Movie getMovieFromJson(Context context, String Json) throws JSONException{
        final String OWN_BACKDROP_PATH = "backdrop_path";
        final String OWN_ORIGINAL_TITLE = "original_title";
        final String OWN_POSTER_PATH = "poster_path";
        final String OWN_OVERVIEW = "overview";
        final String OWN_VOTE_AVERAGE = "vote_average";
        final String OWN_RELEASE_DATE = "release_date";
        final String OWN_RUNTIME = "runtime";
        final String OWN_MOVIE_ID = "id";
        final String OWN_VIDEOS = "videos";
        final String OWN_RESULTS = "results";
        final String OWN_STATUS_CODE = "status_code";
        final String OWN_STATUS_MESSAGE = "status_message";
        final String OWN_ID_VIDEO = "id";
        final String OWN_KEY_VIDEO = "key";
        final String OWN_NAME_VIDEO = "name";
        final String OWN_SITE_VIDEO = "site";


        JSONObject movieJsonoObject = new JSONObject(Json);

        if(movieJsonoObject.has(OWN_STATUS_CODE)){
            Toast.makeText(context, movieJsonoObject.getString(OWN_STATUS_CODE)+"::"+movieJsonoObject.getString(OWN_STATUS_MESSAGE), Toast.LENGTH_LONG).show();
            return null;
        }

        Movie movie = null;

        Video[] videos = null;

        JSONObject videosJsonObject = movieJsonoObject.getJSONObject(OWN_VIDEOS);

        JSONArray videosJsonArray = videosJsonObject.getJSONArray(OWN_RESULTS);

        videos = new Video[videosJsonArray.length()];

        for (int i = 0;i<videos.length;i++){
            JSONObject temp = videosJsonArray.getJSONObject(i);

            Video tempVideo = new Video(
                    temp.getString(OWN_ID_VIDEO),
                    temp.getString(OWN_KEY_VIDEO),
                    temp.getString(OWN_NAME_VIDEO),
                    temp.getString(OWN_SITE_VIDEO)
            );

            videos[i] = tempVideo;
        }

        movie = new Movie(
                movieJsonoObject.getString(OWN_BACKDROP_PATH),
                movieJsonoObject.getString(OWN_ORIGINAL_TITLE),
                movieJsonoObject.getString(OWN_POSTER_PATH),
                movieJsonoObject.getString(OWN_OVERVIEW),
                movieJsonoObject.getDouble(OWN_VOTE_AVERAGE),
                movieJsonoObject.getString(OWN_RELEASE_DATE),
                movieJsonoObject.getInt(OWN_RUNTIME),
                movieJsonoObject.getInt(OWN_ID_VIDEO),
                videos
        );

        return movie;

    }

}