package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import abe.appsfactory.nanodegree.popular_movies.R;

public abstract class ReviewModel extends BaseObservable {
    @Bindable
    public abstract String getAuthorName();

    @Bindable
    public abstract String getText();
}
