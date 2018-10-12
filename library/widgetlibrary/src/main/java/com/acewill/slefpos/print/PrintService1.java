//package com.acewill.slefpos.print;
//
//import android.content.Context;
//import android.hardware.usb.UsbDevice;
//import android.hardware.usb.UsbManager;
//import android.util.Log;
//
//
//import java.util.Map;
//
//public class PrintService1 {
//	private static final String LOG_TAG = "tickt_print";
//	private static UsbPrinter mUsbPrinter;
//
//	public static void init(Context context) {
//		mUsbPrinter = new UsbPrinter(context);
//	}
//
//	private static UsbDevice getCorrectDevice(Context context) {
//		final UsbManager usbMgr = (UsbManager) context
//				.getSystemService(Context.USB_SERVICE);
//		final Map<String, UsbDevice> devMap = usbMgr.getDeviceList();
//		for (String name : devMap.keySet()) {
//			Log.v(LOG_TAG, "check device: " + name);
//			if (UsbPrinter.checkPrinter(devMap.get(name))) {
//				return devMap.get(name);
//			}
//
//		}
//		return null;
//	}
//
////	public static void printTicket(Context context, PrintModel orderReq) {
////		Toast.makeText(context, "打印小票",
////				Toast.LENGTH_LONG).show();
////		final UsbDevice dev = getCorrectDevice(context);
////		if (dev != null && mUsbPrinter.open(dev)) {
////			mUsbPrinter.init();
////
////
////			size2();
////			alignLeft();
////			if (!orderReq.callId.contains("桌")) {
////				printlnText(PrintUtil.getTitle());
////			}
////			alignCenter();
////			size2();
////			printlnText(orderReq.callId+"\n");
////			size1();
////			printlnText(PrintUtil.getWelcom());
////			alignLeft();
////			printlnText(orderReq.oid + "                 " + orderReq.isTakeOutStr);
////			printlnText(orderReq.stime );
////			printlnText(PrintUtil.getCashier() );
////			printText((PrintUtil.getDishItemsString(Cart.getInstance().items,
////					orderReq) + "\n\n\n"));
////			printText(PrintUtil.getPayInfo(orderReq));
////			printText(PrintUtil.dotLine);
////			alignCenter();
////			printlnText("\n\n"+PrintUtil.getWelcomFoot());
////			printlnText(PrintUtil.getAddress());
////
////
//
//
////			printTips(PrintUtil.getTips());
////			printDotLine();
////			printOrderNo(orderReq.callId);
////			printDotLine();
////
////			printTime(orderReq.stime + "  " + orderReq.isTakeOutStr);
////			printDetail(PrintUtil.getDishItemsString(Cart.getInstance().items,
////					orderReq) + "\n");
////			mUsbPrinter.outputString(PrintUtil.getPayInfo(orderReq));
////			alignCenter();
//			// size2();
////			mUsbPrinter.doFunction(Const.TX_QR_DOTSIZE, 8, 0);
////			mUsbPrinter.doFunction(Const.TX_QR_ERRLEVEL,
////					Const.TX_QR_ERRLEVEL_M, 0);
//			// mUsbPrinter.printQRcode("dafdafdafafads");
////			feed(200);
//	//			cut();
//	//
//	//			mUsbPrinter.close();
//	//		}
//	//	}
//
//	private static void printTips(String tips) {
//		size1();
//		alignCenter();
//		mUsbPrinter.outputString(" \n" + tips + "\n");
//
//	}
//
//	private static void printText(String text){
//		mUsbPrinter.outputString(text);
//	}
//	private static void printlnText(String text){
//		mUsbPrinter.outputStringLn(text);
//	}
//
//	private static void printDotLine() {
//		size1();
//		alignCenter();
//		mUsbPrinter.outputString(PrintUtil.dotLine);
//	}
//
//	private static void printOrderNo(String callId) {
//		size2();
//		alignCenter();
//		mUsbPrinter.outputStringLn( callId);
//	}
//
//	private static void printTime(String stime) {
//		size1();
//		alignLeft();
//		mUsbPrinter.outputStringLn(stime);
//	}
//
//	private static void printDetail(String string) {
//		size1();
//		alignLeft();
//		mUsbPrinter.outputStringLn(string);
//	}
//
//	private static void alignLeft() {
//		mUsbPrinter.doFunction(Const.TX_ALIGN, Const.TX_ALIGN_LEFT, 0);
//	}
//
//	private static void alignRight() {
//		mUsbPrinter.doFunction(Const.TX_ALIGN, Const.TX_ALIGN_RIGHT, 0);
//	}
//
//	private static void alignCenter() {
//		mUsbPrinter.doFunction(Const.TX_ALIGN, Const.TX_ALIGN_CENTER, 0);
//	}
//
//	private static void size1() {
//		mUsbPrinter.doFunction(Const.TX_FONT_SIZE, Const.TX_SIZE_1X,
//				Const.TX_SIZE_1X);
//	}
//
//	private static void size2() {
//		mUsbPrinter.doFunction(Const.TX_FONT_SIZE, Const.TX_SIZE_2X,
//				Const.TX_SIZE_2X);
//	}
//
//	private static void feed(int feed) {
//		mUsbPrinter.doFunction(Const.TX_FEED, feed, 0);
//	}
//
//	private static void cut() {
//		mUsbPrinter.doFunction(Const.TX_CUT, Const.TX_PURECUT_PARTIAL, 0);
//	}
//
//}
