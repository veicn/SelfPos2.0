package com.acewill.slefpos.bean.smarantstorebean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 17:17
 * Desc：
 */
public class ImagesData implements Parcelable {

	/**
	 * result : 0
	 * content : null
	 * errmsg : 0
	 * files : [{"fileid":2,"appid":"59841423","brandid":47,"storeid":1,"filename":"","filetype":6,"seq":2,"filetypeStr":"欢迎界面图片","filetypeKey":"WELCOME"},{"fileid":3,"appid":"59841423","brandid":47,"storeid":1,"filename":"http://szfileserver.419174855.mtmss.com/common/fileupload/20171123101842_6072.jpg","filetype":6,"seq":1,"filetypeStr":"欢迎界面图片","filetypeKey":"WELCOME"},{"fileid":4,"appid":"59841423","brandid":47,"storeid":1,"filename":"http://szfileserver.419174855.mtmss.com/common/fileupload/20171123101903_7723.jpg","filetype":6,"seq":2,"filetypeStr":"欢迎界面图片","filetypeKey":"WELCOME"},{"fileid":5,"appid":"59841423","brandid":47,"storeid":1,"filename":"http://szfileserver.419174855.mtmss.com/common/fileupload/20171123101919_5470.jpg","filetype":0,"seq":3,"filetypeStr":"广告图片","filetypeKey":"AD_IMAGE"},{"fileid":7,"appid":"59841423","brandid":47,"storeid":1,"filename":"http://szfileserver.419174855.mtmss.com/common/fileupload/20171123102132_4981.jpg","filetype":0,"seq":1,"filetypeStr":"广告图片","filetypeKey":"AD_IMAGE"},{"fileid":8,"appid":"59841423","brandid":47,"storeid":1,"filename":"http://szfileserver.419174855.mtmss.com/common/fileupload/20171123102147_8019.jpg","filetype":0,"seq":2,"filetypeStr":"广告图片","filetypeKey":"AD_IMAGE"},{"fileid":9,"appid":"59841423","brandid":47,"storeid":1,"filename":"http://szfileserver.419174855.mtmss.com/common/fileupload/20171123102200_847.jpg","filetype":0,"seq":3,"filetypeStr":"广告图片","filetypeKey":"AD_IMAGE"}]
	 */

	private int result;
	private String          errmsg;
	private List<ImageEntity> files;

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

	public List<ImageEntity> getFiles() {
		return files;
	}

	public void setFiles(List<ImageEntity> files) {
		this.files = files;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.result);
		dest.writeString(this.errmsg);
		dest.writeTypedList(this.files);
	}

	public ImagesData() {
	}

	protected ImagesData(Parcel in) {
		this.result = in.readInt();
		this.errmsg = in.readString();
		this.files = in.createTypedArrayList(ImageEntity.CREATOR);
	}

	public static final Parcelable.Creator<ImagesData> CREATOR = new Parcelable.Creator<ImagesData>() {
		@Override
		public ImagesData createFromParcel(Parcel source) {
			return new ImagesData(source);
		}

		@Override
		public ImagesData[] newArray(int size) {
			return new ImagesData[size];
		}
	};
}
