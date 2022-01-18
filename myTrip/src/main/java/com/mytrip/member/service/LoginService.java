package com.mytrip.member.service;

import com.mytrip.main.controller.Service;
import com.mytrip.member.dao.LoginDAO;
import com.mytrip.member.vo.LoginVO;

public class LoginService implements Service {

	private LoginDAO dao;
	
	@Override
	public Object service(Object vo) throws Exception {
		// TODO Auto-generated method stub
		// DAO 에서 처리 - 미리 생성 조립 dao & 호출
		// 정상적인 처리가 되었다면 LoginVO 객체가 나온다. 그것을 리턴한다.
		return dao.login((LoginVO) vo);
	}

	@Override
	public void setDao(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (LoginDAO) dao;
	}

}
