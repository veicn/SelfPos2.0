package com.acewill.slefpos.printer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.acewill.slefpos.printer.vendor.BluetoothPrinter;
import com.acewill.slefpos.printer.vendor.EpsonPrinter;
import com.acewill.slefpos.printer.vendor.WifiPrinter;
import com.acewill.slefpos.printer.vendor.YptPrinter;

/**
 * Created by linmingren on 16/8/3.
 */
public class PrinterFactory {
	public static PrinterInterface createPrinter(PrinterVendor type, String ip,
			PrinterWidth width) {
		if (type == PrinterVendor.EPSON) {
			return new EpsonPrinter(ip, width);
		} else if (type == PrinterVendor.YPT) {
			return new YptPrinter(ip, width);
		} else {
			// 找不到对应的品牌，
			return new WifiPrinter(ip, width == PrinterWidth.WIDTH_80MM ? 42
					: 32);
		}
	}

	public static PrinterInterface createBluetoothPrinter(PrinterVendor type,
			String name, PrinterWidth width) {

		// return new BluetoothPrinter(name, width);
		for (final BluetoothDevice device : BluetoothAdapter
				.getDefaultAdapter().getBondedDevices()) {
			if (device.getAddress().equals("00:11:22:33:44:55")) {
				return new BluetoothPrinter(device, width);
			}
		}
		return null;
	}
}
