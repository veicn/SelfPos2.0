//package com.acewill.slefpos.print;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.text.TextUtils;
//
//import com.acewill.ordermachine.R;
//import com.acewill.ordermachine.activity.OrderDetailActivity;
//import com.acewill.ordermachine.model.Cart;
//import com.acewill.ordermachine.model.DishModel;
//import com.acewill.ordermachine.model.LoginReqModel;
//import com.acewill.ordermachine.model.OptionBean;
//import com.acewill.ordermachine.model.OrderReq;
//import com.acewill.ordermachine.model.PackageBean;
//import com.acewill.ordermachine.model.PrintModel;
//import com.acewill.ordermachine.model.WshAccount;
//import com.acewill.ordermachine.printer.Alignment;
//import com.acewill.ordermachine.printer.TextRow;
//import com.acewill.ordermachine.util.PriceUtil;
//import com.jaydenxiao.common.utils.logutil.FileLog;
//
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class PrintUtil {
//	public static String dotLine = "";
//	//	public static String dotLineHalf = "    ----------------------------------------    \n";
//	public static int    paperWidth;// 48 42
//	// 店名 大小 位置
//	public static String SHOP_NAME;
//	// 桌号 大小 位置
//	public static String TABLE_NO;
//	// 餐段
//	// 单号 大小 位置
//	public static String ORDER_NO;
//	// 设备号
//	public static String POS_ID;
//
//	public static Bitmap LOGO;
//
//	public static String TIPS = "比格北京望承公园店";
//	// public static String TIPS = "请等待服务员叫号";
//
//	//	private static String spaceNumPrice = "";
//
//	//	private static String nameHeader     = "菜品";
//	//	private static String quantityHeader = "数量";
//	//	private static String amountHeader   = "金额";
//
//
//
//	private static List<String> charArr = new ArrayList<>();
//
//	static {
//		charArr.add("(");
//		charArr.add(")");
//		charArr.add("[");
//		charArr.add("]");
//		charArr.add("a");
//		charArr.add("b");
//		charArr.add("c");
//		charArr.add("d");
//		charArr.add("e");
//		charArr.add("f");
//		charArr.add("g");
//		charArr.add("h");
//		charArr.add("i");
//		charArr.add("j");
//		charArr.add("k");
//		charArr.add("l");
//		charArr.add("m");
//		charArr.add("n");
//		charArr.add("o");
//		charArr.add("p");
//		charArr.add("q");
//		charArr.add("r");
//		charArr.add("s");
//		charArr.add("t");
//		charArr.add("u");
//		charArr.add("v");
//		charArr.add("w");
//		charArr.add("x");
//		charArr.add("y");
//		charArr.add("z");
//		charArr.add("0");
//		charArr.add("1");
//		charArr.add("2");
//		charArr.add("3");
//		charArr.add("4");
//		charArr.add("5");
//		charArr.add("6");
//		charArr.add("7");
//		charArr.add("8");
//		charArr.add("9");
//		charArr.add(" ");
//	}
//
//
//	public static String getTips() {
//		return Common.SHOP_INFO.name;
//	}
//
//	public static String getTitle() {
//		return "请留意取餐区叫号屏";
//	}
//
//	public static String getTableNo(String tableNo) {
//		return "桌牌号: " + tableNo;
//	}
//
//	public static String getTime(String stime) {
//		Date date = null;
//		if (TextUtils.isEmpty(stime)) {
//			date = new Date();
//		} else {
//		}
//		String time = new SimpleDateFormat("yyyy/MM/dd/  HH:mm:ss")
//				.format(date);
//		return time;
//	}
//
//	public static String getDishItemsString(List<DishModel> dishs) {
//		StringBuilder sb = new StringBuilder();
//		for (DishModel dishModel : dishs) {
//			//第一行  名称       数量         价格
//			int spaceNum = 0;
//			if (dishModel.isPackage()) {
//				dishModel.dishName = "(" + dishModel.dishName + ")";
//			}
//			if (dishModel.cost.equals("0")) {
//				dishModel.dishName = "(赠)" + dishModel.dishName;
//			}
//			sb.append(dishModel.dishName);
//
//			int headNameLength = calculateHeadNameLength(dishModel.dishName);
//			spaceNum =  Common.leftSpace - headNameLength;
//			int gap = 0;
//			if (spaceNum > 0) {
//				for (int i = 0; i < spaceNum + 1; i++) {//数量的数字往后移了一位
//					sb.append(" ");
//				}
//			} else {
//				sb.append("  ");
//				gap = Math.abs(spaceNum) + 2;//文字过长的情况，这是计算文字过长的部分和数量数字的部分， 后面计算的时候减去就行了
//			}
//
//			sb.append(dishModel.quantity);
//			if (gap == 0) {
//				spaceNum =  Common.rightSpace -  Common.leftSpace - String
//						.valueOf(dishModel.quantity)
//						.length() - 1;//减1 是因为数量的数字往后移了一位
//			} else {
//				spaceNum =  Common.rightSpace -  Common.leftSpace - String
//						.valueOf(dishModel.quantity)
//						.length() - gap;
//			}
//
//			for (int i = 0; i < spaceNum; i++) {
//				sb.append(" ");
//			}
//			sb.append(formatPriceString(PriceUtil.formatPrice(dishModel.getOriginalPrice())));
//			sb.append("\n");
//
//			//第二行    套餐的每一个单项
//
//			if (dishModel.getItems() != null) {//打印套餐
//				for (PackageBean packageBean : dishModel.getItems()) {
//					sb.append("[套]");
//					sb.append(packageBean.dishName);
//					int headNameLength2 = calculateHeadNameLength(packageBean.dishName);
//					int spaceNum2       =  Common.leftSpace - headNameLength2;
//					for (int i = 0; i < spaceNum2-3; i++) {
//						sb.append(" ");
//					}
//					sb.append("x" + packageBean.quantity);
//					sb.append("\n");
//					if (packageBean.optionList != null) {
//						sb.append("    ");
//						for (int i = 0; i < packageBean.optionList.size(); i++) {
//							OptionBean bean = packageBean.optionList.get(i);
//							if (i != packageBean.optionList.size() - 1) {
//								if (bean.price != 0) {
//									sb.append("+" + bean.optionName + "(￥" + PriceUtil
//											.formatPrice(String.valueOf(bean.price)) + ")、");
//								} else {
//									sb.append("+" + bean.optionName + "、");
//								}
//							} else {
//								if (bean.price != 0) {
//									sb.append("+" + bean.optionName + "(￥" + PriceUtil
//											.formatPrice(String.valueOf(bean.price)) + ")");
//								} else {
//									sb.append("+" + bean.optionName);
//								}
//							}
//						}
//						sb.append("\n");
//					}
//				}
//			}
//
//			if (dishModel.optionList != null) {//打印可选项
//				sb.append("    ");
//				for (int i = 0; i < dishModel.optionList.size(); i++) {
//					OptionBean bean = dishModel.optionList.get(i);
//
//					if (i != dishModel.optionList.size() - 1) {
//						if (bean.price != 0) {
//							sb.append("+" + bean.optionName + "(￥" + PriceUtil
//									.formatPrice(String.valueOf(bean.price)) + ")、");
//						} else {
//							sb.append("+" + bean.optionName + "、");
//						}
//					} else {
//						if (bean.price != 0) {
//							sb.append("+" + bean.optionName + "(￥" + PriceUtil
//									.formatPrice(String.valueOf(bean.price)) + ")");
//						} else {
//							sb.append("+" + bean.optionName);
//						}
//					}
//				}
//				sb.append("\n");
//			}
//		}
//		return sb.toString();
//	}
//
//	private static String formatPriceString(String price) {//让价格右对齐
//		StringBuilder sb          = new StringBuilder();
//		int           totalSpace  = 7;
//		int           priceLength = price.length();
//		int           length      = totalSpace - priceLength;
//		for (int i = 0; i < length; i++) {
//			sb.append(" ");
//		}
//		sb.append(price);
//
//		return sb.toString();
//	}
//
//
//	private static int calculateHeadNameLength(String headName) {
//		int    size  = headName.length() * 2;
//		char[] chars = headName.toCharArray();
//		for (char a : chars) {
//			if (charArr.contains(String.valueOf(a)))
//				size = size - 1;
//		}
//		return size;
//	}
//
//	public static int getQuantity(List<DishModel> dishs) {
//		int count = 0;
//		for (int i = 0; i < dishs.size(); i++) {
//			count += dishs.get(i).quantity;
//		}
//		return count;
//	}
//
//	public static String appenThreeParam(String headName, int quantity, String price) {
//
//
//		StringBuilder sb = new StringBuilder();
//		sb.append(headName);
//		int spaceNum       = 0;
//		int headNameLength = calculateHeadNameLength(headName);
//		spaceNum =  Common.leftSpace - headNameLength;
//		int gap = 0;
//		if (spaceNum > 0) {
//			for (int i = 0; i < spaceNum + 1; i++) {
//				sb.append(" ");
//			}
//		} else {
//			sb.append("  ");
//			gap = Math.abs(spaceNum) + 4;//文字过长的情况，这是计算文字过长的部分和数量数字的部分， 后面计算的时候减去就行了
//		}
//
//		sb.append(quantity);
//		if (gap == 0) {
//			spaceNum =  Common.rightSpace -  Common.leftSpace - String
//					.valueOf(quantity)
//					.length() - 1;
//		} else {
//			spaceNum =  Common.rightSpace - Common. leftSpace - String
//					.valueOf(quantity)
//					.length() - gap;
//		}
//
//		for (int i = 0; i < spaceNum; i++) {
//			sb.append(" ");
//		}
//		sb.append(formatPriceString(PriceUtil.formatPrice(price)));
//		sb.append("\n");
//
//		return sb.toString();
//	}
//
//	public static String appendTwoParam(String headName, String price) {
//		StringBuilder sb       = new StringBuilder();
//		int           spaceNum = 0;
//		sb.append(headName);
//		spaceNum =  Common.rightSpace - headName.length() * 2;
//		for (int i = 0; i < spaceNum; i++) {
//			sb.append(" ");
//		}
//		sb.append(formatPriceString(price) + "\n");
//		return sb.toString();
//	}
//
//	public static String getCostInfoStr(List<DishModel> dishs, com.acewill.ordermachine.model.PrintModel order) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(appenThreeParam("应收", getQuantity(dishs), order.total));
//		if (order.packingCost != null) {
//			sb.append(appendTwoParam("打包盒", PriceUtil.formatPrice(order.packingCost)));
//		}
//		if (order.discountPrice != null) {
//			sb.append(appendTwoParam("优惠", PriceUtil.formatPrice(order.discountPrice)));
//		}
//		sb.append(appendTwoParam("实收", Cart.getInstance().getTempPrice().toString()));
//		return sb.toString();
//	}
//
//
//	public static String getOrderNo(String orderNo) {
//		return "订单号\n" + orderNo + "\n";
//	}
//
//	public static String getFooterString() {
//		StringBuilder sb = new StringBuilder();
//		return sb.toString();
//	}
//
//	public static String getPaymentString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("持卡人存根   请妥善保管\n");
//		sb.append(dotLine);
//		sb.append("商户名称：\n");
//		sb.append("商户号：\n");
//		sb.append("终端号：\n");
//		sb.append("操作员：\n");
//		sb.append("\n\n");
//		sb.append("本人确认以上交易，同意将其计入本卡账户");
//		return sb.toString();
//	}
//
//	private static int calculateSpaceNum(String dishName) {
//		int total = 0;
//
//		for (int i = 0; i < dishName.length(); i++) {
//			if (isChinese(dishName.charAt(i))) {
//				total += 2;
//			} else {
//				total += 1;
//			}
//		}
//
//		return total;
//	}
//
//	public static boolean isChinese(char a) {
//		int v = (int) a;
//		return (v >= 19968 && v <= 171941);
//	}
//
//	public static String getShopInfo() {
//		return "电话:" + Common.SHOP_INFO.telNo + "\n" + "地址:"
//				+ Common.SHOP_INFO.shopAddress + "\n";
//	}
//
//	public static String getPayInfo(PrintModel orderReq) {
//		StringBuilder sb = new StringBuilder();
//		//		sb.append("支付方式 :" + "\n");
//		if (orderReq.getPaymentList() == null)
//			return "";
//		for (int i = 0; i < orderReq.getPaymentList().size(); i++) {
//			OrderReq.Payment payment            = orderReq.getPaymentList().get(i);
//			String           paymentTypeNameStr = payment.paymentTypeName;
//			if (payment.paymentTypeName.equals("威富通")) {
//				switch (OrderDetailActivity.clickPosition) {
//					case R.id.orderdetail_pay_method_wechat:
//						paymentTypeNameStr = paymentTypeNameStr + " (微信)";
//						break;
//					case R.id.orderdetail_pay_method_zhifubao:
//						paymentTypeNameStr = paymentTypeNameStr + " (支付宝)";
//						break;
//				}
//
//			}
//
//			sb.append(appendTwoParam(paymentTypeNameStr, PriceUtil.formatPrice(payment.value)));
//		}
//		return sb.toString();
//	}
//
//	public static String getWelcom() {
//		return Common.SHOP_INFO.name;
//	}
//
//	public static String getWelcomFoot() {
//		return "欢迎下次光临!\n联系电话: " + Common.SHOP_INFO.telNo;
//	}
//
//	public static String getAddress() {
//		return "地址:" + Common.SHOP_INFO.shopAddress;
//	}
//
//	public static String getCashier() {
//		return "收银员: " + LoginReqModel.tname;
//	}
//
//	private static void addRow(ArrayList<TextRow> arrayList, boolean bold, int size, int align, String content,
//	                           boolean revertMode) {
//		arrayList.add(createRow(bold, size, align, content, revertMode));
//	}
//
//	private static TextRow createRow(boolean bold, int size, int align, String content,
//	                                 boolean revertMode) {
//		TextRow title = new TextRow(content);
//		title.setScaleWidth(size);
//		title.setScaleHeight(size);
//		title.setBoldFont(bold);
//		title.setRevertMode(revertMode);
//
//		switch (align) {
//			case 0:
//				title.setAlign(Alignment.LEFT);
//				break;
//			case 1:
//				title.setAlign(Alignment.CENTER);
//				break;
//			case 2:
//				title.setAlign(Alignment.RIGHT);
//				break;
//
//			default:
//				break;
//		}
//		return title;
//	}
//
//	private static void addPrintModel(int size, int align, String text,
//	                                  ArrayList<com.acewill.ordermachine.print.PrintModel> arrayList) {
//		com.acewill.ordermachine.print.PrintModel printModel = new com.acewill.ordermachine.print.PrintModel();
//		printModel.setSize(size);
//		printModel.align = align;
//		printModel.text = text;
//
//		arrayList.add(printModel);
//	}
//
//
//	public static String getQrString() {
//
//
//		StringBuilder sb = new StringBuilder();
//
//		//// TODO: 2017/5/10 anch  拼接二维码的内容
//		for (int i = 0; i < Cart.getInstance().getAllItems().size(); i++) {
//
//			DishModel model = Cart.getInstance().getAllItems().get(i);
//			if (i == 0) {
//				sb.append("b");
//			}
//			//套餐
//			if (model.isPackage()) {
//				sb.append("2");
//				sb.append("|");
//				sb.append(model.dishID);
//				sb.append("x");
//				sb.append(model.quantity);
//				sb.append("c");
//
//				if (model.subItemList != null) {
//					for (int j = 0; j < model.subItemList.size(); j++) {
//						PackageBean aPackageBean = model.subItemList.get(j);
//						if (j == 0) {
//							sb.append("[");
//						}
//						sb.append(aPackageBean.dishID);
//						//						sb.append("*");
//						//						sb.append(aPackageBean.quantity);
//						if (j == model.subItemList.size() - 1) {
//							sb.append("]");
//						} else {
//							sb.append("r");
//						}
//					}
//				}
//
//				//选择项
//			} else if (model.haveOption()) {
//				sb.append("1");
//				sb.append("|");
//				sb.append(model.dishID);
//				sb.append("x");
//				sb.append(model.quantity);
//				sb.append("c");
//				if (model.optionList != null) {
//					for (int j = 0; j < model.optionList.size(); j++) {
//						OptionBean option = model.optionList.get(j);
//						if (j == 0) {
//							sb.append("q");
//						}
//						sb.append(option.id);
//						if (j == model.optionList.size() - 1) {
//							sb.append("w");
//						} else {
//							sb.append("r");
//						}
//					}
//				}
//			} else {
//				//单选项
//				sb.append("0");
//				sb.append("|");
//				sb.append(model.dishID);
//				sb.append("x");
//				sb.append(model.quantity);
//			}
//			if (i == Cart.getInstance().getAllItems().size() - 1) {
//				//如果是最后一个了，就添加末尾的标识 "e"
//				sb.append("e");
//			} else {
//				//如果还没到最后一个，就添加连接标识
//				sb.append("a");
//			}
//		}
//		String qrString = sb.toString();
//		return qrString;
//	}
//
//
//	public static String getDishList() {
//
//		StringBuilder sb = new StringBuilder();
//
//		//// TODO: 2017/5/10 anch  拼接二维码的内容
//		for (int i = 0; i < Cart.getInstance().getAllItems().size(); i++) {
//
//			DishModel model = Cart.getInstance().getAllItems().get(i);
//			if (i == 0) {
//				sb.append("用户点了如下商品:" + "\n");
//			}
//			//套餐
//			if (model.isPackage()) {
//				sb.append("套餐 :");
//				sb.append(model.dishName);
//				sb.append(" * ");
//				sb.append(model.quantity + "份" + "\n");
//				sb.append("套餐的小菜:" + "\n");
//
//				if (model.subItemList != null) {
//					for (int j = 0; j < model.subItemList.size(); j++) {
//						PackageBean aPackageBean = model.subItemList.get(j);
//						if (j == 0) {
//							sb.append("[");
//						}
//						sb.append(aPackageBean.dishName);
//						sb.append(" * ");
//						sb.append(aPackageBean.quantity);
//						if (j == model.subItemList.size() - 1) {
//							sb.append("]");
//						} else {
//							sb.append("  ，  ");
//						}
//					}
//				}
//
//				//选择项
//			} else if (model.haveOption()) {
//				sb.append("单品");
//				sb.append(model.dishName);
//				sb.append(" * ");
//				sb.append(model.quantity);
//				sb.append("单品的定制项有:");
//				if (model.optionList != null) {
//					for (int j = 0; j < model.optionList.size(); j++) {
//						OptionBean option = model.optionList.get(j);
//						if (j == 0) {
//							sb.append("(");
//						}
//						sb.append(option.optionName);
//						if (j == model.optionList.size() - 1) {
//							sb.append(")");
//						} else {
//							sb.append("  ,  ");
//						}
//					}
//				}
//			} else {
//				//单选项
//				sb.append("单品 :");
//				sb.append(model.dishName);
//				sb.append(" * ");
//				sb.append(model.quantity);
//			}
//			if (i == Cart.getInstance().getAllItems().size() - 1) {
//				//如果是最后一个了，就添加末尾的标识 "e"
//				sb.append(" ");
//			} else {
//				//如果还没到最后一个，就添加连接标识
//				sb.append("\n");
//			}
//		}
//		String qrString = sb.toString();
//		return qrString;
//	}
//
//	public static String getMemberPayInfo(PrintModel orderReq) {
//		StringBuilder          sb    = new StringBuilder();
//		List<OrderReq.Payment> list1 = orderReq.getPaymentList();
//		BigDecimal             price = new BigDecimal("0");
//		for (OrderReq.Payment payment : list1) {
//			if (payment.paymentTypeId.equals("3") || payment.paymentTypeId
//					.equals("4") || payment.paymentTypeId.equals("5")) {
//				price = PriceUtil.add(price, new BigDecimal(payment.value));
//			}
//		}
//		WshAccount account = orderReq.getWshAccount();
//
//		String phone = account.getPhone();
//		if (!TextUtils.isEmpty(phone) && phone.length() == 11) {
//			String char1 = phone.substring(0, 3);
//			String char2 = phone.substring(7, 11);
//			phone = char1 + "****" + char2;
//		} else {
//			phone = "";
//		}
//		//		for (int i = 0; i < phone.length(); i++) {
//		//			if (i == 3 || i == 4 || i == 5 || i == 6) {
//		//				phone = phone.replace(phone.charAt(i), '*');
//		//			}
//		//		}
//
//		String name = account.getName();
//		if (!TextUtils.isEmpty(phone)) {
//			for (int i = 0; i < name.length(); i++) {
//				if (i != 0) {
//					name = name.replace(name.charAt(i), '*');
//				}
//			}
//		} else {
//			name = "";
//		}
//		sb.append("会员卡号: " + account.getUno() + "(" + phone + ")" + "\n");
//		sb.append("会员姓名: " + name + "\n");
//		sb.append("卡 等 级: " + account.getGradeName() + "\n");
//		if (price.floatValue() != 0)
//			sb.append("消费金额: " + PriceUtil.formatPrice(price.toString()) + " 元" + "\n");
//		sb.append("(如有获赠积分卡券等，此与会员消费规则有关，详情咨询门店)" + "\n");
//		if (account != null) {
//		}
//		return sb.toString();
//	}
//
//	public static void printTicket(Context context, PrintModel orderReq) {
//		initdotLint();
//
//		//		FileLog.log(Common.Log, PrintManager.class, "printTicket", "print", "start-print");
//		//		 printPictrue(PrintUtil.getLogo(context));
//		List<DishModel> printList = orderReq.getDishList();
//
//		//店名
//		printText("\n");
//		printText("\n");
//		printText("\n");
//		size1();
//		bold();
//		center();
//		if (!orderReq.isOrderIsCorrect()) {
//			printlnText("***订单已支付,同步POS失败，请将该小票交给服务员处理！***" + "\n");
//		}
//		printlnText(PrintUtil.getWelcom() + "\n");
//		//桌牌号和堂食外带
//		size1();
//		left();
//		normal();
//		if (!orderReq.callId.contains("餐")) {
//			printlnText(PrintUtil.getTitle());//取餐号
//			//			取餐号:
//			printlnText(context.getResources()
//					.getString(R.string.cardnum_tv) + orderReq.callId + "        " + orderReq.isTakeOutStr);
//		} else {
//			printlnText(orderReq.callId + "        " + orderReq.isTakeOutStr);//桌牌号
//		}
//		//订单号，时间，收银员
//		printlnText(" ");
//		normal();
//		left();
//		size0();
//		printlnText(context.getResources()
//				.getString(R.string.order_num_tv) + orderReq.oid);//订单号 堂食or外带
//		printlnText(orderReq.stime);
//		printlnText(PrintUtil.getCashier());//收银员
//
//		left();
//		printHalfDotLine();
//		//菜品 + 价格
//		if (printList != null && printList
//				.size() > 0) {
//			bold();
//			printDishHeader();
//			normal();
//			printText((PrintUtil.getDishItemsString(printList
//			)));
//			left();
//			printHalfDotLine();
//			printText(PrintUtil.getCostInfoStr(printList, orderReq));
//			left();
//			printHalfDotLine();
//		}
//
//		//支付方式
//		bold();
//		printFormatText("支付方式" + "\n", 0);
//		normal();
//		printText(PrintUtil.getPayInfo(orderReq));
//		printHalfDotLine();
//		if (Cart.isMember) {
//			bold();
//			left();
//			printFormatText("会员消费详情" + "\n", 0);
//			normal();
//			printText(PrintUtil.getMemberPayInfo(orderReq));
//			printHalfDotLine();
//		}
//		center();
//		if (orderReq.isPrintQr()) {
//			//0 表示订单号     1 表示菜单的密文
//			printlnText("\n");
//			printQr(orderReq.id);
//		}
//		if (Common.SHOP_INFO.printInvoiceQrcode) {
//			printlnText("\n");
//			printQr(getBarcodeUrl(orderReq.oid));
//		}
//
//		printlnText(PrintUtil.getWelcomFoot());
//		printlnText(PrintUtil.getAddress());
//		printlnText(" ");
//		printlnText(" ");
//		printlnText(" ");
//		//		FileLog.log(Common.Log, PrintManager.class, "printTicket", "print", "end-print");
//		cut();
//	}
//	private static String getBarcodeUrl(String orderId) {
//		String mob       = "mobilereport/invoice.html?";
//		String storeInfo = "appId=" + LoginReqModel.appid + "&brandId=" + LoginReqModel.brandid + "&storeId=" + LoginReqModel.storeid + "&orderId=" + orderId;
//		String url       = NewPos.BASE_URL_ZHKC + mob + storeInfo;
//		return url;
//	}
//	/**
//	 * 下单失败之后，打印补打订单
//	 *
//	 * @param orderInfo
//	 */
//	public static void printSupplyTicket(PrintModel orderInfo) {
//		List<DishModel> printList;
//		//		if (Cart.getInstance().getOderItemList() != null && Cart.getInstance().getOderItemList()
//		//				.size() > 0) {
//		//			printList = Cart.getInstance().getOderItemList();
//		//		} else {
//		printList = Cart.getInstance().getAllItems();
//		//		}
//		size1();
//		bold();
//		center();
//		printlnText(PrintUtil.getWelcom() + "\n");
//		normal();
//		left();
//		size0();
//		printText("下单时间:" + format.format(new Date()) + "\n");
//		String str = Cart.takeout ? "外带" : "堂食";
//		printText("就餐方式:" + str + "\n");
//
//		printHalfDotLine();
//
//
//		//菜品 + 价格
//		if (printList != null && printList
//				.size() > 0) {
//			bold();
//			printDishHeader();
//			normal();
//			printText((PrintUtil.getDishItemsString(printList
//			)));
//			printHalfDotLine();
//			printText(PrintUtil.getCostInfoStr(printList, orderInfo));
//			printHalfDotLine();
//		}
//		size1();
//		bold();
//		center();
//		printText("注释: 异常订单，请联系工作人员\n");
//		cut();
//	}
//
//	public static void printChikenTicket(Context context, PrintModel printModel) {
//		//		FileLog.log(Common.Log, PrintManager.class, "printChikenTicket", "print", "init-print");
//		PA_Order paOrder = createPrintOrderData(context, printModel);
//		if (paOrder != null) {
//			PosKitchenPrintAdapter.getInstance(context).print(paOrder);
//		} else {
//			FileLog.log(Common.Error, PrintManager.class, "printChikenTicket", "print", "paOrder==null");
//		}
//	}
//
//	private static PA_Order createPrintOrderData(Context context, PrintModel printModel) {
//		if (printModel == null)
//			return null;
//		// 输入桌台的模式，把取餐号，tablename都设置成输入的桌台号码
//		String tmpStr    = printModel.callId;
//		String tableName = tmpStr;
//		if (SharedPreferencesUtil.ifShowTable(context) == 1) {
//			if (tmpStr.indexOf(":") > 0)
//				tableName = tmpStr.substring(tmpStr.indexOf(":") + 1, tmpStr.length());
//
//			printModel.setTableNames(tableName);
//			printModel.setCallNumber(tableName);
//		} else {
//			printModel.setTableNames(tableName);
//		}
//
//		List<DishModel> dishItems = printModel.getDishList();
//		if (dishItems == null || dishItems.isEmpty())
//			return null;
//
//		// 创建相应的菜品列表
//		List<PA_OrderItem> itemList = new ArrayList<PA_OrderItem>();
//		for (DishModel d : dishItems)
//			itemList.add(createPrintDishItem(d, printModel));
//
//		PA_Order po = new PA_Order();
//		po.setId(printModel.id);
//		po.setComment(printModel.getComment());
//		po.setTotal(printModel.total);
//		po.setCost(Cart.getInstance().getSumPrice()
//				.toString());
//		po.setPrintSumTiketQrCode(Common.SHOP_INFO.printKitchenQrcode);
//		//		if (printModel.isTakeOut == 0)
//		//			po.setOrderType("EAT_IN");
//		if (printModel.isTakeOut == 0)
//			po.setOrderType("EAT_IN");
//		else
//			po.setOrderType("EAT_OUT");
//		po.setCallNumber(printModel.callId);
//		po.setTableNames(printModel.getTableNames());
//		po.setCreatedAtStr(formatStime(printModel.stime));
//		po.setItemList(itemList);
//		FileLog.log(Common.Log, PrintManager.class, "printChikenTicket", "print", "paOrder!=null");
//		return po;
//	}
//
//	private static PA_OrderItem createPrintDishItem(DishModel d, PrintModel printModel) {
//		if (d == null)
//			return null;
//
//		PA_OrderItem item = new PA_OrderItem();
//		item.setOrderId(printModel.id);
//		item.setDishId(Long.parseLong(d.dishID));
//		item.setDishName(d.dishName);
//		item.setPrice(new BigDecimal(d.price));
//		item.setCost(new BigDecimal(d.cost));
//		item.setQuantity(d.quantity);
//		item.setDishKind(d.dishKind);
//		item.setIsPackage(d.isPackage);
//		item.setDishUnit(d.dishUnit);
//
//		if (d.isPackage()) {
//			// 是套餐，那么需要把套餐里面的套餐项目构建出来
//			ArrayList<PackageBean>  packageBeanItems = d.subItemList;
//			ArrayList<PA_OrderItem> subItemList      = new ArrayList<PA_OrderItem>();
//			if (packageBeanItems != null) {
//				for (PackageBean p : packageBeanItems) {
//					subItemList.add(createSubOrderItem(p, d.dishName, printModel.id));
//				}
//
//			}
//			item.setSubItemList(subItemList);
//		} else
//			item.setPackName(null);
//
//		if (!d.isPackage()) {
//			// 定制项的信息
//			ArrayList<OptionBean> optionList = d.optionList;
//			if (optionList != null) {
//				ArrayList<PA_Option> oplist = new ArrayList<PA_Option>();
//				for (OptionBean o : optionList) {
//					PA_Option po = new PA_Option();
//					po.setName(o.optionName);
//					DecimalFormat df = new DecimalFormat("#.00");
//					po.setPrice(new BigDecimal(df.format(o.price)));
//					oplist.add(po);
//				}
//				item.setOptionList(oplist);
//			}
//		}
//
//		return item;
//	}
//
//	private static PA_OrderItem createSubOrderItem(PackageBean p, String packageName, String orderid) {
//		PA_OrderItem item = new PA_OrderItem();
//		item.setDishName(p.dishName);
//		item.setDishId(Long.parseLong(p.dishID));
//		item.setOrderId(orderid);
//		if (p.price != null && p.price.length() > 0)
//			item.setPrice(new BigDecimal(p.price));
//		if (p.cost != null && p.cost.length() > 0)
//			item.setCost(new BigDecimal(p.cost));
//		item.setQuantity(p.quantity);
//		item.setDishUnit(p.unit);
//		item.setIsPackage(p.isPackage);
//		item.setPackName(packageName);
//
//
//		if (p.optionList != null) {
//			ArrayList<PA_Option> oplist = new ArrayList<PA_Option>();
//			for (OptionBean o : p.optionList) {
//				PA_Option po = new PA_Option();
//				po.setName(o.optionName);
//				DecimalFormat df = new DecimalFormat("#.00");
//				po.setPrice(new BigDecimal(df.format(o.price)));
//				oplist.add(po);
//			}
//			item.setOptionList(oplist);
//		}
//
//
//		return item;
//	}
//
//	public static PA_Order initPrintOrderData(PrintModel model) {
//		PA_Order        po        = new PA_Order();
//		List<DishModel> dishItems = model.getDishList();
//		if (dishItems == null || dishItems.isEmpty())
//			return null;
//
//		// 创建相应的菜品列表
//		List<PA_OrderItem> itemList = new ArrayList<>();
//		for (DishModel d : dishItems)
//			itemList.add(createPrintDishItem(d, model));
//		po.setItemList(itemList);
//		return po;
//	}
//}
