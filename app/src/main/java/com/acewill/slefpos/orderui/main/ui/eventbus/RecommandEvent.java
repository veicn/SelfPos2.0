package com.acewill.slefpos.orderui.main.ui.eventbus;

import android.widget.ImageView;

import com.acewill.slefpos.bean.uibean.UIRecommand;

/**
 * Author：Anch
 * Date：2018/5/25 17:58
 * Desc：
 */
public class RecommandEvent {
	private UIRecommand mRecommand;
	private ImageView   mImageView;

	public RecommandEvent(UIRecommand recommand, ImageView imageView) {
		this.mRecommand = recommand;
		this.mImageView = imageView;
	}

	public UIRecommand getRecommand() {
		return mRecommand;
	}

	public void setRecommand(UIRecommand recommand) {
		mRecommand = recommand;
	}

	public ImageView getImageView() {
		return mImageView;
	}

	public void setImageView(ImageView imageView) {
		mImageView = imageView;
	}
}
