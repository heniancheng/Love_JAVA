package com.designpattern.dl.construction;

import java.util.ArrayList;

public abstract class CarModel {
	
	private ArrayList<String> sequence = new ArrayList<String>();
	
	public void setSequence(ArrayList<String> sequence) {
		this.sequence = sequence;
	}
	protected abstract void start();
	protected abstract void stop();
	protected abstract void alarm();
	protected abstract void engineBoom();
	
	//操作框架
	final public void run(){
		for(int i=0;i<this.sequence.size();i++){
			String actionName = this.sequence.get(i);
			if(actionName.equals("start")){
				this.start();
			}
			if(actionName.equals("stop")){
				this.stop();
			}
			if(actionName.equals("alarm")){
				this.alarm();
			}
			if(actionName.equals("engine boom")){
				this.engineBoom();
			}
		}
	}

}
