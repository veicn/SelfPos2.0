package com.jaydenxiao.common.commonwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/*
 *  @项目名：  diancan-diancan2.0 
 *  @包名：    com.acewill.ordermachine.util_new
 *  @文件名:   BannerImageLoader
 *  @创建者:   Administrator
 *  @创建时间:  2017/4/20 0020 上午 9:05
 *  @描述：    TODO
*/
public class BannerImageLoader extends ImageLoader {


	private final Drawable placeLoaderRes;

	public BannerImageLoader(Drawable placeLoaderRes){
		this.placeLoaderRes = placeLoaderRes;
	}
	@Override
	public void displayImage(Context context, Object path, ImageView imageView) {
		/**
		 注意：
		 1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
		 2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
		 传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
		 切记不要胡乱强转！
		 */

		//Picasso 加载图片简单用法
//		Picasso.with(context).load((String) path).into(imageView);

//		Glide.with(context).load(path).placeholder(placeLoaderRes).error(placeLoaderRes).into(imageView);
		Glide.with(context).load(path).into(imageView);
		//用fresco加载图片简单用法，记得要写下面的createImageView方法
		//        Uri uri = Uri.parse((String) path);
		//        imageView.setImageURI(uri);
	}

}
