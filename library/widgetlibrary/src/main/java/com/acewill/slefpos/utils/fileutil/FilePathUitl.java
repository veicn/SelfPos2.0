//package com.acewill.slefpos.utils.fileutil;
//
//import android.os.Environment;
//
//import java.io.File;
//
///**
// * Author：Anch
// * Date：2018/2/11 10:54
// * Desc：
// */
//public class FilePathUitl {
//	// 获得缓存文件路径，磁盘空间不足或清除缓存时数据会被删掉，一般存放一些临时文件
//	// /data/data/<application package>/cache目录
//	File cacheDir = getCacheDir();
//Log.d("TAG", "getCacheDir() : " + cacheDir.getAbsolutePath());
//
//	// 获得文件存放路径，一般存放一些需要长期保留的文件
//	// /data/data/<application package>/files目录
//	File fileDir = getFilesDir();  
//Log.d("TAG", "getFilesDir() : " + fileDir.getAbsolutePath());
//
//	// 这是一个可以存放你自己应用程序自定义的文件，你可以通过该方法返回的File实例来创建或者访问这个目录
//	// /data/data/<application package>/
//	File dir = getDir("fileName", MODE_PRIVATE);
//Log.d("TAG", "getDir() : " + dir.getAbsolutePath());
//
//	// 获取应用程序外部存储的缓存目录路径
//	// SDCard/Android/data/<application package>/cache目录
//	File externalCacheDir = getExternalCacheDir();
//Log.d("TAG", "getExternalCacheDir() : " + externalCacheDir.getAbsolutePath());
//
//	// 获取应用程序外部存储的某一类型的文件目录，
//	// SDCard/Android/data/<application package>/files目录
//	// 这里的类型有
//	// Environment.DIRECTORY_MUSIC音乐
//	// Environment.DIRECTORY_PODCASTS 音频
//	// Environment.DIRECTORY_RINGTONES 铃声
//	// Environment.DIRECTORY_ALARMS 闹铃
//	// Environment.DIRECTORY_NOTIFICATIONS 通知铃声
//	// Environment.DIRECTORY_PICTURES 图片
//	// Environment.DIRECTORY_MOVIES 视频
//	File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//Log.d("TAG", "getExternalFilesDir() : " + externalFilesDir.getAbsolutePath());
//
//	// 获取应用的外部存储的缓存目录
//	File[] externalCacheDirs = getExternalCacheDirs();
//for (int i = 0; i < externalCacheDirs.length; i++) {
//		Log.d("TAG", "getExternalCacheDirs() " + i + " : " + externalCacheDirs[i].getAbsolutePath());
//	}
//
//	// 获取应用的外部存储的某一类型的文件目录
//	File[] externalFilesDirs = getExternalFilesDirs(Environment.DIRECTORY_MUSIC);
//for (int i = 0; i < externalFilesDirs.length; i++) {
//		Log.d("TAG", "getExternalFilesDirs() " + i + " : " + externalFilesDirs[i].getAbsolutePath());
//	}
//
//	// 获取应用的外部媒体文件目录
//	File[] externalMediaDirs = getExternalMediaDirs();
//for (int i = 0; i < externalMediaDirs.length; i++) {
//		Log.d("TAG", "getExternalMediaDirs() " + i + " : " + externalMediaDirs[i].getAbsolutePath());
//	}
//
//	// 获得应用程序指定数据库的绝对路径
//	// /data/data/<application package>/database/database.db目录
//	File databasePath = getDatabasePath("database.db");
//Log.d("TAG", "getDatabasePath() : " + databasePath.getAbsolutePath());
//
//
//	// -------------分界线-----------------------
//	// 以下是一些共有的目录，与APP包名无关，不会随APP卸载被删除
//	// /data目录
//	File dataDirectory = Environment.getDataDirectory();
//Log.d("TAG", "Environment.getDataDirectory() : " + dataDirectory.getAbsolutePath());
//	// /cache目录
//	File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
//Log.d("TAG", "Environment.getDownloadCacheDirectory() : " + downloadCacheDirectory.getAbsolutePath());
//	// /sdcard目录
//	File externalStorageDirectory = Environment.getExternalStorageDirectory();
//Log.d("TAG", "Environment.getExternalStorageDirectory() : " + externalStorageDirectory.getAbsolutePath());
//	// /sdcard/Pictures目录
//	File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//Log.d("TAG", "Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) : " + externalStoragePublicDirectory.getAbsolutePath());
//	// /system目录
//	File rootDirectory = Environment.getRootDirectory();
//Log.d("TAG", "Environment.getRootDirectory()() : " + rootDirectory.getAbsolutePath());
//}
