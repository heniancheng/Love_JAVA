package javacore.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/*
 * 通过阻塞队列模拟生产文件/查询文件的同步
 * 阻塞的情况是向空队列移除元素，向满队列添加元素
 * 不能保证多线程的同步
 */

public class FutureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory(e.g. C:/Program Files/Java/jdk1.7.0_76): ");
		String directory = in.nextLine();
		System.out.println("Enter keyword(e.g. volatile): ");
		String keyword = in.nextLine();
		
		MarchCounter counter = new MarchCounter(keyword, new File(directory));
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		new Thread(task).start();
		
		try{
			System.out.println(task.get() + " marching file.");
			
		}catch(ExecutionException ex){
			ex.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class MarchCounter implements Callable<Integer>{
	
	private String keyword;
	private File startDirectory;

	public MarchCounter(String keyword, File startDirectory) {
		this.keyword = keyword;
		this.startDirectory = startDirectory;
	}

	private boolean search(File file) {
		// TODO Auto-generated method stub
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			boolean found = false;
			while (!found && in.hasNextLine()) {
				String line = in.nextLine();
				if (line.contains(keyword))
					found = true;
			}

			in.close();
			return found;
		} catch (IOException ex) {
			return false;
		}

	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			File[] files = this.startDirectory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();

			for (File file : files) {
				if (file.isDirectory()) {
					MarchCounter counter = new MarchCounter(keyword, file);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				} else {
					if (search(file))
						count++;
				}

			}

			for (Future<Integer> result : results) {
				try {
					count += result.get();
				} catch (ExecutionException ex) {
					ex.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
