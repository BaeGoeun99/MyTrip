package com.mytrip.festa.service;

import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.vo.FestaVO;
import com.mytrip.main.controller.Service;
/**축제 삭제 서비스 @author GOEUN**/

public class FestaDeleteService implements Service {
	
	private FestaDAO dao;
	
	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
	}

	@Override
	public Integer service(Object vo) throws Exception {
		
		return dao.delete((FestaVO) vo);
	}

}
