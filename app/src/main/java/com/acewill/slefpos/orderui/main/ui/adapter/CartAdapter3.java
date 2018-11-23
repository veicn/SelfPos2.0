package com.acewill.slefpos.orderui.main.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.CartDishHelper;
import com.acewill.slefpos.bean.uibean.GroupEntity;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.bumptech.glide.Glide;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/22 17:20
 * Desc：
 */
public class CartAdapter3 extends GroupedRecyclerViewAdapter {
	private List<GroupEntity> mGroups;
	private RxManager         mRxManager;

	public CartAdapter3(Context context, List<GroupEntity> groups) {
		super(context);
		mGroups = groups;
		mRxManager = new RxManager();

	}

	@Override
	public int getGroupCount() {
		return mGroups == null ? 0 : mGroups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		List<CartDish> children = mGroups.get(groupPosition).getChildren();
		return children == null ? 0 : children.size();
	}

	@Override
	public boolean hasHeader(int groupPosition) {
		return true;
	}

	@Override
	public boolean hasFooter(int groupPosition) {
		return false;
	}

	@Override
	public int getHeaderLayout(int viewType) {
		return R.layout.layout_sticky_header_view;
	}

	@Override
	public int getFooterLayout(int viewType) {
		return 0;
	}

	@Override
	public int getChildLayout(int viewType) {
		return R.layout.layout_list_item;
	}

	@Override
	public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
		GroupEntity entity = mGroups.get(groupPosition);
		holder.setText(R.id.tv_sticky_header_view, entity.getHeader());
	}

	@Override
	public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
		//		CartAdapterEntity.TagsEntity entity = mGroups.get(groupPosition).getAllTagsList().get();
		//		holder.setText(R.id.tv_footer, entity.getFooter());
	}

	@Override
	public void onBindChildViewHolder(BaseViewHolder holder, final int groupPosition, final int childPosition) {
		final CartDish cartDish = mGroups.get(groupPosition).getChildren()
				.get(childPosition);
		TextView  dishName           = holder.get(R.id.dish_name);
		TextView  dishOption         = holder.get(R.id.dish_option);
		TextView  dishSubitemlist    = holder.get(R.id.dish_subitemlist);
		TextView  dishCost           = holder.get(R.id.dish_cost);
		TextView  dishDiscountAmount = holder.get(R.id.dish_discountamount);
		TextView  dishQuantity       = holder.get(R.id.dish_quantity);
		ImageView dishPhoto          = holder.get(R.id.dish_photo);
		dishName.setText(cartDish.getDishName());
		if (cartDish.getOptionList() != null) {
			dishSubitemlist.setVisibility(View.GONE);
			dishOption.setVisibility(View.VISIBLE);
			dishOption.setText(CartDishHelper
					.getCartDishTasteOptionStr(cartDish
							.getOptionList()));
		} else {
			dishOption.setVisibility(View.GONE);
		}
		if (cartDish.getSubItemList() != null) {
			dishOption.setVisibility(View.GONE);
			dishSubitemlist.setVisibility(View.VISIBLE);
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < cartDish.getSubItemList().size(); i++) {
				UIPackageOptionItem packageOptionItem = cartDish.getSubItemList().get(i);
				if (i == cartDish.getSubItemList().size() - 1) {
					if (packageOptionItem.getOptionList() != null && packageOptionItem
							.getOptionList().size() > 0) {
						builder.append(packageOptionItem
								.getDishName()+(packageOptionItem.getExtraCost()==0?"":"[套餐加价+￥" + packageOptionItem.getExtraCost() + "]") + (packageOptionItem
								.getQuantity() == 1 ? "" : "x" + packageOptionItem
								.getQuantity()) + "{" + CartDishHelper
								.getCartDishTasteOptionStr(packageOptionItem
										.getOptionList()) + "}");
					} else {
						builder.append(packageOptionItem
								.getDishName() +(packageOptionItem.getExtraCost()==0?"":"[套餐加价+￥" + packageOptionItem.getExtraCost() + "]") + (packageOptionItem
								.getQuantity() == 1 ? "" : "x" + packageOptionItem
								.getQuantity()));
					}
				} else {
					if (packageOptionItem.getOptionList() != null && packageOptionItem
							.getOptionList().size() > 0) {
						builder.append(packageOptionItem
								.getDishName()  +(packageOptionItem.getExtraCost()==0?"":"[套餐加价+￥" + packageOptionItem.getExtraCost() + "]") + (packageOptionItem
								.getQuantity() == 1 ? "" : "x" + packageOptionItem
								.getQuantity()) + "{" + CartDishHelper
								.getCartDishTasteOptionStr(packageOptionItem
										.getOptionList()) + "}" + ";");
					} else {
						builder.append(packageOptionItem
								.getDishName()  +(packageOptionItem.getExtraCost()==0?"":"[套餐加价+￥" + packageOptionItem.getExtraCost() + "]") + (packageOptionItem
								.getQuantity() == 1 ? "" : "x" + packageOptionItem
								.getQuantity()) + ";");
					}
				}
			}
			dishSubitemlist.setText(builder.toString());
		} else {
			dishSubitemlist.setVisibility(View.GONE);
		}

		if (cartDish.getDiscountAmount() != 0) {
			dishCost.setText("￥" + PriceUtil
					.subtract(cartDish.getCost(), String
							.valueOf(cartDish.getDiscountAmount())) + "");
		} else
			dishCost.setText("￥" + cartDish.getCost() + "");

		if (cartDish.getDiscountAmount() != 0)
			dishDiscountAmount.setText("(已优惠￥" + PriceUtil
					.multiply(String.valueOf(cartDish.getDiscountAmount()), cartDish
							.getQuantity()) + ")");
		else
			dishDiscountAmount.setText("");
