package com.jaydenxiao.common.commonwidget;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Author：Anch
 * Date：2017/4/27 15:47
 * Desc：
 */
public class BannerImageLoaderLocalResource extends ImageLoader {
	@Override
	public void displayImage(Context context, Object path, ImageView imageView) {
		int resourceId = (int) path;
		imageView.setImageResource(resourceId);
	}
}
