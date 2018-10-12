package com.acewill.slefpos.kds.kdsbean;

import java.util.List;

public class KdsOrderBean {
	public String            oid;// : 订单ID，String
	public long              createTime;// ：下单时间，long，系统时间的毫秒数
	public int               type;// ：订单类型,int, 0：堂食；1：打包外带；2：外卖
	public String            total;// ：总价，String
	public int               paystatus;// ：订单支付信息，int，0：未付款；1：已付款
	public String            tablename;// ：桌台信息，String
	public String            comment;// ：全单备注
	public String            orderRange;// ：订单时段，String
	public String            pos;// ：下单的pos编号，String
	public String            operator;// ：下单人员，String
	public String            openID;// : 微信支付下，顾客支付后的openID，用于微信通知
	public List<KdsDishItem> dishitems;// ：[ 订单中菜品信息，数组形式
	public String            price;
	public String            fetchID;// 取餐号


}
