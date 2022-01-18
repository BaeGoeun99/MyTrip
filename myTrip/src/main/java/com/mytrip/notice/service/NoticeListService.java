package com.mytrip.notice.service;

import java.util.List;

import com.webjjang.util.PageObject;
import com.mytrip.notice.dao.NoticeDAO;
import com.mytrip.notice.vo.NoticeVO;
import com.mytrip.main.controller.Service;

public class NoticeListService implements Service{

	private NoticeDAO dao;
	
	public void setDao(Object dao) {
		this.dao = (NoticeDAO) dao;
	}



	@Override
	public List<NoticeVO> service(Object obj) throws Exception{
		
		PageObject pageObject = (PageObject) obj;
		
		long cnt = dao.getTotalRow(pageObject);
		
		pageObject.setTotalRow(cnt);
		
		return  dao.list(pageObject);
	}
	
}
