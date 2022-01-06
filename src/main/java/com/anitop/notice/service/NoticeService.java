package com.anitop.notice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.anitop.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

/** @author 배고은 **/

@Service
public interface NoticeService {
	
	// 리스트
	public List<NoticeVO> list(PageObject pageObject);
	
	// 상세보기
	public NoticeVO view(Long no);
	
	// 등록
	public int write(NoticeVO vo);
	
	// 수정
	public int update(NoticeVO vo);
	
	// 삭제
	public int delete(NoticeVO vo);
}
