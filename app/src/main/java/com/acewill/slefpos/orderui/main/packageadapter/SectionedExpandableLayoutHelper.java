package com.acewill.slefpos.orderui.main.packageadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bpncool on 2/23/2016.
 */
public class SectionedExpandableLayoutHelper implements SectionStateChangeListener {

	//data list
	private LinkedHashMap<UIPackageItem, List<UIPackageOptionItem>> mSectionDataMap = new LinkedHashMap<UIPackageItem, List<UIPackageOptionItem>>();
	private ArrayList<Object>                                       mDataArrayList  = new ArrayList<Object>();

	//section map
	//TODO : look for a way to avoid this
	private HashMap<String, UIPackageItem> mSectionMap = new HashMap<String, UIPackageItem>();

	//adapter
	private SectionedExpandableGridAdapter mSectionedExpandableGridAdapter;

	//recycler view
	RecyclerView mRecyclerView;
	private UIDish dish;//为了添加头加进来的

	public SectionedExpandableLayoutHelper(Context context, RecyclerView recyclerView, ItemClickListener itemClickListener,
	                                       int gridSpanCount, UIDish dish) {

		//setting the recycler view
		GridLayoutManager gridLayoutManager = new GridLayoutManager(context, gridSpanCount);
		recyclerView.setLayoutManager(gridLayoutManager);

		mSectionedExpandableGridAdapter = new SectionedExpandableGridAdapter(context, mDataArrayList,
				gridLayoutManager, itemClickListener, this);
		//		mSectionedExpandableGridAdapter.initAnimation(recyclerView);//初始化动画
		recyclerView.setAdapter(mSectionedExpandableGridAdapter);
		this.dish = dish;
		mRecyclerView = recyclerView;
	}

	public void notifyDataSetChanged() {
		//TODO : handle this condition such that these functions won't be called if the recycler view is on scroll
		generateDataList();
		mSectionedExpandableGridAdapter.notifyDataSetChanged();
	}

	public void addSection(UIPackageItem section, List<UIPackageOptionItem> packageOptionItems) {
		//        PackageItem newSection;
		mSectionMap.put(section.getItemID() + "", section);
		mSectionDataMap.put(section, packageOptionItems);
	}

	public void addHeader(UIDish dish) {
		mDataArrayList.add(dish);
	}

	public void addItem(UIPackageItem section, UIPackageOptionItem packageOptionItem) {
		mSectionDataMap.get(mSectionMap.get(section.getItemID() + "")).add(packageOptionItem);
	}

	public void removeItem(UIPackageItem section, UIPackageOptionItem packageOptionItem) {
		mSectionDataMap.get(mSectionMap.get(section.getItemID() + "")).remove(packageOptionItem);
	}

	public void removeSection(UIPackageItem section) {
		mSectionDataMap.remove(mSectionMap.get(section.getItemID() + ""));
		mSectionMap.remove(section);
	}

	private void generateDataList() {
		mDataArrayList.clear();
		mDataArrayList.add(0, dish);//添加头
		for (Map.Entry<UIPackageItem, List<UIPackageOptionItem>> entry : mSectionDataMap
				.entrySet()) {
			UIPackageItem key;
			mDataArrayList.add((key = entry.getKey()));
			if (key.isExpanded())
				mDataArrayList.addAll(entry.getValue());
		}
	}

	@Override
	public void onSectionStateChanged(UIPackageItem section, boolean isOpen) {
		if (!mRecyclerView.isComputingLayout()) {
			section.setExpanded(isOpen);
			notifyDataSetChanged();
		}
	}


}
