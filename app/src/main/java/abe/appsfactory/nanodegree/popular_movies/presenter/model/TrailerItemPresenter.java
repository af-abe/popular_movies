package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.logic.models.ITrailerModel;

public class TrailerItemPresenter extends BaseObservable implements ITrailerModel, Parcelable {


    private final String mTrailerImageUrl;
    private final String mYouTubeKey;
    private final String mTrailerName;

    public TrailerItemPresenter(ITrailerModel model) {
        mTrailerImageUrl = model.getTrailerImageUrl();
        mTrailerName = model.getTrailerName();
        mYouTubeKey = model.getYoutubeKey();
    }

    private TrailerItemPresenter(Parcel in) {
        mTrailerImageUrl = in.readString();
        mYouTubeKey = in.readString();
        mTrailerName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTrailerImageUrl);
        dest.writeString(mYouTubeKey);
        dest.writeString(mTrailerName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrailerItemPresenter> CREATOR = new Creator<TrailerItemPresenter>() {
        @Override
        public TrailerItemPresenter createFromParcel(Parcel in) {
            return new TrailerItemPresenter(in);
        }

        @Override
        public TrailerItemPresenter[] newArray(int size) {
            return new TrailerItemPresenter[size];
        }
    };

    @BindingAdapter("trailerImageUrl")
    public static void loadImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.youtube_placeholder)
                .into(imageView);
    }

    public void onClick(Context context){
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse(getYouTubeLink()));
        context.startActivity(i);
    }

    @Bindable
    @Override
    public String getTrailerImageUrl() {
        return mTrailerImageUrl;
    }

    @Bindable
    @Override
    public String getTrailerName() {
        return mTrailerName;
    }

    @Bindable
    @Override
    public String getYoutubeKey() {
        return mYouTubeKey;
    }

    public String getYouTubeLink(){
        return "https://www.youtube.com/embed/" + getYoutubeKey() +"?autoplay=1";
    }
}
