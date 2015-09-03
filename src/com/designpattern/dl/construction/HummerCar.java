package com.designpattern.dl.construction;

public class HummerCar extends CarModel{

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		System.out.println("悍马启动。。。");
	}

	@Override
	protected void stop() {
		// TODO Auto-generated method stub
		System.out.println("悍马停止。。。");
	}

	@Override
	protected void alarm() {
		// TODO Auto-generated method stub
		System.out.println("悍马鸣笛。。。");
	}

	@Override
	protected void engineBoom() {
		// TODO Auto-generated method stub
		System.out.println("悍马引擎。。。");
	}

}
