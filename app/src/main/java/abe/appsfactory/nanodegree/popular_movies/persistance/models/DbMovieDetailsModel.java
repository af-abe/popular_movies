package abe.appsfactory.nanodegree.popular_movies.persistance.models;

import android.os.Parcel;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;

@SuppressWarnings("unused")
public class DbMovieDetailsModel implements IMovieDetails {

    private int mId;

    private String mPosterPath;

    private String mOverview;

    private String mReleaseDate;

    private String mTitle;

    private float mVoteAverage;


    public DbMovieDetailsModel() {
    }

    public DbMovieDetailsModel(int id, String posterPath, String overview, String releaseDate, String title, float voteAverage) {
        mId = id;
        mPosterPath = posterPath;
        mOverview = overview;
        mReleaseDate = releaseDate;
        mTitle = title;
        mVoteAverage = voteAverage;
    }

    public DbMovieDetailsModel(IMovieDetails details) {
        mId = details.getMovieId();
        mPosterPath = details.getPosterUrl();
        mOverview = details.getOverview();
        mReleaseDate = details.getReleaseDate();
        mTitle = details.getTitle();
        mVoteAverage = details.getVoteAverage();
    }

    private DbMovieDetailsModel(Parcel in) {
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

    public static final Creator<DbMovieDetailsModel> CREATOR = new Creator<DbMovieDetailsModel>() {
        @Override
        public DbMovieDetailsModel createFromParcel(Parcel in) {
            return new DbMovieDetailsModel(in);
        }

        @Override
        public DbMovieDetailsModel[] newArray(int size) {
            return new DbMovieDetailsModel[size];
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
