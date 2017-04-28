package abe.appsfactory.nanodegree.popular_movies.logic;

import abe.appsfactory.nanodegree.popular_movies.network.TheMovieDBApi;
import abe.appsfactory.nanodegree.popular_movies.network.models.PopularMoviesResponseModel;

public class PlaceholderLogic {
    public static PopularMoviesResponseModel getMovies(boolean popular) {
        return TheMovieDBApi.getPopularMovies(popular);
    }
}
