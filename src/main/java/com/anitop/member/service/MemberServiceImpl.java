package com.anitop.member.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.anitop.member.mapper.MemberMapper;
import com.anitop.member.vo.LoginVO;
import com.anitop.member.vo.MemberVO;
import com.webjjang.util.PageObject;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
@Service
@Qualifier("mes")
public class MemberServiceImpl implements MemberService {

	// 자동 DI 적용
	@Inject
	private MemberMapper mapper;
	
	@Override
	public LoginVO login(LoginVO vo) {
		return mapper.login(vo);
	}

	@Override
	public int write(MemberVO vo) {
		return mapper.write(vo);
	}

	@Override
	public List<MemberVO> list(PageObject pageObject) {
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("MemberServiceImpl.list()" + pageObject);
		return mapper.list(pageObject);
	}

	@Override
	public MemberVO view(MemberVO vo) {
		return mapper.view(vo);
	}

	@Override
	public int update(MemberVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(MemberVO vo) {
		return mapper.delete(vo);
	}

	@Override
	public int statusUpdate(MemberVO vo) {
		return mapper.statusUpdate(vo);
	}

	@Override
	public int updatePhoto(MemberVO vo) {
		return mapper.updatePhoto(vo);
	}

}
