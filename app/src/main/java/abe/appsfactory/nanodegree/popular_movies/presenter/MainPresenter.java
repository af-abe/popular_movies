package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import abe.appsfactory.nanodegree.popular_movies.logic.SortLogic;
import abe.appsfactory.nanodegree.popular_movies.network.models.MovieDetailsModel;
import abe.appsfactory.nanodegree.popular_movies.network.models.PopularMoviesResponseModel;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.MoviePosterModel;
import abe.appsfactory.nanodegree.popular_movies.utils.MovieLoader;


public class MainPresenter extends BaseObservable {
    private static final int LOADER_ID = 0;
    private ObservableArrayList<MoviePosterModel> mItems = new ObservableArrayList<>();
    public ObservableInt mLoadingVisibility = new ObservableInt(View.GONE);

    public ObservableArrayList<MoviePosterModel> getItems() {
        return mItems;
    }

    public void loadMovies(final Context context, LoaderManager supportLoaderManager) {
        mLoadingVisibility.set(View.VISIBLE);
        supportLoaderManager.restartLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<PopularMoviesResponseModel>() {
            @Override
            public Loader<PopularMoviesResponseModel> onCreateLoader(int id, Bundle args) {
                return new MovieLoader(context);
            }

            @Override
            public void onLoadFinished(Loader<PopularMoviesResponseModel> loader, PopularMoviesResponseModel data) {
                mLoadingVisibility.set(View.GONE);
                mItems.clear();
                mItems.addAll(
                        sort(
                                loader.getContext(),
                                data.getPopularMovies()
                        )
                );
            }

            @Override
            public void onLoaderReset(Loader<PopularMoviesResponseModel> loader) {

            }
        }).forceLoad();
    }

    private List<MovieDetailsModel> sort(Context context, List<MovieDetailsModel> list) {
        if (SortLogic.getInstance(context).getSort() == SortLogic.SORT_POPULAR) {
            Collections.sort(list, (o1, o2) -> Double.compare(o2.getPopularity(), o1.getPopularity()));
        } else {
            Collections.sort(list, (o1, o2) -> Double.compare(o2.getVoteAverage(), o1.getVoteAverage()));
        }
        return list;
    }
}
