package com.acewill.slefpos.printer;

import android.text.TextUtils;

/**
 * 打印处理（58纸）
 *
 * @author aqw
 */
public class PrintUtils {

    public final static int TYPE_TOP = 1;
    public final static int TYPE_BACK = 2;
    public final static int TYPE_BOTH = 3;

    /**
     * 返回空格
     *
     * @param num
     * @return
     */
    public static String getSpace(int num) {
        char c = (char) 32;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < num; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 放大字体后中文占3位字符
     * 获取字符串的长度，如果有中文，则每个中文字符计为3位
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int length_s(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
                valueLength += 3;
            } else {
				/* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int length(String value) {
        if (TextUtils.isEmpty(value)) {
            return 0;
        }
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
            String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
                valueLength += 2;
            } else {
				/* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 拼装字符串
     *
     * @param str  显示的字符串
     * @param num  总共占用字符数
     * @param type TYPE_TOP:字符前面加空格，TYPE_BACK:后面加空格，TYPE_BOTH:前后都加
     * @return
     */
    public static String getStr(String str, int num, int type) {

        int lg = num - length(str);
        if (lg > 0) {
            String s = null;
            switch (type) {
                case TYPE_TOP:
                    s = getSpace(lg) + str;
                    break;
                case TYPE_BACK:
                    s = str + getSpace(lg);

                    break;
                case TYPE_BOTH:
                    s = getSpace(lg) + str + getSpace(lg);

                    break;

            }
            return s;
        }
        return str;
    }

    /**
     * 处理小票标题
     *
     * @param str 标题
     * @return
     */
    public static String getTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = (32 - length(str)) / 2;
        if (i > 0) {
            return getSpace(i) + str + getSpace(i);
        }
        return str;
    }

    /**
     * 处理菜品标题
     *
     * @return
     */
    public static String getDishTitle() {
        String str = getStr("菜品", 22, TYPE_BACK) + getStr("数量", 5, TYPE_BACK)
                + getSpace(1) + getStr("金额", 4, TYPE_BACK);
        return str;
    }

    /**
     * 格式桌台和开台人信息
     *
     * @param pre  桌台信息
     * @param back 开台人信息
     * @return
     */
    public static String getTop(String pre, String back) {
        int i = 32 - length(pre) - length(back);
        if (i > 0)
            return pre + getSpace(i) + back;
        return pre + " " + back;
    }

   

}
