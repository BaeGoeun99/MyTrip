package com.mytrip.main.controller;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.mytrip.diner.controller.DinerController;
import com.mytrip.diner.dao.DinerDAO;
import com.mytrip.diner.service.DinerDeleteService;
import com.mytrip.diner.service.DinerFavCancelService;
import com.mytrip.diner.service.DinerFavService;
import com.mytrip.diner.service.DinerLikeCancelService;
import com.mytrip.diner.service.DinerLikeService;
import com.mytrip.diner.service.DinerListService;
import com.mytrip.diner.service.DinerUpdateService;
import com.mytrip.diner.service.DinerViewService;
import com.mytrip.diner.service.DinerWriteService;
import com.mytrip.festa.controller.FestaController;
import com.mytrip.festa.dao.FestaDAO;
import com.mytrip.festa.service.FestaDeleteService;
import com.mytrip.festa.service.FestaLikeCencelService;
import com.mytrip.festa.service.FestaLikeService;
import com.mytrip.festa.service.FestaListService;
import com.mytrip.festa.service.FestaRepDeleteService;
import com.mytrip.festa.service.FestaRepListService;
import com.mytrip.festa.service.FestaRepUpdateService;
import com.mytrip.festa.service.FestaRepWriteService;
import com.mytrip.festa.service.FestaUpdatePhotoService;
import com.mytrip.festa.service.FestaUpdateService;
import com.mytrip.festa.service.FestaViewService;
import com.mytrip.festa.service.FestaWriteService;
import com.mytrip.member.controller.MemberController;
import com.mytrip.member.dao.LoginDAO;
import com.mytrip.member.dao.MemberDAO;
import com.mytrip.member.service.LoginService;
import com.mytrip.member.service.MemberAdminDeleteService;
import com.mytrip.member.service.MemberDeleteService;
import com.mytrip.member.service.MemberIDCheckService;
import com.mytrip.member.service.MemberListService;
import com.mytrip.member.service.MemberMyService;
import com.mytrip.member.service.MemberUpdateService;
import com.mytrip.member.service.MemberViewService;
import com.mytrip.member.service.MemberWriteService;
import com.mytrip.notice.controller.NoticeController;
import com.mytrip.notice.dao.NoticeDAO;
import com.mytrip.notice.service.NoticeDeleteService;
import com.mytrip.notice.service.NoticeListService;
import com.mytrip.notice.service.NoticeUpdateService;
import com.mytrip.notice.service.NoticeViewService;
import com.mytrip.notice.service.NoticeWriteService;
import com.mytrip.tourist.controller.TouristController;
import com.mytrip.tourist.dao.TouristDAO;
import com.mytrip.tourist.service.TouristListService;
import com.mytrip.util.bean.Beans;

/**
 * Servlet implementation class Init
 */

