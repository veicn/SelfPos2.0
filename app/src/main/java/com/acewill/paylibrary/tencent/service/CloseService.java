package com.acewill.paylibrary.tencent.service;

import com.acewill.paylibrary.tencent.common.Configure;
import com.acewill.paylibrary.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;

/**
 * Author：Anch
 * Date：2018/1/24 15:26
 * Desc：
 */
public class CloseService extends BaseService{
	public CloseService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		super(Configure.CLOSE_API);
	}

	/**
	 * 请求支付查询服务
	 * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
	 * @return API返回的XML数据
	 * @throws Exception
	 */
	public String request(ScanPayQueryReqData scanPayQueryReqData) throws Exception {

		//--------------------------------------------------------------------
		//发送HTTPS的Post请求到API地址
		//--------------------------------------------------------------------
		String responseString = sendPost(scanPayQueryReqData);

		return responseString;
	}
}
