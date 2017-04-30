package abe.appsfactory.nanodegree.popular_movies.ui.activities.models;

import android.os.Parcel;
import android.os.Parcelable;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;

public class MovieIntentModel implements IMovieDetails, Parcelable {
    private int mId;

    private String mPosterPath;

    private String mOverview;

    private String mReleaseDate;

    private String mTitle;

    private float mVoteAverage;

    public MovieIntentModel(IMovieDetails details) {
        mId = details.getMovieId();
        mPosterPath = details.getPosterUrl();
        mOverview = details.getOverview();
        mReleaseDate = details.getReleaseDate();
        mTitle = details.getTitle();
        mVoteAverage = details.getVoteAverage();
    }

    private MovieIntentModel(Parcel in) {
        mId = in.readInt();
        mPosterPath = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mTitle = in.readString();
        mVoteAverage = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mPosterPath);
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);
        dest.writeString(mTitle);
        dest.writeFloat(mVoteAverage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieIntentModel> CREATOR = new Creator<MovieIntentModel>() {
        @Override
        public MovieIntentModel createFromParcel(Parcel in) {
            return new MovieIntentModel(in);
        }

        @Override
        public MovieIntentModel[] newArray(int size) {
            return new MovieIntentModel[size];
        }
    };

    @Override
    public int getMovieId() {
        return mId;
    }

    @Override
    public String getPosterUrl() {
        return mPosterPath;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public float getVoteAverage() {
        return mVoteAverage;
    }

    @Override
    public String getOverview() {
        return mOverview;
    }

    @Override
    public String getReleaseDate() {
        return mReleaseDate;
    }
}
