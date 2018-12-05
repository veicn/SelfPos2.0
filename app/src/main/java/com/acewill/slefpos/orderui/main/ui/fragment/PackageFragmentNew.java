package com.acewill.slefpos.orderui.main.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseFragment;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.orderui.main.ui.adapter.TabAdapter;
import com.acewill.slefpos.orderui.main.ui.adapter.TaoCanDishAdapter;
import com.acewill.slefpos.orderui.main.ui.adapter.TaoCanSelectAdapter;
import com.acewill.slefpos.orderui.main.ui.dialog.OptionDialog;
import com.acewill.slefpos.orderui.main.ui.eventbus.OnSelectDishChange;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.widgetlibrary.MyGridView;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/2/7 15:29
 * Desc：
 */
public class PackageFragmentNew extends BaseFragment {


	private static final String TAG = "PackageFragment";

	@OnClick(R.id.select_ok)
	public void selectOk() {
		for (int i = 0; i < mUIPackageItems.size(); i++) {
			UIPackageItem bean = mUIPackageItems.get(i);
			if (!bean.isSelectOk() && bean.getSelectCount() < bean.getMinQty()) {
				currentTab = bean;
				mTabAdapter.resetSelect(currentTab);
				changeDishList(i, currentTab);
				ToastUitl
						.showLong(mActivity, !TextUtils.isEmpty( bean.getUserdefinedName()) ? bean
								.getUserdefinedName() : bean.getItemName() + "至少选" + bean
								.getMinQty() + "项");
				return;
			}
		}
		if (mSelectAdapter.getDatas() == null) {
			ToastUitl.showLong(mContext,"请选择菜品!");
			return;
		}

		List<UIPackageOptionItem>      uiPackageOptionItems = new ArrayList<>();
		ArrayList<UIPackageOptionItem> datas                = mSelectAdapter.getDatas();
		uiPackageOptionItems.addAll(datas);
		if (isChange) {
			mCartDish.setSubItemList(uiPackageOptionItems);
			mCartDish.setCost(Price.getInstance().getDishCost(mCartDish));
			mRxManager.post(AppConstant.ON_CART_ITEM_CHANGE_OPTIONDIALOG, "PACKAGEITEMCHANGE");
		} else {
			mUIDish.setSubItemList(uiPackageOptionItems);
			mUIDish.setQuantity(1);
			Cart.getInstance().addDish(mUIDish);
		}
		back();
	}


