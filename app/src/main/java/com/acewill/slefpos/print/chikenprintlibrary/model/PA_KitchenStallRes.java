package com.acewill.slefpos.print.chikenprintlibrary.model;

import java.util.List;

/**
 * Author：Anch
 * Date：2017/5/4 20:14
 * Desc：
 */
public class PA_KitchenStallRes {

	/**
	 * result : 0
	 * content : [{"appid":"42458671","brandid":40,"storeid":5,"stallsid":1021,"stallsName":"其他档口","printerid":1041,"kdsid":1014,"summaryReceiptCounts":0,"dishReceiptCounts":1,"dishIdList":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50],"kitchenPrintMode":"PER_ITEM"},{"appid":"42458671","brandid":40,"storeid":5,"stallsid":1026,"stallsName":"面类档口","printerid":1043,"kdsid":-1,"summaryReceiptCounts":1,"dishReceiptCounts":0,"dishIdList":[1,2,3,4,5,7,8,9,10,12,13,14,15,16,17,19,20,21,22],"kitchenPrintMode":"PER_ITEM"}]
	 * errmsg : 0
	 */

	private int result;
	private String            errmsg;
	private List<ContentBean> content;

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

	public List<ContentBean> getContent() {
		return content;
	}

	public void setContent(List<ContentBean> content) {
		this.content = content;
	}

	public static class ContentBean {
		/**
		 * appid : 42458671
		 * brandid : 40
		 * storeid : 5
		 * stallsid : 1021
		 * stallsName : 其他档口
		 * printerid : 1041
		 * kdsid : 1014
		 * summaryReceiptCounts : 0
		 * dishReceiptCounts : 1
		 * dishIdList : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50]
		 * kitchenPrintMode : PER_ITEM
		 */

		private Long           stallsid;
		private String         stallsName;
		private Integer           printerid;
		private Integer           kdsid;
		private Integer           summaryReceiptCounts;
		private Integer           dishReceiptCounts;
		private String        kitchenPrintMode;
		private List<Long> dishIdList;


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

		public Integer getPrinterid() {
			return printerid;
		}

		public void setPrinterid(Integer printerid) {
			this.printerid = printerid;
		}

		public Integer getKdsid() {
			return kdsid;
		}

		public void setKdsid(Integer kdsid) {
			this.kdsid = kdsid;
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
	}
}
