package com.mytrip.festa.service;

import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.vo.FestaRepVO;
import com.mytrip.main.controller.Service;
/**축제 리뷰 수정 서비스 @author GOEUN**/

public class FestaRepUpdateService implements Service {
	
	private FestaDAO dao;
	
	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
	}	

	@Override
	public Integer service(Object vo) throws Exception {
		
		return dao.repupdate((FestaRepVO) vo);
	}
	
}
