package javacore.io;

import java.io.File;
import java.io.IOException;

/*
 * 问题：如何到指定路径下创建文件？
 */


public class FileTest {
	
	public final String FILE_SRC = "./file_src";
	
	public static void main(String[] args) throws IOException{
		File file = new File(".");
		System.out.println(file.getName());
		//注意：相对路径得不到父路径
		System.out.println(file.getParent());
		//建议：获取父路径时尽量使用绝对路径去获取
		System.out.println(file.getAbsoluteFile());
		System.out.println(file.getAbsoluteFile().getParent());
		
		File tmpFile = File.createTempFile("tmp", ".txt", file);
		tmpFile.deleteOnExit();
		
		File newFile = new File(System.currentTimeMillis() + "");
		System.out.println("the newfile is exsisted? " + newFile.exists());
		System.out.println(newFile.createNewFile());
		System.out.println(newFile.mkdir());
		newFile.deleteOnExit();
	
		String[] fileList = file.list();
		System.out.println("-----当前路径下的所有文件和路径------");
		for(String filename: fileList){
			System.out.println(filename);
		}
		
		System.out.println("-----系统所有根路径------");
		File[] roots = File.listRoots();
		for(File root: roots){
			System.out.println(root);
		}
		
	}

}
