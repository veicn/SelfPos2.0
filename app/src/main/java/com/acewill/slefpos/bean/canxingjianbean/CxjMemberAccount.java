package com.acewill.slefpos.bean.canxingjianbean;

import com.acewill.slefpos.bean.wshbean.WshActivity;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/8/15 14:29
 * Desc：
 */
public class CxjMemberAccount {

	/**
	 * cno : 1399953432269799
	 * name : 谢卓麟
	 * type : 实体卡
	 * gradeName : 奥琦玮服务
	 * grade : 3009925
	 * balance : 2220665
	 * credit : 4727
	 * coupons : [{"name":"66元代金券","price":"66","type":1,"typeName":"代金券","starttime":"2018-06-21","endtime":"2019-06-20","enableAmount":0,"maxUse":"1","mixUse":true,"templateId":"8926052","did":"","id":"1603881509206399971"},{"name":"66元代金券","price":"66","type":1,"typeName":"代金券","starttime":"2018-06-25","endtime":"2019-06-24","enableAmount":0,"maxUse":"1","mixUse":true,"templateId":"8926052","did":"","id":"1604236347614791885"}]
	 * acts : [{"maid":"3077489","name":"满140返40元优惠券"}]
	 */

	private String cno;
	private String           name;
	private String           type;
	private String           gradeName;
	private String           grade;
	private String           balance;
	private String           credit;
	private List<CxjCoupons> coupons;
	private List<WshActivity>   acts;

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public List<CxjCoupons> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CxjCoupons> coupons) {
		this.coupons = coupons;
	}

	public List<WshActivity> getActs() {
		return acts;
	}

	public void setActs(List<WshActivity> acts) {
		this.acts = acts;
	}

	public static class CxjCoupons {
		/**
		 * name : 66元代金券
		 * price : 66
		 * type : 1
		 * typeName : 代金券
		 * starttime : 2018-06-21
		 * endtime : 2019-06-20
		 * enableAmount : 0
		 * maxUse : 1
		 * mixUse : true
		 * templateId : 8926052
		 * did :
		 * id : 1603881509206399971
		 */

		private String name;
		private String  price;
		private int     type;
		private String  typeName;
		private String  starttime;
		private String  endtime;
		private int     enableAmount;
		private String  maxUse;
		private boolean mixUse;
		private String  templateId;
		private String  did;
		private String  id;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getStarttime() {
			return starttime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}

		public String getEndtime() {
			return endtime;
		}

		public void setEndtime(String endtime) {
			this.endtime = endtime;
		}

		public int getEnableAmount() {
			return enableAmount;
		}

		public void setEnableAmount(int enableAmount) {
			this.enableAmount = enableAmount;
		}

		public String getMaxUse() {
			return maxUse;
		}

		public void setMaxUse(String maxUse) {
			this.maxUse = maxUse;
		}

		public boolean isMixUse() {
			return mixUse;
		}

		public void setMixUse(boolean mixUse) {
			this.mixUse = mixUse;
		}

		public String getTemplateId() {
			return templateId;
		}

		public void setTemplateId(String templateId) {
			this.templateId = templateId;
		}

		public String getDid() {
			return did;
		}

		public void setDid(String did) {
			this.did = did;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

}
