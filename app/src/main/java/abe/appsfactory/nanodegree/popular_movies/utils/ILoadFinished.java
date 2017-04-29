package abe.appsfactory.nanodegree.popular_movies.utils;

import android.support.v4.content.Loader;

@FunctionalInterface
public interface ILoadFinished<T> {
    void loadFinished(Loader<T> loader, T data);
}
