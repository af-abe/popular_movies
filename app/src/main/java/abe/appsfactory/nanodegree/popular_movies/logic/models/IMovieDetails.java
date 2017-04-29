package abe.appsfactory.nanodegree.popular_movies.logic.models;

public interface IMovieDetails {
    int getMovieId();

    String getPosterUrl();

    String getTitle();

    float getVoteAverage();

    String getOverview();

    String getReleaseDate();
}
