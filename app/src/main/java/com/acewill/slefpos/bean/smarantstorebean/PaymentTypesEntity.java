package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/1/25 17:36
 * Desc：
 */
public class PaymentTypesEntity implements Parcelable {
	private int id;
	private String  name;
	private String  appIDs;
	private String  keyStr;
	private String  mchID;
	private String  subMchID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppIDs() {
		return appIDs;
	}

	public void setAppIDs(String appIDs) {
		this.appIDs = appIDs;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public String getMchID() {
		return mchID;
	}

	public void setMchID(String mchID) {
		this.mchID = mchID;
	}

	public String getSubMchID() {
		return subMchID;
	}

	public void setSubMchID(String subMchID) {
		this.subMchID = subMchID;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private String appsecret;
	private int    status;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
		dest.writeString(this.appIDs);
		dest.writeString(this.keyStr);
		dest.writeString(this.mchID);
		dest.writeString(this.subMchID);
		dest.writeString(this.appsecret);
		dest.writeInt(this.status);
	}

	public PaymentTypesEntity() {
	}

	protected PaymentTypesEntity(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.appIDs = in.readString();
		this.keyStr = in.readString();
		this.mchID = in.readString();
		this.subMchID = in.readString();
		this.appsecret = in.readString();
		this.status = in.readInt();
	}

	public static final Parcelable.Creator<PaymentTypesEntity> CREATOR = new Parcelable.Creator<PaymentTypesEntity>() {
		@Override
		public PaymentTypesEntity createFromParcel(Parcel source) {
			return new PaymentTypesEntity(source);
		}

		@Override
		public PaymentTypesEntity[] newArray(int size) {
			return new PaymentTypesEntity[size];
		}
	};
}
