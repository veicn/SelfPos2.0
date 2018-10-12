package com.acewill.slefpos.bean.cartbean;

import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.orderbean.CheckCountReq;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptReq;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.google.gson.Gson;
import com.jaydenxiao.common.baserx.RxManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/2/5 16:50
 * Desc：购物车
 */
public class Cart {
	private static final String TAG = "Cart";
	private List<CartDish> mCartDishList;

	private static Cart      mCart;
	private static RxManager mRxManager;
	private        String    callNumber;//送餐模式的时候需要设置


	/**
	 * 获取购物车对象
	 *
	 * @return
	 */
	public static Cart getInstance() {
		if (mCart == null) {
			mCart = new Cart();
			mRxManager = new RxManager();
		}
		return mCart;
	}

	/**
	 * 获取购物车商品包含mix价格
	 *
	 * @param cartDish
	 * @return
	 */
	public String getCartDishPriceWithMix(CartDish cartDish) {
		BigDecimal total = new BigDecimal(String.valueOf(cartDish.getPrice()));
		if (cartDish.getOptionList() != null) {
			for (UITasteOption option : cartDish.getOptionList()) {
				total = PriceUtil.add(total, PriceUtil
						.multiply(new BigDecimal(String.valueOf(option.getPrice())), option
								.getQuantity()));
			}
		}
		return total.toString();
	}

	/**
	 * 获取购物车商品中的套餐的单品的价格包含mix价格
	 *
	 * @param optionItem
	 * @return
	 */
	public String getSubItemPriceWithMix(UIPackageOptionItem optionItem) {
		BigDecimal total = new BigDecimal(String.valueOf(optionItem.getPrice()));
		if (optionItem.getOptionList() != null) {
			for (UITasteOption option : optionItem.getOptionList()) {
				total = PriceUtil.add(total, new BigDecimal(String.valueOf(option.getPrice())));
			}
		}
		return total.toString();
	}

	/**
	 * 加入购物车
	 *
	 * @param dish
	 */
	public void addDish(UIDish dish) {

		CartDish cartDish = toCartDish(dish);

		if (Cart.getInstance().getCartDishes().size() != 0 && equal(cartDish)) {
			return;
		}
		Cart.getInstance().getCartDishes().add(cartDish);
		mRxManager.post(AppConstant.ADD_DISH_TO_CART, cartDish);
	}

