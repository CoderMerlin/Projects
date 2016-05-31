package com.yc.hdmedia.entity;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class JsonObject<T> {
	private List<T> Rows;
	private int total;
	
	
	public List<T> getRows() {
		return Rows;
	}
	public void setRows(List<T> rows) {
		Rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
