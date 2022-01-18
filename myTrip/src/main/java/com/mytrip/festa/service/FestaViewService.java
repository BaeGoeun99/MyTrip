package com.mytrip.festa.service;

import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.vo.FestaVO;
import com.mytrip.main.controller.Service;
/**축제 보기 서비스 @author GOEUN**/

public class FestaViewService implements Service{
	
	private FestaDAO dao;
	
	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
		
	}

	@Override
	public FestaVO service(Object vo) throws Exception {
		
		return dao.view((FestaVO)vo);
	}
}
