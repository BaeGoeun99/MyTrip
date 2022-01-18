package com.mytrip.diner.service;

import java.util.List;
import com.mytrip.diner.dao.DinerDAO;
import com.mytrip.diner.vo.DinerVO;
import com.mytrip.main.controller.Service;
import com.mytrip.util.pageobject.PageObject;

public class DinerListService implements Service {
	
	private DinerDAO dao;

	public void setDao(Object dao) {
		this.dao = (DinerDAO) dao;
	}
	
	@Override
	public List<DinerVO> service(Object obj) throws Exception  {

		PageObject pageObject = (PageObject) obj;
		System.out.println(dao);
		long cnt = dao.getTotalRow(pageObject);
		pageObject.setTotalRow(cnt);
		return dao.list(pageObject);
	}


}
