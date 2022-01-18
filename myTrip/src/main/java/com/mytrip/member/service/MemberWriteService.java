package com.mytrip.member.service;

import com.mytrip.main.controller.Service;
import com.mytrip.member.dao.MemberDAO;
import com.mytrip.member.vo.MemberVO;

public class MemberWriteService implements Service {

	private MemberDAO dao;
	
	@Override
	public Object service(Object vo) throws Exception {
		return new MemberDAO().write((MemberVO)vo);
	}

	@Override
	public void setDao(Object dao) {
		this.dao=(MemberDAO) dao;
	}

}
