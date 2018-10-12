package com.acewill.slefpos.orderui.main.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseFragment;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.uibean.UIDishKind;
import com.acewill.slefpos.orderui.main.ui.eventbus.OnCartItemChangeOptionDialog;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.animation.CustomItemAnimator;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.commonwidget.LoadingTip;

import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/2/1 11:48
 * Desc：
 */
public class KindFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener {
	@Bind(R.id.irc)
	IRecyclerView irc;
	@Bind(R.id.loadedTip)
	LoadingTip    loadedTip;


	private CommonRecycleViewAdapter<UIDishKind> adapter;
	//	private View                               activeView;
	//	private DishKind                           activeKind;

	@Override
	protected int getLayoutResource() {
		return R.layout.fra_kind;
	}

	@Override
	public void initPresenter() {

	}


	@Override
	protected void initView() {
		initAddDishToCartListener();
		adapter = new CommonRecycleViewAdapter<UIDishKind>(getActivity(), R.layout.item_dishkind) {
			@Override
			public void convert(final ViewHolderHelper helper, final UIDishKind kind) {

				/**
				 *右边被点击的时候
				 */
				final FrameLayout dot_ly       = helper.getView(R.id.dot_ly);
				final TextView    dot_count_tv = helper.getView(R.id.dot_count_tv);
				final View        itemview     = helper.getConvertView();
				dot_count_tv.setText(kind.getSelectCount() + "");
				if (kind.getSelectCount() > 0) {
					dot_ly.setVisibility(View.VISIBLE);
				} else {
					dot_ly.setVisibility(View.GONE);
				}
				if (kind.isSelect()) {
					selectItem(itemview);
				} else {
					delectItem(itemview);
				}
				final ImageView imageView = helper.getView(R.id.kind_photo);
				Glide.with(mContext).load(kind.getImageName())
						.placeholder(R.mipmap.default_img)
						.error(R.mipmap.default_img).into(imageView);

				final TextView kind_name = helper.getView(R.id.kind_name);
				kind_name.setText(kind.getKindName());

				itemview.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						//放大选中的那个，图片和文字，还有整个item的大小、
						if (kind.isSelect())
							return;
						for (UIDishKind kind1 : adapter.getAll()) {
							if (kind1.isSelect()) {
								kind1.setSelect(false);
							}
						}
						kind.setSelect(true);
						notifyDataSetChanged();
						//传数据
						RxBus.getInstance().post(AppConstant.CLICK_KIND, kind);
						//传位置
						RxBus.getInstance()
								.post(AppConstant.CLICK_KIND_VIEW, helper.getConvertView());
						notifyDataSetChanged();
						//						notifyItemChanged(helper.getAdapterPosition());
					}
				});
			}
		};
		irc.setAdapter(adapter);
		irc.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
		irc.setItemAnimator(new CustomItemAnimator());
		irc.setOnLoadMoreListener(this);
		irc.setOnRefreshListener(this);
		irc.setRefreshEnabled(false);
		irc.setLoadMoreEnabled(false);
		//		adapter.initAnimation(irc);//初始化刚进入界面时候的动画
		adapter.addAll(getDishKindList());
	}

	/**
	 * 做了一个转化
	 *
	 * @return
	 */
	private List<UIDishKind> getDishKindList() {
		List<UIDishKind> data1 = UIDataProvider.getUIDishKind();
		if (data1 != null && data1.size() > 0) {
			data1.get(0).setSelect(true);
		}
		return data1;
	}


	private void initAddDishToCartListener() {
		mRxManager.on(AppConstant.ADD_DISH_TO_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				//				ToastUitl.showShort(mContext, "成功加入购物车");
				//找到对应的
				List<UIDishKind> dishKinds = adapter.getAll();
				for (int i = 0; i < dishKinds.size(); i++) {
					if (dishKinds.get(i).getKindID().equals(cartDish.getKindId())) {
						UIDishKind kind = dishKinds.get(i);
						kind.setSelectCount(kind.getSelectCount() + cartDish.getQuantity());
						adapter.replaceAt(i, kind);
						return;
					}
				}
			}
		});
		mRxManager.on(AppConstant.REMOVE_DISH_FROM_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				//找到对应的
				List<UIDishKind> dishKinds = adapter.getAll();
				for (int i = 0; i < dishKinds.size(); i++) {
					if (dishKinds.get(i).getKindID().equals(cartDish.getKindId())) {
						UIDishKind kind = dishKinds.get(i);
						kind.setSelectCount(kind.getSelectCount() - cartDish.getQuantity());
						adapter.replaceAt(i, kind);
						return;
					}
				}
			}
		});

		mRxManager.on(AppConstant.REDUCE_DISH_QUANTITY_FROM_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				//找到对应的
				List<UIDishKind> dishKinds = adapter.getAll();
				for (int i = 0; i < dishKinds.size(); i++) {
					if (dishKinds.get(i).getKindID().equals(cartDish.getKindId())) {
						UIDishKind kind = dishKinds.get(i);
						kind.setSelectCount(kind.getSelectCount() - 1);
						adapter.replaceAt(i, kind);
						return;
					}
				}
			}
		});

		mRxManager.on(AppConstant.ADD_DISH_QUANTITY_FROM_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				//找到对应的
				List<UIDishKind> dishKinds = adapter.getAll();
				for (int i = 0; i < dishKinds.size(); i++) {
					if (dishKinds.get(i).getKindID().equals(cartDish.getKindId())) {
						UIDishKind kind = dishKinds.get(i);
						kind.setSelectCount(kind.getSelectCount() + 1);
						adapter.replaceAt(i, kind);
						return;
					}
				}
			}
		});


		mRxManager.on(AppConstant.CLEAR_CART, new Action1<Object>() {
			@Override
			public void call(Object cartDish) {
				//找到对应的
				List<UIDishKind> dishKinds = adapter.getAll();
				for (int i = 0; i < dishKinds.size(); i++) {
					UIDishKind kind = dishKinds.get(i);
					kind.setSelectCount(0);
				}
				adapter.notifyDataSetChanged();
			}
		});
		mRxManager.on(AppConstant.ON_CART_ITEM_CHANGE_OPTIONDIALOG, new Action1<String>() {
			@Override
			public void call(String kindId) {
				List<UIDishKind> dishKinds = adapter.getAll();
				for (int i = 0; i < dishKinds.size(); i++) {
					if (dishKinds.get(i).getKindID().equals(kindId)) {
						UIDishKind kind = dishKinds.get(i);
						kind.setSelectCount(kind.getSelectCount() + 1);
						adapter.replaceAt(i, kind);
						return;
					}
				}
			}
		});

		mRxManager
				.on(AppConstant.ON_CART_ITEM_CHANGE_OPTIONDIALOG2, new Action1<OnCartItemChangeOptionDialog>() {
					@Override
					public void call(OnCartItemChangeOptionDialog bean) {
						List<UIDishKind> dishKinds = adapter.getAll();
						for (int i = 0; i < dishKinds.size(); i++) {
							if (dishKinds.get(i).getKindID().equals(bean.getKindId())) {
								UIDishKind kind = dishKinds.get(i);
								kind.setSelectCount(kind.getSelectCount() + bean
										.getMultiQuantity());
								adapter.replaceAt(i, kind);
								return;
							}
						}
					}
				});
	}

	//	private void selectItem(View view) {
	//		CardView cardView = (CardView) view.findViewById(R.id.kind_cardview);
	//		cardView.setCardBackgroundColor(getResources().getColor(R.color.white));
	//		final ImageView icon = (ImageView) view.findViewById(R.id.kind_photo);
	//		//		icon.setSelected(true);
	//		final TextView text = (TextView) view.findViewById(R.id.kind_name);
	//		//		text.setSelected(true);
	//
	//		text.setScaleX(1.2f);
	//		text.setScaleY(1.2f);
	//		text.postInvalidate();
	//		icon.setTranslationY(-10f);
	//		icon.postInvalidate();
	//	}

	private void selectItem(final View item) {
		FrameLayout kind_item_layout = (FrameLayout) item.findViewById(R.id.kind_item_layout);
		kind_item_layout.setBackgroundResource(R.color.white);
		View view = item.findViewById(R.id.view);
		view.setVisibility(View.VISIBLE);
		//		activeView = item;
		//		kind.setSelect(true);
		//		activeKind = kind;
		//		final ImageView icon = (ImageView) item.findViewById(R.id.kind_photo);
		//		icon.setSelected(true);
		//		final TextView text = (TextView) item.findViewById(R.id.kind_name);
		//		text.setSelected(true);
		//		ValueAnimator animator = ValueAnimator.ofFloat(1, 1.5f, 1);
		//		animator.setDuration(200);
		//		animator.setInterpolator(new DecelerateInterpolator());
		//		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		//			@Override
		//			public void onAnimationUpdate(ValueAnimator animation) {
		//		text.setScaleX(1.5f);
		//		text.setScaleY(1.5f);
		//		text.postInvalidate();
		//			}
		//		});
		//		animator.start();
		//		ValueAnimator animator2 = ValueAnimator.ofFloat(0, -20f, 0);
		//		animator2.setDuration(200);
		//		animator2.setInterpolator(new DecelerateInterpolator());
		//		animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		//			@Override
		//			public void onAnimationUpdate(ValueAnimator animation) {
		//		icon.setScaleX(1.2f);
		//		icon.setScaleY(1.2f);
		//		icon.setTranslationY(-20f);
		//		icon.postInvalidate();
		//			}
		//		});
		//		animator2.start();
		//		final FrameLayout dot_ly = (FrameLayout) item.findViewById(R.id.dot_ly);
		//		ValueAnimator     animator3 = ValueAnimator.ofFloat(1, 1.5f, 1);
		//		animator3.setDuration(200);
		//		animator3.setInterpolator(new DecelerateInterpolator());
		//		animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		//			@Override
		//			public void onAnimationUpdate(ValueAnimator animation) {
		//		dot_ly.setScaleX(1.5f);
		//		dot_ly.setScaleY(1.5f);
		//		dot_ly.postInvalidate();
		//			}
		//		});
		//		animator3.start();
	}

	private void delectItem(final View item) {
		FrameLayout kind_item_layout = (FrameLayout) item.findViewById(R.id.kind_item_layout);
		kind_item_layout.setBackgroundResource(R.color.colorSecondary);
		View view = item.findViewById(R.id.view);
		view.setVisibility(View.GONE);
		//		final ImageView icon = (ImageView) item.findViewById(R.id.kind_photo);
		//		icon.setSelected(false);
		//		final TextView text = (TextView) item.findViewById(R.id.kind_name);
		//		text.setSelected(false);
		//		ValueAnimator animator = ValueAnimator.ofFloat(1.2f, 1);
		//		animator.setDuration(200);
		//		animator.setInterpolator(new DecelerateInterpolator());
		//		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		//			@Override
		//			public void onAnimationUpdate(ValueAnimator animation) {
		//				text.setScaleX((Float) animation.getAnimatedValue());
		//				text.setScaleY((Float) animation.getAnimatedValue());
		//				text.postInvalidate();
		//			}
		//		});
		//		animator.start();
		//		ValueAnimator animator2 = ValueAnimator.ofFloat(-20f, 0);
		//		animator2.setDuration(200);
		//		animator2.setInterpolator(new DecelerateInterpolator());
		//		animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		//			@Override
		//			public void onAnimationUpdate(ValueAnimator animation) {
		//				icon.setTranslationY((Float) animation.getAnimatedValue());
		//				icon.postInvalidate();
		//			}
		//		});
		//		animator2.start();
	}

	@Override
	public void onLoadMore(View loadMoreView) {

	}

	@Override
	public void onRefresh() {

	}
}
