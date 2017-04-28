package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import abe.appsfactory.nanodegree.popular_movies.ui.activities.DetailActivity;
import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.network.models.MovieDetailsModel;

public abstract class MoviePosterModel extends BaseObservable {
    @Bindable
    public abstract String getPosterUrl();

    @BindingAdapter("imageUrl")
    public static void loadImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

    public void onClick(Context context) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(DetailActivity.EXTRA_DETAIL_MODEL, (MovieDetailsModel) this);
        context.startActivity(i);
    }
}
