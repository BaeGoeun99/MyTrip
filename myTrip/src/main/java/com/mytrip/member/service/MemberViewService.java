package com.mytrip.member.service;
/*
 * 게시판 글보기
 * 
 * public MemberVO service()
 */

import com.mytrip.main.controller.Service;
import com.mytrip.member.dao.MemberDAO;
import com.mytrip.member.vo.MemberVO;

public class MemberViewService implements Service{


	private MemberDAO dao;
	
	public void setDao(Object dao) {
		this.dao = (MemberDAO) dao;
	}


	@Override
	public Object service(Object obj) throws Exception{
		System.out.println("MemberViewService.service().dao : " + dao);
		
		
		return dao.view((MemberVO) obj);
	}
}
