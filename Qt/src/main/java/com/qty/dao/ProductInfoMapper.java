/**
 * 
 */
package com.qty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qty.entity.ProductInfo;
import com.qty.entity.UserInfo;

/**
 * @author qty
 *
 */
@Mapper
public interface ProductInfoMapper {
	/**
	 * 分页显示历史记录
	 * 
	 * @return
	 */
	public List<ProductInfo>getProductInfoList(ProductInfo productInfo);
	
	/**
	 * 查询产品的总数getProductInfoCount
	 * @return
	 */
	public Long getProductInfoCount(ProductInfo productInfo);
	
	/**
	 * 根据条件查询产品
	 * @param productInfo
	 * @return
	 */
	public ProductInfo getProductInfo(ProductInfo productInfo);
}
