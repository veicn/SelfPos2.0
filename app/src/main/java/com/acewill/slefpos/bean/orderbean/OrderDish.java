package com.acewill.slefpos.bean.orderbean;

import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.orderui.main.market.MarketObject;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/23 11:41
 * Desc：
 */
public class OrderDish {
	private String                    dishID;
	private String                    dishName;
	private String                    price;
	private String                    dishKind;
	private String                    dishKindStr;//菜的分类
	private int                       quantity;//菜的数量
	private List<UITasteOption>       optionList;//这个菜的定制项
	private List<UIPackageOptionItem> subItemList;//这个菜的套餐项
	private String                    cost;
	private String                    memberPrice;
	private String                    dishUnit;
	private List<MarketObject>        marketList;



	public String getDishID() {
		return dishID;
	}

	public void setDishID(String dishID) {
		this.dishID = dishID;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDishKind() {
		return dishKind;
	}

	public void setDishKind(String dishKind) {
		this.dishKind = dishKind;
	}

	public String getDishKindStr() {
		return dishKindStr;
	}

	public void setDishKindStr(String dishKindStr) {
		this.dishKindStr = dishKindStr;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<UITasteOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<UITasteOption> optionList) {
		this.optionList = optionList;
	}

	public List<UIPackageOptionItem> getSubItemList() {
		return subItemList;
	}

	public void setSubItemList(List<UIPackageOptionItem> subItemList) {
		this.subItemList = subItemList;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}

	public String getDishUnit() {
		return dishUnit;
	}

	public void setDishUnit(String dishUnit) {
		this.dishUnit = dishUnit;
	}

	public List<MarketObject> getMarketList() {
		return marketList;
	}

	public void setMarketList(List<MarketObject> marketList) {
		this.marketList = marketList;
	}
}
