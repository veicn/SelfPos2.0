package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.dialog.BaseDialog;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

/**
 * Author：Anch
 * Date：2018/7/11 11:13
 * Desc：确认支付对话框
 */
public class ComfirmPayDialog extends BaseDialog implements OnLoadMoreListener, OnRefreshListener {


	public static ComfirmPayDialog getInstance(int type) {
		ComfirmPayDialog dialog = new ComfirmPayDialog();
		Bundle           bundle = new Bundle();
		bundle.putInt("type", type);
		dialog.setArguments(bundle);
		return dialog;
	}

	private boolean isInit;
	private View    view;

	@Override
	public View getView() {
		if (isInit) {
			return view;
		}
		final int type = getArguments().getInt("type");
		view = View.inflate(mcontext, R.layout.dialog_comfirmpay, null);
		TextView total_price = (TextView) view.findViewById(R.id.total_price);
		TextView title       = (TextView) view.findViewById(R.id.title);
		switch (type) {
			case PayMethod.ALI:
				title.setText("使用支付宝支付");
				break;
			case PayMethod.WECHAT:
				title.setText("使用微信支付");
				break;

		}
		total_price.setText(String.valueOf("￥ " + Price.getInstance().getTotal()));
		view.findViewById(R.id.cancer).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				mRxManager.post(AppConstant.COMFIRM_PAY, type);
			}
		});
		//		view.findViewById(R.id.iv_cancer).setOnClickListener(new View.OnClickListener() {
		//			@Override
		//			public void onClick(View v) {
		//				dismiss();
		//			}
		//		});

		isInit = true;
		return view;
	}


	@Override
	public float getSize() {
		return 0.7f;
	}

	@Override
	public void onLoadMore(View loadMoreView) {

	}

	@Override
	public void onRefresh() {

	}
}
