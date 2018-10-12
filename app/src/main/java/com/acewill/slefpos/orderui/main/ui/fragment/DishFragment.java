package com.acewill.slefpos.orderui.main.ui.fragment;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Fade;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseFragment;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIDishKind;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.trasition.DetailsTransition;
import com.acewill.slefpos.orderui.main.ui.dialog.RecomendPackageDialog;
import com.acewill.slefpos.orderui.main.ui.dialog.OptionDialog;
import com.acewill.slefpos.orderui.main.ui.eventbus.RecommandEvent;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.orderui.main.utils.TextColorUtils;
import com.acewill.slefpos.utils.uiutils.CircularAnimUtil;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingTip;

import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/2/1 11:46
 * Desc：
 */
public class DishFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener {
	@Bind(R.id.irc)
	IRecyclerView  irc;
	@Bind(R.id.loadedTip)
	LoadingTip     loadedTip;
	@Bind(R.id.fra_dish_layout)
	RelativeLayout fra_dish_layout;
	private CommonRecycleViewAdapter<UIDish> adapter;

	@Override
	protected int getLayoutResource() {
		return R.layout.fra_dish;
	}

	@Override
	public void initPresenter() {

	}

	/**
	 * 向四周扩散的动画
	 */
	private void excuteAnimation(View triggerView) {
		CircularAnimUtil
				.show(triggerView, fra_dish_layout, 0, 618);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	protected void initView() {
		registKindClickListener();


		adapter = new CommonRecycleViewAdapter<UIDish>(getContext(), R.layout.item_dish) {

			@Override
			public void convert(ViewHolderHelper helper, final UIDish dish) {
				final ImageView imageView            = helper.getView(R.id.dish_photo);
				final ImageView statu_iv             = helper.getView(R.id.statu_iv);
				final TextView  dish_price           = helper.getView(R.id.dish_price);
				final TextView  dish_memberprice     = helper.getView(R.id.dish_memberprice);
				final TextView  dish_memberprice_vip = helper.getView(R.id.dish_memberprice_vip);
				final View      selectView           = helper.getView(R.id.view);
				final FrameLayout order_right_shouqinlayout = helper
						.getView(R.id.order_right_shouqinlayout);
				TextColorUtils.setLeftTextColorAndSize("￥" + dish.getPrice(), "/" + dish
						.getDishUnit(), dish_price);
				//				dish_price.setText("￥" + dish.getPrice()+);
				if ((SystemConfig.isSmarantSystem||SystemConfig.isCanXingJianSystem )&& dish
						.getMemberPrice() != null && Float
						.parseFloat(dish.getMemberPrice()) != 0 && !dish.getMemberPrice()
						.equals(dish.getPrice())) {
					if (Order.getInstance().isMember() || SmarantDataProvider
							.getSelfposConfigurationdata().getContent().isDisplayMember()) {
						dish_memberprice.setVisibility(View.VISIBLE);
						dish_memberprice_vip.setVisibility(View.VISIBLE);
						dish_memberprice.setText(dish.getMemberPrice());
					} else {
						dish_memberprice.setVisibility(View.GONE);
						dish_memberprice_vip.setVisibility(View.GONE);
					}
				} else {
					dish_memberprice.setVisibility(View.GONE);
					dish_memberprice_vip.setVisibility(View.GONE);
				}

				if (dish.getDealId() != null && dish.getDealId() != 0) {
					statu_iv.setVisibility(View.VISIBLE);
					statu_iv.setImageDrawable(getResources()
							.getDrawable(R.mipmap.order_right_icon_meituanyanquan));
				} else {
					statu_iv.setVisibility(View.GONE);
				}
				// It is important that each shared element in the source screen has a unique transition name.
				// For example, we can't just give all the images in our grid the transition name "kittenImage"
				// because then we would have conflicting transition names.
				// By appending "_image" to the position, we can support having multiple shared elements in each
				// grid cell in the future.
				ViewCompat
						.setTransitionName(imageView, String
								.valueOf(dish.getDishID()) + "_image");
				//				Glide.with(mContext).load(dish.getImageName())
				//						.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				//						.placeholder(R.mipmap.default_img)
				//						.error(R.mipmap.default_img)
				//						.centerCrop().override(1090, 1090 * 3 / 4)
				//						.crossFade().into(imageView);
				Glide.with(mContext).load(dish.getImageName())
						.placeholder(R.mipmap.default_img)
						.error(R.mipmap.default_img)
						.into(imageView);

				TextView dish_name = helper.getView(R.id.dish_name);
				dish_name.setText(dish.getDishName());
				if (dish.getDishCount() == 0) {
					order_right_shouqinlayout.setVisibility(View.VISIBLE);
				} else {
					order_right_shouqinlayout.setVisibility(View.GONE);
				}
				if (dish.isVisible()) {
					helper.getConvertView().setVisibility(View.VISIBLE);
				} else {
					helper.getConvertView().setVisibility(View.GONE);
				}
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (dish.getDishCount() == 0) {
							ToastUitl.showLong(mContext, "不好意思,已经售罄了哦");
							return;
						}
						if (dish.getPackageItems() != null && dish.getPackageItems()
								.size() > 0) {
							UIDish cloneDish = (UIDish) dish.myclone();
							startPackageFragment(cloneDish, imageView);
						} else if (dish.getOptionCategoryList() != null && dish
								.getOptionCategoryList()
								.size() > 0) {
							if (SystemConfig.isSmarantSystem && SmarantDataProvider
									.getSelfposConfigurationdata().getContent()
									.isPackageLenovo() && dish.getRecommandList() != null && dish
									.getRecommandList().size() > 0) {
								RecomendPackageDialog dialog = RecomendPackageDialog
										.newInstance(dish.myclone());
								dialog.setImageView(imageView);
								dialog.show(getFragmentManager(), "RecomendPackageDialog");
							} else {
								Fragment dialog1 = getFragmentManager()
										.findFragmentByTag("SmarantOptionDialog");
								if (dialog1 != null)
									return;
								UIDish cloneDish = (UIDish) dish.myclone();
								OptionDialog dialog = OptionDialog
										.newInstance(cloneDish, null, false, false);
								dialog.show(getFragmentManager(), "SmarantOptionDialog");
							}
						} else {

							if (SystemConfig.isSmarantSystem && SmarantDataProvider
									.getSelfposConfigurationdata().getContent()
									.isPackageLenovo() && dish.getRecommandList() != null && dish
									.getRecommandList().size() > 0) {
								RecomendPackageDialog dialog = RecomendPackageDialog
										.newInstance(dish.myclone());
								dialog.setImageView(imageView);
								dialog.show(getFragmentManager(), "RecomendPackageDialog");
							} else {
								UIDish cloneDish = (UIDish) dish.myclone();
								Cart.getInstance().addDish(cloneDish);
								mRxManager.post(AppConstant.DO_ADD_DISH_ANIMATION, imageView);
							}
						}
					}
				});
			}
		};
		irc.setAdapter(adapter);
		irc.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		irc.setOnLoadMoreListener(this);
		irc.setOnRefreshListener(this);
		irc.setRefreshEnabled(false);
		irc.setLoadMoreEnabled(false);
		//		adapter.initAnimation(irc);
		List<UIDish>     uiDishList = UIDataProvider.getUIDish(true);
		List<UIDishKind> uiKindData = UIDataProvider.getUIDishKind();
		if ((uiDishList != null && uiDishList.size() > 0) && (uiKindData != null && uiKindData
				.size() > 0)) {
			UIDishKind uiDishKind = uiKindData.get(0);
			List<UIDish> dishList = UIDataProvider
					.getDishListByKindId(uiDishKind.getKindID(), uiDishKind.getKindName());

			adapter.addAll(dishList);
			loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
		} else {
			loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
		}

	}

	private void startPackageFragment(UIDish dish, ImageView imageView) {
		PackageFragmentNew packageFragment = PackageFragmentNew
				.newInstance(dish, null, false);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			packageFragment
					.setSharedElementEnterTransition(new DetailsTransition());
			packageFragment.setEnterTransition(new Fade());
			setExitTransition(new Fade());
			packageFragment
					.setSharedElementReturnTransition(new DetailsTransition());
		}
		getActivity().getSupportFragmentManager()
				.beginTransaction()
				.addSharedElement(imageView, "dishphoto")
				.replace(R.id.container, packageFragment)
				.addToBackStack("dishFragment")
				.commit();
	}


	private void registKindClickListener() {
		mRxManager.on(AppConstant.CLICK_KIND, new Action1<UIDishKind>() {
			@Override
			public void call(UIDishKind kind) {

				List<UIDish> dishList = UIDataProvider
						.getDishListByKindId(kind.getKindID(), kind.getKindName());
				if (dishList.size() > 0) {
					adapter.addAll(dishList);
					loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
				} else {
					adapter.clear();
					loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
				}
			}
		});

		mRxManager.on(AppConstant.CLICK_KIND_VIEW, new Action1<View>() {
			@Override
			public void call(View view) {
				excuteAnimation(view);
			}
		});


		mRxManager.on(AppConstant.START_PACKAGEFRAGMENT, new Action1<RecommandEvent>() {
			@Override
			public void call(RecommandEvent event) {
				UIDish uiDish = UIDataProvider.getDishByDishId(event.getRecommand().getPackageid());
				startPackageFragment(uiDish, event.getImageView());
			}
		});
	}


	@Override
	public void onLoadMore(View loadMoreView) {

	}

	@Override
	public void onRefresh() {

	}
}
