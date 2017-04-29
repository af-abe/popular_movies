package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import abe.appsfactory.nanodegree.popular_movies.R;

public abstract class TrailerModel extends BaseObservable {
    @BindingAdapter("trailerImageUrl")
    public static void loadImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.youtube_placeholder)
                .into(imageView);
    }

    @Bindable
    public abstract String getTrailerImageUrl();

    @Bindable
    public abstract String getTrailerName();

    protected abstract String getYoutubeKey();

    public void onClick(Context context){
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/embed/" + getYoutubeKey() +"?autoplay=1"));
        context.startActivity(i);
    }
}
