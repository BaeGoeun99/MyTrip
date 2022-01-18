package com.mytrip.member.service;

import java.util.List;

import com.mytrip.main.controller.Service;
import com.mytrip.member.dao.MemberDAO;
import com.mytrip.member.vo.MemberVO;
import com.webjjang.util.PageObject;

public class MemberListService implements Service {

	private MemberDAO dao;
	
//	 나중에 DAO부분이랑 수정해서 쓸것
	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		this.dao=(MemberDAO) dao;
		
	}
	
	@Override
	public List<MemberVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObject pageObject = (PageObject) obj;
		
//		System.out.println(dao);
		long cnt = dao.getTotalRow(pageObject);
		
		pageObject.setTotalRow(cnt);
		
		
		return dao.list(pageObject);
	}
	
//	// pageObject 미처리
//	@Override
//	public Object service(Object obj) throws Exception {
//		// TODO Auto-generated method stub
//		
//		
//		return new MemberDAO().list();
//	}


}
