package com.jvm.oom;

/*
 *深入理解JAVA虚拟机：JVM高级特性与最佳实践
 * 实战：OutOfMemoryError异常
 * 2.4.2java栈溢出
 * JVM args:-Xss128k -XX:+HeapDumpOnOutOfMemoryError
 */

public class JvmStackSOF {
	
	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JvmStackSOF oom=new JvmStackSOF();
		try{
			oom.stackLeak();
		}catch(Throwable e){
			System.out.println("stack length: "+oom.stackLength);
			throw e;
		}
	}

}


