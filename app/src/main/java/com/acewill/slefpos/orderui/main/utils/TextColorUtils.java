package com.acewill.slefpos.orderui.main.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

/**
 * Author：Anch
 * Date：2018/6/7 15:56
 * Desc：
 */
public class TextColorUtils {
	public static void setLeftTextColorAndSize(String leftText, String rightText, TextView textView) {
		SpannableStringBuilder style = new SpannableStringBuilder(leftText + rightText);
		style.setSpan(new RelativeSizeSpan(1.5f), 0, leftText
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		style.setSpan(new ForegroundColorSpan(Color.RED), 0, leftText
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		textView.setText(style);
	}


	public static void setDishPrice(String leftText, String rightText, TextView textView) {
		SpannableStringBuilder style = new SpannableStringBuilder(leftText + rightText);
		style.setSpan(new RelativeSizeSpan(1.5f), 1, leftText
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		style.setSpan(new ForegroundColorSpan(Color.RED), 0, leftText
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		textView.setText(style);
	}

	public static void setMemberPrice(String leftText, String rightText, TextView textView) {
		SpannableStringBuilder style = new SpannableStringBuilder(leftText + rightText);
		style.setSpan(new RelativeSizeSpan(1.5f), 1, leftText
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		style.setSpan(new ForegroundColorSpan(Color.RED), 0, leftText
				.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		textView.setText(style);
	}
}
