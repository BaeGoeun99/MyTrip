package com.mytrip.util.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 파일 유틸리티 - FileUtil<br>
이미지 올릴 폴더나 파일 있는지 확인 메서드<br>
boolean exist(폴더나 파일의 절대경로)<br>
boolean exist(File객체)<br>
폴더가 없는 경우 폴더는 만드는 메서드<br>
boolean makes(폴더의 절대경로)<br>
파일이 존재하는 경우 파일을 지우는 메서드<br>
boolean deleteFile(지울 파일명, request)<br>
파일 업로드하는 메서드<br>
MultipartRequest upload(모듈명, request)<br>
 * @author EZEN
 *
 */

public class FileUtil {
	
	private static String path; 

	// 파일이나 폴더가 존재하는 여부를 알려 주는 메서드 - 절대 주소를 파라메터로 받는다.
	public static boolean exist(String fileName) {
//		return new File(fileName).exists();
		return exist(new File(fileName));
	}
	// 파일이나 폴더가 존재하는 여부를 알려 주는 메서드 - 절대 주소를 파라메터로 받는다.
	public static boolean exist(File file) {
		return file.exists();
	}
	
	// 폴더를 만드는 메서드
	public static boolean makes(String savePath) throws Exception{
		return makes(new File(savePath));
	}
	// 폴더를 만드는 메서드 - 실제적으로 만드는 메서드
	public static boolean makes(File file) throws Exception{
		// File - mkdir() - 절대 위치의 해당되는 마지막 폴더가 없는 경우만 만든다. 중간에 것이 없으면 오류가 난다. 
		// File - mkdirs() - 절대 위치의 해당되는 폴더가 없는 경우 중간에 해당되는 폴더가 없어도 모두 만든다.
		return file.mkdirs();
	}
	
	// 파일을 서버로 업로드 시키는 메서드
	public static MultipartRequest upload(String module, HttpServletRequest request)
			throws Exception{
		String basicDir = "/upload/";
		// 전역 변수 path를 사용한다.
		path = basicDir + module;
		String savePath = request.getServletContext().getRealPath(path);
		
		// 저장 위치에 해당되는 savePath가 없으면 만든다.
		if(!exist(savePath)) makes(savePath);
		
		// 그외 upload에 필요한 정보
		int maxSize = 1024 * 1024 * 1024; // 1G 이하
		String encodingType = "utf-8";

		return new MultipartRequest(request, savePath, maxSize, encodingType, 
				new DefaultFileRenamePolicy());
	}
	
	// path를 가져가는 메서드
	public static String getPath() {
		return path + "/";
	}
	
	// 파일을 삭제하는 메서드
	public static boolean delete(String fileName, HttpServletRequest request) throws Exception{
		// 넘겨 받는 fileName을 서버에서 운영되는 상대 주소이다.
		// savePath : 실제 서버의 하드디스크의 드라이버://폴더...// 파일명 -> 실제위치에 있는 것을 찾는다.
		String savePath = getRealPath(fileName, request);
		if (!exist(savePath)) return false;
		return getFile(savePath).delete();
	}
	
	// 문자열을 받아서 File 객체로 만들어 주는 메서드
	public static File getFile(String fileName) {
		return new File(fileName);
	}
	
	// 서버의 위치와 파일 정보를 받아서 절대 위치와 파일 정보를 되돌려 주는 메서드
	public static String getRealPath(String path, HttpServletRequest request) {
		return request.getServletContext().getRealPath(path);
	}
	
}
