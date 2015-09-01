package com.designpattern.rule;

/*
 * 开闭原则实例类
 */

public class NovelBook implements IBook{
	
	private String name;
	private double price;
	private String author;
	

	public NovelBook(String name, double price, String author) {
		super();
		this.name = name;
		this.price = price;
		this.author = author;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

}
