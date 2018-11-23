package com.acewill.slefpos.orderui.main.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseFragment;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.CartDishHelper;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.uibean.GroupEntity;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.trasition.DetailsTransition;
import com.acewill.slefpos.orderui.main.ui.activity.OrderDetailActivity;
import com.acewill.slefpos.orderui.main.ui.adapter.NoFooterAdapter;
import com.acewill.slefpos.orderui.main.ui.dialog.OptionDialog;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.widget.StickyHeaderLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/2/23 10:13
 * Desc：
 */
public class CartFragmentNew extends BaseFragment {
	private RecyclerView       rvList;
	private StickyHeaderLayout stickyLayout;

	@Bind(R.id.paymoney)
	TextView     paymoney;
	@Bind(R.id.special_money_ly)
	LinearLayout special_money_ly;
	@Bind(R.id.special_money)
	TextView     special_money;
	@Bind(R.id.paybtn)
	Button       payBtn;

	@OnClick(R.id.clear_cart)
	public void clearCart() {
		Cart.getInstance().clear();
		// TODO: 2018/2/27 anch 这里应该让用户考虑一下
		getActivity().finish();
	}

	@OnClick(R.id.iv_back)
	public void onBack() {
		getActivity().finish();
	}

	@OnClick(R.id.paybtn)
	public void onPay() {
		startActivity(OrderDetailActivity.class);
		getActivity().overridePendingTransition(R.anim.slide_right_in,
				R.anim.slide_left_out);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fra_cart_new;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private boolean flag;

	@Override
	public void initView() {
		//		if (flag) {
		//			//修改套餐后刷新界面
		//			initListener();
		//			total();
		//			mAdapter.notifyDataSetChanged();
		//			return;
		//		}
		//		flag = true;
		init();
		initData();
		initListener();
		initRecyclerView();
	}

	private List<GroupEntity> datas = new ArrayList<>();

	private List<GroupEntity> initData() {
		datas.clear();
		if (Cart.getInstance().getMarketDishList() != null) {
			for (CartDish cartDish : Cart.getInstance().getMarketDishList()) {
				for (CartDish cartDish2 : Cart.getInstance().getCartDishes()) {
					if (cartDish.getDishID().equals(cartDish2.getDishID())) {
						cartDish2.setTemp_price(cartDish.getDiscountAmount());
					}
				}
			}
		}

		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			boolean isAdd = false;
			for (GroupEntity entity : datas) {
				if (entity.getHeader().equals(cartDish.getDishKindStr())) {
					entity.getChildren().add(cartDish);
					isAdd = true;
					break;
				}
			}
			if (!isAdd) {
				List<CartDish> cartDishes = new ArrayList<>();
				cartDishes.add(cartDish);
				GroupEntity entity = new GroupEntity(cartDish.getDishKindStr(), "", cartDishes);
				datas.add(entity);
			}
		}
		return datas;
	}

	private NoFooterAdapter mAdapter;

	private void initRecyclerView() {
		rvList = (RecyclerView) rootView.findViewById(R.id.recycler);
		stickyLayout = (StickyHeaderLayout) rootView.findViewById(R.id.sticky_layout);

		rvList.setLayoutManager(new LinearLayoutManager(mContext));
		mAdapter = new NoFooterAdapter(mContext, datas);
		mAdapter.setOnHeaderClickListener(new GroupedRecyclerViewAdapter.OnHeaderClickListener() {
			@Override
			public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
			                          int groupPosition) {
				Toast.makeText(mContext, "组头：groupPosition = " + groupPosition,
						Toast.LENGTH_LONG).show();
				Log.e("eee", adapter.toString() + "  " + holder.toString());
			}
		});

		mAdapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
			@Override
			public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
			                         int groupPosition, int childPosition) {
				ImageView dishPhoto = holder.get(R.id.dish_photo);
				CartDish  cartDish  = datas.get(groupPosition).getChildren().get(childPosition);
				if (CartDishHelper.hasOption(cartDish)) {
					Fragment dialog1 = getFragmentManager()
							.findFragmentByTag("SmarantOptionDialog");
					if (dialog1 != null)
						return;
					UIDish cloneDish = (UIDish) SmarantDataProvider
							.getDishById(cartDish.getDishID()).myclone();
					OptionDialog dialog = OptionDialog
							.newInstance(cloneDish, cartDish, true, false);
					dialog.show(getFragmentManager(), "SmarantOptionDialog");
				} else if (CartDishHelper.isPackage(cartDish)) {
					UIDish cloneDish = (UIDish) SmarantDataProvider
							.getDishById(cartDish.getDishID())
							.myclone();
					PackageFragmentNew packageFragment = PackageFragmentNew
							.newInstance(cloneDish, cartDish, true);
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
							.addSharedElement(dishPhoto, "dishphoto")
							.replace(R.id.container, packageFragment)
							.addToBackStack("cartFragment")
							.commit();
				}
			}
		});
		rvList.setAdapter(mAdapter);

	}

	private void init() {
		total();
	}


	/**
	 * 计算总价钱
	 */
	private void total() {
		paymoney.setText("￥" + String.valueOf(Price.getInstance().getTotal()));

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

	}


	@Bind({R.id.recycler})
	RecyclerView recycler;


	private void initListener() {

		mRxManager.on(AppConstant.ON_CART_ITEM_CHANGE, new Action1<Integer>() {
			@Override
			public void call(Integer o) {
				if (Cart.getInstance().getCartCount() == 0)
					clearCart();
				else {
					total();
				}
			}
		});

		//		mRxManager.on(AppConstant.ON_CART_ITEM_CHANGE, new Action1<String>() {
		//			@Override
		//			public void call(String o) {
		//				if (Cart.getInstance().getCartCount() == 0)
		//					clearCart();
		//				else {
		//					total();
		//					mAdapter.notifyDataSetChanged();
		//				}
		//			}
		//		});
		mRxManager.on(AppConstant.ON_CART_ITEM_CHANGE_OPTIONDIALOG, new Action1<String>() {
			@Override
			public void call(String o) {
				if (Cart.getInstance().getCartCount() == 0)
					clearCart();
				else {
					total();
					mAdapter.notifyDataSetChanged();
				}
			}
		});
	}
}
