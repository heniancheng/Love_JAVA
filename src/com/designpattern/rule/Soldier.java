package com.designpattern.rule;

/*
 * 里氏替换原则实例
 */

public class Soldier {
	
	AbstractGun gun=null;
	
	public void killEnemy(){
		gun.shoot();
	}

	public void setGun(AbstractGun gun) {
		this.gun = gun;
	}

}
