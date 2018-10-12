package com.acewill.slefpos.bean.smarantstorebean;


import com.acewill.slefpos.print.chikenprintlibrary.printer.PrintModelInfo;

import java.util.HashMap;

/**
 * Author：Anch
 * Date：2017/5/4 20:14
 * Desc：
 */
public class PrinterTemplatesData {



		private String                          templateType;
		private HashMap<String, PrintModelInfo> models;

		public String getTemplateType() {
			return templateType;
		}

		public void setTemplateType(String templateType) {
			this.templateType = templateType;
		}

		public HashMap<String, PrintModelInfo> getModels() {
			return models;
		}

		public void setModels(HashMap<String, PrintModelInfo> models) {
			this.models = models;
		}
}
