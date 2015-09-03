package com.designpattern.dl.construction;

import java.util.ArrayList;

public class Director {
	public ArrayList<String> sequence=new ArrayList<String>();
	HummerBuilderCar hummerbuilder=new HummerBuilderCar();
	BenzBuilderCar benzbuilder=new BenzBuilderCar();
	
	public BenzCar getBenzCar(){
		this.sequence.clear();
		this.sequence.add("engine boom");
		this.sequence.add("start");
		this.sequence.add("stop");
		this.benzbuilder.setSequence(this.sequence);
		return (BenzCar) this.benzbuilder.getCarModel();
		
	}
	
	public HummerCar getHummerCar(){
		this.sequence.clear();
		this.sequence.add("alarm");
		this.sequence.add("start");
		this.sequence.add("stop");
		this.hummerbuilder.setSequence(this.sequence);
		return (HummerCar) this.hummerbuilder.getCarModel();
		
	}
}
