package com.acewill.slefpos.print.chikenprintlibrary.model;


import com.acewill.slefpos.print.chikenprintlibrary.printer.PrintModelInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Author：Anch
 * Date：2017/5/4 20:14
 * Desc：
 */
public class PrinterTemplatesRes {


	/**
	 * result : 0
	 * content : [{"templateType":"CUSTOMER","models":{"brandName":{"modelType":"brandName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":1,"displayName":"品牌名称","bold":false,"italic":false},"dishDiscount":{"modelType":"dishDiscount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":15,"displayName":"菜品优惠信息","bold":false,"italic":false},"orderId":{"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":6,"displayName":"订单号","bold":false,"italic":false},"packageDetail":{"modelType":"packageDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":14,"displayName":"套餐明细","bold":false,"italic":false},"mealType":{"modelType":"mealType","value":"0","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":7,"displayName":"堂食/外带","bold":false,"italic":false},"ticketType":{"modelType":"ticketType","value":"客用单","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"打印单类型","bold":false,"italic":false},"separator":{"modelType":"separator","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":18,"displayName":"分隔符","bold":false,"italic":false},"operator":{"modelType":"operator","value":"","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":9,"displayName":"服务员","bold":false,"italic":false},"orderTotal":{"modelType":"orderTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":17,"displayName":"总计","bold":false,"italic":false},"tableName":{"modelType":"tableName","value":"1","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"桌台名称","bold":true,"italic":false},"orderTime":{"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":10,"displayName":"下单时间","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":12,"displayName":"菜品明细","bold":false,"italic":false},"callNumber":{"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"取餐号","bold":true,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":13,"displayName":"菜品定制项","bold":false,"italic":false},"freeText":{"modelType":"freeText","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":20,"displayName":"自定义文字","bold":false,"italic":false},"logo":{"modelType":"logo","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"Logo","bold":false,"italic":false},"storeName":{"modelType":"storeName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"门店名称","bold":false,"italic":false},"customerCount":{"modelType":"customerCount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":8,"displayName":"顾客人数","bold":false,"italic":false}}},{"templateType":"ADD_DISH","models":{"brandName":{"modelType":"brandName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":1,"displayName":"品牌名称","bold":false,"italic":false},"dishDiscount":{"modelType":"dishDiscount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":15,"displayName":"菜品优惠信息","bold":false,"italic":false},"orderId":{"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":6,"displayName":"订单号","bold":false,"italic":false},"packageDetail":{"modelType":"packageDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":14,"displayName":"套餐明细","bold":false,"italic":false},"mealType":{"modelType":"mealType","value":"0","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":7,"displayName":"堂食/外带","bold":false,"italic":false},"ticketType":{"modelType":"ticketType","value":"加菜单","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"打印单类型","bold":false,"italic":false},"separator":{"modelType":"separator","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":16,"displayName":"分隔符","bold":false,"italic":false},"operator":{"modelType":"operator","value":"","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":9,"displayName":"服务员","bold":false,"italic":false},"orderTotal":{"modelType":"orderTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":18,"displayName":"总计","bold":false,"italic":false},"tableName":{"modelType":"tableName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"桌台名称","bold":true,"italic":false},"total":{"modelType":"total","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":17,"displayName":"总计","bold":false,"italic":false},"orderTime":{"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":10,"displayName":"下单时间","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":12,"displayName":"菜品明细","bold":false,"italic":false},"callNumber":{"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"取餐号","bold":true,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":13,"displayName":"菜品定制项","bold":false,"italic":false},"logo":{"modelType":"logo","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"Logo","bold":false,"italic":false},"storeName":{"modelType":"storeName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"门店名称","bold":false,"italic":false},"customerCount":{"modelType":"customerCount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":8,"displayName":"顾客人数","bold":false,"italic":false}}},{"templateType":"RETREAT_DISH","models":{"brandName":{"modelType":"brandName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":1,"displayName":"品牌名称","bold":false,"italic":false},"dishDiscount":{"modelType":"dishDiscount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":15,"displayName":"菜品优惠信息","bold":false,"italic":false},"orderId":{"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":6,"displayName":"订单号","bold":false,"italic":false},"packageDetail":{"modelType":"packageDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":14,"displayName":"套餐明细","bold":false,"italic":false},"mealType":{"modelType":"mealType","value":"0","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":7,"displayName":"堂食/外带","bold":false,"italic":false},"ticketType":{"modelType":"ticketType","value":"退菜单","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"打印单类型","bold":false,"italic":false},"separator":{"modelType":"separator","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":16,"displayName":"分隔符","bold":false,"italic":false},"operator":{"modelType":"operator","value":"","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":9,"displayName":"服务员","bold":false,"italic":false},"orderTotal":{"modelType":"orderTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":18,"displayName":"总计","bold":false,"italic":false},"tableName":{"modelType":"tableName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"桌台名称","bold":true,"italic":false},"total":{"modelType":"total","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":17,"displayName":"总计","bold":false,"italic":false},"orderTime":{"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":10,"displayName":"退菜时间","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":12,"displayName":"菜品明细","bold":false,"italic":false},"callNumber":{"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"取餐号","bold":true,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":13,"displayName":"菜品定制项","bold":false,"italic":false},"logo":{"modelType":"logo","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"Logo","bold":false,"italic":false},"storeName":{"modelType":"storeName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"门店名称","bold":false,"italic":false},"customerCount":{"modelType":"customerCount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":8,"displayName":"顾客人数","bold":false,"italic":false}}},{"templateType":"PRE_CHECKOUT","models":{"brandName":{"modelType":"brandName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":1,"displayName":"品牌名称","bold":false,"italic":false},"cost":{"modelType":"cost","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":23,"displayName":"消费额","bold":false,"italic":false},"dishDiscount":{"modelType":"dishDiscount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":17,"displayName":"菜品优惠信息","bold":false,"italic":false},"orderId":{"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":7,"displayName":"订单号","bold":false,"italic":false},"packageDetail":{"modelType":"packageDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":16,"displayName":"套餐明细","bold":false,"italic":false},"mealType":{"modelType":"mealType","value":"0","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":8,"displayName":"堂食/外带","bold":false,"italic":false},"dishcountTotal":{"modelType":"dishcountTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":22,"displayName":"优惠总计","bold":false,"italic":false},"ticketType":{"modelType":"ticketType","value":"预结单","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"打印单类型","bold":false,"italic":false},"preCheckoutTimes":{"modelType":"preCheckoutTimes","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"预结时间","bold":false,"italic":false},"separator":{"modelType":"separator","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":18,"displayName":"分隔符","bold":false,"italic":false},"operator":{"modelType":"operator","value":"","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":10,"displayName":"服务员","bold":false,"italic":false},"orderTotal":{"modelType":"orderTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":21,"displayName":"订单总计","bold":false,"italic":false},"tableName":{"modelType":"tableName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":6,"displayName":"桌台名称","bold":true,"italic":false},"serviceCharge":{"modelType":"serviceCharge","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":19,"displayName":"服务费","bold":false,"italic":false},"orderTime":{"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":11,"displayName":"下单时间","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":14,"displayName":"菜品明细","bold":false,"italic":false},"callNumber":{"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"取餐号","bold":true,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":15,"displayName":"菜品定制项","bold":false,"italic":false},"takeoutCharge":{"modelType":"takeoutCharge","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":20,"displayName":"打包费","bold":false,"italic":false},"logo":{"modelType":"logo","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"Logo","bold":false,"italic":false},"storeName":{"modelType":"storeName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"门店名称","bold":false,"italic":false},"checkoutTime":{"modelType":"checkoutTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":12,"displayName":"结账时间","bold":false,"italic":false},"customerCount":{"modelType":"customerCount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":9,"displayName":"顾客人数","bold":false,"italic":false}}},{"templateType":"CHECKOUT","models":{"orderId":{"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":6,"displayName":"订单号","bold":false,"italic":false},"packageDetail":{"modelType":"packageDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":15,"displayName":"套餐明细","bold":false,"italic":false},"mealType":{"modelType":"mealType","value":"0","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":7,"displayName":"堂食/外带","bold":false,"italic":false},"dishcountTotal":{"modelType":"dishcountTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":21,"displayName":"优惠总计","bold":false,"italic":false},"orderQrCode":{"modelType":"orderQrCode","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":26,"displayName":"订单二维码","bold":false,"italic":false},"operator":{"modelType":"operator","value":"","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":9,"displayName":"服务员","bold":false,"italic":false},"tableName":{"modelType":"tableName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"桌台名称","bold":true,"italic":false},"serviceCharge":{"modelType":"serviceCharge","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":18,"displayName":"服务费","bold":false,"italic":false},"orderTime":{"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":10,"displayName":"下单时间","bold":false,"italic":false},"qrCode":{"modelType":"qrCode","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":27,"displayName":"公众号二维码","bold":false,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":14,"displayName":"菜品定制项","bold":false,"italic":false},"storePhone":{"modelType":"storePhone","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":29,"displayName":"门店电话","bold":false,"italic":false},"takeoutCharge":{"modelType":"takeoutCharge","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":19,"displayName":"打包费","bold":false,"italic":false},"logo":{"modelType":"logo","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"Logo","bold":false,"italic":false},"storeName":{"modelType":"storeName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"门店名称","bold":false,"italic":false},"checkoutTime":{"modelType":"checkoutTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":11,"displayName":"结账时间","bold":false,"italic":false},"brandName":{"modelType":"brandName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":1,"displayName":"品牌名称","bold":false,"italic":false},"cost":{"modelType":"cost","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":22,"displayName":"消费总计","bold":false,"italic":false},"dishDiscount":{"modelType":"dishDiscount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":16,"displayName":"菜品优惠信息","bold":false,"italic":false},"ticketType":{"modelType":"ticketType","value":"结账单","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"打印单类型","bold":false,"italic":false},"separator":{"modelType":"separator","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":23,"displayName":"分隔符","bold":false,"italic":false},"orderTotal":{"modelType":"orderTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":20,"displayName":"订单总计","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":13,"displayName":"菜品明细","bold":false,"italic":false},"callNumber":{"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"取餐号","bold":true,"italic":false},"storeAddress":{"modelType":"storeAddress","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":28,"displayName":"门店地址","bold":false,"italic":false},"freeText":{"modelType":"freeText","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":31,"displayName":"自定义文字","bold":false,"italic":false},"payDetail":{"modelType":"payDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":24,"displayName":"支付明细","bold":false,"italic":false},"invoice":{"modelType":"invoice","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":25,"displayName":"电子发票","bold":false,"italic":false},"customerCount":{"modelType":"customerCount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":8,"displayName":"顾客人数","bold":false,"italic":false}}},{"templateType":"LABEL","models":{"orderTime":{"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"下单时间","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"菜品明细","bold":false,"italic":false},"orderId":{"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":1,"displayName":"订单号","bold":false,"italic":false},"storeAddress":{"modelType":"storeAddress","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"门店地址","bold":false,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"菜品定制项","bold":false,"italic":false},"storePhone":{"modelType":"storePhone","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"门店电话","bold":false,"italic":false},"freeText":{"modelType":"freeText","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":6,"displayName":"自定义文字","bold":false,"italic":false}}},{"templateType":"KICHEN","models":{"orderTime":{"modelType":"orderTime","value":"1","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"下单时间","bold":false,"italic":false},"comments":{"modelType":"comments","value":"","printSize":1,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":9,"displayName":"备注","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":1,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":6,"displayName":"菜品明细","bold":false,"italic":false},"callNumber":{"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"取餐号","bold":false,"italic":false},"orderId":{"modelType":"orderId","value":"1","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"订单号","bold":false,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":1,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":7,"displayName":"菜品定制项","bold":false,"italic":false},"stallsName":{"modelType":"stallsName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"档口名称","bold":false,"italic":false},"kichenDishType":{"modelType":"kichenDishType","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":1,"displayName":"厨房菜品类型","bold":true,"italic":false},"packageName":{"modelType":"packageName","value":"","printSize":1,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":8,"displayName":"套餐名称","bold":false,"italic":false},"tableName":{"modelType":"tableName","value":"1","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"桌台名称","bold":false,"italic":false}}}]
	 * errmsg : 0
	 */

