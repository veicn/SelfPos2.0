package com.acewill.slefpos.orderui.main.ui.eventbus;

import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;

/**
 * Author：Anch
 * Date：2018/4/18 13:15
 * Desc：
 */
public class OnSelectDishChange {
	private boolean           isAddDish;
	private UIPackageOptionItem mBean;
	private int               type;
	private boolean           isadd;

	public boolean isAddDish() {
		return isAddDish;
	}

	public void setAddDish(boolean addDish) {
		isAddDish = addDish;
	}

	public UIPackageOptionItem getBean() {
		return mBean;
	}

	public void setBean(UIPackageOptionItem bean) {
		mBean = bean;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isadd() {
		return isadd;
	}

	public void setIsadd(boolean isadd) {
		this.isadd = isadd;
	}
}
