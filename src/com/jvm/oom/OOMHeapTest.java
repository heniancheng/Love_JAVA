package com.jvm.oom;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * 参考网上博文如何使用Memory Analyzer分析
 * http://blog.csdn.net/rachel_luo/article/details/8992461
 * 在Leak Suspects中可以点击Details查看结节
 * Accumulated Objects in Dominator Tree中可以看到内存占用情况
 * 两个参数（Shallow Heap=自身；Retained Heap=自身+引用对象）
 * Accumulated Objects by Class in Dominator Tree中查看具体情况
 *  List Objects->with outgoing reference查看引用了哪引动对象
 *  Path to GC roots->exclude weak reference 查看被引用情况
 */

public class OOMHeapTest {

	public static void main(String[] args) {
		oom();
	}

	private static void oom() {
		Map<String, Pilot> map = new HashMap<String, Pilot>();
		Object[] array = new Object[1000000];
		for (int i = 0; i < 1000000; i++) {
			String d = new Date().toString();
			Pilot p = new Pilot(d, i);
			map.put(i + "rosen jiang", p);
			array[i] = p;
		}
	}
}

class Pilot{
    
    String name;
    int age;
    
    public Pilot(String a, int b){
        name = a;
        age = b;
    }
}
