package com.designpattern.dl.model;

public abstract class HummerModel {
	protected abstract void start();
	protected abstract void stop();
	protected abstract void alarm();
	protected abstract void engineBoom();
	
	//操作框架
	public void run(){
		this.start();
		this.engineBoom();
		if(this.isAlarm()){
			this.alarm();
		}
		this.stop();
	}
	
	//钩子方法，判断是否鸣笛
	protected boolean isAlarm(){
		return true;
	}

}
