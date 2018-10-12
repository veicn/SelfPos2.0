package com.acewill.slefpos.utils.uiutils;

import java.util.Random;

/**
 * Author：Anch
 * Date：2018/5/16 9:55
 * Desc：
 */
public class NumberUtil {
	public static String getRandomNum() {
		//		String str = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String str    = "0,1,2,3,4,5,6,7,8,9";
		String str2[] = str.split(",");//将字符串以,分割
		Random rand    = new Random();//创建Random类的对象rand
		int    index   = 0;
		String randStr = "";//创建内容为空字符串对象randStr
		for (int i = 0; i < 4;i++) {
			index = rand.nextInt(str2.length - 1);//在0到str2.length-1生成一个伪随机数赋值给index
			randStr += str2[index];//将对应索引的数组与randStr的变量值相连接
		}
		return randStr;
	}


}
