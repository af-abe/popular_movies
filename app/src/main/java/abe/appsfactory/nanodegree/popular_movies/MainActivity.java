package abe.appsfactory.nanodegree.popular_movies;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import abe.appsfactory.nanodegree.popular_movies.databinding.ActivityMainBinding;
import abe.appsfactory.nanodegree.popular_movies.presenter.MainPresenter;
import abe.appsfactory.nanodegree.popular_movies.ui.adapter.MovieGridRecyclerAdapter;
import abe.appsfactory.nanodegree.popular_movies.ui.fragments.SettingsDialogFragment;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mPresenter;
    ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MainPresenter();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setPresenter(mPresenter);



        setupMovieGridView(mBinding.movieGrid);

        mPresenter.loadMovies(this, getSupportLoaderManager());
    }

    private void setupMovieGridView(RecyclerView recyclerView){
        recyclerView.setAdapter(new MovieGridRecyclerAdapter(mPresenter.getItems()));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.movieGrid.setAdapter(null);
        mPresenter = null;
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

    public void notifySort(){
        mPresenter.loadMovies(this, getSupportLoaderManager());
    }
}
