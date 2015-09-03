package com.designpattern.dl.singleton;

public class Emperor {
	// 饿汉式方法（推荐）
	private static final Emperor emperor = new Emperor();

	public static Emperor getInstance() {
		return emperor;
	}
	 
	// 懒汉式方法
	/*
	private static Emperor emperor = null;

	public static Emperor getInstance() {
		if (emperor == null) {
			synchronized (Emperor.class) {
				if (emperor == null)
					emperor = new Emperor();
			}
		}
		return emperor;
	}
	*/
}
