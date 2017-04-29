package abe.appsfactory.nanodegree.popular_movies.network;

import android.accounts.NetworkErrorException;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import abe.appsfactory.nanodegree.popular_movies.BuildConfig;
import abe.appsfactory.nanodegree.popular_movies.network.models.PopularMoviesResponseModel;

/**
 * Helper class with all API requests in a synchronous form to use them in Background later
 */

public class TheMovieDBApi {

    @SuppressWarnings("TryWithIdenticalCatches")
    public static PopularMoviesResponseModel getPopularMovies(boolean popular) throws Exception {
        String enpPoint = popular ? "popular" : "top_rated";
        URL url = new URL("http://api.themoviedb.org/3/movie/" + enpPoint + "?api_key=" + BuildConfig.API_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode >= 200 && responseCode < 300) {
            Gson gson = new Gson();
            InputStream is = connection.getInputStream();
            Reader reader = new InputStreamReader(is);

            return gson.fromJson(reader, PopularMoviesResponseModel.class);
        } else {
            throw new NetworkErrorException("Response not OK");
        }
    }

}
