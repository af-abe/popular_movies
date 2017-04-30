package abe.appsfactory.nanodegree.popular_movies.presenter.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IReviewModel;

public class ReviewItemPresenter extends BaseObservable implements IReviewModel, Parcelable {

    private final String mAuthorName;
    private final String mText;


    public ReviewItemPresenter(IReviewModel model) {
        mAuthorName = model.getAuthorName();
        mText = model.getText();
    }

    private ReviewItemPresenter(Parcel in) {
        mAuthorName = in.readString();
        mText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAuthorName);
        dest.writeString(mText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReviewItemPresenter> CREATOR = new Creator<ReviewItemPresenter>() {
        @Override
        public ReviewItemPresenter createFromParcel(Parcel in) {
            return new ReviewItemPresenter(in);
        }

        @Override
        public ReviewItemPresenter[] newArray(int size) {
            return new ReviewItemPresenter[size];
        }
    };

    @Bindable
    @Override
    public String getAuthorName() {
        return mAuthorName;
    }

    @Bindable
    @Override
    public String getText() {
        return mText;
    }
}
