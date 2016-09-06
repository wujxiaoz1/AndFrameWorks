package com.andframe.widget.multichoice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.andframe.module.AfSelectorBottombar;
import com.andframe.module.AfSelectorTitlebar;
import com.andframe.widget.AfRefreshListView;

@SuppressWarnings("unused")
public class AfMultiChoiceListView extends AfRefreshListView<ListView> implements
		OnItemLongClickListener, OnItemClickListener {
	protected OnItemClickListener mItemClickListener = null;
	protected OnItemLongClickListener mItemLongClickListener = null;
	protected AfMultiChoiceAdapter<?> mAdapter = null;
	protected AfSelectorTitlebar mSelectorTitlebar = null;
	protected AfSelectorBottombar mSelectorBottombar = null;

	public AfMultiChoiceListView(Context context) {
		super(context);
		super.setOnItemClickListener(this);
		super.setOnItemLongClickListener(this);
	}

	public AfMultiChoiceListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setOnItemClickListener(this);
		super.setOnItemLongClickListener(this);
	}

	public AfMultiChoiceListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		super.setOnItemClickListener(this);
		super.setOnItemLongClickListener(this);
	}
	
	@Override
	protected ListView onCreateListView(Context context, AttributeSet attrs) {
		return new ListView(context);
	}

	@Override
	public void setOnItemClickListener(OnItemClickListener listener) {
		mItemClickListener = listener;
	}

	@Override
	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		mItemLongClickListener = listener;
	}

	/**
	 * Deprecated. Use {@link #setAdapter(AfMultiChoiceAdapter adapter)} from
	 * now on.
	 * @deprecated
	 */
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
		if(adapter instanceof AfMultiChoiceAdapter){
			mAdapter = (AfMultiChoiceAdapter<?>) adapter;
			if(mSelectorTitlebar != null){
				mSelectorTitlebar.setAdapter(mAdapter);
			}
			if(mSelectorBottombar != null){
				mSelectorBottombar.setAdapter(mAdapter);
			}
		}
	}

	public void setAdapter(AfMultiChoiceAdapter<?> adapter) {
		super.setAdapter(adapter);
		mAdapter = adapter;
		if(mSelectorTitlebar != null){
			mSelectorTitlebar.setAdapter(adapter);
		}
		if(mSelectorBottombar != null){
			mSelectorBottombar.setAdapter(mAdapter);
		}
	}
	
	public void setSelector(AfSelectorTitlebar selector) {
		this.mSelectorTitlebar = selector;
	}

	public void setSelector(AfSelectorBottombar selector) {
		this.mSelectorBottombar = selector;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adview, View view, int index, long id) {
		if (mAdapter != null && mAdapter.isMultiChoiceMode()) {
			mAdapter.onItemClick(getDataIndex(index));
		} else if (mItemClickListener != null) {
			mItemClickListener.onItemClick(adview, view, index, id);
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adview, View view, int index,
			long id) {
		if (mAdapter != null && !mAdapter.isMultiChoiceMode()) {
			mAdapter.beginMultiChoice(getDataIndex(index));
			return true;
		} else if (mItemLongClickListener != null) {
			return mItemLongClickListener.onItemLongClick(adview, view, index,id);
		}
		return false;
	}

	public boolean isMultiChoiceMode() {
		return mAdapter != null && mAdapter.isMultiChoiceMode();
	}

	public boolean beginMultiChoice() {
		return mAdapter != null && mAdapter.beginMultiChoice();
	}

}
