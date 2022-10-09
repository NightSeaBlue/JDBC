package a_statement;

import java.sql.*;

public class InsertEmp2 {

	public static void main(String[] args) {
		// 라이브러리 인식 여부 확인

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공4");

			// 2. 연결객체 얻어오기
			//			String url1 = "jdbc:oracle:thin:@127.0.0.1:1521:xe";		// Oracle port 접속/ 자기 컴퓨터를 찾는 IP 127.0.0.1
			String url1 = "jdbc:oracle:thin:@192.168.0.52:1521:xe";
			//			String url1 = "jdbc:oracle:thin:@192.168.0.46:1521:xe";
			String user = "scott";
			String pass = "tiger";

			// DB 연결
			Connection con = DriverManager.getConnection(url1,user,pass);
			System.out.println("디비 연결 성공4");

			// ----- 입력값
			String bonmyeong = "이지효";
			int weolgub = 10000;
			String jikup = "IT";

			// 3. SQL 문장
			String sql = "INSERT INTO emp(empno,ename,sal,job)	"
					+ "	VALUES(seq_temp2.nextval,'"+bonmyeong+"','"+weolgub+"','"+jikup+"')";
			// JAVA에서 값을 입력한 것을 받을 경우 '"+입력값+"' 형태로 받아야 함.



			// 4. SQL 전송객체
			Statement stmt = con.createStatement();

			// 5. SQL 전송
			/*
			 *		- ResultSet executeQuery()		:		SELECT
			 *		- int executeUpdate()	:		INSERT/DELETE/UPDATE 
			 */
			int result = stmt.executeUpdate(sql);
			System.out.println(result+"행을 실행하였습니다.");

			// 6. 연결 종료
			stmt.close();
			con.close();



		} catch (Exception e) {
			System.out.println("DB 실패 : "+ e);
		}

	}

}
