package abe.appsfactory.nanodegree.popular_movies.network;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import abe.appsfactory.nanodegree.popular_movies.network.models.PopularMoviesResponseModel;

/**
 * Helper class with all API requests in a synchronous form to use them in Background later
 */

public class TheMovieDBApi {

    public static PopularMoviesResponseModel getPopularMovies(){
        try {
            URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=0c9cff05f89c6179d1e5dd31609dfa8d");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode >= 200 && responseCode < 300){
                Gson gson = new Gson();
                InputStream is = connection.getInputStream();
                Reader reader = new InputStreamReader(is);

                return gson.fromJson(reader, PopularMoviesResponseModel.class);
            } else {
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
