package abe.appsfactory.nanodegree.popular_movies.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import abe.appsfactory.nanodegree.popular_movies.logic.PlaceholderLogic;
import abe.appsfactory.nanodegree.popular_movies.network.models.APITrailerResults;

public class TrailerLoader extends AsyncTaskLoader<APITrailerResults> {
    private final int id;
    public TrailerLoader(Context context, int id)
    {
        super(context);
        this.id = id;
    }

    @Override
    public APITrailerResults loadInBackground() {
        return PlaceholderLogic.getTrailer(id);
    }
}
