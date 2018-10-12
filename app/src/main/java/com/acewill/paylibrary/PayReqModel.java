package com.acewill.paylibrary;

import com.acewill.paylibrary.epay.AliGoodsItem;

import java.util.List;

public class PayReqModel {
	public String             totalAmount;//订单总金额
	public String             orderNo;//订单流水号
	public List<AliGoodsItem> aliGoodsItem;//阿里支付item
	public String             wxGoodsDetail;//微信支付item
	public boolean            isDebug;
	public String             body;//威付通支付item
	public int                payType;//这个payType就是上面的那些个常量
	public String             authCode;//授权码(刷卡的到的码)
	public String             store_id;
	public String             terminal_id;
	public String             store_name;
	public String             mch_create_ip;//威富通支付的终端
	public String             total_fee;//威富通支付的金额， 以分为单位
}
