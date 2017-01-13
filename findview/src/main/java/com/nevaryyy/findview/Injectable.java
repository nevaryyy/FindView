package com.nevaryyy.findview;

import android.view.View;

/**
 * @author nevaryyy
 */

public interface Injectable {
    void setRootView(int layoutId);
    View getRootView();
}
