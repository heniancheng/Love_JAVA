package com.designpattern.rule;

/*
 * 开闭原则实例类
 */

public class OffNovelBook extends NovelBook{

	public OffNovelBook(String name, double price, String author) {
		super(name, price, author);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice()*90/100;
	}
	
	

}
