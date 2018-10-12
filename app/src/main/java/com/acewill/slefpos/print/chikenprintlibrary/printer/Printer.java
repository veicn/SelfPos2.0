package com.acewill.slefpos.print.chikenprintlibrary.printer;


import java.io.Serializable;

/**
 * Created by Acewill on 2016/8/5.
 */
public class Printer implements Serializable {


	//    private Integer id;
	//    private String vendor; //品牌sprt
	//    private String ip;
	//    private String deviceName; //蓝牙打印机的名字
	//    private PrinterWidth width = PrinterWidth.WIDTH_80MM;
	//    private String description;
	////    private List<ReceiptType> receiptTypeList;
	//    private Integer labelHeight; //标签打印纸的高度
	//    private PrinterLinkType linkType = PrinterLinkType.NETWORK; //连接类型， 网络，蓝牙，或者USB
	//    private PrinterOutputType outputType = PrinterOutputType.REGULAR; //打印的是标签， 还是普通的纸， 标签和普通打印的指令完全不一样
	//    private Integer summaryReceiptCounts = 0; //该打印机用来打印总单的份数， 0表示不打印总单
	//    private Integer dishReceiptCounts = 1; //该打印机用来打印分单的份数, 0表示不打印分单
	//
	//    private PrinterState printerState = PrinterState.WATING;//先设置成等待检测
	//    private String errMessage = "";//如果不能使用,记录不能使用的原因
	//    private use


	/**
	 * id : 1103
	 * appId : 59841423
	 * brandId : 47
	 * storeId : 1
	 * vendor : epson
	 * ip : 192.168.1.188
	 * deviceName : null
	 * description : 188打印机
	 * width : WIDTH_80MM
	 * linkType : NETWORK
	 * outputType : REGULAR
	 * labelHeight : null
	 * standbyPrinterIdList :
	 * receiptIdList : null
	 * summaryReceiptCounts : null
	 * dishReceiptCounts : null
	 * vendorStr : 爱普生
	 * widthStr : 80mm
	 * linkTypeStr : 网络打印机
	 * outputTypeStr : 热敏/针式打印机
	 * standbyPrinterName : null
	 */
	private PrinterState printerState = PrinterState.WATING;//先设置成等待检测
	private String       errMessage   = "";//如果不能使用,记录不能使用的原因
	private int     id;
	private String  vendor;
	private String  ip;
	private String  deviceName;
	private String  description;
	//	private String  width;
	//	private String  linkType;
	//	private String  outputType;
	private Integer labelHeight;
	private String  standbyPrinterIdList;
	private Integer receiptIdList;
	private Integer summaryReceiptCounts;
	private Integer dishReceiptCounts;
	private String  vendorStr;
	private String  widthStr;
	private String  linkTypeStr;
	private String  outputTypeStr;
	private String  standbyPrinterName;

	public boolean isUseStandBy() {
		return useStandBy;
	}

	public void setUseStandBy(boolean useStandBy) {
		this.useStandBy = useStandBy;
	}

	private boolean useStandBy;
	private PrinterWidth      width      = PrinterWidth.WIDTH_80MM;
	private PrinterLinkType   linkType   = PrinterLinkType.NETWORK; //连接类型， 网络，蓝牙，或者USB
	private PrinterOutputType outputType = PrinterOutputType.REGULAR; //打印的是标签， 还是普通的纸， 标签和普通打印的指令完全不一样

	//	public int summaryReceiptCounts ; //该打印机用来打印总单的份数， 0表示不打印总单
	//	public int dishReceiptCounts ; //该打印机用来打印分单的份数, 0表示不打印分单
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public PrinterState getPrinterState() {
		return printerState;
	}

