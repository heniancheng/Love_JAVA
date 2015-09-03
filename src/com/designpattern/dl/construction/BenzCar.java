package com.designpattern.dl.construction;

public class BenzCar extends CarModel{

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		System.out.println("奔驰启动。。。");
	}

	@Override
	protected void stop() {
		// TODO Auto-generated method stub
		System.out.println("奔驰停止。。。");
	}

	@Override
	protected void alarm() {
		// TODO Auto-generated method stub
		System.out.println("奔驰鸣笛。。。");
	}

	@Override
	protected void engineBoom() {
		// TODO Auto-generated method stub
		System.out.println("奔驰引擎。。。");
	}

}
