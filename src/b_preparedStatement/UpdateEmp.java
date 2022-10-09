package b_preparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateEmp {
	public static void main(String[] args) {

		// 입력값
		int sabun = 7698;
		String saname = "무야호";
		int salary = 15000;

		// 7698 사원의 이름과 월급을 변경하세요

		try {

			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");

			// 2. 연결객체 얻어오기
			String url1 = "jdbc:oracle:thin:@192.168.0.52:1521:xe";
			String user = "scott";
			String pass = "tiger";
			
			// DB 연결
						Connection con = DriverManager.getConnection(url1,user,pass);
						System.out.println("디비 연결 성공");

			// 3. SQL 문장
			String sql = "UPDATE emp SET ename=? , sal=?	"
					+ "	WHERE empno = ? ";
			
			
			// 4. SQL 전송객체
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, saname);
			stmt.setInt(2,salary);
			stmt.setInt(3, sabun);
			
			// 5. SQL 전송
			int result = stmt.executeUpdate();		//*********************** - sql을  여기 넣으면 안됨.  // 이미 sql이 위에서 연결되어 있음.
			System.out.println(result+"행을 실행하였습니다.");

			// 6. 연결 종료
			stmt.close();
			con.close();

		} catch (Exception e) {
			// 에러일 때
			System.out.println("에러 발생"+e);
		}


	}

}
