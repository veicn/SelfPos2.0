package com.acewill.slefpos.print.chikenprintlibrary.printer;

import java.util.HashMap;

/**
 * Created by DHH on 2017/3/23.
 */

public class PrinterTemplates {
    // 0：客用小票；1：加菜单；2：退菜单；3：预结单；4：结账单；5：标签；6：厨房单
    private PrintTemplateType templateType;
//    private List<PrintModelInfo> models;
    private HashMap<String,PrintModelInfo> models;

    public PrintTemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(PrintTemplateType templateType) {
        this.templateType = templateType;
    }

    public HashMap<String, PrintModelInfo> getModels() {
        return models;
    }

    public void setModels(HashMap<String, PrintModelInfo> models) {
        this.models = models;
    }

    //    public List<PrintModelInfo> getModels() {
//        return models;
//    }
//
//    public void setModels(List<PrintModelInfo> models) {
//        this.models = models;
//    }
}
