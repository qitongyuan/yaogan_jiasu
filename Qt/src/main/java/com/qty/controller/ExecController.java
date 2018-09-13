/**
 * 
 */
package com.qty.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qty.entity.ProductInfo;
import com.qty.entity.UserInfo;
import com.qty.service.ProductInfoService;
import com.qty.utils.Base64Encoding;

import server.IHello;



/**
 * @author qty
 *
 */
@Controller
@RequestMapping("/exec/")
public class ExecController {
	
	@Autowired
	private ProductInfoService productInfoService;
	
	/**
	 * 跳转至上传页面
	 * @return
	 */
	@RequestMapping("chuan")
	public String chuan(){
		return "exec/chuan";
	}
	
	/**
	 * 跳转至服务器选择页面
	 * @return
	 */
	@RequestMapping("server")
	public String server(){
		return "exec/server";
	}
	
	/**
	 * 跳转至分析页面
	 * @return
	 */
	@RequestMapping("analysis")
	public String analysis() {
		return "exec/analysis";
	}
	
	/**
	 * 查看每一个页面的详情，将详情信息传送带该页面中
	 * @param productInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("analy")
	public String analysisEach(ProductInfo productInfo,Model model) {
		ProductInfo pInfo=productInfoService.loadProductInfo(productInfo);
		model.addAttribute("productInfo", pInfo);
		return "exec/analy";
	}
	
	/**
	 * 测试与linux服务器的连接,成功则返回“连接成功”
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("testConnect")
	@ResponseBody
	public String test_connect(HttpSession session) throws Exception {
		IHello hello = (IHello)Naming.lookup("rmi://10.12.37.218:8887/hello");
		String rstr=hello.sayHello(" test connect");
		System.out.println(rstr);
		return rstr;
	}
	
	/**
	 * 发送xml到linux主机(新加使用的用户ID，在服务端填充数据库字段用)
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	@RequestMapping("sendXml")
	@ResponseBody
	public String send_xml(HttpSession session) throws MalformedURLException, RemoteException, Exception {
		//拼装xml,从session中拿图片链接
		String Red=(String) session.getAttribute("Red_Image");
		String Nir=(String) session.getAttribute("Nir_Image");
		UserInfo user=(UserInfo)session.getAttribute("loginUser");
		// 构造xml,将字节数组存入xml中
		Document document = DocumentHelper.createDocument();
		// 创建根节点rss
		Element rss = document.addElement("rss");
		// 向rss节点中添加version属性
		rss.addAttribute("version", "2.0");
		//生成子节点及节点内容
		Element Image=rss.addElement("Image");
		Element RedImage=Image.addElement("RED");
		Element NirImage=Image.addElement("NIR");
		RedImage.setText(Red);
		NirImage.setText(Nir);
		//生成存储用户ID的节点
		Element UserId=rss.addElement("UerId");
		UserId.setText(user.getUserId().toString());
		//设置生成xml的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		File xmlfile=new File("NDVI.xml");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(xmlfile), format);
			//format.setEncoding("UTF-8");
			//设置是否转义，默认值是true，代表转义
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//将xmlfile 文件传送给linxu虚拟机（本应返回另一个xml的，也可以在Linux上将数据入库）这里先返回一个"success"
		IHello hello = (IHello)Naming.lookup("rmi://10.12.37.218:8887/hello");
		System.out.println("文件大小为："+xmlfile.length());
		System.out.println("文件是否存在"+xmlfile.exists());
		//将文件转为字节流
		byte[]buffer=new byte[1024];
		FileInputStream fis=new FileInputStream(xmlfile);
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len;
		while ((len=fis.read(buffer))!=-1) {
	        bos.write(buffer, 0, len);
		}
		fis.close();
		bos.close();
		buffer=bos.toByteArray();
		//将字节流转为base64字符串
		String str= new Base64Encoding().stringToBase64(buffer);
		String rstr=hello.sendXml(str);
		System.out.println(rstr);
		return rstr;
	}
	
	
	

}
