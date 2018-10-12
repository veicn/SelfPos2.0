package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.paylibrary.tencent.common.Util;
import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUser;
import com.acewill.slefpos.dialog.BaseDialog;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Author：Anch
 * Date：2017/9/8 17:40
 * Desc：
 */
public class SyncUserLoginDialog extends BaseDialog {

	/**
	 * @param user
	 * @param fromWho
	 * @return
	 */
	public static SyncUserLoginDialog newInstance(FePosUser user, String fromWho) {
		SyncUserLoginDialog fragment = new SyncUserLoginDialog();
		Bundle              bundle   = new Bundle();
		bundle.putSerializable("user", user);
		bundle.putString("fromWho", fromWho);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_sync_user_login, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		final FePosUser user           = (FePosUser) getArguments().getSerializable("user");
		final String    fromWho        = (String) getArguments().getSerializable("fromWho");
		final TextView  sync_user_name = (TextView) view.findViewById(R.id.sync_user_name);
		final ImageView sync_user_iv = (ImageView) view.findViewById(R.id.sync_user_iv);
		sync_user_name.setText(user.getName());
		if ("M".equals(user.getSex())){
			//男性
			sync_user_iv.setImageDrawable(getResources().getDrawable(R.mipmap.user_man));
		}else{
			sync_user_iv.setImageDrawable(getResources().getDrawable(R.mipmap.user_women));
		}
		final EditText et_pwd = (EditText) view.findViewById(R.id.et_pwd);
		view.findViewById(R.id.btn_yes)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						TimeConfigure.resetScreenTime();
						String pwd = et_pwd.getText()
								.toString()
								.trim();
						if (TextUtils.isEmpty(pwd)) {
							return;
						}

						if (Util.validatePassword(user.getPwd(), pwd)) {
							if (fromWho.equals("eatmethod")) {
								BaseApplication.getInstance().exit();
							} else {
								mRxManager.post(AppConstant.SYNCUSERLOGINSUCCESS, 1);
							}
							dismiss();
						} else {
							et_pwd.setText("");
							ToastUitl.showLong(mcontext, "密码不正确,请重试!");
						}
					}
				});
		view.findViewById(R.id.iv_cancer)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						TimeConfigure.resetScreenTime();
						mRxManager.post(AppConstant.SYNCUSERLOGINCANCLE, 1);
						dismiss();
					}
				});
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

}