package com.acewill.slefpos.bean.syncbean.syncinit;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/9/17 11:09
 * Desc：
 */
public class RegisterResponseData {

	/**
	 * code : 200
	 * data : {"ouid":"1ctAaaMITEmCxb-ADb1skw","companyId":"0605","name":"奥琦玮","locale":"zh_CN","storeshops":[{"ouid":"jMcFjdTVQFyOQQXNk8sR9w","name":"奈雪的茶","storeshopId":"1000","storeshopSn":"1000"},{"ouid":"vptKCaOtSn-ItRy4CVjt4w","name":"奥琦玮测试门店","storeshopId":"1001","storeshopSn":"1001"},{"ouid":"p0CzgBHfRJ605r3_2YDaiw","name":"晋心面馆","storeshopId":"1002","storeshopSn":"1002"}]}
	 */

	private int code;
	private DataBean data;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static class DataBean {
		/**
		 * ouid : 1ctAaaMITEmCxb-ADb1skw
		 * companyId : 0605
		 * name : 奥琦玮
		 * locale : zh_CN
		 * storeshops : [{"ouid":"jMcFjdTVQFyOQQXNk8sR9w","name":"奈雪的茶","storeshopId":"1000","storeshopSn":"1000"},{"ouid":"vptKCaOtSn-ItRy4CVjt4w","name":"奥琦玮测试门店","storeshopId":"1001","storeshopSn":"1001"},{"ouid":"p0CzgBHfRJ605r3_2YDaiw","name":"晋心面馆","storeshopId":"1002","storeshopSn":"1002"}]
		 */

		private String ouid;
		private String               companyId;
		private String               name;
		private String               locale;
		private List<StoreshopsBean> storeshops;

		public String getOuid() {
			return ouid;
		}

		public void setOuid(String ouid) {
			this.ouid = ouid;
		}

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocale() {
			return locale;
		}

		public void setLocale(String locale) {
			this.locale = locale;
		}

		public List<StoreshopsBean> getStoreshops() {
			return storeshops;
		}

		public void setStoreshops(List<StoreshopsBean> storeshops) {
			this.storeshops = storeshops;
		}

		public static class StoreshopsBean {
			/**
			 * ouid : jMcFjdTVQFyOQQXNk8sR9w
			 * name : 奈雪的茶
			 * storeshopId : 1000
			 * storeshopSn : 1000
			 */

			private String ouid;
			private String name;
			private String storeshopId;
			private String storeshopSn;

			public String getOuid() {
				return ouid;
			}

			public void setOuid(String ouid) {
				this.ouid = ouid;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getStoreshopId() {
				return storeshopId;
			}

			public void setStoreshopId(String storeshopId) {
				this.storeshopId = storeshopId;
			}

			public String getStoreshopSn() {
				return storeshopSn;
			}

			public void setStoreshopSn(String storeshopSn) {
				this.storeshopSn = storeshopSn;
			}
		}
	}
}
