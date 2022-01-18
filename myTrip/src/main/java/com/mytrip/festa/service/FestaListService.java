package com.mytrip.festa.service;

import java.util.List;
import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.vo.FestaVO;
import com.mytrip.main.controller.Service;
import com.webjjang.util.PageObject;
/**축제 리스트 서비스 @author GOEUN**/

public class FestaListService implements Service {
	
	private FestaDAO dao;

	@Override
	public List<FestaVO> service(Object obj) throws Exception {
		
		PageObject pageObject = (PageObject) obj;
		
		long cnt = dao.getTotalRow(pageObject);
		
		pageObject.setTotalRow(cnt);
		
		return dao.list(pageObject);
	}

	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
	}
	
}
