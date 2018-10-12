package com.acewill.slefpos.bean.uibean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/27 15:21
 * Desc：
 */
public class UIOptionCategory implements  Serializable ,Parcelable{
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
	private String       id;
	private String              optionCategoryName;
	private int                 minimalOptions;
	private boolean             multipleOptions;
	private int                 maximalOptions;
	private List<UITasteOption> optionList;
	private int                 hasSelect;
	private  String optionCategoryMemo;


	public UIOptionCategory() {
	}

	public UIOptionCategory(String id, String optionCategoryName, int minimalOptions, boolean multipleOptions, int maximalOptions, List<UITasteOption> optionList, String optionCategoryMemo) {
		this.id = id;
		this.optionCategoryName = optionCategoryName;
		this.minimalOptions = minimalOptions;
		this.multipleOptions = multipleOptions;
		this.maximalOptions = maximalOptions;
		this.optionList = optionList;
		this.optionCategoryMemo =optionCategoryMemo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<UITasteOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<UITasteOption> optionList) {
		this.optionList = optionList;
	}

	public int getHasSelect() {
		return hasSelect;
	}

	public void setHasSelect(int hasSelect) {
		this.hasSelect = hasSelect;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.id);
		dest.writeString(this.optionCategoryName);
		dest.writeInt(this.minimalOptions);
		dest.writeByte(this.multipleOptions ? (byte) 1 : (byte) 0);
		dest.writeInt(this.maximalOptions);
		dest.writeTypedList(this.optionList);
		dest.writeInt(this.hasSelect);
	}

	protected UIOptionCategory(Parcel in) {
		this.id = in.readString();
		this.optionCategoryName = in.readString();
		this.minimalOptions = in.readInt();
		this.multipleOptions = in.readByte() != 0;
		this.maximalOptions = in.readInt();
		this.optionList = in.createTypedArrayList(UITasteOption.CREATOR);
		this.hasSelect = in.readInt();
	}

	public static final Creator<UIOptionCategory> CREATOR = new Creator<UIOptionCategory>() {
		@Override
		public UIOptionCategory createFromParcel(Parcel source) {
			return new UIOptionCategory(source);
		}

		@Override
		public UIOptionCategory[] newArray(int size) {
			return new UIOptionCategory[size];
		}
	};

	public UIOptionCategory myclone() {
		UIOptionCategory outer = null;
		try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream    oos  = new ObjectOutputStream(baos);
			oos.writeObject(this);
			// 将流序列化成对象
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream    ois  = new ObjectInputStream(bais);
			outer = (UIOptionCategory) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return outer;
	}

	public String getOptionCategoryMemo() {
		return optionCategoryMemo;
	}

	public void setOptionCategoryMemo(String optionCategoryMemo) {
		this.optionCategoryMemo = optionCategoryMemo;
	}
}
