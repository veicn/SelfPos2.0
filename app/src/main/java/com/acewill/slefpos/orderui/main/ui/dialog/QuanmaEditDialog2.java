package com.acewill.slefpos.orderui.main.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.acewill.slefpos.R;

/**
 * Author：Anch
 * Date：2018/7/25 11:29
 * Desc：
 */
public class QuanmaEditDialog2 extends Dialog implements View.OnClickListener {
	private Context mContext;

	public QuanmaEditDialog2(@NonNull Context context) {
		super(context, R.style.MyDialogStyle);
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_common_quanma);
		findViewById(R.id.iv_cancer).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_cancer:
				dismiss();
				//				if (mListener != null)
				//					mListener.onCancle();
				break;
		}
	}

	private OnClickListener mListener;

	public void setListener(OnClickListener listener) {
		mListener = listener;
	}

	public interface OnClickListener {
		void onCancle();
	}

}
