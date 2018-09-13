/**
 * 
 */
package com.qty.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qty.entity.ProductInfo;
import com.qty.entity.UserInfo;
import com.qty.service.ProductInfoService;
import com.qty.service.UserInfoService;
import com.qty.utils.PageBean;

/**
 * @author qty
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 跳转到登录页
	 * @return
	 */
	@RequestMapping("login")
	public String index() {
		return "login";
	}
	
	/**
	 * 登录逻辑
	 * @param session
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("doLogin")
	public String doLogin(HttpSession session,Model model,HttpServletRequest request,UserInfo userInfo) {
//		String username=request.getParameter("username");
//		String userpass=request.getParameter("userpass");
		UserInfo uInfo=userInfoService.doLogin(userInfo);
		if (uInfo!=null) {
			session.setAttribute("loginUser", uInfo);
			return "index";
		}else {
			return "login";
		}
//		if (username.equals("admin")&&userpass.equals("admin")) {
//			session.setAttribute("username", username);
//			model.addAttribute("username", username);
//			return "index";
//		}else {
//			return "login";
//		}
	}
	
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("doLogout")
	public String doLogout(HttpSession session) {
		session.removeAttribute("username");
		session.removeAttribute("Red_Image");
		session.removeAttribute("Nir_Image");
		return "login";
	}
	
	/**
	 * 显示主页内容
	 * @return
	 */
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	/**
	 * 显示遥感图像
	 * @return
	 */
	@RequestMapping("show")
	public String show() {
		return "show";
	}
	
	/**
	 * 显示每一条记录的图像详情
	 * @return
	 */
	@RequestMapping("showEach")
	public String showEach(ProductInfo productInfo,Model model) {
		ProductInfo pInfo=productInfoService.loadProductInfo(productInfo);
		model.addAttribute("productInfo", pInfo);
		return "showEach";
	}
	
	/**
	 * 分页查看历史并行记录（显示要根据登录用户区别显示）
	 * @return
	 */
	@RequestMapping("history")
	public String history(ProductInfo productInfo,Model model,Integer page) {
		PageBean<ProductInfo>pageBean=productInfoService.getProductInfoList(productInfo, page);
		model.addAttribute("pageBean", pageBean);
		return "history";
	}

}
