/**
 * 
 */
package com.qty.entity;

import java.io.Serializable;

import com.qty.utils.BaseBean;

/**
 * @author qty
 *
 */
public class ProductInfo extends BaseBean implements Serializable {
	
	private Integer productId;
	private Integer userId;
	private String  productSize;
	private String  productStatus;
	private String  productQustResult;
	private String  productNDVIResult;
	private String  productStrerchResult;
	private String  productHistEqulResult;
	private String  productImHistResult;
	private String  productResult;
	private String  productTime;
	private String  productNirImage;
	private String  productRedImage;
	private String  productNDVIImage;
	private String  productQUSTImage;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getProductNDVIImage() {
		return productNDVIImage;
	}
	public void setProductNDVIImage(String productNDVIImage) {
		this.productNDVIImage = productNDVIImage;
	}
	public String getProductQUSTImage() {
		return productQUSTImage;
	}
	public void setProductQUSTImage(String productQUSTImage) {
		this.productQUSTImage = productQUSTImage;
	}
	public String getProductQustResult() {
		return productQustResult;
	}
	public void setProductQustResult(String productQustResult) {
		this.productQustResult = productQustResult;
	}
	public String getProductNDVIResult() {
		return productNDVIResult;
	}
	public void setProductNDVIResult(String productNDVIResult) {
		this.productNDVIResult = productNDVIResult;
	}
	public String getProductStrerchResult() {
		return productStrerchResult;
	}
	public void setProductStrerchResult(String productStrerchResult) {
		this.productStrerchResult = productStrerchResult;
	}
	public String getProductHistEqulResult() {
		return productHistEqulResult;
	}
	public void setProductHistEqulResult(String productHistEqulResult) {
		this.productHistEqulResult = productHistEqulResult;
	}
	public String getProductImHistResult() {
		return productImHistResult;
	}
	public void setProductImHistResult(String productImHistResult) {
		this.productImHistResult = productImHistResult;
	}
	public String getProductNirImage() {
		return productNirImage;
	}
	public void setProductNirImage(String productNirImage) {
		this.productNirImage = productNirImage;
	}
	public String getProductRedImage() {
		return productRedImage;
	}
	public void setProductRedImage(String productRedImage) {
		this.productRedImage = productRedImage;
	}
	public String getProductTime() {
		return productTime;
	}
	public void setProductTime(String productTime) {
		this.productTime = productTime;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getProductResult() {
		return productResult;
	}
	public void setProductResult(String productResult) {
		this.productResult = productResult;
	}
	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", productSize=" + productSize + ", productStatus="
				+ productStatus + ", productResult=" + productResult + ", productTime=" + productTime
				+ ", productNirImage=" + productNirImage + ", productRedImage=" + productRedImage + "]";
	}
	


	

}
