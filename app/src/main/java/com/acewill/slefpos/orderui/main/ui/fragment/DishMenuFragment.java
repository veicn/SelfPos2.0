package com.acewill.slefpos.orderui.main.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.base.BaseFragment;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.ui.activity.CartActivity;
import com.acewill.slefpos.orderui.main.ui.activity.OrderDetailActivity;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.acewill.slefpos.utils.uiutils.BezierEvaluator;
import com.acewill.slefpos.utils.uiutils.CircularAnimUtil;
import com.jaydenxiao.common.commonutils.NetWorkUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.utils.springylib.SpringAnimationType;
import com.jaydenxiao.common.utils.springylib.SpringyAnimator;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/2/1 10:27
 * Desc：
 */
public class DishMenuFragment extends BaseFragment {
	private static final String TAG = "DishMenuFragment";
	@Bind(R.id.fab)
	FloatingActionButton fab;
	@Bind(R.id.tablayout)
	RelativeLayout       tabLayout;
	@Bind(R.id.coordinatorlayout)
	CoordinatorLayout    coordinatorLayout;

	@Bind(R.id.dot_count_tv)
	TextView       dot_count_tv;
	@Bind(R.id.special_money)
	TextView       special_money;
	@Bind(R.id.dot_ly_bg)
	ImageView      dot_ly_bg;
	@Bind(R.id.dot_ly)
	FrameLayout    dot_ly;
	@Bind(R.id.shop_cart_ly)
	RelativeLayout shop_cart_ly;
	@Bind(R.id.special_money_ly)
	LinearLayout   special_money_ly;

	@OnClick(R.id.shop_cart_ly)
	public void onFabClick() {
		if (Cart.getInstance().getCartDishes().size() == 0) {
			ToastUitl.showShort(mContext, "购物车空空如也!");
			return;
		}
		Intent it = new Intent();
		it.setClass(mContext, CartActivity.class);
		CircularAnimUtil.startActivity(getActivity(), it, shop_cart_ly, R.color.colorPrimary);
	}

	@OnClick(R.id.iv_back)
	public void back() {
		/**
		 * 清空购物车
		 */
		Cart.getInstance().clear();
		SyncDataProvider.clearMemberLoginInfo();
		getActivity().finish();
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fra_dishmenu;
	}

	@Override
	public void initPresenter() {
	}

	private int tabLayoutHeight;

