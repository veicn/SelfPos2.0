package com.acewill.slefpos.orderui.main.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.SizeBean;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */

public class OrderDialogAdapter extends BaseExpandableListAdapter {

	private static final String TAG = "OrderDialogAdapter";
	private Context        mContext;
	private List<CartDish> mData;
	//	private HashMap<Integer, Integer> map;
	private List<SizeBean> parentList = new ArrayList<>();
	//	private RemoveItemListener   mListener;
	private OnCartChangeListener mCartChangeListener;
	private static final int addType    = 0;//判断打开对话框的方式
	private static final int changeType = 1;
	private static final int cutType    = 2;

	//	Map<Integer,List<String>> map;//用来记录  parentID 个数
	public OrderDialogAdapter(Context context, List<CartDish> list, int useType) {
		this.mContext = context;
		this.mData = list;
		initData();
	}

	private void initData() {
		//		map = new HashMap<>();
		for (int i = 0; i < mData.size(); i++) {
			CartDish model = mData.get(i);
			/*判断是套餐还是单品*/
			if (model != null) {
				if (UIDataProvider.isDishIsPackage(model.getDishID())) {
					//如果是套餐
					if (model.getSubItemList() != null) {
						SizeBean bean = new SizeBean();
						//						map.put(i, model.subItemList.size());
						bean.setListSize(model.getSubItemList().size());
						parentList.add(bean);
					}
				} else if (model.getOptionList() != null && model.getOptionList().size() > 0) {
					//如果不是套餐，而是单品 并且有选项
					SizeBean bean = new SizeBean();
					bean.setListSize(model.getOptionList().size());
					parentList.add(bean);
				} else {
					SizeBean bean = new SizeBean();
					bean.setListSize(0);
					parentList.add(bean);
				}
			}
		}
	}

	//  获得某个父项的某个子项
	@Override
	public Object getChild(int parentPos, int childPos) {
		return null;
	}

	//  获得父项的数量
	@Override
	public int getGroupCount() {
		if (parentList == null) {
			Toast.makeText(mContext, "map为空", Toast.LENGTH_SHORT)
					.show();
			return 0;
		}
		return parentList.size();
	}

	//  获得某个父项的子项数目
	@Override
	public int getChildrenCount(int parentPos) {
		if (parentList.get(parentPos) == null) {
			Toast.makeText(mContext, "\" + parentList[parentPos] + \" + 数据为空", Toast.LENGTH_SHORT)
					.show();
			return 0;
		}
		return parentList.get(parentPos).getListSize();
	}

	//  获得某个父项
	@Override
	public Object getGroup(int parentPos) {
		return parentList.get(parentPos);
	}

	//  获得某个父项的id
	@Override
	public long getGroupId(int parentPos) {
		return parentPos;
	}

	//  获得某个父项的某个子项的id
	@Override
	public long getChildId(int parentPos, int childPos) {
		return childPos;
	}

	//  按函数的名字来理解应该是是否具有稳定的id，这个函数目前一直都是返回false，没有去改动过
	@Override
	public boolean hasStableIds() {
		return false;
	}

