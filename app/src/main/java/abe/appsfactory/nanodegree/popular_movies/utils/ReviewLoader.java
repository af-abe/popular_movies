package abe.appsfactory.nanodegree.popular_movies.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import abe.appsfactory.nanodegree.popular_movies.logic.PlaceholderLogic;
import abe.appsfactory.nanodegree.popular_movies.network.models.APIReviewResult;

public class ReviewLoader extends AsyncTaskLoader<APIReviewResult> {
    private final int id;

    public ReviewLoader(Context context, int id) {
        super(context);
        this.id = id;
    }

    @Override
    public APIReviewResult loadInBackground() {
        return PlaceholderLogic.getRevies(id);
    }
}
