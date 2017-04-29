package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public abstract class ReviewModel extends BaseObservable {
    @Bindable
    public abstract String getAuthorName();

    @Bindable
    public abstract String getText();
}
