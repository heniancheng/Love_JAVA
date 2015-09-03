package com.designpattern.dl.factorymethod;

import com.designpattern.dl.abstractfactory.Human;


public abstract class AbstractHumanFactory {
	
	//泛型方法实现，可以用多个工厂实现
	public abstract <T extends Human> T createHuman(Class<T> c);

}
