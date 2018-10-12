package com.acewill.slefpos.print;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.widget.Toast;

import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 二代点餐机内置打印机
 */
public class PrintManager {
	private static final String TAG             = "PrintManager";
	public static        String toast_usbpermit = "Please permit app use usb and reclick this button";//隐藏
	public static  boolean isConnect;
	private static int     align;
	private static int     widthSize;
	private static int     heightSize;
	private static int textStyle = 0x00;


	private static PrintManager printManager;

	public static PrintManager getInstance() {
		if (printManager == null) {
			printManager = new PrintManager();
		}

		return printManager;
	}

	public static boolean init(Context context) {
		FileLog.log("初始化掃碼槍~");
		if (isConnect) {
			return true;
		}
		final UsbManager mUsbManager = (UsbManager) context
				.getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> deviceList     = mUsbManager.getDeviceList();
		Iterator<UsbDevice>        deviceIterator = deviceList.values().iterator();
		if (deviceList.size() > 0) {
			// 初始化选择对话框布局，并添加按钮和事件

			while (deviceIterator.hasNext()) { // 这里是if不是while，说明我只想支持一种device
				final UsbDevice device = deviceIterator.next();
				if (Common.PRINTER_PRODUCTID != null && !Common.PRINTER_PRODUCTID
						.equals(device.getProductId() + "")) {
					continue;
				}
				// Toast.makeText(
				// this,
				// "" + device.getDeviceId() + device.getDeviceName()
				// + device.toString(), Toast.LENGTH_LONG).show();

				PendingIntent mPermissionIntent = PendingIntent.getBroadcast(
						context, 0, new Intent(
								context.getApplicationInfo().packageName), 0);
				if (!mUsbManager.hasPermission(device)) {
					mUsbManager.requestPermission(device, mPermissionIntent);
					//					Toast.makeText(context, toast_usbpermit, Toast.LENGTH_LONG)
					//							.show();
					FileLog.log(Common.Log, PrintManager.class, "init", "log", "请求打印权限!");
					return false;
				} else {
					//					WorkService.workThread.connectUsb(mUsbManager, device);
					if (WorkService.workThread == null) {
						ToastUitl.showLong(context, "启动打印机失败");
						return false;
					}
					boolean connectOk = WorkService.workThread.connectUsb(mUsbManager,
							device);
					if (connectOk) {
						PrintManager.isConnect = true;
						isConnect = true;
						return true;
					} else {
						FileLog.log(Common.Log, PrintManager.class, "init", "log", "连接USB异常!");
						ToastUitl.showLong(context, "连接打印机异常，请检查!");
					}
					return false;
				}
			}
		}
		return false;
	}

	public static void size0() {
		widthSize = 0;
		heightSize = 0;
	}

	public static void size1() {
		widthSize = 1;
		heightSize = 1;
	}

	public static void left() {
		align = 0;
	}

	public static void center() {
		align = 1;
	}

	public static void right() {
		align = 2;
	}

	public static void normal() {
		textStyle = 0x00;
	}

	public static void bold() {
		textStyle = 0x08;
	}

	public static void printText(String text) {
		printFormatText(text, widthSize, heightSize, align);
	}

	public static void printlnText(String text) {
		printFormatText(text + "\n", widthSize, heightSize, align);
	}

	public static void printQr(String strQrcode) {
		// 内部命令，nWidthX表示单元宽度
		// nVersion表示模块版本，可以控制整体宽度。
		Bundle data = new Bundle();
		data.putString(Global.STRPARA1, strQrcode);
		data.putInt(Global.INTPARA1, 6);// 宽度控制单个模块宽度   nWidthX  2~6
		data.putInt(Global.INTPARA2, 3); // 版本控制模块数量   nVersion  0~16
		data.putInt(Global.INTPARA3, 4);//1~4   necl 这个一般不需要改
		//以上三个参数配错了不会打印二维码
		WorkService.workThread.handleCmd(
				Global.CMD_POS_SETQRCODE, data);
	}

