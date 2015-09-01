package io.java.core;

import java.io.*;

/*
 * 简单的输出处理流实例
 */

public class PrintStreamTest {
	
	public static void main(String[] args){
		PrintStream ps = null;
		try{
			FileOutputStream fos = new FileOutputStream(PublicConstants.FILE_SRC_PATH + "printstream.in");
			ps = new PrintStream(fos);
			String str = new String("普通字符串");
			ps.println(str);
			ps.println(new PrintStreamTest());
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally{
			ps.close();
		}
	}

}
