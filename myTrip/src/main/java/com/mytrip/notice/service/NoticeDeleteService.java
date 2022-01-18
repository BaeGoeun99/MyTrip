package com.mytrip.notice.service;


import com.mytrip.notice.dao.NoticeDAO;
import com.mytrip.main.controller.Service;

public class NoticeDeleteService implements Service{

	private NoticeDAO dao;
	
	public void setDao(Object dao) {
		this.dao = (NoticeDAO) dao;
	}



	@Override
	public Integer service(Object no) throws Exception{
		
		
		return  dao.delete((long) no);
	}
	
}
