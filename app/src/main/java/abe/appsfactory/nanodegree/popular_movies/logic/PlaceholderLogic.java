package abe.appsfactory.nanodegree.popular_movies.logic;

import android.content.Context;

import java.util.List;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import abe.appsfactory.nanodegree.popular_movies.network.TheMovieDBApi;
import abe.appsfactory.nanodegree.popular_movies.network.models.APIReviewResult;
import abe.appsfactory.nanodegree.popular_movies.network.models.APITrailerResults;
import abe.appsfactory.nanodegree.popular_movies.persistance.DaoHelper;

public class PlaceholderLogic {
    public static List<? extends IMovieDetails> getMovies(boolean popular) throws Exception {
        return TheMovieDBApi.getPopularMovies(popular).getPopularMovies();
    }

    public static APITrailerResults getTrailer(int id) throws Exception {
        return TheMovieDBApi.getTrailer(id);
    }

    public static APIReviewResult getRevies(int id) throws Exception {
        return TheMovieDBApi.getReviews(id);
    }

    public static List<? extends IMovieDetails> getMoviesFavorites(Context context) {
        return DaoHelper.getAllMovies(context);
    }
}
