package cn.e3mall.fast;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDfsTest {
	
	@Test
	public void testUpload() throws Exception{
		//1.创建一个配置文件(client.conf)，文件名任意，内容就是tracker服务器的地址
		
		//2.使用全局对象加载配置文件
		ClientGlobal.init("G:/e3WorkSpace/e3-manager-wab/src/main/resources/conf/client.conf");
		//3.创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		//4.通过TrackClient获得TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//5.创建一个StorageServer的引用，可以是null
		StorageServer storageServer = null;
		//6.创建一个StorageClient，参数需要TrackerServer和StorageServer
		StorageClient storageClient = new StorageClient(trackerServer,storageServer);
		//7.使用StorageClient上传文件
		String [] strings = storageClient.upload_file("C:/Users/Administrator/Pictures/timg.jpg", "jpg",null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDfsClient() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("G:/e3WorkSpace/e3-manager-wab/src/main/resources/conf/client.conf");
		String string = fastDFSClient.uploadFile("C:/Users/Administrator/Pictures/timg.jpg");
		System.out.println(string);
		
	}
}
