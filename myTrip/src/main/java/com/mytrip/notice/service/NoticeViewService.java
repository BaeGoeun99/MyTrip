package com.mytrip.notice.service;


import com.mytrip.notice.dao.NoticeDAO;
import com.mytrip.notice.vo.NoticeVO;
import com.mytrip.main.controller.Service;

public class NoticeViewService implements Service{

	private NoticeDAO dao;
	
	public void setDao(Object dao) {
		this.dao = (NoticeDAO) dao;
	}



	@Override
	public NoticeVO service(Object obj) throws Exception{
		
		Object[] objs = (Object[]) obj; 
		Long no = (Long)objs[0];
		int inc = (Integer)objs[1];
		
		System.out.println("NoticeViewService.service().dao : " + dao);
		
		if(inc == 1) dao.increase((long) no);
		
		return  dao.view((long) no);
	}
	
}
