package javacore.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	private String name;
	public Socket cs = null;
	
	private Client(String name){
		this.name = name;
		try {
			cs = new Socket("127.0.0.1",30000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		System.out.println("input a name: ");
		String name = input.next();
		
		Client cl = new Client(name);
		
		new Thread(new ClientThread(cl)).start();
		
		PrintStream ps = new PrintStream(cl.cs.getOutputStream());
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = bf.readLine())!=null){
			ps.println(line);
		}
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class ClientThread implements Runnable{
	
	Socket s = null;
	BufferedReader br = null;
	Client cl = null;
	
	
	public ClientThread(Client cl) throws IOException{
		this.cl = cl;
		this.s = cl.cs;
		br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			String content = null;
			while((content = br.readLine())!= null){
				System.out.println(cl.getName() + " : " + content);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
}