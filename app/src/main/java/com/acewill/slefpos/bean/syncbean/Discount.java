package com.acewill.slefpos.bean.syncbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/6/14 11:41
 * Desc：
 */
public class Discount {
	private static Discount                                mDiscount;
	private static List<DiscountAmount> mDiscountAmountList;

	/**
	 * 获取优惠对象
	 *
	 * @return
	 */
	public static Discount getInstance() {
		if (mDiscount == null) {
			mDiscount = new Discount();
		}
		return mDiscount;
	}

	public   List<DiscountAmount> getDiscountAmountList() {
		if (mDiscountAmountList == null) {
			mDiscountAmountList = new ArrayList<>();
		}
		return mDiscountAmountList;
	}

	public void clear() {
		if (mDiscountAmountList != null)
			mDiscountAmountList.clear();
	}
}
