package com.designpattern.dl.prototype;

public class Mail implements Cloneable{
	private String receiver;
	private String subject;
	private String application;
	private String context;
	private String tail;
	
	public Mail(AdvTemplete templete){
		this.subject=templete.getAdvSubject();
		this.context=templete.getAdvContext();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTail() {
		return tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Mail mail = null;
		try{
			mail = (Mail) super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return mail;
	}
}
class AdvTemplete{
	private String advSubject ="XX银行国庆信用卡抽奖活动";
	private String advContext ="国庆抽奖活动：只要刷卡就送你一百万！";
	
	public String getAdvSubject() {
		return advSubject;
	}
	public String getAdvContext() {
		return advContext;
	}
	
}


