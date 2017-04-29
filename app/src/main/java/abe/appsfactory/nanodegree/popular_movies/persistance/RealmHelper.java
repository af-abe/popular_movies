package abe.appsfactory.nanodegree.popular_movies.persistance;

import java.util.ArrayList;
import java.util.List;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import abe.appsfactory.nanodegree.popular_movies.persistance.models.DbMovieDetailsModel;
import io.realm.Realm;

public class RealmHelper {

    public static void perstistMovies(List<? extends IMovieDetails> list) {
        Realm realm = Realm.getDefaultInstance();
        final List<DbMovieDetailsModel> insertList = new ArrayList<>();
        for(IMovieDetails details : list){
            insertList.add(new DbMovieDetailsModel(details));
        }
        realm.executeTransactionAsync(bgRealm -> bgRealm.copyToRealm(insertList));
        realm.close();
    }

    public static IMovieDetails getMovieById(int id){
        Realm realm = Realm.getDefaultInstance();
        DbMovieDetailsModel dbModel = realm.where(DbMovieDetailsModel.class).equalTo("mId", id).findFirst();
        IMovieDetails result = null;
        if(dbModel != null){
            result = realm.copyFromRealm(dbModel);
        }
        return result;
    }
}
