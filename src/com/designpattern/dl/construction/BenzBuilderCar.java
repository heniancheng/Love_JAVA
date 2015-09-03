package com.designpattern.dl.construction;

import java.util.ArrayList;

public class BenzBuilderCar extends BuilderCar{

	CarModel benz = new BenzCar();
	@Override
	public void setSequence(ArrayList<String> sequence) {
		// TODO Auto-generated method stub
		this.benz.setSequence(sequence);
	}

	@Override
	public CarModel getCarModel() {
		// TODO Auto-generated method stub
		return this.benz;
	}

}
