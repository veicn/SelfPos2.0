package com.acewill.slefpos.utils.uiutils;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Author：Anch
 * Date：2017/5/17 11:42
 * Desc：
 */
public class BezierEvaluator implements TypeEvaluator<Point> {

	private Point controllPoint;

	public BezierEvaluator(Point controllPoint) {
		this.controllPoint = controllPoint;
	}

	/**
	 * https://www.jianshu.com/p/7c56103dcf63
	 * n 阶贝塞尔曲线计算公式实现
	 * @param t
	 * @param startValue
	 * @param endValue
	 * @return
	 */
	@Override
	public Point evaluate(float t, Point startValue, Point endValue) {
		int x = (int) ((1 - t) * (1 - t) * (startValue.x - 100) + 2 * t * (1 - t) * (controllPoint.x - 300) + t * t * endValue.x);
		int y = (int) ((1 - t) * (1 - t) * (startValue.y - 100) + 2 * t * (1 - t) * (controllPoint.y - 500) + t * t * endValue.y);
		return new Point(x, y);
	}
}