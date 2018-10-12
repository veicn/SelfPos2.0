package com.acewill.slefpos.print.chikenprintlibrary.printer.gpnetwork;


import com.acewill.slefpos.print.chikenprintlibrary.model.PA_Option;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_OrderItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 佳博网口标签打印
 * Created by aqw on 2016/12/29.
 */
public class GpEnternetPrint {

    public static final int port = 9100;

    /**
     * 打印饮料
     * @param orderItem 品项
     * @param ip
     * @param lableHeight 标签纸高度/mm
     * @throws IOException
     */
    public static void gpPrint(final PA_OrderItem orderItem, String ip, Integer lableHeight) throws IOException {

        GpPrintCommand gp = new GpPrintCommand();

        gp.openport(ip, port);

        if(lableHeight == null || lableHeight == 0){
            lableHeight = 26;//默认26mm,按最小尺寸来
        }

        gp.setup(60, lableHeight, 4, 4, 0, 2, 0);
        gp.clearbuffer();
        gp.setDirection(1);
        gp.setReference(0, 0);
        gp.setTear("ON");

        printOrderItem(gp, orderItem);
    }


    //打印标签明细
    public static void printOrderItem(GpPrintCommand gp, PA_OrderItem orderItem) {
        gp.printerfont(0, 1, 1, "订单号:" + orderItem.getOrderId());//第一行
        gp.printerfont(1, 1, 1, "时间:" + getHourStr());//第二行
        gp.printerfont(2, 1, 2, orderItem.getDishName());//第三行
        String sku = getRemarks(orderItem);
        if(orderItem.getCost()!=null && orderItem.getCost().compareTo(BigDecimal.ZERO)==1){
            gp.printerfont(3, 1, 1, "价格:" + orderItem.getCost() + "￥");//第四行
            if(sku != null){
                gp.printerfont(4, 1, 1, sku);//第五行
            }
        }else {//因为是根据坐标定义行，所以当价格为空时，把第四行给定制项，不然的话有可能会空白一行
            if(sku != null){
                gp.printerfont(3, 1, 1, sku);//第四行
            }
        }

        gp.printlabel(1, 1);//打印出缓冲区的数据,第一个参数是打印的份数，第二个是每份打印的张数
        gp.clearbuffer();
        gp.closeport();
    }


    //获取定制项或做法或口味
    private static String getRemarks(PA_OrderItem orderItem) {
        String commnt = "";
        //定制项
        if(orderItem.getOptionList()!=null&&orderItem.getOptionList().size()>0){

            for (PA_Option option : orderItem.getOptionList()) {
                commnt += option.getName()+",";
            }
        }

        if (commnt.length() > 0) {
            commnt = commnt.substring(0, commnt.length() - 1);
        }
        return commnt;
    }

    public static String getHourStr() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }
}
