package com.mytrip.tourist.service;

import java.util.List;

import com.mytrip.main.controller.Service;
import com.mytrip.tourist.dao.TouristDAO;
import com.mytrip.tourist.vo.TouristVO;
import com.webjjang.util.PageObject;

public class TouristListService implements Service{

	//DAO를 연결하여 실행하기 위해서 생성된 객체를 미리 넣어주고(setter or 생성자) 메서드 호출한다.
	// dao를 생성해서 넣어 주는 프로그램을 Init.init()에서 처리하고 있다.
	// 객체 선언
	private TouristDAO dao;
	
	// dao를 넣어 주는 setter() - 서버가 실행이 되면 바로 넣어준다. -> Init.init()
	public void setDao(Object dao) {
		System.out.println("TouristListService.setDao().dao : " + dao);
		this.dao = (TouristDAO) dao;
	}



	@Override
	// 예외가 발생이 되면 Controller에 넘긴다.  throws Exception
	// 파라메터 obj가 없으면 null을 넣어주면 된다. 페이지 처리를 하면 PageObject가 넘어온다.
	public List<TouristVO> service(Object obj) throws Exception{
		
		// 페이지 정보 객체 받아내기
		PageObject pageObject = (PageObject) obj;
		
		// 전체 데이터 구하기 - 오라클에서 구해야한다. DAO . getTotalRow() - long
		// 조건을 적용시킨다. - pageObject에 정보가 담겨있다.
		long cnt = dao.getTotalRow(pageObject);
		// 전체 페이지 구하기 / 표시할 링크 페이지 시작과 끝 계산하기. -> 페이지 처리
		pageObject.setTotalRow(cnt);
		// 리스트 데이터 구하기 - 오라클에서 구해야 한다. DAO .list() - List<TouristVO>
//		list = dao.list();
		
//		return list;
		return  dao.list(pageObject);
	}
	
}
