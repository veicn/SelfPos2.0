//package com.acewill.slefpos.orderui.main.ui.fragment;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//
//import com.acewill.slefpos.R;
//import com.acewill.slefpos.app.AppConstant;
//import com.acewill.slefpos.base.BaseFragment;
//import com.acewill.slefpos.bean.cartbean.Cart;
//import com.acewill.slefpos.bean.cartbean.CartDish;
//import com.acewill.slefpos.bean.uibean.UIDish;
//import com.acewill.slefpos.bean.uibean.UIPackageItem;
//import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
//import com.acewill.slefpos.orderui.main.ui.dialog.SmarantOptionDialog;
//import com.acewill.slefpos.orderui.main.packageadapter.ItemClickListener;
//import com.acewill.slefpos.orderui.main.packageadapter.SectionedExpandableLayoutHelper;
//import com.jaydenxiao.common.commonutils.LogUtils;
//import com.jaydenxiao.common.commonutils.ToastUitl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//import rx.functions.Action1;
//
///**
// * Author：Anch
// * Date：2018/2/7 15:29
// * Desc：
// */
//public class PackageFragment extends BaseFragment implements ItemClickListener {
//
//
//	private static final String TAG = "PackageFragment";
//	@Bind(R.id.select_ok)
//	FrameLayout select_ok;
//	@Bind(R.id.iv_back)
//	FrameLayout iv_back;
//
//
//	@OnClick(R.id.select_ok)
//	public void selectOk() {
//		//todo 单品的定制项
//		for (UIPackageItem item : items) {
//			if (item.getItemCount() != item.getSelectCount()) {
//				ToastUitl.showShort(mContext, item.getItemName() + "至少选" + item
//						.getItemCount() + "项");
//				return;
//			}
//		}
//		dish.setSubItemList(mPackageOptionItems);
//
//		if (flag) {
//			cartDish.setSubItemList(dish.getSubItemList());
//		} else
//			Cart.getInstance().addDish(dish);
//		back();
//	}
//
//
//	@OnClick(R.id.iv_back)
//	public void back() {
//		FragmentManager manager = getFragmentManager();
//		int count = manager
//				.getBackStackEntryCount();//获取退回栈的Fragment的个数
//		FragmentManager.BackStackEntry backStack = manager
//				.getBackStackEntryAt(manager
//						.getBackStackEntryCount() - 1);//获取退回栈的上一个Fragment的tag
//		// 获取当前栈顶的Fragment的标记值
//		String tag = backStack.getName();
//		manager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);//立刻退回到上一个Fragment
//	}
//
//
//	/**
//	 * @param dish
//	 * @param cartDish
//	 * @param flag     @return 是否修改CartDish
//	 */
//	public static PackageFragment newInstance(UIDish dish, CartDish cartDish, boolean flag) {
//		PackageFragment fragment = new PackageFragment();
//		Bundle          bundle   = new Bundle();
//		bundle.putParcelable("dish", dish);
//		bundle.putParcelable("cartDish", cartDish);
//		bundle.putBoolean("flag", flag);
//		fragment.setArguments(bundle);
//		return fragment;
//	}
//
//	@Override
//	protected int getLayoutResource() {
//		return R.layout.act_package;
//	}
//
//	@Override
//	public void initPresenter() {
//
//	}
//
//	RecyclerView mRecyclerView;
//
//	private UIDish   dish;
//	private CartDish cartDish;
//	private boolean  flag;
//	List<UIPackageItem> items;
//
//	@Override
//	protected void initView() {
//		//setting the recycler view
//		mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
//		dish = getArguments().getParcelable("dish");
//		cartDish = getArguments().getParcelable("cartDish");
//		flag = getArguments().getBoolean("flag");
//		SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(mContext,
//				mRecyclerView, this, 4, dish);
//		items = dish.getPackageItems();
//		for (UIPackageItem item : items) {
//			item.setExpanded(true);
//			List<UIPackageOptionItem> options = item.getOptions();
//			sectionedExpandableLayoutHelper.addSection(item, options);
//		}
//		sectionedExpandableLayoutHelper.notifyDataSetChanged();
//		initListener();
//	}
//
//	private void initListener() {
//		mRxManager.on(AppConstant.ON_PACKAGEITEM_OPTION_SELECT_OK, new Action1<UIDish>() {
//			@Override
//			public void call(UIDish dish) {
//				mPackageOptionItem.setOptionList(dish.getOptionList());
//				addPackagetionItem(mPackageOptionItem);
//				changeSelectStatu(mPackageOptionItem);
//			}
//		});
//	}
//
//	private ArrayList<UIPackageOptionItem> mPackageOptionItems = new ArrayList<>();
//
//	private UIPackageOptionItem mPackageOptionItem;
//	private View                mClickView;
//	private UIPackageItem       mPackageItem;
//
//	@Override
//	public void itemClicked(View view, final UIPackageOptionItem packageOptionItem, final UIPackageItem packageItem) {
//		//		Toast.makeText(mContext, "PackageOptionItem: " + packageOptionItem
//		//				.getDishName() + " clicked", Toast.LENGTH_SHORT).show();
//		mClickView = view;
//		mPackageItem = packageItem;
//		/**
//		 * 判断是否已选
//		 *   1、未选
//		 *       判断选择的个数已够
//		 *       设置已选数量为1
//		 *       设置该类的数量+1
//		 *
//		 *    2、已选
//		 *        设置已选数量为0
//		 *        设置该类数量减去之前该项已选的数量
//		 */
//		if (!packageOptionItem.isSelect()) {
//			if (packageItem.getSelectCount() == packageItem.getItemCount()) {
//				return;
//			}
//			// TODO: 2018/2/23 anch 如果单品有定制项
//			if (packageOptionItem.getOptionCategoryList() != null && packageOptionItem
//					.getOptionCategoryList().size() > 0) {
//				mPackageOptionItem = packageOptionItem;
//				showOptionDialog(mPackageOptionItem);
//				return;
//			}
//			addPackagetionItem(packageOptionItem);
//		} else {
//			packageItem.setSelectCount(packageItem.getSelectCount() - packageOptionItem
//					.getSelectCount());
//			packageOptionItem.setSelectCount(0);
//			mPackageOptionItems.remove(packageOptionItem);
//		}
//		changeSelectStatu(packageOptionItem);
//	}
//
//	/**
//	 * 将packageOptionItem添加到packageOptionItems
//	 *
//	 * @param packageOptionItem
//	 */
//	private void addPackagetionItem(UIPackageOptionItem packageOptionItem) {
//		packageOptionItem.setSelectCount(1);
//		mPackageItem.setSelectCount(mPackageItem.getSelectCount() + 1);
//		mPackageOptionItems.add(packageOptionItem);
//	}
//
//	/**
//	 * @param packageOptionItem
//	 */
//	private void changeSelectStatu(final UIPackageOptionItem packageOptionItem) {
//
//		//反向设置状态
//		packageOptionItem.setSelect(!packageOptionItem.isSelect());
//		/**
//		 * 设置显示状态
//		 *
//		 * 单品的
//		 *
//		 *packageOptionItem.getSelectCount() + "");
//		 add_count_ly.setVisibility(packageItem.getItemType() == 1 && packageOptionItem
//		 .isSelect() && packageItem.getItemCount() != 1
//		 */
//		mClickView.findViewById(R.id.item_bg)
//				.setVisibility(packageOptionItem.isSelect() ? View.VISIBLE : View.GONE);
//		mClickView.findViewById(R.id.dish_name)
//				.setVisibility(mPackageItem.getItemType() == 1 && packageOptionItem
//						.isSelect() && mPackageItem.getItemCount() != 1 ? View.GONE : View.VISIBLE);
//		View           add_count_ly = mClickView.findViewById(R.id.add_count_ly);
//		final TextView tv_counts    = (TextView) mClickView.findViewById(R.id.tv_counts);
//		tv_counts.setText(packageOptionItem.getSelectCount() + "");
//		add_count_ly.setVisibility(mPackageItem.getItemType() == 1 && packageOptionItem
//				.isSelect() && mPackageItem.getItemCount() != 1 ? View.VISIBLE : View.GONE);
//		add_count_ly.findViewById(R.id.iv_minus_counts)
//				.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						if (packageOptionItem.getSelectCount() == 1) {
//							//去除该选项
//						} else {
//							int selectCount = packageOptionItem.getSelectCount() - 1;
//							packageOptionItem.setSelectCount(selectCount);
//							tv_counts.setText(selectCount + "");
//							mPackageItem.setSelectCount(mPackageItem.getSelectCount() - 1);
//							ToastUitl.showShort(mContext, mPackageItem.getSelectCount() + "");
//						}
//
//					}
//				});
//
//
//		add_count_ly.findViewById(R.id.iv_plus_counts)
//				.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						if (mPackageItem.getItemCount() == mPackageItem.getSelectCount())
//							return;
//						int selectCount = packageOptionItem.getSelectCount() + 1;
//						packageOptionItem.setSelectCount(selectCount);
//						tv_counts.setText(selectCount + "");
//						mPackageItem.setSelectCount(mPackageItem.getSelectCount() + 1);
//						ToastUitl.showShort(mContext, mPackageItem.getSelectCount() + "");
//					}
//				});
//	}
//
//
//	private void showOptionDialog(UIPackageOptionItem packageItem) {
//
//		//构造Dish
//		UIDish cloneDish = new UIDish();
//		cloneDish.setImageName(packageItem.getImageName());
//		cloneDish.setPrice(packageItem.getPrice());
//		cloneDish.setDishCount(packageItem.getCount());
//		cloneDish.setDishUnit(packageItem.getUnit());
//		cloneDish.setDishName(packageItem.getDishName());
//		cloneDish.setOptionCategoryList(packageItem.getOptionCategoryList());
//		Fragment dialog1 = getFragmentManager()
//				.findFragmentByTag("SmarantOptionDialog");
//		if (dialog1 != null)
//			return;
//		SmarantOptionDialog dialog = SmarantOptionDialog.newInstance(cloneDish, null, false, true);
//		dialog.show(getFragmentManager(), "SmarantOptionDialog");
//	}
//
//	@Override
//	public void itemClicked(View view, UIPackageItem section) {
//		//		Toast.makeText(mContext, "PackageItem: " + section
//		//				.getItemName() + " clicked", Toast.LENGTH_SHORT)
//		//				.show();
//		LogUtils.loge(TAG, section.getItemID() + section.getItemType() + section
//				.getItemCount() + "");
//	}
//}
