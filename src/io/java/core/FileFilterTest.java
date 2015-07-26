package io.java.core;

import java.io.File;
import java.io.FilenameFilter;

/*
 * 疑问：accept方法中的参数dir是用来干嘛的？
 */

public class FileFilterTest {
	
	public static void main(String[] args){
		File file = new File(".");
		String[] nameList = file.list(new MyFileFilter());
		for(String name: nameList){
			System.out.println(name);
		}
	}

}

class MyFileFilter implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return name.endsWith(".java") || new File(name).isDirectory();
	}
	
}