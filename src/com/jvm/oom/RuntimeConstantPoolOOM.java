package com.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/*
 * 深入理解JAVA虚拟机：JVM高级特性与最佳实践
 * 实战：OutOfMemoryError异常
 * 2.4.3java常量池溢出
 * JVM args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * jdk1.7会一直运行，但jdk1.6会抛出异常
 */

public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		int i=0;
		while(true){
			/*
			 * 如果字符串常量池中包含一个等于String对象的字条串
			 * 则直接返回对象；
			 * 否则将String包含的字符串加入常量池中
			 */
			list.add(String.valueOf(i++).intern());
		}
		
	}

}
