package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/1/25 17:22
 * Desc：
 */
public class SelfposConfigurationEntity implements Parcelable {
	private boolean inputTableNumber;
	private String  orderKDS;
	private boolean kitchenPrint;
	private boolean kitchenPrintPrice;
	private int     minTable;
	private int     maxTable;
	private boolean informKDS;
	private boolean packageLenovo;
	private boolean printKitchenQrcode;
	private boolean printInvoiceQrcode;
	private boolean printOrderQrcode;
	private int     screenSaverTime;
	private boolean showShoppingChart;
	private boolean checkMember;

	public boolean isDisplayMember() {
		return displayMember;
	}

	public void setDisplayMember(boolean displayMember) {
		this.displayMember = displayMember;
	}

	private boolean displayMember;
	private int     isScanAndInput;


	public boolean isInputTableNumber() {
		return inputTableNumber;
	}

	public void setInputTableNumber(boolean inputTableNumber) {
		this.inputTableNumber = inputTableNumber;
	}

	public String getOrderKDS() {
		return orderKDS;
	}

	public void setOrderKDS(String orderKDS) {
		this.orderKDS = orderKDS;
	}

	public boolean isKitchenPrint() {
		return kitchenPrint;
	}

	public void setKitchenPrint(boolean kitchenPrint) {
		this.kitchenPrint = kitchenPrint;
	}

	public boolean isKitchenPrintPrice() {
		return kitchenPrintPrice;
	}

	public void setKitchenPrintPrice(boolean kitchenPrintPrice) {
		this.kitchenPrintPrice = kitchenPrintPrice;
	}

	public int getMinTable() {
		return minTable;
	}

	public void setMinTable(int minTable) {
		this.minTable = minTable;
	}

	public int getMaxTable() {
		return maxTable;
	}

	public void setMaxTable(int maxTable) {
		this.maxTable = maxTable;
	}

	public boolean isInformKDS() {
		return informKDS;
	}

	public void setInformKDS(boolean informKDS) {
		this.informKDS = informKDS;
	}

	public boolean isPackageLenovo() {
		return packageLenovo;
	}

	public void setPackageLenovo(boolean packageLenovo) {
		this.packageLenovo = packageLenovo;
	}

	public boolean isPrintKitchenQrcode() {
		return printKitchenQrcode;
	}

	public void setPrintKitchenQrcode(boolean printKitchenQrcode) {
		this.printKitchenQrcode = printKitchenQrcode;
	}

	public boolean isPrintInvoiceQrcode() {
		return printInvoiceQrcode;
	}

	public void setPrintInvoiceQrcode(boolean printInvoiceQrcode) {
		this.printInvoiceQrcode = printInvoiceQrcode;
	}

	public boolean isPrintOrderQrcode() {
		return printOrderQrcode;
	}

	public void setPrintOrderQrcode(boolean printOrderQrcode) {
		this.printOrderQrcode = printOrderQrcode;
	}

	public int getScreenSaverTime() {
		return screenSaverTime;
	}

	public void setScreenSaverTime(int screenSaverTime) {
		this.screenSaverTime = screenSaverTime;
	}

	public boolean isShowShoppingChart() {
		return showShoppingChart;
	}

	public void setShowShoppingChart(boolean showShoppingChart) {
		this.showShoppingChart = showShoppingChart;
	}

	public boolean isCheckMember() {
		return checkMember;
	}

	public void setCheckMember(boolean checkMember) {
		this.checkMember = checkMember;
	}

	public int getIsScanAndInput() {
		return isScanAndInput;
	}

	public void setIsScanAndInput(int isScanAndInput) {
		this.isScanAndInput = isScanAndInput;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte(this.inputTableNumber ? (byte) 1 : (byte) 0);
		dest.writeString(this.orderKDS);
		dest.writeByte(this.kitchenPrint ? (byte) 1 : (byte) 0);
		dest.writeByte(this.kitchenPrintPrice ? (byte) 1 : (byte) 0);
		dest.writeInt(this.minTable);
		dest.writeInt(this.maxTable);
		dest.writeByte(this.informKDS ? (byte) 1 : (byte) 0);
		dest.writeByte(this.packageLenovo ? (byte) 1 : (byte) 0);
		dest.writeByte(this.printKitchenQrcode ? (byte) 1 : (byte) 0);
		dest.writeByte(this.printInvoiceQrcode ? (byte) 1 : (byte) 0);
		dest.writeByte(this.printOrderQrcode ? (byte) 1 : (byte) 0);
		dest.writeInt(this.screenSaverTime);
		dest.writeByte(this.showShoppingChart ? (byte) 1 : (byte) 0);
		dest.writeByte(this.checkMember ? (byte) 1 : (byte) 0);
		dest.writeInt(this.isScanAndInput);
	}

	public SelfposConfigurationEntity() {
	}

	protected SelfposConfigurationEntity(Parcel in) {
		this.inputTableNumber = in.readByte() != 0;
		this.orderKDS = in.readString();
		this.kitchenPrint = in.readByte() != 0;
		this.kitchenPrintPrice = in.readByte() != 0;
		this.minTable = in.readInt();
		this.maxTable = in.readInt();
		this.informKDS = in.readByte() != 0;
		this.packageLenovo = in.readByte() != 0;
		this.printKitchenQrcode = in.readByte() != 0;
		this.printInvoiceQrcode = in.readByte() != 0;
		this.printOrderQrcode = in.readByte() != 0;
		this.screenSaverTime = in.readInt();
		this.showShoppingChart = in.readByte() != 0;
		this.checkMember = in.readByte() != 0;
		this.isScanAndInput = in.readInt();
	}

	public static final Parcelable.Creator<SelfposConfigurationEntity> CREATOR = new Parcelable.Creator<SelfposConfigurationEntity>() {
		@Override
		public SelfposConfigurationEntity createFromParcel(Parcel source) {
			return new SelfposConfigurationEntity(source);
		}

		@Override
		public SelfposConfigurationEntity[] newArray(int size) {
			return new SelfposConfigurationEntity[size];
		}
	};
}
