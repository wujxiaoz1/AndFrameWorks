package com.andframe.api.pager.status;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * 内容布局器
 * Created by SCWANG on 2017/8/2.
 */

public interface Layouter<T extends ViewGroup> {
    @NonNull
    T getLayout();
    void setContenView(View content);
}
