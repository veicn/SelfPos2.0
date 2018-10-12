package com.acewill.slefpos.print.ticketprint;

import android.content.Context;
import android.text.TextUtils;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.print.PrintManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author：Anch
 * Date：2018/5/8 11:52
 * Desc：
 */
public class SmarantTicketPrintHandler {
	private static SmarantTicketPrintHandler sPrintHandler;

	public static SmarantTicketPrintHandler getInstance() {
		if (sPrintHandler == null) {
			sPrintHandler = new SmarantTicketPrintHandler();
		}
		return sPrintHandler;
	}


	private static String nameHeader     = "菜品";
	private static String quantityHeader = "数量";
	private static String amountHeader   = "金额";
	public static  int    totalSpace     = 48;
	public static  int    leftSpace      = 32;
	public static  int    rightSpace     = 41;
	private static String dotLine;

	public void printDishHeader() {
		nameHeader = SystemConfig.isSmarantSystem ? "菜品" : "项目";
		StringBuilder sb = new StringBuilder();
		sb.append(nameHeader);
		for (int i = 0; i < leftSpace - nameHeader.length() * 2; i++) {
			sb.append(" ");
		}
		sb.append(quantityHeader);
		for (int i = 0; i < rightSpace - leftSpace - quantityHeader
				.length() * 2 + 2; i++) {//+2 让金额两个字往右靠一点
			sb.append(" ");
		}
		sb.append(amountHeader + "\n");
		PrintManager.printText(sb.toString());
	}

