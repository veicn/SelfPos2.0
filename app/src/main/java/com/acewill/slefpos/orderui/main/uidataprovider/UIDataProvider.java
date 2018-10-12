package com.acewill.slefpos.orderui.main.uidataprovider;

import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIDishKind;
import com.acewill.slefpos.bean.uibean.UIOptionCategory;
import com.acewill.slefpos.configure.SystemConfig;
import com.jaydenxiao.common.commonutils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/26 18:09
 * Desc：
 */
public class UIDataProvider {

	/**
	 * 每一个后面通过id去获取的dish建议都通过克隆的方式获取
	 */
	public static List<UIDish> sUIDishList;

	public static List<UIDish> getUIDish(boolean needRefresh) {
		if (sUIDishList != null) {
			return sUIDishList;
		} else {
			int type = SPUtils.getSharedIntData(BaseApplication.getContext(), "baseInit");
			switch (type) {
				case SystemConfig.System_Smarant:
					sUIDishList = SmarantDataProvider.getSmarantData(needRefresh);
					break;
				case SystemConfig.System_Sync:
					sUIDishList = SyncDataProvider.getSyncData();
					break;
				case SystemConfig.System_CanXingJian:
					sUIDishList = CXJDataProvider.getCanXingJianData();
				default:
					break;
			}
		}
		return sUIDishList;
	}

	private static List<UIDishKind> uiDishKinds;

	public static List<UIDishKind> getUIDishKind() {
		if (uiDishKinds != null) {
			for (UIDishKind kind : uiDishKinds) {
				kind.setSelect(false);
				kind.setSelectCount(0);
			}
			return uiDishKinds;
		}
		int type = SPUtils.getSharedIntData(BaseApplication.getContext(), "baseInit");
		switch (type) {
			case SystemConfig.System_Smarant:
				uiDishKinds = SmarantDataProvider.getSmarantKindData();
				break;
			case SystemConfig.System_Sync:
				uiDishKinds = SyncDataProvider.getSyncKindData();
				break;
			case SystemConfig.System_CanXingJian:
				uiDishKinds = CXJDataProvider.getCanXingJianKindData();
				break;
		}

		return uiDishKinds;
	}


	public static List<UIDish> getDishListByKindId(String kindId, String name) {
		List<UIDish> data     = UIDataProvider.getUIDish(false);
		List<UIDish> dishList = new ArrayList<>();
		if (data != null && data.size() > 0) {
			for (UIDish dish : data) {
				if (String.valueOf(kindId).equals(dish.getDishKind())) {
					/**
					 * 初始化数据
					 */
					dish.setDishKindStr(name);
					dish.setQuantity(1);
					if (dish.isVisible())
						dishList.add(dish);
				}
			}
		}
		return dishList;
	}

	/*
	通过UIDishId 获取Dish
	 */
	public static UIDish getDishByDishId(String id) {
		if (sUIDishList == null)
			return null;
		for (UIDish uiDish : sUIDishList) {
			if (uiDish.getDishID().equals(id)) {
				return uiDish.myclone();
			}
		}
		return null;
	}

	/*
	判断UIdish有没有定制项
	 */
	public static boolean isDishHaveOption(String id) {
		if (sUIDishList == null)
			return false;
		for (UIDish uiDish : sUIDishList) {
			if (uiDish.getDishID().equals(id)) {
				if (uiDish.getOptionCategoryList() != null && uiDish.getOptionCategoryList()
						.size() > 0)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static boolean isDishIsPackage(String id) {
		if (sUIDishList == null)
			return false;
		for (UIDish uiDish : sUIDishList) {
			if (uiDish.getDishID().equals(id)) {
				if (uiDish.getPackageItems() != null && uiDish.getPackageItems().size() > 0)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static List<UIOptionCategory> getUIDIshOptionCategoryById(String id) {
		if (sUIDishList == null)
			return null;
		List<UIOptionCategory> list = new ArrayList<>();
		for (UIDish uiDish : sUIDishList) {
			if (uiDish.getDishID().equals(id)) {
				if (uiDish.getOptionCategoryList() != null && uiDish.getOptionCategoryList()
						.size() > 0) {
					List<UIOptionCategory> list1 = uiDish.getOptionCategoryList();
					for (UIOptionCategory category : list1) {
						list.add(category.myclone());
					}
					return list;
				} else
					return null;
			}
		}
		return null;
	}


}
