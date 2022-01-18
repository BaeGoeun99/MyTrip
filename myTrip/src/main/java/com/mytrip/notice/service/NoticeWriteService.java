package com.mytrip.notice.service;


import com.mytrip.notice.dao.NoticeDAO;
import com.mytrip.notice.vo.NoticeVO;
import com.mytrip.main.controller.Service;

public class NoticeWriteService implements Service{

	private NoticeDAO dao;
	
	public void setDao(Object dao) {
		this.dao = (NoticeDAO) dao;
	}



	@Override
	public Integer service(Object vo) throws Exception{
		
		
		return  dao.write((NoticeVO) vo);
	}
	
}
