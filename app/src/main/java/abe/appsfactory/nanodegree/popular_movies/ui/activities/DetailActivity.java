package abe.appsfactory.nanodegree.popular_movies.ui.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.databinding.ActivityDetailBinding;
import abe.appsfactory.nanodegree.popular_movies.network.models.MovieDetailsModel;
import abe.appsfactory.nanodegree.popular_movies.presenter.DetailPresenter;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL_MODEL = "extra.detailModel";
    private DetailPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        MovieDetailsModel model = getIntent().getExtras().getParcelable(EXTRA_DETAIL_MODEL);
        mPresenter = new DetailPresenter(model);
        binding.setPresenter(mPresenter);
    }
}
