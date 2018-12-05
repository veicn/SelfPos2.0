package com.acewill.slefpos.bean.uibean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpncool on 2/23/2016.
 */
public class UIPackageOptionItem implements Serializable, Parcelable {

	private String                 itemID;
	private String                 dishName;
	private String                 dishID;
	private String                 price;
	private String                 itemPrice;
	private int                    count;
	private int                    unitID;
	private String                 unit;
	private String                 dishUnitId;
	private String                 imageName;
	private String                 dishKind;
	private String                 dishKindStr;
	private String                 printerStr;
	private List<UITasteOption>    optionList;//这个菜的定制项
	private List<UIOptionCategory> optionCategoryList;
	private boolean                isSelect;
	private float                  extraCost;
	private int                    selectCount;
	//	============================================================
	private int                    quantity;

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishID() {
		return dishID;
	}

	public void setDishID(String dishID) {
		this.dishID = dishID;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getUnitID() {
		return unitID;
	}

	public void setUnitID(int unitID) {
		this.unitID = unitID;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getDishKind() {
		return dishKind;
	}

	public void setDishKind(String dishKind) {
		this.dishKind = dishKind;
	}

	public String getPrinterStr() {
		return printerStr;
	}

	public void setPrinterStr(String printerStr) {
		this.printerStr = printerStr;
	}

	public List<UITasteOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<UITasteOption> optionList) {
		this.optionList = optionList;
	}

	public List<UIOptionCategory> getOptionCategoryList() {
		return optionCategoryList;
	}

	public void setOptionCategoryList(List<UIOptionCategory> optionCategoryList) {
		this.optionCategoryList = optionCategoryList;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
	}

	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isCannoclick() {
		return cannoclick;
	}

	public void setCannoclick(boolean cannoclick) {
		this.cannoclick = cannoclick;
	}

	private boolean cannoclick;


	public UIPackageOptionItem() {
	}

	public UIPackageOptionItem(String itemID, String dishName, String dishID, String price, String itemPrice, int count, int unitID, String unit, String imageName, String dishKind, String printerStr) {
		this.itemID = itemID;
		this.dishName = dishName;
		this.dishID = dishID;
		this.price = price;
		this.itemPrice = itemPrice;
		this.count = count;
		this.unitID = unitID;
		this.unit = unit;
		this.imageName = imageName;
		this.dishKind = dishKind;
		this.dishKindStr =
		this.printerStr = printerStr;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.itemID);
		dest.writeString(this.dishName);
		dest.writeString(this.dishID);
		dest.writeString(this.price);
		dest.writeString(this.itemPrice);
		dest.writeInt(this.count);
		dest.writeInt(this.unitID);
		dest.writeString(this.unit);
		dest.writeString(this.imageName);
		dest.writeString(this.dishKind);
		dest.writeString(this.printerStr);
		dest.writeTypedList(this.optionList);
		dest.writeList(this.optionCategoryList);
		dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
		dest.writeInt(this.selectCount);
		dest.writeInt(this.quantity);
		dest.writeByte(this.cannoclick ? (byte) 1 : (byte) 0);
	}

	protected UIPackageOptionItem(Parcel in) {
		this.itemID = in.readString();
		this.dishName = in.readString();
		this.dishID = in.readString();
		this.price = in.readString();
		this.itemPrice = in.readString();
		this.count = in.readInt();
		this.unitID = in.readInt();
		this.unit = in.readString();
		this.imageName = in.readString();
		this.dishKind = in.readString();
		this.printerStr = in.readString();
		this.optionList = in.createTypedArrayList(UITasteOption.CREATOR);
		this.optionCategoryList = new ArrayList<UIOptionCategory>();
		in.readList(this.optionCategoryList, UIOptionCategory.class.getClassLoader());
		this.isSelect = in.readByte() != 0;
		this.selectCount = in.readInt();
		this.quantity = in.readInt();
		this.cannoclick = in.readByte() != 0;
	}

	public static final Creator<UIPackageOptionItem> CREATOR = new Creator<UIPackageOptionItem>() {
		@Override
		public UIPackageOptionItem createFromParcel(Parcel source) {
			return new UIPackageOptionItem(source);
		}

		@Override
		public UIPackageOptionItem[] newArray(int size) {
			return new UIPackageOptionItem[size];
		}
	};

	public float getExtraCost() {
		return extraCost;
	}

	public void setExtraCost(float extraCost) {
		this.extraCost = extraCost;
	}

	public String getDishUnitId() {
		return dishUnitId;
	}

	public void setDishUnitId(String dishUnitId) {
		this.dishUnitId = dishUnitId;
	}

	public String getDishKindStr() {
		return dishKindStr;
	}

	public void setDishKindStr(String dishKindStr) {
		this.dishKindStr = dishKindStr;
	}
}
