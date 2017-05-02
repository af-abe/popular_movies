package abe.appsfactory.nanodegree.popular_movies.persistance;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import abe.appsfactory.nanodegree.popular_movies.persistance.models.DbMovieDetailsModel;

public class DaoHelper {
    public static void persistMovie(Context context, IMovieDetails model) {

        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();

        values.put(DbContentProvider.COLUMN_ID, model.getMovieId());
        values.put(DbContentProvider.COLUMN_TITLE, model.getTitle());
        values.put(DbContentProvider.COLUMN_OVERVIEW, model.getOverview());
        values.put(DbContentProvider.COLUMN_RELEASE, model.getReleaseDate());
        values.put(DbContentProvider.COLUMN_VOTE, model.getVoteAverage());
        values.put(DbContentProvider.COLUMN_POSTER, model.getPosterUrl());

        resolver.insert(DbContentProvider.CONTENT_URI, values);
    }

    public static List<? extends IMovieDetails> getAllMovies(Context context) {
        ContentResolver resolver = context.getContentResolver();
        String[] projection = {
                DbContentProvider.COLUMN_ID,
                DbContentProvider.COLUMN_TITLE,
                DbContentProvider.COLUMN_OVERVIEW,
                DbContentProvider.COLUMN_RELEASE,
                DbContentProvider.COLUMN_VOTE,
                DbContentProvider.COLUMN_POSTER,
        };

        List<IMovieDetails> result = new ArrayList<>();
        Cursor cursor = resolver.query(DbContentProvider.CONTENT_URI, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DbContentProvider.COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DbContentProvider.COLUMN_TITLE));
                String overview = cursor.getString(cursor.getColumnIndexOrThrow(DbContentProvider.COLUMN_OVERVIEW));
                String release = cursor.getString(cursor.getColumnIndexOrThrow(DbContentProvider.COLUMN_RELEASE));
                float vote = cursor.getFloat(cursor.getColumnIndexOrThrow(DbContentProvider.COLUMN_VOTE));
                String poster = cursor.getString(cursor.getColumnIndexOrThrow(DbContentProvider.COLUMN_POSTER));

                result.add(new DbMovieDetailsModel((int) id, poster, overview, release, title, vote));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return result;
    }

    public static void removeMovie(Context context, IMovieDetails model) {
        ContentResolver resolver = context.getContentResolver();
        resolver.delete(DbContentProvider.CONTENT_URI, "_id=" + model.getMovieId(), null);
    }

    public static boolean hasMovieWithId(Context context, int id) {
        ContentResolver resolver = context.getContentResolver();
        String[] projection = {
                DbContentProvider.COLUMN_ID,
                DbContentProvider.COLUMN_TITLE,
                DbContentProvider.COLUMN_OVERVIEW,
                DbContentProvider.COLUMN_RELEASE,
                DbContentProvider.COLUMN_VOTE,
                DbContentProvider.COLUMN_POSTER,
        };

        Cursor cursor = resolver.query(DbContentProvider.CONTENT_URI, projection, "_id=" + id, null, null);
        boolean result = false;
        if (cursor != null) {
            result = cursor.getCount() == 1;
            cursor.close();
        }

        return result;
    }
}
