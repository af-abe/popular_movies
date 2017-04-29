package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import abe.appsfactory.nanodegree.popular_movies.ui.activities.DetailActivity;
import abe.appsfactory.nanodegree.popular_movies.R;

public class MoviePosterItemPresenter extends BaseObservable{

    public final ObservableField<String> mPosterUrl;
    private final int mMovieId;

    public MoviePosterItemPresenter(IMovieDetails model) {
        mPosterUrl = new ObservableField<>(model.getPosterUrl());
        mMovieId = model.getMovieId();
    }

    @BindingAdapter("imageUrl")
    public static void loadImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

    public void onClick(Context context) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(DetailActivity.EXTRA_DETAIL_MOVIE_ID, mMovieId);
        context.startActivity(i);
    }
}
