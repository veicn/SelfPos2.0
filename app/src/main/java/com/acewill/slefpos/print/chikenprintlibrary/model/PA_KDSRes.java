package com.acewill.slefpos.print.chikenprintlibrary.model;

import java.util.List;

/**
 * Author：Anch
 * Date：2017/5/4 20:14
 * Desc：
 */
public class PA_KDSRes {

	/**
	 * result : 0
	 * content : [{"id":1014,"appid":"42458671","brandid":40,"storeid":5,"ip":"192.168.1.104","kdsName":"192.168.1.104"}]
	 * errmsg : 0
	 */

	private int result;
	private String            errmsg;
	private List<ContentBean> content;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<ContentBean> getContent() {
		return content;
	}

	public void setContent(List<ContentBean> content) {
		this.content = content;
	}

	public static class ContentBean {
		/**
		 * id : 1014
		 * appid : 42458671
		 * brandid : 40
		 * storeid : 5
		 * ip : 192.168.1.104
		 * kdsName : 192.168.1.104
		 */

		private int id;
		private String ip;
		private String kdsName;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}


		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getKdsName() {
			return kdsName;
		}

		public void setKdsName(String kdsName) {
			this.kdsName = kdsName;
		}
	}
}
