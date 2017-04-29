package abe.appsfactory.nanodegree.popular_movies.persistance.models;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("unused")
public class DbMovieDetailsModel extends RealmObject implements IMovieDetails {

    @PrimaryKey
    private int mId;

    private String mPosterPath;

    private String mOverview;

    private String mReleaseDate;

    private String mTitle;

    private float mVoteAverage;


    public DbMovieDetailsModel() {
    }

    public DbMovieDetailsModel(IMovieDetails details) {
        mId = details.getMovieId();
        mPosterPath = details.getPosterUrl();
        mOverview = details.getOverview();
        mReleaseDate = details.getReleaseDate();
        mTitle = details.getTitle();
        mVoteAverage = details.getVoteAverage();
    }

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
