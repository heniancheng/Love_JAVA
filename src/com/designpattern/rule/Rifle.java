package com.designpattern.rule;

/*
 * 里氏替换原则实例
 */

public class Rifle extends AbstractGun{

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		System.out.println("步枪射击。。。");
	}

}
