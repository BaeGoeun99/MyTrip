package com.mytrip.diner.service;

import com.mytrip.diner.dao.DinerDAO;
import com.mytrip.diner.vo.DinerVO;
import com.mytrip.main.controller.Service;

public class DinerFavService implements Service {
	
	// DAO를 연결하여 실행하기 위해 생성된 객체를 미리 넣어주고(setter or 생성자) 메소드 호출		
	private DinerDAO dao;
	
	// dao를 넣어주는 setter() - 서버가 실행이 되면 바로 넣어준다. -> Init.init()
	public void setDao(Object dao) {
		this.dao = (DinerDAO) dao;
	}
	
	@Override
	public Integer service(Object vo) throws Exception {
		
		return dao.fav((DinerVO) vo);
	}
}
