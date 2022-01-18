package com.mytrip.festa.service;

import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.vo.FestaVO;
import com.mytrip.main.controller.Service;
/**축제 수정 서비스 @author GOEUN**/

public class FestaUpdateService implements Service {
	
	private FestaDAO dao;
	
	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
	}	

	@Override
	public Integer service(Object vo) throws Exception {
		
		return dao.update((FestaVO) vo);
	}
	
}
