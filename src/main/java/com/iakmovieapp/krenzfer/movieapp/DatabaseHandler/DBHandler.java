package com.iakmovieapp.krenzfer.movieapp.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.iakmovieapp.krenzfer.movieapp.Data.DataMovieContract;
import com.iakmovieapp.krenzfer.movieapp.Model.MovieMini;

/**
 * Created by krenzfer on 07/09/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    private Context context;

    private static final String CREATE_TABLE_MOVIE="CREATE TABLE IF NOT EXISTS "+ DataMovieContract.DataMovie._TABLE_NAME+"("+
            DataMovieContract.DataMovie._ID_MOVIE+" VARCHAR(255) PRIMARY KEY,"+
            DataMovieContract.DataMovie._ORIGINAL_TITLE+" VARCHAR(255),"+
            DataMovieContract.DataMovie._VOTE_AVERAGE+" VARCHAR(255),"+
            DataMovieContract.DataMovie._POSTER_PATH+" VARCHAR(255)"+
            ");";

    private static final String DROP_TABLE_MOVIE="DROP TABLE  IF EXISTS "+ DataMovieContract.DataMovie._TABLE_NAME;
    private static final String selectMovieQuery = "SELECT * FROM " + DataMovieContract.DataMovie._TABLE_NAME;

    public DBHandler(Context context) {
        super(context, DataMovieContract.DataMovie._DB_NAME, null, DataMovieContract.DataMovie.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_MOVIE);
            Log.d("Database: :", "Database Successfully created");
//            Message.message(context,"onCreate dipanggil");
        } catch (SQLiteException e){
//            Message.message(context,""+e.toString());
//            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
//            Message.message(context,"onUpgrade dipanggil");
            db.execSQL(DROP_TABLE_MOVIE);
            onCreate(db);
        }catch (SQLiteException e){
//            Message.message(context,""+e);
        }
    }

    public void addMovieFavorite(MovieMini movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataMovieContract.DataMovie._ID_MOVIE, movie.getIdMovie());
        values.put(DataMovieContract.DataMovie._ORIGINAL_TITLE, movie.getOriginal_Title());
        values.put(DataMovieContract.DataMovie._VOTE_AVERAGE, String.valueOf(movie.getVote_average()));
        values.put(DataMovieContract.DataMovie._POSTER_PATH, movie.getPosterPath());
        // Inserting Row
        db.insert(DataMovieContract.DataMovie._TABLE_NAME, null, values);
        Log.d("Add : :","Add Success!!!");
        db.close(); // Closing database connection
    }

    public void deleteMahasiswa(MovieMini movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataMovieContract.DataMovie._TABLE_NAME, DataMovieContract.DataMovie._ID_MOVIE + "=?",
                new String[] { String.valueOf(movie.getIdMovie())}
        );
        Log.d("Delete : :","Delete Success!!!");
        db.close();
    }

    public MovieMini[] getAllMovies() {
        MovieMini[] movieList = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectMovieQuery, null);
        movieList = new MovieMini[cursor.getColumnCount()];
        // looping through all rows and adding to list
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                MovieMini movieMini = new MovieMini(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Double.parseDouble(cursor.getString(2)),
                        cursor.getString(3)
                );
                // Adding siswa to list
                movieList[i] = movieMini;
                i++;
            } while (cursor.moveToNext());
        }

        cursor.close();
// return contact list
        return movieList;
    }

}
