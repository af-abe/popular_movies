package abe.appsfactory.nanodegree.popular_movies.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.databinding.ActivityMainBinding;
import abe.appsfactory.nanodegree.popular_movies.logic.SortLogic;
import abe.appsfactory.nanodegree.popular_movies.presenter.MainPresenter;
import abe.appsfactory.nanodegree.popular_movies.ui.adapter.GenericGridRecyclerAdapter;
import abe.appsfactory.nanodegree.popular_movies.ui.fragments.SettingsDialogFragment;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mPresenter;
    ActivityMainBinding mBinding;

    public static boolean triggerReload = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MainPresenter();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setPresenter(mPresenter);


        setupMovieGridView(mBinding.movieGrid);

        if (savedInstanceState == null) {
            mPresenter.loadMovies(this, getSupportLoaderManager());
        } else {
            mPresenter.restoreState(savedInstanceState);
        }
    }

    private void setupMovieGridView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new GenericGridRecyclerAdapter<>(mPresenter.getItems(), R.layout.item_movie_grid));
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.grid_colums)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(triggerReload && SortLogic.getInstance(this).getSort() == SortLogic.SORT_FAVORITES){
            mPresenter.loadMovies(this, getSupportLoaderManager());
            triggerReload = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.movieGrid.setAdapter(null);
        mBinding = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettings() {
        new SettingsDialogFragment().show(getSupportFragmentManager(), null);
    }

    public void notifySort() {
        mPresenter.loadMovies(this, getSupportLoaderManager());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.saveState(outState);
    }
}
