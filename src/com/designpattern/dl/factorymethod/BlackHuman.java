package com.designpattern.dl.factorymethod;

public class BlackHuman implements Human{

	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("我是黑种人");
	}

	@Override
	public void getColor() {
		// TODO Auto-generated method stub
		System.out.println("黑色");
	}

}
