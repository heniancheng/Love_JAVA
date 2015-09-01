package com.designpattern.rule;

/*
 * 依赖倒置原则实例
 */

public class Driver implements IDriver{

	@Override
	public void driver(ICar car) {
		// TODO Auto-generated method stub
		car.run();
	}
	
	

}
