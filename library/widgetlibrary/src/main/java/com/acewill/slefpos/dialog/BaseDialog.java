package com.acewill.slefpos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.jaydenxiao.common.baserx.RxManager;

/**
 * Author：Anch
 * Date：2017/11/21 11:37
 * Desc：
 */
public abstract class BaseDialog extends DialogFragment {

	protected Context   mcontext;
	public    RxManager mRxManager;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mcontext = context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog dialog = getDialog();
		mRxManager = new RxManager();
		if (dialog != null) {
			Window window = getDialog().getWindow();
			getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
			//若无该段代码则用系统的样式而不是自己定义的背景样式
			window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			WindowManager.LayoutParams lp = window.getAttributes();
			window.setGravity(Gravity.CENTER);
			window.setAttributes(lp);
		}
		//XXX初始化view的各控件
		return getView();
	}
	//	@Override
	//	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	//		super.onViewCreated(view, savedInstanceState);
	//		createView(view);
	//	}

	public void createView(View view) {
	}

	;

	public abstract View getView();

	@Override
	public void onStart() {
		super.onStart();
		Dialog dialog = getDialog();
		if (dialog != null) {
			DisplayMetrics dm = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			dialog.getWindow()
					.setLayout((int) (dm.widthPixels * getSize()), ViewGroup.LayoutParams.WRAP_CONTENT);
		}
	}

	public abstract float getSize();


}
