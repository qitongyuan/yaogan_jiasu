/**
 * 
 */
package com.qty.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qty.service.UploadService;

/**
 * @author qty
 *
 */
@Controller
@RequestMapping("/upload/")
public class UploadController {
	
	@Autowired
	private UploadService uploadService;
	
	/**
	 * 上传红外的图像，将文件上传自己的文件服务器上，url已经存在session中
	 * @param request
	 * @param response
	 * @param file
	 */
	@RequestMapping("Red")
	@ResponseBody
	public String uploadRed(HttpSession session,HttpServletRequest request,HttpServletResponse response
			,@RequestParam("Red")MultipartFile file) {
		if (file!=null) {
			String fileName=file.getOriginalFilename();
			System.out.println("文件名  "+fileName);
			System.out.println("文件大小 "+file.getSize());
			String url=uploadService.doPutToFileServer(file);
			session.setAttribute("Red_Image", url);
			session.setAttribute("Red_Name", fileName);
			System.out.println(url);
			return "1";
		}else {
			return "-1";
		}
	}
	
	/**
	 * 上传近红外的图像，将文件上传自己的文件服务器上，url已经存在session中
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping("Nir")
	@ResponseBody
	public String uploadNir(HttpSession session,HttpServletRequest request,HttpServletResponse response
			,@RequestParam("Nir")MultipartFile file) {
		if (file!=null) {
			String fileName=file.getOriginalFilename();
			System.out.println("文件名  "+fileName);
			System.out.println("文件大小 "+file.getSize());
			String url=uploadService.doPutToFileServer(file);
			session.setAttribute("Nir_Image", url);
			session.setAttribute("Nir_Name", fileName);
			System.out.println(url);
			return "1";
		}else {
			return "-1";
		}
	}
	
	

}
