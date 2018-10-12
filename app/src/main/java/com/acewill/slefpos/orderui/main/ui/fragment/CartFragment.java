//package com.acewill.slefpos.orderui.main.ui.fragment;
//
//import android.content.DialogInterface;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.LinearLayoutManager;
//import android.transition.Fade;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.acewill.slefpos.R;
//import com.acewill.slefpos.app.AppConstant;
//import com.acewill.slefpos.base.BaseFragment;
//import com.acewill.slefpos.bean.cartbean.Cart;
//import com.acewill.slefpos.bean.cartbean.CartDish;
//import com.acewill.slefpos.bean.cartbean.CartDishHelper;
//import com.acewill.slefpos.bean.uibean.UIDish;
//import com.acewill.slefpos.orderui.main.ui.dialog.SmarantOptionDialog;
//import com.acewill.slefpos.orderui.main.trasition.DetailsTransition;
//import com.acewill.slefpos.orderui.main.ui.activity.OrderDetailActivity;
//import com.acewill.slefpos.orderui.main.ui.adapter.CartAdapter;
//import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
//import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//import rx.functions.Action1;
//
///**
// * Author：Anch
// * Date：2018/2/23 10:13
// * Desc：
// */
//public class CartFragment extends BaseFragment {
//
//	private CartAdapter adapter;
//	@Bind(R.id.paymoney)
//	TextView paymoney;
//	@Bind(R.id.paybtn)
//	Button payBtn;
//
//	@OnClick(R.id.clear_cart)
//	public void clearCart() {
//		Cart.getInstance().clear();
//		// TODO: 2018/2/27 anch 这里应该让用户考虑一下
//		getActivity().finish();
//	}
//
//	@Override
//	protected int getLayoutResource() {
//		return R.layout.fra_cart;
//	}
//
//	@Override
//	public void initPresenter() {
//
//	}
//
//	@Override
//	public void onCreate(@Nullable Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//	}
//
//	private boolean flag;
//
//	@Override
//	public void initView() {
//		/*
//		此处的放个标记是因为这个recyclerView不能重复setSwipeMenuCreator ，不能重复setAdapter
//		而使用元素共享的时候，将该Fragment加入到了退回栈，当下一个fragment退回到该Fragment的时候，
//		会再次执行这个onViewCreate的方法
//		 */
//		if (flag) {
//			//修改套餐后刷新界面
//			adapter.notifyDataSetChanged();
//			return;
//		}
//		init();
//
//
//		flag = true;
//		final SwipeMenuRecyclerView recyclerView = (SwipeMenuRecyclerView) rootView
//				.findViewById(R.id.recycler_view);
//		recyclerView.setNestedScrollingEnabled(false);
//		recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//		recyclerView.addItemDecoration(new DefaultItemDecoration(ContextCompat
//				.getColor(mContext, R.color.colorAccent)));
//		recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
//		adapter = new CartAdapter(mContext);
//		recyclerView.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
//			@Override
//			public void onItemClick(SwipeMenuBridge menuBridge) {
//				switch (menuBridge.getDirection()) {
//					case 1:
//						adapter.removeItem(menuBridge.getAdapterPosition());
//						mRxManager.post(AppConstant.ON_CART_ITEM_CHANGE, 1);
//						//						ToastUitl.showLong(mContext, "删除");
//						break;
//					case -1:
//						switch (menuBridge.getPosition()) {
//							case 0:
//								adapter.reduceItemQuantity(menuBridge.getAdapterPosition());
//								//								ToastUitl.showLong(mContext, "减少");
//								break;
//							case 1:
//								adapter.addItemQuantity(menuBridge.getAdapterPosition());
//
//								//								ToastUitl.showLong(mContext, "增加");
//								break;
//
//						}
//						break;
//
//				}
//
//			}
//		});
//		recyclerView.setAdapter(adapter);
//		adapter.setOnNontickyItemClickListener(new CartAdapter.OnNontickyItemClickListener() {
//			@Override
//			public void onNontickyItemClick(CartDish cartDish, ImageView dishPhoto) {
//				if (CartDishHelper.hasOption(cartDish)) {
//					Fragment dialog1 = getFragmentManager()
//							.findFragmentByTag("SmarantOptionDialog");
//					if (dialog1 != null)
//						return;
//					UIDish cloneDish = (UIDish) SmarantDataProvider
//							.getDishById(cartDish.getDishID()).myclone();
//					SmarantOptionDialog dialog = SmarantOptionDialog
//							.newInstance(cloneDish, cartDish, true, false);
//					dialog.show(getFragmentManager(), "SmarantOptionDialog");
//				} else if (CartDishHelper.isPackage(cartDish)) {
//					UIDish cloneDish = (UIDish) SmarantDataProvider
//							.getDishById(cartDish.getDishID())
//							.myclone();
//					PackageFragmentNew packageFragment = PackageFragmentNew
//							.newInstance(cloneDish, cartDish, true);
//					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//						packageFragment
//								.setSharedElementEnterTransition(new DetailsTransition());
//						packageFragment.setEnterTransition(new Fade());
//						setExitTransition(new Fade());
//						packageFragment
//								.setSharedElementReturnTransition(new DetailsTransition());
//					}
//					getActivity().getSupportFragmentManager()
//							.beginTransaction()
//							.addSharedElement(dishPhoto, "dishphoto")
//							.replace(R.id.container, packageFragment)
//							.addToBackStack("cartFragment")
//							.commit();
//				}
//			}
//		});
//		adapter.setListItems(Cart.getInstance().getCartDishes());
//		initListener();
//	}
//
//	private void init() {
//		total();
//	}
//
//	/**
//	 * 计算总价钱
//	 */
//	private void total() {
//		paymoney.setText("￥" + String.valueOf(Cart.getInstance().getTotal()));
//		payBtn.setText("结算(" + Cart.getInstance().getCartDishes().size() + ")");
//	}
//
//	private void initListener() {
//		mRxManager.on(AppConstant.ON_CART_ITEM_CHANGE, new Action1<Object>() {
//			@Override
//			public void call(Object o) {
//				if (Cart.getInstance().getCartCount() == 0)
//					clearCart();
//				else
//					total();
//				adapter.notifyDataSetChanged();
//			}
//		});
//	}
//
//
//	/**
//	 * 缺少一个显示对话框的工具类
//	 */
//	private void showDialog() {
//		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
//		builder.setTitle("提示");
//		builder.setMessage("是否删除该菜品");
//		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				getActivity().finish();
//			}
//		});
//		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialogInterface, int i) {
//				//								AppApplication.getAppContext().exitApp();
//
//			}
//		});
//		builder.show();
//	}
//
//	/**
//	 * 菜单创建器。
//	 */
//	private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
//		@Override
//		public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
//			if (viewType == CartAdapter.VIEW_TYPE_NON_STICKY) {
//				int width = getResources().getDimensionPixelSize(R.dimen.x200);
//
//				// 1. MATCH_PARENT 自适应高度，保持和Item一样高;
//				// 2. 指定具体的高，比如80;
//				// 3. WRAP_CONTENT，自身高度，不推荐;
//				int height = ViewGroup.LayoutParams.MATCH_PARENT;
//
//				SwipeMenuItem reduceItem = new SwipeMenuItem(mContext)
//						.setBackground(R.drawable.selector_purple)
//						.setImage(R.mipmap.icon_minus_light)
//						.setWidth(width)
//						.setHeight(height);
//
//				swipeRightMenu.addMenuItem(reduceItem); // 添加菜单到右侧。
//
//				SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//						.setBackground(R.drawable.selector_green)
//						.setImage(R.mipmap.icon_plus_light)
//						.setWidth(width)
//						.setHeight(height);
//
//				swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
//
//				SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
//						.setBackground(R.drawable.selector_red)
//						.setImage(R.mipmap.ic_action_close)
//						.setWidth(width)
//						.setHeight(height);
//
//				swipeLeftMenu.addMenuItem(deleteItem); // 添加菜单到左侧。
//			}
//		}
//	};
//
//	@OnClick(R.id.iv_back)
//	public void onBack() {
//		getActivity().finish();
//	}
//
//	@OnClick(R.id.paybtn)
//	public void onPay() {
//		startActivity(OrderDetailActivity.class);
//	}
//}
