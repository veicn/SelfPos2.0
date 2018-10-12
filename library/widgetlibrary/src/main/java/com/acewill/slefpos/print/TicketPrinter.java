package com.acewill.slefpos.print;//package com.acewill.ordermachine.print;
//
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//
//import POSAPI.POSInterfaceAPI;
//import POSAPI.POSUSBAPI;
//import POSSDK.POSSDK;
//
//import android.content.Context;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.hardware.usb.UsbDevice;
//import android.hardware.usb.UsbManager;
//import android.widget.Toast;
//
//import com.acewill.ordermachine.model.PrintModel;
//import com.acewill.ordermachine.model.PaymentInfo;
//import com.acewill.ordermachine.pos.Order;
//
///**
// * 新北洋打印机
// *
// */
//public class TicketPrinter {
//	// Returned Value Statement
//	public static final int POS_SUCCESS = 1000; // success
//	public static final int ERR_PROCESSING = 1001; // processing error
//	public static final int ERR_PARAM = 1002; // parameter error
//
//	// Print Mode
//	public static final int PRINT_MODE_STANDARD = 0;
//	public static final int PRINT_MODE_PAGE = 1;
//	public static int printMode = PRINT_MODE_STANDARD;
//
//	private static int FontType = 0;
//	private static int FontStyle = 3;
//	/**
//	 * 0 left,1 center,2 right
//	 */
//	private static int Alignment = 0;
//	private static int HorStartingPosition = 0;
//	private static int VerStartingPosition = 0;
//	private static int LineHeight = 0;
//	private static int HorizontalTimes = 0;
//	private static int VerticalTimes = 0;
//
//	// SDK variable
//	public static POSSDK pos_usb = null;
//	private POSInterfaceAPI interface_usb = null;
//	private int error_code = 0;
//
//	private Context context;
//
//	private static TicketPrinter INST = null;
//
//	public static TicketPrinter getInstance(Context context) {
//		if (INST == null) {
//			INST = new TicketPrinter(context);
//		} else {
//			if (!INST.context.equals(context)) {
//				INST.close();
//
//				INST = new TicketPrinter(context);
//			}
//		}
//
//		return INST;
//	}
//
//	private TicketPrinter(Context context) {
//		this.context = context;
//
//		init();
//	}
//
//	private boolean init() {
//		// boolean bRes = grantAutomaticPermission();
//		// System.out.println("grant permission res = " + bRes );
//
//		System.out.println("init TicketPrinter instance for " + context);
//
//		interface_usb = new POSUSBAPI(context);
//		try {
//			interface_usb.CloseDevice();
//			error_code = interface_usb.OpenDevice();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//
//			error_code = -1;
//
//		}
//		if (error_code != POS_SUCCESS) {
//			// Toast.makeText(context, "打印机端口异常(" + error_code +")",
//			// Toast.LENGTH_SHORT).show();
//
//			System.out.println("打印机端口异常(" + error_code + ")");
//			return false;
//		}
//
//		pos_usb = new POSSDK(interface_usb);
//
//		printMode = PRINT_MODE_STANDARD;// PRINT_MODE_PAGE;
//		error_code = pos_usb.systemSelectPrintMode(printMode);
//		return true;
//	}
//
//	public void close() {
//		try {
//			if (interface_usb != null) {
//				interface_usb.CloseDevice();
//			}
//		} catch (Exception ex) {
//
//		}
//		interface_usb = null;
//		pos_usb = null;
//	}
//
//	public boolean print(Order subOrder) {
//		if (pos_usb == null) {
//			boolean init = init();
//			if (!init) {
//				Toast.makeText(context, "设备故障，请联系餐厅服务员", 0).show();
//				return false;
//			}
//		}
//		if (pos_usb == null) {
//			Toast.makeText(context, "设备故障，请联系餐厅服务员", 0).show();
//			return false;
//		}
////		printHeader(false, subOrder.tips);
////		printOrderDetail(subOrder);
////		printFooter(subOrder.orderNo);
//
//		cut();
//
//		close();
//
//		return true;
//
//	}
//
////	public boolean print(PrintModel orderReq) {
//	//		if (pos_usb == null) {
//	//			boolean init = init();
//	//			if (!init) {
//	//				Toast.makeText(context, "设备故障，请联系餐厅服务员", 0).show();
//	//				return false;
//	//			}
//	//		}
//	//		if (pos_usb == null) {
//	//			Toast.makeText(context, "设备故障，请联系餐厅服务员", 0).show();
//	//			return false;
//	//		}
//	//
//	//		size2();
//	//		left();
//	//		if (!orderReq.callId.contains("桌")) {
//	//			printlnText(PrintUtil.getTitle());
//	//		}
//	//		center();
//	//		size2();
//	//		printlnText(orderReq.callId + "\n");
//	//		size1();
//	//		printlnText(PrintUtil.getWelcom());
//	//		left();
//	//		printlnText(orderReq.oid + "                 " + orderReq.isTakeOutStr);
//	//		printlnText(orderReq.stime);
//	//		printlnText(PrintUtil.getCashier());
//	//		printText(PrintUtil.getDishItemsString(Cart.getInstance().items,
//	//				orderReq) + "\n\n\n");
//	//		printText(PrintUtil.getPayInfo(orderReq));
//	//		printText(PrintUtil.dotLine);
//	//		center();
//	//		printlnText("\n\n" + PrintUtil.getWelcomFoot());
//	//		printlnText(PrintUtil.getAddress());
//	//
//	//		cut();
//	//		close();
//	//		return true;
//	//	}
//
//	public boolean print(ArrayList<PrintModel> printModels){
//		if (pos_usb == null) {
//			boolean init = init();
//			if (!init) {
//				Toast.makeText(context, "设备故障，请联系餐厅服务员", 0).show();
//				return false;
//			}
//		}
//		if (pos_usb == null) {
//			Toast.makeText(context, "设备故障，请联系餐厅服务员", 0).show();
//			return false;
//		}
//
//		for (PrintModel printModel : printModels) {
//			Alignment = printModel.align;
//			VerticalTimes = printModel.xSize;
//			printText(printModel.text);
//		}
//
//
//
//		cut();
//		close();
//		return true;
//	}
//
//	private void size1() {
//		VerticalTimes = 1;
//		HorizontalTimes = 1;
//	}
//
//	private void size2() {
//		VerticalTimes = 2;
//		HorizontalTimes = 2;
//	}
//
//	private void left() {
//		Alignment = 0;
//	}
//
//	private void center() {
//		Alignment = 1;
//	}
//
//	private void right() {
//		Alignment = 2;
//	}
//
//	public boolean print(Order subOrder, PaymentInfo paymentInfo) {
//		if (pos_usb == null) {
//			init();
//		}
//
//
//		cut();
//
//		close();
//
//		return true;
//
//	}
//
//
//	private int printText(String txtbuf) {
//		return printText(txtbuf, FontType, FontStyle, Alignment,
//				HorStartingPosition, VerStartingPosition, LineHeight,
//				HorizontalTimes, VerticalTimes);
//	}
//
//	private void printlnText(String text) {
//		printText(text + "\n");
//	}
//
//	private int printText(String txtbuf, int FontType, int FontStyle,
//			int Alignment, int HorStartingPosition, int VerStartingPosition,
//			int LineHeight, int HorizontalTimes, int VerticalTimes) {
//		return printText(pos_usb, printMode, txtbuf, FontType, FontStyle,
//				Alignment, HorStartingPosition, VerStartingPosition,
//				LineHeight, HorizontalTimes, VerticalTimes);
//	}
//
//	private int printText(POSSDK pos_sdk, int printMode, String txtbuf,
//			int FontType, int FontStyle, int Alignment,
//			int HorStartingPosition, int VerStartingPosition, int LineHeight,
//			int HorizontalTimes, int VerticalTimes) {
//		if (pos_sdk == null) {
//			return error_code;
//		}
//		if (printMode == TicketPrinter.PRINT_MODE_PAGE) {
//			// set print area
//			error_code = pos_sdk.pageModeSetPrintArea(0, 0, 640, 100, 0);
//			if (error_code != POS_SUCCESS) {
//				return error_code;
//			}
//			// set print position
//			error_code = pos_sdk.pageModeSetStartingPosition(
//					HorStartingPosition, VerStartingPosition);
//			if (error_code != POS_SUCCESS) {
//				return error_code;
//			}
//		} else {
//			// set the alignment ptid
//			error_code = pos_sdk.textStandardModeAlignment(Alignment);
//			if (error_code != POS_SUCCESS) {
//				return error_code;
//			}
//		}
//
//		// set the horizontal and vertical motion units
//		error_code = pos_sdk.systemSetMotionUnit(100, 100);
//		if (error_code != POS_SUCCESS) {
//			return error_code;
//		}
//
//		// set line height
//		error_code = pos_sdk.textSetLineHeight(LineHeight);
//		if (error_code != POS_SUCCESS) {
//			return error_code;
//		}
//
//		// set character font
//		error_code = pos_sdk.textSelectFont(FontType, FontStyle);
//		if (error_code != POS_SUCCESS) {
//			return error_code;
//		}
//
//		// set character size
//		error_code = pos_sdk.textSelectFontMagnifyTimes(HorizontalTimes,
//				VerticalTimes);
//		if (error_code != POS_SUCCESS) {
//			return error_code;
//		}
//
//		// print text
//		try {
//			byte[] send_buf = txtbuf.getBytes("GB18030");
//			error_code = pos_sdk.textPrint(send_buf, send_buf.length);
//			send_buf = null;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		if (error_code != POS_SUCCESS) {
//			return error_code;
//		}
//
//		// feed line
//		error_code = pos_sdk.systemFeedLine(1);
//		if (error_code != POS_SUCCESS) {
//			return error_code;
//		}
//
//		// entry page mode
//		if (printMode == TicketPrinter.PRINT_MODE_PAGE) {
//			// ******************************************************************************************
//			// print in page mode
//			error_code = pos_sdk.pageModePrint();
//
//			// *****************************************************************************************
//			// clear buffer
//			error_code = pos_sdk.pageModeClearBuffer();
//		}
//
//		return error_code;
//	}
//
//	private int printQR(String pszBuffer) {
//		int DataLength = 0;
//		try {
//			DataLength = pszBuffer.getBytes("GB18030").length;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		return printQR(pos_usb, printMode, pszBuffer, DataLength, 100, 5, 1, 0);
//	}
//
//	private int printQR(POSSDK pos_sdk, int printMode, String pszBuffer,
//			int DataLength, int nOrgx, int iWeigth, int iSymbolType,
//			int iLanguageMode) {
//		if (pos_sdk == null) {
//			return error_code;
//		}
//		if (printMode == TicketPrinter.PRINT_MODE_PAGE) {
//			// set print area
//			error_code = pos_sdk.pageModeSetPrintArea(0, 0, 640, 300, 0);
//			if (error_code != POS_SUCCESS) {
//				return error_code;
//			}
//			// set print position
//			error_code = pos_sdk.pageModeSetStartingPosition(20, 100);
//			if (error_code != POS_SUCCESS) {
//				return error_code;
//			}
//		}
//
//		error_code = pos_sdk.barcodePrintQR(pszBuffer, DataLength, nOrgx,
//				iWeigth, iSymbolType, iLanguageMode);
//		if (error_code != POS_SUCCESS) {
//			return error_code;
//		}
//
//		error_code = pos_sdk.systemFeedLine(2);
//		if (printMode == TicketPrinter.PRINT_MODE_PAGE) {
//			// ******************************************************************************************
//			// print in page mode
//			error_code = pos_sdk.pageModePrint();
//
//			// *****************************************************************************************
//			// clear buffer in page mode
//			error_code = pos_sdk.pageModeClearBuffer();
//		}
//
//		return error_code;
//
//	}// end of function TestPrintQR
//
//	private int cut() {
//		return cutPaper(pos_usb, printMode);
//	}
//
//	private int cutPaper(POSSDK pos_sdk, int printMode) {
//		if (pos_sdk == null) {
//			return error_code;
//		}
//		if (printMode == TicketPrinter.PRINT_MODE_PAGE) {
//			// ******************************************************************************************
//			// print in page mode
//			error_code = pos_sdk.pageModePrint();
//
//			error_code = pos_sdk.systemCutPaper(66, 0);
//
//			// *****************************************************************************************
//			// clear buffer in page mode
//			error_code = pos_sdk.pageModeClearBuffer();
//		} else {
//			error_code = pos_sdk.systemCutPaper(66, 0);
//		}
//		return error_code;
//	}
//
//	public boolean grantAutomaticPermission() {
//		try {
//			UsbManager mUsbManager = (UsbManager) this.context
//					.getSystemService("usb");
//			HashMap deviceList = mUsbManager.getDeviceList();
//
//			UsbDevice printerUSBDev = null;
//
//			for (Iterator deviceIterator = deviceList.values().iterator(); deviceIterator
//					.hasNext();) {
//				UsbDevice device = (UsbDevice) deviceIterator.next();
//				if (device.getVendorId() == 5455
//						|| device.getVendorId() == 1008) {
//					printerUSBDev = device;
//					break;
//				}
//			}
//
//			if (printerUSBDev == null) {
//				return false;
//			}
//
//			PackageManager pkgManager = context.getPackageManager();
//			ApplicationInfo appInfo = pkgManager.getApplicationInfo(
//					context.getPackageName(), PackageManager.GET_META_DATA);
//
//			Class serviceManagerClass = Class
//					.forName("android.os.ServiceManager");
//			Method getServiceMethod = serviceManagerClass.getDeclaredMethod(
//					"getService", String.class);
//			getServiceMethod.setAccessible(true);
//			android.os.IBinder binder = (android.os.IBinder) getServiceMethod
//					.invoke(null, Context.USB_SERVICE);
//
//			Class iUsbManagerClass = Class
//					.forName("android.hardware.usb.IUsbManager");
//			Class stubClass = Class
//					.forName("android.hardware.usb.IUsbManager$Stub");
//			Method asInterfaceMethod = stubClass.getDeclaredMethod(
//					"asInterface", android.os.IBinder.class);
//			asInterfaceMethod.setAccessible(true);
//			Object iUsbManager = asInterfaceMethod.invoke(null, binder);
//
//			System.out.println("UID : " + appInfo.uid + " "
//					+ appInfo.processName + " " + appInfo.permission);
//			@SuppressWarnings("unchecked")
//			final Method grantDevicePermissionMethod = iUsbManagerClass
//					.getDeclaredMethod("grantDevicePermission",
//							UsbDevice.class, int.class);
//			grantDevicePermissionMethod.setAccessible(true);
//			grantDevicePermissionMethod.invoke(iUsbManager, printerUSBDev,
//					appInfo.uid);
//
//			System.out.println("Method OK : " + binder + "  " + iUsbManager);
//			return true;
//		} catch (Exception e) {
//			System.err
//					.println("Error trying to assing automatic usb permission : ");
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	public static boolean isChinese(char a) {
//		int v = (int) a;
//		return (v >= 19968 && v <= 171941);
//	}
//}
