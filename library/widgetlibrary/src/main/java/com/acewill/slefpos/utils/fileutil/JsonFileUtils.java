package com.acewill.slefpos.utils.fileutil;

import com.jaydenxiao.common.compressorutils.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Author：Anch
 * Date：2018/6/27 10:14
 * Desc：
 */
public class JsonFileUtils {
	/**
	 * 保存json数据到文件
	 *
	 * @param fileName
	 * @param data
	 */
	public static void saveDataToFile(String fileName, String data) {
		BufferedWriter writer = null;
		File file2 = new File(FileUtil
				.getSyncFoldPath() + "/orderdata");
		//如果文件不存在，则新建一个
		if (!file2.exists()) {
			try {
				file2.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		File file = new File(FileUtil
				.getSyncFoldPath() + "/orderdata/"+ fileName + ".json");

		//写入
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("文件写入成功！");
	}

	/**
	 * 从文件中获取json数据
	 *
	 * @param fileName
	 */
	public static String getDatafromFile(String fileName) {
		String Path = FileUtil
				.getSyncFoldPath() + "/orderdata/" + fileName + ".json";
		BufferedReader reader  = null;
		String         laststr = "";
		try {
			FileInputStream   fileInputStream   = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

}
