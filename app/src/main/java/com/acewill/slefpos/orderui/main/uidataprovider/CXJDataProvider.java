package com.acewill.slefpos.orderui.main.uidataprovider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.acewill.slefpos.bean.canxingjianbean.CxjDishCook;
import com.acewill.slefpos.bean.canxingjianbean.CxjDishCookCategory;
import com.acewill.slefpos.bean.canxingjianbean.CxjDishModel;
import com.acewill.slefpos.bean.canxingjianbean.CxjKindModel;
import com.acewill.slefpos.bean.canxingjianbean.CxjMenuResp;
import com.acewill.slefpos.bean.canxingjianbean.CxjSaleOutDish;
import com.acewill.slefpos.bean.canxingjianbean.CxjSetmealGroupModel;
import com.acewill.slefpos.bean.canxingjianbean.CxjTasteModel;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIDishKind;
import com.acewill.slefpos.bean.uibean.UIOptionCategory;
import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.jaydenxiao.common.compressorutils.FileUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Author：Anch
 * Date：2018/7/31 9:59
 * Desc：
 */
public class CXJDataProvider {
	private static final String TAG = "CanXingJianDataProvider";
	public static  CxjSaleOutDish        saleOutDish;
	private static ArrayList<UIDish>     uiDishList_in;
	private static ArrayList<UIDish>     uiDishList_out;
	private static ArrayList<UIDishKind> uiDishKindList;
	public static Map<String, String> tableNoMap = new HashMap<String, String>();

	private static String getSaleOutDishDids() {
		if (saleOutDish == null) {
			return null;
		}
		if (!TextUtils.isEmpty(saleOutDish.getDids())) {
			return saleOutDish.getDids();
		}
		if (saleOutDish.getData() != null && saleOutDish.getData().size() > 0) {
			String dids = "";
			for (int i = 0; i < saleOutDish.getData().size(); i++) {
				dids += saleOutDish.getData().get(i).getDid() + ",";
			}
			return dids.substring(0, dids.length() - 1);
		}
		return null;
	}

