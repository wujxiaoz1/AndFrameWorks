package com.andframe.impl.multistatus;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;

import com.andframe.api.multistatus.OnRefreshListener;
import com.andframe.api.multistatus.RefreshLayouter;

import java.util.Date;

/**
 * 可进行刷新操作的布局
 * Created by SCWANG on 2016/10/20.
 */

public class DefaultRefreshLayouter implements RefreshLayouter, SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout mRefreshLayout;
    private OnRefreshListener mOnRefreshListener;
    private View mContentView;

    public DefaultRefreshLayouter(Context context) {
        mRefreshLayout = new SwipeRefreshLayout(context);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,android.R.color.holo_green_light,android.R.color.holo_blue_light,android.R.color.holo_orange_light);
    }

    @Override
    public ViewGroup getLayout() {
        return mRefreshLayout;
    }

    @Override
    public void setContenView(View content) {
        if (mContentView != null) {
            mRefreshLayout.removeView(mContentView);
        }
        mRefreshLayout.addView(mContentView = content);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
    }

    @Override
    public void setLastRefreshTime(Date date) {

    }

    @Override
    public boolean isRefreshing() {
        return mRefreshLayout.isRefreshing();
    }

    public void setRefreshing(boolean refreshing) {
        mRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void onRefresh() {
        if (mOnRefreshListener == null || !mOnRefreshListener.onRefresh()) {
            mRefreshLayout.setRefreshing(false);
        }
    }
}