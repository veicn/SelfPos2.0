package com.acewill.slefpos.bean.uibean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/4/26 11:34
 * Desc：
 */
public class UIDishKind implements Parcelable {
	private String  kindID;
	private int     selectCount;
	private boolean isSelect;
	private String  kindName;
	private String  imageName;

	public UIDishKind(String kindID, String kindName, String imageName) {
		this.kindID = kindID;
		this.kindName = kindName;
		this.imageName = imageName;
	}
	public UIDishKind() {

	}

	public String getKindID() {
		return kindID;
	}

	public void setKindID(String kindID) {
		this.kindID = kindID;
	}

	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
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

	@Override
	public Object clone() {
		UIDishKind kind = null;
		try {
			kind = (UIDishKind) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return kind;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.kindID);
		dest.writeInt(this.selectCount);
		dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
		dest.writeString(this.kindName);
		dest.writeString(this.imageName);
	}

	protected UIDishKind(Parcel in) {
		this.kindID = in.readString();
		this.selectCount = in.readInt();
		this.isSelect = in.readByte() != 0;
		this.kindName = in.readString();
		this.imageName = in.readString();
	}

	public static final Creator<UIDishKind> CREATOR = new Creator<UIDishKind>() {
		@Override
		public UIDishKind createFromParcel(Parcel source) {
			return new UIDishKind(source);
		}

		@Override
		public UIDishKind[] newArray(int size) {
			return new UIDishKind[size];
		}
	};


}
