package com.assignment.moviebuff.movierepo.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie implements Parcelable {

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

    @ColumnInfo(name = "backdrop_path")
    public String backdropPath;

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

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.movieId);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeString(this.backdropPath);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.movieId = in.readInt();
        this.title = in.readString();
        this.posterPath = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.backdropPath = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
