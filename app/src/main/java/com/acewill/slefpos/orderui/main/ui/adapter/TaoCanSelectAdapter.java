package com.acewill.slefpos.orderui.main.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Author：Anch
 * Date：2018/4/18 10:01
 * Desc：
 */
public class TaoCanSelectAdapter extends BaseAdapter {
	private  RxManager mRxManager;
	private Context mContext;
	private ArrayList<UIPackageOptionItem> datas;

	public TaoCanSelectAdapter(Context context,RxManager rxManager) {
		mContext = context;
		mRxManager = rxManager;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		MyViewHodler hodler = null;
		if (convertView == null) {
			hodler = new MyViewHodler();
			convertView = View.inflate(mContext, R.layout.item_taocan_select, null);
			hodler.iv_dish_image = (ImageView) convertView.findViewById(R.id.iv_dish_image);
			hodler.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			hodler.dish_count = (TextView) convertView.findViewById(R.id.dish_count);
			convertView.setTag(hodler);
		} else {
			hodler = (MyViewHodler) convertView.getTag();
		}
		final UIPackageOptionItem bean = datas.get(position);
		Glide.with(mContext).load(bean.getImageName()).error(R.mipmap.default_img)
				.into(hodler.iv_dish_image);
		hodler.tv_name.setText(bean.getDishName());
		hodler.dish_count.setText("x" + bean.getQuantity());
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RxBus.getInstance().post(AppConstant.SELECT_DISH_FROM_PACKAGE_CART, bean);
			}
		});
		convertView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (bean.isCannoclick()) {
					ToastUitl
							.showShort(mContext, "该菜为必选，不能删除!");
					return false;
				}
				RxBus.getInstance().post(AppConstant.REMOVE_DISH_FROM_PACKAGE_CART, bean);
				return true;
			}
		});
		return convertView;
	}

	public void addData(UIPackageOptionItem bean) {
		if (datas == null)
			datas = new ArrayList<>();
		datas.add(bean);
		mRxManager.post(AppConstant.TAOCANSELCETDISHCOUNT, datas.size());
		notifyDataSetChanged();
	}

	public void removeData(UIPackageOptionItem item) {
		Iterator<UIPackageOptionItem> iterator = datas.iterator();
		while (iterator.hasNext()) {
			UIPackageOptionItem next = iterator.next();
			if (item.getDishID().equals(next.getDishID())) {
				iterator.remove();
				break;
			}
		}
		mRxManager.post(AppConstant.TAOCANSELCETDISHCOUNT, datas.size());
		notifyDataSetChanged();
	}

	public ArrayList<UIPackageOptionItem> getDatas() {
		return datas;
	}

	private class MyViewHodler {
		ImageView iv_dish_image;
		TextView  tv_name;
		TextView  dish_count;
	}

	public void setData(ArrayList<UIPackageOptionItem> data) {
		this.datas = data;
		mRxManager.post(AppConstant.TAOCANSELCETDISHCOUNT, datas.size());
		notifyDataSetChanged();
	}
}
