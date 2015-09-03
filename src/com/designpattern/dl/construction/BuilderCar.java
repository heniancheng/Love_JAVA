package com.designpattern.dl.construction;

import java.util.ArrayList;

public abstract class BuilderCar {
	public abstract void setSequence(ArrayList<String> sequence);
	public abstract CarModel getCarModel();

}
