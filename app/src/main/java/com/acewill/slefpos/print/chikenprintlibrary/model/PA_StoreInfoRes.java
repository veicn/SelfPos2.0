package com.acewill.slefpos.print.chikenprintlibrary.model;

import java.util.List;

/**
 * Author：Anch
 * Date：2017/5/4 20:14
 * Desc：
 */
public class PA_StoreInfoRes {

	/**
	 * result : 0
	 * content : {"appId":"42458671","brandId":40,"storeId":5,"wipeZero":2,"tableService":false,"cardNumberMode":false,"callNumberMode":true,"payBeforeMeal":true,"cashOpenDrawer":true,"cardOpenDrawer":true,"allowSpecialDishDiscount":false,"sleepTime":null,"shiftPrintReport":true,"pirntDishShortName":false,"printRetreatDish":false,"printWhenRetreat":true,"onlineOrderToKitchen":false,"retreatCheckAuthority":true,"deleteCheckAuthority":false,"openDrawerCheckAuthority":true,"openPosCheckAuthority":false,"autoDayoutTime":"23:59","createDishFromPOS":false,"customerInfoForWaimai":false,"printInvoiceBarcode":false,"linkStores":[],"printConfiguration":{"appId":"42458671","brandId":40,"storeId":5,"checkoutReceiptCounts":1,"guestReceiptCounts":1}}
	 * errmsg : 0
	 */

	private int result;
	private ContentBean content;
	private String      errmsg;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ContentBean getContent() {
		return content;
	}

