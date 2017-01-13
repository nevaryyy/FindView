package com.nevaryyy.findviewdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nevaryyy.findview.annotation.FindView;
import com.nevaryyy.findviewdemo.base.BaseFragment;

/**
 * @author nevaryyy
 */

public class MainFragment extends BaseFragment {

    @FindView
    private TextView numTextView;
    @FindView
    private Button clickButton;

    private int num = 0;

    @Override
    protected void initData(Bundle bundle) {
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num ++;
                numTextView.setText(String.valueOf(num));
            }
        });
    }
}
