package com.acewill.slefpos.bean.smarantbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/1/27 15:22
 * Desc：
 */

public class TasteOption implements Parcelable,Cloneable {
	/**
	 * id : 1530
	 * appId : 87825359
	 * brandId : 34
	 * optionName : 微辣
	 * price : 1.0
	 * required : false
	 * categoryId : 1068
	 */
	private int     id;
	private String  optionName;
	private double  price;
	private boolean required;
	private int     categoryId;


	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
	}

	private boolean isSelect;

	public TasteOption(int id, String optionName, double price, boolean required,
	                   int categoryId) {
		this.id = id;
		this.optionName = optionName;
		this.price = price;
		this.required = required;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public boolean getRequired() {
		return this.required;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public Object clone() {
		TasteOption tasteOption = null;
		try {
			tasteOption = (TasteOption) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return tasteOption;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.optionName);
		dest.writeDouble(this.price);
		dest.writeByte(this.required ? (byte) 1 : (byte) 0);
		dest.writeInt(this.categoryId);
		dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
	}

	protected TasteOption(Parcel in) {
		this.id = in.readInt();
		this.optionName = in.readString();
		this.price = in.readDouble();
		this.required = in.readByte() != 0;
		this.categoryId = in.readInt();
		this.isSelect = in.readByte() != 0;
	}

	public static final Creator<TasteOption> CREATOR = new Creator<TasteOption>() {
		@Override
		public TasteOption createFromParcel(Parcel source) {
			return new TasteOption(source);
		}

		@Override
		public TasteOption[] newArray(int size) {
			return new TasteOption[size];
		}
	};
}