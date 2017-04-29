package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v4.app.LoaderManager;
import android.widget.Toast;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.logic.PlaceholderLogic;
import abe.appsfactory.nanodegree.popular_movies.persistance.RealmHelper;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.ReviewModel;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.TrailerModel;
import abe.appsfactory.nanodegree.popular_movies.utils.AsyncOperation;

public class DetailPresenter extends BaseObservable {
    private static final int LOADER_ID_TRAILER = 0;
    private static final int LOADER_ID_REVIEWS = 1;

    public final ObservableField<String> mTitle = new ObservableField<>();
    public final ObservableField<String> mRating = new ObservableField<>();
    public final ObservableField<String> mReleaseDate = new ObservableField<>();
    public final ObservableField<String> mOverview = new ObservableField<>();
    public final ObservableField<String> mPosterUrl = new ObservableField<>();
    private int mMovieId = -1;

    private ObservableArrayList<TrailerModel> mTrailerItems = new ObservableArrayList<>();
    private ObservableArrayList<ReviewModel> mReviewItems = new ObservableArrayList<>();

    public ObservableArrayList<TrailerModel> getTrailerItems() {
        return mTrailerItems;
    }

    public ObservableArrayList<ReviewModel> getReviewItems() {
        return mReviewItems;
    }

    public DetailPresenter(final Context context, LoaderManager supportLoaderManager, int modelId) {
        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID_TRAILER, () -> RealmHelper.getMovieById(modelId))
                .setOnSuccess(model -> {
                    if (model != null) {
                        mTitle.set(model.getTitle());
                        mRating.set(String.valueOf(model.getVoteAverage()));
                        mReleaseDate.set(model.getReleaseDate());
                        mOverview.set(model.getOverview());
                        mPosterUrl.set(model.getPosterUrl());
                        mMovieId = model.getMovieId();
                        loadTrailer(context, supportLoaderManager);
                        loadReviews(context, supportLoaderManager);
                    }
                }).setOnError(throwable -> Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show())
                .execute();
    }

    private void loadTrailer(final Context context, LoaderManager supportLoaderManager) {
        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID_TRAILER, () -> PlaceholderLogic.getTrailer(mMovieId))
                .setOnSuccess(data -> {
                    mTrailerItems.clear();
                    mTrailerItems.addAll(data.getResults());
                }).setOnError(throwable -> Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show())
                .execute();
    }

    private void loadReviews(final Context context, LoaderManager supportLoaderManager) {
        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID_REVIEWS, () -> PlaceholderLogic.getRevies(mMovieId))
                .setOnSuccess(data -> {
                    mReviewItems.clear();
                    mReviewItems.addAll(data.getResults());
                }).setOnError(throwable -> Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show())
                .execute();
    }
}
