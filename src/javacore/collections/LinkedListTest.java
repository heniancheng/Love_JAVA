package javacore.collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * 使用两个迭代器的不同情况
 */

public class LinkedListTest {
	
	public static void main(String[] args){
		LinkedList<Character> lista = new LinkedList<Character>();
		LinkedList<Character> listb = new LinkedList<Character>();
		lista.add('A');
		lista.add('C');
		lista.add('E');
		listb.add('B');
		listb.add('D');
		listb.add('F');
		ListIterator itera = lista.listIterator();
		Iterator iterb = listb.iterator();
		while(iterb.hasNext()){
			if(itera.hasNext())itera.next();
			itera.add(iterb.next());
		}
		System.out.println(lista.toString());
		
		iterb = listb.iterator();
		while(iterb.hasNext()){
			iterb.next();
			if(iterb.hasNext()){
				iterb.next();
				iterb.remove();
			}
		}
		System.out.println(listb.toString());
		
	}

}
