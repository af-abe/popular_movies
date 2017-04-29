package abe.appsfactory.nanodegree.popular_movies.logic;

import abe.appsfactory.nanodegree.popular_movies.network.TheMovieDBApi;
import abe.appsfactory.nanodegree.popular_movies.network.models.PopularMoviesResponseModel;

public class PlaceholderLogic {
    public static PopularMoviesResponseModel getMovies(boolean popular) throws Exception {
        return TheMovieDBApi.getPopularMovies(popular);
    }
}
