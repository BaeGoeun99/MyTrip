package com.mytrip.diner.controller;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.mytrip.diner.vo.DinerVO;
import com.oreilly.servlet.MultipartRequest;
import com.mytrip.util.pageobject.PageObject;
import com.mytrip.main.controller.Controller;
import com.mytrip.main.controller.ExecuteService;
import com.mytrip.main.controller.Service;
import com.mytrip.member.vo.LoginVO;
import com.mytrip.util.bean.Beans;
import com.mytrip.util.file.FileUtil;

public class DinerController implements Controller {
	
	// 모듈 이름
	private final String MODULE = "diner";
	
	//실행에 필요한 service 객체 선언
	Service service = null;
	
	private void setService(String url) {
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		
		// 삭제해야할 파일
		String deleteFile = null;
//		String deleteFile2 = null; //**1. subImage도 지우기
//		System.out.println(getClass().getSimpleName() + ".execute()");
		request.setCharacterEncoding("UTF-8");
		// 처리 결과를 담기 위해 session을 request에서 꺼냄
		HttpSession session = request.getSession();
		
		// list, view, write, update, delete -> request에서 URL을 가져와서 구분
		String url = request.getServletPath();
		
		// Service에 전달을 해야하는 객체 선언
		Object data = null;
		
		// request에 담을 데이터의 key
		String key = "";
		
		// 보여줄 jsp나 이동할 페이지의 정보를 저장하는 변수 선언
		String jsp = "";
		
		// 페이지 처리를 위한 객체
		PageObject pageObject = null;
			
		
		// 실행 전 실행할 서비스 세팅
		setService(url);
		if(service != null) { System.out.println(service.getClass().getSimpleName()); }
		
		//CRUD에 해당되는 처리문 작성
		switch (url) {
		case "/" + MODULE + "/list.do":

			jsp = MODULE + "/list";

			key = "list";
			pageObject = PageObject.getInstance(request);			
			String strPerPageNum = request.getParameter("perPageNum");			
			if(strPerPageNum == null || strPerPageNum.equals("")) {	pageObject.setPerPageNum(8); }
			// area, kind 정보가 존재하면 페이지 오브젝트에 담아서 넘김
			int area = 0; 
			int kind = 0;
			String strArea = request.getParameter("area");
			String strKind = request.getParameter("kind");
			if(strArea != null && strArea != "") { area = Integer.parseInt(strArea); }
			if(strKind != null && strKind != "") { kind = Integer.parseInt(strKind); }
			if(area != 0) { pageObject.setArea(area); }
			if(kind != 0) { pageObject.setKind(kind); }
			
			data = pageObject;
			
			
			break;      
		case "/" + MODULE + "/view.do":
			pageObject = PageObject.getInstance(request);
					
			long no = Long.parseLong(request.getParameter("no"));
			DinerVO vo = new DinerVO();
			String id = LoginVO.getId(request);
			vo.setNo(no);
			vo.setId(id);
//			
			data = vo;
			// 데이터가 담길 key
			key = "vo";
			// DB에서 데이터를 가져오면 view.jsp를 이용해서 HTML을 만들도록 설정
			jsp = MODULE + "/view";
			
			break;
		case "/" + MODULE + "/writeForm.do":
			jsp = MODULE + "/writeForm";
			break;
		case "/" + MODULE + "/write.do":
			MultipartRequest multi = FileUtil.upload(MODULE, request);
			String name = multi.getParameter("name");
			area = Integer.parseInt(multi.getParameter("area")) ;
			kind = Integer.parseInt(multi.getParameter("kind")) ;
			String address = multi.getParameter("address");
			String tel = multi.getParameter("tel");
			String introduce = multi.getParameter("introduce");
			String mainImage = multi.getFilesystemName("mainImage");
			String content = multi.getParameter("content");			
			String tag = multi.getParameter("tag");
			String openTime = multi.getParameter("openTime");
			String restDay = multi.getParameter("restDay");
			String subImage = multi.getFilesystemName("subImage");
			
//			String subImage0 = multi.getFilesystemName("subImage[0]");
//			System.out.println(subImage0);
//			String subImage1 = multi.getFilesystemName("subImage[1]");
//			System.out.println(subImage1);
//			String subImage2 = multi.getFilesystemName("subImage[2]");
//			System.out.println(subImage2);
//			String subImage3 = multi.getFilesystemName("subImage[3]");
//			System.out.println(subImage3);
//			String subImage[] = new String[10];	
//
//			
//			for (int i = 0; i < 10; i++) {
//				subImage[i] = multi.getFilesystemName("subImage["+i+"]");				
//				System.out.println(subImage[i]);
//			}
//			
			
			vo = new DinerVO();
			vo.setName(name);
			vo.setArea(area);
			vo.setKind(kind);
			vo.setAddress(address);
			vo.setTel(tel);
			vo.setIntroduce(introduce);
			vo.setMainImage(FileUtil.getPath() + mainImage);
			vo.setContent(content);
			vo.setSubImage(FileUtil.getPath() + subImage);
			vo.setTag(tag);
			vo.setOpenTime(openTime);
			vo.setRestDay(restDay);
			System.out.println(vo.getSubImage());
			data = vo;
			jsp = "redirect:list.do";
		
			session.setAttribute("msg", "맛집 등록 완료.");
			
			break;
		case "/" + MODULE + "/updateForm.do":
			pageObject = PageObject.getInstance(request);			
			no = Long.parseLong(request.getParameter("no"));
			vo = new DinerVO();
			vo.setNo(no);
			data = vo;
			key = "vo";
			jsp = MODULE + "/updateForm";
			
			break;
		case "/"+ MODULE + "/update.do":
//			String test = request.getParameter("no");
//			System.out.println(test);			
			multi = FileUtil.upload(MODULE, request);
			no = Long.parseLong(multi.getParameter("no"));
			area = Integer.parseInt(multi.getParameter("area"));
			kind = Integer.parseInt(multi.getParameter("kind"));
			name = multi.getParameter("name");
			address = multi.getParameter("address");
			tel = multi.getParameter("tel");
			mainImage = multi.getFilesystemName("mainImage");
			introduce = multi.getParameter("introduce");
			content = multi.getParameter("content");
			subImage = multi.getFilesystemName("subImage");
			tag = multi.getParameter("tag");
			openTime = multi.getParameter("openTime");
			restDay = multi.getParameter("restDay");
			
			vo = new DinerVO();
			vo.setNo(no);
			vo.setArea(area);
			vo.setKind(kind);
			vo.setName(name);
			vo.setAddress(address);
			vo.setTel(tel);
			vo.setMainImage(FileUtil.getPath() + mainImage);
			vo.setIntroduce(introduce);
			vo.setContent(content);
			vo.setSubImage(FileUtil.getPath() + subImage);
			vo.setTag(tag);
			vo.setOpenTime(openTime);
			vo.setRestDay(restDay);
			data = vo;
			
			jsp = "redirect:view.do?no="+no
					+ "&page="+multi.getParameter("page")
					+ "&perPageNum="+multi.getParameter("perPageNum")
					+ "&key="+multi.getParameter("key")
					+ "&word=" + URLEncoder.encode(multi.getParameter("word"), "UTF-8");
			
			session.setAttribute("msg", "맛집 정보 수정 완료.");
			break;
		case "/" + MODULE + "/delete.do":
			
			no = Long.parseLong(request.getParameter("no"));
			
			vo = new DinerVO();
			vo.setNo(no);
			
			data = vo;			

			deleteFile = request.getParameter("del");
			
			jsp = "redirect:list.do";
			
			session.setAttribute("msg", "이미지 글 삭제가 완료되었습니다.");
			break;
			// 좋아요 처리
		case "/" + MODULE + "/like.do":
				
				no = Long.parseLong(request.getParameter("no"));
				
				vo = new DinerVO();
				vo.setNo(no);
				vo.setId(LoginVO.getId(request));
				
				data = vo;
				String query = request.getQueryString();
				jsp = "redirect:view.do?no=" + no 
						+ "&page=" + request.getParameter("page")
						+ "&perPageNum=" + request.getParameter("perPageNum")
						+ "&key=" + request.getParameter("key")
						+ "&word=" + URLEncoder.encode(request.getParameter("word"), "UTF-8")
						+ "&area=" + request.getParameter("area")
						+ "&kind=" + request.getParameter("kind");
				
				session.setAttribute("msg", "좋아요 처리가 완료되었습니다.");
				break;
			// 좋아요 취소 처리
		case "/" + MODULE + "/likeCancel.do":
				
				no = Long.parseLong(request.getParameter("no"));
				
				vo = new DinerVO();
				vo.setNo(no);
				vo.setId(LoginVO.getId(request));			
				data = vo;
				
				query = request.getQueryString();
				jsp = "redirect:view.do?no=" + no 
						+ "&page=" + request.getParameter("page")
						+ "&perPageNum=" + request.getParameter("perPageNum")
						+ "&key=" + request.getParameter("key")
						+ "&word=" + URLEncoder.encode(request.getParameter("word"), "UTF-8")
						+ "&area=" + request.getParameter("area")
						+ "&kind=" + request.getParameter("kind");
				
				session.setAttribute("msg", "좋아요 취소 처리가 완료되었습니다.");
				break;
		case "/" + MODULE + "/fav.do":
			
			no = Long.parseLong(request.getParameter("no"));
		
		vo = new DinerVO();
		vo.setNo(no);
		vo.setId(LoginVO.getId(request));
		
		data = vo;
		query = request.getQueryString();
		jsp = "redirect:view.do?no=" + no 
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word=" + URLEncoder.encode(request.getParameter("word"), "UTF-8")
				+ "&area=" + request.getParameter("area")
				+ "&kind=" + request.getParameter("kind");
		
		session.setAttribute("msg", "찜하기 처리가 완료되었습니다.");
		break;
		// 좋아요 취소 처리
		case "/" + MODULE + "/favCancel.do":
			
			no = Long.parseLong(request.getParameter("no"));
		
		vo = new DinerVO();
		vo.setNo(no);
		vo.setId(LoginVO.getId(request));			
		data = vo;
		
		query = request.getQueryString();
		jsp = "redirect:view.do?no=" + no 
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word=" + URLEncoder.encode(request.getParameter("word"), "UTF-8")
				+ "&area=" + request.getParameter("area")
				+ "&kind=" + request.getParameter("kind");
		
		session.setAttribute("msg", "찜하기 취소 처리가 완료되었습니다.");
		break;
			
		default:
			System.out.println("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
			throw new Exception("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
		}
		// service가 null이 아닌 경우에만 실행하게 해야한다. (서비스를 안쓰는 페이지도 있으니까)
		if(service != null) { request.setAttribute(key, ExecuteService.execute(service, data)); } 
		
		//페이지 정보를 request에 담아서 jsp에 전달
		request.setAttribute("pageObject", pageObject);
		
		// deleteFile != null 이면 삭제처리
		if(deleteFile != null) { FileUtil.delete(deleteFile, request); }
		
		return jsp;
	}

}
