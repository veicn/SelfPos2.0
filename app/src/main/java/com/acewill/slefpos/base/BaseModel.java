package com.acewill.slefpos.base;

import android.os.Parcelable;

import com.jaydenxiao.common.basebean.BaseRespose;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.exception.ApiException;

import rx.Observable;
import rx.functions.Func1;

/**
 * des:baseModel
 * Created by xsf
 * on 2016.08.14:50
 */
public class BaseModel {
	/**
	 * 给返回结果去掉状态码等属性,
	 * 如果是查询出错,则返回状态码对应的描述给用户
	 *
	 * @param observable
	 * @return
	 */
	public Observable filterStatus(Observable observable) {
		return observable.map(new ResultFilter()).compose(RxSchedulers.io_main());
	}

	private class ResultFilter<T extends Parcelable> implements Func1<BaseRespose<T>, T> {

		@Override
		public T call(BaseRespose<T> respose) {
			if (Integer.parseInt(respose.getResult()) != 0) {
				//				throw new ApiException(Integer.parseInt(respose.getResult()));
				throw new ApiException(respose.getErrmsg());
			}
			return respose.getContent();
		}
	}
}
