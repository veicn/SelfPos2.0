package com.acewill.slefpos.orderui.main.packageadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jaydenxiao.common.utils.springylib.springyRecyclerView.SpringyAdapterAnimationType;
import com.jaydenxiao.common.utils.springylib.springyRecyclerView.SpringyAdapterAnimator;

import java.util.ArrayList;

/**
 * Created by lenovo on 2/23/2016.
 */
public class SectionedExpandableGridAdapter extends
		RecyclerView.Adapter<SectionedExpandableGridAdapter.ViewHolder> {


	private static final String TAG = "SectionedExpandableGridAdapter";
	//data array
	private ArrayList<Object> mDataArrayList;

	//context
	private final Context mContext;

	//listeners
	private final ItemClickListener          mItemClickListener;
	private final SectionStateChangeListener mSectionStateChangeListener;

	//view type
	private static final int VIEW_TYPE_SECTION = R.layout.layout_section;
	private static final int VIEW_TYPE_ITEM    = R.layout.layout_item; //TODO : change this
	private static final int VIEW_TYPE_HEADER  = R.layout.header_item;

	public SectionedExpandableGridAdapter(Context context, ArrayList<Object> dataArrayList,
	                                      final GridLayoutManager gridLayoutManager, ItemClickListener itemClickListener,
	                                      SectionStateChangeListener sectionStateChangeListener) {
		mContext = context;
		mItemClickListener = itemClickListener;
		mSectionStateChangeListener = sectionStateChangeListener;
		mDataArrayList = dataArrayList;

		gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {//这个方法是指你这item占的位置
				if (position == 0)
					return gridLayoutManager.getSpanCount();
				return isSection(position) ? gridLayoutManager.getSpanCount() : 1;
			}
		});
	}

	private SpringyAdapterAnimator mAnimator;

	public void initAnimation(RecyclerView recyclerView) {
		mAnimator = new SpringyAdapterAnimator(recyclerView);
		mAnimator.setSpringAnimationType(SpringyAdapterAnimationType.SCALE);
		mAnimator.addConfig(85, 15);
	}

	private boolean isSection(int position) {
		return mDataArrayList.get(position) instanceof UIPackageItem;
	}

	private boolean isHeader(int position) {
		return mDataArrayList.get(position) instanceof UIDish;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext)
				.inflate(viewType, parent, false), viewType);
		if (mAnimator != null)
			mAnimator.onSpringItemCreate(holder.itemView);
		return holder;
	}


	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (mAnimator != null)
			mAnimator.onSpringItemBind(holder.itemView, position);
		switch (holder.viewType) {
			case VIEW_TYPE_HEADER:
				final UIDish dish = (UIDish) mDataArrayList
						.get(position);
				Glide.with(mContext).load(dish.getImageName())
						.diskCacheStrategy(DiskCacheStrategy.SOURCE)
						.placeholder(R.mipmap.default_img)
						.error(R.mipmap.default_img)
						//						.centerCrop().override(1090, 1090 * 3 / 4)
						.crossFade().into(holder.dishImageView);
				break;
			case VIEW_TYPE_ITEM:
				final UIPackageOptionItem packageOptionItem = (UIPackageOptionItem) mDataArrayList
						.get(position);
				holder.itemTextView.setText(packageOptionItem.getDishName());
				Glide.with(mContext).load(packageOptionItem.getImageName())
						.diskCacheStrategy(DiskCacheStrategy.SOURCE)
						.placeholder(R.mipmap.default_img)
						.error(R.mipmap.default_img)
						.centerCrop().override(1090, 1090 * 3 / 4)
						.crossFade().into(holder.mRoundCornerImageView);
				holder.view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						UIPackageItem packageItem = findPackageItemByItemId(packageOptionItem
								.getItemID());
						mItemClickListener.itemClicked(v, packageOptionItem, packageItem);
					}
				});
				break;
			case VIEW_TYPE_SECTION:
				final UIPackageItem section = (UIPackageItem) mDataArrayList.get(position);
				holder.sectionTextView.setText(section.getItemName());
				holder.view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mItemClickListener.itemClicked(v, section);
					}
				});
				holder.sectionToggleButton.setChecked(section.isExpanded());
				holder.sectionToggleButton
						.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								mSectionStateChangeListener
										.onSectionStateChanged(section, isChecked);
							}
						});
				break;
		}
	}

	private UIPackageItem findPackageItemByItemId(String id) {

		for (Object object : mDataArrayList) {
			if (object instanceof UIPackageItem) {
				UIPackageItem item = (UIPackageItem) object;
				if (item.getItemID().equals(id) )
					return item;
			}
		}
		return null;
	}

	@Override
	public int getItemCount() {
		return mDataArrayList.size();
	}

	@Override
	public int getItemViewType(int position) {
		//		if (position==0)
		//			return VIEW_TYPE_HEADER;
		if (isHeader(position))
			return VIEW_TYPE_HEADER;
		else if (isSection(position))
			return VIEW_TYPE_SECTION;
		else
			return VIEW_TYPE_ITEM;
	}


	protected static class ViewHolder extends RecyclerView.ViewHolder {
		//common
		View view;
		int  viewType;

		//for section
		TextView     sectionTextView;
		ToggleButton sectionToggleButton;

		//for item
		TextView  itemTextView;
		ImageView mRoundCornerImageView;

		//for Header
		ImageView dishImageView;

		public ViewHolder(View view, int viewType) {
			super(view);
			this.viewType = viewType;
			this.view = view;
			if (viewType == VIEW_TYPE_ITEM) {
				itemTextView = (TextView) view.findViewById(R.id.dish_name);
				mRoundCornerImageView = (ImageView) view.findViewById(R.id.dish_photo);
			} else if (viewType == VIEW_TYPE_SECTION) {
				sectionTextView = (TextView) view.findViewById(R.id.text_section);
				sectionToggleButton = (ToggleButton) view.findViewById(R.id.toggle_button_section);
			} else {
				dishImageView = (ImageView) view.findViewById(R.id.dish_photo);
			}
		}
	}
}
