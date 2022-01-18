package com.mytrip.tourist.service;

import com.mytrip.main.controller.Service;
import com.mytrip.tourist.dao.TouristDAO;
import com.mytrip.tourist.vo.TouristVO;

public class TouristUpdateService implements Service {


	//DAO를 연결하여 실행하기 위해서 생성된 객체를 미리 넣어주고(setter or 생성자) 메서드 호출한다.
	// 객체 선언
	private TouristDAO dao;
	
	// dao를 넣어 주는 setter() - 서버가 실행이 되면 바로 넣어준다. -> Init.init()
	public void setDao(Object dao) {
		this.dao = (TouristDAO) dao;
	}


	@Override
	public Integer service(Object vo) throws Exception {
		
		return dao.update((TouristVO) vo);
	}
	
}
