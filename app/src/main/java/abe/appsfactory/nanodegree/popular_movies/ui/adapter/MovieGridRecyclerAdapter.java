package abe.appsfactory.nanodegree.popular_movies.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abe.appsfactory.nanodegree.popular_movies.BR;
import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.presenter.model.MoviePosterModel;
import abe.appsfactory.nanodegree.popular_movies.utils.OnObservableListChangedCallback;

public class MovieGridRecyclerAdapter extends RecyclerView.Adapter<MovieGridRecyclerAdapter.MovieGridViewHolder>{
    private final ObservableArrayList<MoviePosterModel> mItems;
    private OnObservableListChangedCallback mChangeObserver;

    public MovieGridRecyclerAdapter(ObservableArrayList<MoviePosterModel> items) {
        mItems = items;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mItems != null) {
            mChangeObserver = new OnObservableListChangedCallback(this);
            mItems.addOnListChangedCallback(mChangeObserver);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mItems != null) {
            mItems.removeOnListChangedCallback(mChangeObserver);
        }
        mChangeObserver = null;
    }

    @Override
    public MovieGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieGridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieGridViewHolder holder, int position) {
        MoviePosterModel item = mItems.get(position);
        holder.binding.setVariable(BR.item, item);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class MovieGridViewHolder extends RecyclerView.ViewHolder {
        int itemId;
        ViewDataBinding binding;

        MovieGridViewHolder(View itemView) {
            super(itemView);
            this.itemId = BR.item;
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
