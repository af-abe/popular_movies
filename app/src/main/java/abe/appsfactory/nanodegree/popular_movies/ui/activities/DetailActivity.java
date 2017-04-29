package abe.appsfactory.nanodegree.popular_movies.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.databinding.ActivityDetailBinding;
import abe.appsfactory.nanodegree.popular_movies.presenter.DetailPresenter;
import abe.appsfactory.nanodegree.popular_movies.ui.adapter.GenericGridRecyclerAdapter;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL_MOVIE_ID = "extra.detailModel";
    private DetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_DETAIL_MOVIE_ID)) {
            int modelId = getIntent().getExtras().getInt(EXTRA_DETAIL_MOVIE_ID);
            mPresenter = new DetailPresenter(this, getSupportLoaderManager(), modelId);
            binding.setPresenter(mPresenter);
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
        }

        setupTrailerGridView(binding.trailerList);
        setupReview(binding.reviewList);
    }

    private void setupTrailerGridView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getTrailerItems(), R.layout.item_trailer));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setupReview(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getReviewItems(), R.layout.item_review));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }
}
