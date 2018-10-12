package com.acewill.slefpos.system.dialog;

import android.os.Bundle;
import android.view.View;

import com.acewill.slefpos.R;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.widgetlibrary.RingProgressBar;

/**
 * Author：Anch
 * Date：2018/1/4 10:35
 * Desc：
 */
public class DownLoadProgressDialog extends BaseDialog {
	/**
	 * @return
	 */
	public static DownLoadProgressDialog newInstance(int max) {
		DownLoadProgressDialog fragment = new DownLoadProgressDialog();
		Bundle        bundle   = new Bundle();
		bundle.putInt("max", max);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_downloadprogress_layout, null);
		initView(view);
		return view;
	}

	private RingProgressBar mRingProgressBar2;

	private void initView(View view) {
		int max = getArguments().getInt("max");
		mRingProgressBar2 = (RingProgressBar) view.findViewById(R.id.progress_bar_2);
		mRingProgressBar2.setMax(max);
		setCancelable(false);
	}

	public void setProgress(int progress) {
		mRingProgressBar2.setProgress(progress);
	}

	@Override
	public float getSize() {
		return 0.5f;
	}
}
