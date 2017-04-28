package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import abe.appsfactory.nanodegree.popular_movies.network.models.MovieDetailsModel;

public class DetailPresenter extends BaseObservable {
    public final ObservableField<String> mTitle;
    public final ObservableField<String> mRating;
    public final ObservableField<String> mReleaseDate;
    public final ObservableField<String> mOverview;
    public final ObservableField<String> mPosterUrl;

    public DetailPresenter(MovieDetailsModel model) {
        mTitle = new ObservableField<>(model.getTitle());
        mRating = new ObservableField<>(String.valueOf(model.getVoteAverage()));
        mReleaseDate = new ObservableField<>(model.getReleaseDate());
        mOverview = new ObservableField<>(model.getOverview());
        mPosterUrl = new ObservableField<>(model.getPosterUrl());
    }
}
