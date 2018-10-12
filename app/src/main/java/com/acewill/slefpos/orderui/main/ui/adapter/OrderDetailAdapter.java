package com.acewill.slefpos.orderui.main.ui.adapter;

/**
 * Author：Anch
 * Date：2018/2/27 17:55
 * Desc：
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.cartbean.CartDish;

import java.util.List;


/**
 * Created by YR on 2016/04/12.
 */
public class OrderDetailAdapter extends BaseAdapter {

	private List<CartDish> list = null;

	private Context context = null;

	private LayoutInflater inflater = null;

	public OrderDetailAdapter(List<CartDish> list, Context context) {
		this.list = list;
		this.context = context;
		// 布局装载器对象
		inflater = LayoutInflater.from(context);
	}

	// 适配器中数据集中数据的个数
	@Override
	public int getCount() {
		return list.size();
	}

	// 获取数据集中与指定索引对应的数据项
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	// 获取指定行对应的ID
	@Override
	public long getItemId(int position) {
		return position;
	}

	// 获取每一个Item显示的内容
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.adapter_orderdetail, null);
			viewHolder.dish_name = (TextView) convertView.findViewById(R.id.dish_name);
			convertView.setTag(viewHolder);// 通过setTag将ViewHolder和convertView绑定
		} else {
			viewHolder = (ViewHolder) convertView.getTag(); // 获取，通过ViewHolder找到相应的控件
		}
		CartDish cartDish = list.get(position);
		viewHolder.dish_name.setText(cartDish.getDishName());
		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView  dish_name;
		TextView  tvContent;
	}
}