	public static CxjMenuResp initMenuResp() {
		CxjMenuResp    result = new CxjMenuResp();
		SQLiteDatabase db     = null;
		Cursor         cursor = null;
		try {
			// 以只读方式打开数据库
			db = SQLiteDatabase.openDatabase(FileUtil
							.getCanXingJianFoldPath() + File.separator + "menu.db", null,
					SQLiteDatabase.OPEN_READONLY);

			// 菜品类别
			cursor = db
					.rawQuery(
							"select * from o_dish_kind where dkid > 0 order by seq ",
							null);

			while (cursor.moveToNext()) {
				int id = cursor.getInt(cursor.getColumnIndex("dkid"));
				if (id <= 0) {
					continue;
				}

				CxjKindModel category = new CxjKindModel();
				category.cid = id + "";

				String cname = cursor.getString(cursor
						.getColumnIndex("dishkind"));

				category.name = encodeSqlliteString(cname);
				int seq = cursor.getInt(cursor.getColumnIndex("seq"));

				result.categoryList.add(category);
			}
			List<UIDishKind> uiDishKinds = formartKind(result.categoryList);


			//存储不可用的菜
			Map<String, Integer> dishCounterForCIDMap = new HashMap<String, Integer>();

			cursor = db
					.rawQuery(
							"select dkid from o_dish_kind where dkid < 0  ",
							null);
			ArrayList<Integer> dkids = new ArrayList<Integer>();
			StringBuilder      sb    = new StringBuilder();
			while (cursor.moveToNext()) {
				dkids.add(cursor.getInt(0));
				sb.append(cursor.getString(0) + ",");
			}
			String substring = "";
			if (sb.length() > 0) {

				substring = sb.substring(0, sb.length() - 1);
			}
			// if (!TextUtils.isEmpty(saleOutDishModel.dids)) {
			// String[] dids = saleOutDishModel.dids.split(",");
			// for (String did : dids) {
			// DishDataController.deleteDish(did);
			// }
			// }
			// if (saleOutDishModel.data != null) {
			// for (Dish dish : saleOutDishModel.data) {
			//
			// DishDataController.deleteDish(dish.did);
			// }
			// }

			String dishSql = "select d.*,m.price,m.memberprice,m.duid,u.dishunit  from o_dish as d,o_menu_dish as m,o_dish_unit as u where not d.dish like '%特价%' and not d.dish like '%?%' and d.did = m.did and m.duid = u.duid and d.status != 2 and not dkid in ("
					+ substring + ") ";
			String saleOutDishDids = getSaleOutDishDids();
			if (saleOutDishDids != null) {
				dishSql += " and not d.did in (" + saleOutDishDids + ")";
			}


			//所有的做法大类数据
			ArrayList<CxjDishCookCategory> categoriesList = new ArrayList<>();

			//找做法的小类
			String sql2 = "select * from o_cook_kind order by ckid asc ";
			Cursor rawQuery2 = db.rawQuery(sql2,
					new String[0]);
			while (rawQuery2.moveToNext()) {
				CxjDishCookCategory category = new CxjDishCookCategory();
				category.cookCategeryId = rawQuery2.getInt(rawQuery2.getColumnIndex("ckid")) + "";
				category.cookCategeryName = rawQuery2
						.getString(rawQuery2.getColumnIndex("cookkind"));
				categoriesList.add(category);
			}

			// 菜品项
			cursor = db.rawQuery((dishSql + " order by dkid,seq").replace("?", "堂食"),
					new String[0]);
			setDish(cursor, db, result, dishCounterForCIDMap, 1, categoriesList);

			formartDataForCxjpos(result.dishList_out, 1);
			cursor = db.rawQuery((dishSql + " order by dkid,seq").replace("?", "外带"),
					new String[0]);
			setDish(cursor, db, result, dishCounterForCIDMap, 0, categoriesList);
			formartDataForCxjpos(result.dishList, 0);
			// 菜品备注/风味
			cursor = db.rawQuery("select * from o_order_memo where omkid > 0",
					new String[0]);

			while (cursor.moveToNext()) {
				CxjTasteModel oneTaste = new CxjTasteModel();

				String tasteId = cursor
						.getString(cursor.getColumnIndex("omid"));
				oneTaste.id = encodeSqlliteString(tasteId);

				String tasteName = cursor.getString(cursor
						.getColumnIndex("ordermemo"));
				oneTaste.label = encodeSqlliteString(tasteName);
				if (!oneTaste.label.equals("外带")) {
					result.tasteList.add(oneTaste);
				}

				System.out.println("got one taste " + oneTaste);
			}

			for (Iterator<CxjKindModel> it = result.categoryList.iterator(); it
					.hasNext(); ) {
				String cid = it.next().cid;
				if (!dishCounterForCIDMap.containsKey(cid)) {
					it.remove();
				}
			}

			cursor = db.rawQuery(
					"select tid,tablename from o_table  where status = 1",
					new String[0]);
			while (cursor.moveToNext()) {
				String tid       = cursor.getString(0);
				String tableName = cursor.getString(1);
				tableNoMap.put(tableName, tid);
			}
			if (uiDishKindList != null) {
				uiDishKindList.clear();
			} else {
				uiDishKindList = new ArrayList<>();
			}
			//过滤没有菜品的小类
			Iterator<UIDishKind> iterator = uiDishKinds.iterator();
			while (iterator.hasNext()) {
				UIDishKind kind = iterator.next();
				List<UIDish> dishList = UIDataProvider
						.getDishListByKindId(kind.getKindID(), kind.getKindName());
				if (dishList != null && dishList.size() > 0) {
					uiDishKindList.add(kind);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
				cursor.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}


		return result;
	}


	//	public static HashMap<String, List<UIDish>> dishSparese_in  = new HashMap<String, List<UIDish>>();
	//	public static HashMap<String, List<UIDish>> dishSparese_out = new HashMap<String, List<UIDish>>();

	private static void formartDataForCxjpos(List<CxjDishModel> cxjDishModels,
	                                         int takeout) {
		ArrayList<UIDish> list = new ArrayList<UIDish>();
		//		String            cid  = cxjDishModels.get(0).cid;
		//		if (takeout == 0) {
		//			dishSparese_in.put(cid, list);
		//		} else {
		//			dishSparese_out.put(cid, list);
		//		}
		for (CxjDishModel cxjDishModel : cxjDishModels) {
			//			if (!cxjDishModel.cid.equals(cid)) {
			//				cid = cxjDishModel.cid;
			//				list = new ArrayList<UIDish>();
			//				if (takeout == 0) {
			//					dishSparese_in.put(cid, list);
			//				} else {
			//					dishSparese_out.put(cid, list);
			//				}
			//			}
			UIDish uiDish = new UIDish();
			uiDish.setDishID(String.valueOf(cxjDishModel.did));
			uiDish.setDishKind(String.valueOf(cxjDishModel.cid));
			uiDish.setDishName(cxjDishModel.name);
			uiDish.setPrice(cxjDishModel.price);
			uiDish.setMemberPrice(String.valueOf(cxjDishModel.memberPrice));
			uiDish.setImageName(FileUtil
					.getCanXingJianImagePath(cxjDishModel.imageName));
			uiDish.setDishUnitID(Integer.parseInt(cxjDishModel.dishUnitId));
			uiDish.setDishUnit(cxjDishModel.dishUnit);
			uiDish.setDishCount(Integer.MAX_VALUE);
			//			dishModel.optionCategoryList
			ArrayList<UIOptionCategory> categories = new ArrayList<>();
			for (CxjDishCookCategory category : cxjDishModel.dishCookCategory) {
				UIOptionCategory         category1 = new UIOptionCategory();
				ArrayList<UITasteOption> beenList  = new ArrayList<>();
				for (CxjDishCook cook : category.cxjDishCookList) {
					UITasteOption bean = new UITasteOption();
					bean.setPrice(cook.cookPrice);
					bean.setOptionName(cook.cookName);
					bean.setCategoryId(cook.cookcategoryId);
					bean.setRequired(false);
					bean.setId(cook.cookid);
					bean.setSourceType("M");
					beenList.add(bean);
				}

				category1.setOptionList(beenList);
				category1.setMaximalOptions(beenList.size());
				category1.setMinimalOptions(0);
				category1.setId(category.cookCategeryId);
				category1.setOptionCategoryName(category.cookCategeryName);
				categories.add(category1);
			}
			uiDish.setOptionCategoryList(categories);
			uiDish.setIsPackage(cxjDishModel.diskKind);
			if (cxjDishModel.diskKind == 1) {
				ArrayList<UIPackageItem> packageItems = new ArrayList<>();
				for (CxjSetmealGroupModel setmealGroupModel : cxjDishModel.setmealGroup) {
					List<UIPackageOptionItem> list1 = new ArrayList<>();
					for (CxjDishModel setmeal : setmealGroupModel.setmeals) {
						UIPackageOptionItem item = new UIPackageOptionItem(String
								.valueOf(setmealGroupModel.groupnum), setmeal.name, setmeal.did, setmeal.price, setmeal.price, Integer.MAX_VALUE, Integer
								.parseInt(setmeal.dishUnitId), setmeal.dishUnit, "", String
								.valueOf(cxjDishModel.diskKind), "");
						item.setExtraCost(Float.parseFloat(setmeal.price));
						item.setImageName(FileUtil.getCanXingJianImagePath(setmeal.did));
						list1.add(item);
					}
					UIPackageItem uiPackageItem = new UIPackageItem(getUIPackageItemName(list1
							.size(), setmealGroupModel.max), String
							.valueOf(setmealGroupModel.groupnum),
							list1.size() == 1 ? 1 : 0, setmealGroupModel.max, setmealGroupModel.max, list1
							.size() == setmealGroupModel.max ? 1 : 0, list1, true, 0);
					packageItems.add(uiPackageItem);
				}
				uiDish.setPackageItems(packageItems);
			}
			uiDish.setVisible(true);
			list.add(uiDish);
		}
		if (takeout == 0)
			uiDishList_in = list;
		else
			uiDishList_out = list;
	}

	private static String[] arrary = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "九"};

	private static String getUIPackageItemName(int size, int max) {
		return arrary[size] + "选" + arrary[max];
	}

	private final static String setmealSql = "SELECT distinct s.maindid,s.subdid,s.groupnum,s.selnum,s.addmoney,"
			+ "d.dish,s.bdefaultsetmeal,d.status,s.duid,s.amount FROM o_setmeal as s,o_dish as d where s.subdid == d.did and s.maindid = ? order by groupnum ";

	private static void setDish(Cursor cursor, SQLiteDatabase db,
	                            CxjMenuResp result, Map<String, Integer> dishCounterForCIDMap,
	                            int takeOut, ArrayList<CxjDishCookCategory> categoriesList) {
		while (cursor.moveToNext()) {
			CxjDishModel oneDish = new CxjDishModel();

			String dkid = cursor.getString(cursor.getColumnIndex("dkid"));
			oneDish.cid = encodeSqlliteString(dkid);

			oneDish.memberPrice = cursor.getFloat(cursor.getColumnIndex("memberprice"));
			String did = cursor.getString(cursor.getColumnIndex("did"));
			oneDish.did = encodeSqlliteString(did);

			String dish = cursor.getString(cursor.getColumnIndex("dish"));
			oneDish.name = encodeSqlliteString(dish.replace("堂食", "")
					.replace("外带", "").replace("（）", ""));

			String alias = cursor.getString(cursor.getColumnIndex("alias"));
			oneDish.alias = encodeSqlliteString(alias);

			oneDish.imageName = oneDish.did;
			oneDish.price = cursor.getString(cursor.getColumnIndex("price"));


			//			oneDish.memberPrice = cursor.getFloat(cursor
			//					.getColumnIndex("memberprice"));
			oneDish.status = cursor.getString(cursor.getColumnIndex("status"));
			oneDish.dishUnitId = cursor.getString(cursor.getColumnIndex("duid"));
			oneDish.dishUnit = cursor.getString(cursor
					.getColumnIndex("dishunit"));

			int dishtype = cursor.getInt(cursor.getColumnIndex("dishtype"));
			oneDish.diskKind = dishtype - 1;// status+"";


			//做法小类数据
			String        cids    = cursor.getString(cursor.getColumnIndex("cids"));//"1,2,3";
			String[]      split   = cids.split(",");
			String        sql;
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < split.length; i++) {
				if (i != split.length - 1)
					builder.append(split[i] + ",");
				else
					builder.append(split[i]);
			}
			sql = "select * from o_cook where cid in (" + builder.toString() + ")";

			Cursor rawQuery1 = db.rawQuery(sql,
					new String[0]);
			oneDish.dishCookCategory = new ArrayList<>();
			ArrayList<CxjDishCookCategory> categoriesLis2t = categoriesList;
			ArrayList<CxjDishCook>         dishCookList    = new ArrayList<>();
			while (rawQuery1.moveToNext()) {
				CxjDishCook cook = new CxjDishCook();
				cook.cookid = rawQuery1.getInt(rawQuery1.getColumnIndex("cid")) + "";
				cook.cookcategoryId = rawQuery1.getInt(rawQuery1.getColumnIndex("ckid")) + "";
				cook.cookName = rawQuery1.getString(rawQuery1.getColumnIndex("cook"));
				cook.cookPrice = rawQuery1.getDouble(rawQuery1.getColumnIndex("price"));
				dishCookList.add(cook);
			}
			rawQuery1.close();

			for (CxjDishCookCategory category : categoriesLis2t) {
				CxjDishCookCategory category1 = new CxjDishCookCategory();
				for (CxjDishCook cook : dishCookList) {
					if (category.cookCategeryId.equals(cook.cookcategoryId)) {
						category1.cookCategeryId = category.cookCategeryId;
						category1.cookCategeryName = category.cookCategeryName;
						category1.cxjDishCookList.add(cook);
					}
				}
				if (category1.cxjDishCookList != null && category1.cxjDishCookList.size() > 0)
					oneDish.dishCookCategory.add(category1);
			}
			int leftcount = cursor.getInt(cursor.getColumnIndex("leftamount"));

			if (oneDish.diskKind == 1) {
				oneDish.memberPrice = Float.parseFloat(oneDish.price);
				Cursor rawQuery = db.rawQuery(setmealSql,
						new String[]{oneDish.did});
				//				PackageItemBean
				oneDish.setmealGroup = new ArrayList<CxjSetmealGroupModel>();//
				CxjSetmealGroupModel setmealGroup = new CxjSetmealGroupModel();
				setmealGroup.groupnum = -100;
				while (rawQuery.moveToNext()) {
					String maindid  = rawQuery.getString(0);
					int    groupnum = rawQuery.getInt(2);
					if (groupnum != setmealGroup.groupnum) {
						setmealGroup = new CxjSetmealGroupModel();
						oneDish.setmealGroup.add(setmealGroup);
						setmealGroup.maindid = maindid;
						setmealGroup.groupnum = groupnum;
						setmealGroup.max = rawQuery.getInt(3);
						setmealGroup.setmeals = new ArrayList<CxjDishModel>();

					}

					CxjDishModel dishModel = new CxjDishModel();
					dishModel.did = rawQuery.getString(1);
					dishModel.price = rawQuery.getString(4);
					dishModel.name = rawQuery.getString(5);
					dishModel.bdefaultsetmeal = rawQuery.getInt(6);
					dishModel.status = rawQuery.getString(7);
					dishModel.dishUnitId = rawQuery.getString(8);
					dishModel.selnum = rawQuery.getInt(9);
					setmealGroup.setmeals.add(dishModel);
				}
			}

			if (oneDish.status.equalsIgnoreCase("1")) {
				if (takeOut == 0) {
					result.dishList.add(oneDish);
				} else {
					result.dishList_out.add(oneDish);
				}
				dishCounterForCIDMap.put(oneDish.cid, null);
			}

		}
	}

	private static List<UIDishKind> formartKind(List<CxjKindModel> list) {
		List<UIDishKind> uiDishKinds = new ArrayList<>();
		for (CxjKindModel cxjKindModel : list) {
			UIDishKind dishKind = new UIDishKind();
			dishKind.setKindID(cxjKindModel.cid);
			dishKind.setKindName(cxjKindModel.name);
			dishKind.setImageName(FileUtil
					.getCanXingJianImagePath(cxjKindModel.name));
			uiDishKinds.add(dishKind);
		}
		return uiDishKinds;
	}


	private static String encodeSqlliteString(String str) {
		try {
			return new String(str.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static List<UIDish> getCanXingJianData() {
		int takeOut = Order.getInstance().getTakeOut();
		if (takeOut == 0) {
			return uiDishList_in;
		} else if (takeOut == 1) {
			return uiDishList_out;
		}
		return null;
	}

	public static List<UIDishKind> getCanXingJianKindData() {
		return uiDishKindList;
	}


}
