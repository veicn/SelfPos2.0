package com.acewill.slefpos.bean.cartbean;

import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/2/22 17:49
 * Desc：
 */
public class CartDishHelper {

	/**
	 * 有定制项
	 */
	public static boolean hasOption(CartDish cartDish) {
		UIDish dish = SmarantDataProvider.getDishById(cartDish.getDishID());
		if (dish.getOptionCategoryList() != null && dish.getOptionCategoryList()
				.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是套餐
	 *
	 * @param cartDish
	 */
	public static boolean isPackage(CartDish cartDish) {
		UIDish dish = SmarantDataProvider.getDishById(cartDish.getDishID());
		if (dish.getPackageItems() != null && dish.getPackageItems()
				.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取套餐的单品定制项的str
	 *
	 * @param list
	 * @return
	 */
	public static String getCartDishTasteOptionStr(List<UITasteOption> list) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			UITasteOption option = list.get(i);
			if (i == list.size() - 1) {
				if (option.getPrice() == 0) {
					builder.append(option.getOptionName()+ (option
							.getQuantity() == 1 ? "" : "x" + option
							.getQuantity()));
				} else {
					builder.append(option.getOptionName() + "(+￥" + option
							.getPrice() +(option
							.getQuantity() == 1 ? "" : "x" + option
							.getQuantity())+ ")");
				}
			} else {
				if (option.getPrice() == 0) {
					builder.append(option.getOptionName() + (option
							.getQuantity() == 1 ? "" : "x" + option
							.getQuantity())+ ";");
				} else {
					builder.append(option.getOptionName() + "(+￥" + option
							.getPrice() + (option
							.getQuantity() == 1 ? "" : "x" + option
							.getQuantity())+ ")" + ";");
				}
			}
		}
		return builder.toString();
	}
}
