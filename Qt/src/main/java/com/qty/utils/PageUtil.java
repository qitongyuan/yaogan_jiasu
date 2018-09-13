package com.qty.utils;

public class PageUtil {
	/*
	 * 计算总页数
	 */
	public static int countTotalPage(int allRow,int pageSize) {
		return allRow % pageSize==0?allRow/pageSize:allRow/pageSize+1;
	}
	
	/*
	 * 默认第一页
	 */
	public static int countCurrentPage(int currentPage) {
		return currentPage==0?1:currentPage;
	}
	
	/*
	 * 计算起始记录数 
	 */
	public static int countStart(int pageSize, int currentPage){
		return pageSize * (currentPage - 1);
	}
}
