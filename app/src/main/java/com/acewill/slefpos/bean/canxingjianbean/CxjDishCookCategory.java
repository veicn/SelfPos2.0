package com.acewill.slefpos.bean.canxingjianbean;

import java.util.ArrayList;

/**
 * Author：Anch
 * Date：2017/11/15 15:50
 * Desc：
 */
public class CxjDishCookCategory {
	//	public String                id;//": 1039,
	//	public ArrayList<OptionBean> optionList;//": 1,
	//	public String                optionCategoryName;//": "煲仔饭",
	//	public int                   minimalOptions;//": 0,
	//	public int                   maximalOptions;//": 0,
	//	public String                multipleOptions;//": false
	public String                 cookCategeryId;
	public ArrayList<CxjDishCook> cxjDishCookList;
	public String                 cookCategeryName;
	public int                    minimalOptions;
	public int                    maximalOptions;
	public String                 multipleOptions;

	public CxjDishCookCategory() {
		cxjDishCookList = new ArrayList<>();
	}
}
