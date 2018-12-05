package com.acewill.slefpos.orderui.main.uidataprovider;

import android.text.TextUtils;

import com.acewill.slefpos.bean.smarantbean.Dish;
import com.acewill.slefpos.bean.smarantbean.DishKind;
import com.acewill.slefpos.bean.smarantbean.DishKindData;
import com.acewill.slefpos.bean.smarantbean.DishMenu;
import com.acewill.slefpos.bean.smarantbean.OptionCategory;
import com.acewill.slefpos.bean.smarantbean.PackageItem;
import com.acewill.slefpos.bean.smarantbean.PackageOptionItem;
import com.acewill.slefpos.bean.smarantbean.Recommand;
import com.acewill.slefpos.bean.smarantbean.TasteOption;
import com.acewill.slefpos.bean.smarantstorebean.ImagesData;
import com.acewill.slefpos.bean.smarantstorebean.KdsData;
import com.acewill.slefpos.bean.smarantstorebean.KichenStallsData;
import com.acewill.slefpos.bean.smarantstorebean.MarketData;
import com.acewill.slefpos.bean.smarantstorebean.PayTypeData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterData;
import com.acewill.slefpos.bean.smarantstorebean.PrinterTemplatesData;
import com.acewill.slefpos.bean.smarantstorebean.SelfPosConfigurationData;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIDishKind;
import com.acewill.slefpos.bean.uibean.UIOptionCategory;
import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UIRecommand;
import com.acewill.slefpos.bean.uibean.UITasteOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/29 11:22
 * Desc：所有使用到的数据都保存在这里
 */
public class SmarantDataProvider {
	private static MarketData               marketList;
	private static PrinterData              printerList;
	private static PayTypeData              payTypeList;
	private static ImagesData               imageList;
	private static KichenStallsData         kichenStalls;
	private static KdsData                  kdsInfo;
	private static SelfPosConfigurationData selfposConfigurationdata;
	private static DishKindData             dishKindData;
	private static PrinterTemplatesData     printerTemplatesData;
	private static DishMenu                 dishMenu;

	public static DishKindData getDishKindData() {
		return dishKindData;
	}

	public static void setDishKindData(DishKindData dishKindData) {
		SmarantDataProvider.dishKindData = dishKindData;
	}

	public static DishMenu getDishMenu() {
		return dishMenu;
	}

	public static void setDishMenu(DishMenu dishMenu) {
		SmarantDataProvider.dishMenu = dishMenu;
	}


	public static MarketData getMarketList() {
		return marketList;
	}

	public static void setMarketList(MarketData marketList) {
		SmarantDataProvider.marketList = marketList;
	}

	public static PrinterData getPrinterList() {
		return printerList;
	}

	public static void setPrinterList(PrinterData printerList) {
		SmarantDataProvider.printerList = printerList;
	}

	public static PayTypeData getPayTypeList() {
		return payTypeList;
	}

	public static void setPayTypeList(PayTypeData payTypeList) {
		SmarantDataProvider.payTypeList = payTypeList;
	}

	public static ImagesData getImageList() {
		return imageList;
	}

	public static void setImageList(ImagesData imageList) {
		SmarantDataProvider.imageList = imageList;
	}

	public static KichenStallsData getKichenStalls() {
		return kichenStalls;
	}

	public static void setKichenStalls(KichenStallsData kichenStalls) {
		SmarantDataProvider.kichenStalls = kichenStalls;
	}

	public static KdsData getKdsInfo() {
		return kdsInfo;
	}

	public static void setKdsInfo(KdsData kdsInfo) {
		SmarantDataProvider.kdsInfo = kdsInfo;
	}

	public static SelfPosConfigurationData getSelfposConfigurationdata() {
		return selfposConfigurationdata;
	}

	public static void setSelfposConfigurationdata(SelfPosConfigurationData selfposConfigurationdata) {
		SmarantDataProvider.selfposConfigurationdata = selfposConfigurationdata;
	}


