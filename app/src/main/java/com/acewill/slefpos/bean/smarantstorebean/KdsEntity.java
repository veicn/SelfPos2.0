package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/1/25 18:07
 * Desc：
 */
public class KdsEntity implements Parcelable {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getKdsName() {
		return kdsName;
	}

	public void setKdsName(String kdsName) {
		this.kdsName = kdsName;
	}

	private String ip;
	private String kdsName;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.ip);
		dest.writeString(this.kdsName);
	}

	public KdsEntity() {
	}

	protected KdsEntity(Parcel in) {
		this.id = in.readInt();
		this.ip = in.readString();
		this.kdsName = in.readString();
	}

	public static final Parcelable.Creator<KdsEntity> CREATOR = new Parcelable.Creator<KdsEntity>() {
		@Override
		public KdsEntity createFromParcel(Parcel source) {
			return new KdsEntity(source);
		}

		@Override
		public KdsEntity[] newArray(int size) {
			return new KdsEntity[size];
		}
	};
}
