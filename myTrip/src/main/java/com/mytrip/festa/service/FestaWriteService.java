package com.mytrip.festa.service;

import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.vo.FestaVO;
import com.mytrip.main.controller.Service;
/**축제 작성 서비스 @author GOEUN**/

public class FestaWriteService implements Service {
	
	private FestaDAO dao;
	
	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
		
	}

	@Override
	public Object service(Object vo) throws Exception {

		return dao.write((FestaVO)vo);
	}
	
}
