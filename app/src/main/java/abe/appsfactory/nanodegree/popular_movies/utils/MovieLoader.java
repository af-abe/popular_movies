package abe.appsfactory.nanodegree.popular_movies.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import abe.appsfactory.nanodegree.popular_movies.logic.PlaceholderLogic;
import abe.appsfactory.nanodegree.popular_movies.network.models.PopularMoviesResponseModel;

/**
 * Created by APPSfactory on 14.10.2016.
 */

public class MovieLoader extends AsyncTaskLoader<PopularMoviesResponseModel> {
    public MovieLoader(Context context) {
        super(context);
    }

    @Override
    public PopularMoviesResponseModel loadInBackground() {
        return PlaceholderLogic.getMovies();
    }
}
