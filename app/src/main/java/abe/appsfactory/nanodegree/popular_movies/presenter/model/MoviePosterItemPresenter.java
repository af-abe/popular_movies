package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import abe.appsfactory.nanodegree.popular_movies.ui.activities.DetailActivity;
import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.ui.activities.models.MovieIntentModel;

public class MoviePosterItemPresenter extends BaseObservable implements Parcelable{

    private final MovieIntentModel mIntentModel;

    public MoviePosterItemPresenter(IMovieDetails model) {
        mIntentModel = new MovieIntentModel(model);
    }

    private MoviePosterItemPresenter(Parcel in) {
        mIntentModel = in.readParcelable(MovieIntentModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mIntentModel, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MoviePosterItemPresenter> CREATOR = new Creator<MoviePosterItemPresenter>() {
        @Override
        public MoviePosterItemPresenter createFromParcel(Parcel in) {
            return new MoviePosterItemPresenter(in);
        }

        @Override
        public MoviePosterItemPresenter[] newArray(int size) {
            return new MoviePosterItemPresenter[size];
        }
    };

    @Bindable
    public String getPosterUrl(){
        return mIntentModel.getPosterUrl();
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
        i.putExtra(DetailActivity.EXTRA_DETAIL_MOVIE_ID, mIntentModel);
        context.startActivity(i);
    }
}
