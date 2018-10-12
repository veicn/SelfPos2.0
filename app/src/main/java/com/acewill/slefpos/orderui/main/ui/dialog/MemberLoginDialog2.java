package com.acewill.slefpos.orderui.main.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.ui.widget.NumberInputView;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.jaydenxiao.common.commonutils.SPUtils;

/**
 * Author：Anch
 * Date：2018/6/11 18:17
 * Desc：
 */
public class MemberLoginDialog2 extends Dialog implements View.OnClickListener {

	private Context         mContext;
	private LinearLayout    tipsLayout;
	private NumberInputView phoneLayout;
	private TextView        phonelogin;
	private TextView        qrlogin;
	private TextView        nomember;
	private TextView        nomember2;
	private ImageView       membership_iv;
	private LinearLayout    tab_view;

	public MemberLoginDialog2(Context context) {
		super(context, R.style.MyDialogStyle);
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_member_login2);
		//按空白处不能取消动画
		setCanceledOnTouchOutside(true);
		qrlogin = (TextView) findViewById(R.id.scan_qrcode_login);
		nomember = (TextView) findViewById(R.id.nomember);
		nomember2 = (TextView) findViewById(R.id.nomember2);
		nomember.setOnClickListener(this);
		nomember2.setOnClickListener(this);
		qrlogin.setSelected(true);
		qrlogin.setOnClickListener(this);
		phonelogin = (TextView) findViewById(R.id.phone_login);
		phonelogin.setOnClickListener(this);

		tipsLayout = (LinearLayout) findViewById(R.id.memberlogin_tips_layout);
		phoneLayout = (NumberInputView) findViewById(R.id.memberlogin_phone_layout);
		membership_iv = (ImageView) findViewById(R.id.membership_iv);
		tab_view = (LinearLayout) findViewById(R.id.tab_view);

		initLayout();
	}

	private void initLayout() {
		boolean showphoneinput = true;
		if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
			TextView  tips1    = (TextView) findViewById(R.id.tips1);
			TextView  tips2    = (TextView) findViewById(R.id.tips2);
			TextView  tips3    = (TextView) findViewById(R.id.tips3);
			TextView  tips4    = (TextView) findViewById(R.id.tips4);
			ImageView iv_tips1 = (ImageView) findViewById(R.id.iv_tips1);
			ImageView iv_tips2 = (ImageView) findViewById(R.id.iv_tips2);
			iv_tips1.setVisibility(View.VISIBLE);
			iv_tips2.setVisibility(View.VISIBLE);
			membership_iv
					.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.first_step));
			iv_tips1.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.second_step));
			iv_tips2.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.third_step));
			tips1.setText(mContext.getResources().getString(R.string.memberlogin_tip1));
			tips2.setText(mContext.getResources().getString(R.string.memberlogin_tip2));
			tips3.setText(mContext.getResources().getString(R.string.memberlogin_tip3));
			tips4.setText(mContext.getResources().getString(R.string.memberlogin_tip4));
			showphoneinput = SmarantDataProvider.getSelfposConfigurationdata().getContent()
					.getIsScanAndInput() == 1 ? true : false;
		} else {
			showphoneinput = SPUtils
					.getSharedIntData(mContext, "validatememberno") == 0 ? true : false;
		}
		tab_view.setVisibility(showphoneinput ? View.VISIBLE : View.GONE);
	}


	@Override
	public void onClick(View v) {
		TimeConfigure.resetScreenTime();
		switch (v.getId()) {
			case R.id.scan_qrcode_login:
				nomember.setVisibility(View.VISIBLE);
				tipsLayout.setVisibility(View.VISIBLE);
				phoneLayout.setVisibility(View.GONE);
				nomember2.setVisibility(View.GONE);
				qrlogin.setSelected(true);
				phonelogin.setSelected(false);
				break;
			case R.id.phone_login:
				nomember.setVisibility(View.GONE);
				phonelogin.setSelected(true);
				qrlogin.setSelected(false);
				phoneLayout.setVisibility(View.VISIBLE);
				nomember2.setVisibility(View.VISIBLE);
				tipsLayout.setVisibility(View.GONE);
				break;
			case R.id.nomember:
			case R.id.nomember2:
				AppApplication.getRxManager().post(AppConstant.SKIPMEMBERLOGIN, 1);
				break;
		}
	}

	public void setImage(Bitmap bitmap) {
		if (bitmap != null)
			membership_iv.setImageBitmap(bitmap);
	}

	public void setImage(Drawable drawable) {
		if (drawable != null)
			membership_iv.setImageDrawable(drawable);
	}


}
