package abe.appsfactory.nanodegree.popular_movies.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import abe.appsfactory.nanodegree.popular_movies.BuildConfig;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.MoviePosterModel;

@SuppressWarnings("unused")
public class MovieDetailsModel extends MoviePosterModel implements Parcelable {
    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("adult")
    private boolean mAdult;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("id")
    private int mId;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("popularity")
    private float mPopularity;

    @SerializedName("vote_count")
    private int mVoteCount;

    @SerializedName("vote_average")
    private float mVoteAverage;

    private MovieDetailsModel(Parcel in) {
        mPosterPath = in.readString();
        mAdult = in.readByte() != 0;
        mOverview = in.readString();
        mReleaseDate = in.readString();
        mId = in.readInt();
        mOriginalTitle = in.readString();
        mTitle = in.readString();
        mPopularity = in.readFloat();
        mVoteCount = in.readInt();
        mVoteAverage = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPosterPath);
        dest.writeByte((byte) (mAdult ? 1 : 0));
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);
        dest.writeInt(mId);
        dest.writeString(mOriginalTitle);
        dest.writeString(mTitle);
        dest.writeFloat(mPopularity);
        dest.writeInt(mVoteCount);
        dest.writeFloat(mVoteAverage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieDetailsModel> CREATOR = new Creator<MovieDetailsModel>() {
        @Override
        public MovieDetailsModel createFromParcel(Parcel in) {
            return new MovieDetailsModel(in);
        }

        @Override
        public MovieDetailsModel[] newArray(int size) {
            return new MovieDetailsModel[size];
        }
    };

    public String getPosterPath() {
        return mPosterPath;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public int getId() {
        return mId;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    @Override
    public String getPosterUrl() {
        return BuildConfig.POSTER_BASE_URL + mPosterPath;
    }
}
