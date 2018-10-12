package com.acewill.slefpos.print.chikenprintlibrary;


import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;

import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KDS;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KitchenPrintMode;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KitchenStall;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_Option;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_Order;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_OrderItem;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_StoreInfo;
import com.acewill.slefpos.print.chikenprintlibrary.printer.Alignment;
import com.acewill.slefpos.print.chikenprintlibrary.printer.BitmapRow;
import com.acewill.slefpos.print.chikenprintlibrary.printer.Column;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrintModelInfo;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrintTemplateType;
import com.acewill.slefpos.print.chikenprintlibrary.printer.Printer;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrinterFactory;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrinterInterface;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrinterLinkType;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrinterOutputType;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrinterTemplates;
import com.acewill.slefpos.print.chikenprintlibrary.printer.PrinterVendor;
import com.acewill.slefpos.print.chikenprintlibrary.printer.Separator;
import com.acewill.slefpos.print.chikenprintlibrary.printer.Table;
import com.acewill.slefpos.print.chikenprintlibrary.printer.TextRow;
import com.acewill.slefpos.print.chikenprintlibrary.printer.gpnetwork.GpEnternetPrint;
import com.acewill.slefpos.print.chikenprintlibrary.util.PA_Constant;
import com.acewill.slefpos.print.chikenprintlibrary.util.PrintUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by DHH on 2017/3/17.
 */

public class PrinterDataController {
	private int bigSize     = 2;
	private int scaleHeight = 2;
	private int small       = 1;

	/**
	 * 打印模板列表
	 */
	private List<PrinterTemplates> printerTemplatesList;

	private PA_StoreInfo storeInfo;

