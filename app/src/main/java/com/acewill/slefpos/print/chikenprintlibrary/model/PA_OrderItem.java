package com.acewill.slefpos.print.chikenprintlibrary.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-02.
 */
public class PA_OrderItem
{
//    public String id;
    public String orderId;
    public long dishId;
//    public String appId;
//    public String brandId;
//    public String storeId;
    public String dishName;
    public BigDecimal price;
    public BigDecimal cost;
//    public BigDecimal tempPrice;
    public int quantity;
//    public String createdAt;
    public boolean gift;
    public String dishKindStr;
//    public String printerStr;
    public int dishUnitID;

    public String getDishUnit() {
        return dishUnit;
    }

    public void setDishUnit(String dishUnit) {
        this.dishUnit = dishUnit;
    }

    public String dishUnit;
    public String dishKind;//菜品分类
//    public String sku;
//    public String skuStr;
    public int rejectedQuantity = 0;
    public String packName = "";//菜品所属套餐名称
    public String tempPackName = "";
    public BigDecimal tempCost = new BigDecimal("0.00");

    public int current_select;
    public int current_refund_select;//退菜选择的份数

    public String createdBy; //哪个用户创建的？
    public long rejectedBy; //哪个用户退菜的
    public int rejectedReason; // 拒绝原因
    public double orderTime;			//点这个菜的时间
    public int amount;  //点了多少份？需要看这个菜的unit是什么
    public int rejectedAmount;  //退了多少份？

    public String comment; // 在做法和口味之外 需要特殊注意的地方
    /**
     * 退菜人员名称
     */
    public String returnUserName;
    /**
     * 授权退菜人员名称
     */
    public String authUserName;
    /**
     * 餐段名称
     */
    public String menuName;

    public int isPackage;//是否是套餐（0普通菜品，1套餐）

    public ArrayList<PA_Option> optionList;
    public ArrayList<PA_OrderItem> subItemList;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

//    public String getAppId() {
//        return appId;
//    }
//
//    public void setAppId(String appId) {
//        this.appId = appId;
//    }
//
//    public String getBrandId() {
//        return brandId;
//    }
//
//    public void setBrandId(String brandId) {
//        this.brandId = brandId;
//    }
//
//    public String getStoreId() {
//        return storeId;
//    }
//
//    public void setStoreId(String storeId) {
//        this.storeId = storeId;
//    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

//    public BigDecimal getTempPrice() {
//        return tempPrice;
//    }
//
//    public void setTempPrice(BigDecimal tempPrice) {
//        this.tempPrice = tempPrice;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public String getDishKindStr() {
        return dishKindStr;
    }

    public void setDishKindStr(String dishKindStr) {
        this.dishKindStr = dishKindStr;
    }

//    public String getPrinterStr() {
//        return printerStr;
//    }
//
//    public void setPrinterStr(String printerStr) {
//        this.printerStr = printerStr;
//    }

    public int getDishUnitID() {
        return dishUnitID;
    }

    public void setDishUnitID(int dishUnitID) {
        this.dishUnitID = dishUnitID;
    }

    public String getDishKind() {
        return dishKind;
    }

    public void setDishKind(String dishKind) {
        this.dishKind = dishKind;
    }

//    public String getSku() {
//        return sku;
//    }
//
//    public void setSku(String sku) {
//        this.sku = sku;
//    }
//
//    public String getSkuStr() {
//        return skuStr;
//    }
//
//    public void setSkuStr(String skuStr) {
//        this.skuStr = skuStr;
//    }

    public int getRejectedQuantity() {
        return rejectedQuantity;
    }

    public void setRejectedQuantity(int rejectedQuantity) {
        this.rejectedQuantity = rejectedQuantity;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getTempPackName() {
        return tempPackName;
    }

    public void setTempPackName(String tempPackName) {
        this.tempPackName = tempPackName;
    }

    public BigDecimal getTempCost() {
        return tempCost;
    }

    public void setTempCost(BigDecimal tempCost) {
        this.tempCost = tempCost;
    }

    public int getCurrent_select() {
        return current_select;
    }

    public void setCurrent_select(int current_select) {
        this.current_select = current_select;
    }

    public int getCurrent_refund_select() {
        return current_refund_select;
    }

    public void setCurrent_refund_select(int current_refund_select) {
        this.current_refund_select = current_refund_select;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(long rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    public int getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(int rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public double getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(double orderTime) {
        this.orderTime = orderTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRejectedAmount() {
        return rejectedAmount;
    }

    public void setRejectedAmount(int rejectedAmount) {
        this.rejectedAmount = rejectedAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReturnUserName() {
        return returnUserName;
    }

    public void setReturnUserName(String returnUserName) {
        this.returnUserName = returnUserName;
    }

    public String getAuthUserName() {
        return authUserName;
    }

    public void setAuthUserName(String authUserName) {
        this.authUserName = authUserName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(int isPackage) {
        this.isPackage = isPackage;
    }

    public ArrayList<PA_Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<PA_Option> optionList) {
        this.optionList = optionList;
    }

    public ArrayList<PA_OrderItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(ArrayList<PA_OrderItem> subItemList) {
        this.subItemList = subItemList;
    }
}
