package com.lms.util;

import java.util.Iterator;
import java.util.Map;

public class GridParams {
	
	private int pageNumber = 0;
	private int rowsPerPage = 0;
	private String sortColum = "";
	private String sortOrder = "asc";
	private String action = "";
	private String query = "";
	private String qtype = "";
	
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getSortColum() {
		return sortColum;
	}
	public void setSortColum(String sortColum) {
		this.sortColum = sortColum;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public void populate(Map<String, String[]> map) {
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String[] val = map.get(key);
			if (key.equals("page")) {
				pageNumber = Integer.parseInt(val[0]);
			}
			if (key.equals("rp")) {
				rowsPerPage = Integer.parseInt(val[0]);
			}
			if (key.equals("sortname")) {
				sortColum = val[0];
			}
			if (key.equals("sortorder")) {
				sortOrder = val[0];
			}
			if (key.equals("action")) {
				action = val[0];
			}
			if (key.equals("qtype")) {
				qtype = val[0];
			}if (key.equals("query")) {
				query = val[0];
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("---- Grid Parameters ----\n");
		sb.append("Page Number Requested: ").append(pageNumber).append("\n");
		sb.append("Rows Per Page: ").append(rowsPerPage).append("\n");
		sb.append("Sort Column: ").append(sortColum).append("\n");
		sb.append("Sort Order: ").append(sortOrder).append("\n");
		sb.append("Action: ").append(action).append("\n");
		sb.append("qtype: ").append(qtype).append("\n");
		
		return sb.toString();
	}
}
