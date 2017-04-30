package abe.appsfactory.nanodegree.popular_movies.persistance;

import java.util.ArrayList;
import java.util.List;

import abe.appsfactory.nanodegree.popular_movies.logic.models.IMovieDetails;
import abe.appsfactory.nanodegree.popular_movies.persistance.models.DbMovieDetailsModel;
import io.realm.Realm;

public class RealmHelper {

    public static void persistMovie(IMovieDetails model) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(new DbMovieDetailsModel(model));
        realm.commitTransaction();
        realm.close();
    }

    public static boolean hasMovieWithId(int id) {
        Realm realm = Realm.getDefaultInstance();
        DbMovieDetailsModel dbModel = realm.where(DbMovieDetailsModel.class).equalTo("mId", id).findFirst();
        realm.close();

        return dbModel != null;
    }

    public static void removeMovie(IMovieDetails model) {
        Realm realm = Realm.getDefaultInstance();
        DbMovieDetailsModel dbModel = realm.where(DbMovieDetailsModel.class).equalTo("mId", model.getMovieId()).findFirst();
        realm.beginTransaction();
        dbModel.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public static List<? extends IMovieDetails> getAllMovies() {
        Realm realm = Realm.getDefaultInstance();
        List<DbMovieDetailsModel> dbModels = realm.where(DbMovieDetailsModel.class).findAll();
        List<IMovieDetails> result = new ArrayList<>();
        if (dbModels != null) {
            result.addAll(realm.copyFromRealm(dbModels));
        }
        realm.close();
        return result;
    }
}
