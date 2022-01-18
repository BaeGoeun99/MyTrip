package com.mytrip.festa.service;

import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.vo.FestaRepVO;
import com.mytrip.main.controller.Service;
/**축제 리뷰 작성 서비스 @author GOEUN**/

public class FestaRepWriteService implements Service {
	
	private FestaDAO dao;
	
	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
		
	}

	@Override
	public Object service(Object obj) throws Exception {
		
		return dao.repwrite((FestaRepVO)obj);
	}
	
}
