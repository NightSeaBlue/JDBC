package c_info2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoModelImpl implements InfoModel {

	// 변수 명 앞에 final 이 붙는 경우 변수 명 대문자 선언 (필드에 드라이버,url,user,pw 미리 선언)
	// try catch 내부에 선언하던 걸 , 클래스에 선언해두고 바로 불러와서 사용
	// 변수 선언
	final static String DRIVER 	="oracle.jdbc.driver.OracleDriver";
	final static String URL 	= "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	final static String USER	="scott";
	final static String PASS	="tiger";


	/*
	 * 함수 명 : InfoModelImpl
	 * 인자 : 없음
	 * 리턴 값 : default
	 * 역할 : 정보를 입력할 수 있도록 해줌
	 * 
	 */
	public InfoModelImpl() throws ClassNotFoundException {
		// 1. 드라이버 로딩
		Class.forName(DRIVER);
		System.out.println("드라이버 로딩 성공");
	}	// end of InfoModel Implement

	/*
	 * 함수 명 : InsertInfo()
	 * 인자 : 없음
	 * 리턴값 : void
	 * 역할 : 사용자 입력값을 받아서 DB에 저장하는 역할 
	 */
	@Override
	public void InsertInfo(InfoVO vo) throws SQLException {
		// 2. 연결 객체 얻어오기 (try, catch 외부에서 지정)
		Connection con = null;
		PreparedStatement ps =  null;

		try {
			con = DriverManager.getConnection(URL,USER,PASS);
			System.out.println("디비 연결 성공");

			// 3. sql 문장
			String sql = "INSERT INTO info_tab(name,jumin,tel,gender,age,home)	"
					+ "	VALUES(?,?,?,?,?,?)";

			// 4. 전송객체
			ps = con.prepareStatement(sql);
			// ? 세팅 = #
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getTel());
			ps.setString(4, vo.getSex());
			ps.setInt(5, vo.getAge());
			ps.setString(6, vo.getHome());

			//5. 전송
			ps.executeUpdate();

		} finally {
			//6. 닫기
			ps.close();
			con.close();
		} // end of try~finally
	}// end of InsertInfo

	/*
	 * 	함수명 selectAll
	 * 	인자 : 없음
	 * 	리턴 값 : ArrayList<InfoVO>
	 *  전체 Info_tab의 레코드 검색
	 */

	@Override
	public ArrayList<InfoVO> selectAll() throws SQLException {

		// 2. 연결 객체 얻어오기 (try, catch 외부에서 지정)
		Connection con = null;
		PreparedStatement ps =  null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASS);

			//3. SQL 문장
			String sql = "SELECT * FROM info_tab";

			// 4. 전송객체 얻어오기
			ps = con.prepareStatement(sql);

			// 5. 전송
			rs = ps.executeQuery();

			ArrayList <InfoVO> list = new ArrayList <InfoVO> ();
			while (rs.next()) {
				InfoVO vo = new InfoVO ();
				vo.setName(rs.getString("NAME"));
				vo.setSex(rs.getString("GENDER"));
				vo.setId(rs.getString("JUMIN"));
				vo.setTel(rs.getString("TEL"));
				vo.setHome(rs.getString("HOME"));
				vo.setAge(rs.getInt("AGE"));

				list.add(vo);		// vo 에 지정해 받은 값을 list에 하나씩 저장하고자 함.
			} // end of While

			return list;

		} finally {
			//6. 닫기
			rs.close();
			ps.close();
			con.close();
		} // end of try~finally

	} // end of Select All


	/*
	 * 	함수명 : selectByTel()
	 * 	인자 : tel
	 * 	리턴값 : InfoVO
	 * 	역할 : 전화번호를 넘겨 받아서 해당하는 사람의 정보를 검색
	 */
	@Override
	public InfoVO selectByTel(String tel) throws SQLException {
		// 2. 연결 객체 얻어오기 (try, catch 외부에서 지정)
		Connection con = null;
		PreparedStatement ps =  null;
		InfoVO vo = new InfoVO();

		try {
			con = DriverManager.getConnection(URL,USER,PASS);
			//3. sql 문장
			String sql = "SELECT * FROM info_tab WHERE tel=?";
			//4. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setString(1, tel);

			//5. 전송
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setName(rs.getString("NAME"));
				vo.setAge(rs.getInt("AGE"));
				vo.setId(rs.getString("JUMIN"));
				vo.setSex(rs.getString("GENDER"));
				vo.setHome(rs.getString("HOME"));
				vo.setTel(rs.getString("TEL"));
			}

		} finally {
			//6. 닫기
			ps.close();
			con.close();
		} // end of try~finally

		return vo;	// InfoVO 타입으로 리턴
	}// end of Select By Tel


	/*
	 * 	함수명 : delete()
	 * 	인자 : 전화번호
	 * 	리턴값 : 삭제한 행 수
	 * 	역할 : 전화번호를 넘겨 받아 해당 전화번호의 레코드 삭제
	 */

	@Override
	public int delete(String tel) throws SQLException {
		// 2. 연결 객체 얻어오기 (try, catch 외부에서 지정)
		Connection con = null;
		PreparedStatement ps =  null;
		int ret = 0;
		try {
			con = DriverManager.getConnection(URL,USER,PASS);

			//3. sql 문장
			String sql = "DELETE FROM info_tab WHERE tel=?";

			// 4. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setString(1, tel);

			// 5. 전송
			ret = ps.executeUpdate();

		} finally {
			//6. 닫기
			ps.close();
			con.close();
		}//end of try~finally

		return ret;
	}// end of delete

	/*
	 * 	함수명 : edit()
	 * 	인자 : 전화번호
	 * 	리턴값 : 수정한 행 수
	 * 	역할 : 입력된 값으로 정보 수정
	 */

	@Override
	public int edit(InfoVO vo) throws SQLException {
		// 2. 연결 객체 얻어오기 (try, catch 외부에서 지정)
		Connection con = null;
		PreparedStatement ps =  null;
		int rs = 0;

		try {
			con = DriverManager.getConnection(URL,USER,PASS);

			// 3. sql 문장
			String sql = "UPDATE info_tab SET name=? , jumin=?, tel=?, gender=?, age=?, home=? WHERE tel=?";

			// 4. 전송객체 얻어오기
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getTel());
			ps.setString(4, vo.getSex());
			ps.setInt(5, vo.getAge());
			ps.setString(6, vo.getHome());
			ps.setString(7, vo.getTel());

			//5. 전송
			rs = ps.executeUpdate();

		} finally {
			ps.close();
			con.close();
		}

		return rs;

	}


}
