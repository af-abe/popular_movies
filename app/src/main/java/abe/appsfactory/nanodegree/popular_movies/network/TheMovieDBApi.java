package abe.appsfactory.nanodegree.popular_movies.network;

import android.accounts.NetworkErrorException;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import abe.appsfactory.nanodegree.popular_movies.BuildConfig;
import abe.appsfactory.nanodegree.popular_movies.network.models.APIReviewResult;
import abe.appsfactory.nanodegree.popular_movies.network.models.APITrailerResults;
import abe.appsfactory.nanodegree.popular_movies.network.models.APIPopularMoviesResponseModel;

/**
 * Helper class with all API requests in a synchronous form to use them in Background later
 */

public class TheMovieDBApi {
    public static APIPopularMoviesResponseModel getPopularMovies(boolean popular) throws Exception {
        String enpPoint = popular ? "popular" : "top_rated";
        URL url = new URL("http://api.themoviedb.org/3/movie/" + enpPoint + "?api_key=" + BuildConfig.API_KEY);
        HttpURLConnection connection = getHttpURLConnection(url);
        return readConnection(APIPopularMoviesResponseModel.class, connection);
    }

    public static APITrailerResults getTrailer(int movieId) throws Exception {
        URL url = new URL("http://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=" + BuildConfig.API_KEY);
        HttpURLConnection connection = getHttpURLConnection(url);
        return readConnection(APITrailerResults.class, connection);
    }

    public static APIReviewResult getReviews(int movieId) throws Exception {
        URL url = new URL("http://api.themoviedb.org/3/movie/" + movieId + "/reviews?api_key=" + BuildConfig.API_KEY);
        HttpURLConnection connection = getHttpURLConnection(url);
        return readConnection(APIReviewResult.class, connection);
    }

    private static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();
        return connection;
    }

    private static <T> T readConnection(Class<T> type, HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        if (responseCode >= 200 && responseCode < 300) {
            Gson gson = new Gson();
            InputStream is = connection.getInputStream();
            Reader reader = new InputStreamReader(is);

            return gson.fromJson(reader, type);
        } else {
            throw new NetworkErrorException("Response not OK");
        }
    }
}