	//  获得父项显示的view
	@Override
	public View getGroupView(final int parentPos, boolean b, View view, ViewGroup viewGroup) {
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.expandable_parent_item, null);
		}
		view.setTag(R.layout.expandable_parent_item, parentPos);
		view.setTag(R.layout.expandable_child_item_package, -1);
		TextView dishname_tv = (TextView) view
				.findViewById(R.id.adapterview_dialog_dishname_tv);
		TextView dishprice_tv = (TextView) view
				.findViewById(R.id.adapterview_dialog_dishprice_tv);
		final TextView totalcount_tv = (TextView) view
				.findViewById(R.id.adapter_dialog_item_totalcount_tv);


		if (mData != null && mData.size() > 0) {
			final CartDish model = mData.get(parentPos);
			dishname_tv.setText(model.getDishName());
			dishprice_tv
					.setText(Order.getInstance().isMember() ? model.getMemberPrice() : "￥" + model
							.getPrice());
			totalcount_tv.setText("x" + String.valueOf(model.getQuantity()));

		} else {

		}
		return view;
	}

	//  获得子项显示的view
	@Override
	public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.expandable_child_item_package, null);
		}
		view.setTag(R.layout.expandable_parent_item, parentPos);
		view.setTag(R.layout.expandable_child_item_package, childPos);


		TextView category_tv = (TextView) view
				.findViewById(R.id.adapterview_dialog_category_tv);
		TextView option_tv = (TextView) view
				.findViewById(R.id.adapterview_dialog_category_tv2);
		TextView child_dish_count_tv = (TextView) view
				.findViewById(R.id.child_dish_count_tv);
		TextView optionPriceTv = (TextView) view
				.findViewById(R.id.adapterview_dialog_option_price_tv);
		TextView truePriceTv = (TextView) view
				.findViewById(R.id.adapterview_dialog_option_true_price_tv);
		if (mData != null && mData.size() > 0) {
			CartDish model = mData.get(parentPos);
			if (UIDataProvider.isDishIsPackage(model.getDishID())) {
				UIPackageOptionItem aPackageBean = model.getSubItemList().get(childPos);
				StringBuilder       builder      = new StringBuilder();
				if (aPackageBean
						.getOptionList() != null && aPackageBean.getOptionList()
						.size() > 0) {
					for (int i = 0; i < aPackageBean.getOptionList().size(); i++) {
						UITasteOption bean = aPackageBean.getOptionList().get(i);
						if (i != aPackageBean.getOptionList().size() - 1) {
							if (bean.getPrice() == 0) {
								builder.append(bean.getOptionName() + "+");
							} else {
								builder.append(bean.getOptionName() + (bean
										.getPrice() == 0 ? "" : "(+￥" + PriceUtil.multiply(
										new BigDecimal(bean.getPrice()), new BigDecimal(bean
												.getQuantity())
								).toString()) + ")");
							}
						} else {
							if (bean.getPrice() == 0) {
								builder.append(bean.getOptionName());
							} else {
								builder.append(bean.getOptionName() + (bean
										.getPrice() == 0 ? "" : "(+￥" + PriceUtil.multiply(
										new BigDecimal(bean.getPrice()), new BigDecimal(bean
												.getQuantity())
								).toString()) + ")");
							}
						}
					}
					optionPriceTv.setVisibility(View.VISIBLE);
					optionPriceTv.setText(builder.toString());
				} else {
					optionPriceTv.setVisibility(View.GONE);
				}
				child_dish_count_tv.setVisibility(View.VISIBLE);
				child_dish_count_tv.setText("x" + aPackageBean.getQuantity());
				option_tv.setVisibility(View.GONE);
				category_tv.setVisibility(View.VISIBLE);
				category_tv.setText(aPackageBean.getDishName());
				if (SystemConfig.isSyncSystem) {
					truePriceTv.setVisibility(View.VISIBLE);
					truePriceTv.setText("￥" + aPackageBean.getPrice());
				} else {
					truePriceTv.setVisibility(View.GONE);
				}
				if (SystemConfig.isSmarantSystem&&aPackageBean.getExtraCost()>0){
					truePriceTv.setVisibility(View.VISIBLE);
					truePriceTv.setText( aPackageBean.getExtraCost()+"");
				}else {
					truePriceTv.setVisibility(View.GONE);
				}


			} else if (model.getOptionList() != null && model.getOptionList().size() > 0) {
				UITasteOption option = model.getOptionList().get(childPos);
				category_tv.setVisibility(View.GONE);
				option_tv.setVisibility(View.VISIBLE);
				option_tv.setText(option.getOptionName());
				child_dish_count_tv.setVisibility(View.GONE);
				if (option.getPrice() != 0) {
					optionPriceTv.setVisibility(View.VISIBLE);
					optionPriceTv.setText("（+￥" + PriceUtil
							.multiply(String.valueOf(option.getPrice()), option
									.getQuantity()) + ")");
				} else {
					optionPriceTv.setVisibility(View.GONE);
					optionPriceTv.setText("");
				}
				truePriceTv.setVisibility(View.GONE);
			} else {
				view.setVisibility(View.GONE);
				category_tv.setVisibility(View.GONE);
			}
		}
		category_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			}
		});
		return view;
	}

	//  子项是否可选中，如果需要设置子项的点击事件，需要返回true
	@Override
	public boolean isChildSelectable(int i, int i1) {
		return true;
	}

	public void expandedGroup(ExpandableListView lv) {
		for (int i = 0; i < parentList.size(); i++) {
			lv.expandGroup(i);
		}
		lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
			                            int groupPosition, long id) {
				return true;
			}
		});
	}


	public void setonCartChangeListener(OnCartChangeListener listener) {
		this.mCartChangeListener = listener;
	}


	public interface OnCartChangeListener {

		void onCartDataChange(int type, List<SizeBean> o, CartDish model);
	}


}
