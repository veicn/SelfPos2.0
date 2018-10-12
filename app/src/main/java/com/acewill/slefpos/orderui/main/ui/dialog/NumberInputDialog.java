package com.acewill.slefpos.orderui.main.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.jaydenxiao.common.commonutils.ToastUitl;

public class NumberInputDialog extends BaseDialog implements
		View.OnClickListener {

	private TextView tv_table;
	private String tableNo = "";
	OnConfirmListener listener;
	private int            type;
	private TextView       tv_skip;
	private OnSkipListener skipListener;
	private TextView       tv_title;
	private TextView       tv_tips;

	/**
	 * @return
	 */
	public static NumberInputDialog newInstance(int type) {
		NumberInputDialog fragment = new NumberInputDialog();
		Bundle            bundle   = new Bundle();
		bundle.putInt("type", type);
		fragment.setArguments(bundle);
		return fragment;
	}


	private void initView(View view) {
		type = getArguments().getInt("type");
		view.findViewById(R.id.tv_0).setOnClickListener(this);
		view.findViewById(R.id.tv_1).setOnClickListener(this);
		view.findViewById(R.id.tv_2).setOnClickListener(this);
		view.findViewById(R.id.tv_3).setOnClickListener(this);
		view.findViewById(R.id.tv_4).setOnClickListener(this);
		view.findViewById(R.id.tv_5).setOnClickListener(this);
		view.findViewById(R.id.tv_6).setOnClickListener(this);
		view.findViewById(R.id.tv_7).setOnClickListener(this);
		view.findViewById(R.id.tv_8).setOnClickListener(this);
		view.findViewById(R.id.tv_9).setOnClickListener(this);
		view.findViewById(R.id.tv_c).setOnClickListener(this);
		view.findViewById(R.id.tv_delete).setOnClickListener(this);
		view.findViewById(R.id.tv_ok).setOnClickListener(this);
		view.findViewById(R.id.iv_cancer).setOnClickListener(this);
		tv_table = (TextView) view.findViewById(R.id.tv_table);
		tv_table.clearFocus();
		tv_skip = (TextView) view.findViewById(R.id.tv_skip);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_tips = (TextView) view.findViewById(R.id.tv_tips);
		tv_skip.setOnClickListener(this);
		tv_title.setText(getResources()
				.getString(R.string.input_table_num) + "(" + SmarantDataProvider
				.getSelfposConfigurationdata().getContent()
				.getMinTable() + "-" + SmarantDataProvider.getSelfposConfigurationdata()
				.getContent().getMaxTable() + ")");
		tv_tips.setText("(输入餐牌号与实际的不符将影响服务员送餐!)");
		tv_skip.setVisibility(View.GONE);
		tv_table.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		TimeConfigure.resetScreenTime();
		switch (v.getId()) {
			case R.id.tv_0:
				setTableNo("0");
				break;
			case R.id.tv_1:
				setTableNo("1");
				break;
			case R.id.tv_2:
				setTableNo("2");
				break;
			case R.id.tv_3:
				setTableNo("3");
				break;
			case R.id.tv_4:
				setTableNo("4");
				break;
			case R.id.tv_5:
				setTableNo("5");
				break;
			case R.id.tv_6:
				setTableNo("6");
				break;
			case R.id.tv_7:
				setTableNo("7");
				break;
			case R.id.tv_8:
				setTableNo("8");
				break;
			case R.id.tv_9:
				setTableNo("9");
				break;
			case R.id.tv_c:
				tableNo = "";
				tv_table.setText("");
				break;
			case R.id.tv_delete:
				if (!TextUtils.isEmpty(tableNo)) {
					tableNo = tableNo.substring(0, tableNo.length() - 1);
					if (TextUtils.isEmpty(tableNo)) {
						tv_table.setText("");
					} else {
						tv_table.setText(tableNo);
					}
				}
				break;
			case R.id.tv_ok:
				if (TextUtils.isEmpty(tableNo)) {
					ToastUitl.showShort(mcontext, "输入内容不能为空!");
					return;
				}

				if (type == 0) {
					if (listener != null) {
						//判断是否在这个范围
						double i = Double.parseDouble(tableNo);
						if (i >= SmarantDataProvider.getSelfposConfigurationdata().getContent()
								.getMinTable() && i <= SmarantDataProvider
								.getSelfposConfigurationdata().getContent()
								.getMaxTable()) {
							listener.onConfirm(tableNo);
							dismiss();
						} else {
							ToastUitl
									.showShort(mcontext, "桌台号输入范围错误，请重新输入");
						}
					}
				} else if (type == 1) {
					listener.onConfirm(tableNo);
				}


				break;

			case R.id.iv_cancer:
				//				dismiss();
				if (mOnCancleListener != null) {
					mOnCancleListener.onCancle();
				}
				break;
			case R.id.tv_skip:
				if (skipListener != null) {
					skipListener.onSkip();
				}
				break;

			default:
				break;
		}
	}

	private void setTableNo(String no) {
		tableNo += no;
		tv_table.setText(tableNo);

	}

	private OnCancleListener mOnCancleListener;

	public void setOnCancleListener(OnCancleListener listener) {
		this.mOnCancleListener = listener;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_table_no, null);
		initView(view);
		return view;
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

	@Override
	public void onStart() {
		super.onStart();
		Dialog dialog = getDialog();
		if (dialog != null) {
			DisplayMetrics dm = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			dialog.getWindow()
					.setLayout((int) (dm.widthPixels * getSize()), (int) (dm.heightPixels * 0.53f));
		}
	}

	public interface OnCancleListener {
		void onCancle();
	}


	public void setOnComfirmListener(OnConfirmListener listener) {
		this.listener = listener;
	}

	public interface OnConfirmListener {
		void onConfirm(String tableNo);
	}

	private OnQrImgClickListener mQrImgClickListener;

	public void setOnQrImgClickListener(OnQrImgClickListener listener) {
		this.mQrImgClickListener = listener;
	}

	public interface OnQrImgClickListener {
		void onQrCodeClick();
	}

	public void setOnSkipListener(OnSkipListener listener) {
		this.skipListener = listener;
	}

	public interface OnSkipListener {
		void onSkip();
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
		if (mOnDialogDismissListener != null)
			mOnDialogDismissListener.onDialogDismiss();
	}

	public interface OnDialogDismissListener {
		void onDialogDismiss();
	}

	public OnDialogDismissListener mOnDialogDismissListener;

	public void setOnDialogDismiss(OnDialogDismissListener listener) {
		mOnDialogDismissListener = listener;
	}

}
