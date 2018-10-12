package com.acewill.slefpos.okhttp.builder;


import com.acewill.slefpos.okhttp.OkHttpUtils;
import com.acewill.slefpos.okhttp.request.OtherRequest;
import com.acewill.slefpos.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
