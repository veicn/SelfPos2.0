package com.acewill.slefpos.bean.uibean;

import com.acewill.slefpos.bean.cartbean.CartDish;

import java.util.ArrayList;
import java.util.List;

/**
 * 组数据的实体类
 */
public class GroupEntity {

	private String         header;
	private String         footer;
	private List<CartDish> children;

	public GroupEntity(String header, String footer, List<CartDish> children) {
		this.header = header;
		this.footer = footer;
		this.children = children;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public List<CartDish> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<CartDish> children) {
		this.children = children;
	}
}
