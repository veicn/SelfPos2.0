package com.acewill.slefpos.orderui.main.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.jaydenxiao.common.commonutils.ToastUitl;


/**
 * Author：Anch
 * Date：2017/12/30 15:21
 * Desc：
 */
public class NumberInputView extends LinearLayout implements View.OnClickListener {

	private int    mType;
	private String mTipsText;

	public NumberInputView(Context context) {
		super(context, null);
	}

	private TextView tv_table;
	private Context  mContext;

	public NumberInputView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_table_no2, this, true);
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
		view.findViewById(R.id.tv_ok).setOnClickListener(this);
		view.findViewById(R.id.tv_delete).setOnClickListener(this);
		tv_table = (TextView) view.findViewById(R.id.tv_table);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberInputView);
		mTipsText = a.getString(R.styleable.NumberInputView_tips_text);
		mType = a.getInt(R.styleable.NumberInputView_type, 0);
		tv_table.setText(mTipsText);
		a.recycle();
		//		setTipsHint();
	}

	private void setTableNo(String no) {
		tv_table.setTextColor(Color.rgb(00, 00, 00));
		tableNo += no;
		tv_table.setText(tableNo);
	}

	private String tableNo = "";


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
			case R.id.tv_delete:
				if (!TextUtils.isEmpty(tableNo)) {
					tableNo = tableNo.substring(0, tableNo.length() - 1);
					if (TextUtils.isEmpty(tableNo)) {
						tv_table.setText(mTipsText);
					} else {
						tv_table.setText(tableNo);
					}
				}
				break;
			case R.id.tv_ok:
				if (TextUtils.isEmpty(tableNo)) {
					ToastUitl.showLong(mContext, "输入内容不能为空!");
					return;
				}
				if (mType == 0)
					AppApplication.getRxManager().post(AppConstant.MEMBERID, tableNo);
				else if (mType == 1)
					AppApplication.getRxManager().post(AppConstant.MEITUANYANQUAN, tableNo);
				break;
			case R.id.iv_cancer:
				break;
			default:
				break;
		}
	}


}
