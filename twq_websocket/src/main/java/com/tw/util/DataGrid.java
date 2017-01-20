package com.tw.util;

import java.util.ArrayList;
import java.util.List;

public class DataGrid<T> {

	private Long total = 0L;
	private List<T> rows = new ArrayList<T>();
	private List footer;

	public DataGrid() {
		super();
	}

	public DataGrid(Long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
