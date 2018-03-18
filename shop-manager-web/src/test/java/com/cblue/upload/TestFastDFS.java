package com.cblue.upload;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class TestFastDFS {
	
	/**
	 * 1 创建配置文件，内容是tracker服务器的地址 conf/client.conf  tracker_server=IP:22122
	 * 2 加载配置文件
	 * 3 创建TrackerClient对象，获得一个TrackerService对象
	 * 4 创建StorageServer对象
	 * 5 创建StorageClient对象
	 * 6 上传文件
	 */
	
	@Test
	public void upload()throws Exception{
		ClientGlobal.init("Users/pavel/Documents/workspace/shop-manager-web/src/main/resources/conf/client.conf");
		
		TrackerClient trackerClient = new TrackerClient();
		
		TrackerServer trackerServer =  trackerClient.getConnection();
		
		StorageServer storageServer = null;
		
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		
		String str[]= storageClient.upload_appender_file("图片路径","jpg",null);
		
		for(String s:str){
			System.out.println(s);
		}
		
		
		
		
		
		
		
	}

}