	private static List<UIDish> sUIDishList;


	//	private static List<UIDish> getSyncData() {
	//		if (sUIDishList != null)
	//			return sUIDishList;
	//		else {
	//			getSmarantData();
	//		}
	//		return null;
	//	}

	static List<UIDish> getSmarantData(boolean needRefresh) {
		if (sUIDishList != null && !needRefresh)
			return sUIDishList;
		else {
			List<Dish> data = SmarantDataProvider.getDishMenu().getMenuData().get(0)
					.getDishData();
			sUIDishList = new ArrayList<>();
			if (data != null && data.size() > 0) {
				sUIDishList = new ArrayList<>();
				for (Dish dish : data) {
					UIDish uiDish = new UIDish();
					uiDish.setDishName(dish.getDishName());
					uiDish.setDishID(dish.getDishID() + "");
					uiDish.setDishKind(dish.getDishKind());
					String kindNameStr = getKindStrByKindId(Integer.parseInt(dish.getDishKind()));
					uiDish.setDishKindStr(kindNameStr);
					uiDish.setRecommandList(getUIRecommanList(dish.getRecommandList()));
					uiDish.setQuantity(dish.getQuantity());
					uiDish.setDishCount(dish.getDishCount());
					uiDish.setDishUnit(dish.getDishUnit());
					uiDish.setPrice(String.valueOf(dish.getPrice()));
					uiDish.setOptionCategoryList(toUIOptionCategory(dish.getOptionCategoryList()));
					uiDish.setOptionList(toUITasteOption(dish.getOptionList()));
					uiDish.setPackageItems(toUIPackageItem(dish.getPackageItems(),kindNameStr));
					uiDish.setImageName(dish.getImageName());
					uiDish.setWaiDai_cost(dish.getWaiDai_cost());
					uiDish.setSubItemList(null);
					uiDish.setDealId(dish.getDealId());
					uiDish.setVisible(true);
					uiDish.setMemberPrice(String.valueOf(dish.getMemberPrice()));
					sUIDishList.add(uiDish);
				}
			}
		}
		return sUIDishList;
	}


	private static String getKindStrByKindId(int kindId) {
		if (dishKindData == null)
			return null;
		for (DishKind kind : dishKindData.getDishKindData()) {
			if (kind.getKindID() == kindId)
				return kind.getKindName();
		}
		return null;
	}

	private static List<UIRecommand> getUIRecommanList(List<Recommand> list) {
		if (list == null)
			return null;
		List<UIRecommand> list1 = new ArrayList<>();
		for (Recommand recommand : list) {
			UIRecommand recommand1 = new UIRecommand();
			recommand1.setImageName(recommand.getImageName());
			recommand1.setDescription(recommand.getDescription());
			recommand1.setMemberPrice(recommand.getMemberPrice());
			recommand1.setPackageid(recommand.getPackageid());
			recommand1.setPackageName(recommand.getPackageName());
			recommand1.setPrice(recommand.getPrice());
			recommand1.setSpecialPrice(recommand.getSpecialPrice());
			list1.add(recommand1);
		}
		return list1;
	}

	private static List<UIPackageItem> toUIPackageItem(List<PackageItem> items, String kindNameStr) {
		if (items == null) {
			return null;
		}
		List<UIPackageItem> uiPackageItems = new ArrayList<>();
		for (PackageItem packageItem : items) {
			UIPackageItem uiPackageItem = new UIPackageItem(packageItem.getItemName(), String
					.valueOf(packageItem
							.getItemID()),
					packageItem.getItemType(), packageItem.getItemCount(), packageItem
					.getItemCount(), packageItem
					.getIsMust(), toUIPackageOptionItem(packageItem
					.getOptions()), packageItem.isExpanded, packageItem
					.getSelectCount());
			uiPackageItem.setDishKindStr(kindNameStr);
			if (!TextUtils.isEmpty(packageItem.getUserdefinedName()))
			uiPackageItem.setUserdefinedName(packageItem.getUserdefinedName() + "(" + packageItem
					.getOptions().size() + "选" + packageItem.getItemCount() + ")");
			uiPackageItems.add(uiPackageItem);
		}
		return uiPackageItems;
	}

