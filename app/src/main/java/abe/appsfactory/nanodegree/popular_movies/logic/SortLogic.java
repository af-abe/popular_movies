package abe.appsfactory.nanodegree.popular_movies.logic;

import android.content.Context;

public class SortLogic {
    public static final int SORT_POPULAR = 0;
    public static final int SORT_RATED = 1;

    private static final String SETTINGS_SORT = "settings.sort";

    private static SortLogic ourInstance;

    public static SortLogic getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SortLogic(context);
        }
        return ourInstance;
    }

    private int mSort;

    private SortLogic(Context context) {
        mSort = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE)
                .getInt(SETTINGS_SORT, SORT_POPULAR);
    }

    public int getSort() {
        return mSort;
    }

    public void setSort(Context context, int sort) {
        mSort = sort;
        context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE)
                .edit()
                .putInt(SETTINGS_SORT, mSort)
                .apply();
    }

}
