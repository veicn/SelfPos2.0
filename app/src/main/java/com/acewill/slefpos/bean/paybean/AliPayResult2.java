package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/4/24 11:56
 * Desc：
 */
public class AliPayResult2{


	/**
	 * alipay_trade_precreate_response : {"code":"10000","msg":"Success","out_trade_no":"1011524550668928","qr_code":"https://qr.alipay.com/bax04617dnbfx6f8a9xw40e7"}
	 * sign : TVGm5Ur5bqTRKTTCL7f5P4WtIEHMbl7zzfbjMayV79tFwH/7pCSHzYCX179xt7iGTuzmBTbPeABefK/XDJ/VTQQa5GAsxHQXYCr4kXlCEC0zZxsWZfmvbWMb6TuTlaL6sESmKjC0HjBqmpbou/0mKjMTPxY7fVy5qtt0Whqm85A=
	 */

	private AlipayTradePrecreateResponseBean alipay_trade_precreate_response;
	private String sign;



	public AlipayTradePrecreateResponseBean getAlipay_trade_precreate_response() {
		return alipay_trade_precreate_response;
	}

	public void setAlipay_trade_precreate_response(AlipayTradePrecreateResponseBean alipay_trade_precreate_response) {
		this.alipay_trade_precreate_response = alipay_trade_precreate_response;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public static class AlipayTradePrecreateResponseBean {
		/**
		 * code : 10000
		 * msg : Success
		 * out_trade_no : 1011524550668928
		 * qr_code : https://qr.alipay.com/bax04617dnbfx6f8a9xw40e7
		 */

		private String code;
		private String msg;
		private String out_trade_no;
		private String qr_code;

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

		public String getOut_trade_no() {
			return out_trade_no;
		}

		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}

		public String getQr_code() {
			return qr_code;
		}

		public void setQr_code(String qr_code) {
			this.qr_code = qr_code;
		}
	}
}
