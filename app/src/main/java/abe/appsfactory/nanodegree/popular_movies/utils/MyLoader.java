package abe.appsfactory.nanodegree.popular_movies.utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by andrebauml on 29.04.17.
 */
public class MyLoader<D> extends AsyncTaskLoader<D> {
    private final AsyncOperation.OnError mError;
    AsyncOperation.BackgroundTask<D> mTask;

    public MyLoader(Context context, AsyncOperation.BackgroundTask<D> task, AsyncOperation.OnError error) {
        super(context);
        mTask = task;
        mError = error;
    }

    @Override
    public D loadInBackground() {
        try {
            return mTask.doInBackground();
        } catch (Exception e) {
            if (mError != null) {
                mError.onError(e);
            }
        }
        return null;
    }
}