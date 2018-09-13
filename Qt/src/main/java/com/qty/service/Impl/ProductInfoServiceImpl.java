/**
 * 
 */
package com.qty.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qty.dao.ProductInfoMapper;
import com.qty.entity.ProductInfo;
import com.qty.service.ProductInfoService;
import com.qty.utils.PageBean;
import com.qty.utils.PageUtil;

/**
 * @author qty
 *
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	@Override
	public PageBean<ProductInfo> getProductInfoList(ProductInfo productInfo,Integer page) {
		//查询总记录的个数
		int allRow=getProductInfoCount(productInfo).intValue();
		//计算总页数
		int totalPage=PageUtil.countTotalPage(allRow, 10);
		//计算当前页数
		int currentPage=PageUtil.countCurrentPage(page);
		//起始记录数
		int start=PageUtil.countStart(10, currentPage);
		System.out.println("开始页  "+start);
		if (page>=0) {
			//起始记录数
			productInfo.setStart(start);
			productInfo.setLength(10);
		}else {
			productInfo.setStart(-1);
		}
		List<ProductInfo>products=productInfoMapper.getProductInfoList(productInfo);
		PageBean<ProductInfo>pageBean=new PageBean<>();
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(products);
		pageBean.setPageSize(10);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public Long getProductInfoCount(ProductInfo productInfo) {
		return productInfoMapper.getProductInfoCount(productInfo);
	}

	@Override
	public ProductInfo loadProductInfo(ProductInfo productInfo) {
		return productInfoMapper.getProductInfo(productInfo);
	}

}
