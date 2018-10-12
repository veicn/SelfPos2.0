package com.acewill.slefpos.print.chikenprintlibrary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-02.
 */
public class PA_Order
{
    public String id;  // 订单ID
    public String sequenceNumber;// 订单号码
    public int customerAmount;// 顾客人数
    public String comment = "";//订单说明
//    public String appId;
//    public String brandId;
//    public String storeId;
    public String total;
    public String cost;
    public String source;
    public double discount;
    public double subtraction;
    public String orderType;
    public String orderTypeStr;
    public String callNumber;
    public String createdBy;
    public String saleUserId;
    public String tableNames = "";
    public long createdAt; //这里不能用Date， 因为app的date格式可能和服务器不一样
    public long paidAt; //结账时间
    public List<Long> tableIds = new ArrayList<Long>();
    public Integer workShiftId;//班次ID

    public int printerType = -1;//打印订单类型  Constant.JsToAndroid 中有打印的详细类型
    public int tableStyle;//桌台操作类型   是否是搭台  开台   并台
    public int orderPrintType;
    public String createdAtStr;//订单产生时间

    public String customerName;//外卖顾客的姓名
    public String customerPhoneNumber;//外卖顾客的电话
    public String customerAddress;//外卖顾客的地址
    public String outerOrderid;//外部平台订单号

    //电子流水号，打印小票使用
    public String paymentNo;//微信、支付宝电子流水号

    public List<PA_OrderItem> itemList;
    public List<PA_Option> optionList;

    private long tableId;

    public boolean isPrintSumTiketQrCode() {
        return printSumTiketQrCode;
    }

    public void setPrintSumTiketQrCode(boolean printSumTiketQrCode) {
        this.printSumTiketQrCode = printSumTiketQrCode;
    }

    private boolean printSumTiketQrCode;

    //netOrder
//    public Long refOrderId; //如果是退单的话，这里会保存原单的id
//    public BigDecimal refund = new BigDecimal("0");// 退款金额 -- 放到这里是为了出报表方便
//    public String memberid;
//    public String customerid;
//    public int netorderstatus;
//    public long deliverAt; // 门店接受订单时间
//    public long preorderTime;//预订时间
//    public String prepayId;
//    public int payType;
//    public String rejectReason;
//    public BigDecimal shippingFee = new BigDecimal("0");//网上订单配送费用
//    public long netOrderid;//网络订单Id
//
//    public String tableid = "";//对应的桌台ID， -1代表没有桌台
//    public int operation_type; //0：新下单；1：加菜单
//    public long refNetOrderId;

    /**
     * 退单人员名称
     */
    public String returnUserName;
    /**
     * 授权退单人员名称
     */
    public String authUserName;

    /**
     * 餐段名称
     */
    public String menuName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(int customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSubtraction() {
        return subtraction;
    }

    public void setSubtraction(double subtraction) {
        this.subtraction = subtraction;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeStr() {
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        this.orderTypeStr = orderTypeStr;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(String saleUserId) {
        this.saleUserId = saleUserId;
    }

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(long paidAt) {
        this.paidAt = paidAt;
    }

    public List<Long> getTableIds() {
        return tableIds;
    }

    public void setTableIds(List<Long> tableIds) {
        this.tableIds = tableIds;
    }

    public Integer getWorkShiftId() {
        return workShiftId;
    }

    public void setWorkShiftId(Integer workShiftId) {
        this.workShiftId = workShiftId;
    }

    public int getPrinterType() {
        return printerType;
    }

    public void setPrinterType(int printerType) {
        this.printerType = printerType;
    }

    public int getTableStyle() {
        return tableStyle;
    }

    public void setTableStyle(int tableStyle) {
        this.tableStyle = tableStyle;
    }

    public int getOrderPrintType() {
        return orderPrintType;
    }

    public void setOrderPrintType(int orderPrintType) {
        this.orderPrintType = orderPrintType;
    }

    public String getCreatedAtStr() {
        return createdAtStr;
    }

    public void setCreatedAtStr(String createdAtStr) {
        this.createdAtStr = createdAtStr;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getOuterOrderid() {
        return outerOrderid;
    }

    public void setOuterOrderid(String outerOrderid) {
        this.outerOrderid = outerOrderid;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public List<PA_OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<PA_OrderItem> orderItemList) {
        this.itemList = orderItemList;
    }

    public List<PA_Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<PA_Option> optionList) {
        this.optionList = optionList;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
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
}
