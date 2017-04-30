package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import abe.appsfactory.nanodegree.popular_movies.logic.SortLogic;

public class SettingsPresenter extends BaseObservable {

    private ISettingsEvents mEvents;
    public ObservableBoolean mPopular;
    public ObservableBoolean mRated;
    public ObservableBoolean mFavorites;

    public SettingsPresenter(Context context, ISettingsEvents events) {
        mEvents = events;
        switch (SortLogic.getInstance(context).getSort()) {
            case SortLogic.SORT_FAVORITES:
                mPopular = new ObservableBoolean(false);
                mRated = new ObservableBoolean(false);
                mFavorites = new ObservableBoolean(true);
                break;
            case SortLogic.SORT_POPULAR:
                mPopular = new ObservableBoolean(true);
                mRated = new ObservableBoolean(false);
                mFavorites = new ObservableBoolean(false);
                break;
            case SortLogic.SORT_RATED:
                mPopular = new ObservableBoolean(false);
                mRated = new ObservableBoolean(true);
                mFavorites = new ObservableBoolean(false);
                break;
        }
    }

    public void onAccept(Context context) {
        if(mPopular.get()){
            SortLogic.getInstance(context).setSort(context, SortLogic.SORT_POPULAR);
        } else if(mRated.get()) {
            SortLogic.getInstance(context).setSort(context, SortLogic.SORT_RATED);
        } else {
            SortLogic.getInstance(context).setSort(context, SortLogic.SORT_FAVORITES);
        }

        if (mEvents != null) {
            mEvents.onAccept();
        }
    }

    public void onDismiss() {
        if (mEvents != null) {
            mEvents.onDismiss();
        }
    }

    public void destroy() {
        mEvents = null;
    }

    public interface ISettingsEvents {
        void onAccept();

        void onDismiss();
    }
}