	public static void PrintText(Context context, String str) {
		byte[] tmp1 = {0x1b, 0x40, (byte) 0xB2, (byte) 0xE2, (byte) 0xCA,
				(byte) 0xD4, (byte) 0xD2, (byte) 0xB3, 0x0A};
		byte[] tmp2 = {0x1b, 0x21, 0x01};
		byte[] tmp3 = {0x0A, 0x0A, 0x0A, 0x0A};
		byte[] buf = DataUtils.byteArraysToBytes(new byte[][]{tmp1,
				str.getBytes(), tmp2, str.getBytes(), tmp3});
		if (WorkService.workThread.isConnected()) {
			Bundle data = new Bundle();
			data.putByteArray(Global.BYTESPARA1, buf);
			data.putInt(Global.INTPARA1, 0);
			data.putInt(Global.INTPARA2, buf.length);
			WorkService.workThread.handleCmd(Global.CMD_WRITE, data);
		} else {
			Toast.makeText(context, Global.toast_notconnect, Toast.LENGTH_SHORT)
					.show();
		}
	}

	public static void printFormatText(String text) {
		printFormatText(text, 0, 0, PrinterThread.ALIGN_CENTER);
	}

	public static void printFormatText(String text, int align) {
		printFormatText(text, 0, 0, align);
	}

	public static void printFormatText(String text, int widthSize,
	                                   int heightSize, int align) {
		Bundle dataTextOut = new Bundle();

		dataTextOut.putString(Global.STRPARA1, text);// 需要打印的字符串
		dataTextOut.putString(Global.STRPARA2, "GBK");//
		// 指定 X 方向（水平）的起始点位置离左边界的点数。2寸打印机一行384点，3寸打印机一行576点。
		dataTextOut.putInt(Global.INTPARA1, 0);
		// 指定字符的宽度方向上的放大倍数。可以为0到 1。
		dataTextOut.putInt(Global.INTPARA2, widthSize);
		// 指定字符高度方向上的放大倍数。可以为0 到 1。
		dataTextOut.putInt(Global.INTPARA3, heightSize);
		// 指定字符的字体类型。 (0x00 标准ASCII 12x24) (0x01压缩ASCII 9x17)
		dataTextOut.putInt(Global.INTPARA4, 0x00);
		// 指定字符的字体风格。可以为以下列表中的一个或若干个。 (0x00 正常) (0x08 加粗) (0x80 1点粗的下划线)
		// (0x100 2点粗的下划线) (0x200 倒置、只在行首有效) (0x400 反显、黑底白字)
		// (0x1000 每个字符顺时针旋转90 度)
		dataTextOut.putInt(Global.INTPARA5, textStyle);
		dataTextOut.putInt(Global.INTPARA6, align);
		WorkService.workThread.handleCmd(Global.CMD_POS_STEXTOUT,
				dataTextOut);
	}

	public static void printPictrue(Bitmap bitmap) {
		if (WorkService.workThread.isConnected()) {
			Bundle data = new Bundle();
			// data.putParcelable(Global.OBJECT1, mBitmap);
			data.putParcelable(Global.PARCE1, bitmap);
			data.putInt(Global.INTPARA1, 200);// 384 576 832
			data.putInt(Global.INTPARA2, 0);
			WorkService.workThread.handleCmd(Global.CMD_POS_PRINTPICTURE,
					data);
		}
	}

	public static boolean isChinese(char a) {
		int v = (int) a;
		return (v >= 19968 && v <= 171941);
	}

	/**
	 * 走到切纸位置切纸
	 */
	public static void cut() {
		byte[] buf = new byte[]{0x1d, 0x56, 0x42, 0x00};

		if (WorkService.workThread.isConnected()) {
			Bundle data = new Bundle();
			data.putByteArray(Global.BYTESPARA1, buf);
			data.putInt(Global.INTPARA1, 0);
			data.putInt(Global.INTPARA2, buf.length);
			WorkService.workThread.handleCmd(Global.CMD_POS_WRITE, data);
		} else {
			System.out.println("printer: " + Global.toast_notconnect);
		}
	}

}
