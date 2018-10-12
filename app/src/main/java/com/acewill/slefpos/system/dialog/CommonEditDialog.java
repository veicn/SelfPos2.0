package com.acewill.slefpos.system.dialog;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.dialog.BaseDialog;

/**
 * Author：Anch
 * Date：2017/9/8 17:40
 * Desc：
 */
public class CommonEditDialog extends BaseDialog {
	/**
	 * @return
	 */
	public static CommonEditDialog newInstance(boolean flag) {
		CommonEditDialog fragment = new CommonEditDialog();
		Bundle           bundle   = new Bundle();
		bundle.putBoolean("flag", flag);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View           view    = View.inflate(mcontext, R.layout.dialog_common_input, null);
		final Button   btn_yes = (Button) view.findViewById(R.id.btn_yes);
		final EditText et_code = (EditText) view.findViewById(R.id.et_code);
		et_code.setInputType(InputType.TYPE_CLASS_TEXT);
		final boolean flag = getArguments().getBoolean("flag");
		if (!flag) {
			((TextView) view.findViewById(R.id.tv_title))
					.setText(getResources().getString(R.string.un_bind));
			btn_yes.setText(getResources().getString(R.string.un_bind2));
			et_code.setVisibility(View.GONE);
		}
		btn_yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (flag) {
					mYesClickListener.onYesClick(et_code.getText().toString());
				} else {
					mNoClickListener.onNoClick();
				}
			}
		});
		view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		return view;
	}

	private OnNoClickListener  mNoClickListener;
	private OnYesClickListener mYesClickListener;
	public void setOnYesClickListener(OnYesClickListener listener) {
		this.mYesClickListener = listener;

	}public interface OnYesClickListener {
		void onYesClick(String s);
	}


	public void setOnNoClickListener(OnNoClickListener listener) {
		this.mNoClickListener = listener;
	}

	public interface OnNoClickListener {
		void onNoClick();
	}


	@Override
	public float getSize() {
		return 0.7f;
	}
}