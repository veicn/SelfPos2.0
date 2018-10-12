package com.acewill.slefpos.print.chikenprintlibrary.printer.usb;//package com.acewill.pos.kitchenprint.apater.printer.usb;
//
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.hardware.usb.UsbDevice;
//import android.hardware.usb.UsbManager;
//import android.os.IBinder;
//import android.os.RemoteException;
//import android.util.Base64;
//import android.util.Log;
//
//import com.acewill.ordermachine.model.OrderItem;
//import com.acewill.ordermachine.pos.Order;
//import com.gprinter.aidl.GpService;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Vector;
//
//
//
///**
// * 佳博USB标签打印机
// * 调用流程：
// * 1. 先调用  GpUsbPrinter.listGpUsbPrinterList 来获得佳博usb打印机列表
// * 2. 然后把其中的某个usb设备的名字传递给GpUsbPrinter 构造函数, 然后调用init函数
// * 3. 然后调用 printOrderItem来打印内容
// * Created by Acewill on 2016/12/20.
// */
//
//public class GpUsbPrinter {
//    public static final String ACTION_CONNECT_STATUS = "action.connect.status";
//
//    private String TAG = "GpUsbPrinter";
//
//
//    private Integer labelHeight; //标签打印纸的高度
//    private String usbDeviceName;
//    private Context context;
//    private GpService mGpService;
//    private PrinterServiceConnection conn;
//
//    public GpUsbPrinter(Context context, String usbDeviceName) {
//        this.usbDeviceName = usbDeviceName;
//        this.context = context;
//    }
//
//    //绑定佳博打印机服务
//    public void init() {
//        bindService();
//    }
//
//
//    //找到设备上的所有的佳博USB打印机
//    public static List<String> listGpUsbPrinterList(Context context)
//    {
//        UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
//        // Get the list of attached devices
//        HashMap<String, UsbDevice> devices = manager.getDeviceList();
//        Iterator<UsbDevice> deviceIterator = devices.values().iterator();
//        int count = devices.size();
//        Log.d("", "found "+ count + " usb devices");
//
//        List<String> usbPrinterNameList = new ArrayList<>();
//
//        if(count > 0)
//        {
//            while (deviceIterator.hasNext()) {
//                UsbDevice device = deviceIterator.next();
//                String devicename = device.getDeviceName();
//                if(checkUsbDevicePidVid(device)){
//                    Log.d("", "found GP printer on USB: " + devicename);
//                    usbPrinterNameList.add(devicename);
//                    break;
//                }
//            }
//        }
//        else
//        {
//            Log.d("", "no USB Devices found");
//        }
//
//        return usbPrinterNameList;
//    }
//
//    //检查是否为佳博的打印机
//    private static boolean checkUsbDevicePidVid(UsbDevice dev) {
//        int pid = dev.getProductId();
//        int vid = dev.getVendorId();
//        boolean rel = false;
//        if ((vid == 34918 && pid == 256) || (vid == 1137 && pid == 85)
//                || (vid == 6790 && pid == 30084)
//                || (vid == 26728 && pid == 256) || (vid == 26728 && pid == 512)
//                || (vid == 26728 && pid == 256) || (vid == 26728 && pid == 768)
//                || (vid == 26728 && pid == 1024)|| (vid == 26728 && pid == 1280)
//                || (vid == 26728 && pid == 1536)) {
//            rel = true;
//        }
//        return rel;
//    }
//
//    private class PrinterServiceConnection implements ServiceConnection {
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            Log.i(TAG, "onServiceDisconnected() called");
//            mGpService = null;
//        }
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mGpService = GpService.Stub.asInterface(service);
//            //绑定后打开打印机
//            openUsbPrinter(usbDeviceName);
//        }
//    }
//
//    private void bindService() {
//        conn = new PrinterServiceConnection();
//        Log.i(TAG, "connection");
//        Intent intent = new Intent("com.gprinter.aidl.GpPrintService");
//        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
//    }
//
//    public void unbindService()
//    {
//        if(conn != null)
//        {
//            context.unbindService(conn);
//        }
//    }
//
//
//
//    private void openUsbPrinter(String usbDeviceName) {
//        int rel = 0;
//        try {
//            rel = mGpService.openPort(0, PortParameters.USB, usbDeviceName, 0);
//            GpCom.ERROR_CODE r=GpCom.ERROR_CODE.values()[rel];
//            if(r != GpCom.ERROR_CODE.SUCCESS){
//                if(r==GpCom.ERROR_CODE.DEVICE_ALREADY_OPEN){
//                    Log.i("INFO", "usb device: " + usbDeviceName + " already open");
//                } else {
//                    Log.e("ERROR", "failed to open usb device: " + usbDeviceName);
//                }
//
//            } else {
//                Log.i("SUCCESS", "success to open usb device: " + usbDeviceName);
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //每一份都打印一张标签
//    public void printItem(Order order, OrderItem orderItem) {
//        for (int i =0; i< orderItem.getQuantity(); ++i) {
//            printSingleItem(order, orderItem);
//        }
//    }
//
//    //打印订单中某个菜品的标签
//    public void printSingleItem(Order order, OrderItem orderItem) {
//        if (mGpService == null) {
//            Log.e("","USB printer not ready yet");
//            return;
//        }
//
//        Log.i("=====","starting print dish， height: " + PosInfo.getInstance().getLabelPrinterHeight());
//
//        TscCommand tsc = new TscCommand();
//        if (labelHeight != null) {
//            tsc.addSize(60, labelHeight.intValue()); //设置标签尺寸，按照实际尺寸设置
//        } else {
//            tsc.addSize(60, PosInfo.getInstance().getLabelPrinterHeight()); //设置标签尺寸，按照实际尺寸设置
//        }
//
//        tsc.addGap(0);           //设置标签间隙，按照实际尺寸设置，如果为无间隙纸则设置为0
//        tsc.addDirection(TscCommand.DIRECTION.BACKWARD, TscCommand.MIRROR.NORMAL);//设置打印方向
//        tsc.addReference(0, 0);//设置原点坐标
//        tsc.addTear(EscCommand.ENABLE.ON); //撕纸模式开启
//
//        tsc.addCls();// 清除打印缓冲区
//        //绘制简体中文
//        String orderText = "无效桌台号";
//        if (order.getTableNames() != null && !order.getTableNames().isEmpty()) {
//            orderText = "桌台号: " + order.getTableNames();
//        } else {
//            orderText = "订单号: " + order.getId();
//        }
//
//        //这个的单位是 点（dot), 不是英寸或者毫米, 标签共216个点那么高
//        tsc.addText(20,30, TscCommand.FONTTYPE.SIMPLIFIED_CHINESE, TscCommand.ROTATION.ROTATION_0, TscCommand.FONTMUL.MUL_1, TscCommand.FONTMUL.MUL_1,orderText);
//        String dishInfo = "";
//        if(orderItem.getCost() != null)
//        {
//
//             dishInfo = orderItem.getDishName() + "   " +orderItem.getCost()  ;
//        }
//        else
//        {
//            dishInfo = orderItem.getDishName() ;
//        }
//        tsc.addText(20,60, TscCommand.FONTTYPE.SIMPLIFIED_CHINESE, TscCommand.ROTATION.ROTATION_0, TscCommand.FONTMUL.MUL_1, TscCommand.FONTMUL.MUL_1,dishInfo);
//
//        if (orderItem.getOptionList() != null) {
//            String optionsStr = "";
//            for (Option o : orderItem.getOptionList()) {
//                optionsStr += o.getName() + ",";
//            }
//            tsc.addText(20,90, TscCommand.FONTTYPE.SIMPLIFIED_CHINESE, TscCommand.ROTATION.ROTATION_0, TscCommand.FONTMUL.MUL_1, TscCommand.FONTMUL.MUL_1,optionsStr);
//        }
//
//        if (StoreInfor.phoneNumber != null) {
//            String storeInfo = "电话: " + StoreInfor.phoneNumber;
//            tsc.addText(20,200- 80, TscCommand.FONTTYPE.SIMPLIFIED_CHINESE, TscCommand.ROTATION.ROTATION_0, TscCommand.FONTMUL.MUL_1, TscCommand.FONTMUL.MUL_1,storeInfo);
//        }
//
//        tsc.addText(20,200 - 40, TscCommand.FONTTYPE.SIMPLIFIED_CHINESE, TscCommand.ROTATION.ROTATION_0, TscCommand.FONTMUL.MUL_1, TscCommand.FONTMUL.MUL_1, TimeUtil.getStringTimeLong(order.getCreatedAt()));
//
//        tsc.addPrint(1,1); // 打印标签
//        Vector<Byte> datas = tsc.getCommand(); //发送数据
//        Byte[] Bytes = datas.toArray(new Byte[datas.size()]);
//        byte[] bytes = ToolsUtils.Byte2byte(Bytes);
//        String str = Base64.encodeToString(bytes, Base64.DEFAULT);
//        int rel;
//        try {
//            rel = mGpService.sendTscCommand(0, str);
//            GpCom.ERROR_CODE r=GpCom.ERROR_CODE.values()[rel];
//            if(r != GpCom.ERROR_CODE.SUCCESS){
//                Log.e("ERROR", "failed to print text " + GpCom.getErrorText(r));
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //打印一个测试用的菜品
//    public void printTestOrder() {
//        Order order = new Order();
//        order.setId(111);
//        order.setCreatedAt(System.currentTimeMillis());
//        OrderItem oi = new OrderItem();
//        oi.setCost(new BigDecimal("13.5"));
//        oi.setDishName("红烧乌龙茶-测试");
//        oi.setSkuStr("加冰,加珍珠,加牛奶，加咖啡");
//        PosInfo.getInstance().setPhone("123556669");
//        printSingleItem(order , oi);
//    }
//
//    public Integer getLabelHeight() {
//        return labelHeight;
//    }
//
//    public void setLabelHeight(Integer labelHeight) {
//        this.labelHeight = labelHeight;
//    }
//}
