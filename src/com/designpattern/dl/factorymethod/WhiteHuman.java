package com.designpattern.dl.factorymethod;

public class WhiteHuman implements Human{

	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("我是白人");
	}

	@Override
	public void getColor() {
		// TODO Auto-generated method stub
		System.out.println("白色");
	}

}
