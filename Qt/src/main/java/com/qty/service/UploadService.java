/**
 * 
 */
package com.qty.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author qty
 *
 */
public interface UploadService {
	/**
	 * 向文件服务器发送图片，并返回图片的所在地址
	 * @param file
	 * @return
	 */
	public String doPutToFileServer(MultipartFile file);

}
