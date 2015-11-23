package javacore.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * 通过阻塞队列模拟生产文件/查询文件的同步
 * 阻塞的情况是向空队列移除元素，向满队列添加元素
 * 不能保证多线程的同步
 */

public class BlockingQueueTest {
	
	public static final int FILE_QUEUE_SIZE = 10;
	public static final int SEARCH_THREADS = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory(e.g. C:/Program Files/Java/jdk1.7.0_76: ");
		String directory = in.nextLine();
		System.out.println("Enter keyword(e.g. volatile: ");
		String keyword = in.nextLine();
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
		
		FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumerator).start();
		for(int i = 1; i <= SEARCH_THREADS; i++){
			new Thread(new SearchTask(queue, keyword)).start();
		}
	}

}

class FileEnumerationTask implements Runnable{
	
	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startDirectory;
	
	public FileEnumerationTask(BlockingQueue<File> queue, File directory) {
		this.queue = queue;
		this.startDirectory = directory;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			enumerate(startDirectory);
			queue.put(DUMMY);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	private void enumerate(File directory) throws InterruptedException {
		// TODO Auto-generated method stub
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory())enumerate(file);
			else queue.put(file);
		}
	}
	
}

class SearchTask implements Runnable{
	private BlockingQueue<File> queue;
	private String keyword;

	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			boolean done = false;
			while(!done){
				File file = queue.take();
				if(file == FileEnumerationTask.DUMMY){
					queue.put(file);
					done = true;
				}else{
					search(file);
				}
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void search(File file) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		while(in.hasNextLine()){
			lineNumber++;
			String line = in.nextLine();
			if(line.contains(keyword))
				System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
		}
	}
	
}
