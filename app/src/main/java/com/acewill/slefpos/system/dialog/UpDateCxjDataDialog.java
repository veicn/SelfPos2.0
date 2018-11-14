package com.acewill.slefpos.system.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.dialog.BaseDialog;

/**
 * Created by Administrator on 2018/11/13 0013.
 */

public class UpDateCxjDataDialog extends BaseDialog implements View.OnClickListener {

	private TextView tv_title;
	private TextView tv_content;
	private int      type;
	private Button   btn_yes;
	private Button   btn_cancel;
	;

	public static UpDateCxjDataDialog getInstance(int type) {
		UpDateCxjDataDialog dialog = new UpDateCxjDataDialog();
		return dialog;
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.activity_updatecxjdata_dialog, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_content = (TextView) view.findViewById(R.id.tv_content);

		btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(this);
		btn_yes = (Button) view.findViewById(R.id.btn_yes);
		btn_yes.setOnClickListener(this);
		tv_content.setVisibility(View.GONE);
		//		type = getArguments().getInt("type");
		//		switch (type) {
		//			case 0://是否更新餐行健菜品数据？
		//				tv_title.setText("是否更新菜品数据?");
		//				break;
		//			case 1://是否已经删除服务器上的db?

		//				break;
		//		}
	}

	public void changeContent() {
		type = 1;
		tv_content.setVisibility(View.VISIBLE);
		tv_title.setText("是否已经删除服务器上的数据库文件?");
		tv_content.setText("路径> D:xampp/acewill/sync/sqlite/basicMenuData.db");
		btn_cancel.setText("未删除");
		btn_yes.setText("已删除");
	}

	@Override
	public float getSize() {
		return 0.5f;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_yes:
				switch (type) {
					case 0:
						changeContent();
						break;
					case 1:
						if (mYesClickListener != null)
							mYesClickListener.onYesClick();
						dismiss();
						break;
				}
				break;
			case R.id.btn_cancel:
				if (mYesClickListener != null)
					mYesClickListener.onCancle();
				dismiss();
				break;
		}
	}

	private OnYesClickListener mYesClickListener;

	public void setOnYesClickListener(OnYesClickListener listener) {
		this.mYesClickListener = listener;

	}

	public interface OnYesClickListener {
		void onYesClick();

		void onCancle();
	}


}
