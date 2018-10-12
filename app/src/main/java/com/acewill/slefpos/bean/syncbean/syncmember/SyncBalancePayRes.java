package com.acewill.slefpos.bean.syncbean.syncmember;

/**
 * Author：Anch
 * Date：2018/6/1 13:47
 * Desc：
 */
public class SyncBalancePayRes {

	/**
	 * code : 0
	 * data : {"memberNo":"0605100005287415","balance":9990.34,"shopId":"vptKCaOtSn-ItRy4CVjt4w","txNo":"888888201806011536255013","txAmount":1,"txStatus":"S"}
	 */

	private int      code;
	private String   message;
	private DataBean data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * memberNo : 0605100005287415
		 * balance : 9990.34
		 * shopId : vptKCaOtSn-ItRy4CVjt4w
		 * txNo : 888888201806011536255013
		 * txAmount : 1
		 * txStatus : S
		 */

		private String memberNo;
		private float balance;
		private String shopId;
		private String txNo;
		private float    txAmount;
		private String txStatus;

		public String getMemberNo() {
			return memberNo;
		}

		public void setMemberNo(String memberNo) {
			this.memberNo = memberNo;
		}

		public float getBalance() {
			return balance;
		}

		public void setBalance(float balance) {
			this.balance = balance;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getTxNo() {
			return txNo;
		}

		public void setTxNo(String txNo) {
			this.txNo = txNo;
		}

		public float getTxAmount() {
			return txAmount;
		}

		public void setTxAmount(float txAmount) {
			this.txAmount = txAmount;
		}

		public String getTxStatus() {
			return txStatus;
		}

		public void setTxStatus(String txStatus) {
			this.txStatus = txStatus;
		}
	}
}
