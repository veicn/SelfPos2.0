package com.acewill.slefpos.orderui.main.ui.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.listener.OnCancleClickListener;
import com.acewill.slefpos.utils.imageutils.QRImageUitl;

/**
 * Author：Anch
 * Date：2017/12/15 17:42
 * Desc：
 */
public class PayDialog extends BaseDialog implements View.OnClickListener {

	/**
	 * @return
	 */
	public static PayDialog newInstance(String url, String cost, int payMethod) {
		PayDialog fragment = new PayDialog();
		Bundle    bundle   = new Bundle();
		bundle.putString("url", url);
		bundle.putString("cost", cost);
		bundle.putInt("payMethod", payMethod);
		fragment.setArguments(bundle);
		return fragment;
	}

	TextView timeTV;
	TextView tipsTV;

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_pay_qr, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		ImageView iv_qr = (ImageView) view.findViewById(R.id.iv_qr);
		iv_qr.setImageBitmap(QRImageUitl.createQRImage(getArguments().getString("url")));//生成支付的二维码
		TextView               tv_price  = (TextView) view.findViewById(R.id.tv_price);
		String                 price     = "支付金额 ￥" + getArguments().getString("cost");
		int                    payMethod = getArguments().getInt("payMethod");
		SpannableStringBuilder style     = new SpannableStringBuilder(price);
		style.setSpan(new ForegroundColorSpan(Color.RED), "支付金额 ".length(), price.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv_price.setText(style);

		TextView tv_title    = (TextView) view.findViewById(R.id.tv_title);

		if (payMethod == PayMethod.WECHAT) {
			tv_title.setText(mcontext
					.getResources().getString(R.string.wechat_pay));
		} else {
			tv_title.setText(mcontext.getResources()
					.getString(R.string.ali_pay));
		}
		//		if (payMethod == PayMethod.SWIFF) {
		//			String payType = null;
		//			String payTips = null;
		//			payType = OrderDetailActivity.clickPosition == R.id.orderdetail_pay_method_wechat ? "微信支付" : "支付宝支付";
		//			payTips = OrderDetailActivity.clickPosition == R.id.orderdetail_pay_method_wechat ? mcontext
		//					.getResources().getString(R.string.wechat_pay) : mcontext.getResources()
		//					.getString(R.string.ali_pay);
		//			tv_title.setText(payType);
		//			tv_pay_tips.setText(payTips);
		//		}
		timeTV = (TextView) view.findViewById(R.id.pay_qr_tv_time);
		tipsTV = (TextView) view.findViewById(R.id.pay_tips);
		view.findViewById(R.id.btn_cancle).setOnClickListener(this);
		setCancelable(false);
	}

	public void setTimeTV(int time) {
		timeTV.setText("剩余支付时间" + time + "s");
	}

	public void setTips(String tips) {
		tipsTV.setVisibility(View.VISIBLE);
		tipsTV.setText(tips);
	}

	private OnCancleClickListener mCancleClickListener;

	public void setOnCancelListener(OnCancleClickListener listener) {
		mCancleClickListener = listener;
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_cancle:
				dismiss();
				if (mCancleClickListener != null)
					mCancleClickListener.onCancleClick();
				break;
		}
	}
}
