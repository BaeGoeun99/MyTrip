package com.mytrip.festa.service;

import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.main.controller.Service;
import com.webjjang.util.PageObject;
/**축제 리뷰 리스트 서비스 @author GOEUN**/

public class FestaRepListService implements Service {
	
	private FestaDAO dao;

	@Override
	public Object service(Object obj) throws Exception {
		
		Object[] objs = (Object[]) obj;		
		Long no = (Long) objs[0];		
		PageObject pageObject = (PageObject) objs[1];
		
		long cnt = dao.getRepTotalRow(no);
		
		pageObject.setTotalRow(cnt);
		
		return dao.replist(no, pageObject);
	}

	@Override
	public void setDao(Object dao) {
		this.dao = (FestaDAO) dao;
	}
	
//	private FestaRepDAO dao;
	
//	@Override
//	public List<FestaRepVO> service(Object obj) throws Exception {
//		
//		PageObject pageObject = (PageObject) obj;
//		
//		long cnt = dao.getTotalRow(pageObject);
//		
//		pageObject.setTotalRow(cnt);
//		
//		return dao.list(pageObject);
//	}
//	
//	@Override
//	public void setDao(Object dao) {
//		this.dao = (FestaRepDAO) dao;
//	}
	
}
