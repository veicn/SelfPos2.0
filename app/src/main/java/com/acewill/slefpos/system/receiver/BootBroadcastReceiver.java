package com.acewill.slefpos.system.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.acewill.slefpos.orderui.main.ui.activity.SplashActivity;

/**
 *开机启动 
 *
 */
public class BootBroadcastReceiver extends BroadcastReceiver{
	static final String action_boot="android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(action_boot)) {
            Intent ootStartIntent = new Intent(context, SplashActivity.class);
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(ootStartIntent);
        }
	}

}
