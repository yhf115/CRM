package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;

@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map uploadFile(MultipartFile uploadFiles){
		try {
			//1.把图片上传到图片服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			//取文件扩展名
			String originalFilename = uploadFiles.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			
			//2.得到一个图片的地址和文件名
			String url = fastDFSClient.uploadFile(uploadFiles.getBytes(),extName);
			//3.补充为完整的url
			url = IMAGE_SERVER_URL + url;
			//4.封装到map中返回
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "上传失败");
			return result;
		}
		
		
		
	}
}