	private static List<UIPackageOptionItem> toUIPackageOptionItem(List<PackageOptionItem> options) {
		if (options == null)
			return null;
		List<UIPackageOptionItem> uiPackageOptionItems = new ArrayList<>();
		for (PackageOptionItem item : options) {
			UIPackageOptionItem item1 = new UIPackageOptionItem(String
					.valueOf(item.getItemID()), item
					.getDishName(), String.valueOf(item.getDishID()), String
					.valueOf(item.getPrice())
					, String.valueOf(item.getItemPrice()), item.getCount(), item.getUnitID(), item
					.getUnit(), item
					.getImageName(), item.getDishKind(), item.getPrinterStr());
			item1.setExtraCost(item.getExtraCost());
			uiPackageOptionItems.add(item1);
		}
		return uiPackageOptionItems;
	}

	/**
	 * 将smarant的数据转换成界面需要的数据
	 *
	 * @param list1
	 * @return List<UIOptionCategory> 某个UIDish定制项分类列表
	 */
	private static List<UIOptionCategory> toUIOptionCategory(List<OptionCategory> list1) {
		List<UIOptionCategory> categories = new ArrayList<>();
		if (list1 == null)
			return null;
		for (OptionCategory category : list1) {
			UIOptionCategory category1 = new UIOptionCategory(String
					.valueOf(category.getId()), category
					.getOptionCategoryName(), category.getMinimalOptions(),
					category.isMultipleOptions(), category
					.getMaximalOptions(), toUITasteOption(category
					.getOptionList()), "");
			categories.add(category1);
		}
		return categories;
	}

	/**
	 * 将smarant的数据转换成界面需要的数据
	 *
	 * @param list
	 * @return List<UITasteOption> 某个UIOptionCategory的小类目
	 */
	private static List<UITasteOption> toUITasteOption(List<TasteOption> list) {
		List<UITasteOption> uiTasteOptions = new ArrayList<>();
		if (list == null)
			return null;
		for (TasteOption tasteOption : list) {
			UITasteOption option = new UITasteOption("", 0, String
					.valueOf(tasteOption.getId()), tasteOption
					.getOptionName(), tasteOption.getPrice(), tasteOption.getRequired()
					, String.valueOf(tasteOption.getCategoryId()), "S", "");
			uiTasteOptions.add(option);
		}
		return uiTasteOptions;
	}

	public static UIDish getDishById(String dishId) {
		for (UIDish dish : UIDataProvider.getUIDish(false)) {
			if (dish.getDishID().equals(dishId)) {
				return dish;
			}
		}
		return null;
	}

	/*public static Dish getDishById(int dishId) {
		for (Dish dish : getUIDish()) {
			if (dish.getDishID() == dishId)
				return dish;
		}
		return null;
	}*/


	public static List<UIDishKind> getSmarantKindData() {
		List<DishKind> list = SmarantDataProvider.getDishKindData().getDishKindData();
		Collections.sort(list, new Comparator<DishKind>() {

			@Override
			public int compare(DishKind o1, DishKind o2) {
				int i = o1.getSeq() - o2.getSeq();
				return i;
			}
		});
		List<UIDishKind> uidishKinds = new ArrayList<>();
		for (DishKind kind : list) {
			UIDishKind uiDishKind = new UIDishKind(String.valueOf(kind.getKindID()), kind
					.getKindName(), kind.getImageName());
			uidishKinds.add(uiDishKind);
		}
		return uidishKinds;
	}


	public static void setPrinterTemplatesData(PrinterTemplatesData data) {
		SmarantDataProvider.printerTemplatesData = data;
	}

	public static PrinterTemplatesData getPrinterTemplatesData() {
		return printerTemplatesData;
	}


}