	/**
	 * 判断是否是相同的套餐
	 * 1、是否相同id
	 * 2、是否相同品项
	 *
	 * @param dish
	 */
	private boolean equalPackage(CartDish dish, CartDish cartDish) {
		if (cartDish.getDishID().equals(dish.getDishID())) {
			if (cartDish.getSubItemList().size() == dish.getSubItemList()
					.size()) {
				boolean b = equalPackageItem(cartDish.getSubItemList(), dish
						.getSubItemList());
				if (b) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	private boolean equalPackageItem(List<UIPackageOptionItem> items, List<UIPackageOptionItem> items1) {
		int sameSize = 0;
		for (UIPackageOptionItem item : items) {
			for (UIPackageOptionItem item2 : items1) {
				if (item.getItemID().equals(item2.getItemID()) && item.getQuantity() == item2
						.getQuantity()) {
					boolean b = sameOption(item.getOptionList(), item2
							.getOptionList());
					if (b) {
						sameSize++;
					}
				}
			}
		}
		if (sameSize == items.size())
			return true;
		else
			return false;
	}

	/**
	 * 通过id获取购物车中的CartDish
	 *
	 * @param id
	 * @return
	 */
	private CartDish getCartDishByDishId(int id) {
		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			if (cartDish.getDishID().equals(id))
				return cartDish;
		}
		return null;
	}


	/**
	 * 判断新添加的Dish在购物车中是否有相同的
	 * 判断点
	 * 1、dishId
	 * 2、dishOption
	 */
	public boolean equal(CartDish dish) {
		boolean same = false;
		for (CartDish cartDish2 : Cart.getInstance().getCartDishes()) {
			if (!cartDish2.getDishID().equals(dish.getDishID())) {
				continue;
			} else if (UIDataProvider.isDishIsPackage(dish.getDishID())) {
				boolean b = equalPackage(dish, cartDish2);
				if (b) {
					changeDishQuantity(cartDish2, true);
					return true;
				}
			} else {
				/**
				 *1、对比定制项
				 */
				List<UITasteOption> dishOptionList     = dish.getOptionList();
				List<UITasteOption> cartdishOptionList = cartDish2.getOptionList();
				same = sameOption(dishOptionList, cartdishOptionList);
				if (same) {
					//					CartDish cartDishByDishId = getCartDishByDishId(cartDish.getDishID());
					cartDish2.setQuantity(cartDish2.getQuantity() + dish.getQuantity());
					mRxManager.post(AppConstant.ADD_DISH_TO_CART, dish);
					return true;
				} else
					continue;
				//				return sameOption;
			}
		}
		return same;
	}

	/**
	 * 判断两个商品的定制项是否相同
	 *
	 * @param dishOptionList
	 * @param cartdishOptionList
	 * @return
	 */
	private boolean sameOption(List<UITasteOption> dishOptionList, List<UITasteOption> cartdishOptionList) {

		if (dishOptionList == null && cartdishOptionList == null)
			return true;
		else if (dishOptionList != null && cartdishOptionList != null) {
			if (dishOptionList.size() != cartdishOptionList.size())
				return false;
			else {
				//接下来对比项是否相同子项
				boolean sameOptionName = sameOptionName(dishOptionList, cartdishOptionList);
				if (sameOptionName) {
					return sameOptionSize(dishOptionList, cartdishOptionList);
				} else {
					return sameOptionName;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * 同步时加料加数量
	 *
	 * @param dishOptionList
	 * @param cartdishOptionList
	 */
	private boolean sameOptionSize(List<UITasteOption> dishOptionList, List<UITasteOption> cartdishOptionList) {
		for (UITasteOption option : dishOptionList) {
			if (option.getQuantity() != getCartDishOptionSize(option.getId(), cartdishOptionList)) {
				return false;
			}
		}
		return true;
	}

	private int getCartDishOptionSize(String id, List<UITasteOption> cartdishOptionList) {
		for (UITasteOption option : cartdishOptionList) {
			if (option.getId().equals(id)) {
				return option.getQuantity();
			}
		}
		return 0;
	}

	private boolean sameOptionName(List<UITasteOption> dishOptionList, List<UITasteOption> cartdishOptionList) {
		List<String> dishOption     = new ArrayList<>();//新加的
		List<String> cartdishOption = new ArrayList<>();//购物车的
		for (UITasteOption item : cartdishOptionList) {
			cartdishOption.add(item.getId());
		}
		for (UITasteOption item : dishOptionList) {
			dishOption.add(item.getId());
		}

		for (String i : dishOption) {
			if (!cartdishOption.contains(i))
				return false;
		}
		return true;
	}

	/**
	 * 改变购物车中的菜品的数量
	 *
	 * @param dishId dishId
	 * @param isAdd  true 为增加， false为减少
	 */
	@Deprecated
	public void changeDishQuantity(String dishId, boolean isAdd) {
		if (Cart.getInstance().getCartDishes() == null || Cart.getInstance().getCartDishes()
				.size() == 0)
			return;
		Iterator<CartDish> iterator = Cart.getInstance().getCartDishes().iterator();
		while (iterator.hasNext()) {
			CartDish cartDish = iterator.next();
			if (cartDish.getDishID().equals(dishId)) {
				if (isAdd) {
					cartDish.setQuantity(cartDish.getQuantity() + 1);
					mRxManager.post(AppConstant.ADD_DISH_QUANTITY_FROM_CART, cartDish);
				} else {
					cartDish.setQuantity(cartDish.getQuantity() - 1);
					mRxManager.post(AppConstant.REDUCE_DISH_QUANTITY_FROM_CART, cartDish);
				}

			}
		}
	}

	/**
	 * 改变购物车中的菜品的数量
	 *
	 * @param isAdd true 为增加， false为减少
	 */
	public void changeDishQuantity(CartDish cartDish, boolean isAdd) {
		if (Cart.getInstance().getCartDishes() == null || Cart.getInstance().getCartDishes()
				.size() == 0)
			return;
		if (isAdd) {
			cartDish.setQuantity(cartDish.getQuantity() + 1);
			mRxManager.post(AppConstant.ADD_DISH_QUANTITY_FROM_CART, cartDish);
		} else {
			cartDish.setQuantity(cartDish.getQuantity() - 1);
			mRxManager.post(AppConstant.REDUCE_DISH_QUANTITY_FROM_CART, cartDish);
		}
	}

	/**
	 * 从购物车中删除
	 *
	 * @param cartDish
	 */
	public void removeDish(CartDish cartDish) {
		if (Cart.getInstance().getCartDishes() == null || Cart.getInstance().getCartDishes()
				.size() == 0)
			return;
		Cart.getInstance().getCartDishes().remove(cartDish);
		mRxManager.post(AppConstant.REMOVE_DISH_FROM_CART, cartDish);
	}

	/**
	 * 将Dish转化成购物车的CartDish
	 *
	 * @param dish
	 * @return
	 */
	private CartDish toCartDish(UIDish dish) {
		CartDish cartDish = new CartDish();
		cartDish.setDishName(dish.getDishName());
		cartDish.setDishID(dish.getDishID());
		cartDish.setSkuId(dish.getSkuId());
		cartDish.setKindId(dish.getDishKind());
		cartDish.setDishKindStr(dish.getDishKindStr());
		cartDish.setQuantity(dish.getQuantity());
		cartDish.setPrice(dish.getPrice());
		cartDish.setCost(Price.getInstance().getDishCost(dish));//这里应该是实收
		cartDish.setOptionList(dish.getOptionList());
		cartDish.setImageName(dish.getImageName());
		cartDish.setSubItemList(dish.getSubItemList());
		cartDish.setMemberPrice(dish.getMemberPrice());
		cartDish.setDishUnit(dish.getDishUnit());
		cartDish.setDishUnitID(dish.getDishUnitID());
		cartDish.setDealId(dish.getDealId());
		cartDish.setWaiDai_cost(dish.getWaiDai_cost());
		return cartDish;
	}


	/**
	 * 获取购物车商品的列表
	 *
	 * @return
	 */
	public List<CartDish> getCartDishes() {
		if (mCartDishList == null) {
			mCartDishList = new ArrayList<>();
		}
		return mCartDishList;
	}

	/**
	 * 清除购物车
	 */
	public void clear() {
		if (Cart.getInstance().getCartDishes() != null) {
			Cart.getInstance().getCartDishes().clear();
			mRxManager.post(AppConstant.CLEAR_CART, null);
		}
	}


	/**
	 * 获取购物车商品的数量
	 *
	 * @return
	 */
	public int getCartCount() {
		int total = 0;
		if (Cart.getInstance().getCartDishes() != null) {
			for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
				total += cartDish.getQuantity();
			}
		}
		return total;
	}


	/**
	 * 业务流水号,这里统一了payNo
	 */
	private String biz_id;


	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}


	public String getCheckDishStr() {
		List<CheckCountReq> arrayList = new ArrayList<>();
		for (CartDish item : Cart.getInstance().getCartDishes()) {
			if (item.getSubItemList() != null && item.getSubItemList().size() > 0) {
				for (UIPackageOptionItem packageBeanItem : item.getSubItemList()) {
					arrayList.add(new CheckCountReq(packageBeanItem.getDishID(),
							item.getQuantity()));
				}
			} else {
				arrayList.add(new CheckCountReq(item.getDishID(), item.getQuantity()));
			}
		}
		return new Gson().toJson(arrayList);
	}

	/**
	 * 这里还要讲cartdish转成同步时的item
	 *
	 * @return
	 */
	public ArrayList<SyncAcceptReq.DataBean.ItemsBean> getSyncAcceptItems() {
		ArrayList<SyncAcceptReq.DataBean.ItemsBean> beans = new ArrayList<>();
		for (CartDish dish : Cart.getInstance().getCartDishes()) {
			SyncAcceptReq.DataBean.ItemsBean bean = new SyncAcceptReq.DataBean.ItemsBean();
			bean.setSkuId(dish.getSkuId());
			bean.setSkuName(dish.getDishName());
			bean.setSalePrice(String.valueOf(Float.parseFloat(dish.getPrice())));
			bean.setQty(dish.getQuantity());
			bean.setSaleAmount(PriceUtil
					.multiply(String.valueOf(Float.parseFloat(dish.getPrice())), dish.getQuantity())
					.toString());
			bean.setSaleSubtotal(PriceUtil
					.multiply(String.valueOf(Float
							.parseFloat(Cart.getInstance().getCartDishPriceWithMix(dish))), dish
							.getQuantity())
					.toString());
			bean.setMemo("");
			setMixAndFeature(dish.getOptionList(), bean, dish);

			if (dish.getSubItemList() != null && dish.getSubItemList().size() > 0) {
				List<SyncAcceptReq.DataBean.ItemsBean.CombosBean> combosBeanList = new ArrayList<>();
				for (UIPackageOptionItem optionItem : dish.getSubItemList()) {
					SyncAcceptReq.DataBean.ItemsBean.CombosBean combosBean = new SyncAcceptReq.DataBean.ItemsBean.CombosBean();
					combosBean.setSkuId(optionItem.getDishID());
					combosBean.setQty(String.valueOf(optionItem.getQuantity()));
					combosBean.setSalePrice(optionItem.getPrice());
					combosBean.setSkuName(optionItem.getDishName());
					combosBean.setSaleAmount(PriceUtil
							.multiply(optionItem.getPrice(), optionItem.getQuantity())
							.toString());
					setMixAndFeature(optionItem.getOptionList(), combosBean, optionItem);
					combosBean.setMemo("");
					combosBean.setSaleSubtotal(PriceUtil
							.multiply(String.valueOf(Float
									.parseFloat(Cart.getInstance()
											.getSubItemPriceWithMix(optionItem))), optionItem
									.getQuantity())
							.toString());
					combosBeanList.add(combosBean);
				}
				bean.setCombos(combosBeanList);
			}
			beans.add(bean);
		}
		return beans;
	}

	private void setMixAndFeature(List<UITasteOption> list, SyncAcceptReq.DataBean.ItemsBean bean, CartDish dish) {
		List<SyncAcceptReq.DataBean.ItemsBean.MixsBean>     mixsBeenList     = new ArrayList<>();
		List<SyncAcceptReq.DataBean.ItemsBean.FeaturesBean> featuresBeanList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (UITasteOption option : list) {
				if ("M".equals(option.getSourceType())) {
					SyncAcceptReq.DataBean.ItemsBean.MixsBean mixsBean = new SyncAcceptReq.DataBean.ItemsBean.MixsBean();
					mixsBean.setMixId(option.getId());
					mixsBean.setMixName(option.getOptionName());
					mixsBean.setMixPrice((float) option.getPrice());
					mixsBean.setMixQty(option.getQuantity() * dish.getQuantity());
					mixsBean.setMixAmount(PriceUtil
							.multiply(String.valueOf(option.getPrice()), mixsBean.getMixQty())
							.floatValue());
					mixsBeenList.add(mixsBean);
				} else if ("F".equals(option.getSourceType())) {
					SyncAcceptReq.DataBean.ItemsBean.FeaturesBean featuresBean = new SyncAcceptReq.DataBean.ItemsBean.FeaturesBean();
					featuresBean.setFeatureId(option.getId());
					featuresBean.setFeatureName(option.getOptionName());
					featuresBeanList.add(featuresBean);
				} else if ("G".equals(option.getSourceType())) {
					bean.setSkuName(bean.getSkuName() + "(" + option.getOptionName() + ")");
				}
			}
		}
		bean.setMixs(mixsBeenList);
		bean.setFeatures(featuresBeanList);
	}

	private void setMixAndFeature(List<UITasteOption> list, SyncAcceptReq.DataBean.ItemsBean.CombosBean bean, UIPackageOptionItem optionItem) {
		List<SyncAcceptReq.DataBean.ItemsBean.MixsBean>     mixsBeenList     = new ArrayList<>();
		List<SyncAcceptReq.DataBean.ItemsBean.FeaturesBean> featuresBeanList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (UITasteOption option : list) {
				if ("M".equals(option.getSourceType())) {
					SyncAcceptReq.DataBean.ItemsBean.MixsBean mixsBean = new SyncAcceptReq.DataBean.ItemsBean.MixsBean();
					mixsBean.setMixId(option.getId());
					mixsBean.setMixName(option.getOptionName());
					mixsBean.setMixPrice((float) option.getPrice());
					mixsBean.setMixQty(option.getQuantity() * optionItem.getQuantity());
					mixsBean.setMixAmount(PriceUtil
							.multiply(String.valueOf(option.getPrice()), mixsBean.getMixQty())
							.floatValue());
					mixsBeenList.add(mixsBean);
				} else if ("F".equals(option.getSourceType())) {
					SyncAcceptReq.DataBean.ItemsBean.FeaturesBean featuresBean = new SyncAcceptReq.DataBean.ItemsBean.FeaturesBean();
					featuresBean.setFeatureId(option.getId());
					featuresBean.setFeatureName(option.getOptionName());
					featuresBeanList.add(featuresBean);
				} else if ("G".equals(option.getSourceType())) {
					bean.setSkuName(bean.getSkuName() + "(" + option.getOptionName() + ")");
				}
			}
		}
		bean.setMixs(mixsBeenList);
		bean.setFeatures(featuresBeanList);
	}

	private void setMixAndFeature(List<UITasteOption> list, SyncAcceptReq.DataBean.ItemsBean.CombosBean bean) {
		List<SyncAcceptReq.DataBean.ItemsBean.MixsBean>     mixsBeenList     = new ArrayList<>();
		List<SyncAcceptReq.DataBean.ItemsBean.FeaturesBean> featuresBeanList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (UITasteOption option : list) {
				if ("M".equals(option.getSourceType())) {
					SyncAcceptReq.DataBean.ItemsBean.MixsBean mixsBean = new SyncAcceptReq.DataBean.ItemsBean.MixsBean();
					mixsBean.setMixId(option.getId());
					mixsBean.setMixName(option.getOptionName());
					mixsBean.setMixPrice((float) option.getPrice());
					mixsBean.setMixQty(option.getQuantity());
					mixsBean.setMixAmount(PriceUtil
							.multiply(String.valueOf(option.getPrice()), option.getQuantity())
							.floatValue());
					mixsBeenList.add(mixsBean);
				} else if ("F".equals(option.getSourceType())) {
					SyncAcceptReq.DataBean.ItemsBean.FeaturesBean featuresBean = new SyncAcceptReq.DataBean.ItemsBean.FeaturesBean();
					featuresBean.setFeatureId(option.getId());
					featuresBean.setFeatureName(option.getOptionName());
					featuresBeanList.add(featuresBean);

				} else if ("G".equals(option.getSourceType())) {
					bean.setSkuName(bean.getSkuName() + "(" + option.getOptionName() + ")");
				}
			}
		}
		bean.setMixs(mixsBeenList);
		bean.setFeatures(featuresBeanList);
	}




	public String getCartDisheStr() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < Cart.getInstance().getCartDishes().size(); i++) {
			CartDish cartDish = Cart.getInstance().getCartDishes().get(i);
			if (i == Cart.getInstance().getCartDishes().size() - 1) {
				builder.append(cartDish.getDishName() + "*" + cartDish.getQuantity());
			} else {
				builder.append(cartDish.getDishName() + "*" + cartDish.getQuantity() + ";");
			}
		}
		return builder.toString();
	}

	public CartDish getCartDishById(String s) {
		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			if (cartDish.getDishID().equals(s)) {
				return cartDish;
			}
		}
		return null;
	}

	/**
	 * 购物车菜品中含有美团团购券id的套餐的个数
	 *
	 * @return
	 */
	public int getDealIdCount() {
		int count = 0;
		for (CartDish model : getCartDishes()) {
			if (model.getDealId() != null) {
				count += model.getQuantity();
			}
		}
		return count;
	}

	/**
	 * 判断是否包含和这个dealId
	 *
	 * @return
	 */
	public CartDish checkContainDealId(Integer dealId) {
		for (CartDish model : getCartDishes()) {
			if (model.getDealId() != null && String.valueOf(model.getDealId())
					.equals(String.valueOf(dealId))) {
				return model;
			}
		}
		return null;
	}
}