	@Override
	protected void initView() {
		initFragment();
		tabLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		tabLayoutHeight = tabLayout.getMeasuredHeight();
		mRxManager.on(AppConstant.MENU_SHOW_HIDE, new Action1<Boolean>() {

			@Override
			public void call(Boolean hideOrShow) {
				//显示购物车
				startAnimation(hideOrShow);
			}
		});
		mRxManager.on(AppConstant.DO_ADD_DISH_ANIMATION, new Action1<ImageView>() {
			@Override
			public void call(ImageView o) {
				startAddDishAnimation(o);
			}
		});
		mRxManager.on(AppConstant.ADD_DISH_TO_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				refreshUI();
			}
		});
		mRxManager.on(AppConstant.ON_CART_ITEM_CHANGE, new Action1<Object>() {
			@Override
			public void call(Object object) {
				refreshUI();
			}
		});
		mRxManager.on(AppConstant.REMOVE_DISH_FROM_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				refreshUI();
			}
		});
		mRxManager.on(AppConstant.REDUCE_DISH_QUANTITY_FROM_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				//找到对应的
				refreshUI();
			}
		});

		mRxManager.on(AppConstant.ADD_DISH_QUANTITY_FROM_CART, new Action1<CartDish>() {
			@Override
			public void call(CartDish cartDish) {
				//找到对应的
				refreshUI();
			}
		});
		mRxManager.on(AppConstant.CLEAR_CART, new Action1<Object>() {
			@Override
			public void call(Object cartDish) {
				refreshUI();
			}
		});
		mRxManager.on(AppConstant.ON_CART_ITEM_CHANGE_OPTIONDIALOG, new Action1<String>() {
			@Override
			public void call(String o) {
				refreshUI();
			}
		});


		//		Typeface typeFace1 = Typeface
		//				.createFromAsset(getActivity().getAssets(), "fonts/kaitifan.ttf");
		//		payBtn.setTypeface(typeFace1);
		//		tv_1.setTypeface(typeFace1);
	}

	private boolean isFirstRefresh = true;

	private void refreshUI() {
		dot_count_tv.setText(Cart.getInstance().getCartCount() + "");
		paymoney.setText("￥" + Price.getInstance().getTotal() + "");

		if (SystemConfig.isSmarantSystem) {
			if (Price.getInstance().getActualCost() != null && Float
					.parseFloat(Price.getInstance().getActualCost()) != 0 && PriceUtil
					.subtract(Price.getInstance().getDishTotalWithMix(), Price.getInstance()
							.getActualCost()).floatValue() != 0) {
				special_money_ly.setVisibility(View.VISIBLE);
				special_money.setText("￥" + PriceUtil
						.subtract(Price.getInstance().getDishTotalWithMix(), Price.getInstance()
								.getActualCost()).toString());
			} else {
				special_money_ly.setVisibility(View.GONE);
			}
		} else if (SystemConfig.isSyncSystem) {
			if (Price.getInstance().getTotalDiscountAmount() != 0) {
				special_money_ly.setVisibility(View.VISIBLE);
				special_money.setText("￥" + PriceUtil
						.formatPrice(Price.getInstance().getTotalDiscountAmount()));
			} else {
				special_money_ly.setVisibility(View.GONE);
			}
		} else {
			special_money_ly.setVisibility(View.GONE);
		}


		//		dot_ly_bg

		if (isFirstRefresh && Cart.getInstance().getCartCount() > 0) {
			dot_count_tv.setVisibility(View.VISIBLE);
			dot_ly_bg.setImageDrawable(getResources().getDrawable(R.mipmap.icon_shop_cart_deep));
			tabLayout.setBackgroundColor(getResources().getColor(R.color.main_color_white));
			//			tabLayout.setBackground(getResources().getDrawable(R.mipmap.cart_bg));
			img_back.setImageDrawable(getResources().getDrawable(R.mipmap.icon_back2));
			doShowAnimation(true);
			isFirstRefresh = false;
		} else if (Cart.getInstance().getCartCount() == 0) {
			dot_ly_bg.setImageDrawable(getResources().getDrawable(R.mipmap.icon_shop_cart_empty));
			dot_ly.setVisibility(View.GONE);
			dot_count_tv.setVisibility(View.GONE);
			tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
			//			tabLayout.setBackground(getResources().getDrawable(R.mipmap.cart_bg));
			payBtn.setVisibility(View.GONE);
			total_ly.setVisibility(View.GONE);
			img_back.setImageDrawable(getResources().getDrawable(R.mipmap.icon_back));
			isFirstRefresh = true;
		}
	}

	@Bind(R.id.paybtn)
	Button       payBtn;
	@Bind(R.id.img_back)
	ImageView    img_back;
	@Bind(R.id.total_ly)
	LinearLayout total_ly;
	@Bind(R.id.tv_1)
	TextView     tv_1;
	@Bind(R.id.paymoney)
	TextView     paymoney;

	@OnClick(R.id.paybtn)
	public void pay() {
		startActivity(OrderDetailActivity.class);
		//			//结算
	}


	/**
	 * 检查下单的网络环境
	 */
	private void checkNetEnvironment() {
		MyTask task = new MyTask();
		task.execute("", "");
	}

	private class MyTask extends AsyncTask<String, String, Boolean> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			startProgressDialog("正在检查支付环境,请稍后...");
		}

		@Override
		protected Boolean doInBackground(String... params) {
			return NetWorkUtils.ping();
		}

		@Override
		protected void onPostExecute(Boolean aBoolean) {
			super.onPostExecute(aBoolean);
			stopProgressDialog();
			if (aBoolean) {
				TimeConfigure.resetScreenTime();
				startActivity(OrderDetailActivity.class);
				getActivity().overridePendingTransition(R.anim.slide_right_in,
						R.anim.slide_left_out);
			} else {
				ToastUitl.showLong(mContext, "网络异常，请检查网络!");
			}
		}
	}


	//	private enum AnimationState {
	//		STATE_SHOW,
	//		STATE_HIDDEN
	//	}

	//	/**
	//	 * 渐隐渐现动画
	//	 *
	//	 * @param view     需要实现动画的对象
	//	 * @param state    需要实现的状态
	//	 * @param duration 动画实现的时长（ms）
	//	 */
	//	private void showAndHiddenAnimation(final View view, AnimationState state, long duration) {
	//		float start = 0f;
	//		float end   = 0f;
	//		if (state == AnimationState.STATE_SHOW) {
	//			end = 1f;
	//			view.setVisibility(View.VISIBLE);
	//		} else if (state == AnimationState.STATE_HIDDEN) {
	//			start = 1f;
	//			view.setVisibility(View.INVISIBLE);
	//		}
	//		AlphaAnimation animation = new AlphaAnimation(start, end);
	//		animation.setDuration(duration);
	//		animation.setFillAfter(true);
	//		animation.setAnimationListener(new Animation.AnimationListener() {
	//
	//			@Override
	//			public void onAnimationStart(Animation animation) {
	//			}
	//
	//			@Override
	//			public void onAnimationRepeat(Animation animation) {
	//			}
	//
	//			@Override
	//			public void onAnimationEnd(Animation animation) {
	//				view.clearAnimation();
	//
	//				setScale(payBtn, 300);
	//				dot_ly.setVisibility(View.VISIBLE);
	//				total_ly.setVisibility(View.VISIBLE);
	//			}
	//		});
	//		view.setAnimation(animation);
	//		animation.start();
	//	}

	public void doShowAnimation(boolean isShow) {
		if (isShow) {
			setScaleAlpha(shop_cart_ly, 100);
			setScale(dot_ly, 200);
			setScale(total_ly, 300);
			setScale(payBtn, 400);
		}
	}
	//从0到1
	//			ObjectAnimator showAnimation = ObjectAnimator.ofFloat(dot_ly_bg, "alpha", 0, 1);
	//			showAnimation.setDuration(1000);
	//			showAnimation.start();


	//			动画没毛病，但是无法实现我想要的效果
	//创建动画,这里的关键就是使用ArgbEvaluator, 后面2个参数就是 开始的颜色,和结束的颜色.
	//			ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), getResources()
	//					.getColor(R.color.gray), getResources().getColor(R.color.colorPrimary));
	//			colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
	//				@Override
	//				public void onAnimationUpdate(ValueAnimator animation) {
	//					int color = (int) animation.getAnimatedValue();//之后就可以得到动画的颜色了
	//					dot_ly_bg.setBackgroundColor(color);//设置一下, 就可以看到效果.
	//				}
	//			});
	//			colorAnimator.setDuration(700);
	//			colorAnimator.start();
	//	/**
	//	 * 菜单显示隐藏动画
	//	 *
	//	 * @param showOrHide
	//	 */
	//	private void doShowAnimation(boolean showOrHide) {
	//		final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
	//		ValueAnimator                valueAnimator;
	//		final ValueAnimator          valueAnimator2;
	//		ObjectAnimator               alpha;
	//		if (!showOrHide) {
	//			valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
	//			valueAnimator2 = ValueAnimator.ofFloat(0, 70);
	//			alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 1, 0);
	//		} else {
	//			valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
	//			valueAnimator2 = ValueAnimator.ofFloat(70, 0);
	//			alpha = ObjectAnimator.ofFloat(tabLayout, "alpha", 0, 1);
	//		}
	//		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
	//			@Override
	//			public void onAnimationUpdate(ValueAnimator valueAnimator) {
	//				layoutParams.height = (int) valueAnimator.getAnimatedValue();
	//				tabLayout.setLayoutParams(layoutParams);
	//			}
	//		});
	//		valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
	//			@Override
	//			public void onAnimationUpdate(ValueAnimator valueAnimator) {
	//				shop_cart_ly.setTranslationY((Float) valueAnimator2.getAnimatedValue());
	//				shop_cart_ly.postInvalidate();
	//			}
	//		});
	//		AnimatorSet animatorSet = new AnimatorSet();
	//		animatorSet.setDuration(500);
	//		animatorSet.playTogether(valueAnimator, valueAnimator2, alpha);
	//		animatorSet.start();
	//	}

	/**
	 * ★★★★★把商品添加到购物车的动画效果 抛物线★★★★★
	 */
	public void startAddDishAnimation(ImageView iv) {
		//      一、创造出执行动画的主题---imageview
		//代码new一个imageview，图片资源是上面的imageview的图片
		// (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
		final ImageView goods = new ImageView(mContext);
		goods.setImageDrawable(iv.getDrawable());
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(300, 300);
		coordinatorLayout.addView(goods, params);


		//        二、计算动画开始/结束点的坐标的准备工作
		//得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
		int[] parentLocation = new int[2];
		coordinatorLayout.getLocationInWindow(parentLocation);
		Log.e(TAG, "px:" + parentLocation[0] + ",py:" + parentLocation[1]);
		//得到商品图片的坐标（用于计算动画开始的坐标）
		int startLoc[] = new int[2];
		iv.getLocationInWindow(startLoc);
		Log.e(TAG, "startLocx:" + startLoc[0] + ",startLocy:" + startLoc[1]);
		//得到购物车图片的坐标(用于计算动画结束后的坐标)
		int endLoc[] = new int[2];
		shop_cart_ly.getLocationInWindow(endLoc);
		Log.e(TAG, "endLocx:" + endLoc[0] + ",endLocy:" + endLoc[1]);

		//        三、正式开始计算动画开始/结束的坐标
		//开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
		int   startX     = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
		int   startY     = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;
		Point startPoint = new Point(startX, startY);
		//商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
		int   toX      = endLoc[0] - parentLocation[0] - shop_cart_ly.getWidth() / 2;
		int   toY      = endLoc[1] - parentLocation[1] - shop_cart_ly.getHeight() / 2;
		Point endPoint = new Point(toX, toY);
		//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
		//开始绘制贝塞尔曲线
		//		Path path = new Path();
		//移动到起始点（贝塞尔曲线的起点）
		//		path.moveTo(startX, startY);
		//使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
		//		path.quadTo((startX + toX) / 2, startY, toX, toY);
		//mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
		// 如果是true，path会形成一个闭环
		//		mPathMeasure = new PathMeasure(path, false);


		int   pointX        = (startX + toX) / 2;
		int   pointY        = startY - 100;
		Point controllPoint = new Point(pointX, pointY);
		//★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
		//		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
		BezierEvaluator bezierEvaluator = new BezierEvaluator(controllPoint);
		ValueAnimator valueAnimator = ValueAnimator
				.ofObject(bezierEvaluator, startPoint, endPoint);
		// 匀速线性插值器
		valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// 当插值计算进行时，获取中间的每个值，
				// 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
				Point value = (Point) animation.getAnimatedValue();
				// ★★★★★获取当前点坐标封装到mCurrentPosition
				// boolean getPosTan(float distance, float[] pos, float[] tan) ：
				// 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
				// 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。

				//				mPathMeasure
				//						.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
				// 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
				goods.setTranslationX(value.x);
				goods.setTranslationY(value.y);
			}
		});
		//      五、 开始执行动画
		//        valueAnimator.start();
		ObjectAnimator animator2 = ObjectAnimator
				.ofFloat(goods, "scaleY", 1, 0.9f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0f);
		ObjectAnimator animator3 = ObjectAnimator
				.ofFloat(goods, "scaleX", 1, 0.9f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0f);
		AnimatorSet animSet = new AnimatorSet();
		animSet.play(valueAnimator).with(animator2).with(animator3);
		animSet.setDuration(1000);
		animSet.start();
		//      六、动画结束后的处理
		valueAnimator.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			//当动画结束后：
			@Override
			public void onAnimationEnd(Animator animation) {
				// 购物车的数量加1
				//                count.setText(String.valueOf(i));
				// 把移动的图片imageview从父布局里移除
				if (coordinatorLayout != null)
					coordinatorLayout.removeView(goods);
				//				MyDataController.addProductToCart(dishModel);
				//				Cart.getInstance().addProductToCart(dishModel);
				//				mCountTv.setText(String.valueOf(Cart.getInstance().getAllItemSize()));
				//				customerChooseView.changeCartStatus();
				//				showGiftDialog();
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
	}

	/**
	 * 菜单显示隐藏动画
	 *
	 * @param showOrHide
	 */
	private void startAnimation(boolean showOrHide) {
		final ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
		ValueAnimator                valueAnimator;
		final ValueAnimator          valueAnimator2;
		ObjectAnimator               alpha;
		if (!showOrHide) {
			valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
			valueAnimator2 = ValueAnimator.ofFloat(0, 50);
			alpha = ObjectAnimator.ofFloat(shop_cart_ly, "alpha", 1, 0.5f);
		} else {
			valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
			valueAnimator2 = ValueAnimator.ofFloat(50, 0);
			alpha = ObjectAnimator.ofFloat(shop_cart_ly, "alpha", 0.5f, 1);
		}
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				//				layoutParams.height = (int) valueAnimator.getAnimatedValue();
				//				tabLayout.setLayoutParams(layoutParams);
			}
		});
		valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				//				shop_cart_ly.setTranslationY((Float) valueAnimator2.getAnimatedValue());
				//				shop_cart_ly.postInvalidate();
			}
		});
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setDuration(500);
		animatorSet.playTogether(valueAnimator, valueAnimator2, alpha);
		animatorSet.start();
	}

	private void initFragment() {
		FragmentTransaction transaction  = getFragmentManager().beginTransaction();
		DishFragment        dishFragment = new DishFragment();
		KindFragment        kindFragment = new KindFragment();
		transaction.add(dishFragment, "dishFragment");
		transaction.add(kindFragment, "kindFragment");
		transaction.replace(R.id.kind_contain, kindFragment);
		transaction.replace(R.id.dish_contain, dishFragment);
		transaction.commit();
	}


	//	private void animateView(View view) {
	//		SpringyAnimator springHelper = new SpringyAnimator(SpringAnimationType.SCALEXY, 100, 4, 0, 1);
	//		springHelper.startSpring(view);
	//	}


	//	private void playAnimate() {
	//		final SpringyAnimator springHelper = new SpringyAnimator(SpringAnimationType.SCALEXY, 100, 3, 0.5f, 1);
	//		for (int i = 0; i < views.size(); i++) {
	//			final int count = i;
	//			new Handler().postDelayed(new Runnable() {
	//				@Override
	//				public void run() {
	//					springHelper.startSpring(views.get(count));
	//					if (count == 2) {
	//						if (!isPlay) {
	//							play.setImageResource(R.drawable.ic_pause_black_24dp);
	//							isPlay = true;
	//						} else {
	//							play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
	//							isPlay = false;
	//						}
	//					}
	//				}
	//			}, 80 * i);
	//
	//		}
	//	}


	private void setScale(View view, int delay) {
		SpringyAnimator helper = new SpringyAnimator(SpringAnimationType.SCALEXY, 15, 4, 0, 1);
		helper.setDelay(delay);
		helper.startSpring(view);
		SpringyAnimator s1 = new SpringyAnimator(SpringAnimationType.ALPHA, 10, 5, 0, 1);
		s1.setDelay(delay);
		s1.startSpring(view);

	}

	private void setScaleAlpha(View view, int delay) {
		SpringyAnimator s1 = new SpringyAnimator(SpringAnimationType.ALPHA, 10, 5, 0, 1);
		s1.setDelay(delay);
		s1.startSpring(view);
	}

	//		private void setTranslate(View view, int delay) {
	//
	//			switch (view.getId()) {
	//				case R.id.image:
	//					SpringyAnimator s1 = new SpringyAnimator(SpringAnimationType.ALPHA, 10, 5, 0, 1);
	//					s1.setDelay(delay);
	//					s1.startSpring(view);
	//					break;
	//				case R.id.gradient:
	//					SpringyAnimator s2 = new SpringyAnimator(SpringAnimationType.TRANSLATEY, 40, 10, getResources()
	//							.getDisplayMetrics().heightPixels, 0);
	//					s2.setDelay(delay);
	//					s2.startSpring(view);
	//					break;
	//				case R.id.title:
	//					SpringyAnimator s3 = new SpringyAnimator(SpringAnimationType.TRANSLATEY, TRANS_TENSION, TRANS_FRACTION, getResources()
	//							.getDisplayMetrics().heightPixels / 8, 0);
	//					s3.setDelay(delay);
	//					s3.startSpring(view);
	//					break;
	//				case R.id.artist:
	//					SpringyAnimator s4 = new SpringyAnimator(SpringAnimationType.TRANSLATEY, TRANS_TENSION, TRANS_FRACTION, getResources()
	//							.getDisplayMetrics().heightPixels / 8, 0);
	//					s4.setDelay(delay);
	//					s4.startSpring(view);
	//					break;
	//				case R.id.forward:
	//					SpringyAnimator s5 = new SpringyAnimator(SpringAnimationType.TRANSLATEX, 8, 3, -play
	//							.getLayoutParams().width, 0);
	//					s5.setDelay(delay);
	//					s5.startSpring(view);
	//					break;
	//
	//				case R.id.rewind:
	//					SpringyAnimator s6 = new SpringyAnimator(SpringAnimationType.TRANSLATEX, 8, 3, play
	//							.getLayoutParams().width, 0);
	//					s6.setDelay(delay);
	//					s6.startSpring(view);
	//					break;
	//			}
	//		}
}
