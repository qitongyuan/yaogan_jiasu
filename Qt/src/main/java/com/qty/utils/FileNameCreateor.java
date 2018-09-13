/**
 * 
 */
package com.qty.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qty
 *
 */
public class FileNameCreateor {
	public static String createRandomName(String url,String filename) {
		DateFormat fmt=new SimpleDateFormat("yyyyMMddHHmmSSS");
		String format=fmt.format(new Date());
		
		//Ҫ����ĵ�ַ
		 url=url+format+filename;
		 return url;
	}

}
