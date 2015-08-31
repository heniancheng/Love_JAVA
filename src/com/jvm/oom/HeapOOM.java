package com.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/*
 * 深入理解JAVA虚拟机：JVM高级特性与最佳实践
 * 实战：OutOfMemoryError异常
 * 2.4.1java堆溢出
 * JVM args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */

public class HeapOOM {
	static class OOMObject{
		
	}
	
	public static void main(String[] args){
		List<OOMObject> list=new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}

}
