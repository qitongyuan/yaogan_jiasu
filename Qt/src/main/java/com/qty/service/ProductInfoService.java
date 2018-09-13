package com.qty.service;

import com.qty.entity.ProductInfo;
import com.qty.utils.PageBean;

public interface ProductInfoService {
	/**
	 * 分页查询
	 * @param productInfo
	 * @param page
	 * @return
	 */
	public PageBean<ProductInfo>getProductInfoList(ProductInfo productInfo,Integer page);
	
	/**
	 * 查询记录个数
	 * @return
	 */
	public Long getProductInfoCount(ProductInfo productInfo);
	
	/**
	 * 加载显示的记录
	 */
	public ProductInfo loadProductInfo(ProductInfo productInfo);
}