// @WebServlet() -> 웹정보를 설정 value = 접근 url, loadOnStartup = 서버의 시작과 함께 로드하는 순서 번호
//@WebServlet(value = "/Init", loadOnStartup = 1) -> web.xml
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 * 서버가 실행이 될 때 초기화 시키는 작업 메서드
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			// DBInfo 확인 - 드라이버 확인 한다.
			Class.forName("com.mytrip.util.db.DBInfo");
			// 객체를 생성해서 저장해 놓는다. -Beans 객체 - controllerMap, serviceMap, daoMap 만들어서 저장
			newAndSave();
			// 서로 연관되어 있는 객체를 넣어준다. (DI - Dependency Inject : 의존성 주입)
			inject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("드라이버가 존재하지 않습니다.");
		}
	}
	
	// 객체를 생성해서 저장하는 메서드
	private void newAndSave() {
		// Beans 객체가 필요하다.
		// Controller 생성 저장
		Beans.put("/main", new MainController());
		Beans.put("/diner", new DinerController());
		Beans.put("/member", new MemberController());
		Beans.put("/notice", new NoticeController());
		Beans.put("/festa", new FestaController());		
		Beans.put("/tourist", new TouristController());
		
		// 관광지
		Beans.put("touristDAO", new TouristDAO());
		Beans.put("/tourist/list.do", new TouristListService());		
		
		// 맛집 서비스 및 DAO
		Beans.put("dinerDAO", new DinerDAO());
		Beans.put("/diner/list.do", new DinerListService());
		Beans.put("/diner/view.do", new DinerViewService());
		Beans.put("/diner/write.do", new DinerWriteService());
		Beans.put("/diner/update.do", new DinerUpdateService());
		Beans.put("/diner/updateForm.do", Beans.getService("/diner/view.do"));
		Beans.put("/diner/delete.do", new DinerDeleteService());
		Beans.put("/diner/like.do", new DinerLikeService());
		Beans.put("/diner/likeCancel.do", new DinerLikeCancelService());
		Beans.put("/diner/fav.do", new DinerFavService());
		Beans.put("/diner/favCancel.do", new DinerFavCancelService());
		
		// 회원 서비스 및 DAO		
		Beans.put("memberDAO", new MemberDAO());
		Beans.put("loginDAO", new LoginDAO());
		Beans.put("/member/login.do", new LoginService());
		Beans.put("/member/list.do", new MemberListService());
		Beans.put("/member/write.do", new MemberWriteService());
		Beans.put("/member/idCheck.do", new MemberIDCheckService());
		Beans.put("/member/view.do", new MemberViewService());
		Beans.put("/member/myPage.do", new MemberMyService());
		Beans.put("/member/delete.do", new MemberDeleteService());
		Beans.put("/member/Admindelete.do", new MemberAdminDeleteService());
		Beans.put("/member/updateForm.do", Beans.getService("/member/myPage.do"));
		Beans.put("/member/update.do", new MemberUpdateService());
		
		/* festa */
		// Service 생성 저장 - URL > 실행 서비스 선택
		Beans.put("festaDAO", new FestaDAO());
		Beans.put("/festa/list.do", new FestaListService());
		Beans.put("/festa/view.do", new FestaViewService());
		Beans.put("/festa/write.do", new FestaWriteService());
		Beans.put("/festa/updateForm.do", Beans.getService("/festa/view.do"));
		Beans.put("/festa/update.do", new FestaUpdateService());
		Beans.put("/festa/updatePhoto.do", new FestaUpdatePhotoService());
		Beans.put("/festa/delete.do", new FestaDeleteService());
		Beans.put("/festa/like.do", new FestaLikeService());
		Beans.put("/festa/likeCancel.do", new FestaLikeCencelService());
		Beans.put("/festa/replist.do", new FestaRepListService());
		Beans.put("/festa/repwrite.do", new FestaRepWriteService());
		Beans.put("/festa/repupdate.do", new FestaRepUpdateService());
		Beans.put("/festa/repdelete.do", new FestaRepDeleteService());
		
		//공지
		Beans.put("noticeDAO", new NoticeDAO());
		Beans.put("/notice/list.do", new NoticeListService());
		Beans.put("/notice/view.do", new NoticeViewService());
		Beans.put("/notice/write.do", new NoticeWriteService());
		Beans.put("/notice/updateForm.do", Beans.getService("/notice/view.do"));
		Beans.put("/notice/update.do", new NoticeUpdateService());
		Beans.put("/notice/delete.do", new NoticeDeleteService());
		
	}
	
	// 필요한 객체 넣어주기 : service -> controller, dao -> sevice
	public void inject() {
		// 관광지
		Beans.getService("/tourist/list.do").setDao(Beans.getDAO("touristDAO"));	
		
		// 맛집
		Beans.getService("/diner/list.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/view.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/write.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/update.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/delete.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/like.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/likeCancel.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/fav.do").setDao(Beans.getDAO("dinerDAO"));
		Beans.getService("/diner/favCancel.do").setDao(Beans.getDAO("dinerDAO"));
		
		// 회원관리 - 로그인 
		Beans.getService("/member/login.do").setDao(Beans.getDAO("loginDAO"));

		Beans.getService("/member/list.do").setDao(Beans.getDAO("memberDAO"));
		Beans.getService("/member/write.do").setDao(Beans.getDAO("memberDAO"));
		Beans.getService("/member/idCheck.do").setDao(Beans.getDAO("memberDAO"));
		Beans.getService("/member/view.do").setDao(Beans.getDAO("memberDAO"));
		Beans.getService("/member/myPage.do").setDao(Beans.getDAO("memberDAO"));
		Beans.getService("/member/delete.do").setDao(Beans.getDAO("memberDAO"));
		Beans.getService("/member/Admindelete.do").setDao(Beans.getDAO("memberDAO"));
		Beans.getService("/member/update.do").setDao(Beans.getDAO("memberDAO"));
		
		/* 축제 게시판 조립 */
		Beans.getService("/festa/list.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/view.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/write.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/update.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/updatePhoto.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/delete.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/like.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/likeCancel.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/replist.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/repwrite.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/repupdate.do").setDao(Beans.getDAO("festaDAO"));
		Beans.getService("/festa/repdelete.do").setDao(Beans.getDAO("festaDAO"));
		
		// 공지사항
		Beans.getService("/notice/list.do").setDao(Beans.getDAO("noticeDAO"));
		Beans.getService("/notice/view.do").setDao(Beans.getDAO("noticeDAO"));
		Beans.getService("/notice/write.do").setDao(Beans.getDAO("noticeDAO"));
		Beans.getService("/notice/update.do").setDao(Beans.getDAO("noticeDAO"));
		Beans.getService("/notice/delete.do").setDao(Beans.getDAO("noticeDAO"));
	}

}

