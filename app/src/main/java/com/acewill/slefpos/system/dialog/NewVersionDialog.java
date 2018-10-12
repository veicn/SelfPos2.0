package com.acewill.slefpos.system.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.dialog.BaseDialog;

/**
 * Author：Anch
 * Date：2018/1/4 10:24
 * Desc：
 */
public class NewVersionDialog extends BaseDialog {


	/**
	 * @return
	 */
	public static NewVersionDialog newInstance(String version,String description) {
		NewVersionDialog fragment = new NewVersionDialog();
		Bundle           bundle   = new Bundle();
		bundle.putString("version", version);
		bundle.putString("description", description);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View   view = View.inflate(mcontext, R.layout.dialog_newversion, null);
		String version  = getArguments().getString("version");
		String description  = getArguments().getString("description");
		view.findViewById(R.id.cancer).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
		view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				if (mYesClickListener!=null){
					mYesClickListener.onYesClick();
				}
			}
		});
		((TextView) view.findViewById(R.id.tv_version)).setText("新版本 V "+version);
		((TextView) view.findViewById(R.id.tv_description)).setText(description);
		return view;
	}

	private OnYesClickListener mYesClickListener;

	public void setOnYesClickListener(OnYesClickListener listener) {
		this.mYesClickListener = listener;

	}

	public interface OnYesClickListener {
		void onYesClick();
	}

	@Override
	public float getSize() {
		return 0.6f;
	}
}
