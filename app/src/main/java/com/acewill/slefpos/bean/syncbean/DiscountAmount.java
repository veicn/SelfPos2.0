package com.acewill.slefpos.bean.syncbean;

import com.acewill.slefpos.bean.cartbean.CartDish;

/**
 * Author：Anch
 * Date：2018/6/14 11:38
 * Desc：优惠明细
 */
public class DiscountAmount {
	private String discountAmountType;//优惠的类型
	private float  discountAmountValue;//优惠值。如：折扣率百分比(9折记为90)、优惠券面值、抵用的积分值

	public float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

	private float discountAmount;//优惠的金额


	public String getDiscountAmountName() {
		return discountAmountName;
	}

	public void setDiscountAmountName(String discountAmountName) {
		this.discountAmountName = discountAmountName;
	}

	private String   discountAmountName;//优惠的金额
	private CartDish mCartDish;//优惠的金额

	public DiscountAmount(String discountAmountName, float discountAmount, String discountAmountType, float discountAmountValue,  CartDish cartDish) {
		this.discountAmountName = discountAmountName;
		this.discountAmount = discountAmount;
		this.discountAmountType = discountAmountType;
		this.discountAmountValue = discountAmountValue;
		this.mCartDish = cartDish;
	}

	public String getDiscountAmountType() {
		return discountAmountType;
	}

	public void setDiscountAmountType(String discountAmountType) {
		this.discountAmountType = discountAmountType;
	}

	public float getDiscountAmountValue() {
		return discountAmountValue;
	}

	public void setDiscountAmountValue(float discountAmountValue) {
		this.discountAmountValue = discountAmountValue;
	}
}
