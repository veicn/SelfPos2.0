package com.acewill.slefpos.print.ticketprint;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.orderbean.Payment;
import com.acewill.slefpos.bean.syncbean.Discount;
import com.acewill.slefpos.bean.syncbean.DiscountAmount;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptReq;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.printer.Alignment;
import com.acewill.slefpos.printer.TextRow;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.jaydenxiao.common.commonutils.SPUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmarantPrintUtil {
	public static String dotLine = "";
	//	public static String dotLineHalf = "    ----------------------------------------    \n";
	public static int    paperWidth;// 48 42
	// 店名 大小 位置
	public static String SHOP_NAME;
	// 桌号 大小 位置
	public static String TABLE_NO;
	// 餐段
	// 单号 大小 位置
	public static String ORDER_NO;
	// 设备号
	public static String POS_ID;

	public static Bitmap LOGO;

	public static String TIPS = "比格北京望承公园店";
	// public static String TIPS = "请等待服务员叫号";

	//	private static String spaceNumPrice = "";

	//	private static String nameHeader     = "菜品";
	//	private static String quantityHeader = "数量";
	//	private static String amountHeader   = "金额";


	private static List<String> charArr = new ArrayList<>();

	static {
		charArr.add("(");
		charArr.add(")");
		charArr.add("[");
		charArr.add("]");
		charArr.add("a");
		charArr.add("b");
		charArr.add("c");
		charArr.add("d");
		charArr.add("e");
		charArr.add("f");
		charArr.add("g");
		charArr.add("h");
		charArr.add("i");
		charArr.add("j");
		charArr.add("k");
		charArr.add("l");
		charArr.add("m");
		charArr.add("n");
		charArr.add("o");
		charArr.add("p");
		charArr.add("q");
		charArr.add("r");
		charArr.add("s");
		charArr.add("t");
		charArr.add("u");
		charArr.add("v");
		charArr.add("w");
		charArr.add("x");
		charArr.add("y");
		charArr.add("z");
		charArr.add("0");
		charArr.add("1");
		charArr.add("2");
		charArr.add("3");
		charArr.add("4");
		charArr.add("5");
		charArr.add("6");
		charArr.add("7");
		charArr.add("8");
		charArr.add("9");
		charArr.add(" ");
		charArr.add("%");
		charArr.add("-");
		charArr.add("+");
	}


	public static String getTitle() {
		return "请留意取餐区叫号屏";
	}

	public static String getTableNo(String tableNo) {
		return "桌牌号: " + tableNo;
	}

	public static String getTime(String stime) {
		Date date = null;
		if (TextUtils.isEmpty(stime)) {
			date = new Date();
		} else {
		}
		String time = new SimpleDateFormat("yyyy/MM/dd/  HH:mm:ss")
				.format(date);
		return time;
	}

	public static String getSyncDishItemsString() {
		StringBuilder sb = new StringBuilder();
		for (CartDish dishModel : Cart.getInstance().getCartDishes()) {
			//第一行  名称       数量         价格
			int spaceNum = 0;
			if (UIDataProvider.isDishIsPackage(dishModel.getDishID())) {
				dishModel.setDishName("(" + dishModel.getDishName() + ")");
			}
			if ("0".equals(dishModel.getCost())) {
				dishModel.setDishName("(赠)" + dishModel.getDishName());
			}
			sb.append(dishModel.getDishName());

			int headNameLength = calculateHeadNameLength(dishModel.getDishName());
			spaceNum = Common.leftSpace - headNameLength;
			int gap = 0;
			if (spaceNum > 0) {
				for (int i = 0; i < spaceNum + 1; i++) {//数量的数字往后移了一位
					sb.append(" ");
				}
			} else {
				sb.append("  ");
				gap = Math.abs(spaceNum) + 2;//文字过长的情况，这是计算文字过长的部分和数量数字的部分， 后面计算的时候减去就行了
			}

			sb.append(dishModel.getQuantity());
			if (gap == 0) {
				spaceNum = Common.rightSpace - Common.leftSpace - String
						.valueOf(dishModel.getQuantity())
						.length() - 1;//减1 是因为数量的数字往后移了一位
			} else {
				spaceNum = Common.rightSpace - Common.leftSpace - String
						.valueOf(dishModel.getQuantity())
						.length() - gap;
			}

			for (int i = 0; i < spaceNum; i++) {
				sb.append(" ");
			}
			sb.append(formatPriceString(PriceUtil.formatPrice(dishModel.getPrice())));
			sb.append("\n");

			//第二行    套餐的每一个单项

			if (dishModel.getSubItemList() != null) {//打印套餐
				for (UIPackageOptionItem packageBean : dishModel.getSubItemList()) {
					//					sb.append("[套]");
					//					sb.append(packageBean.getDishName());
					//					int headNameLength2 = calculateHeadNameLength(packageBean.getDishName());
					//					int spaceNum2       = Common.leftSpace - headNameLength2;
					//					for (int i = 0; i < spaceNum2 - 3; i++) {
					//						sb.append(" ");
					//					}
					//					sb.append(packageBean.getQuantity());
					//					sb.append("\n");
					sb.append(appendThreeParam("[套]" + packageBean.getDishName(), packageBean
							.getQuantity(), packageBean.getPrice()));
					if (packageBean.getOptionList() != null) {
						sb.append("    ");
						for (int i = 0; i < packageBean.getOptionList().size(); i++) {
							UITasteOption bean = packageBean.getOptionList().get(i);
							if (i != packageBean.getOptionList().size() - 1) {
								if (bean.getPrice() != 0) {
									if (!bean.getSourceType().equals("M")) {
										sb.append(bean.getOptionName() + "(￥" + PriceUtil
												.formatPrice(String
														.valueOf(bean.getPrice())) + ")、");
									}

								} else {
									if (!bean.getSourceType().equals("M")) {
										sb.append(bean.getOptionName() + "、");
									}
								}
							} else {
								if (bean.getPrice() != 0) {
									if (!bean.getSourceType().equals("M")) {
										sb.append(bean.getOptionName() + "(￥" + PriceUtil
												.formatPrice(String
														.valueOf(bean.getPrice())) + ")");
									}
								} else {
									if (!bean.getSourceType().equals("M")) {
										sb.append(bean.getOptionName());
									}
								}
							}
						}
						sb.append("\n");
					}


					if (packageBean.getOptionList() != null) {
						for (int i = 0; i < packageBean.getOptionList().size(); i++) {
							UITasteOption bean = packageBean.getOptionList().get(i);
							if (bean.getSourceType().equals("M"))
								sb.append(appendThreeParam("    +" + bean.getOptionName(), bean
										.getQuantity(), String.valueOf(bean.getPrice())));
						}
					}
				}
			}
			if (dishModel.getOptionList() != null) {
				sb.append("    ");
				for (int i = 0; i < dishModel.getOptionList().size(); i++) {
					UITasteOption bean = dishModel.getOptionList().get(i);
					if (i != dishModel.getOptionList().size() - 1) {
						if (bean.getPrice() != 0) {
							if (!bean.getSourceType().equals("M")) {
								sb.append(bean.getOptionName() + "(￥" + PriceUtil
										.formatPrice(String
												.valueOf(bean.getPrice())) + ")、");
							}

						} else {
							if (!bean.getSourceType().equals("M")) {
								sb.append(bean.getOptionName() + "、");
							}
						}
					} else {
						if (bean.getPrice() != 0) {
							if (!bean.getSourceType().equals("M")) {
								sb.append(bean.getOptionName() + "(￥" + PriceUtil
										.formatPrice(String
												.valueOf(bean.getPrice())) + ")");
							}
						} else {
							if (!bean.getSourceType().equals("M")) {
								sb.append(bean.getOptionName());
							}
						}
					}
				}
				sb.append("\n");
			}

			if (dishModel.getOptionList() != null) {//打印可选项
				for (int i = 0; i < dishModel.getOptionList().size(); i++) {
					UITasteOption bean = dishModel.getOptionList().get(i);
					if (bean.getSourceType().equals("M"))
						sb.append(appendThreeParam("    +" + bean.getOptionName(), bean
								.getQuantity(), String.valueOf(bean.getPrice())));
				}
			}
		}
		return sb.toString();
	}

	public static String getDishItemsString() {
		StringBuilder sb = new StringBuilder();
		for (CartDish dishModel : Cart.getInstance().getCartDishes()) {
			//第一行  名称       数量         价格
			int spaceNum = 0;
			if (UIDataProvider.isDishIsPackage(dishModel.getDishID())) {
				dishModel.setDishName("(" + dishModel.getDishName() + ")");
			}
			if ("0".equals(dishModel.getCost())) {
				dishModel.setDishName("(赠)" + dishModel.getDishName());
				;
			}
			sb.append(dishModel.getDishName());

			int headNameLength = calculateHeadNameLength(dishModel.getDishName());
			spaceNum = Common.leftSpace - headNameLength;
			int gap = 0;
			if (spaceNum > 0) {
				for (int i = 0; i < spaceNum + 1; i++) {//数量的数字往后移了一位
					sb.append(" ");
				}
			} else {
				sb.append("  ");
				gap = Math.abs(spaceNum) + 2;//文字过长的情况，这是计算文字过长的部分和数量数字的部分， 后面计算的时候减去就行了
			}

			sb.append(dishModel.getQuantity());
			if (gap == 0) {
				spaceNum = Common.rightSpace - Common.leftSpace - String
						.valueOf(dishModel.getQuantity())
						.length() - 1;//减1 是因为数量的数字往后移了一位
			} else {
				spaceNum = Common.rightSpace - Common.leftSpace - String
						.valueOf(dishModel.getQuantity())
						.length() - gap;
			}

			for (int i = 0; i < spaceNum; i++) {
				sb.append(" ");
			}
			sb.append(formatPriceString(PriceUtil
					.formatPrice(Order.getInstance().isMember() ? dishModel
							.getMemberPrice() : dishModel.getPrice())));
			sb.append("\n");

			//第二行    套餐的每一个单项

			if (dishModel.getSubItemList() != null) {//打印套餐
				for (UIPackageOptionItem packageBean : dishModel.getSubItemList()) {
					sb.append("[套]");
					sb.append(packageBean.getDishName() + (packageBean
							.getExtraCost() == 0 ? "" : "[套餐加价+￥" + packageBean
							.getExtraCost() + "]"));
					int headNameLength2 = calculateHeadNameLength(packageBean.getDishName());
					int spaceNum2       = Common.leftSpace - headNameLength2;
					for (int i = 0; i < spaceNum2 - 3; i++) {
						sb.append(" ");
					}
					sb.append(packageBean.getQuantity());
					sb.append("\n");
					if (packageBean.getOptionList() != null) {
						sb.append("    ");
						for (int i = 0; i < packageBean.getOptionList().size(); i++) {
							UITasteOption bean = packageBean.getOptionList().get(i);
							if (i != packageBean.getOptionList().size() - 1) {
								if (bean.getPrice() != 0) {
									if (!bean.getSourceType().equals("M")) {
										sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
												.formatPrice(String
														.valueOf(bean.getPrice())) + ")、");
									} else {
										sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
												.formatPrice(String
														.valueOf(bean.getPrice())) + ") x " + bean
												.getQuantity() + "、");
									}

								} else {
									if (!bean.getSourceType().equals("M")) {
										sb.append("+" + bean.getOptionName() + "、");
									} else {
										sb.append("+" + bean.getOptionName() + " x " + bean
												.getQuantity() + "、");
									}
								}
							} else {
								if (bean.getPrice() != 0) {
									if (!bean.getSourceType().equals("M")) {
										sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
												.formatPrice(String
														.valueOf(bean.getPrice())) + ")");
									} else {
										sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
												.formatPrice(String
														.valueOf(bean.getPrice())) + ") x " + bean
												.getQuantity());
									}
								} else {
									if (!bean.getSourceType().equals("M")) {
										sb.append("+" + bean.getOptionName());
									} else {
										sb.append("+" + bean.getOptionName() + " x " + bean
												.getQuantity());
									}
								}
							}
						}
						sb.append("\n");
					}
				}
			}

			if (dishModel.getOptionList() != null) {//打印可选项
				sb.append("    ");
				for (int i = 0; i < dishModel.getOptionList().size(); i++) {
					UITasteOption bean = dishModel.getOptionList().get(i);

					if (i != dishModel.getOptionList().size() - 1) {
						if (bean.getPrice() != 0) {
							if (!bean.getSourceType().equals("M")) {
								sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
										.formatPrice(String.valueOf(bean.getPrice())) + ")、");
							} else {
								sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
										.formatPrice(String
												.valueOf(bean.getPrice())) + ") x " + bean
										.getQuantity() + "、");
							}
						} else {
							if (bean.getSourceType() != null && !bean.getSourceType().equals("M")) {
								sb.append("+" + bean.getOptionName() + "、");
							} else {
								sb.append("+" + bean.getOptionName() + " x " + bean
										.getQuantity() + "、");
							}
						}
					} else {
						if (bean.getPrice() != 0) {
							if (!bean.getSourceType().equals("M")) {
								sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
										.formatPrice(String.valueOf(bean.getPrice())) + ")");
							} else {
								sb.append("+" + bean.getOptionName() + "(￥" + PriceUtil
										.formatPrice(String
												.valueOf(bean.getPrice())) + ") x " + bean
										.getQuantity());
							}
						} else {
							if (bean.getSourceType() != null && !bean.getSourceType().equals("M")) {
								sb.append("+" + bean.getOptionName());
							} else {
								sb.append("+" + bean.getOptionName() + " x " + bean
										.getQuantity());
							}
						}
					}
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private static String formatPriceString(String price) {//让价格右对齐
		StringBuilder sb          = new StringBuilder();
		int           totalSpace  = 7;
		int           priceLength = price.length();
		int           length      = totalSpace - priceLength;
		for (int i = 0; i < length; i++) {
			sb.append(" ");
		}
		sb.append(price);

		return sb.toString();
	}


	private static int calculateHeadNameLength(String headName) {
		int    size  = headName.length() * 2;
		char[] chars = headName.toCharArray();
		for (char a : chars) {
			if (charArr.contains(String.valueOf(a)))
				size = size - 1;
		}
		return size;
	}

	public static int getQuantity(List<CartDish> dishs) {
		int count = 0;
		for (int i = 0; i < dishs.size(); i++) {
			count += dishs.get(i).getQuantity();
		}
		return count;
	}

	public static String appendThreeParam(String headName, int quantity, String price) {
		StringBuilder sb = new StringBuilder();
		sb.append(headName);
		int spaceNum       = 0;
		int headNameLength = calculateHeadNameLength(headName);
		spaceNum = Common.leftSpace - headNameLength;
		int gap = 0;
		if (spaceNum > 0) {
			for (int i = 0; i < spaceNum + 1; i++) {
				sb.append(" ");
			}
		} else {
			sb.append("  ");
			gap = Math.abs(spaceNum) + 4;//文字过长的情况，这是计算文字过长的部分和数量数字的部分， 后面计算的时候减去就行了
		}

		sb.append(quantity);
		if (gap == 0) {
			spaceNum = Common.rightSpace - Common.leftSpace - String
					.valueOf(quantity)
					.length() - 1;
		} else {
			spaceNum = Common.rightSpace - Common.leftSpace - String
					.valueOf(quantity)
					.length() - gap;
		}

		for (int i = 0; i < spaceNum; i++) {
			sb.append(" ");
		}
		sb.append(formatPriceString(PriceUtil.formatPrice(price)));
		sb.append("\n");

		return sb.toString();
	}

	public static String appendTwoParam(String headName, String price) {
		StringBuilder sb       = new StringBuilder();
		int           spaceNum = 0;
		sb.append(headName);
		int i1 = calculateHeadNameLength(headName);
		spaceNum = Common.rightSpace - i1;
		for (int i = 0; i < spaceNum; i++) {
			sb.append(" ");
		}
		sb.append(formatPriceString(price) + "\n");
		return sb.toString();
	}

	public static String getCostInfoStr() {
		StringBuilder sb = new StringBuilder();
		sb.append(appendThreeParam("应收", getQuantity(Cart.getInstance().getCartDishes()), String
				.valueOf(Price.getInstance().getDishTotalWithMix())));
		//		if (order.packingCost != null) {
		//			sb.append(appendTwoParam("打包盒", PriceUtil.formatPrice(order.packingCost)));
		//		}

		if (Price.getInstance().getActualCost() != null && PriceUtil
				.subtract(Price.getInstance().getDishTotalWithMix(), Price.getInstance()
						.getActualCost()).floatValue() != 0) {
			sb.append(appendTwoParam("优惠","-"+ PriceUtil
					.subtract(Price.getInstance().getDishTotalWithMix(), Price.getInstance()
							.getActualCost()).toString()));
		}
		if (Price.getInstance().getActualCost() != null && Float
				.parseFloat(Price.getInstance().getActualCost()) != 0) {
			sb.append(appendTwoParam("实收", String
					.valueOf(Price.getInstance().getActualCost())));
		} else {
			sb.append(appendTwoParam("实收", String
					.valueOf(Price.getInstance().getDishTotalWithMix())));
		}

		return sb.toString();
	}

	public static String getSyncCostInfoStr() {
		StringBuilder sb = new StringBuilder();
		sb.append(appendThreeParam("合计", getQuantity(Cart.getInstance().getCartDishes()), String
				.valueOf(Price.getInstance().getDishTotalWithMix())));
		for (int i = Discount.getInstance().getDiscountAmountList().size() - 1; i >= 0; i--) {
			DiscountAmount amount = Discount.getInstance().getDiscountAmountList().get(i);
			sb.append(appendTwoParam(amount.getDiscountAmountName(), "-" + String.valueOf(amount
					.getDiscountAmount())));
		}
		if (Price.getInstance()
				.getPointValue() != 0)
			sb.append(appendTwoParam("积分抵扣", "-" + Price.getInstance()
					.getPointValue()));

		if (Price.getInstance()
				.getCouponValue() != 0)
			sb.append(appendTwoParam(Price.getInstance().getSyncCoupon()
					.getCouponName(), "-" + Price.getInstance()
					.getCouponValue()));
		sb.append(appendTwoParam("小计", "" + Float
				.parseFloat(Order.getInstance().getPaymentAmout())));
		sb.append(appendTwoParam("应收金额", "" + Float
				.parseFloat(Order.getInstance().getPaymentAmout())));
		return sb.toString();
	}

	public static String getOrderNo(String orderNo) {
		return "订单号\n" + orderNo + "\n";
	}

	public static String getFooterString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	public static String getPaymentString() {
		StringBuilder sb = new StringBuilder();
		sb.append("持卡人存根   请妥善保管\n");
		sb.append(dotLine);
		sb.append("商户名称：\n");
		sb.append("商户号：\n");
		sb.append("终端号：\n");
		sb.append("操作员：\n");
		sb.append("\n\n");
		sb.append("本人确认以上交易，同意将其计入本卡账户");
		return sb.toString();
	}

	private static int calculateSpaceNum(String dishName) {
		int total = 0;

		for (int i = 0; i < dishName.length(); i++) {
			if (isChinese(dishName.charAt(i))) {
				total += 2;
			} else {
				total += 1;
			}
		}

		return total;
	}

	public static boolean isChinese(char a) {
		int v = (int) a;
		return (v >= 19968 && v <= 171941);
	}

	//	public static String getShopInfo() {
	//		return "电话:" + Common.SHOP_INFO.telNo + "\n" + "地址:"
	//				+ Common.SHOP_INFO.shopAddress + "\n";
	//	}

	public static String getPayInfo() {
		StringBuilder sb = new StringBuilder();
		//		sb.append("支付方式 :" + "\n");
		if (Order.getInstance().getPaymentList() == null)
			return "";
		for (int i = 0; i < Order.getInstance().getPaymentList().size(); i++) {
			Payment payment            = Order.getInstance().getPaymentList().get(i);
			String  paymentTypeNameStr = payment.getPaymentTypeName();
			sb.append(appendTwoParam(paymentTypeNameStr, PriceUtil
					.formatPrice(payment.getValue())));
		}
		return sb.toString();
	}

	public static String getSyncPayInfo() {
		StringBuilder sb = new StringBuilder();
		//		sb.append("支付方式 :" + "\n");
		if (Order.getInstance().getSyncPaymentList() == null)
			return "";
		for (int i = 0; i < Order.getInstance().getSyncPaymentList().size(); i++) {
			SyncAcceptReq.DataBean.PaymentItemBean bean = Order.getInstance().getSyncPaymentList()
					.get(i);
			if (bean.getPayMode().equals("M")) {
				sb.append(appendTwoParam("会员储值支付", String.valueOf(bean.getTxAmount())));
			} else if (bean.getPayMode().equals("A")) {
				sb.append(appendTwoParam("支付宝支付", String.valueOf(bean.getTxAmount())));
			} else if (bean.getPayMode().equals("W")) {
				sb.append(appendTwoParam("微信支付", String.valueOf(bean.getTxAmount())));
			}
		}
		return sb.toString();
	}

	public static String getWelcom() {
		if (SystemConfig.isSmarantSystem)
			return StoreConfigure.getSname();
		else if (SystemConfig.isSyncSystem)
			return SPUtils.getSharedStringData(BaseApplication.getContext(), "feStoreshopName");
		else
			return "";
	}

	public static String getWelcomFoot() {
		if (SystemConfig.isSmarantSystem)
			return "欢迎下次光临!\n联系电话: " + StoreConfigure.getPhone();
		else
			return "欢迎下次光临!\n联系电话: " + SyncDataProvider.getSyncShopTel(SPUtils
					.getSharedStringData(BaseApplication.getContext(), "shopNo"));
	}

	public static String getAddress() {
		if (SystemConfig.isSmarantSystem)
			return "地址:" + StoreConfigure.getAddress();
		else
			return "地址:" + SyncDataProvider.getSyncShopAddress(SPUtils
					.getSharedStringData(BaseApplication.getContext(), "shopNo"));
	}

	public static String getCashier() {
		return "收银员: " + TerminalConfigure.getTname();
	}

	private static void addRow(ArrayList<TextRow> arrayList, boolean bold, int size, int align, String content,
	                           boolean revertMode) {
		arrayList.add(createRow(bold, size, align, content, revertMode));
	}

	private static TextRow createRow(boolean bold, int size, int align, String content,
	                                 boolean revertMode) {
		TextRow title = new TextRow(content);
		title.setScaleWidth(size);
		title.setScaleHeight(size);
		title.setBoldFont(bold);
		title.setRevertMode(revertMode);

		switch (align) {
			case 0:
				title.setAlign(Alignment.LEFT);
				break;
			case 1:
				title.setAlign(Alignment.CENTER);
				break;
			case 2:
				title.setAlign(Alignment.RIGHT);
				break;

			default:
				break;
		}
		return title;
	}


	public static String getQrString() {


		StringBuilder sb = new StringBuilder();

		//// TODO: 2017/5/10 anch  拼接二维码的内容
		for (int i = 0; i < Cart.getInstance().getCartDishes().size(); i++) {

			CartDish model = Cart.getInstance().getCartDishes().get(i);
			if (i == 0) {
				sb.append("b");
			}
			//套餐
			if (UIDataProvider.isDishIsPackage(model.getDishID())) {
				sb.append("2");
				sb.append("|");
				sb.append(model.getDishID());
				sb.append("x");
				sb.append(model.getQuantity());
				sb.append("c");

				if (model.getSubItemList() != null) {
					for (int j = 0; j < model.getSubItemList().size(); j++) {
						UIPackageOptionItem aPackageBean = model.getSubItemList().get(j);
						if (j == 0) {
							sb.append("[");
						}
						sb.append(aPackageBean.getDishID());
						//						sb.append("*");
						//						sb.append(aPackageBean.quantity);
						if (j == model.getSubItemList().size() - 1) {
							sb.append("]");
						} else {
							sb.append("r");
						}
					}
				}

				//选择项
			} else if (model.getOptionList() != null && model.getOptionList().size() > 0) {
				sb.append("1");
				sb.append("|");
				sb.append(model.getDishID());
				sb.append("x");
				sb.append(model.getQuantity());
				sb.append("c");
				if (model.getOptionList() != null) {
					for (int j = 0; j < model.getOptionList().size(); j++) {
						UITasteOption option = model.getOptionList().get(j);
						if (j == 0) {
							sb.append("q");
						}
						sb.append(option.getId());
						if (j == model.getOptionList().size() - 1) {
							sb.append("w");
						} else {
							sb.append("r");
						}
					}
				}
			} else {
				//单选项
				sb.append("0");
				sb.append("|");
				sb.append(model.getDishID());
				sb.append("x");
				sb.append(model.getQuantity());
			}
			if (i == Cart.getInstance().getCartDishes().size() - 1) {
				//如果是最后一个了，就添加末尾的标识 "e"
				sb.append("e");
			} else {
				//如果还没到最后一个，就添加连接标识
				sb.append("a");
			}
		}
		String qrString = sb.toString();
		return qrString;
	}


	public static String getDishList() {

		StringBuilder sb = new StringBuilder();

		//// TODO: 2017/5/10 anch  拼接二维码的内容
		for (int i = 0; i < Cart.getInstance().getCartDishes().size(); i++) {

			CartDish model = Cart.getInstance().getCartDishes().get(i);
			if (i == 0) {
				sb.append("用户点了如下商品:" + "\n");
			}
			//套餐
			if (UIDataProvider.isDishIsPackage(model.getDishID())) {
				sb.append("套餐 :");
				sb.append(model.getDishName());
				sb.append(" * ");
				sb.append(model.getQuantity() + "份" + "\n");
				sb.append("套餐的小菜:" + "\n");

				if (model.getSubItemList() != null) {
					for (int j = 0; j < model.getSubItemList().size(); j++) {
						UIPackageOptionItem aPackageBean = model.getSubItemList().get(j);
						if (j == 0) {
							sb.append("[");
						}
						sb.append(aPackageBean.getDishName());
						sb.append(" * ");
						sb.append(aPackageBean.getQuantity());
						if (j == model.getSubItemList().size() - 1) {
							sb.append("]");
						} else {
							sb.append("  ，  ");
						}
					}
				}

				//选择项
			} else if (model.getOptionList() != null && model.getOptionList().size() > 0) {
				sb.append("单品");
				sb.append(model.getDishName());
				sb.append(" * ");
				sb.append(model.getQuantity());
				sb.append("单品的定制项有:");
				if (model.getOptionList() != null) {
					for (int j = 0; j < model.getOptionList().size(); j++) {
						UITasteOption option = model.getOptionList().get(j);
						if (j == 0) {
							sb.append("(");
						}
						sb.append(option.getOptionName());
						if (j == model.getOptionList().size() - 1) {
							sb.append(")");
						} else {
							sb.append("  ,  ");
						}
					}
				}
			} else {
				//单选项
				sb.append("单品 :");
				sb.append(model.getDishName());
				sb.append(" * ");
				sb.append(model.getQuantity());
			}
			if (i == Cart.getInstance().getCartDishes().size() - 1) {
				//如果是最后一个了，就添加末尾的标识 "e"
				sb.append(" ");
			} else {
				//如果还没到最后一个，就添加连接标识
				sb.append("\n");
			}
		}
		String qrString = sb.toString();
		return qrString;
	}

	public static String getMemberPayInfo() {
		StringBuilder sb    = new StringBuilder();
		List<Payment> list1 = Order.getInstance().getPaymentList();
		BigDecimal    price = new BigDecimal("0");
		for (Payment payment : list1) {
			if (payment.getPaymentTypeId().equals("3") || payment.getPaymentTypeId()
					.equals("4") || payment.getPaymentTypeId().equals("5")) {
				price = PriceUtil.add(price, new BigDecimal(payment.getValue()));
			}
		}
		WshAccount account = WshDataProvider.getWshAccount();

		String phone = account.getPhone();
		if (!TextUtils.isEmpty(phone) && phone.length() == 11) {
			String char1 = phone.substring(0, 3);
			String char2 = phone.substring(7, 11);
			phone = char1 + "****" + char2;
		} else {
			phone = "";
		}
		//		for (int i = 0; i < phone.length(); i++) {
		//			if (i == 3 || i == 4 || i == 5 || i == 6) {
		//				phone = phone.replace(phone.charAt(i), '*');
		//			}
		//		}

		String name = account.getName();
		if (!TextUtils.isEmpty(phone)) {
			for (int i = 0; i < name.length(); i++) {
				if (i != 0) {
					name = name.replace(name.charAt(i), '*');
				}
			}
		} else {
			name = "";
		}
		sb.append("会员卡号: " + account.getUno() + "(" + phone + ")" + "\n");
		sb.append("会员姓名: " + name + "\n");
		sb.append("卡 等 级: " + account.getGradeName() + "\n");
		if (price.floatValue() != 0)
			sb.append("消费金额: " + PriceUtil.formatPrice(price.toString()) + " 元" + "\n");
		sb.append("(如有获赠积分卡券等，此与会员消费规则有关，详情咨询门店)" + "\n");
		if (account != null) {
		}
		return sb.toString();
	}
}
