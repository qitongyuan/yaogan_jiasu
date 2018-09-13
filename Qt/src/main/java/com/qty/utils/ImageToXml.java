package com.qty.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class ImageToXml {
	
	//图片转为字节数组
	public byte[] toByte(String location) {
		byte[] buffer=null;
		try {
			// 将location所在的图片转化为字节流
			File file = new File(location);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(fis.available());
			byte[] bytes = new byte[fis.available()];
			int temp;
			while ((temp = fis.read(bytes)) != -1) {
				baos.write(bytes, 0, temp);
			}
			fis.close();
			baos.close();
			buffer = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	//字节数组插入到xml中
	public  File toXml(byte[] buffer) {
		// 构造xml,将字节数组存入xml中
		Document document = DocumentHelper.createDocument();
		// 创建根节点rss
		Element rss = document.addElement("rss");
		// 向rss节点中添加version属性
		rss.addAttribute("version", "2.0");
		//生成子节点及节点内容
		Element Image=rss.addElement("Image");
		Element NIRContent=Image.addElement("QTSU");
		//使用base64的编码格式进行编码(赋值给xml)
		NIRContent.setText(new Base64Encoding().stringToBase64(buffer));
		//设置生成xml的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		File file=new File("new.xml");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			//format.setEncoding("UTF-8");
			//设置是否转义，默认值是true，代表转义
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
/*	public static void main(String[] args) {
		byte[] buff=toByte("E:\\sharedPic\\NIR.bmp");
		File xml= toXml(buff);
		System.out.println(xml.exists());
	}*/

}
