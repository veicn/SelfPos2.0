package com.acewill.slefpos.bean.uibean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Author：Anch
 * Date：2018/1/27 15:22
 * Desc：
 */

public class UITasteOption implements Serializable, Parcelable {


	private  int seqOrder;
	/**
	 * id : 1530
	 * appId : 87825359
	 * brandId : 34
	 * optionName : 微辣
	 * price : 1.0
	 * required : false
	 * categoryId : 1068
	 */
	private String  ouid;//同步时才会用到
	private String  id;
	private String  optionName;
	private double  price;
	private boolean required;
	private String  categoryId;
	private String  sourceType;
	private boolean isSelect;
	private int     quantity;
	private String  kindOuid;

	public UITasteOption() {
	}

	public UITasteOption(String ouid, int seqOrder, String id, String optionName, double price, boolean required,
	                     String categoryId, String sourceType, String mixKindOuid) {
		this.ouid = ouid;
		this.id = id;
		this.optionName = optionName;
		this.price = price;
		this.required = required;
		this.categoryId = categoryId;
		this.sourceType = sourceType;
		this.kindOuid = mixKindOuid;
		this.seqOrder = seqOrder;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
		dest.writeString(this.id);
		dest.writeString(this.optionName);
		dest.writeDouble(this.price);
		dest.writeByte(this.required ? (byte) 1 : (byte) 0);
		dest.writeString(this.categoryId);
		dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
	}

	protected UITasteOption(Parcel in) {
		this.id = in.readString();
		this.optionName = in.readString();
		this.price = in.readDouble();
		this.required = in.readByte() != 0;
		this.categoryId = in.readString();
		this.isSelect = in.readByte() != 0;
	}

	public static final Creator<UITasteOption> CREATOR = new Creator<UITasteOption>() {
		@Override
		public UITasteOption createFromParcel(Parcel source) {
			return new UITasteOption(source);
		}

		@Override
		public UITasteOption[] newArray(int size) {
			return new UITasteOption[size];
		}
	};

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public UITasteOption myclone() {
		UITasteOption outer = null;
		try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream    oos  = new ObjectOutputStream(baos);
			oos.writeObject(this);
			// 将流序列化成对象
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream    ois  = new ObjectInputStream(bais);
			outer = (UITasteOption) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return outer;
	}

	public int getQuantity() {
		if (quantity == 0)
			quantity = 1;
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getKindOuid() {
		return kindOuid;
	}

	public void setKindOuid(String kindOuid) {
		this.kindOuid = kindOuid;
	}

	public String getOuid() {
		return ouid;
	}

	public void setOuid(String ouid) {
		this.ouid = ouid;
	}

	public int getSeqOrder() {
		return seqOrder;
	}

	public void setSeqOrder(int seqOrder) {
		this.seqOrder = seqOrder;
	}
}