package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 17:59
 * Desc：
 */
public class KichenStallEntity implements Parcelable {
	private Long           stallsid;

	public Long getStallsid() {
		return stallsid;
	}

	public void setStallsid(Long stallsid) {
		this.stallsid = stallsid;
	}

	public String getStallsName() {
		return stallsName;
	}

	public void setStallsName(String stallsName) {
		this.stallsName = stallsName;
	}

	public int getPrinterid() {
		return printerid;
	}

	public void setPrinterid(int printerid) {
		this.printerid = printerid;
	}

	public int getKdsid() {
		return kdsid;
	}

	public void setKdsid(int kdsid) {
		this.kdsid = kdsid;
	}

	public int getSummaryReceiptCounts() {
		return summaryReceiptCounts;
	}

	public void setSummaryReceiptCounts(int summaryReceiptCounts) {
		this.summaryReceiptCounts = summaryReceiptCounts;
	}

	public int getDishReceiptCounts() {
		return dishReceiptCounts;
	}

	public void setDishReceiptCounts(int dishReceiptCounts) {
		this.dishReceiptCounts = dishReceiptCounts;
	}

	public String getKitchenPrintMode() {
		return kitchenPrintMode;
	}

	public void setKitchenPrintMode(String kitchenPrintMode) {
		this.kitchenPrintMode = kitchenPrintMode;
	}

	public List<Long> getDishIdList() {
		return dishIdList;
	}

	public void setDishIdList(List<Long> dishIdList) {
		this.dishIdList = dishIdList;
	}

	private String        stallsName;
	private int           printerid;
	private int           kdsid;
	private int           summaryReceiptCounts;
	private int           dishReceiptCounts;
	private String        kitchenPrintMode;
	private List<Long> dishIdList;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.stallsid);
		dest.writeString(this.stallsName);
		dest.writeInt(this.printerid);
		dest.writeInt(this.kdsid);
		dest.writeInt(this.summaryReceiptCounts);
		dest.writeInt(this.dishReceiptCounts);
		dest.writeString(this.kitchenPrintMode);
		dest.writeList(this.dishIdList);
	}

	public KichenStallEntity() {
	}

	protected KichenStallEntity(Parcel in) {
		this.stallsid = in.readLong();
		this.stallsName = in.readString();
		this.printerid = in.readInt();
		this.kdsid = in.readInt();
		this.summaryReceiptCounts = in.readInt();
		this.dishReceiptCounts = in.readInt();
		this.kitchenPrintMode = in.readString();
		this.dishIdList = new ArrayList<Long>();
		in.readList(this.dishIdList, Integer.class.getClassLoader());
	}

	public static final Parcelable.Creator<KichenStallEntity> CREATOR = new Parcelable.Creator<KichenStallEntity>() {
		@Override
		public KichenStallEntity createFromParcel(Parcel source) {
			return new KichenStallEntity(source);
		}

		@Override
		public KichenStallEntity[] newArray(int size) {
			return new KichenStallEntity[size];
		}
	};
}
