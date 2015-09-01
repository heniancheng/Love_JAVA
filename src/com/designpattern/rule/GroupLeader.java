package com.designpattern.rule;

import java.util.List;

/*
 * 最小知识实例类
 */

public class GroupLeader {
	
	List<Girl> list = null;
	
	

	public GroupLeader(List<Girl> list) {
		this.list = list;
	}

	public int count() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
