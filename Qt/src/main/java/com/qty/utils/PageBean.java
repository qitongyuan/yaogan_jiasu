/**
 * 
 */
package com.qty.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author qty  2018年3月8日
 *  在视图之间进行数据传输的类
 */
public class PageBean<T> implements Serializable {
	/*
	 * 要返回的某一页集合
	 */
	private List<T>list;
	/*
	 * 总记录数
	 */
	private int allRow;
	/*
	 * 总页数
	 */
	private int totalPage;
	/*
	 * 当前是第几页
	 */
	private int currentPage;
	/*
	 * 页容量
	 */
	private int pageSize;
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
