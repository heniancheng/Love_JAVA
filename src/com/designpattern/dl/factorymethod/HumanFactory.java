package com.designpattern.dl.factorymethod;

import com.designpattern.dl.abstractfactory.Human;


public class HumanFactory extends AbstractHumanFactory{

	@Override
	public <T extends Human> T createHuman(Class<T> c) {
		// TODO Auto-generated method stub
		Human human=null;
		try{
			human = (T) Class.forName(c.getName()).newInstance();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return (T) human;
	}

}
