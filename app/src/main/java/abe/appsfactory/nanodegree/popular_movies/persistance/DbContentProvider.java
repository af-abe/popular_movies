package abe.appsfactory.nanodegree.popular_movies.persistance;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class DbContentProvider extends ContentProvider {
    private static final String DBNAME = "mydb";

    public static final String TABLE = "movie";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RELEASE = "release";
    public static final String COLUMN_VOTE = "vote";
    public static final String COLUMN_POSTER = "poster";

    private static final String AUTHORITY = "abe.appsfactory.nanodegree.popular_movies.persistance";

    private static final String BASE_PATH = "movies";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    private MovieDataBaseHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new MovieDataBaseHelper(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(TABLE);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase sqlDB = mOpenHelper.getWritableDatabase();

        long id = sqlDB.insert(TABLE, null, values);

        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase sqlDB = mOpenHelper.getWritableDatabase();
        int rowsDeleted = 0;
        if (!TextUtils.isEmpty(selection)) {
            rowsDeleted = sqlDB.delete(
                    TABLE,
                    selection,
                    null);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_OVERVIEW + " TEXT, " +
            COLUMN_RELEASE + " TEXT, " +
            COLUMN_VOTE + " REAL, " +
            COLUMN_POSTER + " TEXT)";

    private static final class MovieDataBaseHelper extends SQLiteOpenHelper {
        MovieDataBaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + "movies");
            onCreate(db);
        }
    }
}
