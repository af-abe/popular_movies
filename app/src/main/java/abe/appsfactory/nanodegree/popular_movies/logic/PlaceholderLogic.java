package abe.appsfactory.nanodegree.popular_movies.logic;

import abe.appsfactory.nanodegree.popular_movies.network.TheMovieDBApi;
import abe.appsfactory.nanodegree.popular_movies.network.models.APIReviewResult;
import abe.appsfactory.nanodegree.popular_movies.network.models.PopularMoviesResponseModel;
import abe.appsfactory.nanodegree.popular_movies.network.models.APITrailerResults;

public class PlaceholderLogic {
    public static PopularMoviesResponseModel getMovies(boolean popular) {
        return TheMovieDBApi.getMovies(popular);
    }

    public static APITrailerResults getTrailer(int id) {
        return TheMovieDBApi.getTrailer(id);
    }

    public static APIReviewResult getRevies(int id) {
        return TheMovieDBApi.getReviews(id);
    }
}
