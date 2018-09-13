package com.qty.service.Impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qty.service.UploadService;
import com.qty.utils.FileNameCreateor;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

@Service
public class UploadServiceImpl implements UploadService {

	@Override
	public String doPutToFileServer(MultipartFile file) {
		String fileName=file.getOriginalFilename();
		String url=FileNameCreateor.createRandomName("http://10.12.52.155:8081/file_server/upload/", fileName);
		Client client=new Client();
		WebResource resource=client.resource(url);
		//向服务器发文件
		try {
			byte[]buf=file.getBytes();
			resource.put(String.class, buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

}