//		if (cartDish.getTemp_price() != 0)
//			dishDiscountAmount.setText("(优惠 -￥" + PriceUtil
//					.multiply(String.valueOf(cartDish.getTemp_price()), cartDish
//							.getQuantity()) + ")");
//		else
//			dishDiscountAmount.setText("");
		dishQuantity.setText("x" + cartDish.getQuantity() + "");


		Glide.with(mContext).load(cartDish.getImageName())
				.placeholder(R.mipmap.default_img).error(R.mipmap.default_img)
				.into(dishPhoto);
		ViewCompat
				.setTransitionName(dishPhoto, String
						.valueOf(cartDish.getDishID()) + "_image");


		holder.get(R.id.iv_plus_counts).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					addItemQuantity(cartDish, groupPosition, childPosition);
				} catch (Exception e) {
					FileLog.log("addItemQuantity>" + e.getMessage());
				}

			}
		});
		holder.get(R.id.iv_minus_counts).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					reduceItemQuantity(cartDish, groupPosition, childPosition);
				} catch (Exception e) {
					FileLog.log("reduceItemQuantity>" + e.getMessage());
				}
			}
		});
	}


	/**
	 * 增加菜品数量
	 *
	 * @param cartDish
	 * @param groupPosition
	 * @param childPosition
	 */
	public void addItemQuantity(CartDish cartDish, int groupPosition, int childPosition) {
		Cart.getInstance().changeDishQuantity(cartDish, true);
		changeChild(groupPosition, childPosition);
		mRxManager.post(AppConstant.ON_CART_ITEM_CHANGE, 1);
	}

	/**
	 * 减少菜品数量
	 *
	 * @param cartDish
	 * @param groupPosition
	 * @param childPosition
	 */
	public void reduceItemQuantity(CartDish cartDish, int groupPosition, int childPosition) {
		if (cartDish.getQuantity() == 1) {
			if (mGroups.get(groupPosition).getChildren().size() == 1) {
				if (mGroups.size() > 1) {
					removeGroup(groupPosition);
					mGroups.remove(groupPosition);
				}
			} else {
				removeChild(groupPosition, childPosition);
				mGroups.get(groupPosition).getChildren().remove(childPosition);
			}
			Cart.getInstance().removeDish(cartDish);
		} else {
			Cart.getInstance().changeDishQuantity(cartDish, false);
			changeChild(groupPosition, childPosition);
		}
		mRxManager.post(AppConstant.ON_CART_ITEM_CHANGE, 1);
	}


	//	public void removeItem(CartDish cartDish, int groupPosition, int childPosition) {
	//		Cart.getInstance().removeDish(cartDish);
	//		notifyItemRemoved(position);
	//		isRemoveStickTitle(cartDish, position);
	//
	//		//		isShowEmptyCart();
	//	}
}
