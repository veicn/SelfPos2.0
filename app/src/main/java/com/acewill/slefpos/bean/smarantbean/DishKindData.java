package com.acewill.slefpos.bean.smarantbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/2/1 17:38
 * Desc：
 */
public class DishKindData {

	/**
	 * result : 0
	 * content :
	 * errmsg : 0
	 * dishKindData : [{"kindID":1,"kindName":"肉类","imageName":"http://szfileserver.419174855.mtmss.com/common/fileupload/20170506102210_346.png","seq":1,"kindColor":"ffd58a","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":0,"subBrandId":null},{"kindID":10,"kindName":"套餐","imageName":"http://szfileserver.419174855.mtmss.com/common/fileupload/20170506102141_3841.png","seq":1,"kindColor":"ffd58a","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":0,"subBrandId":null},{"kindID":2,"kindName":"素菜","imageName":"http://szfileserver.419174855.mtmss.com/common/fileupload/20170506102141_3841.png","seq":2,"kindColor":"ffd58a","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":0,"subBrandId":null},{"kindID":11,"kindName":"测试小类","imageName":"","seq":3,"kindColor":"ffd58a","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":0,"subBrandId":null},{"kindID":4,"kindName":"甜品","imageName":"http://szfileserver.419174855.mtmss.com/common/fileupload/20170506102210_346.png","seq":4,"kindColor":"ffd58a","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":0,"subBrandId":null},{"kindID":7,"kindName":"汤","imageName":"","seq":7,"kindColor":"b8edf1","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":1,"subBrandId":null},{"kindID":8,"kindName":"酒","imageName":"","seq":8,"kindColor":"b8edf1","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":1,"subBrandId":null},{"kindID":9,"kindName":"饮料","imageName":"","seq":9,"kindColor":"b8edf1","synchroPOS":1,"synchroMealMachine":1,"synchroWechat":1,"synchroTakeOut":1,"subBrandId":null}]
	 * dishKindDataMap : null
	 */

	private int            result;
	private String         content;
	private String         errmsg;
	private List<DishKind> dishKindData;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<DishKind> getDishKindData() {
		if (dishKindData != null)
			return copyKindList(dishKindData);

		return dishKindData;
	}

	private List<DishKind> copyKindList(List<DishKind> list) {
		Iterator<DishKind> iterator     = list.iterator();
		List<DishKind>     dishKindList = new ArrayList<>();
		while (iterator.hasNext()) {
			dishKindList.add((DishKind) iterator.next().clone());
		}
		return dishKindList;
	}

	public void setDishKindData(List<DishKind> dishKindData) {
		this.dishKindData = dishKindData;
	}
}
