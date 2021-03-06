package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.content.Context;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.logic.PlaceholderLogic;
import abe.appsfactory.nanodegree.popular_movies.logic.models.IReviewModel;
import abe.appsfactory.nanodegree.popular_movies.logic.models.ITrailerModel;
import abe.appsfactory.nanodegree.popular_movies.persistance.DaoHelper;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.ReviewItemPresenter;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.TrailerItemPresenter;
import abe.appsfactory.nanodegree.popular_movies.ui.activities.MainActivity;
import abe.appsfactory.nanodegree.popular_movies.ui.activities.models.MovieIntentModel;
import abe.appsfactory.nanodegree.popular_movies.utils.AsyncOperation;

public class DetailPresenter extends BasePresenter {
    private static final int LOADER_ID_TRAILER = 0;
    private static final int LOADER_ID_REVIEWS = 1;
    public final ObservableBoolean mIsFavorite = new ObservableBoolean(false);
    private final MovieIntentModel mModel;

    private final ObservableArrayList<TrailerItemPresenter> mTrailerItems = new ObservableArrayList<>();
    private final ObservableArrayList<ReviewItemPresenter> mReviewItems = new ObservableArrayList<>();

    public DetailPresenter(Context context, MovieIntentModel model) {
        mIsFavorite.set(DaoHelper.hasMovieWithId(context, model.getMovieId()));
        mModel = model;
    }

    public ObservableArrayList<TrailerItemPresenter> getTrailerItems() {
        return mTrailerItems;
    }

    public ObservableArrayList<ReviewItemPresenter> getReviewItems() {
        return mReviewItems;
    }

    public DetailPresenter(final Context context, LoaderManager supportLoaderManager, MovieIntentModel model) {
        mIsFavorite.set(DaoHelper.hasMovieWithId(context, model.getMovieId()));
        mModel = model;
        loadTrailer(context, supportLoaderManager);
        loadReviews(context, supportLoaderManager);
    }

    private void loadTrailer(final Context context, LoaderManager supportLoaderManager) {
        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID_TRAILER, () -> PlaceholderLogic.getTrailer(mModel.getMovieId()))
                .setOnSuccess(data -> {
                    mTrailerItems.clear();
                    for (ITrailerModel model : data.getResults()) {
                        mTrailerItems.add(new TrailerItemPresenter(model));
                    }
                }).setOnError(throwable -> Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show())
                .execute();
    }

    private void loadReviews(final Context context, LoaderManager supportLoaderManager) {
        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID_REVIEWS, () -> PlaceholderLogic.getRevies(mModel.getMovieId()))
                .setOnSuccess(data -> {
                    mReviewItems.clear();
                    for (IReviewModel model : data.getResults()) {
                        mReviewItems.add(new ReviewItemPresenter(model));
                    }
                }).setOnError(throwable -> Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show())
                .execute();
    }

    @Bindable
    public String getTitle() {
        return mModel.getTitle();
    }

    @Bindable
    public float getRating() {
        return mModel.getVoteAverage();
    }

    @Bindable
    public String getReleaseDate() {
        return mModel.getReleaseDate();
    }

    @Bindable
    public String getOverview() {
        return mModel.getOverview();
    }

    @Bindable
    public String getPosterUrl() {
        return mModel.getPosterUrl();
    }

    public void onClickFav(Context context) {
        if (mIsFavorite.get()) {
            DaoHelper.removeMovie(context, mModel);
            mIsFavorite.set(false);
        } else {
            DaoHelper.persistMovie(context, mModel);
            mIsFavorite.set(true);
        }
        MainActivity.triggerReload = true;
    }

    @Override
    public void saveState(Bundle out) {
        out.putParcelableArrayList(TrailerItemPresenter.class.getCanonicalName(), mTrailerItems);
        out.putParcelableArrayList(ReviewItemPresenter.class.getCanonicalName(), mReviewItems);
    }

    @Override
    public void restoreState(Bundle state) {
        ArrayList<TrailerItemPresenter> arrayListTrailer = state.getParcelableArrayList(TrailerItemPresenter.class.getCanonicalName());
        if (arrayListTrailer != null) {
            mTrailerItems.addAll(arrayListTrailer);
        }
        ArrayList<ReviewItemPresenter> arrayListReviews = state.getParcelableArrayList(ReviewItemPresenter.class.getCanonicalName());
        if (arrayListReviews != null) {
            mReviewItems.addAll(arrayListReviews);
        }
    }

    @BindingAdapter("setFavorite")
    public static void setFavorite(ImageView view, boolean state) {
        if (state) {
            view.setImageResource(android.R.drawable.star_big_on);
        } else {
            view.setImageResource(android.R.drawable.star_big_off);
        }
    }

    public void shareYouTube(Context context) {
        if (mTrailerItems.size() > 0) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Look!");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mTrailerItems.get(0).getYouTubeLink());
            context.startActivity(Intent.createChooser(sharingIntent, "Select App"));
        }
    }
}
