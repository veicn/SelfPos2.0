/**
 * ClassName: MyProcessDialog.java
 * created on 2012-3-17
 * Copyrights 2011-2012 qjyong All rights reserved.
 * site: http://blog.csdn.net/qjyong
 * email: qjyong@gmail.com
 */
package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.dialog.BaseDialog;
import com.jaydenxiao.common.commonutils.ToastUitl;

import rokudol.com.pswtext.PswText;


/**
 * 对话框
 */
public class MemberPwdInputDialog extends BaseDialog {


	/**
	 * @param dialogType 1表示密码 0表示验证短信
	 * @param useType    表示
	 * @return
	 */
	public static MemberPwdInputDialog newInstance(int dialogType, int useType) {
		MemberPwdInputDialog fragment = new MemberPwdInputDialog();
		Bundle               bundle   = new Bundle();
		bundle.putInt("dialogType", dialogType);
		bundle.putInt("useType", useType);
		fragment.setArguments(bundle);
		return fragment;
	}

	private OnMemCancleClickListener mOnMemCancleClickListener;

	public void setOnMemCancleClickListener(OnMemCancleClickListener listener) {
		this.mOnMemCancleClickListener = listener;
	}

	public interface OnMemCancleClickListener {
		void onMemberCancleClick(int useType);
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_input_password, null);
		initView(view);
		return view;
	}

	private PswText psw_tv;
	private int     useType;

	private void initView(View view) {
		final int dialogType = getArguments().getInt("dialogType");
		useType = getArguments().getInt("useType");
		TextView     tv_title = (TextView) view.findViewById(R.id.tv_title);
		final Button btn_yes  = (Button) view.findViewById(R.id.btn_yes);
		psw_tv = (PswText) view.findViewById(R.id.psw_tv);
		psw_tv.requestFocus();

		if (dialogType == 1) {
			tv_title.setText("请输入会员交易密码");
		} else {
			tv_title.setText("请输入短信验证码");
		}
		setCancelable(false);

		btn_yes.setText("确定");


		btn_yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				TimeConfigure.resetScreenTime();
				String s        = dialogType == 1 ? "会员交易密码" : "短信验证码";
				String password = psw_tv.getPsw();
				if (TextUtils.isEmpty(password)) {
					ToastUitl.showLong(mcontext, "请输入" + s);
					return;
				}
				if (password.length() < 6) {
					ToastUitl.showLong(mcontext, "请输入六位" + s);
					return;
				}
				mYesClickListener.onYesClick(password, useType);
				dismiss();
			}
		});
		view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				TimeConfigure.resetScreenTime();
				mOnMemCancleClickListener.onMemberCancleClick(useType);
				dismiss();
			}
		});
	}


	public interface OnYesClickListener {
		void onYesClick(String password, int useType);
	}

	public OnYesClickListener mYesClickListener;

	public void setOnYesClickListener(OnYesClickListener listener) {
		mYesClickListener = listener;
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

}
