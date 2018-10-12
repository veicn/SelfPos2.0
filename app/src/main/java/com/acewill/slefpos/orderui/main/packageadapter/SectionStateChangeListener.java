package com.acewill.slefpos.orderui.main.packageadapter;

/**
 * Created by bpncool on 2/24/2016.
 */

import com.acewill.slefpos.bean.uibean.UIPackageItem;

/**
 * interface to listen changes in state of sections
 */
public interface SectionStateChangeListener {
    void onSectionStateChanged(UIPackageItem section, boolean isOpen);
}