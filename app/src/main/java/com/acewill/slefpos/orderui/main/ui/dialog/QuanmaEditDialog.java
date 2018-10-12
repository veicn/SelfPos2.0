package com.acewill.slefpos.orderui.main.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.acewill.slefpos.R;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.listener.OnCancleClickListener;


/**
 * Author：Anch
 * Date：2017/9/8 17:40
 * Desc：
 */
public class QuanmaEditDialog extends BaseDialog implements View.OnClickListener {

	public static QuanmaEditDialog newInstance() {
		QuanmaEditDialog fragment = new QuanmaEditDialog();
		Bundle           bundle   = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_common_quanma, null);
		view.findViewById(R.id.iv_cancer).setOnClickListener(this);
		return view;
	}

	private OnCancleClickListener mCancleClickListener;

	public void setOnMemberLoginDialogCancleListener(OnCancleClickListener listener) {
		mCancleClickListener = listener;
	}

	@Override
	public void onStart() {
		super.onStart();
		Dialog dialog = getDialog();
		if (dialog != null) {
			DisplayMetrics dm = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			dialog.getWindow()
					.setLayout((int) (dm.widthPixels * getSize()), ((int) (dm.heightPixels * 0.53f)));
		}
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
		if (mCancleClickListener != null)
			mCancleClickListener.onCancleClick();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_cancer:
				dismiss();
				break;
		}
	}
}