	public void setStoreInfo(PA_StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	/**
	 * 门店打印机列表
	 */
	private List<Printer>         printerList      = new ArrayList<>();
	/**
	 * 门店KDS列表
	 */
	private List<PA_KDS>          kdsList          = new ArrayList<>();
	/**
	 * 门店档口列表
	 */
	private List<PA_KitchenStall> kitchenStallList = new ArrayList<>();

	/**
	 * 打印模板类型映射
	 */
	private HashMap<Integer, HashMap<String, PrintModelInfo>> printModeMap = new HashMap<>();

	/**
	 * 菜品对应的厨房档口映射
	 */
	private HashMap<Long, List<PA_KitchenStall>> kitchenStallDishMap = new HashMap<>();
	/**
	 * 档口与打印机的映射
	 */
	private Map<Integer, Printer>                printerMap          = new HashMap<>();
	/**
	 * 档口与KDS的映射
	 */
	private Map<Integer, PA_KDS>                 kdsMap              = new HashMap<>();


	private boolean printQR = true;

	//    //临时存储打总单菜品的档口
	//    private HashMap<Long, List<PA_KitchenStall>> summaryKitchenStallMap = new HashMap<>();
	//    //将要在总单上打印的菜品
	//    private HashMap<Long, List<PA_OrderItem>> summaryOiPrintMap = new HashMap<>();
	//
	//    private HashMap<Long, PA_KitchenStall> summaryTempKitchenStallMap = new HashMap<>();

	private Map<Integer, Printer> getPrinterListforMap(List<Printer> printers) {
		if (printerMap == null)
			printerMap = new HashMap<Integer, Printer>();
		else
			printerMap.clear();
		if (printers != null && printers.size() > 0) {
			for (Printer printer : printers) {
				printerMap.put(printer.getId(), printer);
			}
		}
		return printerMap;
	}


	public List<PA_KDS> getKdsList() {
		return kdsList;
	}

	private Map<Integer, PA_KDS> getKdsIdforMap(List<PA_KDS> kdsList) {
		if (kdsMap == null)
			kdsMap = new HashMap<Integer, PA_KDS>();
		else
			kdsMap.clear();
		if (kdsList != null && kdsList.size() > 0) {
			for (PA_KDS kds : kdsList) {
				kdsMap.put(kds.getId(), kds);
			}
		}
		return kdsMap;
	}

	public void setPrinterList(List<Printer> printerList) {
		this.printerList = printerList;
		getPrinterListforMap(printerList);
	}

	public void setKdsList(List<PA_KDS> kdsList) {
		this.kdsList = kdsList;
		getKdsIdforMap(kdsList);
	}

	public void setKitchenStallList(List<PA_KitchenStall> kitchenStallList) {
		this.kitchenStallList = kitchenStallList;

		handleKitchenStall();
	}

	//    public List<PrinterVendors> getPrinterVendorsList() {
	//        return printerVendorsList;
	//    }
	//
	//    public void setPrinterVendorsList(List<PrinterVendors> printerVendorsList) {
	//        this.printerVendorsList = printerVendorsList;
	//    }

	public void setPrinterTemplatesList(List<PrinterTemplates> printerTemplatesList) {
		this.printerTemplatesList = printerTemplatesList;

		if (!printModeMap.isEmpty())
			printModeMap.clear();

		int size = printerTemplatesList.size();
		for (int i = 0; i < size; i++) {
			PrinterTemplates printerTemplate = printerTemplatesList.get(i);
			//客用小票
			if (printerTemplate.getTemplateType() == PrintTemplateType.CUSTOMER) {
				printModeMap.put(PA_Constant.EventState.PRINTER_ORDER, printerTemplate.getModels());
			}
			//加菜单
			if (printerTemplate.getTemplateType() == PrintTemplateType.ADD_DISH) {
				printModeMap.put(PA_Constant.JsToAndroid.JS_A_ADDDISH, printerTemplate.getModels());
			}
			//退菜单
			if (printerTemplate.getTemplateType() == PrintTemplateType.RETREAT_DISH) {
				printModeMap
						.put(PA_Constant.EventState.PRINTER_RETREAT_KITCHEN_ORDER, printerTemplate
								.getModels());
			}
			//预结单
			if (printerTemplate.getTemplateType() == PrintTemplateType.PRE_CHECKOUT) {
				printModeMap.put(PA_Constant.EventState.ORDER_TYPE_ADVANCE, printerTemplate
						.getModels());
			}
			//结账单
			if (printerTemplate.getTemplateType() == PrintTemplateType.CHECKOUT) {
				printModeMap
						.put(PA_Constant.EventState.PRINT_CHECKOUT, printerTemplate.getModels());
			}
			//标签
			if (printerTemplate.getTemplateType() == PrintTemplateType.LABEL) {

			}
			//厨房单
			if (printerTemplate.getTemplateType() == PrintTemplateType.KICHEN) {
				printModeMap.put(PA_Constant.EventState.PRINTER_KITCHEN_ORDER, printerTemplate
						.getModels());
			}
		}

	}

	/**
	 * 映射厨房档口打印机数据
	 */
	private void handleKitchenStall() {
		if (kitchenStallDishMap == null)
			kitchenStallDishMap = new HashMap<Long, List<PA_KitchenStall>>();
		else
			kitchenStallDishMap.clear();
		if (kitchenStallList != null && !kitchenStallList.isEmpty()) {
			int size = kitchenStallList.size();
			for (int i = 0; i < size; i++) {
				//循环档口列表
				PA_KitchenStall kitchenStall = kitchenStallList.get(i);
				//如果该档口下的菜品列表不为空
				if (kitchenStall.getDishIdList() != null && kitchenStall.getDishIdList()
						.size() > 0) {
					for (Long dishId : kitchenStall.getDishIdList()) {
						//判断该菜品档口是否添加档口数据  list是因为一个菜品可能在多个档口
						List<PA_KitchenStall> kitchenStallList = kitchenStallDishMap
								.get(Long.valueOf(String.valueOf(dishId)));
						if (kitchenStallList == null) {
							kitchenStallList = new ArrayList<>();
							kitchenStallDishMap
									.put(Long.valueOf(String.valueOf(dishId)), kitchenStallList);
						}
						if (!kitchenStallList.contains(kitchenStall))
							kitchenStallList.add(kitchenStall);
					}
				}
			}
		}
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");// 用于格式化日期,作为日志文件名的一部分

	/**
	 * 打印菜品
	 *
	 * @param order
	 * @param oi
	 * @param printer
	 * @param kitchenStall
	 * @param printCount   当前的打印次数
	 */
	private void printOrderItemDish(final PA_Order order, final PA_OrderItem oi, final Printer printer, final PA_KitchenStall kitchenStall, int printCount) {
		try {
			if (printer.getIp() != null) {
				//                List<PA_KitchenStall> kitchenStallList = kitchenStallDishMap.get(oi.getDishId());
				//                int kitchenSize = kitchenStallList.size();
				//判断是标签纸还是普通纸  并且不是退菜单
				if (printer
						.getOutputType() == PrinterOutputType.LABEL && !isLogicRefundDish(order)) {
					//标签一份打印一张
					if (printer.getLinkType() == PrinterLinkType.NETWORK) {
						for (int i = 0; i < oi.getQuantity(); i++) {
							GpEnternetPrint.gpPrint(oi, printer.getIp(), printer.getLabelHeight());
						}
					}
				} else {
					if (kitchenStall.getDishReceiptCounts() != null && (int) kitchenStall
							.getDishReceiptCounts() > 0) {
						boolean isRetreatDish = false;
						//判断是否是退菜模式
						if (order.getTableStyle() == PA_Constant.EventState.PRINTER_RETREAT_DISH) {
							isRetreatDish = true;
						}
						int             modeCount       = getKitModeCount(oi, kitchenStall, isRetreatDish);
						ExecutorService fixedThreadPool = Executors.newSingleThreadExecutor();
						for (int i = 0; i < modeCount; i++) {
							final PrinterInterface printerInterface = PrinterFactory
									.createPrinter(PrinterVendor
											.fromName(printer.getVendor()), printer.getIp(), printer
											.getWidth());
							if (printer.getVendor().equals("sprt") || printer.getVendor()
									.equals("unknown")) {
								SystemClock.sleep(2000);
							}
							fixedThreadPool.execute(new Runnable() {
								@Override
								public void run() {
									try {
										printKitchenReceipt(printerInterface, order, oi, kitchenStall, printer);
									} catch (Exception e) {
										FileLog.log(Common.Exception, PrinterDataController.class, "printOrderItemDish", "printException", e
												.getMessage());
										e.printStackTrace();
									}
								}
							});


							//							new Thread() {
							//							@Override
							//								public void run() {
							//									try {
							//										printKitchenReceipt(printerInterface, order, oi, kitchenStall, printer);
							//									} catch (Exception e) {
							//										FileLog.log(format
							//												.format(new Date()) + "printOrderItemDish()异常,异常原因:" + e
							//												.getMessage() + "\n");
							//										e.printStackTrace();
							//									}
							//								}
							//							}.start();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//    /**
	//     * 保存总单上打印的菜品
	//     *
	//     * @param oi
	//     * @param kitchenStall
	//     */
	//    private void saveKitchenStallForDish(PA_OrderItem oi, PA_KitchenStall kitchenStall) {
	//        List<PA_OrderItem> oiList = summaryOiPrintMap.get(kitchenStall.getStallsid());
	//        if (oiList == null) {
	//            oiList = new ArrayList<>();
	//            summaryOiPrintMap.put(kitchenStall.getStallsid(), oiList);
	//        }
	//        oiList.add(oi);
	//    }

	private void printKitchenReceipt(PrinterInterface printerInterface, PA_Order order, PA_OrderItem orderItem, PA_KitchenStall kitchenStall, Printer printer) throws Exception {
		printerInterface.init();
		if (printer.isStandBy()) {
			TextRow row = createRow(true, 2, printer.getStandByErrmsg());
			row.setAlign(Alignment.CENTER);
			printerInterface.printRow(row);
			printerInterface.printRow(new Separator("-"));
			printerInterface.printRow(new Separator(" "));
		}
		printerInterface.printRow(new Separator(" "));
		printerInterface.printRow(new Separator(" "));
		printerInterface.printRow(new Separator(" "));

		String orderType = order.getOrderType();
		if (order.getTableStyle() == PA_Constant.EventState.PRINTER_RETREAT_DISH) {
			orderType = "退菜";
		} else if (order.getTableStyle() == PA_Constant.EventState.PRINTER_RUSH_DISH) {
			orderType = "催菜";
		} else if (order.getTableStyle() == PA_Constant.EventState.PRINTER_EXTRA_KITCHEN_RECEIPT) {
			orderType = "补打";
		} else {
			orderType = orderType.equals("EAT_IN") ? "堂食" : "外带";
		}
		TextRow row = createRow(false, 2, "厨房分单");
		row.setAlign(Alignment.CENTER);
		printerInterface.printRow(row);

		printerInterface.printRow(new Separator("-"));
		String tableName = order.getTableNames();
		String comment   = "";
		comment = order.getComment();
		if (logicIsTable()) {
			if (tableName != null && tableName.length() > 0) {
				TextRow rowNum = createRow(true, 2, "桌台号:" + (tableName == null ? "0" : tableName) + "     " + orderType);
				rowNum.setAlign(Alignment.CENTER);
				printerInterface.printRow(rowNum);
			}
		} else {
			String cardNumberType = "";
			String callNumber     = "";
			if (storeInfo.isCardNumberMode()) {
				cardNumberType = "餐牌号:";
				callNumber = (order.getCallNumber() == null ? "0" : order.getCallNumber());
			} else {
				cardNumberType = "取餐号:";
				callNumber = (order.getCallNumber() == null ? "0" : order.getCallNumber());
			}

			row = createRow(false, 2, cardNumberType + callNumber + "     " + orderType);
			row.setAlign(Alignment.CENTER);
			row.setScaleHeight(2);
			printerInterface.printRow(row);
		}

		printerInterface.printRow(new Separator("-"));

		printerInterface.printRow(createRow(false, 1, "订单号: " + order.getId()));
		printerInterface.printRow(createRow(false, 1, "下单时间: " + order.getCreatedAtStr()));

		printerInterface.printRow(new Separator(" "));
		String orderTitle = PrintUtils.getStr("菜品", 21, PrintUtils.TYPE_BACK) + PrintUtils
				.getStr("数量", 21, PrintUtils.TYPE_TOP);
		printerInterface.printRow(createRow(false, 1, orderTitle));
		printerInterface.printRow(new Separator("-"));

		String dishItem     = "";
		String dishQuantity = "";

		if (kitchenStall.getKitchenPrintMode() == PA_KitchenPrintMode.PER_DISH) {
			//退菜
			if (isLogicRefundDish(order)) {
				dishQuantity = orderItem.getRejectedQuantity() + "";
				dishQuantity = getDishRejectedQuantity(dishQuantity);
			}
			//正常下单打菜
			else {
				dishQuantity = orderItem.getQuantity() + "";
			}
		} else {
			dishQuantity = "1";
		}
		if (orderItem.getPackName() != null && orderItem.getPackName().length() > 0) {
			printerInterface.printRow(createRow(false, 2, "(" + orderItem
					.getPackName() + isShowDishPackageMoney(orderItem) + ")"));
		}
		dishItem = PrintUtils.getStr(orderItem.getDishName() + isWaiDai(order
				.getOrderType()), 16, PrintUtils.TYPE_BACK) + PrintUtils
				.getStr(dishQuantity + orderItem.dishUnit + isShowDishMoney(orderItem), 7, PrintUtils.TYPE_TOP);
		//厨房单里的菜品需要比较大的字体
		printerInterface.printRow(createRow(false, 2, dishItem));
		printDishOption(true, printerInterface, orderItem.optionList);
		if (!TextUtils.isEmpty(comment)) {
			TextRow rowNum = createRow(true, 2, "备注:( " + (comment == null ? "" : comment) + " ) ");
			rowNum.setAlign(Alignment.LEFT);
			printerInterface.printRow(rowNum);
		}
		printerInterface.printRow(new Separator(" "));
		printerInterface.printRow(new Separator(" "));
		printerInterface.printRow(new Separator(" "));
		printerInterface.close();
	}

	/**
	 * 这个订单是不是退菜模式
	 */
	private static boolean isLogicRefundDish(PA_Order order) {
		if (order.getTableStyle() == PA_Constant.EventState.PRINTER_RETREAT_DISH) {
			return true;
		}
		return false;
	}

	private static String getDishRejectedQuantity(String dishRejectedQuantity) {
		if (dishRejectedQuantity == null || "0".equals(dishRejectedQuantity)) {
			dishRejectedQuantity = "1";
		}
		return dishRejectedQuantity;
	}

	/**
	 * 厨房单是否要显示菜品金额  套餐项
	 *
	 * @param oi
	 * @return
	 */
	private String isShowDishPackageMoney(PA_OrderItem oi) {
		return "";
	}

	/**
	 * 厨房单是否要显示菜品金额  普通菜品
	 *
	 * @param oi
	 * @return
	 */
	private String isShowDishMoney(PA_OrderItem oi) {
		return "";
	}

	/**
	 * 打印普通菜品中的定制项
	 *
	 * @param printerInterface
	 * @param optionList
	 */
	private BigDecimal printDishOption(boolean isKitReceipt, PrinterInterface printerInterface, List<PA_Option> optionList) {
		BigDecimal dishOption = new BigDecimal("0.00");
		try {
			if (printerInterface != null && optionList != null && optionList.size() > 0) {
				if (optionList != null && optionList.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for (PA_Option option : optionList) {
						if (option.getPrice().compareTo(new BigDecimal("0")) == 0) {
							sb.append(option.name + "、");
						} else {
							dishOption = dishOption.add(option.getPrice());
							sb.append(option.name + "(" + option.getPrice() + "元)、");
						}
					}
					TextRow oiRow = createRow(false, 1, "    " + sb.toString());
					if (isKitReceipt) {
						oiRow.setScaleWidth(2);
						oiRow.setScaleHeight(2);
					}
					printerInterface.printRow(oiRow);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dishOption;
	}


	/**
	 * 打印总单处理
	 *
	 * @param summaryKitchenStallDishItemMap
	 * @param order
	 */
	private void printOrderKitSummaryTicket(Map<PA_KitchenStall, List<PA_OrderItem>> summaryKitchenStallDishItemMap, final PA_Order order) {
		if (summaryKitchenStallDishItemMap == null || summaryKitchenStallDishItemMap.isEmpty())
			return;

		for (final PA_KitchenStall kitchenStall : summaryKitchenStallDishItemMap.keySet()) {
			final List<PA_OrderItem> itemList = summaryKitchenStallDishItemMap.get(kitchenStall);
			final Printer            printer  = printerMap.get(kitchenStall.getPrinterid());


			if (printer != null) {
				final PrinterInterface printerInterface;
				if (printer.isUseStandBy()) {
					final Printer printer2 = printerMap.get(Integer
							.parseInt(printer.getStandbyPrinterIdList()));
					printerInterface = PrinterFactory
							.createPrinter(PrinterVendor.fromName(printer2.getVendor()), printer2
									.getIp(), printer2.getWidth());
					printOrderDishKitSummaryTicket(printerInterface, printer2, order, kitchenStall, itemList);
				} else {
					printerInterface = PrinterFactory
							.createPrinter(PrinterVendor.fromName(printer.getVendor()), printer
									.getIp(), printer.getWidth());
					printOrderDishKitSummaryTicket(printerInterface, printer, order, kitchenStall, itemList);
				}
			}
		}
	}
	//    /**
	//     * 初始化打印厨房总单
	//     *
	//     * @param orderKit
	//     */
	//    private void printOrderKitSummaryTicket(final PA_Order orderKit) {
	//        summaryTempKitchenStallMap.clear();
	//        if (summaryKitchenStallMap != null && summaryKitchenStallMap.size() > 0) {
	//            Iterator iter = summaryKitchenStallMap.entrySet().iterator();
	//            while (iter.hasNext()) {
	//                Map.Entry entry = (Map.Entry) iter.next();
	//                Long stallsid = (Long) entry.getKey();//找出总单map key
	//                if (orderKit != null && orderKit.getItemList() != null && orderKit.getItemList().size() > 0) {
	//                    List<PA_KitchenStall> kitchenStallList = summaryKitchenStallMap.get(stallsid);
	//                    if (kitchenStallList != null && kitchenStallList.size() > 0) {
	//                        //将同一个档口的菜品集合到一起
	//                        for (PA_KitchenStall kitchenStall : kitchenStallList) {
	//                            PA_KitchenStall tempMap = summaryTempKitchenStallMap.get(kitchenStall.getStallsid());
	//                            if (tempMap == null) {
	//                                summaryTempKitchenStallMap.put(kitchenStall.getStallsid(), kitchenStall);
	//                            }
	//                        }
	//                        Iterator iter2 = summaryTempKitchenStallMap.entrySet().iterator();
	//                        while (iter2.hasNext()) {
	//                            Map.Entry entry2 = (Map.Entry) iter2.next();
	//                            Long stallsid2 = (Long) entry2.getKey();//找出总单map key
	//                            final PA_KitchenStall kitchenStall2 = summaryTempKitchenStallMap.get(stallsid2);
	//
	//                            final Printer printer = printerMap.get(kitchenStall2.getPrinterid());
	//                            if (printer != null) {
	//                                final PrinterInterface printerInterface = PrinterFactory.createPrinter(PrinterVendor.fromName(printer.getVendor()), printer.getIp(), printer.getWidth());
	//                                new Thread()
	//                                {
	//                                    @Override
	//                                    public void run()
	//                                    {
	//                                        printOrderDishKitSummaryTicket(printerInterface, printer, orderKit, kitchenStall2);
	//                                    }
	//                                }.start();
	//                            }
	//                        }
	//                    }
	//                }
	//            }
	//        }
	//    }

	/**
	 * 打印厨房总单
	 *
	 * @param printerInterface
	 * @param printer
	 * @param order
	 * @param kitchenStall
	 */
	private void printOrderDishKitSummaryTicket(PrinterInterface printerInterface, Printer printer, PA_Order order, PA_KitchenStall kitchenStall, List<PA_OrderItem> orderItemList) {
		if (orderItemList != null && orderItemList.size() > 0) {
			try {
				printerInterface.init();
				printerInterface.printRow(new Separator(" "));
				printerInterface.printRow(new Separator(" "));
				printerInterface.printRow(new Separator(" "));
				printerInterface.printRow(new Separator(" "));

				if (printer.isStandBy()) {
					TextRow row = createRow(true, 2, printer.getStandByErrmsg());
					row.setAlign(Alignment.CENTER);
					printerInterface.printRow(row);
					printerInterface.printRow(new Separator("-"));
					printerInterface.printRow(new Separator(" "));
				}
				for (PA_OrderItem orderItem : orderItemList) {
					orderItem.setPackName(orderItem.getTempPackName());
				}
				String orderType = order.getOrderType();
				if (order.getTableStyle() == PA_Constant.EventState.PRINTER_RETREAT_DISH) {
					orderType = "退菜";
				} else if (order.getTableStyle() == PA_Constant.EventState.PRINTER_RUSH_DISH) {
					orderType = "催菜";
				} else if (order
						.getTableStyle() == PA_Constant.EventState.PRINTER_EXTRA_KITCHEN_RECEIPT) {
					orderType = "补打";
				} else {
					orderType = orderType.equals("EAT_IN") ? "堂食" : "外带";
				}
				TextRow row = createRow(false, 2, "厨房总单");
				row.setAlign(Alignment.CENTER);
				printerInterface.printRow(row);

				printerInterface.printRow(new Separator("-"));
				String tableName = order.getTableNames();
				String comment   = "";
				comment = order.getComment();
				if (logicIsTable()) {
					if (tableName != null) {
						TextRow rowNum = createRow(true, 2, "桌台号:" + (tableName == null ? "0" : tableName) + "     " + orderType);
						rowNum.setAlign(Alignment.CENTER);
						printerInterface.printRow(rowNum);
					}
				} else {
					String cardNumberType = "";
					String callNumber     = "";
					if (storeInfo.isCardNumberMode()) {
						cardNumberType = "餐牌号:";
						callNumber = (order.getCallNumber() == null ? "0" : order.getCallNumber());
					} else {
						cardNumberType = "取餐号:";
						callNumber = (order.getCallNumber() == null ? "0" : order.getCallNumber());
					}

					row = createRow(false, 2, cardNumberType + callNumber + "     " + orderType);
					row.setAlign(Alignment.CENTER);
					row.setScaleHeight(2);
					printerInterface.printRow(row);
				}

				printerInterface.printRow(new Separator("-"));

				printerInterface.printRow(createRow(false, 1, "订单号: " + order.getId()));
				printerInterface.printRow(createRow(false, 1, "下单时间: " + order.getCreatedAtStr()));

				printerInterface.printRow(new Separator(" "));
				String orderTitle = PrintUtils.getStr("菜品", 21, PrintUtils.TYPE_BACK) + PrintUtils
						.getStr("数量", 21, PrintUtils.TYPE_TOP);
				printerInterface.printRow(createRow(false, 1, orderTitle));
				printerInterface.printRow(new Separator("-"));

				String previousPackageName = "";
				for (PA_OrderItem orderItem : orderItemList) {
					orderItem.setTempPackName(orderItem.getPackName());
					if (previousPackageName.equals(orderItem.getPackName())) {
						orderItem.setPackName("");
					} else {
						previousPackageName = orderItem.getPackName();
					}
				}
				for (PA_OrderItem orderItem : orderItemList) {
					String dishItem     = "";
					String dishQuantity = "";
					//退菜
					if (isLogicRefundDish(order)) {
						dishQuantity = orderItem.getRejectedQuantity() + "";
						dishQuantity = getDishRejectedQuantity(dishQuantity);
					}
					//正常下单打菜
					else {
						dishQuantity = orderItem.getQuantity() + "";
					}

					if (orderItem.getPackName() != null && orderItem.getPackName()
							.length() > 0) {
						TextRow rowNum = createRow(true, 2, "(" + orderItem
								.getPackName() + isShowDishPackageMoney(orderItem) + ")");
						rowNum.setAlign(Alignment.LEFT);
						printerInterface.printRow(rowNum);
					}
					//					}
					if (isLogicRefundDish(order)) {
						dishItem = PrintUtils
								.getStr("[退]" + orderItem.getDishName() + isWaiDai(order
										.getOrderType()), 16, PrintUtils.TYPE_BACK) + PrintUtils
								.getStr("-" + dishQuantity + orderItem
										.getDishUnit() + isShowDishMoney(orderItem), 5, PrintUtils.TYPE_TOP);
					} else {
						dishItem = PrintUtils.getStr(orderItem.getDishName() + isWaiDai(order
								.getOrderType()), 16, PrintUtils.TYPE_BACK) + PrintUtils
								.getStr(dishQuantity + orderItem
										.getDishUnit() + isShowDishMoney(orderItem), 5, PrintUtils.TYPE_TOP);
					}


					printerInterface.printRow(createRow(false, 2, dishItem));


					printDishOption(true, printerInterface, orderItem.optionList);


					if (!TextUtils.isEmpty(comment)) {
						TextRow rowNum = createRow(true, 2, "备注:( " + (comment == null ? "" : comment) + " ) ");
						rowNum.setAlign(Alignment.LEFT);
						printerInterface.printRow(rowNum);
					}
				}

				if (order.isPrintSumTiketQrCode()) {
					String kdsCode = getBarcode(order.getId() + "");
					printerInterface.printRow(new Separator(" "));

					Bitmap qrcode = createQRCode(kdsCode, 250);
					printerInterface.printBmp(new BitmapRow(qrcode), true);
				}

				printerInterface.printRow(new Separator(" "));
				printerInterface.printRow(new Separator(" "));
				printerInterface.printRow(new Separator(" "));
				printerInterface.close();
				FileLog.log(Common.Log, PrinterDataController.class, "printOrderDishKitSummaryTicket", "printSummary", printer
						.isStandBy() ? "备用" : "" + "打印机>" + printer
						.getDeviceName() + "," + printer.getIp() + "总单打印完成");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix matrix = new MultiFormatWriter()
				.encode(str, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
		int   width  = matrix.getWidth();
		int   height = matrix.getHeight();
		int[] pixels = new int[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				} else {
					pixels[y * width + x] = 0xffffffff;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	private String getBarcode(String oid) {
		return "X" + oid + "Z";
	}

	private Alignment getAlian(int alian) {
		Alignment align = Alignment.LEFT;
		if (alian == 0) {
			align = Alignment.LEFT;
		}
		if (alian == 2) {
			align = Alignment.CENTER;
		}
		if (alian == 3) {
			align = Alignment.LEFT;
		}
		return align;
	}

	private void printEnter(PrinterInterface printerInterface, boolean isPrintEnter) throws IOException {
		if (isPrintEnter) {
			printerInterface.printRow(new Separator(" "));
		}
	}

	private static TextRow createRow(boolean bold, int size, String content) {
		TextRow title = new TextRow(content);
		title.setScaleWidth(size);
		title.setScaleHeight(size);
		title.setBoldFont(bold);
		return title;
	}

	//创建一行， 内容为 左对齐 和 有对齐 的2列
	private Table createRow(boolean bold, int size, String left, String right) {
		Table   table = new Table(2);
		TextRow row   = new TextRow();

		row.setScaleHeight(size);
		row.setScaleWidth(size);
		row.setBoldFont(bold);
		row.addColumn(new Column(left, Alignment.LEFT));
		row.addColumn(new Column(right, Alignment.RIGHT));

		table.addRow(row);
		return table;
	}

	private int getKitModeCount(PA_OrderItem oi, PA_KitchenStall kitchenStall, boolean isRefundDish) {
		int modeCount = 0;
		if (kitchenStall.getKitchenPrintMode() == PA_KitchenPrintMode.PER_DISH) {//多份一单
			modeCount = 1;
		} else //多份多单
		{
			if (isRefundDish) {
				modeCount = oi.getRejectedQuantity();
			} else {
				modeCount = oi.getQuantity();
			}
		}
		return modeCount;
	}

	/**
	 * 判断是否配置桌台  true是  false否
	 *
	 * @return
	 */
	private boolean logicIsTable() {
		boolean isTable   = true;
		String  storeMode = storeInfo.getStoreMode();
		if ("TABLE".equals(storeMode)) {
			isTable = true;
		} else {
			isTable = false;
		}
		return isTable;
	}

	private String isWaiDai(String orderType) {
		if (!orderType.equals("EAT_IN")) {
			return "(外带)";
		}
		return "";
	}

	//    private String getTimeStr(long time) {
	//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	//        return sf.format(new Date(time));
	//    }

	// 对订单中的菜品进行处理， 把套餐中的每一项都抽出来
	private PA_Order initOrder(PA_Order o) {
		List<PA_OrderItem> copyOrderItemList = new CopyOnWriteArrayList<>();
		//全新的订单菜品list
		for (PA_OrderItem orderItem : o.getItemList()) {
			//套餐
			if (orderItem.getSubItemList() != null && orderItem.getSubItemList().size() > 0) {
				List<PA_OrderItem> subDishes = orderItem.getSubItemList();
				int                size      = subDishes.size();
				for (int i = 0; i < size; i++) {
					PA_OrderItem orderItem1 = subDishes.get(i);
					orderItem1.setRejectedQuantity(orderItem.getRejectedQuantity() * orderItem1
							.getQuantity());
					orderItem1.setQuantity(orderItem.quantity * orderItem1.quantity);
					orderItem1.setPackName(orderItem.getDishName());
					orderItem1.setDishName("[套]" + orderItem1.getDishName());
					//                    orderItem1.setPrinterStr(orderItem1.getPrinterStr());
					orderItem1.setDishKind(orderItem1.getDishKind());
					;
					//如果是全单退菜模式 退菜份数等于菜品选择的份数
					if (o.getTableStyle() == PA_Constant.EventState.PRINTER_RETREAT_DISH) {
						orderItem1.setRejectedQuantity(orderItem.getQuantity() * orderItem1
								.getQuantity());
					}
					copyOrderItemList.add(orderItem1);
				}
			}
			//普通菜品
			else {
				//                orderItem.setPrinterStr(orderItem.getPrinterStr());
				if (o.getTableStyle() == PA_Constant.EventState.PRINTER_RETREAT_DISH) {
					orderItem.setRejectedQuantity(orderItem.getQuantity());
				}
				copyOrderItemList.add(orderItem);
			}
		}
		o.setItemList(copyOrderItemList);
		return o;
	}


	public Printer getPrint(int printId) {
		Printer printer = printerMap
				.get(printId);//通过档口id找到打印机
		return printer;
	}

	/**
	 * 打印总单
	 *
	 * @param order
	 */
	public void printKitchenSummaryOrder(PA_Order order) {
		initOrder(order);
		try {
			Map<PA_KitchenStall, List<PA_OrderItem>> summaryKitchenStallDishItemMap = new HashMap<PA_KitchenStall, List<PA_OrderItem>>();
			if (order != null && order.getItemList() != null && order.getItemList().size() > 0) {
				for (PA_OrderItem oi : order.getItemList()) {
					oi.setOrderId(order.getId());
					List<PA_KitchenStall> kitchenStallList = kitchenStallDishMap
							.get(oi.getDishId());//通过菜品id找到相应的档口
					if (kitchenStallList != null) {
						int size = kitchenStallList.size();
						for (int i = 0; i < size; i++) {
							PA_KitchenStall kitchenStall = kitchenStallList.get(i);
							//如果是总单档口
							if (kitchenStall.getSummaryReceiptCounts() != null && kitchenStall
									.getSummaryReceiptCounts() > 0) {
								if (!summaryKitchenStallDishItemMap.containsKey(kitchenStall)) {
									List<PA_OrderItem> itemList = new ArrayList<PA_OrderItem>();
									itemList.add(oi);
									summaryKitchenStallDishItemMap.put(kitchenStall, itemList);
								} else {
									List<PA_OrderItem> itemList = summaryKitchenStallDishItemMap
											.get(kitchenStall);
									itemList.add(oi);
								}
							}
						}
					}
				}
				// 如果总单中不是空的，打印总单
				if (!summaryKitchenStallDishItemMap.isEmpty())
					printOrderKitSummaryTicket(summaryKitchenStallDishItemMap, order);
			}
		} catch (Exception e) {
			FileLog.log(Common.Log, PrinterDataController.class, "printKitchenSummaryOrder", "print", "总单打印异常" + e
					.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 打印厨房分单
	 *
	 * @param order
	 */
	public void printKitchenItemOrder(PA_Order order) {
		//        summaryKitchenStallMap.clear();
		//        summaryOiPrintMap.clear();
		//        summaryTempKitchenStallMap.clear();
		initOrder(order);
		try {
			if (order != null && order.getItemList() != null && order.getItemList().size() > 0) {
				for (PA_OrderItem oi : order.getItemList()) {
					oi.setOrderId(order.getId());
					List<PA_KitchenStall> kitchenStallList = kitchenStallDishMap
							.get(oi.getDishId());//通过菜品id找到相应的档口
					if (kitchenStallList != null) {
						int size = kitchenStallList.size();
						for (int i = 0; i < size; i++) {
							PA_KitchenStall kitchenStall = kitchenStallList.get(i);
							if (kitchenStall.getDishReceiptCounts() > 0) {
								//判断门店配置的打印机打印菜品,打印分单
								if (printerMap != null && printerMap.size() > 0) {
									Printer printer = printerMap
											.get(kitchenStall.getPrinterid());//通过档口id找到打印机
									if (printer != null) {

										if (printer.isUseStandBy()) {
											Printer printer2 = printerMap.get(Integer
													.parseInt(printer.getStandbyPrinterIdList()));
											if (printer2 != null) {
												printOrderItemDish(order, oi, printer2, kitchenStall, i);
											}
										} else {
											printOrderItemDish(order, oi, printer, kitchenStall, i);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			FileLog.log(Common.Log, PrinterDataController.class, "printKitchenItemOrder", "print", "分单打印异常" + e
					.getMessage());
			e.printStackTrace();
		}

	}

	public List<Printer> getPrintList(PA_Order order) {
		ArrayList<Printer>                       printerList                    = new ArrayList<>();
		ArrayList<Integer>                       idList                         = new ArrayList<>();
		Map<PA_KitchenStall, List<PA_OrderItem>> summaryKitchenStallDishItemMap = new HashMap<PA_KitchenStall, List<PA_OrderItem>>();
		if (order != null && order.getItemList() != null && order.getItemList().size() > 0) {
			for (PA_OrderItem oi : order.getItemList()) {
				List<PA_KitchenStall> kitchenStallList = kitchenStallDishMap
						.get(oi.getDishId());//通过菜品id找到相应的档口
				if (kitchenStallList != null) {
					int size = kitchenStallList.size();
					for (int i = 0; i < size; i++) {
						PA_KitchenStall kitchenStall = kitchenStallList.get(i);
						if (kitchenStall.getDishReceiptCounts() > 0) {
							//判断门店配置的打印机打印菜品,打印分单
							if (printerMap != null && printerMap.size() > 0) {
								Printer printer = printerMap
										.get(kitchenStall.getPrinterid());//通过档口id找到打印机

								if (printer == null) {
									continue;
								}
								if (!idList.contains(kitchenStall.getPrinterid())) {
									if (!printerList.contains(printer)) {
										printerList.add(printer);
									}
									idList.add(kitchenStall.getPrinterid());
								}
							}
						}
						if (kitchenStall.getSummaryReceiptCounts() != null && kitchenStall
								.getSummaryReceiptCounts() > 0) {
							//                            List<PA_KitchenStall> summaryKitchenStallList = summaryKitchenStallMap.get(kitchenStall.getStallsid());
							//                            if (summaryKitchenStallList == null) {
							//                                summaryKitchenStallList = new ArrayList<>();
							//                                summaryKitchenStallMap.put(kitchenStall.getStallsid(), summaryKitchenStallList);
							//                            }
							//                            if (!summaryKitchenStallList.contains(kitchenStall))
							//                                summaryKitchenStallList.add(kitchenStall);
							//
							//                            saveKitchenStallForDish(oi, kitchenStall);
							if (!summaryKitchenStallDishItemMap.containsKey(kitchenStall)) {
								Printer printer = printerMap
										.get(kitchenStall.getPrinterid());//通过档口id找到打印机
								if (!printerList.contains(printer)) {
									printerList.add(printer);
								}
							}
						}
					}
				}
			}
		}
		return printerList;
	}

	public void resetPrintStatus() {
		if (printerMap != null) {
			for (Integer key : printerMap.keySet()) {
				Printer printer = printerMap.get(key);
				if (printer != null) {
					printer.setUseStandBy(false);
					printer.setErrMessage("");
				}
			}
		}
	}


}
