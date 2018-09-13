package server;


import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.server.UnicastRemoteObject;

import com.qty.utils.Base64Encoding;

import java.rmi.RemoteException;
public class HelloImpl  extends UnicastRemoteObject implements IHello {
     

	 public HelloImpl() throws RemoteException {

	 }

	/**
	 * 用于测试连接成功与否
	 */
	public String sayHello(String message) throws RemoteException {
		System.out.println("================client message" + message);
		return "连接成功!！";
	}

	/**
	 * 向服务器发送base64编码格式的XML,xml中附带两张图片
	 */
	public String sendXml(String base64Message) throws RemoteException {
		//接收到base64编码的的字符串，将其转化为字节流
		byte[]buffer=new Base64Encoding().base64ToString(base64Message);
		//将字节流转化为文件存储在本地
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		try {
			File file=new File("NDVI.xml");
			if (!file.exists()) {
				file.mkdir();
			}
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos);
			bos.write(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (bos!=null) {
					bos.close();
				}
				if (fos!=null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "发送的文件已经接收到";
	}
	 


} 