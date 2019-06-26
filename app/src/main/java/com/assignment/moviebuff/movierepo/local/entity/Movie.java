package com.assignment.moviebuff.movierepo.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    public int movieId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "poster_path")
    public String posterPath;

    @ColumnInfo(name = "vote_average")
    public Double voteAverage;

    @ColumnInfo(name = "release_date")
    public String releaseDate;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "geners")
    public String geners;

    @ColumnInfo(name = "production_companies")
    public String productionCompanies;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGeners() {
        return geners;
    }

    public void setGeners(String geners) {
        this.geners = geners;
    }

    public String getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(String productionCompanies) {
        this.productionCompanies = productionCompanies;
    }
}
