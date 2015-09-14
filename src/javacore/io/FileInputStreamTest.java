package javacore.io;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/*
 * 简单的节点输入流实例
 * 
 * 问题：两种方法读取时，发现用字符流（数组大小512）出现与字节流不一致 512*2 ＝ 1024
 *      当数组大小变为1024时没问题
 * 规避：尽量还是大些为好
 */

public class FileInputStreamTest {
	
	public static void main(String[] args) throws IOException{
		
		//FileInputStream fis = new FileInputStream(FILE_SRC_PATH + "data.in");
		//byte[] buf = new byte[1024];
		
		FileReader fis = new FileReader(PublicConstants.FILE_SRC_PATH + "data.in");
		char[] buf = new char[1024];
		
		int hasRead = 0;
		while((hasRead = fis.read(buf)) >0){
			System.out.println(new String(buf, 0, hasRead));
		}
		fis.close();
	}
}
