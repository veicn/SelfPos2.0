package com.acewill.slefpos.system.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.ui.eventbus.ResultEvent;


public class SetDialog extends BaseDialog {
	/**
	 * @return
	 */
	public static SetDialog newInstance(String[] data, int requestcode) {
		SetDialog fragment = new SetDialog();
		Bundle    bundle   = new Bundle();
		bundle.putStringArray("data", data);
		bundle.putInt("requestcode", requestcode);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.activity_set_dialog, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		//		String[] data = getIntent().getStringArrayExtra("data");
		String[]     data = getArguments().getStringArray("data");
		LinearLayout ll   = (LinearLayout) view.findViewById(R.id.ll);
		int          i    = 0;
		for (String str : data) {

			View item_ll_set = View.inflate(mcontext,
					R.layout.item_ll_set, null);
			final TextView tv = (TextView) item_ll_set.findViewById(R.id.tv);
			tv.setText(str);
			tv.setId(i);
			i++;
			if (i == data.length) {
				item_ll_set.findViewById(R.id.line).setVisibility(View.GONE);
			}
			item_ll_set.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					//					getActivity()
					//							.setResult(Activity.RESULT_OK, new Intent().putExtra("position", tv.getId()));
					mRxManager.post(AppConstant.SETRESULT, new ResultEvent(getArguments()
							.getInt("requestcode"), tv.getId()));
					dismiss();
				}
			});
			ll.addView(item_ll_set);
		}

		view.findViewById(R.id.tv_cancer).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	@Override
	public float getSize() {
		return 0.6f;
	}
}
