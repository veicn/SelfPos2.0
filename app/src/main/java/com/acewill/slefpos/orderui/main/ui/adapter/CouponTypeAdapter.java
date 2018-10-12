package com.acewill.slefpos.orderui.main.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.uibean.CouponType;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/6/6 10:23
 * Desc：
 */
public class CouponTypeAdapter extends BaseAdapter {
	private Context          mContext;
	private List<CouponType> datas;
	private String           currentTabType;

	public CouponTypeAdapter(Context context) {
		mContext = context;
		currentTabType = "ALL";
	}

	@Override
	public int getCount() {
		if (datas != null)
			return datas.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (datas != null)
			return datas.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		MyViewHodler hodler = null;
		if (convertView == null) {
			hodler = new MyViewHodler();
			convertView = View.inflate(mContext, R.layout.item_coupon_tab, null);
			hodler.coupon_name = (TextView) convertView.findViewById(R.id.coupon_type_name);
			hodler.coupon_type_size = (TextView) convertView.findViewById(R.id.coupon_type_size);
			hodler.coupon_type_ly = (RelativeLayout) convertView.findViewById(R.id.coupon_type_ly);
			convertView.setTag(hodler);
		} else {
			hodler = (MyViewHodler) convertView.getTag();
		}
		final CouponType coupon = datas.get(position);
		if (coupon.getType().equals("A")) {
			hodler.coupon_name.setText("折扣券");
		} else if (coupon.getType().equals("B") || coupon.getType().equals("1")) {
			hodler.coupon_name.setText("代金券");
		} else if (coupon.getType().equals("C")) {
			hodler.coupon_name.setText("兑换券");
		} else if (coupon.getType().equals("ALL")) {
			hodler.coupon_name.setText("全部");
		} else if (coupon.getType().equals("2")) {
			hodler.coupon_name.setText("菜品券");
		} else {
			hodler.coupon_name.setText("未知");
		}
		hodler.coupon_type_size.setText(coupon.getType_size() + "");

		if (coupon.isSelect()) {
			setItemStatu(1, hodler.coupon_name, hodler.coupon_type_ly);
		} else {
			setItemStatu(0, hodler.coupon_name, hodler.coupon_type_ly);
		}
		hodler.coupon_type_ly.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null && !coupon.getType().equals(currentTabType)) {
					for (CouponType type : datas) {
						if (type.getType().equals(coupon.getType()))
							type.setSelect(true);
						else
							type.setSelect(false);
					}
					currentTabType = coupon.getType();
					mListener.onTabClickListener(coupon.getType());
					notifyDataSetChanged();
				}
			}
		});
		return convertView;
	}

	private TabClickListener mListener;

	public interface TabClickListener {
		void onTabClickListener(String position);
	}

	public void setOnTabClickListener(TabClickListener listener) {
		mListener = listener;
	}


	private class MyViewHodler {
		TextView       coupon_name;
		TextView       coupon_type_size;
		RelativeLayout coupon_type_ly;
	}

	public void setData(List<CouponType> data) {
		this.datas = data;
		//		this.datas.get(0).setSelect(true);
		notifyDataSetChanged();
	}

	private void setItemStatu(int i, TextView tv_taste, View view) {
		switch (i) {
			case 0://未选中
				view.setBackgroundDrawable(setShape("#fcf6e6", "#fcf6e6"));
				tv_taste.setTextColor(Color.parseColor("#666666"));
				break;
			case 1:
				view.setBackgroundDrawable(setShape("#FF9100", "#FF9100"));
				tv_taste.setTextColor(Color.parseColor("#ffffff"));
				break;
		}
	}

	//设置
	private GradientDrawable setShape(String stroke, String fill) {
		//		int strokeWidth = 2; // 2 边框宽度

		int roundRadius = 5; // 5 圆角半径
		//		int strokeColor = Color.parseColor(stroke);//边框颜色
		int              fillColor = Color.parseColor(fill);//内部填充颜色
		GradientDrawable gd        = new GradientDrawable();//创建drawable
		gd.setColor(fillColor);
		gd.setCornerRadius(roundRadius);
		//		gd.setStroke(strokeWidth, strokeColor);
		return gd;
	}

}
