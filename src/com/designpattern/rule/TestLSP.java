package com.designpattern.rule;

/*
 * 里氏替换原则实例
 */

public class TestLSP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Soldier zhansi = new Soldier();
		AbstractGun gun = new Headgun();
		zhansi.setGun(gun);
		zhansi.killEnemy();
	}

}
