package com.andframe.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.andframe.adapter.AfHeaderFooterAdapter;
import com.andframe.api.adapter.ListItem;
import com.andframe.api.adapter.ListItemAdapter;
import com.andframe.api.multistatus.MoreFooter;
import com.andframe.api.pager.ItemsHelper;
import com.andframe.api.pager.ItemsPager;
import com.andframe.api.pager.StatusHelper;
import com.andframe.api.view.ItemsViewer;
import com.andframe.api.view.ViewQuery;
import com.andframe.impl.helper.AfItemsPagerHelper;
import com.andframe.task.AfHandlerTask;

import java.util.Date;
import java.util.List;

/**
 * 多项数据页面
 * Created by SCWANG on 2016/10/21.
 */

public abstract class AfItemsActivity<T> extends AfStatusActivity<List<T>> implements ItemsPager<T> {

    protected ListItemAdapter<T> mAdapter;
    protected ItemsHelper<T> mItemsHelper = newItemsHelper();

    @NonNull
    @Override
    protected StatusHelper<List<T>> newHelper() {
        return mItemsHelper = newItemsHelper();
    }

    @NonNull
    protected ItemsHelper<T> newItemsHelper() {
        if (mHelper instanceof ItemsHelper) {
            return ((ItemsHelper<T>) mHelper);
        }
        return new AfItemsPagerHelper<>(this);
    }

    //<editor-fold desc="查询转发">
    @Override
    public ViewQuery<? extends ViewQuery> $(Integer id, int... ids) {
        return mItemsHelper.$(id, ids);
    }

    @Override
    public ViewQuery<? extends ViewQuery> $(View... views) {
        return mItemsHelper.$(views);
    }

    @Override
    public ViewQuery<? extends ViewQuery> $(String idvalue, String... idvalues) {
        return mItemsHelper.$(idvalue, idvalues);
    }

    @Override
    public ViewQuery<? extends ViewQuery> $(Class<? extends View> type) {
        return mItemsHelper.$(type);
    }

    @Override
    public ViewQuery<? extends ViewQuery> $(Class<? extends View>[] types) {
        return mItemsHelper.$(types);
    }
    //</editor-fold>

    //<editor-fold desc="初始化">

    //</editor-fold>

    //<editor-fold desc="子类实现">
    /**
     *
     * 获取列表控件
     *
     * @param pager 页面对象
     * @return pager.findListViewById(id)
     */
    @Override
    public ItemsViewer findItemsViewer(ItemsPager<T> pager, View contentView) {
        return mItemsHelper.findItemsViewer(pager, contentView);
    }

    /**
     * 获取列表项布局Item
     * 如果重写 newAdapter 之后，本方法将无效
     *
     * @return 实现 布局接口 ListItem 的Item兑现
     * new LayoutItem implements ListItem<T>(){}
     */
    public abstract ListItem<T> newListItem(int viewType);

    /**
     * 获取指定数据的视图Item类型
     * @param position 数据索引
     */
    @Override
    public int getItemViewType(int position) {
        return mItemsHelper.getItemViewType(position);
    }

    /**
     * 获取数据视图Item的数量
     */
    @Override
    public int getViewTypeCount() {
        return mItemsHelper.getViewTypeCount();
    }

    //</editor-fold>

    //<editor-fold desc="原始事件">
    /**
     * 数据列表点击事件
     *
     * @param parent 列表控件
     * @param view   被点击的视图
     * @param index  被点击的index
     * @param id     被点击的视图ID
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
        mItemsHelper.onItemClick(parent, view, index, id);
    }
    /**
     * 数据列表点击事件
     *
     * @param parent 列表控件
     * @param view   被点击的视图
     * @param index  被点击的index
     * @param id     被点击的视图ID
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long id) {
        return mItemsHelper.onItemLongClick(parent, view, index, id);
    }

    @Override
    public boolean onMore() {
        return mItemsHelper.onMore();
    }

    @Override
    public boolean onRefresh() {
        return mItemsHelper.onRefresh();
    }

    //</editor-fold>

    //<editor-fold desc="页面状态">
    @Override
    public void finishRefresh() {
        mItemsHelper.finishRefresh();
    }

    @Override
    public void finishRefreshFail() {
        mItemsHelper.finishRefreshFail();
    }

    @Override
    public void setLastRefreshTime(Date time) {
        mItemsHelper.setLastRefreshTime(time);
    }

    //</editor-fold>

    //<editor-fold desc="子类重写">

    //<editor-fold desc="缓存功能">
    @Override
    public void initCache() {
        mItemsHelper.initCache();
    }

    @Override
    public void putCache() {
        mItemsHelper.putCache();
    }

    @Override
    public void clearCache() {
        mItemsHelper.clearCache();
    }

    @Override
    public Date getCacheTime() {
        return mItemsHelper.getCacheTime();
    }

    @Override
    public String getCacheKey() {
        return null;
    }

    @Override
    public void putCache(List<T> list) {
        mItemsHelper.putCache(list);
    }

    //</editor-fold>

    /**
     * 创建加载更多的视图
     */
    @Override
    public MoreFooter newMoreFooter() {
        return mItemsHelper.newMoreFooter();
    }

