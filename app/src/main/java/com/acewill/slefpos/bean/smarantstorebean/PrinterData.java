package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 16:47
 * Desc：
 */
public class PrinterData implements Parcelable {

	/**
	 * result : 0
	 * content : [{"id":1117,"appId":"59841423","brandId":47,"storeId":1,"vendor":"shangmi_fix","ip":"","deviceName":null,"description":"商米","width":"WIDTH_80MM","linkType":"BLUETOOTH","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":"","receiptIdList":null,"summaryReceiptCounts":null,"dishReceiptCounts":null,"sealOf":null,"vendorStr":"商米POS","widthStr":"80mm","linkTypeStr":"蓝牙打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1203,"appId":"59841423","brandId":47,"storeId":1,"vendor":"sprt","ip":"192.168.1.211","deviceName":"","description":"打印机211","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":30,"standbyPrinterIdList":"","receiptIdList":null,"summaryReceiptCounts":0,"dishReceiptCounts":1,"sealOf":null,"vendorStr":"思普瑞特","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1213,"appId":"59841423","brandId":47,"storeId":1,"vendor":"unknown","ip":"192.168.1.168","deviceName":"","description":"打印机168","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":30,"standbyPrinterIdList":"","receiptIdList":null,"summaryReceiptCounts":0,"dishReceiptCounts":1,"sealOf":null,"vendorStr":"未知厂商","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1223,"appId":"59841423","brandId":47,"storeId":1,"vendor":"epson","ip":"192.168.1.188","deviceName":"","description":"188打印机","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":"1203","receiptIdList":null,"summaryReceiptCounts":1,"dishReceiptCounts":0,"sealOf":"","vendorStr":"爱普生","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":"打印机211"},{"id":1234,"appId":"59841423","brandId":47,"storeId":1,"vendor":"sprt","ip":"192.168.1.222","deviceName":null,"description":"打印机222","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":"","receiptIdList":null,"summaryReceiptCounts":0,"dishReceiptCounts":1,"sealOf":"","vendorStr":"思普瑞特","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null}]
	 * errmsg : 0
	 */

	private int result;
	private String              errmsg;
	private List<PrinterEntity> content;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<PrinterEntity> getContent() {
		return content;
	}

	public void setContent(List<PrinterEntity> content) {
		this.content = content;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.result);
		dest.writeString(this.errmsg);
		dest.writeTypedList(this.content);
	}

	public PrinterData() {
	}

	protected PrinterData(Parcel in) {
		this.result = in.readInt();
		this.errmsg = in.readString();
		this.content = in.createTypedArrayList(PrinterEntity.CREATOR);
	}

	public static final Parcelable.Creator<PrinterData> CREATOR = new Parcelable.Creator<PrinterData>() {
		@Override
		public PrinterData createFromParcel(Parcel source) {
			return new PrinterData(source);
		}

		@Override
		public PrinterData[] newArray(int size) {
			return new PrinterData[size];
		}
	};
}
