package c_info2;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InfoModel {

	/*
	 * 	함수 명 : InsertInfo()
	 * 	인자 : 없음
	 * 	리턴값 : void
	 * 	역할 : 사용자 입력값을 받아서 DB에 저장하는 역할 
	 */
	void InsertInfo(InfoVO vo) throws SQLException;// end of InsertInfo
	
	/*
	 * 	함수 명 : selectAll()
	 * 	인자 : 없음
	 * 	리턴값 : ArrayList <InfoVO>
	 * 	역할 : InfoVO 내 모든 데이터 도출 
	 */
	
	ArrayList<InfoVO> selectAll() throws SQLException; // end of Select All

	/*
	 * 	함수명 : selectByTel
	 * 	인자 : tel
	 * 	리턴값 : InfoVO
	 * 	역할 : 전화번호를 넘겨 받아서 해당하는 사람의 정보를 검색
	 */
	
	InfoVO selectByTel(String tel) throws SQLException;
	
	/*
	 * 	
	 * 	역할 : 전화번호를 넘겨 받아서 해당하는 사람의 정보를 삭제
	 */
	
	int delete(String tel) throws SQLException;
	
	int edit(InfoVO vo) throws SQLException;
}