/**
 * 
 */
package com.qty.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @author qty
 *
 */
public class Base64Encoding {
	
	//解码(将base64字符串转化成字节流数组)
	public  byte[] base64ToString(String base64Str) {
		return Base64.decodeBase64(base64Str);
	}
	
	//编码(将字节数组转化成字符串)
	public  String stringToBase64(byte[]buff) {	
		return Base64.encodeBase64String(buff);
	}

}
