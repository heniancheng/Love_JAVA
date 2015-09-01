package io.java.core;

import java.io.*;

/*
 * 简单的节点输出流实例
 */

public class FileOutputStreamTest {
	
	public static void main(String[] args){
		//FileInputStream fis = null;
		//FileOutputStream fos = null;
		
		FileReader fis = null;
		FileWriter fos = null;
		
		try{
			//fis = new FileInputStream(PublicConstants.FILE_SRC_PATH + "data.in");
			//fos = new FileOutputStream(PublicConstants.FILE_SRC_PATH + "copydata.in");
			//byte[] buf = new byte[1024];
			
			fis = new FileReader(PublicConstants.FILE_SRC_PATH + "data.in");
			fos = new FileWriter(PublicConstants.FILE_SRC_PATH + "copydata.in");
			char[] buf = new char[1024];
			
			int hasRead = 0;
			while((hasRead=fis.read(buf))>0){
				System.out.println(new String(buf, 0, hasRead));
				fos.write(buf);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(fis != null){
					fis.close();
				}
				if(fos != null){
					fos.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
}
