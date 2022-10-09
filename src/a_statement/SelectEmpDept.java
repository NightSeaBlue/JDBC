package a_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectEmpDept {

	public static void main(String[] args) {

		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공3");

			// 2. 연결객체 얻어오기	
			String url1 = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user = "scott";
			String pass = "tiger";

			// 3. SQL 문장
			//		-> 20번 부서의 사원들의 정보 추출 : 사번, 사원명, 월급, 부서명, 근무지
			String sql = "SELECT e.empno as empno ,e.ename as ename,e.sal as sal,d.dname as dname,d.loc as dloc 	"
					+ "	FROM emp e LEFT OUTER JOIN dept d "
					+ "	ON e.deptno=d.deptno 	"
					+ "	WHERE e.deptno = 20";

			// DB 연결
			Connection con = DriverManager.getConnection(url1,user,pass);
			System.out.println("디비 연결 성공3");

			// 4. SQL 전송객체
			Statement stmt = con.createStatement();

			// 5. SQL 전송
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int empno 			= rs.getInt("empno");
				String ename 		= rs.getString("ename");
				int sal 			= rs.getInt("sal");
				String dname		= rs.getString("dname");
				String dloc			= rs.getString("dloc");
				System.out.println(empno+"/"+ename+"/"+sal+"/"+dname+"/"+dloc);

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