	@OnClick(R.id.iv_back)
	public void back() {
		FragmentManager manager = getFragmentManager();
		int count = manager
				.getBackStackEntryCount();//获取退回栈的Fragment的个数
		FragmentManager.BackStackEntry backStack = manager
				.getBackStackEntryAt(manager
						.getBackStackEntryCount() - 1);//获取退回栈的上一个Fragment的tag
		// 获取当前栈顶的Fragment的标记值
		String tag = backStack.getName();
		manager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);//立刻退回到上一个Fragment
	}


	/**
	 * @param dish
	 * @param cartDish
	 * @param flag     @return 是否修改CartDish
	 */
	public static PackageFragmentNew newInstance(UIDish dish, CartDish cartDish, boolean flag) {
		PackageFragmentNew fragment = new PackageFragmentNew();
		Bundle             bundle   = new Bundle();
		bundle.putSerializable("uiDish", dish);
		bundle.putParcelable("cartDish", cartDish);
		bundle.putBoolean("flag", flag);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.act_package_new;
	}

	@Override
	public void initPresenter() {

	}


	@Override
	protected void initView() {
		initListViewAndAdapter();
		initData();
		initListener();
		initUITasteOptionListener();
	}

	@Bind(R.id.tab_list)
	MyGridView   tab_list;
	@Bind(R.id.dish_list)
	MyGridView   dish_list;
	@Bind(R.id.choose_list)
	MyGridView   choose_list;
	@Bind(R.id.arrow_down)
	ImageView    arrow_down;
	@Bind(R.id.activity_taocan_food_iv)
	ImageView    activity_taocan_food_iv;
	@Bind(R.id.activity_taocan_food_name_tv)
	TextView     activity_taocan_food_name_tv;
	@Bind(R.id.activity_taocan_food_cost_tv)
	TextView     activity_taocan_food_cost_tv;
	@Bind(R.id.seleced_ly)
	LinearLayout seleced_ly;


	private void initListViewAndAdapter() {
		mSelectAdapter = new TaoCanSelectAdapter(mContext,mRxManager);
		choose_list.setAdapter(mSelectAdapter);
		mTaoCanDishAdapter = new TaoCanDishAdapter(mContext);
		dish_list.setAdapter(mTaoCanDishAdapter);
		mTabAdapter = new TabAdapter(mContext);
		tab_list.setAdapter(mTabAdapter);
	}

	private void initUITasteOptionListener() {
		mRxManager.on(AppConstant.ON_PACKAGEITEM_OPTION_SELECT_OK, new Action1<UIDish>() {
			@Override
			public void call(UIDish dish) {
				mUIPackageOptionItem.setOptionList(dish.getOptionList());
			}
		});
	}

	private void showOptionDialog(UIPackageOptionItem packageItem) {

		//构造Dish
		UIDish cloneDish = new UIDish();
		cloneDish.setImageName(packageItem.getImageName());
		cloneDish.setPrice(packageItem.getPrice());
		cloneDish.setDishCount(packageItem.getCount());
		cloneDish.setDishUnit(packageItem.getUnit());
		cloneDish.setDishName(packageItem.getDishName());
		cloneDish.setOptionCategoryList(UIDataProvider
				.getUIDIshOptionCategoryById(packageItem.getDishID()));
		Fragment dialog1 = getFragmentManager()
				.findFragmentByTag("SmarantOptionDialog");
		if (dialog1 != null)
			return;
		OptionDialog dialog = OptionDialog.newInstance(cloneDish, null, false, true);
		dialog.show(getFragmentManager(), "SmarantOptionDialog");
	}

	private UIDish              mUIDish;
	private TaoCanSelectAdapter mSelectAdapter;
	private List<UIPackageItem> mUIPackageItems;
	private boolean             isChange;
	private CartDish            mCartDish;
	@Bind(R.id.dish_photo)
	ImageView dishImageView;

	private void initData() {
		mUIDish = (UIDish) getArguments().getSerializable("uiDish");
		Glide.with(mContext).load(mUIDish.getImageName()).crossFade().fitCenter()
				.placeholder(R.mipmap.default_img).error(R.mipmap.default_img)
				.into(dishImageView);
		mUIPackageItems = mUIDish.getPackageItems();
		isChange = getArguments().getBoolean("flag");
		mCartDish = getArguments().getParcelable("cartDish");
		for (UIPackageItem bean : mUIPackageItems) {
			bean.setDishKind(mUIDish.getDishKind());
			bean.setDishKindStr(mUIDish.getDishKindStr());
			bean.setQuantity(1);
			if ((bean.getIsMust() == 1 && bean.getSelectCount() == 0)) {
				//必选
				//加入到选择商品列表
				for (UIPackageOptionItem bean1 : bean.getOptions()) {
					List<UIPackageOptionItem> options = bean.getSelectedOptions();
					if (options == null) {
						options = new ArrayList<>();
					}
					options.add(bean1);//加入到选择的列表
					mSelectAdapter.addData(bean1);
					bean.setQuantity(bean.getQuantity() + 1);
					bean.setSelectOk(true);//该小类下的菜都是必选项
					bean1.setSelect(true);
					bean1.setQuantity(1);
					bean1.setCannoclick(true);
				}
			}
			if (bean.getMinQty() == 0) {
				bean.setSelectOk(true);//没有必选项
			}
		}

		if (mSelectAdapter.getCount()>0){
			seleced_ly.setVisibility(View.VISIBLE);
			mSelectAdapter.notifyDataSetChanged();
		}
		initTabView(mUIPackageItems);
		initDishView(mUIPackageItems.get(0));
		initSelectView();
		initRegister();
	}

	private void initRegister() {
		mRxManager.on(AppConstant.TAOCANSELCETDISHCOUNT, new Action1<Integer>() {
			@Override
			public void call(Integer integer) {
				if (integer > 0) {
					seleced_ly.setVisibility(View.VISIBLE);
				} else {
					seleced_ly.setVisibility(View.GONE);
				}
			}
		});
	}

	private UIPackageItem currentTab;
	private TabAdapter    mTabAdapter;

	private void initTabView(final List<UIPackageItem> datas) {

		tab_list.setNumColumns(datas.size());
		tab_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				currentTab = datas.get(position);
				mTabAdapter.resetSelect(currentTab);
				changeDishList(position, currentTab);
			}
		});
		mTabAdapter.setData(datas);
		currentTab = datas.get(0);
	}

	private TaoCanDishAdapter mTaoCanDishAdapter;

	private void changeDishList(int position, UIPackageItem bean) {
		mTaoCanDishAdapter.setData(mUIDish.getPackageItems().get(position).getOptions(), bean);
	}

	private void initDishView(final UIPackageItem bean) {
		ArrayList<UIPackageOptionItem> packageBeans = new ArrayList<>();
		packageBeans.addAll(bean.getOptions());
		mTaoCanDishAdapter.setData(packageBeans, bean);
	}

	private void initSelectView() {
		if (mUIDish.getSubItemList() != null && mUIDish.getSubItemList().size() > 0) {
			ArrayList<UIPackageOptionItem> mPackageBeanList = new ArrayList<>();
			mPackageBeanList.addAll(mUIDish.getSubItemList());
			mSelectAdapter.setData(mPackageBeanList);
			mTabAdapter.resetSelect(currentTab);
			changeDishList(0, currentTab);
			mUIDish.setSubItemList(null);
		}
	}

	private void removeBean(UIPackageOptionItem bean) {
		mSelectAdapter.removeData(bean);
		if (bean.getQuantity() == 0)
			currentTab.setSelectCount(currentTab.getSelectCount() - 1);
		else
			currentTab.setSelectCount(currentTab.getSelectCount() - bean.getQuantity());
		if (bean.getOptionList() != null && bean
				.getOptionList().size() > 0) {
			for (UITasteOption option : bean.getOptionList()) {
				if (option.getSourceType().equals("M")) {
					option.setQuantity(bean.getQuantity());
				}
			}
		}
		boolean select = bean.isSelect();
		bean.setSelect(!select);
		//		bean.showAdd = false;
		bean.setQuantity(0);
		//		Iterator<PackageBean> iterator = currentTab.selectedOptions.iterator();
		//		while (iterator.hasNext()) {
		//			if (iterator.next().dishID.equals(bean.dishID)) {
		//				iterator.remove();
		//				break;
		//			}
		//		}
		setUIPackageItemSelect(currentTab);
		mTaoCanDishAdapter.notifyDataSetChanged();
		mSelectAdapter.notifyDataSetChanged();
	}

	//	private UIPackageOptionItem currentPackageBean;

	private void replacePackageBean(UIPackageOptionItem newBean) {
		ArrayList<UIPackageOptionItem> datas = mSelectAdapter.getDatas();
		//		if (datas == null || datas.size() == 0)
		//			return;
		if (datas == null)
			return;
		Iterator<UIPackageOptionItem> iterator = datas.iterator();
		while (iterator.hasNext()) {
			UIPackageOptionItem oldBean = iterator.next();
			if (oldBean.getItemID().equals(newBean.getItemID())) {
				//曾经选过
				oldBean.setSelect(false);
				oldBean.setQuantity(0);
				iterator.remove();
				newBean.setSelect(true);
				newBean.setQuantity(1);
				datas.add(newBean);
				mSelectAdapter.notifyDataSetChanged();
				mTaoCanDishAdapter.notifyDataSetChanged();
				return;
			}
		}
		//		如果没有选过
		currentTab.setSelectCount(currentTab.getSelectCount() + 1);
		newBean.setSelect(true);
		newBean.setQuantity(1);
		mTaoCanDishAdapter.setSelectOk(true);
		mSelectAdapter.addData(newBean);
		mSelectAdapter.notifyDataSetChanged();
		mTaoCanDishAdapter.notifyDataSetChanged();

	}

	private UIPackageOptionItem mUIPackageOptionItem;

	public void initListener() {
		mRxManager.on(AppConstant.ONSElECTDISHCHANGE, new Action1<OnSelectDishChange>() {
			@Override
			public void call(OnSelectDishChange change) {
				mUIPackageOptionItem = change.getBean();
				//				bean = getUIPackageOptionItemById(bean.getItemID());
				if (change.getType() == 0) {
					if (change.isAddDish()) {
						removeBean(mUIPackageOptionItem);
					} else {
						//						currentPackageBean = bean;
						if (currentTab.getMinQty() == 1 && currentTab.getMaxQty() == 1 && currentTab
								.isSelectOk()) {
							replacePackageBean(mUIPackageOptionItem);
							//如果本来就之能选择一个，那么就删除原来已经选择的，然后选择这个
							UIDish model = UIDataProvider
									.getDishByDishId(mUIPackageOptionItem.getDishID());
							if (model != null) {
								//								model.currentPrice = Cart.isMember ? model.memberPrice : model.price;
								if (UIDataProvider.isDishHaveOption(model.getDishID())) {
									showOptionDialog(mUIPackageOptionItem);
								}
							}

						} else if (currentTab.getSelectCount() < currentTab.getMaxQty()) {
							UIDish model = UIDataProvider
									.getDishByDishId(mUIPackageOptionItem.getDishID());
							if (model != null) {
								//								model.currentPrice = Cart.isMember ? model.memberPrice : model.price;
								if (UIDataProvider.isDishHaveOption(model.getDishID())) {
									showOptionDialog(mUIPackageOptionItem);
								}
							}
							mSelectAdapter.addData(mUIPackageOptionItem);
							currentTab.setSelectCount(currentTab.getSelectCount() + 1);

							setUIPackageItemSelect(currentTab);
							mUIPackageOptionItem.setQuantity(1);
							boolean select = mUIPackageOptionItem.isSelect();
							mUIPackageOptionItem.setSelect(!select);
							mTaoCanDishAdapter.notifyDataSetChanged();
							mSelectAdapter.notifyDataSetChanged();
						} else {
							ToastUitl
									.showShort(mActivity, "最多能选" + currentTab.getMaxQty() + "项");
						}
					}
				} else {
					//选中，添加
					if (change.isadd()) {
						if (currentTab.getSelectCount() == currentTab.getMaxQty()) {
							ToastUitl
									.showShort(mActivity, "最多能选" + currentTab.getMaxQty() + "项");
							return;
						}
						mUIPackageOptionItem.setQuantity(mUIPackageOptionItem.getQuantity() + 1);

						currentTab.setSelectCount(currentTab.getSelectCount() + 1);
						//				currentTab.selectedOptions.add(bean);
						mSelectAdapter.notifyDataSetChanged();
						mTaoCanDishAdapter.notifyDataSetChanged();
						if (currentTab.getSelectCount() == currentTab.getMaxQty()) {
							mTaoCanDishAdapter.setSelectOk(true);
						}
					} else {
						mUIPackageOptionItem.setQuantity(mUIPackageOptionItem.getQuantity() - 1);

						if (mUIPackageOptionItem.getQuantity() == 0)
							removeBean(mUIPackageOptionItem);
						else {
							mTaoCanDishAdapter.setSelectOk(false);
							currentTab.setSelectCount(currentTab.getSelectCount() - 1);
							mTaoCanDishAdapter.notifyDataSetChanged();
							mSelectAdapter.notifyDataSetChanged();
						}
					}
					if (mUIPackageOptionItem.getOptionList() != null && mUIPackageOptionItem
							.getOptionList().size() > 0) {
						for (UITasteOption option : mUIPackageOptionItem.getOptionList()) {
							if (option.getSourceType().equals("M")) {
								option.setQuantity(mUIPackageOptionItem.getQuantity());
							}
						}
					}
				}
			}
		});

		mRxManager
				.on(AppConstant.SELECT_DISH_FROM_PACKAGE_CART, new Action1<UIPackageOptionItem>() {
					@Override
					public void call(UIPackageOptionItem item) {
						Map<Integer, UIPackageItem> map = getUIPackageItemByItemId(item
								.getItemID());
						for (Map.Entry<Integer, UIPackageItem> entry : map.entrySet()) {
							//			System.out.println(entry.getKey() + ":" + entry.getValue());
							currentTab = entry.getValue();
							mTabAdapter.resetSelect(currentTab);
							changeDishList(entry.getKey(), currentTab);
						}
					}
				});

		mRxManager
				.on(AppConstant.REMOVE_DISH_FROM_PACKAGE_CART, new Action1<UIPackageOptionItem>() {
					@Override
					public void call(UIPackageOptionItem item) {
						//				PackageBean                   bean = change.getBean();
						Map<Integer, UIPackageItem> map = getUIPackageItemByItemId(item
								.getItemID());
						for (Map.Entry<Integer, UIPackageItem> entry : map.entrySet()) {
							//			System.out.println(entry.getKey() + ":" + entry.getValue());
							UIPackageItem packageItemBean = entry.getValue();
							longClickToRemoveBean(packageItemBean, item);
						}
					}
				});
	}

	private UIPackageOptionItem getUIPackageOptionItemById(String id) {
		for (UIPackageOptionItem item : currentTab.getOptions()) {
			if (item.getItemID().equals(id))
				return item;
		}
		return null;
	}

	private void longClickToRemoveBean(UIPackageItem packageItemBean, UIPackageOptionItem bean) {
		mSelectAdapter.removeData(bean);
		if (bean.getQuantity() == 0)
			packageItemBean.setSelectCount(packageItemBean.getSelectCount() - 1);
		else
			packageItemBean.setSelectCount(packageItemBean.getSelectCount() - bean.getQuantity());
		boolean select = bean.isSelect();
		bean.setSelect(!select);
		bean.setQuantity(0);
		setUIPackageItemSelect(packageItemBean);
		mTaoCanDishAdapter.notifyDataSetChanged();
		mSelectAdapter.notifyDataSetChanged();
	}

	/**
	 * 设置是否已经选好
	 *
	 * @param packageItemBean
	 */
	private void setUIPackageItemSelect(UIPackageItem packageItemBean) {
		if (packageItemBean.getSelectCount() == 0) {
			//不论同步时还是智慧快餐
			packageItemBean.setSelectOk(false);
		} else if (packageItemBean.getMinQty() == packageItemBean.getMaxQty()) {
			//不论同步时还是智慧快餐
			if (packageItemBean.getSelectCount() == packageItemBean
					.getMinQty() || packageItemBean
					.getSelectCount() == packageItemBean.getMaxQty()) {
				mTaoCanDishAdapter.setSelectOk(true);
			} else {
				mTaoCanDishAdapter.setSelectOk(false);
			}
		} else {
			//同步时的
			if (packageItemBean.getSelectCount() == packageItemBean.getMinQty()) {
				mTaoCanDishAdapter.setSelectOk(false);
			} else {
				mTaoCanDishAdapter.setSelectOk(true);
			}
		}
	}

	public Map<Integer, UIPackageItem> getUIPackageItemByItemId(String itemID) {
		Map<Integer, UIPackageItem> map = new HashMap<>();
		for (int i = 0; i < mUIPackageItems.size(); i++) {
			UIPackageItem packageItemBean = mUIPackageItems.get(i);
			if (packageItemBean.getItemID().equals(itemID)) {
				map.put(i, packageItemBean);
				return map;
			}
		}
		return null;
	}

	//	@Subscribe(threadMode = ThreadMode.MAIN)
	//	public void onSelectDishChange(OnSelectDishChange change) {
	//
	//	}

	@OnClick({R.id.arrow_down, R.id.arrow_layout})
	public void hideCart() {
		choose_list.setVisibility(choose_list
				.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
		arrow_down.setImageDrawable(choose_list
				.getVisibility() == View.VISIBLE ? getResources()
				.getDrawable(R.mipmap.arrow_down_n) : getResources()
				.getDrawable(R.mipmap.arrow_up_n));
	}

}
