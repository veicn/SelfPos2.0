package com.acewill.slefpos.bean.smarantbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bpncool on 2/23/2016.
 */
public class PackageItem implements Parcelable, Cloneable {


	public  boolean isExpanded;
	private int     selectCount;

	/**
	 * itemName : 精选单品
	 * itemID : 7
	 * itemType : 0
	 * itemCount : 1
	 * isMust : 1
	 * options : [{"dishName":"鱼香肉丝唧唧复唧唧木兰当户织不闻机杼声","dishID":2,"price":5,"itemPrice":5,"count":1,"unitID":3,"unit":"KG","imageName":"http://szfileserver.419174855.mtmss.com/common/fileupload/20180125152738_5639.jpg","dishKind":"2","printerStr":"","optionCategoryList":[{"id":1068,"appId":"87825359","brandId":34,"optionList":[{"id":1530,"appId":"87825359","brandId":34,"optionName":"微辣","price":1,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1531,"appId":"87825359","brandId":34,"optionName":"中辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1532,"appId":"87825359","brandId":34,"optionName":"猛辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null}],"optionCategoryName":"口味","minimalOptions":1,"multipleOptions":true,"maximalOptions":2,"multipleOptionsStr":null},{"id":1089,"appId":"87825359","brandId":34,"optionList":[{"id":1391,"appId":"87825359","brandId":34,"optionName":"热饮","price":0,"required":false,"categoryId":1089,"numSeq":null,"requiredStr":null},{"id":1392,"appId":"87825359","brandId":34,"optionName":"冻饮","price":2,"required":false,"categoryId":1089,"numSeq":null,"requiredStr":null}],"optionCategoryName":"饮料","minimalOptions":1,"multipleOptions":true,"maximalOptions":1,"multipleOptionsStr":null}]}]
	 */

	private String                  itemName;
	private int                     itemID;
	private int                     itemType;
	private int                     itemCount;
	private int                     isMust;
	private List<PackageOptionItem> options;
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getIsMust() {
		return isMust;
	}

	public void setIsMust(int isMust) {
		this.isMust = isMust;
	}

	public List<PackageOptionItem> getOptions() {
		if (options != null)
			return copyPackageOptionItems(options);
		return options;
	}

	private List<PackageOptionItem> copyPackageOptionItems(List<PackageOptionItem> list) {
		Iterator<PackageOptionItem> iterator           = list.iterator();
		List<PackageOptionItem>     packageOptionItems = new ArrayList<>();
		while (iterator.hasNext()) {
			packageOptionItems.add((PackageOptionItem) iterator.next().clone());
		}
		return packageOptionItems;
	}

	public void setOptions(List<PackageOptionItem> options) {
		this.options = options;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public Object clone() {
		PackageItem packageItem = null;
		try {
			packageItem = (PackageItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return packageItem;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte(this.isExpanded ? (byte) 1 : (byte) 0);
		dest.writeString(this.itemName);
		dest.writeInt(this.itemID);
		dest.writeInt(this.itemType);
		dest.writeInt(this.itemCount);
		dest.writeInt(this.isMust);
		dest.writeTypedList(this.options);
	}

	protected PackageItem(Parcel in) {
		this.isExpanded = in.readByte() != 0;
		this.itemName = in.readString();
		this.itemID = in.readInt();
		this.itemType = in.readInt();
		this.itemCount = in.readInt();
		this.isMust = in.readInt();
		this.options = in.createTypedArrayList(PackageOptionItem.CREATOR);
	}

	public static final Creator<PackageItem> CREATOR = new Creator<PackageItem>() {
		@Override
		public PackageItem createFromParcel(Parcel source) {
			return new PackageItem(source);
		}

		@Override
		public PackageItem[] newArray(int size) {
			return new PackageItem[size];
		}
	};

	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}


}
