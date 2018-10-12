package com.acewill.slefpos.print.chikenprintlibrary;

import android.content.Context;

import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.smarantstorebean.KdsEntity;
import com.acewill.slefpos.bean.smarantstorebean.KichenStallEntity;
import com.acewill.slefpos.bean.smarantstorebean.PrinterEntity;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.print.PrintManager;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KDS;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KDSRes;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KitchenPrintMode;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KitchenStall;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KitchenStallRes;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_Option;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_Order;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_OrderItem;
import com.acewill.slefpos.print.chikenprintlibrary.model.PA_StoreInfo;
import com.acewill.slefpos.print.chikenprintlibrary.printer.Printer;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by John5 on 2017/5/1.
 */
public class PosKitchenPrintAdapter {
	private static final String TAG = "PosKitchenPrintAdapter";
	private static Context               mContext;
	private        PrinterDataController printerDataController;

	private static PosKitchenPrintAdapter instance;

	private PosKitchenPrintAdapter() {
		printerDataController = new PrinterDataController();
	}

	public static PosKitchenPrintAdapter getInstance(Context context) {
		if (instance == null) {
			instance = new PosKitchenPrintAdapter();
			mContext = context;
		}

		return instance;
	}

	//	/**
	//	 * 初始化厨房打印的信息
	//	 *
	//	 * @param serverUrl http://host:port
	//	 * @param appid
	//	 * @param brandid
	//	 * @param storeid
	//	 * @param token
	//	 * @throws Exception
	//	 */
	//	public void init(String serverUrl, String appid, String brandid, String storeid, String token) throws Exception {
	//
	//		// 获取打印需要的相关信息
	//		// 获取门店设置信息
	//		KitChenPrintManager.getInstance()
	//				.sendPostRequest(serverUrl, token, appid, brandid, KitChenPrintManager.GETSTORECONFIGURATIONURL, storeid, KitChenPrintManager.GETSTORECONFIGURATION, new KitChenPrintCallBack());
	//
	//		// 获取打印机的信息
	//		KitChenPrintManager.getInstance()
	//				.sendPostRequest(serverUrl, token, appid, String
	//						.valueOf(brandid), KitChenPrintManager.GETPRINTERSURL, String
	//						.valueOf(storeid), KitChenPrintManager.GETPRINTERS, new KitChenPrintCallBack());
	//
	//		// 获取KDS的信息
	//		KitChenPrintManager.getInstance()
	//				.sendPostRequest(serverUrl, token, appid, String
	//						.valueOf(brandid), KitChenPrintManager.GETKDSESURL, String
	//						.valueOf(storeid), KitChenPrintManager.GETKDSES, new KitChenPrintCallBack());
	//
	//		// 获取档口的信息
	//		KitChenPrintManager.getInstance()
	//				.sendPostRequest(serverUrl, token, appid, String
	//						.valueOf(brandid), KitChenPrintManager.GETKICHENSTALLSURL, String
	//						.valueOf(storeid), KitChenPrintManager.GETKICHENSTALLS, new KitChenPrintCallBack());
	//
	//		// 获取打印模板信息
	//		KitChenPrintManager.getInstance()
	//						.sendPostRequest(serverUrl, token, appid, String
	//								.valueOf(brandid), KitChenPrintManager.GETALLTEMPLATESURL, String
	//								.valueOf(storeid), KitChenPrintManager.GETALLTEMPLATES, new KitChenPrintCallBack());
	//
	//		//		printerDataController.set
	//	}

	/**
	 * 进行厨房打印处理
	 *
	 * @param order
	 * @throws Exception
	 */
	public void print(final PA_Order order) {
		// 处理厨房打印
		new Thread(new Runnable() {
			@Override
			public void run() {
				printerDataController.printKitchenItemOrder(order);
				printerDataController.printKitchenSummaryOrder(order);
			}
		}).start();
	}


	public List<Printer> getPrintList(PA_Order order) {
		return printerDataController.getPrintList(order);
	}

	public Printer getPrint(int printId) {
		return printerDataController.getPrint(printId);
	}

	public List<PA_KDS> getKdsList() {
		return printerDataController.getKdsList();
	}

	public void resetPrintStatus() {
		printerDataController.resetPrintStatus();
	}


