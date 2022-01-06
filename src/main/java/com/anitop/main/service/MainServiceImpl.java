package com.anitop.main.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.anitop.main.mapper.MainMapper;
import com.anitop.main.vo.MainVO;
import com.webjjang.util.PageObject;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
@Service
@Qualifier("ms")
public class MainServiceImpl implements MainService {
	
	@Inject
	private MainMapper mapper;
	
	// 리스트 및 전체 데이터 개수
	@Override
	public List<MainVO> list(PageObject pageObject) {
		// 전체 데이터 개수를 구한 후 pageObject로 넘김
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("MainServiceImpl.list() = "+pageObject);
		return mapper.list(pageObject);
	}
	// 상세보기
	@Override
	public MainVO view(Long no) {
		return mapper.view(no);
	}
	// 작성
	@Override
	public int write(MainVO vo) {
		return mapper.write(vo);
	}
	// 수정
	@Override
	public int update(MainVO vo) {
		return mapper.update(vo);
	}
	// 이미지 단독 수정
	@Override
	public int updateImage(MainVO vo) {
		return mapper.updateImage(vo);
	}
	// 삭제
	@Override
	public int delete(MainVO vo) {
		return mapper.delete(vo);
	}
	
	
	
}
