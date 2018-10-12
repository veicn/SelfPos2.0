//package com.acewill.slefpos.print.ticketprint;
//
//import android.content.Context;
//
//import com.acewill.slefpos.R;
//import com.acewill.slefpos.bean.orderbean.NewOrderRes;
//import com.acewill.slefpos.bean.orderbean.Order;
//import com.acewill.slefpos.print.PrintManager;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Author：Anch
// * Date：2018/5/8 11:52
// * Desc：
// */
//public class SyncTicketPrintHandler {
//	private static SyncTicketPrintHandler sPrintHandler;
//
//	public static SyncTicketPrintHandler getInstance() {
//		if (sPrintHandler == null) {
//			sPrintHandler = new SyncTicketPrintHandler();
//
//		}
//		return sPrintHandler;
//	}
//
//
//	private static String nameHeader      = "项目";
//	private static String itemPriceHeader = "单价";
//	private static String quantityHeader  = "数量";
//	private static String amountHeader    = "小计";
//	public static  int    totalSpace      = 48;
//	public static  int    leftSpace       = 29;
//	public static  int    leftSpace2      = 22;
//	public static  int    rightSpace      = 41;
//	private static String dotLine;
//
//	public void printSyncDishHeader() {
//		StringBuilder sb = new StringBuilder();
//		sb.append(nameHeader);
//		for (int i = 0; i < leftSpace2 - nameHeader.length() * 2; i++) {
//			sb.append(" ");
//		}
//		sb.append(itemPriceHeader);
//		for (int i = 0; i < leftSpace - leftSpace2 - nameHeader.length() * 2; i++) {
//			sb.append(" ");
//		}
//		sb.append(quantityHeader);
//
//		for (int i = 0; i < rightSpace - leftSpace - quantityHeader
//				.length() * 2 + 2; i++) {//+2 让金额两个字往右靠一点
//			sb.append(" ");
//		}
//		sb.append(amountHeader + "\n");
//		PrintManager.printText(sb.toString());
//	}
//
//	public void initdotLint() {
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < totalSpace; i++) {
//			if (i == totalSpace - 1)
//				sb.append("\n");
//			sb.append("-");
//		}
//		dotLine = sb.toString();
//	}
//
//	public void printHalfDotLine() {
//		PrintManager.printFormatText(dotLine);
//	}
//
//
//	/**
//	 * 这个是同步时的小票
//	 *
//	 * @param context
//	 * @param orderRes
//	 */
//	public void printSyncTicket(Context context, NewOrderRes orderRes) {
//		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "start-print");
//		//		 printPictrue(SmarantPrintUtil.getLogo(context));
//		//店名
//		PrintManager.size1();
//		PrintManager.bold();
//		PrintManager.center();
//		//		if (!orderRes.isOrderIsCorrect()) {
//		//			PrintManager.printlnText("***订单已支付,同步POS失败，请将该小票交给服务员处理！***" + "\n");
//		//		}
//		PrintManager.printlnText(SmarantPrintUtil.getWelcom() + "\n");
//		//桌牌号和堂食外带
//		PrintManager.size1();
//		PrintManager.left();
//		PrintManager.normal();
//		PrintManager.printlnText(Order.getInstance()
//				.getTakeOutStr() + "              #" + Order.getInstance().getOrderSeq());
//		//订单号，时间，收银员
//		PrintManager.printlnText(" ");
//		PrintManager.normal();
//		PrintManager.left();
//		PrintManager.size0();
//		PrintManager.printlnText("单号:" + orderRes.getContent().getId());//订单号 堂食or外带
//		PrintManager.printlnText(context.getResources()
//				.getString(R.string.order_time_tv) + format1.format(new Date()));
//		PrintManager.printlnText(SmarantPrintUtil.getCashier());//收银员
//
//		PrintManager.left();
//		printHalfDotLine();
//		//菜品 + 价格
//
//		PrintManager.bold();
//		printSyncDishHeader();
//		PrintManager.normal();
//		PrintManager.printText((SyncPrintUtil.getDishItemsString(
//		)));
//				PrintManager.left();
//				printHalfDotLine();
//				PrintManager.printText(SyncPrintUtil.getCostInfoStr());
//		//		PrintManager.left();
//		//		printHalfDotLine();
//		//
//		//		//支付方式
//		//		PrintManager.bold();
//		//		PrintManager.printFormatText("支付方式" + "\n", 0);
//		//		PrintManager.normal();
//		//		PrintManager.printText(SmarantPrintUtil.getPayInfo());
//		//		printHalfDotLine();
//		//		//		if (Order.getInstance().isMember()) {
//		//		//			PrintManager.bold();
//		//		//			PrintManager.left();
//		//		//			PrintManager.printFormatText("会员消费详情" + "\n", 0);
//		//		//			PrintManager.normal();
//		//		//			PrintManager.printText(SmarantPrintUtil.getMemberPayInfo());
//		//		//			printHalfDotLine();
//		//		//		}
//		//		PrintManager.center();
//		//		//		if (orderReq.isPrintQr()) {
//		//		//			//0 表示订单号     1 表示菜单的密文
//		//		//			PrintManager.printlnText("\n");
//		//		//			PrintManager.printQr(orderReq.id);
//		//		//		}
//		//		//		if (Common.SHOP_INFO.printInvoiceQrcode) {
//		//		//			PrintManager.printlnText("\n");
//		//		//			PrintManager.printQr(getBarcodeUrl(orderReq.oid));
//		//		//		}
//		//
//		//		PrintManager.printlnText(SmarantPrintUtil.getWelcomFoot());
//		//		PrintManager.printlnText(SmarantPrintUtil.getAddress());
//		//		PrintManager.printlnText(" ");
//		//		PrintManager.printlnText(" ");
//		//		PrintManager.printlnText(" ");
//		//		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "end-print");
//		PrintManager.cut();
//	}
//
//	private static SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 用于格式化日期,作为日志文件名的一部分
//	private static SimpleDateFormat format1 = new SimpleDateFormat("MM/dd HH:mm:ss");// 用于格式化日期,作为日志文件名的一部分
//}
