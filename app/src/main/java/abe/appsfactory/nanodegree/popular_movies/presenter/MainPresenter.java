package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.logic.PlaceholderLogic;
import abe.appsfactory.nanodegree.popular_movies.logic.SortLogic;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.MoviePosterModel;
import abe.appsfactory.nanodegree.popular_movies.utils.AsyncOperation;


public class MainPresenter extends BaseObservable {
    private static final int LOADER_ID = 0;
    private ObservableArrayList<MoviePosterModel> mItems = new ObservableArrayList<>();
    public ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);

    public ObservableArrayList<MoviePosterModel> getItems() {
        return mItems;
    }

    public void loadMovies(final Context context, LoaderManager supportLoaderManager) {
        mLoadingVisibility.set(View.VISIBLE);

        new AsyncOperation<>(context, supportLoaderManager, LOADER_ID, () -> {
            boolean popular = SortLogic.getInstance(context).getSort() == SortLogic.SORT_POPULAR;
            return PlaceholderLogic.getMovies(popular);
        }).setOnSuccess(data -> {
            mLoadingVisibility.set(View.GONE);
            mItems.clear();
            mItems.addAll(data.getPopularMovies());
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
}
