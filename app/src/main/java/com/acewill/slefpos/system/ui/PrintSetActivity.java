package com.acewill.slefpos.system.ui;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.acewill.slefpos.R;
import com.acewill.slefpos.print.DataUtils;
import com.acewill.slefpos.print.Global;
import com.acewill.slefpos.print.PrintManager;
import com.acewill.slefpos.print.WorkService;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PrintSetActivity extends Activity implements OnClickListener {
	private Iterator<UsbDevice>        deviceIterator;
	private HashMap<String, UsbDevice> deviceList;
	private UsbManager                 mUsbManager;
	private ArrayList<String>          keyList;
	private String                     productId;
	private Context                    context;
	private MHandler                   mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_printset);
		context = this;
		ListView lv = (ListView) findViewById(R.id.lv);
		findViewById(R.id.back).setOnClickListener(this);
		findViewById(R.id.save).setOnClickListener(this);
		mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
		deviceList = mUsbManager.getDeviceList();
		deviceIterator = deviceList.values().iterator();
		keyList = new ArrayList<String>();
		for (String string : deviceList.keySet()) {
			keyList.add(string);
		}
		lv.setAdapter(new MyAdapter());
		mHandler = new MHandler(this);
		WorkService.addHandler(mHandler);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.save:
				if (!TextUtils.isEmpty(productId)) {
					SPUtils.setSharedStringData(this, "productId", productId);
					setResult(RESULT_OK, new Intent().putExtra("productId", productId));
				}
				WorkService.delHandler(mHandler);
				mHandler = null;
				finish();
			case R.id.back:
				finish();
				break;
		}
	}


	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return deviceList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final UsbDevice device = deviceList.get(keyList.get(position));


			View           inflate = View.inflate(context, R.layout.item_ll_set, null);
			final TextView tv      = (TextView) inflate.findViewById(R.id.tv);
			tv.setText(device.getProductId() + "");
			if (position == deviceList.size() - 1) {
				inflate.findViewById(R.id.line).setVisibility(View.GONE);
			}

			tv.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub

					PendingIntent mPermissionIntent = PendingIntent
							.getBroadcast(
									PrintSetActivity.this,
									0,
									new Intent(PrintSetActivity.this
											.getApplicationInfo().packageName),
									0);
					if (!mUsbManager.hasPermission(device)) {
						mUsbManager
								.requestPermission(device, mPermissionIntent);
					} else {
						boolean connectOk = WorkService.workThread.connectUsb(mUsbManager,
								device);
						if (connectOk) {
							PrintManager.isConnect = true;
							productId = tv.getText().toString();
						} else {
							ToastUitl.showShort(PrintSetActivity.this, "连接打印机异常，请检查!");
						}
					}
				}
			});
			return inflate;
		}

	}

	static class MHandler extends Handler {

		WeakReference<PrintSetActivity> mActivity;

		MHandler(PrintSetActivity activity) {
			mActivity = new WeakReference<PrintSetActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			PrintSetActivity theActivity = mActivity.get();
			if (theActivity == null)
				return;
			switch (msg.what) {
				/**
				 * DrawerService 的 onStartCommand会发送这个消息
				 */
				case Global.MSG_WORKTHREAD_SEND_CONNECTUSBRESULT: {
					int result = msg.arg1;
					Toast.makeText(
							theActivity,
							(result == 1) ? Global.toast_success
									: Global.toast_fail, Toast.LENGTH_SHORT).show();
					if (1 == result) {
						PrintTest();
						PrintManager.cut();
					}
					break;
				}

			}
		}
	}

	static void PrintTest() {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\n0123456789\n";
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
		}
	}
}
