package com.acewill.slefpos.bean.canxingjianbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/8/15 11:40
 * Desc：
 */
public class CXJPrecheckRes {

	/**
	 * success : true
	 * extradiscount : 0
	 * cost : 1.3
	 * costservice : 0
	 * bgiftcoupon : 0
	 * discountamount : 6.70
	 * oid : 243
	 * memberinfo : []
	 * printInfo : []
	 * orderitem : [{"did":"3817","oicost":"1.30","amount":"1.00"}]
	 */

	private boolean success;
	private int                 extradiscount;
	private double              cost;
	private int                 costservice;
	private int                 bgiftcoupon;
	private String              discountamount;
	private String              oid;
	private List<?>             memberinfo;
	private List<?>             printInfo;
	private List<OrderitemBean> orderitem;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getExtradiscount() {
		return extradiscount;
	}

	public void setExtradiscount(int extradiscount) {
		this.extradiscount = extradiscount;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getCostservice() {
		return costservice;
	}

	public void setCostservice(int costservice) {
		this.costservice = costservice;
	}

	public int getBgiftcoupon() {
		return bgiftcoupon;
	}

	public void setBgiftcoupon(int bgiftcoupon) {
		this.bgiftcoupon = bgiftcoupon;
	}

	public String getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(String discountamount) {
		this.discountamount = discountamount;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public List<?> getMemberinfo() {
		return memberinfo;
	}

	public void setMemberinfo(List<?> memberinfo) {
		this.memberinfo = memberinfo;
	}

	public List<?> getPrintInfo() {
		return printInfo;
	}

	public void setPrintInfo(List<?> printInfo) {
		this.printInfo = printInfo;
	}

	public List<OrderitemBean> getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(List<OrderitemBean> orderitem) {
		this.orderitem = orderitem;
	}

	public static class OrderitemBean {
		/**
		 * did : 3817
		 * oicost : 1.30
		 * amount : 1.00
		 */

		private String did;
		private String oicost;
		private String amount;

		public String getDid() {
			return did;
		}

		public void setDid(String did) {
			this.did = did;
		}

		public String getOicost() {
			return oicost;
		}

		public void setOicost(String oicost) {
			this.oicost = oicost;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}
	}
}