	public void setPrinterState(PrinterState printerState) {
		this.printerState = printerState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PrinterWidth getWidth() {
		return width;
	}

	//
	public void setWidth(PrinterWidth width) {
		this.width = width;
	}

	public PrinterLinkType getLinkType() {
		return linkType;
	}

	public void setLinkType(PrinterLinkType linkType) {
		this.linkType = linkType;
	}

	public PrinterOutputType getOutputType() {
		return outputType;
	}

	public void setOutputType(PrinterOutputType outputType) {
		this.outputType = outputType;
	}

	public Integer getLabelHeight() {
		return labelHeight;
	}

	public void setLabelHeight(Integer labelHeight) {
		this.labelHeight = labelHeight;
	}

	public String getStandbyPrinterIdList() {
		return standbyPrinterIdList;
	}

	public void setStandbyPrinterIdList(String standbyPrinterIdList) {
		this.standbyPrinterIdList = standbyPrinterIdList;
	}

	public Integer getReceiptIdList() {
		return receiptIdList;
	}

	public void setReceiptIdList(Integer receiptIdList) {
		this.receiptIdList = receiptIdList;
	}

	public Integer getSummaryReceiptCounts() {
		return summaryReceiptCounts;
	}

	public void setSummaryReceiptCounts(Integer summaryReceiptCounts) {
		this.summaryReceiptCounts = summaryReceiptCounts;
	}

	public Integer getDishReceiptCounts() {
		return dishReceiptCounts;
	}

	public void setDishReceiptCounts(Integer dishReceiptCounts) {
		this.dishReceiptCounts = dishReceiptCounts;
	}

	public String getVendorStr() {
		return vendorStr;
	}

	public void setVendorStr(String vendorStr) {
		this.vendorStr = vendorStr;
	}

	public String getWidthStr() {
		return widthStr;
	}

	public void setWidthStr(String widthStr) {
		this.widthStr = widthStr;
	}

	public String getLinkTypeStr() {
		return linkTypeStr;
	}

	public void setLinkTypeStr(String linkTypeStr) {
		this.linkTypeStr = linkTypeStr;
	}

	public String getOutputTypeStr() {
		return outputTypeStr;
	}

	public void setOutputTypeStr(String outputTypeStr) {
		this.outputTypeStr = outputTypeStr;
	}

	public String getStandbyPrinterName() {
		return standbyPrinterName;
	}

	public void setStandbyPrinterName(String standbyPrinterName) {
		this.standbyPrinterName = standbyPrinterName;
	}

	public boolean isStandBy() {
		return isStandBy;
	}

	public void setStandBy(boolean standBy) {
		this.isStandBy = standBy;
	}

	private boolean isStandBy;


	public String getStandByErrmsg() {
		return standByErrmsg;
	}

	public void setStandByErrmsg(String standByErrmsg) {
		this.standByErrmsg = standByErrmsg;
	}

	private String standByErrmsg;
	//
	//
	//    public boolean isSelect = false;
	//
	//    public Printer(String deviceName)
	//    {
	//        this.deviceName = deviceName;
	//    }
	//    public Printer()
	//    {
	//
	//    }
	//
	////    public List<ReceiptType> getReceiptTypeList() {
	////        return receiptTypeList;
	////    }
	////
	////    public void setReceiptTypeList(List<ReceiptType> receiptTypeList) {
	////        this.receiptTypeList = receiptTypeList;
	////    }
	//
	//    public Integer getId() {
	//        return id;
	//    }
	//
	//    public void setId(Integer id) {
	//        this.id = id;
	//    }
	//
	//    public String getVendor() {
	//        return vendor;
	//    }
	//
	//
	//    public void setVendor(String vendor) {
	//        this.vendor = vendor;
	//    }
	//
	//    public String getIp() {
	//        return ip;
	//    }
	//
	//    public void setIp(String ip) {
	//        this.ip = ip;
	//    }
	//
	//    public String getDescription() {
	//        return description;
	//    }
	//
	//    public void setDescription(String description) {
	//        this.description = description;
	//    }
	//
	//    public String getDeviceName() {
	//        return deviceName;
	//    }
	//
	//    public void setDeviceName(String deviceName) {
	//        this.deviceName = deviceName;
	//    }
	//
	//    public PrinterWidth getWidth() {
	//        return width;
	//    }
	//
	//    public void setWidth(PrinterWidth width) {
	//        this.width = width;
	//    }
	//
	//    public Integer getLabelHeight() {
	//        return labelHeight;
	//    }
	//
	//    public void setLabelHeight(Integer labelHeight) {
	//        this.labelHeight = labelHeight;
	//    }
	//
	//    public PrinterLinkType getLinkType() {
	//        return linkType;
	//    }
	//
	//    public void setLinkType(PrinterLinkType linkType) {
	//        this.linkType = linkType;
	//    }
	//
	//    public PrinterOutputType getOutputType() {
	//        return outputType;
	//    }
	//
	//    public void setOutputType(PrinterOutputType outputType) {
	//        this.outputType = outputType;
	//    }
	//
	//    public Integer getSummaryReceiptCounts() {
	//        return summaryReceiptCounts;
	//    }
	//
	//    public void setSummaryReceiptCounts(Integer summaryReceiptCounts) {
	//        this.summaryReceiptCounts = summaryReceiptCounts;
	//    }
	//
	//    public Integer getDishReceiptCounts() {
	//        return dishReceiptCounts;
	//    }
	//
	//    public void setDishReceiptCounts(Integer dishReceiptCounts) {
	//        this.dishReceiptCounts = dishReceiptCounts;
	//    }
	//
	//    public String getErrMessage() {
	//        return errMessage;
	//    }
	//
	//    public void setErrMessage(String errMessage) {
	//        this.errMessage = errMessage;
	//    }
	//
	//    public PrinterState getPrinterState() {
	//        return printerState;
	//    }
	//
	//    public void setPrinterState(PrinterState printerState) {
	//        this.printerState = printerState;
	//    }


}
