package com.acewill.slefpos.bean.syncbean.syncdish;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/25 16:42
 * Desc：组合套餐明細(SKU_COMBO_KIND的明細).
 */
public class FeSkuComboRes {

	public List<FeSkuCombo> getFeSkuCombo() {
		return FeSkuCombo;
	}

	public void setFeSkuCombo(List<FeSkuCombo> feSkuCombo) {
		FeSkuCombo = feSkuCombo;
	}

	private List<FeSkuCombo> FeSkuCombo;


}
