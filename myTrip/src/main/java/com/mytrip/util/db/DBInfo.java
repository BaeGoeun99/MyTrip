package com.mytrip.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInfo {
	
	//DB 정보 - 다른 클래스에서는 못가져 가도록 정의한다.
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String UID = "my2";
	private static final String UPW = "my2";
	
	// 드라이버 확인 변수
	private static boolean driverCheck = false; // 드라이버가 없음으로 해 놓는다.
	
	// 처음에 딱한번 실행되는 부분에다가 드라이버 확인을 시킨다. -> static 초기화 블록 -> main()에 처음에 적용시키나.
	// Class.forName("com.webjjang.util.db.DBInfo");
	static {
		System.out.println("DBInfo static 초기화 블록 실행 - 드라이버 확인");
		try {
			Class.forName(DRIVER);
			// 드라이버가 있음으로 변경해 준다.
			driverCheck = true;
			System.out.println("드라이버 확인 완료");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("드라이버가 존재하지 않음.");
//			System.out.println("프로그램을 종료합니다.-----------");
//			System.exit(0);
		}
	}
	
	// Connection을 가져 가는 메서드 만들기 - 다른 패키지에서 사용이 가능하도록 한다.
	// 사용 방법 - DBInfo.getConnection()
	public static Connection getConnection() throws Exception {
		// 드라이버가 있는지 확인.
		if(!driverCheck) throw new Exception("드라이버가 존재하지 않습니다.");
		// 연결객체 가져가기 처리
		try {
			return DriverManager.getConnection(URL, UID, UPW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("DB 연결 실패 - " + e.getMessage());
		}
	}

	// 사용객체를 닫는 메서드 만들기 - DBInfo.close(con, pstmt)
	public static void close(Connection con, PreparedStatement pstmt) {
		try {
			// 7. 닫기 - select -> con, pstmt, rs을 사용한다.
			if(con !=null) con.close();
			if(pstmt !=null) pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 사용객체를 닫는 메서드 만들기 - DBInfo.close(con, pstmt, rs) -> Overload했다.
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			// 7. 닫기 - select -> con, pstmt, rs을 사용한다.
			close(con, pstmt); // con과 pstmt는 위에서 닫고 오라고 시킨다. -> 호출해서 해결
			if(rs !=null) rs.close(); // 남아 있는 rs만 닫는다.
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
}
