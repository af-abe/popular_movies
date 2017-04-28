package abe.appsfactory.nanodegree.popular_movies.presenter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import abe.appsfactory.nanodegree.popular_movies.logic.SortLogic;

public class SettingsPresenter extends BaseObservable {

    private ISettingsEvents mEvents;
    public ObservableBoolean mPopular;
    public ObservableBoolean mRated;

    public SettingsPresenter(Context context, ISettingsEvents events) {
        mEvents = events;
        if (SortLogic.getInstance(context).getSort() == SortLogic.SORT_POPULAR) {
            mPopular = new ObservableBoolean(true);
            mRated = new ObservableBoolean(false);
        } else {
            mPopular = new ObservableBoolean(false);
            mRated = new ObservableBoolean(true);
        }
    }

    public void onAccept(Context context) {
        if(mPopular.get()){
            SortLogic.getInstance(context).setSort(context, SortLogic.SORT_POPULAR);
        } else {
            SortLogic.getInstance(context).setSort(context, SortLogic.SORT_RATED);
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
