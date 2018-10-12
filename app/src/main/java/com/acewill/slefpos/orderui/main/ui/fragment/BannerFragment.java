package com.acewill.slefpos.orderui.main.ui.fragment;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.base.BaseFragment;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.smarantstorebean.ImageEntity;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.jaydenxiao.common.commonwidget.BannerImageLoader;
import com.jaydenxiao.common.commonwidget.BannerImageLoaderLocalResource;
import com.jaydenxiao.common.commonwidget.NewBanner;
import com.jaydenxiao.common.compressorutils.FileUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
 *  @项目名：  diancan-diancan2.0
 *  @包名：    com.acewill.ordermachine.fragment_new
 *  @文件名:   BannerFragment
 *  @创建者:   Administrator
 *  @创建时间:  2017/4/20 0020 下午 16:22
 *  @描述：    TODO
 */
public class BannerFragment
		extends BaseFragment {
	private NewBanner banner;


	@Bind(R.id.member_info_ly)
	RelativeLayout member_info_ly;
	@Bind(R.id.member_name_tv)
	TextView       member_name_tv;

	@Override
	protected int getLayoutResource() {
		return R.layout.ad_banner;
	}

	@Override
	public void initPresenter() {

	}


	@Override
	protected void initView() {
		initBanner();
		initMemberInfo();
	}

	private void initMemberInfo() {
		member_info_ly.setVisibility(Order.getInstance().isMember() ? View.VISIBLE : View.GONE);

		if (Order.getInstance().isMember()) {
			String        welName = "欢迎  ";
			StringBuilder builder = new StringBuilder();
			builder.append(welName);
			if ((SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) && WshDataProvider
					.getWshAccount() != null) {
				if (!TextUtils.isEmpty(WshDataProvider.getWshAccount().getName())) {
					builder.append(WshDataProvider.getWshAccount().getName());
				} else {
					builder.append("尊贵的会员");
				}
			} else if (SystemConfig.isSyncSystem && SyncDataProvider
					.getSyncMemberAccount() != null) {
				if (!TextUtils.isEmpty(SyncDataProvider.getSyncMemberAccount().getMemberName())) {
					builder.append(SyncDataProvider.getSyncMemberAccount().getMemberName());
				} else {
					builder.append("尊贵的会员");
				}
			}
			SpannableStringBuilder style = new SpannableStringBuilder(builder.toString());
			style.setSpan(new RelativeSizeSpan(1.5f), welName.length(), builder.toString()
					.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			style.setSpan(new ForegroundColorSpan(getResources()
					.getColor(R.color.colorPrimary)), welName.length(), builder.toString()
					.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			member_name_tv.setText(style);
		}
	}

	/**
	 * 上移和下移的操作，最好的结果是，列表移动多少，它就移动多少
	 */
	/**
	 * 菜单显示隐藏动画
	 */


	private void initBanner() {


		//		if (SmarantDataProvider.getImageList() == null)
		//			return;
		banner = (NewBanner) rootView.findViewById(R.id.ad_banner);
		//设置图片加载器

		//设置图片集合
		//        List<Integer> images = new ArrayList<>();
		//        images.add("http://img.taopic.com/uploads/allimg/130828/240425-130RPQ94762.jpg");
		//        images.add("http://img.taopic.com/uploads/allimg/140124/240509-14012410000254.jpg");
		//        images.add("http://img.taopic.com/uploads/allimg/121020/240425-12102019510784.jpg");
		//        images.add("http://img1.3lian.com/img013/v2/74/d/45.jpg");
		List<String> imagesList = new ArrayList<>();
		//		SmarantDataProvider.getImageList().getFiles()
		if ((SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) && SmarantDataProvider
				.getImageList() != null) {
			for (ImageEntity entity : SmarantDataProvider.getImageList().getFiles()) {
				if ("AD_IMAGE".equals(entity.getFiletypeKey()))
					imagesList.add(entity.getFilename());
			}
		} else if (SystemConfig.isSyncSystem) {
			List<String> image = FileUtil.getSyncImageList("AD_IMAGE");
			if (image != null)
				imagesList.addAll(image);
		}

		if (imagesList != null && imagesList.size() > 0) {
			banner.setImageLoader(new BannerImageLoader(mContext
					.getResources().getDrawable(R.mipmap.default_img)));
			banner.setImages(imagesList);
		} else {
			//            images.ad
			banner.setImageLoader(new BannerImageLoaderLocalResource());
			List<Integer> images = new ArrayList<>();
			images.add(R.mipmap.default_img);
			banner.setImages(images);
		}
		//banner设置方法全部调用完毕时最后调用
		banner.setIndicatorVisible(View.GONE);
		banner.start();
	}

}
