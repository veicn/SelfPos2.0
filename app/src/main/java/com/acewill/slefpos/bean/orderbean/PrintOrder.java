package com.acewill.slefpos.bean.orderbean;

import android.text.TextUtils;

import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;

/**
 * Created by Administrator on 2018/11/29 0029.
 */

public class PrintOrder {

	private String welcome;//门店名称

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String getEatmethod() {
		return eatmethod;
	}

	public void setEatmethod(String eatmethod) {
		this.eatmethod = eatmethod;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCasher() {
		return casher;
	}

	public void setCasher(String casher) {
		this.casher = casher;
	}

	public String getDishList() {
		return dishList;
	}

	public void setDishList(String dishList) {
		this.dishList = dishList;
	}

	public String getCostList() {
		return costList;
	}

	public void setCostList(String costList) {
		this.costList = costList;
	}

	public String getPayInfoList() {
		return payInfoList;
	}

	public void setPayInfoList(String payInfoList) {
		this.payInfoList = payInfoList;
	}

	public String getMemberPayInfo() {
		return memberPayInfo;
	}

	public void setMemberPayInfo(String memberPayInfo) {
		this.memberPayInfo = memberPayInfo;
	}

	public String getWelcomefood() {
		return welcomefood;
	}

	public void setWelcomefood(String welcomefood) {
		this.welcomefood = welcomefood;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String  eatmethod;//就餐方式
	private String  orderId;//订单id
	private String  createTime;//下单时间
	private String  casher;//收银员
	private String  dishList;//菜品列表
	private String  costList;//消费详情
	private String  payInfoList;//支付详情
	private String  memberPayInfo;//会员信息
	private String  welcomefood;//门店电话
	private String  address;//门店地址
	private String  jyj_address;//吉野家地址
	private String  callId;
	private boolean isMember;
	private String  memberNameAndPhone;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getJyj_address() {
		return jyj_address;
	}

	public void setJyj_address(String jyj_address) {
		this.jyj_address = jyj_address;
	}

	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean member) {
		isMember = member;
	}

	public String getMemberNameAndPhone() {
		return memberNameAndPhone;
	}

	public void setMemberNameAndPhone() {
		WshAccount account = WshDataProvider.getWshAccount();

		String phone = account.getPhone();
		if (!TextUtils.isEmpty(phone) && phone.length() == 11) {
			String char1 = phone.substring(0, 3);
			String char2 = phone.substring(7, 11);
			phone = char1 + "****" + char2;
		} else {
			phone = "";
		}
		//		for (int i = 0; i < phone.length(); i++) {
		//			if (i == 3 || i == 4 || i == 5 || i == 6) {
		//				phone = phone.replace(phone.charAt(i), '*');
		//			}
		//		}

		String name = account.getName();
		if (!TextUtils.isEmpty(phone)) {
			for (int i = 0; i < name.length(); i++) {
				if (i != 0) {
					name = name.replace(name.charAt(i), '*');
				}
			}
		} else {
			name = "";
		}
		this.memberNameAndPhone = name + "(" + phone + ")";
	}
}
