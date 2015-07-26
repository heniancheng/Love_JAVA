package net.java.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class ServerII {
	
	public static ClientMap<String, PrintStream> usermap = new ClientMap<String, PrintStream>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		try{
			ss = new ServerSocket(30000);
			while(true){
				Socket s = ss.accept();
				new ServerThreadII(s).start();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				if(ss !=null){
					ss.close();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}

class ServerThreadII extends Thread{
	Socket s =null;
	BufferedReader br = null;
	PrintStream ps = null;
	
	public ServerThreadII(Socket s){
		super();
		this.s = s;
		
	}
	
	public void run(){
		try{
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ps = new PrintStream(s.getOutputStream());
			String line = null;
			while((line = br.readLine())!=null){
				if(line.startsWith(Protocol.USER_ROUND) && line.endsWith(Protocol.USER_ROUND)){
					String userName = getRealMsg(line);
					if(ServerII.usermap.containsKey(userName)){
						System.out.println("repeat");
						ps.println(Protocol.NAME_REP);
					}else{
						System.out.println("success");
						ps.println(Protocol.LOGIN_SUCCESS);
						ServerII.usermap.put(userName, ps);
					}	
				}else if(line.startsWith(Protocol.PRIVATE_ROUND)&&line.endsWith(Protocol.PRIVATE_ROUND)){
					String userAndMsg = getRealMsg(line);
					String user = userAndMsg.split(Protocol.SPLIT_SIGN)[0];
					String msg = userAndMsg.split(Protocol.SPLIT_SIGN)[1];
					ServerII.usermap.get(user).println("i am " + ServerII.usermap.getKeyByValue(ps) + ", " + msg);
				}else if(line.startsWith(Protocol.MSG_ROUND) && line.endsWith(Protocol.MSG_ROUND)){
					String msg = getRealMsg(line);
					String user =  ServerII.usermap.getKeyByValue(ps);
					for(PrintStream ps:ServerII.usermap.values()){
						ps.println("i am " + user + ", " + msg);
					}
				}else{
					ps.println("input error!");
				}
			}
		}catch(Exception ex){
			ServerII.usermap.removeByValue(ps);
			System.out.println(ServerII.usermap.size());
			try{
				if(br != null){
					br.close();
				}
				if(ps != null){
					ps.close();
				}
				if(s != null){
					s.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	private String getRealMsg(String line) {
		// TODO Auto-generated method stub
		return line.substring(Protocol.PROTOCOL_LEN-1, line.length()-Protocol.PROTOCOL_LEN+1);
	}
}
