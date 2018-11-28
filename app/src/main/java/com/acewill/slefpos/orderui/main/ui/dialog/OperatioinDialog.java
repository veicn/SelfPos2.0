package com.acewill.slefpos.orderui.main.ui.dialog;

import android.view.View;

import com.acewill.slefpos.R;
import com.acewill.slefpos.dialog.BaseDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/27 0027.
 */

public class OperatioinDialog extends BaseDialog {

	@OnClick(R.id.printticket)
	public void printTicket() {
		showPrintOrderListDialog();
	}

	private void showPrintOrderListDialog() {

	}

	boolean isPrepare;
	View    view;

	@Override
	public View getView() {


		if (!isPrepare) {
			view = View.inflate(mcontext, R.layout.dialog_operation, null);
			ButterKnife.bind(this, view);
			initData();
			isPrepare = true;
		}
		return view;
	}

	private void initData() {

	}

	private void initView(View view) {

	}

	@Override
	public float getSize() {
		return 0.7f;
	}
}
