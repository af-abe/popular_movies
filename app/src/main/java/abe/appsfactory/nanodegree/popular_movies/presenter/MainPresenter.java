package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.ArrayList;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.logic.PlaceholderLogic;
import abe.appsfactory.nanodegree.popular_movies.logic.SortLogic;
import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.MoviePosterItemPresenter;
import abe.appsfactory.nanodegree.popular_movies.utils.AsyncOperation;


public class MainPresenter extends BasePresenter {
    private static final int LOADER_ID = 0;


    private final ObservableArrayList<MoviePosterItemPresenter> mItems = new ObservableArrayList<>();
    public final ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);

    public ObservableArrayList<MoviePosterItemPresenter> getItems() {
        return mItems;
    }

    @SuppressLint("SwitchIntDef")
    public void loadMovies(final Context context, LoaderManager supportLoaderManager) {
        mLoadingVisibility.set(View.VISIBLE);

        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID, () -> {
            switch (SortLogic.getInstance(context).getSort()) {
                case SortLogic.SORT_POPULAR:
                    return PlaceholderLogic.getMovies(true);
                case SortLogic.SORT_RATED:
                    return PlaceholderLogic.getMovies(false);
            }
            return PlaceholderLogic.getMoviesFavorites();
        }).setOnSuccess(data -> {
            mLoadingVisibility.set(View.GONE);
            mItems.clear();
            for (IMovieDetails details : data) {
                mItems.add(new MoviePosterItemPresenter(details));
            }
        }).setOnError(throwable -> {
            mLoadingVisibility.set(View.GONE);
            new AlertDialog.Builder(context)
                    .setMessage(R.string.error_dialog_msg)
                    .setTitle(R.string.error_dialog_title)
                    .setPositiveButton(R.string.error_dialog_button, (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        loadMovies(context, supportLoaderManager);
                    }).setCancelable(false)
                    .show();
        }).execute();
    }

    @Override
    public void saveState(Bundle out) {
        out.putParcelableArrayList(MoviePosterItemPresenter.class.getCanonicalName(), mItems);
    }

    @Override
    public void restoreState(Bundle state) {
        ArrayList<MoviePosterItemPresenter> arrayList = state.getParcelableArrayList(MoviePosterItemPresenter.class.getCanonicalName());
        if (arrayList != null) {
            mItems.addAll(arrayList);
        }
    }
}
