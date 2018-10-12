package com.acewill.slefpos.bean.syncbean.syncdish;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/25 17:09
 * Desc：產品加料(附加物品,使用會造成價格增減).
 */
public class FeSkuMixRes {


	private List<FeSkuMix> FeSkuMix;

	public List<FeSkuMix> getFeSkuMix() {
		return FeSkuMix;
	}

	public void setFeSkuMix(List<FeSkuMix> FeSkuMix) {
		this.FeSkuMix = FeSkuMix;
	}

}
