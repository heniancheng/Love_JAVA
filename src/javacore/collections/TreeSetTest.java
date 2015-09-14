package javacore.collections;

import java.util.*;


public class TreeSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedSet<Item> set = new TreeSet<Item>();
		set.add(new Item("yagao", 20));
		set.add(new Item("yashua", 10));
		set.add(new Item("maojin", 8));
		set.add(new Item("beizi", 3));
		System.out.println(set.toString());
		
		SortedSet<Item> other = new TreeSet<Item>(new Comparator<Item>(){

			@Override
			public int compare(Item a, Item b) {
				// TODO Auto-generated method stub
				return a.getDescription().compareTo(b.getDescription());
			}
			
		});
		
		other.addAll(set);
		System.out.println(other);
	}

}

class Item implements Comparable<Item>{

	private String description;
	private int partNumber;
	
	public Item(String description, int partNumber) {
		this.description = description;
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}

	public int getPartNumber() {
		return partNumber;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[description=" + description + ", partNumber=" + partNumber + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 13*this.description.hashCode() + 17*this.partNumber;
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		if(this == other) return true;
		if(other == null) return false;
		if(getClass() != other.getClass())return false;
		Item tmp = (Item) other;
		return this.description.equals(tmp.getDescription()) 
				&& this.partNumber == tmp.getPartNumber();
	}

	@Override
	public int compareTo(Item other) {
		// TODO Auto-generated method stub
		return this.partNumber - other.getPartNumber();
	}
	
}

