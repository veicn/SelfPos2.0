package com.acewill.slefpos.print;

public class PrintModel {
	
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;
	
	public static final int TEXT = 0;
	public static final int QR_CODE = 1;
	public static final int BAR_CODE = 2;
	
	public int align;
	public int xSize;//1,2,3
	public int ySize;
	public String text;
	public int mode;//0 text,1 QR;2 bar code
	
	public void setSize(int size){
		xSize = size;
		ySize = size;
	}
	
}
