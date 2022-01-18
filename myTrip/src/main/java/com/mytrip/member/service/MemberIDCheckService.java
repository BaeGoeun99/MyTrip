package com.mytrip.member.service;

import com.mytrip.main.controller.Service;
import com.mytrip.member.dao.MemberDAO;

public class MemberIDCheckService implements Service {

	private MemberDAO dao;
	
	// 넘어오는 데이터 - id - String
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.idCheck((String) obj);
	}

	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		this.dao=(MemberDAO) dao;
		
	}

}
