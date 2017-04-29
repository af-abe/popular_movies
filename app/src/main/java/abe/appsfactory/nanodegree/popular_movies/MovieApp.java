package abe.appsfactory.nanodegree.popular_movies;

import android.app.Application;

import io.realm.Realm;

public class MovieApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
