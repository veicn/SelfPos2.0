package com.acewill.slefpos.bean.meituanbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/2/28 13:23
 * Desc：
 */
public class ValidationSetoutResult {

	/**
	 * result : 1
	 * errmsg :
	 * content : {"result":1,"message":"","count":1,"minConsume":1,"couponBuyPrice":0.001,"dealPrice":0.001,"dealValue":0.001,"couponCode":"","dealBeginTime":"","couponEndTime":"","dealId":1,"dealTitle":"","dealMenu":[{"content":"","specification":"","price":"","total":"","type":""}]}
	 */

	private int result;
	private String      errmsg;
	private ContentBean content;

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

	public ContentBean getContent() {
		return content;
	}

	public void setContent(ContentBean content) {
		this.content = content;
	}

	public static class ContentBean {
		/**
		 * result : 1
		 * message :
		 * count : 1
		 * minConsume : 1
		 * couponBuyPrice : 0.001
		 * dealPrice : 0.001
		 * dealValue : 0.001
		 * couponCode :
		 * dealBeginTime :
		 * couponEndTime :
		 * dealId : 1
		 * dealTitle :
		 * dealMenu : [{"content":"","specification":"","price":"","total":"","type":""}]
		 */

		private int result;
		private String             message;
		private int                count;
		private int                minConsume;
		private double             couponBuyPrice;
		private double             dealPrice;
		private double             dealValue;
		private String             couponCode;
		private String             dealBeginTime;
		private String             couponEndTime;
		private int                dealId;
		private String             dealTitle;
		private List<DealMenuBean> dealMenu;

		public int getResult() {
			return result;
		}

		public void setResult(int result) {
			this.result = result;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getMinConsume() {
			return minConsume;
		}

		public void setMinConsume(int minConsume) {
			this.minConsume = minConsume;
		}

		public double getCouponBuyPrice() {
			return couponBuyPrice;
		}

		public void setCouponBuyPrice(double couponBuyPrice) {
			this.couponBuyPrice = couponBuyPrice;
		}

		public double getDealPrice() {
			return dealPrice;
		}

		public void setDealPrice(double dealPrice) {
			this.dealPrice = dealPrice;
		}

		public double getDealValue() {
			return dealValue;
		}

		public void setDealValue(double dealValue) {
			this.dealValue = dealValue;
		}

		public String getCouponCode() {
			return couponCode;
		}

		public void setCouponCode(String couponCode) {
			this.couponCode = couponCode;
		}

		public String getDealBeginTime() {
			return dealBeginTime;
		}

		public void setDealBeginTime(String dealBeginTime) {
			this.dealBeginTime = dealBeginTime;
		}

		public String getCouponEndTime() {
			return couponEndTime;
		}

		public void setCouponEndTime(String couponEndTime) {
			this.couponEndTime = couponEndTime;
		}

		public int getDealId() {
			return dealId;
		}

		public void setDealId(int dealId) {
			this.dealId = dealId;
		}

		public String getDealTitle() {
			return dealTitle;
		}

		public void setDealTitle(String dealTitle) {
			this.dealTitle = dealTitle;
		}

		public List<DealMenuBean> getDealMenu() {
			return dealMenu;
		}

		public void setDealMenu(List<DealMenuBean> dealMenu) {
			this.dealMenu = dealMenu;
		}

		public static class DealMenuBean {
			/**
			 * content :
			 * specification :
			 * price :
			 * total :
			 * type :
			 */

			private String content;
			private String specification;
			private String price;
			private String total;
			private String type;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getSpecification() {
				return specification;
			}

			public void setSpecification(String specification) {
				this.specification = specification;
			}

			public String getPrice() {
				return price;
			}

			public void setPrice(String price) {
				this.price = price;
			}

			public String getTotal() {
				return total;
			}

			public void setTotal(String total) {
				this.total = total;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}
		}
	}
}
