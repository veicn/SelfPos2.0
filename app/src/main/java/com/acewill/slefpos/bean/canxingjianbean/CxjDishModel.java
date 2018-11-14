package com.acewill.slefpos.bean.canxingjianbean;

import java.io.Serializable;
import java.util.ArrayList;

public class CxjDishModel implements Serializable {
	public String dishUnit;
	public String dishUnitId;

	public CxjDishModel() {
	}


	public String type;
	public String name;
	public String alias;// 汉语拼音首字母，快速查找用
	public String price;//套餐加价
	/**
	 * 1：正常
	 */
	public String status;
	/**
	 * 门店ID
	 */
	public String rid;
	public String imageName;
	public String description;
	public String shortcutImageName;
	/**
	 * 菜品ID
	 */
	public String did;
	public String salePrice;
	public float  memberPrice;
	/**
	 * 类别ID
	 */
	public String cid;
	public int    icon;
	public int    count;

	/**
	 * 0：单品 1：套餐
	 */
	public int    diskKind;
	public String duid;
	/**
	 * 用户自由填写的备注
	 */
	public String remarks;
	/**
	 * 套餐标识 -1 为必选 ，>0组合多选
	 */
	public int    groupnum;

	/**
	 * 已选套餐
	 */
	public ArrayList<CxjDishModel>         setmeals;
	/**
	 * 套餐项
	 */
	public ArrayList<CxjSetmealGroupModel> setmealGroup;


	public ArrayList<CxjDishCookCategory> dishCookCategory;
	/**
	 * 套餐: 选择的位置
	 */
	public int setmealIndex = -1;
	public int selnum;
	public int bdefaultsetmeal;

	public String toString() {
		return "ptid = " + type + "&label = " + name + "alias = " + alias
				+ "&price=" + price + "&status = " + status + "&rid = " + rid
				+ "&imageName = " + imageName + "&did=" + did + "&cid = " + cid
				+ "&count = " + count;
	}

	public String getRemarks() {
		String text = "";
		return text;
	}

	public String getOmids() {
		String omids = "";
		return omids;
	}

}
