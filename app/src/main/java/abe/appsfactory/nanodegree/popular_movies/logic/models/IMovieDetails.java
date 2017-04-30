package abe.appsfactory.nanodegree.popular_movies.logic.models;

import android.os.Parcelable;

public interface IMovieDetails extends Parcelable {
    int getMovieId();

    String getPosterUrl();

    String getTitle();

    float getVoteAverage();

    String getOverview();

    String getReleaseDate();
}
