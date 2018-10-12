package com.acewill.slefpos.print.chikenprintlibrary.model;


import com.acewill.slefpos.print.chikenprintlibrary.printer.Printer;

import java.util.List;

/**
 * Author：Anch
 * Date：2017/5/4 20:14
 * Desc：
 */
public class PrinterRes {

	/**
	 * result : 0
	 * content : [{"id":1041,"appId":"42458671","brandId":40,"storeId":5,"vendor":"unknown","ip":"192.168.1.180","deviceName":null,"description":"打印机180","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":null,"receiptIdList":null,"summaryReceiptCounts":0,"dishReceiptCounts":1,"vendorStr":"未知厂商","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1042,"appId":"42458671","brandId":40,"storeId":5,"vendor":"unknown","ip":"192.168.1.211","deviceName":null,"description":"打印机211","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":null,"receiptIdList":null,"summaryReceiptCounts":0,"dishReceiptCounts":1,"vendorStr":"未知厂商","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1043,"appId":"42458671","brandId":40,"storeId":5,"vendor":"unknown","ip":"192.168.1.251","deviceName":null,"description":"打印机251","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":null,"receiptIdList":null,"summaryReceiptCounts":0,"dishReceiptCounts":1,"vendorStr":"未知厂商","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1050,"appId":"42458671","brandId":40,"storeId":5,"vendor":"epson","ip":"192.168.1.183","deviceName":null,"description":"打印机183","width":"WIDTH_80MM","linkType":"NETWORK","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":"","receiptIdList":null,"summaryReceiptCounts":0,"dishReceiptCounts":1,"vendorStr":"爱普生","widthStr":"80mm","linkTypeStr":"网络打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1056,"appId":"42458671","brandId":40,"storeId":5,"vendor":"shangmi_fix","ip":"","deviceName":null,"description":"商米","width":"WIDTH_80MM","linkType":"BLUETOOTH","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":"","receiptIdList":null,"summaryReceiptCounts":null,"dishReceiptCounts":null,"vendorStr":"商米POS","widthStr":"80mm","linkTypeStr":"蓝牙打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null},{"id":1057,"appId":"42458671","brandId":40,"storeId":5,"vendor":"unknown","ip":"","deviceName":null,"description":"股份","width":"WIDTH_80MM","linkType":"BLUETOOTH","outputType":"REGULAR","labelHeight":null,"standbyPrinterIdList":"","receiptIdList":null,"summaryReceiptCounts":null,"dishReceiptCounts":null,"vendorStr":"未知厂商","widthStr":"80mm","linkTypeStr":"蓝牙打印机","outputTypeStr":"热敏/针式打印机","standbyPrinterName":null}]
	 * errmsg : 0
	 */

	private int           result;
	private String        errmsg;
	private List<Printer> content;

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

	public List<Printer> getContent() {
		return content;
	}

	public void setContent(List<Printer> content) {
		this.content = content;
	}

}
