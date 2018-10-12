package com.acewill.slefpos.widgetlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Author：Anch
 * Date：2017/5/22 15:58
 * Desc：
 */
public class AlwaysMarqueeTextView extends TextView{

	public AlwaysMarqueeTextView(Context context) {
		super(context);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}}