package io.java.core;

import java.io.*;

/*
 * 对于接收数据（输入流）经常的用法是将字节流转换成字符流,然后再封装成处理流
 * new BufferedReader(new InputStreamReader(System.in))
 * 
 * 对于输出数据（输出流）经常是封装成处理流
 * new PrintStream(new FileOutputStream(file))
 */

public class GeneralIOTest {
	
	public static void main(String[] args){
		BufferedReader br = null;
		PrintStream ps = null;
		
		try{
			InputStreamReader reader = new InputStreamReader(System.in);
			ps = new PrintStream(new FileOutputStream(PublicConstants.FILE_SRC_PATH + "general.in"));
			br = new BufferedReader(reader);
			String buf = null;
			while((buf = br.readLine()) !=null){
				if(buf.equals("quit")){
					System.exit(1);
				}
				ps.println(buf);
			}
		}catch (IOException ioe){
			ioe.printStackTrace();
		}finally{
			try{
				if(br != null){
					br.close();
				}
				if(ps != null){
					ps.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}

}
