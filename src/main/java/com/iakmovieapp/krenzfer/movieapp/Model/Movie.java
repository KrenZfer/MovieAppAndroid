package com.iakmovieapp.krenzfer.movieapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by krenzfer on 03/09/17.
 */

public class Movie implements Parcelable{

    private String backdropPath;
    private String originalTitle;
    private String imgThumbnailpath;
    private String sinopsisMovie;
    private Double userRating;
    private String releaseDate;
    private int runTime;
    private int movieId;
    private Video[] videoList;

    public Movie(String backdropPath, String originalTitle, String imgThumbnailpath,
                 String sinopsisMovie, Double userRating, String releaseDate, int runTime,
                 int movieId, Video[] videoList) {
        this.backdropPath = backdropPath;
        this.originalTitle = originalTitle;
        this.imgThumbnailpath = imgThumbnailpath;
        this.sinopsisMovie = sinopsisMovie;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.runTime = runTime;
        this.movieId = movieId;
        this.videoList = videoList;
    }


    protected Movie(Parcel in) {
        backdropPath = in.readString();
        originalTitle = in.readString();
        imgThumbnailpath = in.readString();
        sinopsisMovie = in.readString();
        releaseDate = in.readString();
        runTime = in.readInt();
        movieId = in.readInt();
        videoList = in.createTypedArray(Video.CREATOR);
        userRating = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getImgThumbnailpath() {
        return imgThumbnailpath;
    }

    public void setImgThumbnailpath(String imgThumbnailpath) {
        this.imgThumbnailpath = imgThumbnailpath;
    }

    public String getSinopsisMovie() {
        return sinopsisMovie;
    }

    public void setSinopsisMovie(String sinopsisMovie) {
        this.sinopsisMovie = sinopsisMovie;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Video[] getVideoList() {
        return videoList;
    }

    public void setVideoList(Video[] videoList) {
        this.videoList = videoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backdropPath);
        dest.writeString(originalTitle);
        dest.writeString(imgThumbnailpath);
        dest.writeString(sinopsisMovie);
        dest.writeString(releaseDate);
        dest.writeInt(runTime);
        dest.writeInt(movieId);
        dest.writeTypedArray(videoList, flags);
        dest.writeDouble(userRating);
    }
}
