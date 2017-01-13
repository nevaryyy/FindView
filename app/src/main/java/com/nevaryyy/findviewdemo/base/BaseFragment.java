package com.nevaryyy.findviewdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nevaryyy.findview.Injectable;
import com.nevaryyy.findview.annotation.SetLayout;
import com.nevaryyy.findview.util.InjectUtil;

/**
 * @author nevaryyy
 */

@SetLayout
public abstract class BaseFragment extends Fragment implements Injectable {

    private LayoutInflater inflater;

    private View rootView;

    protected abstract void initData(Bundle bundle);

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void setRootView(int layoutId) {
        rootView = inflater.inflate(layoutId, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        InjectUtil.inject(this);
        initData(savedInstanceState);
        return rootView;
    }
}
