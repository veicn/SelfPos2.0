package com.acewill.slefpos.bean.smarantbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/1/27 15:21
 * Desc：
 */
public  class Recommand implements Parcelable,Cloneable {
	/**
	 * packageid : 41
	 * packageName : 套餐2
	 * description :
	 * imageName : http://szfileserver.419174855.mtmss.com/common/fileupload/20180125153447_559.jpg
	 * price : 8.00
	 * memberPrice : 12.00
	 * specialPrice : 8.00
	 */

	private String packageid;
	private String packageName;
	private String description;
	private String imageName;
	private String price;
	private String memberPrice;
	private String specialPrice;

	public String getPackageid() {
		return packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}

	public String getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(String specialPrice) {
		this.specialPrice = specialPrice;
	}

	@Override
	public int describeContents() {
		return 0;
	}



	@Override
	protected Recommand clone()  {
		Recommand recommand = null;
		try {
			recommand = (Recommand) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return recommand;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.packageid);
		dest.writeString(this.packageName);
		dest.writeString(this.description);
		dest.writeString(this.imageName);
		dest.writeString(this.price);
		dest.writeString(this.memberPrice);
		dest.writeString(this.specialPrice);
	}

	public Recommand() {
	}

	protected Recommand(Parcel in) {
		this.packageid = in.readString();
		this.packageName = in.readString();
		this.description = in.readString();
		this.imageName = in.readString();
		this.price = in.readString();
		this.memberPrice = in.readString();
		this.specialPrice = in.readString();
	}

	public static final Creator<Recommand> CREATOR = new Creator<Recommand>() {
		@Override
		public Recommand createFromParcel(Parcel source) {
			return new Recommand(source);
		}

		@Override
		public Recommand[] newArray(int size) {
			return new Recommand[size];
		}
	};
}