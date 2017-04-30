package abe.appsfactory.nanodegree.popular_movies.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class APIPopularMoviesResponseModel {
    @SerializedName("page")
    private int mCurrentPage;
    @SerializedName("results")
    private List<APIMovieDetailsModel> mPopularMovies;
    @SerializedName("total_results")
    private int mTotalResults;
    @SerializedName("total_pages")
    private int mTotalPages;

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public List<APIMovieDetailsModel> getPopularMovies() {
        return mPopularMovies;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }
}
