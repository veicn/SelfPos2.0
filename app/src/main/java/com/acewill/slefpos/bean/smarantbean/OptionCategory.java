package com.acewill.slefpos.bean.smarantbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/27 15:21
 * Desc：
 */
public class OptionCategory implements Parcelable, Cloneable {
	/**
	 * id : 1068
	 * appId : 87825359
	 * brandId : 34
	 * optionList : [{"id":1530,"appId":"87825359","brandId":34,"optionName":"微辣","price":1,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1531,"appId":"87825359","brandId":34,"optionName":"中辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null},{"id":1532,"appId":"87825359","brandId":34,"optionName":"猛辣","price":0,"required":false,"categoryId":1068,"numSeq":null,"requiredStr":null}]
	 * optionCategoryName : 口味
	 * minimalOptions : 1
	 * multipleOptions : true
	 * maximalOptions : 2
	 * multipleOptionsStr : null
	 */
	private int               id;
	private String            optionCategoryName;
	private int               minimalOptions;
	private boolean           multipleOptions;
	private int               maximalOptions;
	private List<TasteOption> optionList;


	public int getHasSelect() {
		return hasSelect;
	}

	public void setHasSelect(int hasSelect) {
		this.hasSelect = hasSelect;
	}

	//	==========================================================================
	private int hasSelect;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOptionCategoryName() {
		return optionCategoryName;
	}

	public void setOptionCategoryName(String optionCategoryName) {
		this.optionCategoryName = optionCategoryName;
	}

	public int getMinimalOptions() {
		return minimalOptions;
	}

	public void setMinimalOptions(int minimalOptions) {
		this.minimalOptions = minimalOptions;
	}

	public boolean isMultipleOptions() {
		return multipleOptions;
	}

	public void setMultipleOptions(boolean multipleOptions) {
		this.multipleOptions = multipleOptions;
	}

	public int getMaximalOptions() {
		return maximalOptions;
	}

	public void setMaximalOptions(int maximalOptions) {
		this.maximalOptions = maximalOptions;
	}

	public List<TasteOption> getOptionList() {
		if (optionList != null)
			return copyTastOption(optionList);
		return optionList;
	}

	private List<TasteOption> copyTastOption(List<TasteOption> list) {
		Iterator<TasteOption> iterator = list.iterator();
		List<TasteOption>     newList  = new ArrayList<>();
		while (iterator.hasNext()) {
			newList.add((TasteOption) iterator.next().clone());
		}
		return newList;
	}

	public void setOptionList(List<TasteOption> optionList) {
		this.optionList = optionList;
	}

	@Override
	public Object clone() {
		OptionCategory optionCategory = null;
		try {
			optionCategory = (OptionCategory) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return optionCategory;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.optionCategoryName);
		dest.writeInt(this.minimalOptions);
		dest.writeByte(this.multipleOptions ? (byte) 1 : (byte) 0);
		dest.writeInt(this.maximalOptions);
		dest.writeList(this.optionList);
	}

	public OptionCategory() {
	}

	protected OptionCategory(Parcel in) {
		this.id = in.readInt();
		this.optionCategoryName = in.readString();
		this.minimalOptions = in.readInt();
		this.multipleOptions = in.readByte() != 0;
		this.maximalOptions = in.readInt();
		this.optionList = new ArrayList<TasteOption>();
		in.readList(this.optionList, TasteOption.class.getClassLoader());
	}

	public static final Creator<OptionCategory> CREATOR = new Creator<OptionCategory>() {
		@Override
		public OptionCategory createFromParcel(Parcel source) {
			return new OptionCategory(source);
		}

		@Override
		public OptionCategory[] newArray(int size) {
			return new OptionCategory[size];
		}
	};
}
