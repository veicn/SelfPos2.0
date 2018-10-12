package com.acewill.slefpos.print.chikenprintlibrary.util;

/**
 * Created by Administrator on 2017-05-02.
 */
public class PA_Constant
{
    /**
     * js与android通信标识类
     */
    public static class JsToAndroid
    {
        /**
         *  js_android 加菜
         */
        public static final int JS_A_ADDDISH = 2;
    }

    public static class EventState
    {
        /**
         * 选择桌台区域回调<tt>tag</tt>
         */
        public static final int SELECT_AREA = 0x11;
        /**
         * 切换fragment下单界面
         */
        public static final int SELECT_FRAGMTNT_ORDER = 0x12;
        /**
         * 切换fragment桌台界面
         */
        public static final int SELECT_FRAGMTNT_TABLE = 0x13;
        /**
         * 切换fragment退菜界面
         */
        public static final int SELECT_FRAGMTNT_RETREAT = 0x33;
        /**
         * 切换到桌台结账界面
         */
        public static final int SELECT_CHECK_OUT = 0x34;
        /**
         * 主界面下拉回调
         */
        public static final int SELECT_MAIN_DROP_LIST = 0x14;
        /**
         * 清除购物车事件
         */
        public static final int CLEAN_CART = 0x18;
        /**
         * 跳转到结账界面参数
         */
        public static final int SOURCE_CREAT_ORDER = 0;//从创建订单界面过来
        public static final int SOURCE_ORDER_DAY = 1;//从当日订单界面过来
        public static final int SOURCE_TABLE_ORDER = 2;//从桌台界面过来
        /**
         *  小票类型Dialog回调
         */
        public static final int DIALOG_RECEIPT_CALLBACK = 0x15;
        /**
         * 服务器处于在线状态
         */
        public static final int SERVER_STATUS_UP = 0X16;
        /**
         * 服务器处于离线状态
         */
        public static final int SERVER_STATUS_DOWN = 0X17;
        /**
         * 获取到网络订单状态标识
         */
        public static final int PUT_NET_ORDER = 0X19;
        /**
         * Token过期的标识
         */
        public static final int TOKEN_TIME_OUT = 0X20;
        /**
         * 下单打印的标识
         */
        public static final int PRINTER_ORDER = 0X23;
        /**
         * 当前时段没有菜品显示的标识
         */
        public static final int CURRENT_TIME_DISH_NULL = 0X24;
        /**
         * 下单打印厨房小票的标识
         */
        public static final int PRINTER_KITCHEN_ORDER = 0X25;
        /**
         * 服务器请求超时
         */
        public static final int SERVER_REQUEST_TIMEOUT = 0X26;
        /**
         * 打印交接班报表
         */
        public static final int PRINTER_WORKSHIFT_REPORT = 0X27;
        /**
         * 退菜打印的标识
         */
        public static final int PRINTER_RETREAT_DISH = 0X28;
        /**
         * 催菜后台厨房打印标识
         */
        public static final int PRINTER_RUSH_DISH = 0X29;
        /**
         * 补打小票标识
         */
        public static final int PRINTER_EXTRA_RECEIPT = 0X30;
        /**
         * 补打厨房小票标识
         */
        public static final int PRINTER_EXTRA_KITCHEN_RECEIPT = 0X33;
        /**
         * 打印订单小票出错
         */
        public static final int ERR_PRINT_ORDER = 0X60;
        /**
         * 打印厨房小票出错
         */
        public static final int ERR_PRINT_KITCHEN_ORDER = 0X61;
        /**
         * 打印总单小票出错
         */
        public static final int ERR_PRINT_KITCHEN_SUMMARY_ORDER = 0X70;
        /**
         * 打印退单出错
         */
        public static final int ERR_PRINT_REFUND_ORDER = 0X68;
        /**
         * 打印客用订单小票出错
         */
        public static final int ERR_PRINT_GUEST_ORDER = 0X69;
        /**
         * 收银打印机配置出错
         */
        public static final int ERR_PRINT_CASH = 0X72;
        /**
         * 收银打印机打印交班记录Id
         */
        public static final int PRINT_WORKSHIFT = 0X73;
        /**
         * 注销
         */
        public static final int LOGOUT = 0X74;
        /**
         * 打印结账单
         */
        public static final int PRINT_CHECKOUT = 0X62;
        /**
         * 退单打印的标识
         */
        public static final int PRINTER_RETREAT_ORDER = 0X63;

        /**
         *
         */
        public static final int SET_ORDER_ID = 0X64;
        /**
         * 退菜客用打印的标识
         */
        public static final int PRINTER_RETREAT_DISH_GUEST = 0X65;

        /**
         * 打开钱箱
         */
        public static final int PRINTER_OPEN_MONEYBOX = 0X66;
        /**
         * 厨房退单打印的标识
         */
        public static final int PRINTER_RETREAT_KITCHEN_ORDER = 0X67;
        /**
         * 预结小票标识
         */
        public static final int ORDER_TYPE_ADVANCE = 0x68;
        /**
         * 打印外卖结账单
         */
        public static final int PRINT_WAIMAI = 0X69;

    }
}
