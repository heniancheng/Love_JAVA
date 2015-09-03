package com.designpattern.dl.abstractfactory;

public class FemaleFactory implements AbstractHumanFactory{

	@Override
	public Human createYellowHuman() {
		// TODO Auto-generated method stub
		return new FemaleYellowHuman();
	}

	@Override
	public Human createWhiteHuman() {
		// TODO Auto-generated method stub
		return new FemaleWhiteHuman();
	}

	@Override
	public Human createBlackHuman() {
		// TODO Auto-generated method stub
		return new FemaleBlackHuman();
	}

}
