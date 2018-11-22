package com.acewill.slefpos.orderui.main.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.market.MarketBean;
import com.acewill.slefpos.orderui.main.market.MarketController;

/**
 * Author：Anch
 * Date：2018/1/4 10:53
 * Desc：
 */
public class FavorDialog extends BaseDialog {
	/**
	 * @return
	 */
	public static FavorDialog newInstance() {
		FavorDialog fragment = new FavorDialog();
		Bundle      bundle   = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_marketlist, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		TextView     name      = (TextView) view.findViewById(R.id.dialog_marketlist_name);
		LinearLayout container = (LinearLayout) view.findViewById(R.id.dialog_marketlist_container);
		name.setText(StoreConfigure.getSname());

		for (MarketBean bean : MarketController.getMarketBeanList()) {
			View inflate = View
					.inflate(mcontext, R.layout.noticelayout2, null);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
			//			android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 360);
			inflate.setLayoutParams(params);
			ImageView imageView = (ImageView) inflate.findViewById(R.id.market_ad_img);
			imageView.setImageResource(bean.getMarketImg());
			TextView tv = (TextView) inflate.findViewById(R.id.market_ad_tv);
			tv.setText(bean.getMarketName());
			container.addView(inflate);
		}
		setCancelable(true);
	}


	@Override
	public float getSize() {
		return 0.7f;
	}
}
