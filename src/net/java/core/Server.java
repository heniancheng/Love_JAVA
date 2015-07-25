package net.java.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Server {
	
	public static ArrayList<Socket> socketlist = new ArrayList<Socket>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(30000);
		while(true){
			Socket s = ss.accept();
			socketlist.add(s);
			new Thread(new ServerThread(s)).start();
		}
	}

}

class ServerThread implements Runnable{

	Socket s = null;
	BufferedReader br = null;
	
	public ServerThread(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			String content =null;
			while((content = readFromClient())!=null){
				for(Socket s:Server.socketlist){
					PrintStream ps = new PrintStream(s.getOutputStream());
					ps.println(content);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	private String readFromClient() {
		// TODO Auto-generated method stub
		try{
			return br.readLine();
		}catch(Exception ex){
			Server.socketlist.remove(s);
		}
		
		return null;
	}
	
}