	public void init() {

		PA_StoreInfo info = new PA_StoreInfo();
		info.setCardNumberMode(SPUtils.getSharedIntData(mContext, "SHOWTABLE") == 1 ? true : false);
		printerDataController.setStoreInfo(info);


		List<Printer> plist = copyPrintList(SmarantDataProvider.getPrinterList().getContent());
		if (plist != null) {
			printerDataController.setPrinterList(plist);
		}


		List<PA_KDSRes.ContentBean> kdslist = copyKdsList(SmarantDataProvider.getKdsInfo()
				.getContent());
		if (kdslist != null) {
			ArrayList<PA_KDS> kdses = new ArrayList<>();
			for (PA_KDSRes.ContentBean bean : kdslist) {
				PA_KDS pk = new PA_KDS();
				pk.setId(bean.getId());
				pk.setIp(bean.getIp());
				pk.setKdsName(bean.getKdsName());
				kdses.add(pk);
			}
			printerDataController.setKdsList(kdses);
		}


		List<PA_KitchenStallRes.ContentBean> slist = copyKitChenStallList(SmarantDataProvider
				.getKichenStalls().getContent());
		if (slist != null) {
			ArrayList<PA_KitchenStall> stalls = new ArrayList<>();
			for (PA_KitchenStallRes.ContentBean bean : slist) {
				PA_KitchenStall k = new PA_KitchenStall();
				k.setStallsid(bean.getStallsid());
				k.setStallsName(bean.getStallsName());
				k.setPrinterid(bean.getPrinterid());
				k.setKdsid(bean.getKdsid());
				k.setSummaryReceiptCounts(bean.getSummaryReceiptCounts());
				k.setDishReceiptCounts(bean.getDishReceiptCounts());
				k.setDishIdList(bean.getDishIdList());
				k.setKitchenPrintMode(PA_KitchenPrintMode
						.fromValue(bean.getKitchenPrintMode()));
				stalls.add(k);
			}
			printerDataController.setKitchenStallList(stalls);
		}

	}

	private List<PA_KitchenStallRes.ContentBean> copyKitChenStallList(List<KichenStallEntity> content) {
		List<PA_KitchenStallRes.ContentBean> contentBeanList = new ArrayList<>();
		for (KichenStallEntity entity : content) {
			PA_KitchenStallRes.ContentBean bean = new PA_KitchenStallRes.ContentBean();
			bean.setSummaryReceiptCounts(entity.getSummaryReceiptCounts());
			bean.setDishIdList(entity.getDishIdList());
			bean.setDishReceiptCounts(entity.getDishReceiptCounts());
			bean.setKdsid(entity.getKdsid());
			bean.setKitchenPrintMode(entity.getKitchenPrintMode());
			bean.setPrinterid(entity.getPrinterid());
			bean.setStallsid(entity.getStallsid());
			bean.setStallsName(entity.getStallsName());
			contentBeanList.add(bean);
		}
		return contentBeanList;
	}

	private List<PA_KDSRes.ContentBean> copyKdsList(List<KdsEntity> content) {
		PA_KDSRes.ContentBean       bean            = new PA_KDSRes.ContentBean();
		List<PA_KDSRes.ContentBean> contentBeenList = new ArrayList<>();
		for (KdsEntity entity : content) {
			bean.setId(entity.getId());
			bean.setIp(entity.getIp());
			bean.setKdsName(entity.getKdsName());
			contentBeenList.add(bean);
		}
		return contentBeenList;
	}

	private List<Printer> copyPrintList(List<PrinterEntity> list) {
		ArrayList<Printer> printers = new ArrayList<>();
		for (PrinterEntity entity : list) {
			Printer printer = new Printer();
			printer.setDescription(entity.getDescription());
			printer.setDeviceName(entity.getDeviceName());
			printer.setDishReceiptCounts(entity.getDishReceiptCounts());
			printer.setId(entity.getId());
			printer.setLabelHeight(entity.getLabelHeight());
			printer.setReceiptIdList(entity.getReceiptIdList());
			printer.setStandbyPrinterIdList(entity.getStandbyPrinterIdList());
			printer.setStandbyPrinterName(entity.getStandbyPrinterName());
			printer.setVendor(entity.getVendor());
			printer.setVendorStr(entity.getVendorStr());
			printer.setWidthStr(entity.getWidthStr());
			printer.setSummaryReceiptCounts(entity.getSummaryReceiptCounts());
			printer.setIp(entity.getIp());
			printers.add(printer);
		}
		return printers;
	}

	public static void printChikenTicket(Context context, String orderId, String callNumber) {
		//		FileLog.log(Common.Log, PrintManager.class, "printChikenTicket", "print", "init-print");
		PA_Order paOrder = PosKitchenPrintAdapter
				.createPrintOrderData(orderId, callNumber);
		if (paOrder != null) {
			PosKitchenPrintAdapter.getInstance(context).print(paOrder);
		} else {
			FileLog.log(Common.Error, PrintManager.class, "printChikenTicket", "print", "paOrder==null");
		}
	}

