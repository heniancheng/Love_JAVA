package net.java.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class ClientII {
	
	private Socket cs =null;
	private PrintStream ps =null;
	private BufferedReader bf = null;
	private BufferedReader keyIn =null;

	public void initial() {
		// TODO Auto-generated method stub
		try{
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			cs = new Socket("127.0.0.1",30000);
			ps = new PrintStream(cs.getOutputStream());
			bf = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			Scanner input = new Scanner(System.in);
			while(true){
				System.out.println("input name: ");
				String usrName = null;
				while(input.hasNext()){
					usrName = input.next();
					break;
				}
				ps.println(Protocol.USER_ROUND + usrName + Protocol.USER_ROUND);
				String result = bf.readLine();
				if(result.equals(Protocol.NAME_REP)){
					System.out.println("repeat");
					continue;
				}
				if(result.equals(Protocol.LOGIN_SUCCESS)){
					break;
				}
				
			}		
		}catch(Exception ex){
			try{
				if(keyIn != null){
					keyIn.close();
				}
				if(bf != null){
					bf.close();
				}
				if(ps != null){
					ps.close();
				}
				if(cs != null){
					cs.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		new ClientThreadII(bf).start();
				
	}
	
	private void readAndSend(){
		try{
			String line = null;
			while((line = keyIn.readLine()) != null){
				if(line.indexOf(":") > 0 && line.startsWith("//")){
					line = line.substring(2);
					ps.println(Protocol.PRIVATE_ROUND + line.split(":")[0] + Protocol.SPLIT_SIGN + line.split(":")[1] + Protocol.PRIVATE_ROUND);
				}
				else{
					ps.println(Protocol.MSG_ROUND + line + Protocol.MSG_ROUND);
				}
			}
		}catch(Exception ex){
			try{
				if(keyIn != null){
					keyIn.close();
				}
				if(bf != null){
					bf.close();
				}
				if(ps != null){
					ps.close();
				}
				if(cs != null){
					cs.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ClientII cl = new ClientII();
		cl.initial();
		cl.readAndSend();
	}

}

class ClientThreadII extends Thread{
	BufferedReader bf = null;
	
	public ClientThreadII(BufferedReader bf){
		this.bf = bf;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			String line = null;
			while((line = bf.readLine()) != null){
				System.out.println(line);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(bf != null){
					bf.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
