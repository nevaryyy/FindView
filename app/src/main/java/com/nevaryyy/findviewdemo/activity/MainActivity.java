package com.nevaryyy.findviewdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nevaryyy.findview.annotation.FindView;
import com.nevaryyy.findviewdemo.base.BaseActivity;

/**
 * @author nevaryyy
 */
public class MainActivity extends BaseActivity {

    @FindView
    private TextView textView;
    @FindView
    private EditText editText;
    @FindView
    private Button confirmButton;
    @FindView
    private Fragment mainfragment;

    @Override
    protected void initData(Bundle bundle) {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                if (text.length() > 0) {
                    textView.setText(text);
                }
            }
        });
    }
}