	public static PA_Order createPrintOrderData(String orderId, String callNumber) {
		// 输入桌台的模式，把取餐号，tablename都设置成输入的桌台号码

		// 创建相应的菜品列表
		List<PA_OrderItem> itemList = new ArrayList<PA_OrderItem>();
		for (CartDish d : Cart.getInstance().getCartDishes())
			itemList.add(createPrintDishItem(d, orderId));

		PA_Order po = new PA_Order();
		po.setId(orderId);
		po.setComment("");
		po.setTotal(String.valueOf(Price.getInstance().getTotal()));
		po.setCost(String.valueOf(Price.getInstance().getTotal()));
		po.setPrintSumTiketQrCode(false);
		//		if (printModel.isTakeOut == 0)
		//			po.setOrderType("EAT_IN");
		if (Order.getInstance().getTakeOut() == 0)
			po.setOrderType("EAT_IN");
		else
			po.setOrderType("EAT_OUT");
		if (Order.getInstance().getTableId() != null)
			po.setCallNumber(Order.getInstance().getTableId());
		else
			po.setCallNumber(callNumber);
		po.setTableNames("");
		po.setCreatedAtStr(format2.format(new Date()));
		po.setItemList(itemList);
		return po;
	}

	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 用于格式化日期,作为日志文件名的一部分

	private static PA_OrderItem createPrintDishItem(CartDish d, String orderId) {
		if (d == null)
			return null;

		PA_OrderItem item = new PA_OrderItem();
		item.setOrderId(orderId);
		item.setDishId(Long.parseLong(d.getDishID()));
		item.setDishName(d.getDishName());
		item.setPrice(new BigDecimal(d.getPrice()));
		item.setCost(new BigDecimal(d.getCost()));
		item.setQuantity(d.getQuantity());
		item.setDishKind(d.getDishKind());
		item.setIsPackage(d.getIsPackage());
		item.setDishUnit(d.getDishUnit());

		if (UIDataProvider.isDishIsPackage(d.getDishID())) {
			// 是套餐，那么需要把套餐里面的套餐项目构建出来
			List<UIPackageOptionItem> packageBeanItems = d.getSubItemList();
			ArrayList<PA_OrderItem>   subItemList      = new ArrayList<PA_OrderItem>();
			if (packageBeanItems != null) {
				for (UIPackageOptionItem p : packageBeanItems) {
					subItemList.add(createSubOrderItem(p, d.getDishName(), orderId));
				}

			}
			item.setSubItemList(subItemList);
		} else
			item.setPackName(null);

		if (!UIDataProvider.isDishIsPackage(d.getDishID())) {
			// 定制项的信息
			List<UITasteOption> optionList = d.getOptionList();
			if (optionList != null) {
				ArrayList<PA_Option> oplist = new ArrayList<PA_Option>();
				for (UITasteOption o : optionList) {
					PA_Option po = new PA_Option();
					po.setName(o.getOptionName());
					DecimalFormat df = new DecimalFormat("#.00");
					po.setPrice(new BigDecimal(df.format(o.getPrice())));
					oplist.add(po);
				}
				item.setOptionList(oplist);
			}
		}

		return item;
	}

	private static PA_OrderItem createSubOrderItem(UIPackageOptionItem p, String packageName, String orderid) {
		PA_OrderItem item = new PA_OrderItem();
		item.setDishName(p.getDishName());
		item.setDishId(Long.parseLong(p.getDishID()));
		item.setOrderId(orderid);
		if (p.getPrice() != null && p.getPrice().length() > 0)
			item.setPrice(new BigDecimal(p.getPrice()));
		if (p.getPrice() != null && p.getPrice().length() > 0)
			item.setCost(new BigDecimal(p.getPrice()));
		item.setQuantity(p.getQuantity());
		item.setDishUnit(p.getUnit());
		item.setIsPackage(0);
		item.setPackName(packageName);


		if (p.getOptionList() != null) {
			ArrayList<PA_Option> oplist = new ArrayList<PA_Option>();
			for (UITasteOption o : p.getOptionList()) {
				PA_Option po = new PA_Option();
				po.setName(o.getOptionName());
				DecimalFormat df = new DecimalFormat("#.00");
				po.setPrice(new BigDecimal(df.format(o.getPrice())));
				oplist.add(po);
			}
			item.setOptionList(oplist);
		}


		return item;
	}

	//	public static PA_Order initPrintOrderData() {
	//		PA_Order       po        = new PA_Order();
	//		List<CartDish> dishItems = Cart.getInstance().getCartDishes();
	//		if (dishItems == null || dishItems.isEmpty())
	//			return null;
	//
	//		// 创建相应的菜品列表
	//		List<PA_OrderItem> itemList = new ArrayList<>();
	//		for (CartDish d : dishItems)
	//			itemList.add(createPrintDishItem(d,));
	//		po.setItemList(itemList);
	//		return po;
	//	}
}
