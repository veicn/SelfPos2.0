package com.acewill.slefpos.orderui.main.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIRecommand;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.ui.eventbus.RecommandEvent;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Author：Anch
 * Date：2017/5/11 10:52
 * Desc：
 */
public class RecomendPackageDialog extends BaseDialog implements View.OnClickListener {
	private UIDish                                dishModel;
	private IRecyclerView                         xre;
	private List<UIRecommand>                     beanList;
	private ImageView                             dishImageViewIv;
	private TextView                              dishNameTv;
	private TextView                              dishPriceTv;
	private TextView                              typeTv;
	private TextView                              showTypeTv;
	private CommonRecycleViewAdapter<UIRecommand> adapter;

	private ImageView imageView;

	/**
	 * @return
	 */
	public static RecomendPackageDialog newInstance(UIDish dishModel) {
		RecomendPackageDialog fragment = new RecomendPackageDialog();
		Bundle                bundle   = new Bundle();
		bundle.putSerializable("dishModel", dishModel);
		fragment.setArguments(bundle);
		return fragment;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	private void initView(View view) {

		init(view);
		initAdapter();
	}

	private void init(View view) {
		dishModel = (UIDish) getArguments().getSerializable("dishModel");
		this.beanList = dishModel.getRecommandList();
		xre = (IRecyclerView) view.findViewById(R.id.dialog_recomendpackage_xre);
		dishImageViewIv = (ImageView) view.findViewById(R.id.dish_photo);
		dishNameTv = (TextView) view.findViewById(R.id.dish_name);
		dishPriceTv = (TextView) view.findViewById(R.id.dish_price);

		String currentPrice = Order.getInstance().isMember() ? dishModel
				.getMemberPrice() : dishModel
				.getPrice();
		dishPriceTv.setText("￥" + currentPrice);
		typeTv = (TextView) view.findViewById(R.id.dialog_recomendpackage_type_tv);
		showTypeTv = (TextView) view.findViewById(R.id.dialog_recomendpackage_showtype_tv);
		typeTv.setText("我们推荐");
		showTypeTv.setText("套餐推荐");
		view.findViewById(R.id.dialog_recomendpackage_dishlayout).setOnClickListener(this);
		view.findViewById(R.id.dialog_recomendpackage_close_back).setOnClickListener(this);
		dishNameTv.setText(dishModel.getDishName());
		Glide.with(mcontext).load(dishModel.getImageName())
				.error(R.mipmap.default_img).into(dishImageViewIv);

	}

	private void initAdapter() {
		adapter = new CommonRecycleViewAdapter<UIRecommand>(getActivity(), R.layout.adapterview_recomendpackage) {
			@Override
			public void convert(ViewHolderHelper helper, final UIRecommand recommand) {
				ImageView dish_photo = helper.getView(R.id.dish_photo);
				TextView  dish_name  = helper.getView(R.id.dish_name);
				TextView  dish_price = helper.getView(R.id.dish_price);
				dish_name.setText(recommand.getPackageName());
				dish_price.setText("￥" + recommand.getPrice());
				Glide.with(mContext).load(recommand.getImageName()).crossFade().fitCenter()
						.placeholder(R.mipmap.default_img).error(R.mipmap.default_img)
						.into(dish_photo);
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						TimeConfigure.resetScreenTime();
						mRxManager.post(AppConstant.START_PACKAGEFRAGMENT, new RecommandEvent(recommand,imageView));
						dismiss();
					}
				});
			}
		};

		xre.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false));
		xre.setRefreshEnabled(false);
		xre.setLoadMoreEnabled(false);
		adapter.addAll(beanList);
		xre.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.dialog_recomendpackage_dishlayout:
				//在判断是不是 单选 单选是否有定制项

				//这个时候不要关闭 ，因为他们还没有选完
				if (dishModel.getOptionCategoryList() != null && dishModel.getOptionCategoryList()
						.size() > 0) {
					Fragment dialog1 = getFragmentManager()
							.findFragmentByTag("SmarantOptionDialog");
					if (dialog1 != null)
						return;
					UIDish cloneDish = (UIDish) dishModel.myclone();
					OptionDialog dialog = OptionDialog
							.newInstance(cloneDish, null, false, false);
					dialog.show(getFragmentManager(), "SmarantOptionDialog");
				}else{
					Cart.getInstance().addDish(dishModel);
					mRxManager.post(AppConstant.DO_ADD_DISH_ANIMATION, imageView);
				}
				dismiss();
				break;
			case R.id.dialog_recomendpackage_close_back:
				dismiss();
				break;
		}
	}

	@Override
	public View getView() {
		View view = View.inflate(mcontext, R.layout.dialog_recomendpackage, null);
		initView(view);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Dialog dialog = getDialog();
		if (dialog != null) {
			DisplayMetrics dm = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
			dialog.getWindow()
					.setLayout((int) (dm.widthPixels * getSize()), ((int) (dm.heightPixels * 0.5)));
		}
	}

	@Override
	public float getSize() {
		return 0.7f;
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
	}

}
