package com.acewill.slefpos.utils.permission;

import java.util.List;

/**
 * Created by Limuyang on 2016/12/29.
 */

public interface IPermission {

    void onGranted();

    void onDenied(List<String> deniedPermission);
}
