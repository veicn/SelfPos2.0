package com.acewill.slefpos.orderui.main.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.listener.OnCancleClickListener;
import com.acewill.slefpos.orderui.main.ui.widget.NumberInputView;


/**
 * Author：Anch
 * Date：2017/12/30 15:13
 * Desc：
 */
public class MemberLoginDialog extends BaseDialog implements View.OnClickListener {
	/**
	 * @param showphoneinput
	 * @return
	 */
	public static MemberLoginDialog newInstance(boolean showphoneinput) {
		MemberLoginDialog fragment = new MemberLoginDialog();
		Bundle            bundle   = new Bundle();
		bundle.putBoolean("showphoneinput", showphoneinput);
		fragment.setArguments(bundle);
		return fragment;
	}

	private LinearLayout    tipsLayout;
	private NumberInputView phoneLayout;
	private TextView        phonelogin;
	private TextView        qrlogin;
	private TextView        nomember;

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_member_login2, null);
		qrlogin = (TextView) view.findViewById(R.id.scan_qrcode_login);
		nomember = (TextView) view.findViewById(R.id.nomember);
		nomember.setOnClickListener(this);
		qrlogin.setSelected(true);
		qrlogin.setOnClickListener(this);
		phonelogin = (TextView) view.findViewById(R.id.phone_login);
		phonelogin.setOnClickListener(this);
		boolean showphoneinput = getArguments().getBoolean("showphoneinput", true);
		phonelogin.setVisibility(showphoneinput ? View.VISIBLE : View.GONE);
		tipsLayout = (LinearLayout) view.findViewById(R.id.memberlogin_tips_layout);
		phoneLayout = (NumberInputView) view
				.findViewById(R.id.memberlogin_phone_layout);
		return view;
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

	private OnCancleClickListener mCancleClickListener;

	public void setOnMemberLoginDialogCancleListener(OnCancleClickListener listener) {
		mCancleClickListener = listener;
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
			case R.id.scan_qrcode_login:
				nomember.setVisibility(View.VISIBLE);
				tipsLayout.setVisibility(View.VISIBLE);
				phoneLayout.setVisibility(View.GONE);
				qrlogin.setSelected(true);
				phonelogin.setSelected(false);
				break;
			case R.id.phone_login:
				nomember.setVisibility(View.GONE);
				phonelogin.setSelected(true);
				qrlogin.setSelected(false);
				phoneLayout.setVisibility(View.VISIBLE);
				tipsLayout.setVisibility(View.GONE);
				break;
			case R.id.nomember:
				AppApplication.getRxManager().post(AppConstant.SKIPMEMBERLOGIN, 1);
				break;
		}
	}

	public static void setImage(Bitmap bitmap) {

	}
}
