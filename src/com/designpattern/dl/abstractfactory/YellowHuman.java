package com.designpattern.dl.abstractfactory;

public abstract class YellowHuman implements Human{

	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("我是黄种人");
	}

	@Override
	public void getColor() {
		// TODO Auto-generated method stub
		System.out.println("黄色");
	}

}
