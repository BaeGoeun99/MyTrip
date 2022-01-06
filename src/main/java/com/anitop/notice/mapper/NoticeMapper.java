package com.anitop.notice.mapper;

import java.util.List;
import com.anitop.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

/** @author 배고은 **/

public interface NoticeMapper {
	
	// 리스트
	public List<NoticeVO> list(PageObject pageObject);

	// 전체 데이터 개수
	public Long getTotalRow(PageObject pageObject);
	
	// 상세보기
	public NoticeVO view(Long no);

	// 등록
	public int write(NoticeVO vo);

	// 수정
	public int update(NoticeVO vo);

	// 삭제
	public int delete(NoticeVO vo);
	
}
