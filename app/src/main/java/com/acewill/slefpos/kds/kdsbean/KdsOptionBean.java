package com.acewill.slefpos.kds.kdsbean;

import java.io.Serializable;

/**
 * Author：Anch
 * Date：2017/5/28 14:12
 * Desc：
 */
public class KdsOptionBean implements Serializable {
	public String id;//": 1046,
	public String optionName;//": "锅巴加厚",
	public double price;//": 0,
	public boolean required;//": false,
	public String categoryId;//": 1039

	public boolean isSelect;

	public int getBelong() {
		return belong;
	}

	public void setBelong(int belong) {
		this.belong = belong;
	}

	public int belong;//": 1046,

	@Override
	public String toString() {
		return "OptionBean{" +
				"id='" + id + '\'' +
				", optionName='" + optionName + '\'' +
				", price=" + price +
				", required=" + required +
				", categoryId='" + categoryId + '\'' +
				", isSelect=" + isSelect +
				", belong=" + belong +
				'}';
	}
}