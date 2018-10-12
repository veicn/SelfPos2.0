package com.acewill.slefpos.bean.canxingjianbean;

import java.util.ArrayList;

public class CxjSetmealGroupModel {
	/**
	 * 需要选择的数量
	 */
	public int                     max;
	/**
	 * 已选的数量
	 */
	public int                     quantity;
	/**
	 * 分组id
	 */
	public int                     groupnum;
	/**
	 * 所属套餐id
	 */
	public String                  maindid;
	/**
	 * 套餐项
	 */
	public ArrayList<CxjDishModel> setmeals;
}
