package com.acewill.slefpos.orderui.main.ui.dialog;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIOptionCategory;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.dialog.BaseDialog;
import com.acewill.slefpos.widgetlibrary.RoundCornerImageView;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Anch
 * Date：2018/2/5 11:10
 * Desc：
 */
public class SyncOptionDialog extends BaseDialog {
	private CommonRecycleViewAdapter<UITasteOption> adapter;
	private boolean                                 wantChange;
	private CartDish                                cartDish;
	private boolean                                 fromPackage;

	/**
	 * @param dish
	 * @param wantChange
	 * @return
	 */
	public static SyncOptionDialog newInstance(UIDish dish, CartDish cartDish, boolean wantChange, boolean fromPackage) {
		SyncOptionDialog fragment = new SyncOptionDialog();
		Bundle           bundle   = new Bundle();
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
	LinearLayout         container;
	@Bind(R.id.tv_option_choose)
	TextView             tv_option_choose;
	@Bind(R.id.tv_tips)
	TextView             tv_tips;
	@Bind(R.id.dish_photo)
	RoundCornerImageView dish_photo;
	@Bind(R.id.dish_price)
	TextView             dish_price;
	@Bind(R.id.dish_count)
	TextView             dish_count;
	@Bind(R.id.tv_counts)
	TextView             tv_counts;
	@Bind(R.id.iv_plus_counts)
	ImageView            iv_plus_counts;
	@Bind(R.id.iv_minus_counts)
	ImageView            iv_minus_counts;
	@Bind(R.id.dish_name)
	TextView             dish_name;

	@Bind(R.id.counts_ly)
	RelativeLayout counts_ly;
	private int counts           = 1;
	private int repertory_counts = 1;

	@OnClick(R.id.iv_minus_counts)
	public void MinusCounts() {
		if (counts == 1) {
			return;
		} else {
			counts--;
			if (counts == 1)
				iv_minus_counts.setImageResource(R.mipmap.icon_minus_light);
		}
		tv_counts.setText(counts + "");
		excuteAnimation();
	}

	public void excuteAnimation() {
		ValueAnimator animator = ValueAnimator.ofFloat(1, 1.3f, 1);
		animator.setDuration(200);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				tv_counts.setScaleX((Float) animation.getAnimatedValue());
				tv_counts.setScaleY((Float) animation.getAnimatedValue());
				tv_counts.postInvalidate();
			}
		});
		animator.start();
	}


	@OnClick(R.id.iv_plus_counts)
	public void PlusCounts() {
		counts++;
		iv_minus_counts.setImageResource(R.mipmap.icon_minus_deep);
		tv_counts.setText(counts + "");
		excuteAnimation();
	}

	@OnClick({ R.id.iv_dismiss_dialog})
	public void dismissDialog() {
		dismiss();
	}

	@OnClick(R.id.tv_ok)
	public void onSelectedOk() {
		/**
		 *判断已经选择的是否应满足条件
		 */

		/*
		for (int i = 0; mOptionCategoryListist != null && i < mOptionCategoryListist.size(); i++) {
			UIOptionCategory category = mOptionCategoryListist.get(i);
			if (category != null&&  category.getHasSelect() < category.getMinimalOptions()) {
				tv_tips.setText(category.getOptionCategoryName() + "至少选" + category
						.getMinimalOptions() + "项");
				startTipsAnimatioin(tv_tips);
				return;
			}
		}*/
		dish.setQuantity(counts);//设置选择的份数
		dish.setOptionList(tasteOptions);
		if (wantChange) {
			//这个其实最好的方式还是直接改变它的对象
			cartDish.setQuantity(dish.getQuantity());
			cartDish.setOptionList(dish.getOptionList());
			mRxManager.post(AppConstant.ON_PACKAGEITEM_OPTION_SELECT_OK, 1);
		} else if (fromPackage) {
			mRxManager.post(AppConstant.ON_PACKAGEITEM_OPTION_SELECT_OK, dish);
		} else
			Cart.getInstance().addDish(dish);

		dismiss();
	}

	private ArrayList<UITasteOption> tasteOptions = new ArrayList<>();

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

	private UIOptionCategory getTempCountByChooseId(String integer) {
		for (UIOptionCategory category : mOptionCategoryListist) {
			for (UITasteOption option : category.getOptionList())
				if (integer.equals(option.getId()))
					return category;
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
		cartDish = getArguments().getParcelable("cartDish");
		wantChange = getArguments().getBoolean("flag");
		fromPackage = getArguments().getBoolean("fromPackage");
		mOptionCategoryListist = dish.getOptionCategoryList();
		for (int i = 0; i < mOptionCategoryListist.size(); i++) {
			initView(mOptionCategoryListist.get(i));
		}
		initDialog(dish, cartDish);
	}


	private void initDialog(UIDish dish, CartDish cartDish) {
		Glide.with(mcontext).load(dish.getImageName())
				.placeholder(R.mipmap.default_img)
				.error(R.mipmap.default_img).into(dish_photo);
		dish_price.setText("￥" + dish.getPrice() + "");
		dish_count.setText("库存" + dish.getDishCount() + dish.getDishUnit());
		tv_option_choose.setText("请选择您的口味");
		if (cartDish != null) {
			counts = cartDish.getQuantity();
			tv_counts.setText(counts + "");
		}
		if (fromPackage) {
			counts_ly.setVisibility(View.GONE);
		}

		dish_name.setText(dish.getDishName());
	}

	private List<String> chooseId   = new ArrayList<>();
	private List<String> chooseName = new ArrayList<>();

	private void initView(UIOptionCategory optionCategory) {

		View         chip_layout = View.inflate(mcontext, R.layout.item_option_layout, null);
		RecyclerView irc         = (RecyclerView) chip_layout.findViewById(R.id.irc);
		TextView tv_taste_category = (TextView) chip_layout
				.findViewById(R.id.tv_taste_category);
		/*
		同步时没有最多选和最少选的说法
		tv_taste_category.setText(optionCategory.getOptionCategoryName() + "(最多选" + optionCategory
				.getMaximalOptions() + "项,最少选" + optionCategory.getMinimalOptions() + "项)");*/
		ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(mcontext)
				//a layoutOrientation of layout manager, could be VERTICAL OR HORIZONTAL. HORIZONTAL by default
				.setOrientation(ChipsLayoutManager.HORIZONTAL)
				.build();

		adapter = new CommonRecycleViewAdapter<UITasteOption>(getContext(), R.layout.item_option_sublayout) {

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
					tv_taste.setText(tasteOption.getOptionName() + "(+￥" + tasteOption
							.getPrice() + ")");
				if (tasteOption.isRequired()) {
					setItemStatu(1, tv_taste);
					chooseId.add(tasteOption.getId());
					chooseName.add(tasteOption.getOptionName());
					UIOptionCategory category = getTempCountByChooseId(tasteOption.getId());
					int              select   = category.getHasSelect() + 1;
					tasteOption.setSelect(true);
					tasteOptions.add(tasteOption);
					category.setHasSelect(select);
				}

				helper.getConvertView().setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						/**
						 * 1、反选setItemStatu();
						 * 2、判断是否已经选够了，够了就不能选了
						 */
						if (tasteOption.isRequired()) {
							ToastUitl.showLong(mcontext, "此项是必选,不能取消!");
							return;
						}
						UIOptionCategory category = getTempCountByChooseId(tasteOption.getId());
						if (tasteOption.isSelect()) {
							//反选
							tasteOption.setSelect(false);
							removeItem(tasteOption.getId(), tasteOption.getOptionName());
							int i = category.getHasSelect() - 1;
							category.setHasSelect(i);
							tasteOptions.remove(tasteOption);
						} else {
							//未选
							if (category.getHasSelect() == category.getMaximalOptions())
								return;
							tasteOption.setSelect(true);
							tasteOptions.add(tasteOption);
							int select = category.getHasSelect() + 1;
							category.setHasSelect(select);
							chooseId.add(tasteOption.getId());
							chooseName.add(tasteOption.getOptionName());
							//							PriceUtil.add()
						}
						setItemStatu(tasteOption.isSelect() ? 1 : 0, tv_taste);
						refreshOutSideUI();
					}
				});
			}
		};
		irc.setAdapter(adapter);
		List<UITasteOption> tasteOptions = optionCategory.getOptionList();
		adapter.addAll(tasteOptions);
		irc.setLayoutManager(chipsLayoutManager);
		container.addView(chip_layout);
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
				tv_taste.setBackgroundDrawable(setShape("#f5f5f5", "#f5f5f5"));
				tv_taste.setTextColor(Color.parseColor("#666666"));
				break;
			case 1:
				tv_taste.setBackgroundDrawable(setShape("#FF9100", "#FF9100"));
				tv_taste.setTextColor(Color.parseColor("#ffffff"));
				break;
		}
	}

	//设置
	private GradientDrawable setShape(String stroke, String fill) {
		//		int strokeWidth = 2; // 2 边框宽度

		int roundRadius = 5; // 5 圆角半径
		//		int strokeColor = Color.parseColor(stroke);//边框颜色
		int              fillColor = Color.parseColor(fill);//内部填充颜色
		GradientDrawable gd        = new GradientDrawable();//创建drawable
		gd.setColor(fillColor);
		gd.setCornerRadius(roundRadius);
		//		gd.setStroke(strokeWidth, strokeColor);
		return gd;
	}

	@Override
	public float getSize() {
		return 1;
	}

}
