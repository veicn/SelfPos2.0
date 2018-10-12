package com.acewill.slefpos.bean.syncbean.syncdish;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/25 16:09
 * Desc：產品(Stock Keeping Unit:單品項管理) (套餐也屬於一種虛擬商品/品項).
 */
public class FeSkuRes {

	private List<FeSku> FeSku;

	public List<FeSku> getFeSku() {
		return FeSku;
	}

	public void setFeSku(List<FeSku> FeSku) {
		this.FeSku = FeSku;
	}
}
