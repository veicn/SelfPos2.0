//package com.acewill.slefpos.orderui.main.ui.adapter;
//
//import android.content.Context;
//import android.support.v4.view.ViewCompat;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.acewill.slefpos.R;
//import com.acewill.slefpos.app.AppConstant;
//import com.acewill.slefpos.bean.cartbean.Cart;
//import com.acewill.slefpos.bean.cartbean.CartDish;
//import com.acewill.slefpos.bean.cartbean.CartDishHelper;
//import com.acewill.slefpos.bean.cartbean.StickyCartDish;
//import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
//import com.bumptech.glide.Glide;
//import com.jaydenxiao.common.baserx.RxManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Author：Anch
// * Date：2018/2/9 16:25
// * Desc：
// */
//public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
//	public static final  int          VIEW_TYPE_NON_STICKY = R.layout.item_menu_main;
//	private static final int          VIEW_TYPE_STICKY     = R.layout.item_menu_sticky;
//	private              List<Object> mListItems           = new ArrayList<>();
//
//	private Context   mContext;
//	private RxManager mRxManager;
//
//	public CartAdapter(Context context) {
//		this.mContext = context;
//		mRxManager = new RxManager();
//	}
//
//	@Override
//	public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//		View           view     = inflater.inflate(viewType, parent, false);
//		return new CartHolder(view, viewType);
//	}
//
//	@Override
//	public void onBindViewHolder(final CartHolder holder, int position) {
//
//		switch (holder.viewType) {
//			case VIEW_TYPE_NON_STICKY:
//				final CartDish cartDish = (CartDish) mListItems.get(position);
//				holder.dishName.setText(cartDish.getDishName());
//				if (cartDish.getOptionList() != null) {
////					StringBuilder builder = new StringBuilder();
//					//					for (int i = 0; i < cartDish.getOptionList().size(); i++) {
//					//						UITasteOption option = cartDish.getOptionList().get(i);
//					//						if (i == cartDish.getOptionList().size() - 1)
//					//							builder.append(option.getOptionName());
//					//						else
//					//							builder.append(option.getOptionName() + ";");
//					//					}
//					holder.dishOption.setText(CartDishHelper
//							.getCartDishTasteOptionStr(cartDish
//									.getOptionList()));
//				}
//				if (cartDish.getSubItemList() != null) {
//					StringBuilder builder = new StringBuilder();
//					for (int i = 0; i < cartDish.getSubItemList().size(); i++) {
//						UIPackageOptionItem packageOptionItem = cartDish.getSubItemList().get(i);
//						if (i == cartDish.getSubItemList().size() - 1)
//							if (packageOptionItem.getOptionList() != null && packageOptionItem
//									.getOptionList().size() > 0) {
//								builder.append(packageOptionItem
//										.getDishName() + "x" + packageOptionItem
//										.getQuantity() + "{" + CartDishHelper
//										.getCartDishTasteOptionStr(packageOptionItem
//												.getOptionList()) + "}");
//							} else {
//								builder.append(packageOptionItem
//										.getDishName() + "x" + packageOptionItem
//										.getQuantity());
//							}
//						else {
//							if (packageOptionItem.getOptionList() != null && packageOptionItem
//									.getOptionList().size() > 0) {
//								builder.append(packageOptionItem
//										.getDishName() + "x" + packageOptionItem
//										.getQuantity() + "{" + CartDishHelper
//										.getCartDishTasteOptionStr(packageOptionItem
//												.getOptionList()) + "}" + ";");
//							} else {
//								builder.append(packageOptionItem
//										.getDishName() + "x" + packageOptionItem
//										.getQuantity() + ";");
//							}
//						}
//					}
//					holder.dishOption.setText(builder.toString());
//				}
//
//				holder.dishCost.setText("￥" + cartDish.getCost() + "");
//				holder.dishQuantity.setText("x" + cartDish.getQuantity() + "");
//
//
//				Glide.with(mContext).load(cartDish.getImageName())
//						.placeholder(R.mipmap.default_img).error(R.mipmap.default_img)
//						.into(holder.dishPhoto);
//
//
//				holder.view.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						mListener.onNontickyItemClick(cartDish, holder.dishPhoto);
//					}
//				});
//				// It is important that each shared element in the source screen has a unique transition name.
//				// For example, we can't just give all the images in our grid the transition name "kittenImage"
//				// because then we would have conflicting transition names.
//				// By appending "_image" to the position, we can support having multiple shared elements in each
//				// grid cell in the future.
//				ViewCompat
//						.setTransitionName(holder.dishPhoto, String
//								.valueOf(cartDish.getDishID()) + "_image");
//				break;
//
//			case VIEW_TYPE_STICKY:
//				StickyCartDish stickyCartDish = (StickyCartDish) mListItems.get(position);
//				holder.kindName.setText(stickyCartDish.getKindId());
//				break;
//		}
//	}
//
//	public OnNontickyItemClickListener mListener;
//
//	public interface OnNontickyItemClickListener {
//		void onNontickyItemClick(CartDish cartDish, ImageView dishPhoto);
//	}
//
//	public void setOnNontickyItemClickListener(OnNontickyItemClickListener listener) {
//		mListener = listener;
//	}
//
//
//	@Override
//	public int getItemViewType(int position) {
//		if (mListItems.get(position) instanceof StickyCartDish) {
//			return VIEW_TYPE_STICKY;
//		}
//		return VIEW_TYPE_NON_STICKY;
//	}
//
//	@Override
//	public int getItemCount() {
//		return mListItems.size();
//	}
//
//	public void removeItem(int position) {
//		CartDish cartDish = (CartDish) mListItems.get(position);
//		Cart.getInstance().removeDish(cartDish);
//		mListItems.remove(position);
//		notifyItemRemoved(position);
//		isRemoveStickTitle(cartDish, position);
//
////		isShowEmptyCart();
//	}
//
//	private void isShowEmptyCart() {
//		if (mListItems != null && mListItems.size() == 0) {
//			showEmptyCart();
//		}
//	}
//
//	/**
//	 * 购物车为空
//	 */
//	private void showEmptyCart() {
//		// TODO: 2018/2/23 anch
//	}
//
//	/**
//	 * 如果该小类没有菜了，删除该小类
//	 *
//	 * @param cartDish
//	 * @param position
//	 */
//	private void isRemoveStickTitle(CartDish cartDish, int position) {
//		String kindId = cartDish.getKindId();
//		for (Object o : mListItems) {
//			if (o instanceof CartDish) {
//				if (kindId.equals(((CartDish) o).getKindId())) {
//					return;
//				}
//			}
//		}
//		mListItems.remove(position - 1);
//		notifyItemRemoved(position - 1);
//	}
//
//	/**
//	 * 减少菜品数量
//	 *
//	 * @param position
//	 */
//	public void reduceItemQuantity(int position) {
//		CartDish cartDish = (CartDish) mListItems.get(position);
//		if (cartDish.getQuantity() == 1)
//			removeItem(position);
//		else {
//			Cart.getInstance().changeDishQuantity(cartDish, false);
//			notifyItemChanged(position);
//		}
//		mRxManager.post(AppConstant.ON_CART_ITEM_CHANGE, 1);
//	}
//
//	/**
//	 * 增加菜品数量
//	 *
//	 * @param position
//	 */
//	public void addItemQuantity(int position) {
//		CartDish cartDish = (CartDish) mListItems.get(position);
//		Cart.getInstance().changeDishQuantity(cartDish, true);
//		notifyItemChanged(position);
//		mRxManager.post(AppConstant.ON_CART_ITEM_CHANGE, 1);
//	}
//
//	public class CartHolder extends RecyclerView.ViewHolder {
//		private TextView  dishName;
//		private TextView  kindName;
//		private TextView  dishOption;
//		private TextView  dishCost;
//		private TextView  dishQuantity;
//		private ImageView dishPhoto;
//		int  viewType;
//		View view;
//
//		public CartHolder(View itemView, int viewType) {
//			super(itemView);
//			this.viewType = viewType;
//			this.view = itemView;
//			switch (viewType) {
//				case VIEW_TYPE_NON_STICKY:
//					dishName = (TextView) itemView.findViewById(R.id.dish_name);
//					dishOption = (TextView) itemView.findViewById(R.id.dish_option);
//					dishCost = (TextView) itemView.findViewById(R.id.dish_cost);
//					dishQuantity = (TextView) itemView.findViewById(R.id.dish_quantity);
//					dishPhoto = (ImageView) itemView.findViewById(R.id.dish_photo);
//					break;
//				case VIEW_TYPE_STICKY:
//					kindName = (TextView) itemView.findViewById(R.id.tv_title);
//					break;
//			}
//		}
//	}
//
//	/**
//	 * 获取购物车的分组数据
//	 *
//	 * @param newItems
//	 */
//	public void setListItems(List<CartDish> newItems) {
//		mListItems.clear();
//		List<String> titleList = new ArrayList<>();
//		for (CartDish cartDish : newItems) {
//			if (!titleList.contains(cartDish.getDishKindStr())) {
//				titleList.add(cartDish.getDishKindStr());
//			}
//		}
//		List<Object> cartDishes = new ArrayList<>();
//		for (String title : titleList) {
//			cartDishes.add(new StickyCartDish(title));
//			for (CartDish cartDish : newItems) {
//				if (cartDish.getDishKindStr().equals(title)) {
//					cartDishes.add(cartDish);
//				}
//			}
//		}
//		mListItems.addAll(cartDishes);
//		notifyDataSetChanged();
//	}
//}
