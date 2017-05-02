package abe.appsfactory.nanodegree.popular_movies.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.databinding.ActivityDetailBinding;
import abe.appsfactory.nanodegree.popular_movies.presenter.DetailPresenter;
import abe.appsfactory.nanodegree.popular_movies.ui.activities.models.MovieIntentModel;
import abe.appsfactory.nanodegree.popular_movies.ui.adapter.GenericGridRecyclerAdapter;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL_MOVIE_ID = "extra.detailModel";
    private DetailPresenter mPresenter;
    private MovieIntentModel mMovieIntentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra(EXTRA_DETAIL_MOVIE_ID)) {
                mMovieIntentModel = getIntent().getExtras().getParcelable(EXTRA_DETAIL_MOVIE_ID);
                mPresenter = new DetailPresenter(this, getSupportLoaderManager(), mMovieIntentModel);
                binding.setPresenter(mPresenter);
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
            }
        } else {
            mMovieIntentModel = savedInstanceState.getParcelable(MovieIntentModel.class.getCanonicalName());
            if (mMovieIntentModel != null) {
                mPresenter = new DetailPresenter(this, mMovieIntentModel);
                binding.setPresenter(mPresenter);
                mPresenter.restoreState(savedInstanceState);
            } else {
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
            }
        }

        setupTrailerGridView(binding.trailerList);
        setupReview(binding.reviewList);
    }

    private void setupTrailerGridView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getTrailerItems(), R.layout.item_trailer));
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.grid_colums_trailer)));
    }

    private void setupReview(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getReviewItems(), R.layout.item_review));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share:
                mPresenter.shareYouTube(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.saveState(outState);
        if (mMovieIntentModel != null) {
            outState.putParcelable(mMovieIntentModel.getClass().getCanonicalName(), mMovieIntentModel);
        }
    }
}
