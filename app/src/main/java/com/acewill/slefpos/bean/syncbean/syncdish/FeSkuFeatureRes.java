package com.acewill.slefpos.bean.syncbean.syncdish;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/25 17:46
 * Desc：產品口味(特徵屬性,使用不會有價格差異).
 */
public class FeSkuFeatureRes {

	private List<FeSkuFeature> FeSkuFeature;

	public List<FeSkuFeature> getFeSkuFeature() {
		return FeSkuFeature;
	}

	public void setFeSkuFeature(List<FeSkuFeature> FeSkuFeature) {
		this.FeSkuFeature = FeSkuFeature;
	}
}
