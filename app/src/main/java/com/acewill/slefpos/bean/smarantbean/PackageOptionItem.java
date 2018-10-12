package com.acewill.slefpos.bean.smarantbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bpncool on 2/23/2016.
 */
public class PackageOptionItem implements Parcelable, Cloneable {

	private int                  itemID;
	private String               dishName;
	private int                  dishID;
	private double               price;
	private double               itemPrice;
	private float                extraCost;
	private int                  count;
	private int                  unitID;
	private String               unit;
	private String               imageName;
	private String               dishKind;
	private String               printerStr;
	private List<OptionCategory> optionCategoryList;
	//	============================================================

	public List<OptionCategory> getOptionCategoryList() {
		if (optionCategoryList != null)
			return copyOptioinCategory(optionCategoryList);
		return optionCategoryList;
	}

	public void setOptionCategoryList(List<OptionCategory> optionCategoryList) {
		this.optionCategoryList = optionCategoryList;
	}

	private boolean isSelect;
	private int     selectCount;

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getDishID() {
		return dishID;
	}

	public void setDishID(int dishID) {
		this.dishID = dishID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
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

	public PackageOptionItem() {
	}

	@Override
	public Object clone() {
		PackageOptionItem packageOptionItem = null;
		try {
			packageOptionItem = (PackageOptionItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return packageOptionItem;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.dishName);
		dest.writeInt(this.dishID);
		dest.writeDouble(this.price);
		dest.writeDouble(this.itemPrice);
		dest.writeInt(this.count);
		dest.writeInt(this.unitID);
		dest.writeString(this.unit);
		dest.writeString(this.imageName);
		dest.writeString(this.dishKind);
		dest.writeString(this.printerStr);
		dest.writeTypedList(this.optionCategoryList);
	}

	protected PackageOptionItem(Parcel in) {
		this.dishName = in.readString();
		this.dishID = in.readInt();
		this.price = in.readDouble();
		this.itemPrice = in.readDouble();
		this.count = in.readInt();
		this.unitID = in.readInt();
		this.unit = in.readString();
		this.imageName = in.readString();
		this.dishKind = in.readString();
		this.printerStr = in.readString();
		this.optionCategoryList = in.createTypedArrayList(OptionCategory.CREATOR);
	}

	public static final Creator<PackageOptionItem> CREATOR = new Creator<PackageOptionItem>() {
		@Override
		public PackageOptionItem createFromParcel(Parcel source) {
			return new PackageOptionItem(source);
		}

		@Override
		public PackageOptionItem[] newArray(int size) {
			return new PackageOptionItem[size];
		}
	};

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


	private List<OptionCategory> copyOptioinCategory(List<OptionCategory> list) {
		Iterator<OptionCategory> iterator = list.iterator();
		List<OptionCategory>     newList  = new ArrayList<>();
		while (iterator.hasNext()) {
			newList.add((OptionCategory) iterator.next().clone());
		}
		return newList;
	}

	public float getExtraCost() {
		return extraCost;
	}

	public void setExtraCost(float extraCost) {
		this.extraCost = extraCost;
	}
}
