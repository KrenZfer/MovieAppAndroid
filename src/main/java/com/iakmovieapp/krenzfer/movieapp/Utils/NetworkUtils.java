package com.iakmovieapp.krenzfer.movieapp.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by krenzfer on 03/09/17.
 */

public final class NetworkUtils {

    public static final String TAG = NetworkUtils.class.getSimpleName();

    public static final String TMBD_URL = "http://api.themoviedb.org/3/";

    public static final String TMDB_IMAGE_CONFIGURATION_URL = "http://image.tmdb.org/t/p/w185";

    public static final String YOUTUBE_PlAY_URL_BASE = "https://www.youtube.com/watch";

    public static final String MOVIE_BASE_URL = TMBD_URL;

    public static final String MOVIE_END_POINT = "movie/";

    public static final String POPULAR_MOVIE_END_POINT = "popular";

    public static final String TOP_RATED_MOVIE_END_POINT = "top_rated";

    public static final String REVIEWS_END_POINT = "/reviews";

    public static final String VIDEO_END_POINT = "videos";

    //find your own api_key from TMDB website account
    private static final String TMBD_API_KEY = "";

    private static final String DEFAULT_LANGUAGE = "en-US";
    private static final int DEFAULT_NUM_PAGE = 1;

    public static String API_KEY_PARAM = "api_key";
    public static String LANGUAGE_PARAM = "language";
    public static String PAGE_PARAM = "page";
    public static String APPEND_REQUEST = "append_to_response";
    public static String VIDEO_YOUTUBE_PARAM = "v";

    public static URL buildURLForListMovie(final String end_point){
        Uri uri = Uri.parse(MOVIE_BASE_URL+MOVIE_END_POINT+end_point).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, TMBD_API_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, DEFAULT_LANGUAGE)
                .appendQueryParameter(PAGE_PARAM, Integer.toString(DEFAULT_NUM_PAGE))
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
            return url;
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static URL buildURLForgetReviews(int idMovie){
        Uri uri = Uri.parse(MOVIE_BASE_URL+idMovie+REVIEWS_END_POINT).buildUpon()
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
            return url;
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public URL buildURLForgetVideos(int idMovie){
        Uri uri = Uri.parse(MOVIE_BASE_URL+idMovie+VIDEO_END_POINT).buildUpon()
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
            return url;
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static URL buildURLForgetDetails(int idMovie){
        Uri uri = Uri.parse(MOVIE_BASE_URL+MOVIE_END_POINT+idMovie).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, TMBD_API_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, DEFAULT_LANGUAGE)
                .appendQueryParameter(APPEND_REQUEST, VIDEO_END_POINT)
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
            return url;
        }catch (MalformedURLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static String getResponseFromHTTPUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput){
                return scanner.next();
            }else{
                return null;
            }

        }finally {
            urlConnection.disconnect();
        }
    }
}

