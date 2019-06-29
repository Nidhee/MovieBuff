package com.assignment.moviebuff.movierepo;

import com.assignment.moviebuff.movierepo.local.entity.Movie;

import java.util.ArrayList;

public class MovieResult {
    private ArrayList<Movie> arrayList;
    private APIErrorParser apiErrorParser;

    public ArrayList<Movie> getMovieList() {
        return arrayList;
    }

    public void setMovieList(ArrayList<Movie> arrayList) {
        this.arrayList = arrayList;
    }

    public APIErrorParser getApiErrorParser() {
        return apiErrorParser;
    }

    public void setApiErrorParser(APIErrorParser apiErrorParser) {
        this.apiErrorParser = apiErrorParser;
    }
}
