package com.acewill.slefpos.orderui.main.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.orderui.main.ui.eventbus.OnSelectDishChange;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.baserx.RxBus;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/18 9:55
 * Desc：
 */
public class TaoCanDishAdapter extends BaseAdapter {
	private Context                   mContext;
	private List<UIPackageOptionItem> datas;
	private UIPackageItem             mBean;

	public TaoCanDishAdapter(Context context) {
		mContext = context;
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
			convertView = View.inflate(mContext, R.layout.item_taocan_dish, null);
			hodler.iv_dish_image = (ImageView) convertView.findViewById(R.id.iv_dish_image);
			hodler.iv_dish_image_bg = (ImageView) convertView.findViewById(R.id.iv_dish_image_bg);
			hodler.add_count_ly = (LinearLayout) convertView.findViewById(R.id.add_count_ly);
			hodler.iv_minus_counts = (FrameLayout) convertView.findViewById(R.id.iv_minus_counts);
			hodler.iv_plus_counts = (FrameLayout) convertView.findViewById(R.id.iv_plus_counts);
			hodler.tv_counts = (TextView) convertView.findViewById(R.id.tv_counts);
			hodler.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			hodler.add_price_layout = (RelativeLayout) convertView
					.findViewById(R.id.add_price_layout);
			hodler.tv_extra_price = (TextView) convertView
					.findViewById(R.id.tv_extra_price);
			convertView.setTag(hodler);
		} else {
			hodler = (MyViewHodler) convertView.getTag();
		}
		final UIPackageOptionItem bean = datas.get(position);
		Glide.with(mContext).load(bean.getImageName()).error(R.mipmap.default_img)
				.into(hodler.iv_dish_image);
		hodler.tv_name.setText(bean.getDishName());
		hodler.iv_dish_image_bg.setVisibility(bean.isSelect() ? View.VISIBLE : View.INVISIBLE);
		if (mBean.getMaxQty() != 1 && !mBean.isSelectOk())
			hodler.add_count_ly
					.setVisibility(bean.getQuantity() >= 1 ? View.VISIBLE : View.INVISIBLE);
		else {
			if (bean.getQuantity() > 1)
				hodler.add_count_ly.setVisibility(View.VISIBLE);
			else {
				hodler.add_count_ly.setVisibility(View.INVISIBLE);
			}
		}
		if (bean.getExtraCost() > 0) {
			hodler.tv_extra_price.setText("+￥"+bean.getExtraCost() + "");
			hodler.add_price_layout.setVisibility(View.VISIBLE);
		} else {
			hodler.add_price_layout.setVisibility(View.GONE);
		}
		hodler.tv_counts.setText(bean.getQuantity() + "");

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (bean.isCannoclick())
					return;
				//判断是否已经选满了
				OnSelectDishChange change = new OnSelectDishChange();
				change.setAddDish(bean.isSelect());
				change.setType(0);
				change.setBean(bean);
				RxBus.getInstance().post(AppConstant.ONSElECTDISHCHANGE, change);
			}
		});
		hodler.iv_minus_counts.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				OnSelectDishChange change = new OnSelectDishChange();
				change.setAddDish(bean.isSelect());
				change.setBean(bean);
				change.setIsadd(false);
				change.setType(1);
				RxBus.getInstance().post(AppConstant.ONSElECTDISHCHANGE, change);
			}
		});
		hodler.iv_plus_counts.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				OnSelectDishChange change = new OnSelectDishChange();
				change.setAddDish(bean.isSelect());
				change.setBean(bean);
				change.setIsadd(true);
				change.setType(1);
				RxBus.getInstance().post(AppConstant.ONSElECTDISHCHANGE, change);
			}
		});
		return convertView;
	}

	public void setSelectOk(boolean selectOk) {
		mBean.setSelectOk(selectOk);
	}

	private class MyViewHodler {
		ImageView      iv_dish_image;
		ImageView      iv_dish_image_bg;
		TextView       tv_name;
		TextView       tv_counts;
		LinearLayout   add_count_ly;
		FrameLayout    iv_minus_counts;
		FrameLayout    iv_plus_counts;
		RelativeLayout add_price_layout;
		TextView       tv_extra_price;
	}

	public void setData(List<UIPackageOptionItem> data, UIPackageItem bean) {
		if (datas == null) {
			this.datas = data;
		} else {
			datas.clear();
			datas.addAll(data);
		}
		this.mBean = bean;
		notifyDataSetChanged();
	}
}
