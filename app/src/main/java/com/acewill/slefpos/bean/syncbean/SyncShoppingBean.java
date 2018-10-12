package com.acewill.slefpos.bean.syncbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/6/5 10:38
 * Desc：
 */
public class SyncShoppingBean {

	/**
	 * bizNo : lAmbE3ViQl61Ua1tDWTm7A
	 * ordersNo : lAmbE3ViQl61Ua1tDWTm7A
	 * orderPromoAmount : 0
	 * orderAmount : 2
	 * totalAmount : 2
	 * totalQty : 1
	 * promoItems : []
	 * items : [{"ouid":"8xgpEpwkQv6HRSEE0HcKRQ","skuId":"22","skuName":"2","qty":1,"usableQty":1,"skuPrice":2,"promoAmount":0,"itemAmount":2,"condimentsItem":[]}]
	 * payments : []
	 */

	private String          bizNo;
	private String          ordersNo;
	private float           orderPromoAmount;
	private float           orderAmount;
	private float           totalAmount;
	private int             totalQty;
	private List<?>         promoItems;
	private List<ItemsBean> items;
	private List<?>         payments;

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getOrdersNo() {
		return ordersNo;
	}

	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}

	public float getOrderPromoAmount() {
		return orderPromoAmount;
	}

	public void setOrderPromoAmount(float orderPromoAmount) {
		this.orderPromoAmount = orderPromoAmount;
	}

	public float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public List<?> getPromoItems() {
		return promoItems;
	}

	public void setPromoItems(List<?> promoItems) {
		this.promoItems = promoItems;
	}

	public List<ItemsBean> getItems() {
		return items;
	}

	public void setItems(List<ItemsBean> items) {
		this.items = items;
	}

	public List<?> getPayments() {
		return payments;
	}

	public void setPayments(List<?> payments) {
		this.payments = payments;
	}

	public static class ItemsBean {
		/**
		 * ouid : 8xgpEpwkQv6HRSEE0HcKRQ
		 * skuId : 22
		 * skuName : 2
		 * qty : 1
		 * usableQty : 1
		 * skuPrice : 2
		 * promoAmount : 0
		 * itemAmount : 2
		 * condimentsItem : []
		 */

		private String               ouid;
		private String               skuId;
		private String               skuName;
		private int                  qty;
		private int                  usableQty;
		private float                skuPrice;
		private float                promoAmount;
		private float                itemAmount;
		private List<CondimentsItem> condimentsItem;

		public static class CondimentsItem {
			private String id;
			private String name;
			private int qty;
			private double price;
			private String sourceType ;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getQty() {
				return qty;
			}

			public void setQty(int qty) {
				this.qty = qty;
			}

			public double getPrice() {
				return price;
			}

			public void setPrice(double price) {
				this.price = price;
			}

			public String getSourceType() {
				return sourceType;
			}

			public void setSourceType(String sourceType) {
				this.sourceType = sourceType;
			}
		}

		public String getOuid() {
			return ouid;
		}

		public void setOuid(String ouid) {
			this.ouid = ouid;
		}

		public String getSkuId() {
			return skuId;
		}

		public void setSkuId(String skuId) {
			this.skuId = skuId;
		}

		public String getSkuName() {
			return skuName;
		}

		public void setSkuName(String skuName) {
			this.skuName = skuName;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		public int getUsableQty() {
			return usableQty;
		}

		public void setUsableQty(int usableQty) {
			this.usableQty = usableQty;
		}

		public float getSkuPrice() {
			return skuPrice;
		}

		public void setSkuPrice(float skuPrice) {
			this.skuPrice = skuPrice;
		}

		public float getPromoAmount() {
			return promoAmount;
		}

		public void setPromoAmount(float promoAmount) {
			this.promoAmount = promoAmount;
		}

		public float getItemAmount() {
			return itemAmount;
		}

		public void setItemAmount(float itemAmount) {
			this.itemAmount = itemAmount;
		}

		public List<CondimentsItem> getCondimentsItem() {
			return condimentsItem;
		}

		public void setCondimentsItem(List<CondimentsItem> condimentsItem) {
			this.condimentsItem = condimentsItem;
		}
	}
}
