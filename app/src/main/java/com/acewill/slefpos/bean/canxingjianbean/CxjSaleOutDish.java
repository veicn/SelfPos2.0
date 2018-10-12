package com.acewill.slefpos.bean.canxingjianbean;

import java.util.ArrayList;

/**
 * Author：Anch
 * Date：2018/7/31 11:34
 * Desc：
 */
public class CxjSaleOutDish {
	private int    success;

	public String getDids() {
		return dids;
	}

	public void setDids(String dids) {
		this.dids = dids;
	}

	/**
	 * 估清菜品id,以逗号分隔
	 */
	private String dids;

	public ArrayList<Dish> getData() {
		return data;
	}

	public void setData(ArrayList<Dish> data) {
		this.data = data;
	}

	private ArrayList<Dish> data;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public class Dish{
		private String did;
		private String leftamount;

		public String getDid() {
			return did;
		}

		public void setDid(String did) {
			this.did = did;
		}

		public String getLeftamount() {
			return leftamount;
		}

		public void setLeftamount(String leftamount) {
			this.leftamount = leftamount;
		}
	}
}
