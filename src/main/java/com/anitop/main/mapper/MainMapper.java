package com.anitop.main.mapper;

import java.util.List;
import com.anitop.main.vo.MainVO;
import com.webjjang.util.PageObject;

/** @author 배고은 **/

public interface MainMapper {
	
	// 리스트
	public List<MainVO> list(PageObject pageObject);

	// 전체 데이터 개수
	public Long getTotalRow(PageObject pageObject);
	
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
