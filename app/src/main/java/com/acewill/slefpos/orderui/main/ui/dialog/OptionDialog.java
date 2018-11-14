package com.acewill.slefpos.orderui.main.ui.dialog;

import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIOptionCategory;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.orderui.main.ui.eventbus.OnCartItemChangeOptionDialog;
import com.acewill.slefpos.orderui.main.uidataprovider.UIDataProvider;
import com.acewill.slefpos.orderui.main.utils.TextColorUtils;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Anch
 * Date：2018/2/5 11:10
 * Desc：
 */
public class OptionDialog extends BaseDialog {
	//	private CommonRecycleViewAdapter<UITasteOption> adapter;
	private boolean  wantChange;
	private CartDish cartDish;
	private boolean  fromPackage;
	private int      originalQuantity;

	/**
	 * @param dish
	 * @param wantChange
	 * @return
	 */
	public static OptionDialog newInstance(UIDish dish, CartDish cartDish, boolean wantChange, boolean fromPackage) {
		OptionDialog fragment = new OptionDialog();
		Bundle       bundle   = new Bundle();
		bundle.putParcelable("dish", dish);
		bundle.putParcelable("cartDish", cartDish);
		bundle.putBoolean("flag", wantChange);
		bundle.putBoolean("fromPackage", fromPackage);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NORMAL, R.style.bottomDialogStyle);
	}

	@Bind(R.id.container)
	LinearLayout container;
	@Bind(R.id.tv_option_choose)
	TextView     tv_option_choose;
	@Bind(R.id.tv_tips)
	TextView     tv_tips;
	@Bind(R.id.dish_photo)
	ImageView    dish_photo;
	@Bind(R.id.dish_price)
	TextView     dish_price;
	@Bind(R.id.dish_count)
	TextView     dish_count;
	@Bind(R.id.tv_counts)
	TextView     tv_counts;
	@Bind(R.id.iv_plus_counts)
	ImageView    iv_plus_counts;
	@Bind(R.id.iv_minus_counts)
	ImageView    iv_minus_counts;
	@Bind(R.id.dish_name)
	TextView     dish_name;

	@Bind(R.id.mix_ly)
	RelativeLayout mix_ly;
	@Bind(R.id.iv_mix_minus_counts)
	ImageView      iv_mix_minus_counts;
	@Bind(R.id.tv_mix_counts)
	TextView       tv_mix_counts;
	@Bind(R.id.iv_mix_plus_counts)
	ImageView      iv_mix_plus_counts;
	@Bind(R.id.div_mix_view)
	View           div_mix_view;

	@Bind(R.id.tv_ok)
	TextView tv_ok;

	@Bind(R.id.counts_ly)
	RelativeLayout counts_ly;
	private int counts           = 1;
	private int repertory_counts = 1;


	private void refreshPrice() {
		if (fromPackage) {
			tv_ok.setText("确定");
		} else {
			tv_ok.setText("确定（小计￥" + PriceUtil
					.multiply(Price.getInstance().getDishCost(dish), counts) + ")");
		}
	}

	@OnClick(R.id.option_view)
	public void onViewClick() {
		TimeConfigure.resetScreenTime();
	}


	@OnClick(R.id.iv_minus_counts)
	public void MinusCounts() {
		TimeConfigure.resetScreenTime();
		if (counts == 1) {
			return;
		} else {
			counts--;
			if (counts == 1)
				iv_minus_counts.setImageResource(R.mipmap.icon_minus_light);
			tv_counts.setText(counts + "");
			refreshPrice();
			excuteAnimation(tv_counts);
		}
	}

	@OnClick(R.id.iv_mix_minus_counts)
	public void MinusMixCounts() {
		TimeConfigure.resetScreenTime();
		if (mixList.size() == 0)
			return;
		String lastKey = "";
		for (String key : mixList.keySet()) {
			lastKey = key;
		}
		UITasteOption option = getUItasteOptionById(lastKey, mixList.get(lastKey));
		if (option.getQuantity() > 1) {
			option.setQuantity(option.getQuantity() - 1);
			tv_mix_counts.setText(option.getQuantity() + "");
			CommonRecycleViewAdapter<UITasteOption> adapter = getAdapterByCategoryId(option
					.getCategoryId());
			adapter.notifyDataSetChanged();
			refreshPrice();
			excuteAnimation(tv_mix_counts);
		}
	}

	/**
	 * 通过UITasteCategoryId 和 optionId 去获取optioin
	 * 注意 不能只通过optionId 去获取UItasteOption 因为这个值不是唯一的，不同的category可能会有相同的optionID
	 *
	 * @param optionCatoryId
	 * @param optionId
	 * @return
	 */
	private UITasteOption getUItasteOptionById(String optionId, String optionCatoryId) {
		for (UIOptionCategory category : mOptionCategoryListist) {
			for (UITasteOption option : category.getOptionList()) {
				if (option.getCategoryId().equals(optionCatoryId) && option.getId()
						.equals(optionId)) {
					return option;
				}
			}
		}
		return null;
	}

	@OnClick(R.id.iv_mix_plus_counts)
	public void PlusMixCounts() {
		TimeConfigure.resetScreenTime();
		if (mixList.size() == 0)
			return;
		String lastKey = "";
		for (String key : mixList.keySet()) {
			lastKey = key;
		}
		UITasteOption option = getUItasteOptionById(lastKey, mixList.get(lastKey));
		option.setQuantity(option.getQuantity() + 1);
		tv_mix_counts.setText(option.getQuantity() + "");
		CommonRecycleViewAdapter<UITasteOption> adapter = getAdapterByCategoryId(option
				.getCategoryId());
		adapter.notifyDataSetChanged();
		refreshPrice();
		excuteAnimation(tv_mix_counts);
	}

	private CommonRecycleViewAdapter<UITasteOption> getAdapterByCategoryId(String categoryId) {
		return adapterMap.get(categoryId);
	}

	public void excuteAnimation(final TextView textView) {
		ValueAnimator animator = ValueAnimator.ofFloat(1, 1.3f, 1);
		animator.setDuration(200);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				textView.setScaleX((Float) animation.getAnimatedValue());
				textView.setScaleY((Float) animation.getAnimatedValue());
				textView.postInvalidate();
			}
		});
		animator.start();
	}


	@OnClick(R.id.iv_plus_counts)
	public void PlusCounts() {
		TimeConfigure.resetScreenTime();
		counts++;
		iv_minus_counts.setImageResource(R.mipmap.icon_minus_deep);
		tv_counts.setText(counts + "");
		refreshPrice();
		excuteAnimation(tv_counts);

	}

	@OnClick({R.id.iv_dismiss_dialog})
	public void dismissDialog() {
		TimeConfigure.resetScreenTime();
		dismiss();
	}

	@OnClick(R.id.tv_ok)
	public void onSelectedOk() {
		/**
		 *判断已经选择的是否应满足条件
		 */
		TimeConfigure.resetScreenTime();
		for (int i = 0; mOptionCategoryListist != null && i < mOptionCategoryListist.size(); i++) {
			UIOptionCategory category = mOptionCategoryListist.get(i);
			if (category != null && category
					.getHasSelect() < category.getMinimalOptions()) {
				tv_tips.setText("(" + category.getOptionCategoryName() + "至少选" + category
						.getMinimalOptions() + "项)");
				startTipsAnimatioin(tv_tips);
				return;
			}
		}
		dish.setQuantity(counts);//设置选择的份数
		if (wantChange) {
			//这个其实最好的方式还是直接改变它的对象
			cartDish.setDishID(dish.getDishID());
			cartDish.setSkuId(dish.getSkuId());
			cartDish.setPrice(dish.getPrice());
			cartDish.setQuantity(dish.getQuantity());
			cartDish.setOptionList(dish.getOptionList());
			cartDish.setCost(Price.getInstance().getDishCost(cartDish));
			mRxManager.post(AppConstant.ON_CART_ITEM_CHANGE_OPTIONDIALOG, "OPTIONITEMCHANGE");
			if (originalQuantity != cartDish.getQuantity()) {
				mRxManager
						.post(AppConstant.ON_CART_ITEM_CHANGE_OPTIONDIALOG2, new OnCartItemChangeOptionDialog(cartDish
								.getKindId(), cartDish.getQuantity() - originalQuantity));
			}
		} else if (fromPackage) {
			mRxManager.post(AppConstant.ON_PACKAGEITEM_OPTION_SELECT_OK, dish);
		} else
			Cart.getInstance().addDish(dish);

		dismiss();
	}


	private void startTipsAnimatioin(final TextView tv_tips) {
		ValueAnimator animator = ValueAnimator.ofFloat(0, 50f, 0, -50f, 0);
		animator.setDuration(300);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				tv_tips.setTranslationX((Float) animation.getAnimatedValue());
				tv_tips.postInvalidate();
			}
		});
		animator.start();
	}

	private UIOptionCategory getTempCountByChooseId(String categoryId) {
		for (UIOptionCategory category : mOptionCategoryListist) {

			if (category.getId().equals(categoryId))
				return category;

			//			for (UITasteOption option : category.getOptionList())
			//				if (integer.equals(option.getId()))
			//					return category;
		}
		return null;
	}

	//	private OptionAdapter2 optionAdapter;

	View view = null;

	private boolean isPrepare;

	@Override
	public View getView() {
		if (!isPrepare) {
			view = View.inflate(mcontext, R.layout.dialog_option, null);
			ButterKnife.bind(this, view);
			initData();
			isPrepare = true;
		}
		return view;
	}

	List<UIOptionCategory> mOptionCategoryListist;

	private UIDish dish;

	private void initData() {
		dish = getArguments().getParcelable("dish");
		originalQuantity = -1;
		cartDish = getArguments().getParcelable("cartDish");
		wantChange = getArguments().getBoolean("flag");
		if (wantChange && cartDish != null)
			originalQuantity = cartDish.getQuantity();
		fromPackage = getArguments().getBoolean("fromPackage");
		mOptionCategoryListist = dish.getOptionCategoryList();
		for (int i = 0; i < mOptionCategoryListist.size(); i++) {
			initView(mOptionCategoryListist.get(i));
		}
		initDialog(dish, cartDish);

		dish.setOptionList(tasteOptions);
		refreshPrice();
	}

	private List<String>             chooseId     = new ArrayList<>();
	private List<String>             chooseName   = new ArrayList<>();
	private ArrayList<UITasteOption> tasteOptions = new ArrayList<>();

	private void changeDish(UIDish dish) {
		this.dish = dish;
		chooseId.clear();
		chooseName.clear();
		tasteOptions.clear();
		dish.setOptionList(tasteOptions);
		//		resetUIoptionStatu();
		container.removeAllViews();
		mOptionCategoryListist = dish.getOptionCategoryList();
		for (int i = 0; mOptionCategoryListist != null && i < mOptionCategoryListist.size(); i++) {
			initView(mOptionCategoryListist.get(i));
		}
		initDialog(dish, cartDish);
	}

	private void initDialog(UIDish dish, CartDish cartDish) {
		Glide.with(mcontext).load(dish.getImageName())
				.placeholder(R.mipmap.default_img)
				.error(R.mipmap.default_img).into(dish_photo);
		//		dish_price.setText("￥" + dish.getPrice() + "/"+dish.getDishUnit());

		TextColorUtils.setLeftTextColorAndSize("￥" + (Order.getInstance().isMember() ? dish
				.getMemberPrice() : dish.getPrice()), "/" + dish
				.getDishUnit(), dish_price);
		if (SystemConfig.isSmarantSystem) {
			if (fromPackage) {
				dish_price.setVisibility(View.GONE);
				dish_count.setVisibility(View.GONE);
			} else {
				dish_count.setVisibility(View.VISIBLE);
				dish_count.setText("库存:" + dish.getDishCount());
			}
		} else {
			dish_count.setVisibility(View.GONE);
		}
		tv_option_choose.setText("请选择您的口味");
		if (cartDish != null) {
			counts = cartDish.getQuantity();
			tv_counts.setText(counts + "");
		}
		if (fromPackage) {
			counts_ly.setVisibility(View.GONE);
		}

		dish_name.setText(dish.getDishName());

		if (SystemConfig.isSmarantSystem) {
			mix_ly.setVisibility(View.GONE);
		} else if (SystemConfig.isSyncSystem) {

			boolean hasMixLayout = false;//判斷是否顯示加料的那個 加減控件
			for (UIOptionCategory category : dish.getOptionCategoryList()) {
				if (hasMixLayout)
					break;
				for (UITasteOption option : category.getOptionList()) {
					if (option.getSourceType().equals("M")) {
						hasMixLayout = true;
						break;
					}
				}
			}
			if (!hasMixLayout) {
				mix_ly.setVisibility(View.GONE);
			} else {
				mix_ly.setVisibility(View.VISIBLE);
			}
		} else if (SystemConfig.isCanXingJianSystem) {
			mix_ly.setVisibility(View.VISIBLE);
			//			mix_ly.setVisibility(View.GONE);//餐行健有bug，无法使用该功能
		}

	}

	private HashMap<String, CommonRecycleViewAdapter<UITasteOption>> adapterMap = new HashMap<>();

	private void initView(UIOptionCategory optionCategory) {

		View         chip_layout = View.inflate(mcontext, R.layout.item_option_layout, null);
		RecyclerView irc         = (RecyclerView) chip_layout.findViewById(R.id.irc);
		TextView tv_taste_category = (TextView) chip_layout
				.findViewById(R.id.tv_taste_category);
		TextView tv_taste_category_memo = (TextView) chip_layout
				.findViewById(R.id.tv_taste_category_memo);
		tv_taste_category.setText(optionCategory.getOptionCategoryName());
		if (!TextUtils.isEmpty(optionCategory.getOptionCategoryMemo()))
			tv_taste_category_memo.setText("(" + optionCategory.getOptionCategoryMemo() + ")");

		//		if (optionCategory.getMinimalOptions() == 0 && optionCategory.getMaximalOptions() == 1) {
		//			tv_taste_category
		//					.setText("单选");
		//		} else if (optionCategory.getMinimalOptions() == 0 && optionCategory
		//				.getMaximalOptions() == optionCategory.getOptionList().size()) {
		//			tv_taste_category
		//					.setText("任意组合");
		//		} else {
		//			tv_taste_category
		//					.setText(optionCategory.getOptionCategoryName() + "(最多选" + optionCategory
		//							.getMaximalOptions() + "项,最少选" + optionCategory
		//							.getMinimalOptions() + "项)");
		//		}
		ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(mcontext)
				//a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
				.setOrientation(ChipsLayoutManager.HORIZONTAL)
				.build();

		CommonRecycleViewAdapter<UITasteOption> adapter = new CommonRecycleViewAdapter<UITasteOption>(getContext(), R.layout.item_option_sublayout) {

			@Override
			public void convert(ViewHolderHelper helper, final UITasteOption tasteOption) {

				final TextView tv_taste = helper.getView(R.id.tv_taste);
				if (tasteOption.getPrice() == 0 && tasteOption.getOptionName().length() <= 3)
					tv_taste.setText("    " + tasteOption.getOptionName() + "    ");
				else if (tasteOption.getPrice() == 0 && tasteOption.getOptionName().length() < 8)
					tv_taste.setText("  " + tasteOption.getOptionName() + "  ");
				else if (tasteOption.getPrice() == 0)
					tv_taste.setText(tasteOption.getOptionName());
				else
					tv_taste.setText(tasteOption.getOptionName() + "(￥" + tasteOption
							.getPrice() + ")");
				if (tasteOption.isRequired() && !chooseId.contains(tasteOption.getId())) {
					setItemStatu(1, tv_taste);
					chooseId.add(tasteOption.getId());
					chooseName.add(tasteOption.getOptionName());
					UIOptionCategory category = getTempCountByChooseId(tasteOption.getCategoryId());
					int              select   = category.getHasSelect() + 1;
					tasteOption.setSelect(true);
					tasteOptions.add(tasteOption);
					category.setHasSelect(select);
				}
				if (tasteOption.isSelect()) {
					if ("M".equals(tasteOption.getSourceType())) {
						//						tv_taste.setText(tasteOption.getOptionName() + (tasteOption
						//								.getPrice() == 0 ? "" : "(￥" + tasteOption
						//								.getPrice() + ")") );
						if (tasteOption.getPrice() == 0 && tasteOption.getOptionName()
								.length() <= 3)
							tv_taste.setText("    " + tasteOption
									.getOptionName() + " x" + tasteOption
									.getQuantity() + "    ");
						else if (tasteOption.getPrice() == 0 && tasteOption.getOptionName()
								.length() < 8)
							tv_taste.setText("  " + tasteOption.getOptionName() + " x" + tasteOption
									.getQuantity() + "  ");
						else if (tasteOption.getPrice() == 0)
							tv_taste.setText(tasteOption.getOptionName() + " x" + tasteOption
									.getQuantity());
						else
							tv_taste.setText(tasteOption.getOptionName() + "(￥" + tasteOption
									.getPrice() + ")" + " x" + tasteOption
									.getQuantity());
					}
					setItemStatu(1, tv_taste);
				} else {
					setItemStatu(0, tv_taste);
				}
				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						TimeConfigure.resetScreenTime();
						/**
						 * 1、反选setItemStatu();
						 * 2、判断是否已经选够了，够了就不能选了
						 */
						if (tasteOption.isRequired()) {
							ToastUitl.showLong(mcontext, "此项是必选,不能取消!");
							return;
						}
						UIOptionCategory category = getTempCountByChooseId(tasteOption
								.getCategoryId());

						//1、未选，就判断是否可以选，可以选就选


						if (tasteOption.isSelect()) {
							//反选
							tasteOption.setSelect(false);
							removeItem(tasteOption.getId(), tasteOption.getOptionName());
							int i = category.getHasSelect() - 1;
							category.setHasSelect(i);
							tasteOptions.remove(tasteOption);
							if (mixList.containsKey(tasteOption.getId()) && mixList
									.get(tasteOption.getId()).equals(tasteOption.getCategoryId())) {
								tasteOption.setQuantity(0);
								mixList.remove(tasteOption.getId());
								if (mixList.size() >= 1) {
									String lastKey = "";
									for (String key : mixList.keySet()) {
										lastKey = key;
									}
									UITasteOption option = getUItasteOptionById(lastKey, mixList
											.get(lastKey));
									tv_mix_counts.setText(option.getQuantity() + "");
								} else {
									tv_mix_counts.setText("0");
								}
							}
						} else {
							//未选
							if (category.getMaximalOptions() > 1) {
								//可多选
								if (category.getHasSelect() == category.getMaximalOptions())
									return;
								tasteOption.setSelect(true);
								tasteOptions.add(tasteOption);
								int select = category.getHasSelect() + 1;
								category.setHasSelect(select);
								chooseId.add(tasteOption.getId());
								chooseName.add(tasteOption.getOptionName());
								if (tasteOption.getSourceType().equals("M")) {
									mixList.put(tasteOption.getId(), tasteOption.getCategoryId());
									tv_mix_counts.setText("1");
								}
							} else {
								//不可多选
								//列表里肯定不包含,先判断列表里是否有和它同个类型的，如果有，就去掉
								Iterator<UITasteOption> iterator = tasteOptions.iterator();
								while (iterator.hasNext()) {
									UITasteOption nextOption = iterator.next();
									if (nextOption.getCategoryId()
											.equals(tasteOption.getCategoryId())) {
										nextOption.setSelect(false);
										removeItem(nextOption.getId(), nextOption.getOptionName());
										category.setHasSelect(0);
										tasteOptions.remove(nextOption);
										break;
									}
								}

								//单选的情况，让它们互斥
								tasteOption.setSelect(true);
								tasteOptions.add(tasteOption);
								category.setHasSelect(1);
								chooseId.add(tasteOption.getId());
								chooseName.add(tasteOption.getOptionName());
							}
						}
						if (category.getId().equals("888888")) {
							//如果是规格，那么要将dish的属性全部改变
							changeDish(UIDataProvider.getDishByDishId(tasteOption.getId()));
						} else {
							notifyDataSetChanged();
						}
						refreshOutSideUI();
					}
				});
			}
		};
		adapterMap.put(optionCategory.getId(), adapter);
		irc.setAdapter(adapter);
		List<UITasteOption> tasteOptions = optionCategory.getOptionList();
		adapter.addAll(tasteOptions);
		irc.setLayoutManager(chipsLayoutManager);
		container.addView(chip_layout);
	}

	//	private List<String> mixList = new ArrayList<>();
	private Map<String, String> mixList = new LinkedHashMap<>();

	private void resetUIoptionStatu() {
		for (UIOptionCategory category : mOptionCategoryListist) {
			for (UITasteOption option : category.getOptionList()) {
				option.setSelect(false);
			}
		}
	}

	private void removeItem(String id, String optionName) {
		Iterator<String> iterator = chooseId.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(id))
				iterator.remove();
		}
		Iterator<String> iterator2 = chooseName.iterator();
		while (iterator2.hasNext()) {
			if (optionName.equals(iterator2.next()))
				iterator2.remove();
		}
	}


	private void refreshOutSideUI() {
		refreshPrice();
		if (chooseName.size() == 0) {
			tv_option_choose.setText("请选择您的口味");
			return;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < chooseName.size(); i++) {
			builder.append("\"" + chooseName.get(i) + "\" ");
		}
		tv_option_choose.setText("已选:" + builder.toString());
	}

	private void setItemStatu(int i, TextView tv_taste) {
		switch (i) {
			case 0://未选中
				tv_taste.setBackgroundDrawable(setShape("#fcf6e6", R.color.colorSecondary));
				tv_taste.setTextColor(getResources().getColor(R.color.main_goods_name));

				break;
			case 1:
				tv_taste.setBackgroundDrawable(setShape("#FF9100", R.color.colorPrimary));
				tv_taste.setTextColor(getResources().getColor(R.color.main_color_white));
				break;
		}
	}

	//设置
	private GradientDrawable setShape(String stroke, int fill) {
		//		int strokeWidth = 2; // 2 边框宽度

		int roundRadius = 5; // 5 圆角半径
		//		int strokeColor = Color.parseColor(stroke);//边框颜色
		//		int              fillColor = Color.parseColor(fill);//内部填充颜色
		GradientDrawable gd = new GradientDrawable();//创建drawable
		gd.setColor(getResources().getColor(fill));
		gd.setCornerRadius(roundRadius);
		//		gd.setStroke(strokeWidth, strokeColor);
		return gd;
	}

	@Override
	public float getSize() {
		return 1;
	}

}
