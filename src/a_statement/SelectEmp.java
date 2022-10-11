package a_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectEmp {

	public static void main(String[] args) {

		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공2");

			// 2. 연결객체 얻어오기	
			String url1 = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user = "scott";
			String pass = "tiger";

			// 3. SQL 문장
			String sql = "SELECT empno, ename, sal, job FROM emp";

			// DB 연결
			Connection con = DriverManager.getConnection(url1,user,pass);
			System.out.println("디비 연결 성공2");

			// 4. SQL 전송객체
			Statement stmt = con.createStatement();

			// 5. SQL 전송
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int empno 			= rs.getInt("EMPNO");
				String ename 		= rs.getString("ENAME");
				int sal 			= rs.getInt("SAL");
				String job 			= rs.getString("JOB");
				System.out.println(empno+"/"+ename+"/"+sal+"/"+job);

			}
			System.out.println(rs+"행을 실행하였습니다.");

			// 6. 연결 종료
			stmt.close();
			con.close();


		} catch (Exception e) {
			// 로딩 실패시
			System.out.println("실패 하셨습니다."+e);

		}


	}

}
