package com.acewill.paylibrary.epay;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/24 11:56
 * Desc：
 */
public class AliPayResult {

	/**
	 * alipay_trade_pay_response : {"code":"10000","msg":"Success","buyer_logon_id":"138****0060","buyer_pay_amount":"0.01","buyer_user_id":"2088702226137295","fund_bill_list":[{"amount":"0.01","fund_channel":"PCREDIT"}],"gmt_payment":"2018-04-24 11:54:09","invoice_amount":"0.01","out_trade_no":"1011524542047963","point_amount":"0.00","receipt_amount":"0.01","total_amount":"0.01","trade_no":"2018042421001004290577764238"}
	 * sign : Xiba0YAQ+I/7jiTKrhJf4aoEmASDQq1xjAUDp8+MxD7aVc1JVAmqSMZLKA/lMYw8mmN4d8e1whSkGipFWoLDmk1wCK6jNp9OFfsvbJxHegvx7wBkgrgIS7UoKh+50OxtAMqs5Lj0KMAfHTm0vLlSdidPmtKEqs3ihD4EarMzQJY=
	 */

	private AlipayTradePayResponseBean alipay_trade_pay_response;
	private String sign;

	public AlipayTradePayResponseBean getAlipay_trade_pay_response() {
		return alipay_trade_pay_response;
	}

	public void setAlipay_trade_pay_response(AlipayTradePayResponseBean alipay_trade_pay_response) {
		this.alipay_trade_pay_response = alipay_trade_pay_response;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public static class AlipayTradePayResponseBean {
		/**
		 * code : 10000
		 * msg : Success
		 * buyer_logon_id : 138****0060
		 * buyer_pay_amount : 0.01
		 * buyer_user_id : 2088702226137295
		 * fund_bill_list : [{"amount":"0.01","fund_channel":"PCREDIT"}]
		 * gmt_payment : 2018-04-24 11:54:09
		 * invoice_amount : 0.01
		 * out_trade_no : 1011524542047963
		 * point_amount : 0.00
		 * receipt_amount : 0.01
		 * total_amount : 0.01
		 * trade_no : 2018042421001004290577764238
		 */

		private String code;
		private String                 msg;
		private String                 buyer_logon_id;
		private String                 buyer_pay_amount;
		private String                 buyer_user_id;
		private String                 gmt_payment;
		private String                 invoice_amount;
		private String                 out_trade_no;
		private String                 point_amount;
		private String                 receipt_amount;
		private String                 total_amount;
		private String                 trade_no;
		private String                 qr_code;
		private List<FundBillListBean> fund_bill_list;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getBuyer_logon_id() {
			return buyer_logon_id;
		}

		public void setBuyer_logon_id(String buyer_logon_id) {
			this.buyer_logon_id = buyer_logon_id;
		}

		public String getBuyer_pay_amount() {
			return buyer_pay_amount;
		}

		public void setBuyer_pay_amount(String buyer_pay_amount) {
			this.buyer_pay_amount = buyer_pay_amount;
		}

		public String getBuyer_user_id() {
			return buyer_user_id;
		}

		public void setBuyer_user_id(String buyer_user_id) {
			this.buyer_user_id = buyer_user_id;
		}

		public String getGmt_payment() {
			return gmt_payment;
		}

		public void setGmt_payment(String gmt_payment) {
			this.gmt_payment = gmt_payment;
		}

		public String getInvoice_amount() {
			return invoice_amount;
		}

		public void setInvoice_amount(String invoice_amount) {
			this.invoice_amount = invoice_amount;
		}

		public String getOut_trade_no() {
			return out_trade_no;
		}

		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}

		public String getPoint_amount() {
			return point_amount;
		}

		public void setPoint_amount(String point_amount) {
			this.point_amount = point_amount;
		}

		public String getReceipt_amount() {
			return receipt_amount;
		}

		public void setReceipt_amount(String receipt_amount) {
			this.receipt_amount = receipt_amount;
		}

		public String getTotal_amount() {
			return total_amount;
		}

		public void setTotal_amount(String total_amount) {
			this.total_amount = total_amount;
		}

		public String getTrade_no() {
			return trade_no;
		}

		public void setTrade_no(String trade_no) {
			this.trade_no = trade_no;
		}

		public List<FundBillListBean> getFund_bill_list() {
			return fund_bill_list;
		}

		public void setFund_bill_list(List<FundBillListBean> fund_bill_list) {
			this.fund_bill_list = fund_bill_list;
		}

		public String getQr_code() {
			return qr_code;
		}

		public void setQr_code(String qr_code) {
			this.qr_code = qr_code;
		}

		public static class FundBillListBean {
			/**
			 * amount : 0.01
			 * fund_channel : PCREDIT
			 */

			private String amount;
			private String fund_channel;

			public String getAmount() {
				return amount;
			}

			public void setAmount(String amount) {
				this.amount = amount;
			}

			public String getFund_channel() {
				return fund_channel;
			}

			public void setFund_channel(String fund_channel) {
				this.fund_channel = fund_channel;
			}
		}
	}
}
