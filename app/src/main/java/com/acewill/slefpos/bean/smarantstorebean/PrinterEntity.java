package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：Anch
 * Date：2018/1/25 15:47
 * Desc：
 */
public class PrinterEntity implements Parcelable {


	/**
	 * id : 1117
	 * appId : 59841423
	 * brandId : 47
	 * storeId : 1
	 * vendor : shangmi_fix
	 * ip :
	 * deviceName : null
	 * description : 商米
	 * width : WIDTH_80MM
	 * linkType : BLUETOOTH
	 * outputType : REGULAR
	 * labelHeight : null
	 * standbyPrinterIdList :
	 * receiptIdList : null
	 * summaryReceiptCounts : null
	 * dishReceiptCounts : null
	 * sealOf : null
	 * vendorStr : 商米POS
	 * widthStr : 80mm
	 * linkTypeStr : 蓝牙打印机
	 * outputTypeStr : 热敏/针式打印机
	 * standbyPrinterName : null
	 */

	private int    id;
	private String vendor;
	private String ip;
	private String deviceName;
	private String description;
	private String width;
	private String linkType;
	private String outputType;
	private Integer labelHeight;
	private String standbyPrinterIdList;
	private Integer receiptIdList;

	private Integer summaryReceiptCounts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public Integer getLabelHeight() {
		return labelHeight;
	}

	public void setLabelHeight(Integer labelHeight) {
		this.labelHeight = labelHeight;
	}

	public String getStandbyPrinterIdList() {
		return standbyPrinterIdList;
	}

	public void setStandbyPrinterIdList(String standbyPrinterIdList) {
		this.standbyPrinterIdList = standbyPrinterIdList;
	}

	public Integer getReceiptIdList() {
		return receiptIdList;
	}

	public void setReceiptIdList(Integer receiptIdList) {
		this.receiptIdList = receiptIdList;
	}

	public Integer getSummaryReceiptCounts() {
		return summaryReceiptCounts;
	}

	public void setSummaryReceiptCounts(Integer summaryReceiptCounts) {
		this.summaryReceiptCounts = summaryReceiptCounts;
	}

	public Integer getDishReceiptCounts() {
		return dishReceiptCounts;
	}

	public void setDishReceiptCounts(Integer dishReceiptCounts) {
		this.dishReceiptCounts = dishReceiptCounts;
	}

	public String getVendorStr() {
		return vendorStr;
	}

	public void setVendorStr(String vendorStr) {
		this.vendorStr = vendorStr;
	}

	public String getWidthStr() {
		return widthStr;
	}

	public void setWidthStr(String widthStr) {
		this.widthStr = widthStr;
	}

	public String getLinkTypeStr() {
		return linkTypeStr;
	}

	public void setLinkTypeStr(String linkTypeStr) {
		this.linkTypeStr = linkTypeStr;
	}

	public String getOutputTypeStr() {
		return outputTypeStr;
	}

	public void setOutputTypeStr(String outputTypeStr) {
		this.outputTypeStr = outputTypeStr;
	}

	public String getStandbyPrinterName() {
		return standbyPrinterName;
	}

	public void setStandbyPrinterName(String standbyPrinterName) {
		this.standbyPrinterName = standbyPrinterName;
	}

	private Integer dishReceiptCounts;
	private String  vendorStr;
	private String  widthStr;
	private String  linkTypeStr;
	private String  outputTypeStr;
	private String  standbyPrinterName;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.vendor);
		dest.writeString(this.ip);
		dest.writeString(this.deviceName);
		dest.writeString(this.description);
		dest.writeString(this.width);
		dest.writeString(this.linkType);
		dest.writeString(this.outputType);
		dest.writeValue(this.labelHeight);
		dest.writeString(this.standbyPrinterIdList);
		dest.writeValue(this.receiptIdList);
		dest.writeValue(this.summaryReceiptCounts);
		dest.writeValue(this.dishReceiptCounts);
		dest.writeString(this.vendorStr);
		dest.writeString(this.widthStr);
		dest.writeString(this.linkTypeStr);
		dest.writeString(this.outputTypeStr);
		dest.writeString(this.standbyPrinterName);
	}

	public PrinterEntity() {
	}

	protected PrinterEntity(Parcel in) {
		this.id = in.readInt();
		this.vendor = in.readString();
		this.ip = in.readString();
		this.deviceName = in.readString();
		this.description = in.readString();
		this.width = in.readString();
		this.linkType = in.readString();
		this.outputType = in.readString();
		this.labelHeight = (Integer) in.readValue(Integer.class.getClassLoader());
		this.standbyPrinterIdList = in.readString();
		this.receiptIdList = (Integer) in.readValue(Integer.class.getClassLoader());
		this.summaryReceiptCounts = (Integer) in.readValue(Integer.class.getClassLoader());
		this.dishReceiptCounts = (Integer) in.readValue(Integer.class.getClassLoader());
		this.vendorStr = in.readString();
		this.widthStr = in.readString();
		this.linkTypeStr = in.readString();
		this.outputTypeStr = in.readString();
		this.standbyPrinterName = in.readString();
	}

	public static final Parcelable.Creator<PrinterEntity> CREATOR = new Parcelable.Creator<PrinterEntity>() {
		@Override
		public PrinterEntity createFromParcel(Parcel source) {
			return new PrinterEntity(source);
		}

		@Override
		public PrinterEntity[] newArray(int size) {
			return new PrinterEntity[size];
		}
	};
}
