package com.iakmovieapp.krenzfer.movieapp.Model;

/**
 * Created by krenzfer on 03/09/17.
 */

public class ListMovie {

    private int page;
//    private Movie[] movieList;
    private MovieMini[] movieMinis;
    private int totalResult;
    private int totalPage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public MovieMini[] getMovieMinis() {
        return movieMinis;
    }

    public void setMovieMinis(MovieMini[] movieMinis) {
        this.movieMinis = movieMinis;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}

