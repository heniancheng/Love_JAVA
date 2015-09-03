package com.designpattern.dl.abstractfactory;

public abstract class WhiteHuman implements Human{
	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("我是白种男人");
	}

	@Override
	public void getColor() {
		// TODO Auto-generated method stub
		System.out.println("白色");
	}
}
