package com.anitop.main.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.anitop.main.vo.MainVO;
import com.webjjang.util.PageObject;

/** @author 배고은 **/

@Service
public interface MainService {
	
	// 리스트
	public List<MainVO> list(PageObject pageObject);
	
	// 상세보기
	public MainVO view(Long no);
	
	// 등록
	public int write(MainVO vo);
	
	// 수정
	public int update(MainVO vo);
	
	// 사진 단독 수정
	public int updateImage(MainVO vo);
	
	// 삭제
	public int delete(MainVO vo);
}
