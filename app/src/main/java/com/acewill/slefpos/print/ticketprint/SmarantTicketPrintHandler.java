package com.acewill.slefpos.print.ticketprint;

import android.content.Context;
import android.text.TextUtils;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.orderbean.NewOrderRes;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.orderbean.PrintOrder;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.print.PrintManager;
import com.jaydenxiao.common.commonutils.SPUtils;

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

	private static String dotLine;

	public void printDishHeader() {
		nameHeader = SystemConfig.isSmarantSystem ? "菜品" : "项目";
		StringBuilder sb = new StringBuilder();
		sb.append(nameHeader);
		for (int i = 0; i < Common.leftSpace - nameHeader.length() * 2; i++) {
			sb.append(" ");
		}
		sb.append(quantityHeader);
		for (int i = 0; i < Common.rightSpace - Common.leftSpace - quantityHeader
				.length() * 2 + 2; i++) {//+2 让金额两个字往右靠一点
			sb.append(" ");
		}
		sb.append(amountHeader + "\n");
		PrintManager.printText(sb.toString());
	}

	public void initdotLint() {
		if (SPUtils.getSharedIntData(BaseApplication.getAppContext(), "printtickettype") == 1)
			Common.totalSpace = 33;
		else
			Common.totalSpace = 48;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Common.totalSpace; i++) {
			if (i == Common.totalSpace - 1)
				sb.append("\n");
			sb.append("-");
		}
		dotLine = sb.toString();
	}

	public void printHalfDotLine() {
		PrintManager.printFormatText(dotLine);
	}

	public void printHalfDotLine58() {
		PrintManager.printText(dotLine);
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
		if (SPUtils.getSharedIntData(BaseApplication.getAppContext(), "printtickettype") == 1) {
			print58(context, orderRes);
		} else {
			print80(context, orderRes);
		}
	}

	private void print80(Context context, NewOrderRes orderRes) {
		Common.totalSpace = 48;
		Common.leftSpace = 32;
		Common.rightSpace = 41;


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
		PrintManager.center();
		PrintManager.printlnText(SmarantPrintUtil.getWelcomFoot());
		PrintManager.printlnText(SmarantPrintUtil.getAddress());
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "end-print");
		PrintManager.cut();
	}

	private void print58(Context context, NewOrderRes orderRes) {


		Common.totalSpace = 36;
		Common.leftSpace = 20;
		Common.rightSpace = 26;
		//店名
		PrintManager.size1();
		PrintManager.bold();
		PrintManager.left();

		/**
		 * 1、看一下一行能打多少个字 测试能容纳6个字，多出来的字要换行
		 * 2、计算店的名称长度
		 * 3、动态的添加空格
		 */
		if (!TextUtils.isEmpty(SmarantPrintUtil.getWelcom())) {
			String welcom = SmarantPrintUtil.getWelcom();
			if (welcom.length() > 8) {
				String first  = welcom.substring(0, 7);
				String second = welcom.substring(7, welcom.length());
				PrintManager.printlnText(first + "\n");
				PrintManager.printlnText(second + "\n");
			} else {
				PrintManager.printlnText(welcom + "\n");
			}
		}

		//桌牌号和堂食外带
		PrintManager.size1();
		PrintManager.left();
		PrintManager.normal();
		PrintManager.printlnText(Order.getInstance()
				.getTakeOutStr() + "       #" + Order.getInstance().getOrderSeq());
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
		printHalfDotLine58();
		//菜品 + 价格

		PrintManager.bold();
		printDishHeader();
		PrintManager.normal();
		PrintManager.printText((SmarantPrintUtil.getSyncDishItemsString(
		)));
		PrintManager.left();
		printHalfDotLine58();
		PrintManager.printText(SmarantPrintUtil.getSyncCostInfoStr());
		PrintManager.left();
		printHalfDotLine58();

		//支付方式
		PrintManager.bold();
		PrintManager.printFormatText("支付方式" + "\n", 0);
		PrintManager.normal();
		PrintManager.printText(SmarantPrintUtil.getSyncPayInfo());
		printHalfDotLine58();
		PrintManager.printlnText(SmarantPrintUtil.getWelcomFoot());
		PrintManager.printlnText(SmarantPrintUtil.getAddress());
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "end-print");
		PrintManager.cut();
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 用于格式化日期,作为日志文件名的一部分

	/**
	 * 获取打印的model
	 *
	 * @param orderRes
	 * @return
	 */
	private PrintOrder getPrintOrder(NewOrderRes orderRes) {
		PrintOrder printOrder = new PrintOrder();
		if (orderRes != null) {
			String callId  = orderRes.getContent().getCallNumber();//取餐号
			String tableId = Order.getInstance().getTableId();//餐牌号
			if (tableId != null)
				callId = "餐牌号:" + tableId;

			printOrder.setCallId(callId);
			printOrder.setEatmethod(Order.getInstance().getTakeOutStr());

			printOrder.setOrderId(AppApplication.getContext().getResources()
					.getString(R.string.order_num_tv) + orderRes.getContent().getId());//订单号 堂食or外带
		}
		printOrder.setWelcome(SmarantPrintUtil.getWelcom());
		printOrder.setCreateTime( format.format(new Date()));
		printOrder.setCasher(SmarantPrintUtil.getCashier());
		printOrder.setDishList(SmarantPrintUtil.getDishItemsString(
		));
		printOrder.setCostList(SmarantPrintUtil.getCostInfoStr());
		printOrder.setPayInfoList(SmarantPrintUtil.getPayInfo());
		if (Order.getInstance().isMember()){
			printOrder.setMemberNameAndPhone();
			printOrder.setMemberPayInfo(SmarantPrintUtil.getMemberPayInfo());
		}
		printOrder.setWelcomefood(SmarantPrintUtil.getWelcomFoot());
		printOrder.setAddress(SmarantPrintUtil.getAddress());
		printOrder.setMember(Order.getInstance().isMember());

		/**
		 * 以下是智慧快餐多出的部分
		 */

		printOrder.setJyj_address(StoreConfigure.getJyjAddress());


		return printOrder;
	}


	/**
	 * 打印餐行健的小票
	 * @param orderRes
	 */
	public void printCXJTicket(NewOrderRes orderRes) {
		PrintOrder printOrder = getPrintOrder(orderRes);
		SmarantPrintUtil.addPrintOrder(printOrder);
		printTicket(printOrder);
	}


	/**
	 * 打印智慧快餐的小票
	 * @param orderRes
	 */
	public void printSmarantTicket( NewOrderRes orderRes) {
		PrintOrder printOrder = getPrintOrder(orderRes);
		SmarantPrintUtil.addPrintOrder(printOrder);
		if (orderRes.isFail()) {
			PrintManager.printlnText("\n"+"***订单已支付,同步POS失败，请将该小票交给服务员处理！***" + "\n");
		}
		printTicket(printOrder);
	}



	/**
	 * 智慧快餐和餐行健共用一套打印
	 *
	 * @param printOrder
	 */
	public void printTicket(PrintOrder printOrder) {
		PrintManager.size1();
		PrintManager.bold();
		PrintManager.center();
		PrintManager.printlnText(printOrder.getWelcome() + "\n");
		//桌牌号和堂食外带
		PrintManager.size1();
		PrintManager.left();
		PrintManager.normal();
		if (!printOrder.getCallId().contains("餐")) {
			PrintManager.printlnText(SmarantPrintUtil.getTitle());//取餐号
			//			取餐号:
			PrintManager.printlnText(AppApplication.getContext().getResources()
					.getString(R.string.cardnum_tv) + printOrder
					.getCallId() + "        " + printOrder.getEatmethod());
		} else {
			PrintManager
					.printlnText(printOrder.getCallId() + "        " + printOrder
							.getEatmethod());//桌牌号
		}
		//订单号，时间，收银员
		PrintManager.printlnText(" ");
		PrintManager.normal();
		PrintManager.left();
		PrintManager.size0();
		PrintManager.printlnText(printOrder.getOrderId());//订单号 堂食or外带
		//订单号，时间，收银员
		PrintManager.normal();
		PrintManager.left();
		PrintManager.size0();
		PrintManager.printlnText(AppApplication.getContext().getResources()
				.getString(R.string.order_time_tv) +printOrder.getCreateTime());
		PrintManager.printlnText(printOrder.getCasher());//收银员

		PrintManager.left();
		printHalfDotLine();
		//菜品 + 价格

		PrintManager.bold();
		printDishHeader();
		PrintManager.normal();
		PrintManager.printText(printOrder.getDishList());
		PrintManager.left();
		printHalfDotLine();
		PrintManager.printText(printOrder.getCostList());
		PrintManager.left();
		printHalfDotLine();

		//支付方式
		PrintManager.bold();
		PrintManager.printFormatText("支付方式" + "\n", 0);
		PrintManager.normal();
		PrintManager.printText(printOrder.getPayInfoList());
		printHalfDotLine();
		if (printOrder.isMember()) {
			PrintManager.bold();
			PrintManager.left();
			PrintManager.printFormatText("会员消费详情" + "\n", 0);
			PrintManager.normal();
			PrintManager.printText(printOrder.getMemberPayInfo());
			printHalfDotLine();
		}
		PrintManager.center();
		PrintManager.printlnText(printOrder.getWelcomefood());
		PrintManager.printlnText(printOrder.getAddress());
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		PrintManager.printlnText(" ");
		//		FileLog.log(Common.Log, PrintManager.class, "printSmarantTicket", "print", "end-print");
		PrintManager.cut();
	}
}
