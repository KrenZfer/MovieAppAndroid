package com.iakmovieapp.krenzfer.movieapp.Model;

public class MovieMini {
    private int idMovie;
    private String original_Title;
    private Double vote_average;
    private String posterPath;

    public MovieMini(int idMovie, String original_Title, Double vote_average, String posterPath) {
        this.idMovie = idMovie;
        this.original_Title = original_Title;
        this.vote_average = vote_average;
        this.posterPath = posterPath;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getOriginal_Title() {
        return original_Title;
    }

    public void setOriginal_Title(String original_Title) {
        this.original_Title = original_Title;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
