package com.acewill.slefpos.orderui.main.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.uibean.UIPackageItem;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/18 9:55
 * Desc：
 */
public class TabAdapter extends BaseAdapter {
	private Context                  mContext;
	private List<UIPackageItem> datas;

	public TabAdapter(Context context) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		MyViewHodler hodler = null;
		if (convertView == null) {
			hodler = new MyViewHodler();
			convertView = View.inflate(mContext, R.layout.item_tab, null);
			hodler.item_tv = (TextView) convertView.findViewById(R.id.item_tv);
			hodler.tab_view = convertView.findViewById(R.id.tab_view);
			convertView.setTag(hodler);
		} else {
			hodler = (MyViewHodler) convertView.getTag();
		}
		final UIPackageItem bean = datas.get(position);
		hodler.item_tv
				.setText(bean.getUserdefinedName() != null ? bean.getUserdefinedName() : bean
						.getItemName());
		hodler.tab_view.setVisibility(bean.isSelect() ? View.VISIBLE : View.INVISIBLE);
		hodler.item_tv.setTextColor(bean.isSelect() ? mContext.getResources()
								.getColor(R.color.colorPrimary) :
				mContext.getResources().getColor(R.color.colorSecondaryText));
//		hodler.item_tv.setBackgroundColor(bean.isSelect() ? mContext.getResources()
//				.getColor(R.color.white) :
//				mContext.getResources().getColor(R.color.color_divide));
		return convertView;
	}

	public void resetSelect(UIPackageItem bean) {
		for (UIPackageItem bean1 : datas) {
			bean1.setSelect(bean1.getItemID().equals(bean.getItemID()) ? true : false);
		}
		notifyDataSetChanged();
	}

	private class MyViewHodler {
		TextView item_tv;
		View     tab_view;
	}

	public void setData(List<UIPackageItem> data) {
		this.datas = data;
		this.datas.get(0).setSelect(true);
		notifyDataSetChanged();
	}
}
