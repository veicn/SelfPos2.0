package com.acewill.slefpos.orderui.main.packageadapter;

import android.view.View;

import com.acewill.slefpos.bean.uibean.UIPackageItem;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface ItemClickListener {
    void itemClicked(View v, UIPackageItem section);
    void itemClicked(View v, UIPackageOptionItem packageOptionItem, UIPackageItem section);
}