	public void initdotLint() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < totalSpace; i++) {
			if (i == totalSpace - 1)
				sb.append("\n");
			sb.append("-");
		}
		dotLine = sb.toString();
	}

	public void printHalfDotLine() {
		PrintManager.printFormatText(dotLine);
	}


	/**
	 * 这个是智慧快餐的小票
	 *
	 * @param context
	 * @param orderRes
	 */
	public void printSmarantTicket(Context context, NewOrderRes orderRes) {
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "start-print");


		//店名
		if (TextUtils.isEmpty(StoreConfigure.getJyjAddress())) {
			//吉野家要求空多三行
			PrintManager.printText("\n");
			PrintManager.printText("\n");
			PrintManager.printText("\n");
		}
		PrintManager.size1();
		PrintManager.bold();
		PrintManager.center();
		if (orderRes == null) {
			PrintManager.printlnText("***订单已支付,同步POS失败，请将该小票交给服务员处理！***" + "\n");
		}

		//		if (!orderRes.isOrderIsCorrect()) {
		//			PrintManager.printlnText("***订单已支付,同步POS失败，请将该小票交给服务员处理！***" + "\n");
		//		}
		PrintManager.printlnText(SmarantPrintUtil.getWelcom() + "\n");
		//桌牌号和堂食外带
		PrintManager.size1();
		PrintManager.left();
		PrintManager.normal();

		if (orderRes != null) {
			String callId  = orderRes.getContent().getCallNumber();
			String tableId = Order.getInstance().getTableId();
			if (tableId != null)
				callId = "餐牌号:" + tableId;
			if (!callId.contains("餐")) {
				PrintManager.printlnText(SmarantPrintUtil.getTitle());//取餐号
				//			取餐号:
				PrintManager.printlnText(context.getResources()
						.getString(R.string.cardnum_tv) + callId + "        " + Order.getInstance()
						.getTakeOutStr());
			} else {
				PrintManager
						.printlnText(callId + "        " + Order.getInstance()
								.getTakeOutStr());//桌牌号
			}
			//订单号，时间，收银员
			PrintManager.printlnText(" ");
			PrintManager.normal();
			PrintManager.left();
			PrintManager.size0();
			PrintManager.printlnText(context.getResources()
					.getString(R.string.order_num_tv) + orderRes.getContent().getId());//订单号 堂食or外带
		}
		//订单号，时间，收银员
		PrintManager.normal();
		PrintManager.left();
		PrintManager.size0();
		PrintManager.printlnText(context.getResources()
				.getString(R.string.order_time_tv) + format.format(new Date()));
		PrintManager.printlnText(SmarantPrintUtil.getCashier());//收银员

		PrintManager.left();
		printHalfDotLine();
		//菜品 + 价格

		PrintManager.bold();
		printDishHeader();
		PrintManager.normal();
		PrintManager.printText((SmarantPrintUtil.getDishItemsString(
		)));
		PrintManager.left();
		printHalfDotLine();
		PrintManager.printText(SmarantPrintUtil.getCostInfoStr());
		PrintManager.left();
		printHalfDotLine();

		//支付方式
		PrintManager.bold();
		PrintManager.printFormatText("支付方式" + "\n", 0);
		PrintManager.normal();
		PrintManager.printText(SmarantPrintUtil.getPayInfo());
		printHalfDotLine();
		if (Order.getInstance().isMember()) {
			PrintManager.bold();
			PrintManager.left();
			PrintManager.printFormatText("会员消费详情" + "\n", 0);
			PrintManager.normal();
			PrintManager.printText(SmarantPrintUtil.getMemberPayInfo());
			printHalfDotLine();
		}
		PrintManager.center();
		//		if (orderReq.isPrintQr()) {
		//			//0 表示订单号     1 表示菜单的密文
		//			PrintManager.printlnText("\n");
		//			PrintManager.printQr(orderReq.id);
		//		}
		//		if (Common.SHOP_INFO.printInvoiceQrcode) {
		//			PrintManager.printlnText("\n");
		//			PrintManager.printQr(getBarcodeUrl(orderReq.oid));
		//		}

		PrintManager.printlnText(SmarantPrintUtil.getWelcomFoot());
		PrintManager.printlnText(SmarantPrintUtil.getAddress());
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "end-print");
		PrintManager.cut();
	}

	/**
	 * 这个是同步时的小票
	 *
	 * @param context
	 * @param orderRes
	 */
	public void printSyncTicket(Context context, NewOrderRes orderRes) {
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "start-print");
		//		 printPictrue(PrintUtil.getLogo(context));
		//店名
		PrintManager.size1();
		PrintManager.bold();
		PrintManager.center();
		//		if (!orderRes.isOrderIsCorrect()) {
		//			PrintManager.printlnText("***订单已支付,同步POS失败，请将该小票交给服务员处理！***" + "\n");
		//		}
		PrintManager.printlnText(SmarantPrintUtil.getWelcom() + "\n");
		//桌牌号和堂食外带
		PrintManager.size1();
		PrintManager.left();
		PrintManager.normal();
		PrintManager.printlnText(Order.getInstance()
				.getTakeOutStr() + "              #" + Order.getInstance().getOrderSeq());
		//订单号，时间，收银员
		PrintManager.printlnText(" ");
		PrintManager.normal();
		PrintManager.left();
		PrintManager.size0();
		PrintManager.printlnText(context.getResources()
				.getString(R.string.order_num_tv) + orderRes.getContent().getId());//订单号 堂食or外带
		PrintManager.printlnText(context.getResources()
				.getString(R.string.order_time_tv) + format.format(new Date()));
		PrintManager.printlnText(SmarantPrintUtil.getCashier());//收银员

		PrintManager.left();
		printHalfDotLine();
		//菜品 + 价格

		PrintManager.bold();
		printDishHeader();
		PrintManager.normal();
		PrintManager.printText((SmarantPrintUtil.getSyncDishItemsString(
		)));
		PrintManager.left();
		printHalfDotLine();
		PrintManager.printText(SmarantPrintUtil.getSyncCostInfoStr());
		PrintManager.left();
		printHalfDotLine();

		//支付方式
		PrintManager.bold();
		PrintManager.printFormatText("支付方式" + "\n", 0);
		PrintManager.normal();
		PrintManager.printText(SmarantPrintUtil.getSyncPayInfo());
		printHalfDotLine();
		//		if (Order.getInstance().isMember()) {
		//			PrintManager.bold();
		//			PrintManager.left();
		//			PrintManager.printFormatText("会员消费详情" + "\n", 0);
		//			PrintManager.normal();
		//			PrintManager.printText(PrintUtil.getMemberPayInfo());
		//			printHalfDotLine();
		//		}
		PrintManager.center();
		//		if (orderReq.isPrintQr()) {
		//			//0 表示订单号     1 表示菜单的密文
		//			PrintManager.printlnText("\n");
		//			PrintManager.printQr(orderReq.id);
		//		}
		//		if (Common.SHOP_INFO.printInvoiceQrcode) {
		//			PrintManager.printlnText("\n");
		//			PrintManager.printQr(getBarcodeUrl(orderReq.oid));
		//		}

		PrintManager.printlnText(SmarantPrintUtil.getWelcomFoot());
		PrintManager.printlnText(SmarantPrintUtil.getAddress());
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "end-print");
		PrintManager.cut();
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 用于格式化日期,作为日志文件名的一部分

	public void printCXJTicket(Context context, NewOrderRes orderRes) {

		//店名

		PrintManager.size1();
		PrintManager.bold();
		PrintManager.center();
		PrintManager.printlnText(SmarantPrintUtil.getWelcom() + "\n");
		//桌牌号和堂食外带
		PrintManager.size1();
		PrintManager.left();
		PrintManager.normal();

		if (orderRes != null) {
			String callId  = orderRes.getContent().getCallNumber();
			String tableId = Order.getInstance().getTableId();
			if (tableId != null)
				callId = "餐牌号:" + tableId;
			if (!callId.contains("餐")) {
				PrintManager.printlnText(SmarantPrintUtil.getTitle());//取餐号
				//			取餐号:
				PrintManager.printlnText(context.getResources()
						.getString(R.string.cardnum_tv) + callId + "        " + Order.getInstance()
						.getTakeOutStr());
			} else {
				PrintManager
						.printlnText(callId + "        " + Order.getInstance()
								.getTakeOutStr());//桌牌号
			}
			//订单号，时间，收银员
			PrintManager.printlnText(" ");
			PrintManager.normal();
			PrintManager.left();
			PrintManager.size0();
			PrintManager.printlnText(context.getResources()
					.getString(R.string.order_num_tv) + orderRes.getContent().getId());//订单号 堂食or外带
		}
		//订单号，时间，收银员
		PrintManager.normal();
		PrintManager.left();
		PrintManager.size0();
		PrintManager.printlnText(context.getResources()
				.getString(R.string.order_time_tv) + format.format(new Date()));
		PrintManager.printlnText(SmarantPrintUtil.getCashier());//收银员

		PrintManager.left();
		printHalfDotLine();
		//菜品 + 价格

		PrintManager.bold();
		printDishHeader();
		PrintManager.normal();
		PrintManager.printText((SmarantPrintUtil.getDishItemsString(
		)));
		PrintManager.left();
		printHalfDotLine();
		PrintManager.printText(SmarantPrintUtil.getCostInfoStr());
		PrintManager.left();
		printHalfDotLine();

		//支付方式
		PrintManager.bold();
		PrintManager.printFormatText("支付方式" + "\n", 0);
		PrintManager.normal();
		PrintManager.printText(SmarantPrintUtil.getPayInfo());
		printHalfDotLine();
		if (Order.getInstance().isMember()) {
			PrintManager.bold();
			PrintManager.left();
			PrintManager.printFormatText("会员消费详情" + "\n", 0);
			PrintManager.normal();
			PrintManager.printText(SmarantPrintUtil.getMemberPayInfo());
			printHalfDotLine();
		}
		PrintManager.center();
		//		if (orderReq.isPrintQr()) {
		//			//0 表示订单号     1 表示菜单的密文
		//			PrintManager.printlnText("\n");
		//			PrintManager.printQr(orderReq.id);
		//		}
		//		if (Common.SHOP_INFO.printInvoiceQrcode) {
		//			PrintManager.printlnText("\n");
		//			PrintManager.printQr(getBarcodeUrl(orderReq.oid));
		//		}

		PrintManager.printlnText(SmarantPrintUtil.getWelcomFoot());
		PrintManager.printlnText(SmarantPrintUtil.getAddress());
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "end-print");
		PrintManager.cut();
	}




}
