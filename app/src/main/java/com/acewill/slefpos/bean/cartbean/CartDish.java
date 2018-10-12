package com.acewill.slefpos.bean.cartbean;

import android.os.Parcelable;

import com.acewill.slefpos.bean.uibean.UIDish;

/**
 * Author：Anch
 * Date：2018/2/5 16:51
 * Desc：
 */
public class CartDish extends UIDish implements Parcelable {
	private String kindId;
	/**
	 * 商品的优惠金额， 不一定为正值，还有可能为负值
	 * <p>
	 * 正值表示的是 商品的优惠
	 * <p>
	 * 负值表示的是 商品的加价
	 */
	private float  discountAmount;

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}
}
