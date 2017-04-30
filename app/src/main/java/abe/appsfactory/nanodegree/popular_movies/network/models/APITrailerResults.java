package abe.appsfactory.nanodegree.popular_movies.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class APITrailerResults {

    @SerializedName("id")
    private Integer id;
    @SerializedName("results")
    private List<APITrailerModel> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<APITrailerModel> getResults() {
        return results;
    }

    public void setResults(List<APITrailerModel> results) {
        this.results = results;
    }

}
