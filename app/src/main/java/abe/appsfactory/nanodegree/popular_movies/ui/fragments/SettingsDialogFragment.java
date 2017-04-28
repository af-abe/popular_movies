package abe.appsfactory.nanodegree.popular_movies.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abe.appsfactory.nanodegree.popular_movies.MainActivity;
import abe.appsfactory.nanodegree.popular_movies.R;
import abe.appsfactory.nanodegree.popular_movies.databinding.DialogSettingsBinding;
import abe.appsfactory.nanodegree.popular_movies.presenter.SettingsPresenter;

public class SettingsDialogFragment extends DialogFragment implements SettingsPresenter.ISettingsEvents {
    private SettingsPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SettingsPresenter(getContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_settings, container, false);
        DialogSettingsBinding binding = DataBindingUtil.bind(view);
        binding.setPresenter(mPresenter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void onAccept() {
        dismiss();

        Activity activity = getActivity();
        if(activity instanceof MainActivity){
            ((MainActivity) activity).notifySort();
        }
    }

    @Override
    public void onDismiss() {
        dismiss();
    }
}
