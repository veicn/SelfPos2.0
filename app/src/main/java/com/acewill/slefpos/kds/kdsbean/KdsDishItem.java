package com.acewill.slefpos.kds.kdsbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/9 14:27
 * Desc：
 */
public class KdsDishItem {
	public String              did;// ：菜品的ID， String
	public String              name;// ：菜品名称，String
	public int                 count;// ：菜品数量，int
	public String              dishKind;// ：菜品类别名称，String
	public String              cook;// ： 菜品做法， String
	public String              alias;// ：菜品别名， String
	public String              price;// ：菜品价格，String
	public String              comment;// ：菜品备注，String
	public String              seq;// ：菜品品项编码，String
	public List<KdsOptionBean> mOptionBean;
}
