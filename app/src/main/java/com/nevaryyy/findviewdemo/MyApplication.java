package com.nevaryyy.findviewdemo;

import android.app.Application;

import com.nevaryyy.findview.config.ViewNamingRuleInit;

/**
 * @author nevaryyy
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ViewNamingRuleInit.getInstance().loadViewNamingRule(this);
    }
}
