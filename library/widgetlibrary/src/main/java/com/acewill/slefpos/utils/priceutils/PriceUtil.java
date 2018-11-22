package com.acewill.slefpos.utils.priceutils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PriceUtil {
	public static BigDecimal multiply(String price, int quantity) {
		return multiply(new BigDecimal(price), quantity);
	}

	public static BigDecimal multiply(BigDecimal price, int quantity) {
		return price.multiply(new BigDecimal(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal multiply(BigDecimal price, BigDecimal rate) {
		return price.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal multiply(BigDecimal price, BigDecimal rate, int scale) {
		return price.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal multiplynofloat(BigDecimal price, BigDecimal rate) {
		return price.multiply(rate).setScale(0, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal add(String price1, String price2) {
		return add(new BigDecimal(price1), new BigDecimal(price2));
	}

	public static BigDecimal add(BigDecimal price1, String price2) {
		return add(price1, new BigDecimal(price2));
	}

	public static BigDecimal add(BigDecimal price1, BigDecimal price2) {
		return price1.add(price2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}


	public static BigDecimal subtract(String price1, String price2) {
		return subtract(new BigDecimal(price1), new BigDecimal(price2));
	}

	public static BigDecimal subtract(BigDecimal price1, String price2) {
		return subtract(price1, new BigDecimal(price2));
	}

	public static BigDecimal subtract(BigDecimal price1, BigDecimal price2) {
		return price1.subtract(price2).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal divide(String price1, String price2) {
		return divide(new BigDecimal(price1), new BigDecimal(price2));
	}

	public static BigDecimal divide(BigDecimal price1, BigDecimal price2) {
		return price1.divide(price2, 2, BigDecimal.ROUND_HALF_UP);
	}

	public static String formatPrice(String price) {
		BigDecimal    decimal = new BigDecimal(price).setScale(BigDecimal.ROUND_HALF_UP);
		DecimalFormat df      = new DecimalFormat("0.00");
		return df.format(decimal.doubleValue());
	}

	public static String formatPrice(float price) {
		return formatPrice(String.valueOf(price));
	}

	public static String formatPrice(BigDecimal price) {
		return formatPrice(String.valueOf(price));
	}

	/**
	 * 反转rate,比如：传入8.5，返回0.15
	 *
	 * @param rate
	 * @return
	 */
	public static BigDecimal reversalRate(float rate) {
		return divide(PriceUtil
				.subtract(new BigDecimal(10), new BigDecimal(rate)), new BigDecimal(10));
	}

	public static int compare(String cost1, String cost2) {
		return subtract(multiply(cost1, 100), multiply(cost2, 100)).intValue();
	}
}