	public void setContent(ContentBean content) {
		this.content = content;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public static class ContentBean {
		/**
		 * appId : 42458671
		 * brandId : 40
		 * storeId : 5
		 * wipeZero : 2
		 * tableService : false
		 * cardNumberMode : false
		 * callNumberMode : true
		 * payBeforeMeal : true
		 * cashOpenDrawer : true
		 * cardOpenDrawer : true
		 * allowSpecialDishDiscount : false
		 * sleepTime : null
		 * shiftPrintReport : true
		 * pirntDishShortName : false
		 * printRetreatDish : false
		 * printWhenRetreat : true
		 * onlineOrderToKitchen : false
		 * retreatCheckAuthority : true
		 * deleteCheckAuthority : false
		 * openDrawerCheckAuthority : true
		 * openPosCheckAuthority : false
		 * autoDayoutTime : 23:59
		 * createDishFromPOS : false
		 * customerInfoForWaimai : false
		 * printInvoiceBarcode : false
		 * linkStores : []
		 * printConfiguration : {"appId":"42458671","brandId":40,"storeId":5,"checkoutReceiptCounts":1,"guestReceiptCounts":1}
		 */

		private String appId;
		private int                    brandId;
		private int                    storeId;
		private int                    wipeZero;
		private boolean                tableService;
		private boolean                cardNumberMode;
		private boolean                callNumberMode;
		private boolean                payBeforeMeal;
		private boolean                cashOpenDrawer;
		private boolean                cardOpenDrawer;
		private boolean                allowSpecialDishDiscount;
		private Object                 sleepTime;
		private boolean                shiftPrintReport;
		private boolean                pirntDishShortName;
		private boolean                printRetreatDish;
		private boolean                printWhenRetreat;
		private boolean                onlineOrderToKitchen;
		private boolean                retreatCheckAuthority;
		private boolean                deleteCheckAuthority;
		private boolean                openDrawerCheckAuthority;
		private boolean                openPosCheckAuthority;
		private String                 autoDayoutTime;
		private boolean                createDishFromPOS;
		private boolean                customerInfoForWaimai;
		private boolean                printInvoiceBarcode;
		private PrintConfigurationBean printConfiguration;
		private List<?>                linkStores;

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public int getBrandId() {
			return brandId;
		}

		public void setBrandId(int brandId) {
			this.brandId = brandId;
		}

		public int getStoreId() {
			return storeId;
		}

		public void setStoreId(int storeId) {
			this.storeId = storeId;
		}

		public int getWipeZero() {
			return wipeZero;
		}

		public void setWipeZero(int wipeZero) {
			this.wipeZero = wipeZero;
		}

		public boolean isTableService() {
			return tableService;
		}

		public void setTableService(boolean tableService) {
			this.tableService = tableService;
		}

		public boolean isCardNumberMode() {
			return cardNumberMode;
		}

		public void setCardNumberMode(boolean cardNumberMode) {
			this.cardNumberMode = cardNumberMode;
		}

		public boolean isCallNumberMode() {
			return callNumberMode;
		}

		public void setCallNumberMode(boolean callNumberMode) {
			this.callNumberMode = callNumberMode;
		}

		public boolean isPayBeforeMeal() {
			return payBeforeMeal;
		}

		public void setPayBeforeMeal(boolean payBeforeMeal) {
			this.payBeforeMeal = payBeforeMeal;
		}

		public boolean isCashOpenDrawer() {
			return cashOpenDrawer;
		}

		public void setCashOpenDrawer(boolean cashOpenDrawer) {
			this.cashOpenDrawer = cashOpenDrawer;
		}

		public boolean isCardOpenDrawer() {
			return cardOpenDrawer;
		}

		public void setCardOpenDrawer(boolean cardOpenDrawer) {
			this.cardOpenDrawer = cardOpenDrawer;
		}

		public boolean isAllowSpecialDishDiscount() {
			return allowSpecialDishDiscount;
		}

		public void setAllowSpecialDishDiscount(boolean allowSpecialDishDiscount) {
			this.allowSpecialDishDiscount = allowSpecialDishDiscount;
		}

		public Object getSleepTime() {
			return sleepTime;
		}

		public void setSleepTime(Object sleepTime) {
			this.sleepTime = sleepTime;
		}

		public boolean isShiftPrintReport() {
			return shiftPrintReport;
		}

		public void setShiftPrintReport(boolean shiftPrintReport) {
			this.shiftPrintReport = shiftPrintReport;
		}

		public boolean isPirntDishShortName() {
			return pirntDishShortName;
		}

		public void setPirntDishShortName(boolean pirntDishShortName) {
			this.pirntDishShortName = pirntDishShortName;
		}

		public boolean isPrintRetreatDish() {
			return printRetreatDish;
		}

		public void setPrintRetreatDish(boolean printRetreatDish) {
			this.printRetreatDish = printRetreatDish;
		}

		public boolean isPrintWhenRetreat() {
			return printWhenRetreat;
		}

		public void setPrintWhenRetreat(boolean printWhenRetreat) {
			this.printWhenRetreat = printWhenRetreat;
		}

		public boolean isOnlineOrderToKitchen() {
			return onlineOrderToKitchen;
		}

		public void setOnlineOrderToKitchen(boolean onlineOrderToKitchen) {
			this.onlineOrderToKitchen = onlineOrderToKitchen;
		}

		public boolean isRetreatCheckAuthority() {
			return retreatCheckAuthority;
		}

		public void setRetreatCheckAuthority(boolean retreatCheckAuthority) {
			this.retreatCheckAuthority = retreatCheckAuthority;
		}

		public boolean isDeleteCheckAuthority() {
			return deleteCheckAuthority;
		}

		public void setDeleteCheckAuthority(boolean deleteCheckAuthority) {
			this.deleteCheckAuthority = deleteCheckAuthority;
		}

		public boolean isOpenDrawerCheckAuthority() {
			return openDrawerCheckAuthority;
		}

		public void setOpenDrawerCheckAuthority(boolean openDrawerCheckAuthority) {
			this.openDrawerCheckAuthority = openDrawerCheckAuthority;
		}

		public boolean isOpenPosCheckAuthority() {
			return openPosCheckAuthority;
		}

		public void setOpenPosCheckAuthority(boolean openPosCheckAuthority) {
			this.openPosCheckAuthority = openPosCheckAuthority;
		}

		public String getAutoDayoutTime() {
			return autoDayoutTime;
		}

		public void setAutoDayoutTime(String autoDayoutTime) {
			this.autoDayoutTime = autoDayoutTime;
		}

		public boolean isCreateDishFromPOS() {
			return createDishFromPOS;
		}

		public void setCreateDishFromPOS(boolean createDishFromPOS) {
			this.createDishFromPOS = createDishFromPOS;
		}

		public boolean isCustomerInfoForWaimai() {
			return customerInfoForWaimai;
		}

		public void setCustomerInfoForWaimai(boolean customerInfoForWaimai) {
			this.customerInfoForWaimai = customerInfoForWaimai;
		}

		public boolean isPrintInvoiceBarcode() {
			return printInvoiceBarcode;
		}

		public void setPrintInvoiceBarcode(boolean printInvoiceBarcode) {
			this.printInvoiceBarcode = printInvoiceBarcode;
		}

		public PrintConfigurationBean getPrintConfiguration() {
			return printConfiguration;
		}

		public void setPrintConfiguration(PrintConfigurationBean printConfiguration) {
			this.printConfiguration = printConfiguration;
		}

		public List<?> getLinkStores() {
			return linkStores;
		}

		public void setLinkStores(List<?> linkStores) {
			this.linkStores = linkStores;
		}

		public static class PrintConfigurationBean {
			/**
			 * appId : 42458671
			 * brandId : 40
			 * storeId : 5
			 * checkoutReceiptCounts : 1
			 * guestReceiptCounts : 1
			 */

			private String appId;
			private int brandId;
			private int storeId;
			private int checkoutReceiptCounts;
			private int guestReceiptCounts;

			public String getAppId() {
				return appId;
			}

			public void setAppId(String appId) {
				this.appId = appId;
			}

			public int getBrandId() {
				return brandId;
			}

			public void setBrandId(int brandId) {
				this.brandId = brandId;
			}

			public int getStoreId() {
				return storeId;
			}

			public void setStoreId(int storeId) {
				this.storeId = storeId;
			}

			public int getCheckoutReceiptCounts() {
				return checkoutReceiptCounts;
			}

			public void setCheckoutReceiptCounts(int checkoutReceiptCounts) {
				this.checkoutReceiptCounts = checkoutReceiptCounts;
			}

			public int getGuestReceiptCounts() {
				return guestReceiptCounts;
			}

			public void setGuestReceiptCounts(int guestReceiptCounts) {
				this.guestReceiptCounts = guestReceiptCounts;
			}
		}
	}
}
