package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUser;
import com.acewill.slefpos.dialog.BaseDialog;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Author：Anch
 * Date：2017/9/8 17:40
 * Desc：
 */
public class UserLoginDialog extends BaseDialog {

	/**
	 * @param user
	 * @return
	 */
	public static UserLoginDialog newInstance(FePosUser user) {
		UserLoginDialog fragment = new UserLoginDialog();
		Bundle          bundle   = new Bundle();
		bundle.putSerializable("user", user);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_login, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		final EditText et_user = (EditText) view.findViewById(R.id.et_user);
		final EditText et_pwd  = (EditText) view.findViewById(R.id.et_pwd);
		view.findViewById(R.id.btn_yes)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						String user = et_user.getText()
								.toString()
								.trim();
						String pwd = et_pwd.getText()
								.toString()
								.trim();
						if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
							return;
						}
						ToastUitl.showLong(mcontext, "pwd>" + pwd);
						dismiss();
						mYesClickListener.onYesClick(user, pwd);
					}
				});
		view.findViewById(R.id.iv_cancer)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

	//	public interface OnCancleClickListener {
	//		void onCancle();
	//	}
	//
	//	private OnCancleClickListener listener;
	//
	//	public void setOnCancleClickListener(OnCancleClickListener listener) {
	//		this.listener = listener;
	//	}

	public interface OnYesClickListener {
		void onYesClick(String et_user, String et_pwd);
	}

	private OnYesClickListener mYesClickListener;

	public void setOnYesClickListener(OnYesClickListener listener) {
		this.mYesClickListener = listener;
	}
}