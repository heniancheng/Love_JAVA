package com.designpattern.dl.construction;

import java.util.ArrayList;

public class HummerBuilderCar extends BuilderCar{

	CarModel hummerCar = new HummerCar();
	@Override
	public void setSequence(ArrayList<String> sequence) {
		// TODO Auto-generated method stub
		this.hummerCar.setSequence(sequence);
	}

	@Override
	public CarModel getCarModel() {
		// TODO Auto-generated method stub
		return this.hummerCar;
	}

}