	private int result;
	private String            errmsg;
	private List<ContentBean> content;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<ContentBean> getContent() {
		return content;
	}

	public void setContent(List<ContentBean> content) {
		this.content = content;
	}

	public static class ContentBean {
		/**
		 * templateType : CUSTOMER
		 * models : {"brandName":{"modelType":"brandName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":1,"displayName":"品牌名称","bold":false,"italic":false},"dishDiscount":{"modelType":"dishDiscount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":15,"displayName":"菜品优惠信息","bold":false,"italic":false},"orderId":{"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":6,"displayName":"订单号","bold":false,"italic":false},"packageDetail":{"modelType":"packageDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":14,"displayName":"套餐明细","bold":false,"italic":false},"mealType":{"modelType":"mealType","value":"0","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":7,"displayName":"堂食/外带","bold":false,"italic":false},"ticketType":{"modelType":"ticketType","value":"客用单","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"打印单类型","bold":false,"italic":false},"separator":{"modelType":"separator","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":18,"displayName":"分隔符","bold":false,"italic":false},"operator":{"modelType":"operator","value":"","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":9,"displayName":"服务员","bold":false,"italic":false},"orderTotal":{"modelType":"orderTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":17,"displayName":"总计","bold":false,"italic":false},"tableName":{"modelType":"tableName","value":"1","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"桌台名称","bold":true,"italic":false},"orderTime":{"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":10,"displayName":"下单时间","bold":false,"italic":false},"dishDetail":{"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":12,"displayName":"菜品明细","bold":false,"italic":false},"callNumber":{"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"取餐号","bold":true,"italic":false},"dishCutomerize":{"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":13,"displayName":"菜品定制项","bold":false,"italic":false},"freeText":{"modelType":"freeText","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":20,"displayName":"自定义文字","bold":false,"italic":false},"logo":{"modelType":"logo","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"Logo","bold":false,"italic":false},"storeName":{"modelType":"storeName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"门店名称","bold":false,"italic":false},"customerCount":{"modelType":"customerCount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":8,"displayName":"顾客人数","bold":false,"italic":false}}
		 */

