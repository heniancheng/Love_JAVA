package net.java.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.*;
import java.util.Arrays;

class DownThread extends Thread{
	private final int BUFF_LEN = 32;
	private long start;
	private long end;
	
	private InputStream in;
	private RandomAccessFile raf;
	
	public DownThread(long start, long end, InputStream in, RandomAccessFile raf){
		System.out.println(start + " ------> " + end);
		this.start = start;
		this.end = end;
		this.in = in;
		this.raf = raf;
	}
	//写入数据到文件
	public void run(){
		try{
			in.skip(start);
			raf.seek(start);
			byte[] buff = new byte[BUFF_LEN];
			long contentlen = end - start;
			long times = contentlen / BUFF_LEN + 4;
			int hasRead = 0;
			
			for(int i = 0; i < times; i++){
				hasRead = in.read(buff);
				if(hasRead < 0)
					break;
				raf.write(buff, 0, hasRead);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(in != null){
					in.close();
				}
				if(raf != null){
					raf.close();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}

public class MutilDown{
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		final int DOWN_THREAD_NUM = 4;
		final String OUT_FILE_NAME = "index.html";
		InputStream[] inArr = new InputStream[DOWN_THREAD_NUM];
		RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
		try{
			//创建URL对象
			URL url = new URL("https://www.baidu.com/index.html");
			//以此URL打开一个输入流
			URLConnection urlcon = url.openConnection();
			inArr[0] = urlcon.getInputStream();
			//以此URL打开一个输入流
			//inArr[0] = url.openStream();
			//获取资源大小
			long filelen = getFileLength(url);
			System.out.println("网络资源大小：" + filelen);
			outArr[0] = new RandomAccessFile(OUT_FILE_NAME,"rw");
			for(int i=0; i<filelen;i++){
				outArr[0].write(0);
			}
			
			byte[] str = new byte[(int) filelen];
			outArr[0].read(str);
			System.out.println(Arrays.toString(str));
			
			//建立线程写数据到文件
			long numPerThread = filelen / DOWN_THREAD_NUM;
			long left = filelen % DOWN_THREAD_NUM;
			for(int i=0;i<DOWN_THREAD_NUM;i++){
				if(i!=0){
					inArr[i] = url.openStream();
					outArr[i] = new RandomAccessFile(OUT_FILE_NAME,"rw");
				}
				if(i == DOWN_THREAD_NUM -1){
					new DownThread(i*numPerThread, (i+1)*numPerThread+left,inArr[i],outArr[i]).start();
				}
				else{
					new DownThread(i*numPerThread, (i+1)*numPerThread,inArr[i],outArr[i]).start();
				}
			}
		
			new RandomAccessFile(OUT_FILE_NAME,"rw").read(str);
			System.out.println(Arrays.toString(str));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	//获取网络资源大小
	private static long getFileLength(URL url) throws Exception {
		// TODO Auto-generated method stub
		long length = 0;
		
		//打开链接
		URLConnection con = url.openConnection();
		long size = con.getContentLength();
		length = size;
		return length;
	}
}
