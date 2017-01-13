package com.nevaryyy.findviewdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nevaryyy.findview.annotation.SetLayout;
import com.nevaryyy.findview.util.InjectUtil;

/**
 * @author nevaryyy
 */

@SetLayout
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initData(Bundle bundle);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtil.inject(this);
        initData(savedInstanceState);
    }
}