		private String                          templateType;
//		private ModelsBean models;
		private HashMap<String, PrintModelInfo> models;

		public String getTemplateType() {
			return templateType;
		}

		public void setTemplateType(String templateType) {
			this.templateType = templateType;
		}

		public HashMap<String, PrintModelInfo> getModels() {
			return models;
		}

		public void setModels(HashMap<String, PrintModelInfo> models) {
			this.models = models;
		}

		//		public ModelsBean getModels() {
//			return models;
//		}
//
//		public void setModels(ModelsBean models) {
//			this.models = models;
//		}

//		public static class ModelsBean {
//			/**
//			 * brandName : {"modelType":"brandName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":1,"displayName":"品牌名称","bold":false,"italic":false}
//			 * dishDiscount : {"modelType":"dishDiscount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":15,"displayName":"菜品优惠信息","bold":false,"italic":false}
//			 * orderId : {"modelType":"orderId","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":6,"displayName":"订单号","bold":false,"italic":false}
//			 * packageDetail : {"modelType":"packageDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":14,"displayName":"套餐明细","bold":false,"italic":false}
//			 * mealType : {"modelType":"mealType","value":"0","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":7,"displayName":"堂食/外带","bold":false,"italic":false}
//			 * ticketType : {"modelType":"ticketType","value":"客用单","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":3,"displayName":"打印单类型","bold":false,"italic":false}
//			 * separator : {"modelType":"separator","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":18,"displayName":"分隔符","bold":false,"italic":false}
//			 * operator : {"modelType":"operator","value":"","printSize":0,"printAlian":2,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":9,"displayName":"服务员","bold":false,"italic":false}
//			 * orderTotal : {"modelType":"orderTotal","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":17,"displayName":"总计","bold":false,"italic":false}
//			 * tableName : {"modelType":"tableName","value":"1","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":5,"displayName":"桌台名称","bold":true,"italic":false}
//			 * orderTime : {"modelType":"orderTime","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":10,"displayName":"下单时间","bold":false,"italic":false}
//			 * dishDetail : {"modelType":"dishDetail","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":12,"displayName":"菜品明细","bold":false,"italic":false}
//			 * callNumber : {"modelType":"callNumber","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":4,"displayName":"取餐号","bold":true,"italic":false}
//			 * dishCutomerize : {"modelType":"dishCutomerize","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":13,"displayName":"菜品定制项","bold":false,"italic":false}
//			 * freeText : {"modelType":"freeText","value":"-","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":20,"displayName":"自定义文字","bold":false,"italic":false}
//			 * logo : {"modelType":"logo","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":0,"displayName":"Logo","bold":false,"italic":false}
//			 * storeName : {"modelType":"storeName","value":"","printSize":1,"printAlian":1,"doubleH":false,"doubleW":false,"printEnter":true,"shouldPrint":true,"seq":2,"displayName":"门店名称","bold":false,"italic":false}
//			 * customerCount : {"modelType":"customerCount","value":"","printSize":0,"printAlian":0,"doubleH":false,"doubleW":false,"printEnter":false,"shouldPrint":true,"seq":8,"displayName":"顾客人数","bold":false,"italic":false}
//			 */
//
//			private BrandNameBean brandName;
//			private DishDiscountBean   dishDiscount;
//			private OrderIdBean        orderId;
//			private PackageDetailBean  packageDetail;
//			private MealTypeBean       mealType;
//			private TicketTypeBean     ticketType;
//			private SeparatorBean      separator;
//			private OperatorBean       operator;
//			private OrderTotalBean     orderTotal;
//			private TableNameBean      tableName;
//			private OrderTimeBean      orderTime;
//			private DishDetailBean     dishDetail;
//			private CallNumberBean     callNumber;
//			private DishCutomerizeBean dishCutomerize;
//			private FreeTextBean       freeText;
//			private LogoBean           logo;
//			private StoreNameBean      storeName;
//			private CustomerCountBean  customerCount;
//
//			public BrandNameBean getBrandName() {
//				return brandName;
//			}
//
//			public void setBrandName(BrandNameBean brandName) {
//				this.brandName = brandName;
//			}
//
//			public DishDiscountBean getDishDiscount() {
//				return dishDiscount;
//			}
//
//			public void setDishDiscount(DishDiscountBean dishDiscount) {
//				this.dishDiscount = dishDiscount;
//			}
//
//			public OrderIdBean getOrderId() {
//				return orderId;
//			}
//
//			public void setOrderId(OrderIdBean orderId) {
//				this.orderId = orderId;
//			}
//
//			public PackageDetailBean getPackageDetail() {
//				return packageDetail;
//			}
//
//			public void setPackageDetail(PackageDetailBean packageDetail) {
//				this.packageDetail = packageDetail;
//			}
//
//			public MealTypeBean getMealType() {
//				return mealType;
//			}
//
//			public void setMealType(MealTypeBean mealType) {
//				this.mealType = mealType;
//			}
//
//			public TicketTypeBean getTicketType() {
//				return ticketType;
//			}
//
//			public void setTicketType(TicketTypeBean ticketType) {
//				this.ticketType = ticketType;
//			}
//
//			public SeparatorBean getSeparator() {
//				return separator;
//			}
//
//			public void setSeparator(SeparatorBean separator) {
//				this.separator = separator;
//			}
//
//			public OperatorBean getOperator() {
//				return operator;
//			}
//
//			public void setOperator(OperatorBean operator) {
//				this.operator = operator;
//			}
//
//			public OrderTotalBean getOrderTotal() {
//				return orderTotal;
//			}
//
//			public void setOrderTotal(OrderTotalBean orderTotal) {
//				this.orderTotal = orderTotal;
//			}
//
//			public TableNameBean getTableName() {
//				return tableName;
//			}
//
//			public void setTableName(TableNameBean tableName) {
//				this.tableName = tableName;
//			}
//
//			public OrderTimeBean getOrderTime() {
//				return orderTime;
//			}
//
//			public void setOrderTime(OrderTimeBean orderTime) {
//				this.orderTime = orderTime;
//			}
//
//			public DishDetailBean getDishDetail() {
//				return dishDetail;
//			}
//
//			public void setDishDetail(DishDetailBean dishDetail) {
//				this.dishDetail = dishDetail;
//			}
//
//			public CallNumberBean getCallNumber() {
//				return callNumber;
//			}
//
//			public void setCallNumber(CallNumberBean callNumber) {
//				this.callNumber = callNumber;
//			}
//
//			public DishCutomerizeBean getDishCutomerize() {
//				return dishCutomerize;
//			}
//
//			public void setDishCutomerize(DishCutomerizeBean dishCutomerize) {
//				this.dishCutomerize = dishCutomerize;
//			}
//
//			public FreeTextBean getFreeText() {
//				return freeText;
//			}
//
//			public void setFreeText(FreeTextBean freeText) {
//				this.freeText = freeText;
//			}
//
//			public LogoBean getLogo() {
//				return logo;
//			}
//
//			public void setLogo(LogoBean logo) {
//				this.logo = logo;
//			}
//
//			public StoreNameBean getStoreName() {
//				return storeName;
//			}
//
//			public void setStoreName(StoreNameBean storeName) {
//				this.storeName = storeName;
//			}
//
//			public CustomerCountBean getCustomerCount() {
//				return customerCount;
//			}
//
//			public void setCustomerCount(CustomerCountBean customerCount) {
//				this.customerCount = customerCount;
//			}
//
//			public static class BrandNameBean {
//				/**
//				 * modelType : brandName
//				 * value :
//				 * printSize : 1
//				 * printAlian : 1
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : false
//				 * shouldPrint : true
//				 * seq : 1
//				 * displayName : 品牌名称
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class DishDiscountBean {
//				/**
//				 * modelType : dishDiscount
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 15
//				 * displayName : 菜品优惠信息
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class OrderIdBean {
//				/**
//				 * modelType : orderId
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : false
//				 * shouldPrint : true
//				 * seq : 6
//				 * displayName : 订单号
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class PackageDetailBean {
//				/**
//				 * modelType : packageDetail
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 14
//				 * displayName : 套餐明细
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class MealTypeBean {
//				/**
//				 * modelType : mealType
//				 * value : 0
//				 * printSize : 0
//				 * printAlian : 2
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 7
//				 * displayName : 堂食/外带
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class TicketTypeBean {
//				/**
//				 * modelType : ticketType
//				 * value : 客用单
//				 * printSize : 1
//				 * printAlian : 1
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 3
//				 * displayName : 打印单类型
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class SeparatorBean {
//				/**
//				 * modelType : separator
//				 * value : -
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 18
//				 * displayName : 分隔符
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class OperatorBean {
//				/**
//				 * modelType : operator
//				 * value :
//				 * printSize : 0
//				 * printAlian : 2
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 9
//				 * displayName : 服务员
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class OrderTotalBean {
//				/**
//				 * modelType : orderTotal
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 17
//				 * displayName : 总计
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class TableNameBean {
//				/**
//				 * modelType : tableName
//				 * value : 1
//				 * printSize : 1
//				 * printAlian : 1
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 5
//				 * displayName : 桌台名称
//				 * bold : true
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class OrderTimeBean {
//				/**
//				 * modelType : orderTime
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 10
//				 * displayName : 下单时间
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class DishDetailBean {
//				/**
//				 * modelType : dishDetail
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 12
//				 * displayName : 菜品明细
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class CallNumberBean {
//				/**
//				 * modelType : callNumber
//				 * value :
//				 * printSize : 1
//				 * printAlian : 1
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 4
//				 * displayName : 取餐号
//				 * bold : true
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class DishCutomerizeBean {
//				/**
//				 * modelType : dishCutomerize
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 13
//				 * displayName : 菜品定制项
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class FreeTextBean {
//				/**
//				 * modelType : freeText
//				 * value : -
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 20
//				 * displayName : 自定义文字
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class LogoBean {
//				/**
//				 * modelType : logo
//				 * value :
//				 * printSize : 1
//				 * printAlian : 1
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 0
//				 * displayName : Logo
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class StoreNameBean {
//				/**
//				 * modelType : storeName
//				 * value :
//				 * printSize : 1
//				 * printAlian : 1
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : true
//				 * shouldPrint : true
//				 * seq : 2
//				 * displayName : 门店名称
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//
//			public static class CustomerCountBean {
//				/**
//				 * modelType : customerCount
//				 * value :
//				 * printSize : 0
//				 * printAlian : 0
//				 * doubleH : false
//				 * doubleW : false
//				 * printEnter : false
//				 * shouldPrint : true
//				 * seq : 8
//				 * displayName : 顾客人数
//				 * bold : false
//				 * italic : false
//				 */
//
//				private String modelType;
//				private String  value;
//				private int     printSize;
//				private int     printAlian;
//				private boolean doubleH;
//				private boolean doubleW;
//				private boolean printEnter;
//				private boolean shouldPrint;
//				private int     seq;
//				private String  displayName;
//				private boolean bold;
//				private boolean italic;
//
//				public String getModelType() {
//					return modelType;
//				}
//
//				public void setModelType(String modelType) {
//					this.modelType = modelType;
//				}
//
//				public String getValue() {
//					return value;
//				}
//
//				public void setValue(String value) {
//					this.value = value;
//				}
//
//				public int getPrintSize() {
//					return printSize;
//				}
//
//				public void setPrintSize(int printSize) {
//					this.printSize = printSize;
//				}
//
//				public int getPrintAlian() {
//					return printAlian;
//				}
//
//				public void setPrintAlian(int printAlian) {
//					this.printAlian = printAlian;
//				}
//
//				public boolean isDoubleH() {
//					return doubleH;
//				}
//
//				public void setDoubleH(boolean doubleH) {
//					this.doubleH = doubleH;
//				}
//
//				public boolean isDoubleW() {
//					return doubleW;
//				}
//
//				public void setDoubleW(boolean doubleW) {
//					this.doubleW = doubleW;
//				}
//
//				public boolean isPrintEnter() {
//					return printEnter;
//				}
//
//				public void setPrintEnter(boolean printEnter) {
//					this.printEnter = printEnter;
//				}
//
//				public boolean isShouldPrint() {
//					return shouldPrint;
//				}
//
//				public void setShouldPrint(boolean shouldPrint) {
//					this.shouldPrint = shouldPrint;
//				}
//
//				public int getSeq() {
//					return seq;
//				}
//
//				public void setSeq(int seq) {
//					this.seq = seq;
//				}
//
//				public String getDisplayName() {
//					return displayName;
//				}
//
//				public void setDisplayName(String displayName) {
//					this.displayName = displayName;
//				}
//
//				public boolean isBold() {
//					return bold;
//				}
//
//				public void setBold(boolean bold) {
//					this.bold = bold;
//				}
//
//				public boolean isItalic() {
//					return italic;
//				}
//
//				public void setItalic(boolean italic) {
//					this.italic = italic;
//				}
//			}
//		}
	}
}
