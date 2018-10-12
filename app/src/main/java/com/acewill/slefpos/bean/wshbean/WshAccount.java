package com.acewill.slefpos.bean.wshbean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Author：Anch
 * Date：2017/5/27 13:07
 * Desc：
 */
public class WshAccount implements Serializable {


	/**
	 * uid : 61568523574414062
	 * uno : 1300575
	 * type : wx
	 * name :
	 * phone :
	 * birthday : 1970-01-01
	 * gender :
	 * registered : 2017-05-27
	 * openid : oLkhtwDRtp8sHP7xEhNAEwQx3XXo
	 * grade : 2004800
	 * gradeName : 会员卡
	 * balance : 0
	 * credit : 0
	 * usecredit : true
	 * inEffect : true
	 * uActualNo : 0
	 * coupons : [{"template_id":"2049287","coupon_ids":["1568523574925141"],"title":"2元代金券","deno":2,"type":1,"sids":[1174517085],"effective_time":"2017-05-27 00:00:00","failure_time":"2017-06-02 23:59:59","limitations":["每次消费最多可以使用1张","不可以和其他种类的券混用"],"enable_amount":0,"max_use":1,"mix_use":false}]
	 */
	private String                 cno;
	private String                 uid;
	private String                 uno;
	private String                 type;
	private String                 name;
	private String                 phone;
	private String                 birthday;
	private String                 gender;
	private String                 registered;
	private String                 openid;
	private String                 grade;
	private String                 gradeName;
	private float                  balance;
	private int                    credit;
	private boolean                usecredit;
	private boolean                inEffect;
	private String                 uActualNo;
	private List<WshAccountCoupon> coupons;
	private  List<WshActivity> acts;

	public WshAccount myclone() {
		WshAccount outer = null;
		try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream    oos  = new ObjectOutputStream(baos);
			oos.writeObject(this);
			// 将流序列化成对象
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream    ois  = new ObjectInputStream(bais);
			outer = (WshAccount) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return outer;
	}

	public TradeVerify getTrade_verify() {
		return trade_verify;
	}

	public void setTrade_verify(TradeVerify trade_verify) {
		this.trade_verify = trade_verify;
	}

	public String getTrade_verify_type() {
		return trade_verify_type;
	}

	public void setTrade_verify_type(String trade_verify_type) {
		this.trade_verify_type = trade_verify_type;
	}

	private TradeVerify trade_verify;
	private String      trade_verify_type;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegistered() {
		return registered;
	}

	public void setRegistered(String registered) {
		this.registered = registered;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isUsecredit() {
		return usecredit;
	}

	public void setUsecredit(boolean usecredit) {
		this.usecredit = usecredit;
	}

	public boolean isInEffect() {
		return inEffect;
	}

	public void setInEffect(boolean inEffect) {
		this.inEffect = inEffect;
	}

	public String getUActualNo() {
		return uActualNo;
	}

	public void setUActualNo(String uActualNo) {
		this.uActualNo = uActualNo;
	}

	public List<WshAccountCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<WshAccountCoupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "WshAccount{" +
				"uid='" + uid + '\'' +
				", uno='" + uno + '\'' +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", birthday='" + birthday + '\'' +
				", gender='" + gender + '\'' +
				", registered='" + registered + '\'' +
				", openid='" + openid + '\'' +
				", gradeName='" + gradeName + '\'' +
				", balance=" + balance +
				", credit=" + credit +
				", usecredit=" + usecredit +
				", inEffect=" + inEffect +
				", uActualNo='" + uActualNo + '\'' +
				", coupons=" + coupons +
				", trade_verify=" + trade_verify +
				", trade_verify_type='" + trade_verify_type + '\'' +
				'}';
	}

	public int getCoponSize() {
		int sum = 0;
		if (getCoupons() == null || getCoupons().size() == 0) {
			return sum;
		}
		for (int i = 0; i < getCoupons().size(); i++) {
			WshAccountCoupon coupon = getCoupons().get(i);
			//			if (coupon.getType()==1){
			sum += coupon.getCoupon_ids().size();
			//			}
		}
		return sum;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<WshActivity> getActs() {
		return acts;
	}

	public void setActs(List<WshActivity> acts) {
		this.acts = acts;
	}

	public class TradeVerify {

		private boolean charge_verify;

		public boolean isCharge_verify() {
			return charge_verify;
		}

		public void setCharge_verify(boolean charge_verify) {
			this.charge_verify = charge_verify;
		}

		public boolean isCredit_verify() {
			return credit_verify;
		}

		public void setCredit_verify(boolean credit_verify) {
			this.credit_verify = credit_verify;
		}

		public boolean isCash_coupon_verify() {
			return cash_coupon_verify;
		}

		public void setCash_coupon_verify(boolean cash_coupon_verify) {
			this.cash_coupon_verify = cash_coupon_verify;
		}

		public boolean isGift_coupon_verify() {
			return gift_coupon_verify;
		}

		public void setGift_coupon_verify(boolean gift_coupon_verify) {
			this.gift_coupon_verify = gift_coupon_verify;
		}

		private boolean credit_verify;
		private boolean cash_coupon_verify;
		private boolean gift_coupon_verify;

		@Override
		public String toString() {
			return "TradeVerify{" +
					"charge_verify=" + charge_verify +
					", credit_verify=" + credit_verify +
					", cash_coupon_verify=" + cash_coupon_verify +
					", gift_coupon_verify=" + gift_coupon_verify +
					'}';
		}
	}
}