    /**
     * 根据加载的数据判断是否可以加载更多
     * @return false 数据加载完毕，关闭加载更多功能 true 数据还未加载完，开启加载功能功能
     */
    @Override
    public boolean setMoreShow(AfHandlerTask task, List<T> list) {
        return mItemsHelper.setMoreShow(task, list);
    }

    /**
     * onItemClick 事件的 包装 一般情况下子类可以重写这个方法
     *
     * @param model 被点击的数据model
     * @param index 被点击的index
     */
    @Override
    public void onItemClick(T model, int index) {

    }

    /**
     * onItemLongClick 事件的 包装 一般情况下子类可以重写这个方法
     *
     * @param model 被点击的数据model
     * @param index 被点击的index
     */
    @Override
    public boolean onItemLongClick(T model, int index) {
        return false;
    }

    /**
     * 初始化 适配器（内部调用 newAdapter）
     */
    @Override
    public ListItemAdapter<T> initAdapter() {
        return mItemsHelper.initAdapter();
    }


    /**
     * 根据数据ltdata新建一个 适配器 重写这个方法之后getItemLayout方法将失效
     *
     * @param context Context对象
     * @param list  完成加载数据
     * @return 新的适配器
     */
    @Override
    public ListItemAdapter<T> newAdapter(Context context, List<T> list) {
        return mAdapter = mItemsHelper.newAdapter(context, list);
    }

    /**
     * 为列表添加 Header 和 Footer
     * （在bindAdapter之前执行）
     */
    @Override
    public void bindListHeaderAndFooter(AfHeaderFooterAdapter<T> adapter) {
        mItemsHelper.bindListHeaderAndFooter(adapter);
    }

    /**
     * 绑定适配器
     * @param listView 列表
     * @param adapter 适配器
     */
    @Override
    public void bindAdapter(ItemsViewer listView, ListAdapter adapter) {
        mItemsHelper.bindAdapter(listView, adapter);
    }

    //<editor-fold desc="任务相关">

    //<editor-fold desc="缓存数据">
    @Override
    public void onTaskPutCache(List<T> list) {
        mItemsHelper.onTaskPutCache(list);
    }

    @Override
    public void onTaskPushCache(List<T> list) {
        mItemsHelper.onTaskPushCache(list);
    }
    //</editor-fold>

    //<editor-fold desc="任务加载">
    @Override
    public List<T> onTaskLoadCache(boolean isCheckExpired) {
        return mItemsHelper.onTaskLoadCache(isCheckExpired);
    }

//    @Override
//    public List<T> onTaskLoadList(Page page) throws Exception {
//        return mItemsHelper.onTaskLoadList(page);
//    }
    //</editor-fold>

    //<editor-fold desc="任务完成">
    @Override
    public void onTaskLoadedCache(AfHandlerTask task, List<T> list) {
        mItemsHelper.onTaskLoadedCache(task, list);
    }

    @Override
    public void onTaskLoadedRefresh(AfHandlerTask task, List<T> list) {
        mItemsHelper.onTaskLoadedRefresh(task, list);
    }

    @Override
    public void onTaskLoadedMore(AfHandlerTask task, List<T> list) {
        mItemsHelper.onTaskLoadedMore(task, list);
    }
    //</editor-fold>

    //<editor-fold desc="父类任务">
    @Override
    public final void onTaskFailed(AfHandlerTask task) {
        super.onTaskFailed(task);
    }

    @Override
    public final void onTaskFinish(List<T> data) {
        super.onTaskFinish(data);
    }

    @Override
    public final List<T> onTaskLoading() throws Exception {
        return null;//super.onTaskLoading();
    }

    @Override
    public final void onTaskLoaded(List<T> data) {
    }

    //</editor-fold>

    //</editor-fold>

    //</editor-fold>

}