package com.anitop.notice.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.anitop.notice.mapper.NoticeMapper;
import com.anitop.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
@Service
@Qualifier("ns")
public class NoticeServiceImpl implements NoticeService {
	
	@Inject
	private NoticeMapper mapper;
	
	@Override
	public List<NoticeVO> list(PageObject pageObject) {
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("NoticeServiceImpl.list() = "+pageObject);
		return mapper.list(pageObject);
	}

	@Override
	public NoticeVO view(Long no) {
		return mapper.view(no);
	}

	@Override
	public int write(NoticeVO vo) {
		return mapper.write(vo);
	}
	
	@Override
	public int update(NoticeVO vo) {
		return mapper.update(vo);
	}
	
	@Override
	public int delete(NoticeVO vo) {
		return mapper.delete(vo);
	}
	
}
