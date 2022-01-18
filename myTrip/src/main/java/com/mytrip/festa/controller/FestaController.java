package com.mytrip.festa.controller;

import javax.servlet.http.HttpServletRequest;
import com.mytrip.festa.vo.FestaRepVO;
import com.mytrip.festa.vo.FestaVO;
import com.mytrip.main.controller.Controller;
import com.mytrip.main.controller.ExecuteService;
import com.mytrip.main.controller.Service;
import com.mytrip.member.vo.LoginVO;
import com.mytrip.util.bean.Beans;
import com.mytrip.util.file.FileUtil;
import com.oreilly.servlet.MultipartRequest;
import com.webjjang.util.PageObject;

/**축제 컨트롤러 @author GOEUN**/

public class FestaController implements Controller {
	
	private final String MODULE = "festa";
	
	Service service = null;
	
	private void setService(String url) {
		service = Beans.getService(url);
	}
	

	@Override
	public String execute(HttpServletRequest request) throws Exception {
	
//	HttpSession session = request.getSession();
		
	String deleteFile = null;
		
	String url = request.getServletPath();

	Object data = null;
	String key = "";
	String jsp = "";

	PageObject pageObject = null;
	
	setService(url);
	
	switch (url) {
// 리스트 ------------------------------------------
	case "/"+ MODULE +"/list.do":
		
		pageObject = PageObject.getInstance(request);
		
		String strPerPageNum = request.getParameter("perPageNum");
		if (strPerPageNum == null || strPerPageNum.equals(""))
			pageObject.setPerPageNum(8);
		
		data = pageObject;
		
		key = "list";
		
		jsp = MODULE +"/list";
		
	break;
	
// 상세보기 ------------------------------------------	
	case "/"+ MODULE +"/view.do":
		
		pageObject = PageObject.getInstance(request);
		
		String noStr = request.getParameter("no").trim();
		long no = Long.parseLong(noStr);
		String id = LoginVO.getId(request);
		
		FestaVO vo = new FestaVO();
		FestaRepVO rvo = new FestaRepVO();
		
		vo.setNo(no);
		vo.setId(id);
		rvo.getWriteDate();

//		request.setAttribute("replist", ExecuteService.execute(Beans.getService("/festa/replist.do"), pageObject));
		request.setAttribute("replist", ExecuteService.execute(Beans.getService("/festa/replist.do"), new Object[] {no, pageObject}));

		data = vo;
		
		key = "vo";		
		
		jsp = MODULE +"/view";
	
	break;
	
// 작성폼 ------------------------------------------		
	case "/"+ MODULE +"/writeForm.do":
		jsp = MODULE +"/writeForm";
	
	break;
	
// 작성처리 ------------------------------------------	
	case "/"+ MODULE +"/write.do":
		
		MultipartRequest multi = FileUtil.upload(MODULE, request);
	
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String fileName = multi.getFilesystemName("photo");
		String address = multi.getParameter("address");
		String rgn = multi.getParameter("rgn");
		String[] tel = multi.getParameterValues("tel");
		String website = multi.getParameter("website");
		String host = multi.getParameter("host");
		String startDate = multi.getParameter("startDate");
		String endDate = multi.getParameter("endDate");
		
		vo = new FestaVO();
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPhoto(FileUtil.getPath() + fileName);
		vo.setAddress(address);
		vo.setRgn(rgn);
		vo.setTel(String.join("-", tel));
		vo.setWebsite(website);
		vo.setHost(host);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		
		data = vo;
		
		jsp = "redirect:list.do";
	
	break;
	
// 수정폼 ------------------------------------------	
	case "/"+ MODULE +"/updateForm.do":
		
		noStr = request.getParameter("no");
		no = Long.parseLong(noStr);
		vo = new FestaVO();
		vo.setNo(no);
		
		data = vo;
		
		key = "vo";
		
		jsp = MODULE +"/updateForm";
	
	break;
	
// 수정처리 ------------------------------------------	
	case "/"+ MODULE +"/update.do":
		
		pageObject = PageObject.getInstance(request);
		
		noStr = request.getParameter("no");
		no = Long.parseLong(noStr);		
		title = request.getParameter("title");
		content = request.getParameter("content");
		address = request.getParameter("address");
		rgn = request.getParameter("rgn");
		tel = request.getParameterValues("tel");
		website = request.getParameter("website");
		host = request.getParameter("host");
		startDate = request.getParameter("startDate");
		endDate = request.getParameter("endDate");
		
		vo = new FestaVO();
		
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setAddress(address);
		vo.setRgn(rgn);
		vo.setTel(String.join("-", tel));
		vo.setWebsite(website);
		vo.setHost(host);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		
		data = vo;		
		
		jsp = "redirect:view.do?no=" + no
			+ "&page=" + request.getParameter("page")
			+ "&perPageNum=" + request.getParameter("perPageNum");
			
	break;
// 축제 이미지 단독 수정폼 ------------------------------------------	
	case "/"+ MODULE +"/updatePhoto.do":
		
		multi = FileUtil.upload(MODULE, request);	
		
		pageObject = new PageObject();
		
		String strPage = multi.getParameter("page");
		if(strPage != null && !strPage.equals(""))
			pageObject.setPage(Long.parseLong(strPage));
		
		strPerPageNum = multi.getParameter("perPageNum");
		
		if(strPerPageNum != null && !strPerPageNum.equals(""))
			pageObject.setPerPageNum(Long.parseLong(strPerPageNum));
		else pageObject.setPerPageNum(8);
		
		String strNo = multi.getParameter("no");
		no = Long.parseLong(strNo);

		fileName = multi.getFilesystemName("photo");
		vo = new FestaVO();
		vo.setNo(no);
		vo.setPhoto(FileUtil.getPath() + fileName);
		data = vo;
		
		deleteFile = multi.getParameter("del");
		
		jsp = "redirect:view.do?no=" + no
				+ "&page="+pageObject.getPage()
				+ "&perPageNum="+pageObject.getPerPageNum();
	
	break;	
// 삭제 ------------------------------------------	
	case "/"+ MODULE +"/delete.do":
		
		noStr = request.getParameter("no");
		no = Long.parseLong(noStr);
		
		vo = new FestaVO();
		vo.setNo(no);
		
		data = vo;
		
		deleteFile = request.getParameter("del");
		
		jsp = "redirect:list.do";
	
	break;
	
// 북마크 ------------------------------------------	
	case "/"+ MODULE +"/like.do":
		
		noStr = request.getParameter("no");
		no = Long.parseLong(noStr);
		
		vo = new FestaVO();
		vo.setNo(no);
		vo.setId(LoginVO.getId(request));
		
		data = vo;
		
		String query = request.getQueryString();
		
		jsp = "redirect:view.do?no=" + no;
//			+ "&page=" + request.getParameter("page")
//			+ "&perPageNum=" + request.getParameter("perPageNum");
	
	break;
	
// 북마크취소 ------------------------------------------	
	case "/"+ MODULE +"/likeCancel.do":
		
		noStr = request.getParameter("no");
		no = Long.parseLong(noStr);
		
		vo = new FestaVO();
		vo.setNo(no);
		vo.setId(LoginVO.getId(request));
		
		data = vo;
		
		query = request.getQueryString();		
		
		jsp = "redirect:view.do?no=" + no;
//			+ "&page=" + request.getParameter("page")
//			+ "&perPageNum=" + request.getParameter("perPageNum");
	
	break;
	
// 리뷰 등록 ------------------------------------------
	case "/"+ MODULE +"/repwrite.do":
		
		noStr = request.getParameter("no");
		no = Long.parseLong(noStr);
		content = request.getParameter("content");
		String writeDate = request.getParameter("writeDate");
		id = LoginVO.getId(request);
		
		rvo = new FestaRepVO();
		
		rvo.setNo(no);
		rvo.setContent(content);
		rvo.setWriteDate(writeDate);
		rvo.setId(LoginVO.getId(request));
		
//		request.setAttribute("repwrite", ExecuteService.execute(Beans.getService("/festa/repwrite.do"), rvo));
		
		data = rvo;
		
		key = "rvo";
		
		jsp = "redirect:view.do?no=" + request.getParameter("no");
		
	break;
// 리뷰 삭제 ------------------------------------------
	case "/"+ MODULE +"/repdelete.do":
		
		noStr = request.getParameter("no");
		no = Long.parseLong(noStr);
		String rnoStr = request.getParameter("rno");
		long rno = Long.parseLong(rnoStr);
		id = LoginVO.getId(request);

		rvo = new FestaRepVO();
		rvo.setNo(no);
		rvo.setRno(rno);
		rvo.setId(LoginVO.getId(request));
		
		data = rvo;
				
		jsp = "redirect:view.do?no=" + request.getParameter("no");
		
	break;
	
// 디폴트 : 끝나는 부분 ---------------------------------------------------
	default:
		System.out.println("Error 404::존재하지 않는 페이지를 요청했습니다.");
		throw new Exception("Error 404::존재하지 않는 페이지를 요청했습니다.");
	}
	if(service != null)
		request.setAttribute(key, ExecuteService.execute(service, data));
	
	request.setAttribute("pageObject", pageObject);
	
//	System.out.println("FestaController.execute().deleteFile - " + deleteFile);
	
	if(deleteFile != null) FileUtil.delete(deleteFile, request);

		return jsp;
	}
	
}
