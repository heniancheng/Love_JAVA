package net.java.core;


import java.net.*;

public class BaseNet {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		InetAddress ip = InetAddress.getByName("www.baidu.com");
		System.out.println("www.baidu.com is reacheable: " + ip.isReachable(2000));
		System.out.println("the hostname: " + ip.getHostName());
		System.out.println("the address: " + ip.getHostAddress());
		
		//InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		InetAddress local = InetAddress.getByName("localhost");
		System.out.println("local is reacheable: " + local.isReachable(2000));
		System.out.println("local name is " + local.getCanonicalHostName());
		System.out.println("the hostname: " + local.getHostName());
		System.out.println("the address: " + local.getHostAddress());
		
		System.out.println("local name is " + InetAddress.getLocalHost().getCanonicalHostName());
		System.out.println("localname is " + InetAddress.getLocalHost().getHostName());
		System.out.println("the address: " + InetAddress.getLocalHost().getHostAddress());
		
		String encodeStr = URLEncoder.encode("这是要编码的字符串","GBK");
		System.out.println("编码后的字符串： " + encodeStr);
		
		String decodeStr = URLDecoder.decode(encodeStr,"GBK");
		System.out.println("解码后的字符串：" + decodeStr);
	}

}
