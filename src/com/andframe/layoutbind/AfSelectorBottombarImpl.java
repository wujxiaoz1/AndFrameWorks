package com.andframe.layoutbind;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.andframe.R;
import com.andframe.activity.framework.AfPageable;
import com.andframe.activity.framework.AfViewable;

public class AfSelectorBottombarImpl extends AfSelectorBottombar{

	public static final int ID_EDIT = R.drawable.af_bottom_edit;
	public static final int ID_DELETE = R.drawable.af_bottom_delete;
	public static final int ID_OK = R.drawable.af_icon_ok;

	public static final String DETAIL_EDIT = "编辑";
	public static final String DETAIL_DELETE = "删除";
	public static final String DETAIL_OK = "完成";
	
	public AfSelectorBottombarImpl(AfPageable page) {
		super(page,R.id.af_bottombar_select_layout);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected ImageView getFunctionViewMore(AfViewable view) {
		// TODO Auto-generated method stub
		return view.findViewByID(R.id.af_bottombar_select_more);
	}

	@Override
	protected LinearLayout getFunctionLayout(AfViewable view) {
		// TODO Auto-generated method stub
		return view.findViewByID(R.id.af_bottombar_select_contain);
	}
	
	@Override
	protected int getSelectorDrawableResId() {
		// TODO Auto-generated method stub
		return R.drawable.af_selector_background;
	}
}