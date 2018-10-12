package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/1/25 17:33
 * Desc：
 */
public class ImageEntity implements Parcelable {

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletypeKey() {
		return filetypeKey;
	}

	public void setFiletypeKey(String filetypeKey) {
		this.filetypeKey = filetypeKey;
	}

	private String filename;
	private String filetypeKey;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.filename);
		dest.writeString(this.filetypeKey);
	}

	public ImageEntity() {
	}

	protected ImageEntity(Parcel in) {
		this.filename = in.readString();
		this.filetypeKey = in.readString();
	}

	public static final Parcelable.Creator<ImageEntity> CREATOR = new Parcelable.Creator<ImageEntity>() {
		@Override
		public ImageEntity createFromParcel(Parcel source) {
			return new ImageEntity(source);
		}

		@Override
		public ImageEntity[] newArray(int size) {
			return new ImageEntity[size];
		}
	};
}
