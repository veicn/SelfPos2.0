package com.acewill.slefpos.bean.smarantbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/2/1 17:42
 * Desc：
 */
public class DishKind implements Parcelable,Cloneable{
	private int    kindID;
	private String kindName;
	private String imageName;
	private int    seq;
	private String kindColor;
	private int    synchroPOS;
	private int    synchroMealMachine;
	private int    synchroWechat;
	private int    synchroTakeOut;


	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}

	//	以下字段不是后台返回的数据，为了界面显示而添加的数据
	private int selectCount;
	private boolean isSelect;


	// ==========================================================

	@Override
	public Object clone() {
		DishKind dishKind = null;
		try {
			dishKind = (DishKind) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return dishKind;
	}
	public int getKindID() {
		return kindID;
	}

	public void setKindID(int kindID) {
		this.kindID = kindID;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getKindColor() {
		return kindColor;
	}

	public void setKindColor(String kindColor) {
		this.kindColor = kindColor;
	}

	public int getSynchroPOS() {
		return synchroPOS;
	}

	public void setSynchroPOS(int synchroPOS) {
		this.synchroPOS = synchroPOS;
	}

	public int getSynchroMealMachine() {
		return synchroMealMachine;
	}

	public void setSynchroMealMachine(int synchroMealMachine) {
		this.synchroMealMachine = synchroMealMachine;
	}

	public int getSynchroWechat() {
		return synchroWechat;
	}

	public void setSynchroWechat(int synchroWechat) {
		this.synchroWechat = synchroWechat;
	}

	public int getSynchroTakeOut() {
		return synchroTakeOut;
	}

	public void setSynchroTakeOut(int synchroTakeOut) {
		this.synchroTakeOut = synchroTakeOut;
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
		dest.writeInt(this.kindID);
		dest.writeString(this.kindName);
		dest.writeString(this.imageName);
		dest.writeInt(this.seq);
		dest.writeString(this.kindColor);
		dest.writeInt(this.synchroPOS);
		dest.writeInt(this.synchroMealMachine);
		dest.writeInt(this.synchroWechat);
		dest.writeInt(this.synchroTakeOut);
		dest.writeInt(this.selectCount);
		dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
	}

	public DishKind() {
	}

	protected DishKind(Parcel in) {
		this.kindID = in.readInt();
		this.kindName = in.readString();
		this.imageName = in.readString();
		this.seq = in.readInt();
		this.kindColor = in.readString();
		this.synchroPOS = in.readInt();
		this.synchroMealMachine = in.readInt();
		this.synchroWechat = in.readInt();
		this.synchroTakeOut = in.readInt();
		this.selectCount = in.readInt();
		this.isSelect = in.readByte() != 0;
	}

	public static final Creator<DishKind> CREATOR = new Creator<DishKind>() {
		@Override
		public DishKind createFromParcel(Parcel source) {
			return new DishKind(source);
		}

		@Override
		public DishKind[] newArray(int size) {
			return new DishKind[size];
		}
	};
}
