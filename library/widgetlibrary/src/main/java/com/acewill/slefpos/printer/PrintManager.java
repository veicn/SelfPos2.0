//package com.acewill.slefpos.printer;
//
////
//
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.acewill.slefpos.print.Common;
//import com.acewill.slefpos.print.PrintModel;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
////import com.acewill.ordermachine.model.Printer;
//
///**
// * 打印厨房小票 Created by aqw on 2016/9/28.
// */
//public class PrintManager {
//	//
//	private List<Printer>       printerList;
//	private static PrintManager printManager;
//	// private static List<OrderItem> orderItemList = new
//	// ArrayList<>();//记录打印成功的菜品
//	public String               printName;// 打印机名称，用于打印失败时提示哪个打印机
//	private int lineWidth = 23;//行宽
//
//	public static PrintManager getInstance() {
//		if (printManager == null) {
//			printManager = new PrintManager();
//		}
//
//		return printManager;
//	}
//
//	private HashMap<Long, String> sumPrinter = new HashMap<Long, String>();
//	public boolean mode = false;
//
//	/**
//	 * 打印厨房订单/退菜单
//	 *
//	 * @param isrefund
//	 */
//	public void print(PrintModel orderPrinter, final int isrefund)
//			throws Exception {
//		if(!Common.SHOP_INFO.kitPrint){
//			return ;
//		}
//		sumPrinter.clear();
//		if (printerList == null || printerList.size() == 0) {
//			return;
//		}
//
//		for (Printer printer : printerList) {
//			boolean printerSum = printSingleDish(orderPrinter, printer);
//
//			if (printer.getSummaryReceiptCounts() > 0 && printerSum) {
//
//				printSumDish(printer, orderPrinter, isrefund);
//			}
//		}
//		// orderItemList.clear();//清除打印成功记录
//	}
//
//	private boolean printSingleDish(PrintModel orderPrinter, Printer printer)
//			throws Exception {
//		boolean printerSum = false;
//		PrinterInterface printerInterface = PrinterFactory.createPrinter(
//				PrinterVendor.fromName(printer.getVendor()), printer.getIp(),
//				PrinterWidth.WIDTH_76MM);
//
//		for (DishModel dish : Cart.getInstance().items) {
//			if (dish.isPackage()) {
//				for (BaseDishModel subItem : dish.subItemList) {
//					subItem.quantity = dish.quantity;
//					subItem.packName = dish.dishName;
//					boolean isPrintSum = printSingleDish(printer,
//							printerInterface, orderPrinter, subItem,
//							dish.getRemarks(),dish.getCurrentPrice());
//					if (isPrintSum) {
//						printerSum = true;
//					}
//				}
//
//			} else {
//
//				boolean isPrintSum = printSingleDish(printer, printerInterface,
//						orderPrinter, dish, dish.getRemarks(),dish.getCurrentPrice());
//				if (isPrintSum) {
//					printerSum = true;
//				}
//			}
//		}
//		if (printer.getDishReceiptCounts() > 0) {
//			printerInterface.close();
//		}
//		return printerSum;
//	}
//
//	// 该打印机包含该菜品，返回true
//	private boolean printSingleDish(Printer printer,
//			PrinterInterface printerInterface, PrintModel orderPrinter,
//			BaseDishModel dish, String remarks,String price) throws Exception {
//		if (dish.containsPrint(printer.getId() + "")) {
//			printName = printer.getDeviceName();
//			Log.i("厨房打印", "打印机" + printName + "," + printer.getIp());
//			if (printer.getDishReceiptCounts() == 0) {
//				return true;
//			}
//			// 判断是标签纸还是普通纸
//			if (printer.getOutputType().getType() == PrinterOutputType.REGULAR.getType()) {
//				Log.i("厨房打印菜品:" + dish.dishName, printer.getIp() + " start");
//
//				printerInterface.init();
//				printDish(printerInterface, orderPrinter, dish, remarks,price);
//
//				Log.i("厨房打印菜品:", "end");
//				// 标签打印
//			} else {
//				// for (int i = 0; i < oi.getQuantity(); i++)
//				// {//标签一份打印一张
//				// Log.i("标签打印:", oi.getDishName());
//				// GpEnternetPrint.gpPrint(oi, isrefund,
//				// printer.getIp(), printer.getLabelHeight());
//				// }
//			}
//
//			// 记录打印成功的菜品(为了打印失败时，补打使用)
//			// orderItemList.add(oi);
//			return true;
//		}
//		return false;
//	}
//
//	private int printSize = 2;
//
//	public void printTitle(PrinterInterface printerInterface, PrintModel order,
//			boolean kitSummary) throws IOException {
//		boolean mode = false;
//
//		String typeStr = "";
//
//		String eatout = typeStr + order.isTakeOutStr;
//		if (TextUtils.isEmpty(order.tid)) {
//			eatout += "(" + ToSBC(order.callId) + "号)";
//		} else {
//			eatout += "(" + ToSBC(order.tid) + "号)";
//		}
//		if (kitSummary) {
//			eatout += "总单";
//		} else {
//			eatout += "分单";
//		}
//
//		TextRow row = createRow(false, 2, eatout, mode);
//		row.setAlign(Alignment.CENTER);
//		printerInterface.printRow(row);
//		printerInterface.printRow(new Separator(" "));
//
//		printerInterface.printRow(createRow(false, 1, "单号: " + order.id, mode));
//
//		printerInterface.printRow(createRow(false, 1, order.stime, mode));
//		printerInterface.printRow(new Separator(" "));
//		String dishTitle = getStr("菜品", "数量", 37, false);
//		printerInterface.printRow(createRow(false, 1, dishTitle, mode));
//		printerInterface.printRow(new Separator("-"));
//	}
//
//	// 分单
//	public void printDish(PrinterInterface printerInterface, PrintModel order,
//			BaseDishModel dish, String remarks,String price) throws Exception {
//
//		if (StoreInfo.getPrintConfig().getKitchenPrintMode() == KitchenPrintMode.PER_DISH) {
//
//			printTitle(printerInterface, order, false);
//			printOrderItem(printerInterface, order.isWaiDai(), dish,
//					dish.quantity,price);
//			printItemRemarks(printerInterface, remarks);
//			printerInterface.cut();
//		} else {
//			for (int i = 0; i < dish.quantity; i++) {
//
//				printTitle(printerInterface, order, false);
//				printOrderItem(printerInterface, order.isWaiDai(), dish, 1,price);
//				printItemRemarks(printerInterface, remarks);
//				printerInterface.cut();
//			}
//		}
//
//	}
//
//
//	public void printSumDish(Printer printer, PrintModel order, int isrefund)
//			throws Exception {
//		PrinterInterface printerInterface = PrinterFactory.createPrinter(
//				PrinterVendor.fromName(printer.getVendor()), printer.getIp(),
//				PrinterWidth.WIDTH_76MM);
//		printerInterface.init();
//		printTitle(printerInterface, order, true);
//		for (DishModel dish : Cart.getInstance().items) {
//			TextRow dishRow = getDishRow(printer, order, dish);
//			if (dishRow != null) {
//				printerInterface.printRow(dishRow);
//				printItemRemarks(printerInterface, dish.getRemarks());
//			}
//		}
//		printerInterface.cut();
//		printerInterface.close();
//	}
//
//	private TextRow getDishRow(Printer printer, PrintModel order, DishModel dish) {
//		String dishItemStr = getDishItemStr(printer, order, dish);
//		if (dishItemStr == null) {
//			return null;
//		}
//		TextRow oiRow = createRow(false, 2, dishItemStr, mode);
//		oiRow.setScaleWidth(printSize);
//		return oiRow;
//	}
//
//	private void printOrderItem(PrinterInterface printerInterface,
//			String waidai, BaseDishModel dish, int dishCount,String price)
//			throws IOException {
//		String dishItemStr = getDishItemStr(waidai, dish.dishUnit,
//				dish.packName, dish.dishName, dishCount,price);
//		TextRow oiRow = createRow(false, 2, dishItemStr, mode);
//		oiRow.setScaleWidth(printSize);
//		// oiRow.setScaleHeight(printSize);
//		printerInterface.printRow(oiRow);
//
//	}
//
//	private void printItemRemarks(PrinterInterface printerInterface, String sku)
//			throws IOException {
//		if (!TextUtils.isEmpty(sku)) {
//			TextRow skuRow = createRow(false, 2, "备注: " + sku, mode);
//			skuRow.setScaleWidth(printSize);
//			// skuRow.setScaleHeight(printSize);
//			printerInterface.printRow(new Separator(" "));
//			printerInterface.printRow(skuRow);
//		}
//	}
//
//	/**
//	 * 分单
//	 *
//	 * @return
//	 */
//	private String getDishItemStr(String waidai, String dishUnit,
//			String packName, String dishName, int dishCount,String price) {
//		if (TextUtils.isEmpty(dishUnit)) {
//			dishUnit = "份";
//		}
//		StringBuilder dishItem = new StringBuilder();
//		if (StoreInfo.isPrintPackName()) {
//			if (!TextUtils.isEmpty(packName)) {
//				dishItem.append("(").append(packName).append(")").append("\n");
//			}
//		}
//
//		dishItem.append(getStr(dishName + waidai, ToSBC(dishCount) + dishUnit,
//				lineWidth, false));
//		if(Common.SHOP_INFO.kitPrintPrice){
//			dishItem.append("\n价格  "+price);
//		}
//		return dishItem.toString();
//	}
//
//	/**
//	 * 总单
//	 *
//	 * @return
//	 */
//	private String getDishItemStr(Printer printer, PrintModel order,
//			DishModel dish) {
//		String dishUnit = "份";
//		if (!TextUtils.isEmpty(dish.dishUnit)) {
//			dishUnit = dish.dishUnit;
//		}
//		StringBuilder dishItem = new StringBuilder();
//		if (StoreInfo.isPrintPackName() && dish.isPackage()) {
//			dishItem.append("(").append(dish.dishName).append(")");
//			if(Common.SHOP_INFO.kitPrintPrice){
//				dishItem.append(" 价格  "+dish.getCurrentPrice());
//			}
//			dishItem.append("\n");
//		}
//		StringBuilder sb = new StringBuilder();
//		if (dish.isPackage()) {
//			for (BaseDishModel subDish : dish.subItemList) {
//				if (subDish.containsPrint(printer.getId() + "")) {
//					sb.append(
//							getStr(subDish.dishName + order.isWaiDai(),
//									ToSBC(dish.quantity) + dishUnit, lineWidth, false))
//							.append("\n");
//				}
//			}
//		} else {
//			if (dish.containsPrint(printer.getId() + "")) {
//
//				sb.append(getStr(dish.dishName + order.isWaiDai(),
//						ToSBC(dish.quantity) + dishUnit, lineWidth, false));
//				if(Common.SHOP_INFO.kitPrintPrice){
//					sb.append("\n价格  "+dish.getCurrentPrice());
//				}
//			}
//		}
//		if (sb.length() == 0) {
//			return null;
//		}
//		return dishItem.append(sb).toString();
//	}
//
//	/**
//	 * 拼接打印行
//	 *
//	 * @param pre
//	 *            行的左边字符
//	 * @param back
//	 *            行的右边字符
//	 * @param width
//	 *            一行总字符数
//	 * @return
//	 */
//	private String getStr(String pre, String back, int width, boolean isscale) {
//		int num = 0;
//		if (isscale) {
//			num = width
//					- (PrintUtils.length_s(pre) + PrintUtils.length_s(back));
//		} else {
//			num = width - (PrintUtils.length(pre) + PrintUtils.length(back));
//		}
//		String result = pre + PrintUtils.getSpace(num) + back;
//		return result;
//	}
//
//	// 获取定制项或做法或口味
//	private String getRemarks(DishModel dish) {
//		String commnt = "";
//		// 定制项
//		if (dish.optionList != null && dish.optionList.size() > 0) {
//
//			for (OptionBean option : dish.optionList) {
//				commnt += option.optionName + ",";
//			}
//		}
//
//		if (commnt.length() > 0) {
//			commnt = commnt.substring(0, commnt.length() - 1);
//		}
//		return commnt;
//	}
//
//	private TextRow createRow(boolean bold, int size, String content,
//			boolean revertMode) {
//		TextRow title = new TextRow(content);
//		title.setScaleWidth(size);
//		title.setScaleHeight(size);
//		title.setBoldFont(bold);
//		title.setRevertMode(revertMode);
//		return title;
//	}
//
//	// 创建一行， 内容为 左对齐 和 有对齐 的2列
//	private Table createRow(boolean bold, int size, String left, String right) {
//		Table table = new Table(2);
//		TextRow row = new TextRow();
//
//		row.setScaleHeight(size);
//		row.setScaleWidth(size);
//		row.setBoldFont(bold);
//		row.addColumn(new Column(left, Alignment.LEFT));
//		row.addColumn(new Column(right, Alignment.RIGHT));
//
//		table.addRow(row);
//		return table;
//	}
//
//	public List<Printer> getPrinterList() {
//		return printerList;
//	}
//
//	public void setPrinterList(List<Printer> printerList) {
//		this.printerList = printerList;
//	}
//
//	/**
//	 * 半角转全角
//	 *
//	 * @param inputs
//	 *            String.
//	 * @return 全角字符串.
//	 */
//	public static String ToSBC(int inputs) {
//		String input = inputs + "";
//		char c[] = input.toCharArray();
//		for (int i = 0; i < c.length; i++) {
//			if (c[i] == ' ') {
//				c[i] = '\u3000';
//			} else if (c[i] < '\177') {
//				c[i] = (char) (c[i] + 65248);
//			}
//		}
//		return new String(c);
//	}
//
//	/**
//	 * 半角转全角
//	 *
//	 * @param input
//	 *            String.
//	 * @return 全角字符串.
//	 */
//	public static String ToSBC(String input) {
//		char c[] = input.toCharArray();
//		for (int i = 0; i < c.length; i++) {
//			if (c[i] == ' ') {
//				c[i] = '\u3000';
//			} else if (c[i] < '\177') {
//				c[i] = (char) (c[i] + 65248);
//			}
//		}
//		return new String(c);
//	}
//
//}
