package abe.appsfactory.nanodegree.popular_movies.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IReviewModel;

@SuppressWarnings("unused")
class APIReviewModel implements IReviewModel, Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("url")
    private String url;

    private APIReviewModel(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<APIReviewModel> CREATOR = new Creator<APIReviewModel>() {
        @Override
        public APIReviewModel createFromParcel(Parcel in) {
            return new APIReviewModel(in);
        }

        @Override
        public APIReviewModel[] newArray(int size) {
            return new APIReviewModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getAuthorName() {
        return author;
    }

    @Override
    public String getText() {
        return content;
    }
}
