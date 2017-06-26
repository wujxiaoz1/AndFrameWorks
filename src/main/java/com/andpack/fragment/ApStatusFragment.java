package com.andpack.fragment;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.andframe.api.pager.status.RefreshLayouter;
import com.andframe.api.pager.status.StatusLayouter;
import com.andframe.fragment.AfStatusFragment;
import com.andpack.activity.ApFragmentActivity;
import com.andpack.api.ApPager;
import com.andpack.impl.ApStatusHelper;

/**
 * 多状态页面支持
 * Created by SCWANG on 2016/10/21.
 */

public class ApStatusFragment<T> extends AfStatusFragment<T> implements ApPager {

    protected ApStatusHelper mApHelper = new ApStatusHelper(this);

    @Override
    protected void onCreated() {
        mApHelper.onCreate();
        super.onCreated();
    }

    @Override
    public void onDestroy() {
        mApHelper.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        mApHelper.onDestroyView();
        super.onDestroyView();
    }

    @Override
    @CallSuper
    public void onViewCreated()  {
        mApHelper.onViewCreated();
        super.onViewCreated();
    }

    @Override
    public View findContentView() {
        View view = mApHelper.findContentView();
        if (view != null) {
            return view;
        }
        return super.findContentView();
    }

    @NonNull
    @Override
    public RefreshLayouter newRefreshLayouter(Context context) {
        RefreshLayouter layouter = mApHelper.newRefreshLayouter(context);
        if (layouter != null) {
            return layouter;
        }
        return super.newRefreshLayouter(context);
    }

    @NonNull
    @Override
    public StatusLayouter newStatusLayouter(Context context) {
        StatusLayouter layouter = mApHelper.newStatusLayouter(context);
        if (layouter != null) {
            return layouter;
        }
        return super.newStatusLayouter(context);
    }

    @Override
    public T onTaskLoading() throws Exception {
        return null;
    }

    @Override
    public void onTaskLoaded(@NonNull T model) {

    }

    @Override
    public void startFragment(Class<? extends Fragment> clazz, Object... args) {
        ApFragmentActivity.start(this, clazz, args);
    }

    @Override
    public void startFragmentForResult(Class<? extends Fragment> clazz, int request, Object... args) {
        ApFragmentActivity.startResult(this, clazz, request, args);
    }

    @Override
    public void postEvent(Object event) {
        mApHelper.postEvent(event);
    }
}
