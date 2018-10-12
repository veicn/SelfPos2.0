package com.jaydenxiao.common.exception;

import com.jaydenxiao.common.utils.StatusUtils;

/**
 * Created by mango on 16/8/18.
 */
public class ApiException extends RuntimeException {

    public ApiException(int status) {
        super(getErrorDesc(status));
    }
    public ApiException(String errmsg) {
        super(errmsg);
    }
    private static String getErrorDesc(int status){
        return StatusUtils.judgeStatus(status).desc;
    }
}
