package com.acewill.slefpos.bean.uibean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpncool on 2/23/2016.
 */
public class UIPackageItem implements Serializable, Parcelable {


	/**
	 * itemName : 精选单品
	 * itemID : 7
	 * itemType : 0
	 * itemCount : 1
	 * isMust : 1
	 * options : [{"dishName":"鱼香肉丝唧唧复唧唧木兰当户织不闻机杼声","dishID":2,"price":5,"itemPrice":5,"count":1,"unitID":3,"unit":"KG","imageName":"http://szfileserver.419174855.mtmss.com/common/fileupload/20180125152738_5639.jpg","dishKind":"2","printerStr":"","optionCategoryList":[{"id":1068,"appId":"87825359","brandId":34,"optionList":[{"id":1530,"appId":"87825359","brandId":34,"optionName":"微辣","price":1,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1531,"appId":"87825359","brandId":34,"optionName":"中辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1532,"appId":"87825359","brandId":34,"optionName":"猛辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null}],"optionCategoryName":"口味","minimalOptions":1,"multipleOptions":true,"maximalOptions":2,"multipleOptionsStr":null},{"id":1089,"appId":"87825359","brandId":34,"optionList":[{"id":1391,"appId":"87825359","brandId":34,"optionName":"热饮","price":0,"required":false,"categoryId":1089,"numSeq":null,"requiredStr":null},{"id":1392,"appId":"87825359","brandId":34,"optionName":"冻饮","price":2,"required":false,"categoryId":1089,"numSeq":null,"requiredStr":null}],"optionCategoryName":"饮料","minimalOptions":1,"multipleOptions":true,"maximalOptions":1,"multipleOptionsStr":null}]}]
	 */

	private String                    itemName;
	private String                    itemID;
	private int                       itemType;
	//	private int                       itemCount;//最多选
	private int                       isMust;
	private List<UIPackageOptionItem> options;
	private int                       maxQty;

	public int getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}

	public int getMinQty() {
		return minQty;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	private int minQty;


	//	++++++++++++++++++++++++++++++++++++++++++++++
	private String                    dishKind;
	private String                    dishKindStr;
	private int                       quantity;
	private List<UIPackageOptionItem> selectedOptions;
	private float            extraCost;
	private boolean isExpanded;
	private int     selectCount;
	private boolean isSelectOk;
	private String  userdefinedName;
	private boolean isSelect;


	public UIPackageItem() {
	}

	public UIPackageItem(String name, String id, int type, int minQty, int maxQty, int must, List<UIPackageOptionItem> items, boolean isExpanded, int selectCount) {
		this.itemName = name;
		this.itemID = id;
		this.itemType = type;
		//		this.itemCount = count;
		this.minQty = minQty;
		this.maxQty = maxQty;
		this.isMust = must;
		this.options = items;
		this.isExpanded = isExpanded;
		this.selectCount = selectCount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	//	public int getItemCount() {
	//		return itemCount;
	//	}
	//
	//	public void setItemCount(int itemCount) {
	//		this.itemCount = itemCount;
	//	}

	public int getIsMust() {
		return isMust;
	}

	public void setIsMust(int isMust) {
		this.isMust = isMust;
	}

	public List<UIPackageOptionItem> getOptions() {
		return options;
	}

	public void setOptions(List<UIPackageOptionItem> options) {
		this.options = options;
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

	public List<UIPackageOptionItem> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(List<UIPackageOptionItem> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean expanded) {
		isExpanded = expanded;
	}

	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}

	public boolean isSelectOk() {
		return isSelectOk;
	}

	public void setSelectOk(boolean selectOk) {
		isSelectOk = selectOk;
	}

	public String getUserdefinedName() {
		return userdefinedName;
	}

	public void setUserdefinedName(String userdefinedName) {
		this.userdefinedName = userdefinedName;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.itemName);
		dest.writeString(this.itemID);
		dest.writeInt(this.itemType);

		//		dest.writeInt(this.itemCount);
		dest.writeInt(this.minQty);
		dest.writeInt(this.maxQty);
		dest.writeInt(this.isMust);
		dest.writeList(this.options);
		dest.writeString(this.dishKind);
		dest.writeString(this.dishKindStr);
		dest.writeInt(this.quantity);
		dest.writeList(this.selectedOptions);
		dest.writeByte(this.isExpanded ? (byte) 1 : (byte) 0);
		dest.writeInt(this.selectCount);
		dest.writeByte(this.isSelectOk ? (byte) 1 : (byte) 0);
		dest.writeString(this.userdefinedName);
		dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
	}

	protected UIPackageItem(Parcel in) {
		this.itemName = in.readString();
		this.itemID = in.readString();
		this.itemType = in.readInt();
		this.minQty = in.readInt();
		this.maxQty = in.readInt();
		this.isMust = in.readInt();
		this.options = new ArrayList<UIPackageOptionItem>();
		in.readList(this.options, UIPackageOptionItem.class.getClassLoader());
		this.dishKind = in.readString();
		this.dishKindStr = in.readString();
		this.quantity = in.readInt();
		this.selectedOptions = new ArrayList<UIPackageOptionItem>();
		in.readList(this.selectedOptions, UIPackageOptionItem.class.getClassLoader());
		this.isExpanded = in.readByte() != 0;
		this.selectCount = in.readInt();
		this.isSelectOk = in.readByte() != 0;
		this.userdefinedName = in.readString();
		this.isSelect = in.readByte() != 0;
	}

	public static final Creator<UIPackageItem> CREATOR = new Creator<UIPackageItem>() {
		@Override
		public UIPackageItem createFromParcel(Parcel source) {
			return new UIPackageItem(source);
		}

		@Override
		public UIPackageItem[] newArray(int size) {
			return new UIPackageItem[size];
		}
	};

	public float getExtraCost() {
		return extraCost;
	}

	public void setExtraCost(float extraCost) {
		this.extraCost = extraCost;
	}
}
