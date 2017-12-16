package com.cblue.oa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Encoder;

import com.cblue.oa.entity.Template;

public class FileUtils {
	
	//上传
	
	/**
	 * 上传的文件 放在不同的日期目录（yyyy/MM/dd） 这个文件不能重复（重命名）
	 * 
	 * @param file 保存的文件对象
	 * @param saveFolder 保存的文件的目录
	 * @return
	 */
	public static String uploadFile(File file,String saveFolder) {
		// TODO Auto-generated method stub
		// 获得上传路径
		String saveDir = ServletActionContext.getServletContext().getRealPath(
				"/WEB-INF/"+saveFolder);
		// 创建日期文件夹
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy-MM-dd/");
		String dateName = simpleDateFormat.format(new Date());
		String realDir = saveDir + dateName;
		File createFile = new File(realDir);
		if (!createFile.exists()) {
			createFile.mkdirs();
		}

		// 修改文件（不重复）UUID
		String fileName = realDir + UUID.randomUUID().toString() + ".doc";
		File realFile = new File(fileName);

		// 上传文件
		file.renameTo(realFile);

		return fileName;
	}
	
	
	//下载

	/**
	 * 得到模板流
	 * @param template
	 * @return
	 */
	public static InputStream download(Template template) {
		InputStream downloadInputStream = getFileInputStream(template.getFilePath());
		return downloadInputStream;
	}
	
	/**
	 * 
	 * @param template
	 * @return
	 */
	public static String downloadFileName(Template template){
		String fileName = null;
		String agent = ServletActionContext.getRequest().getHeader("user-agent");		
		try {
			fileName = encodeDownloadFilename(template.getName() + ".doc", agent) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
	
	

	public static InputStream getFileInputStream(String filePath) {
		// TODO Auto-generated method stub
		InputStream input = null;
		try {
			input = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return input;
	}

	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * 
	 * @param filename
	 *            下载文件名
	 * @param agent
	 *            客户端浏览器(通过request.getHeader("user-agent")获得)
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public static String encodeDownloadFilename(String filename, String agent)
			throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?"
					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
					+ "?=";
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
