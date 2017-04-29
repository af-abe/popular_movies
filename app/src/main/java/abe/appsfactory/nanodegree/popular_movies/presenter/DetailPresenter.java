package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import abe.appsfactory.nanodegree.popular_movies.network.models.APIReviewResult;
import abe.appsfactory.nanodegree.popular_movies.network.models.APITrailerResults;
import abe.appsfactory.nanodegree.popular_movies.network.models.MovieDetailsModel;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.ReviewModel;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.TrailerModel;
import abe.appsfactory.nanodegree.popular_movies.utils.ReviewLoader;
import abe.appsfactory.nanodegree.popular_movies.utils.TrailerLoader;

public class DetailPresenter extends BaseObservable {
    private static final int LOADER_ID_TRAILER = 0;
    private static final int LOADER_ID_REVIEWS = 1;

    public final ObservableField<String> mTitle;
    public final ObservableField<String> mRating;
    public final ObservableField<String> mReleaseDate;
    public final ObservableField<String> mOverview;
    public final ObservableField<String> mPosterUrl;
    private final int mMovieId;

    private ObservableArrayList<TrailerModel> mTrailerItems = new ObservableArrayList<>();
    private ObservableArrayList<ReviewModel> mReviewItems = new ObservableArrayList<>();

    public ObservableArrayList<TrailerModel> getTrailerItems() {
        return mTrailerItems;
    }

    public ObservableArrayList<ReviewModel> getReviewItems() {
        return mReviewItems;
    }

    public DetailPresenter(MovieDetailsModel model) {
        mTitle = new ObservableField<>(model.getTitle());
        mRating = new ObservableField<>(String.valueOf(model.getVoteAverage()));
        mReleaseDate = new ObservableField<>(model.getReleaseDate());
        mOverview = new ObservableField<>(model.getOverview());
        mPosterUrl = new ObservableField<>(model.getPosterUrl());
        mMovieId = model.getId();
    }

    public void loadTrailer(final Context context, LoaderManager supportLoaderManager) {
        supportLoaderManager.restartLoader(LOADER_ID_TRAILER, null, new LoaderManager.LoaderCallbacks<APITrailerResults>() {
            @Override
            public Loader<APITrailerResults> onCreateLoader(int id, Bundle args) {
                return new TrailerLoader(context, mMovieId);
            }

            @Override
            public void onLoadFinished(Loader<APITrailerResults> loader, APITrailerResults data) {
                mTrailerItems.addAll(data.getResults());
            }

            @Override
            public void onLoaderReset(Loader<APITrailerResults> loader) {

            }
        }).forceLoad();
    }

    public void loadReviews(final Context context, LoaderManager supportLoaderManager) {
        supportLoaderManager.restartLoader(LOADER_ID_REVIEWS, null, new LoaderManager.LoaderCallbacks<APIReviewResult>() {
            @Override
            public Loader<APIReviewResult> onCreateLoader(int id, Bundle args) {
                return new ReviewLoader(context, mMovieId);
            }

            @Override
            public void onLoadFinished(Loader<APIReviewResult> loader, APIReviewResult data) {
                mReviewItems.addAll(data.getResults());
            }

            @Override
            public void onLoaderReset(Loader<APIReviewResult> loader) {

            }
        }).forceLoad();
    }
}
