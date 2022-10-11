package c_info2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoView {

	// 1. 멤버 변수 선언
	JFrame f;
	JTextField tfName,tfID,tfTel,tfSex,tfAge,tfHome;
	JTextArea ta;
	JButton bAdd,bShow,bSearch,bDelete,bCancel,bEdit;


	//비지니스로직 - 모델단 : DB 연동
	InfoModel model;


	// 2. 멤버 변수 객체 생성
	InfoView () {
		f = new JFrame("DBTest");
		tfName = new JTextField(20);	// 문자열을 하나 입력할 수 있는 변수 생성
		tfID = new JTextField(20);
		tfTel = new JTextField(20);
		tfSex = new JTextField(20);
		tfAge = new JTextField(20);
		tfHome = new JTextField(20);
		ta = new JTextArea (250,50);	// 여러 문자열을 입력할 수 있는 변수 생성
		bAdd = new JButton("Add");		// 각 버튼의 형태를 띄는 JButton 생성
		bShow = new JButton("Show");
		bSearch = new JButton("Search");
		bDelete = new JButton("Delete");
		bCancel = new JButton("Cancel");
		bEdit = new JButton("수정하기");

	} // end of InfoView

	// 3. 화면 구성 및 출력
	/*
	 *  전체 프레임 BorderLayout 지정
	 *  - West	: JPanel GridLayout 지정 6행 2열 JPanel(GridLayout (6,2))
	 *  - Center : TextArea
	 *  - South : JPanel 생성 (GridLayout(1,6))
	 */
	public void addLayout() {
		// 프레임 지정
		f.setLayout(new BorderLayout());

		// 붙이기 작업 진행 (West)
		JPanel jwest = new JPanel (new GridLayout (6,2));	// textfield를 묶을 수 있는 판넬 생성
		jwest.setPreferredSize(new Dimension(250,25));	// 판넬의 사이즈 지정
		jwest.add(new JLabel("Name",JLabel.CENTER));	// 각 라벨 입력 , JLabel.CENTER을 통해 가운데 정렬
		jwest.add(tfName);								// 입력창 생성
		jwest.add(new JLabel("ID",JLabel.CENTER));
		jwest.add(tfID);
		jwest.add(new JLabel("Tel",JLabel.CENTER));
		jwest.add(tfTel);
		jwest.add(new JLabel("Sex",JLabel.CENTER));
		jwest.add(tfSex);
		jwest.add(new JLabel("Age",JLabel.CENTER));
		jwest.add(tfAge);
		jwest.add(new JLabel("Home",JLabel.CENTER));
		jwest.add(tfHome);
		f.add(jwest,BorderLayout.WEST);

		// 붙이기 작업 진행 (East)
		f.add(ta,BorderLayout.CENTER);		//textarea 를 가운데에 위치

		// 붙이기 작업 진행 (South)
		JPanel jsouth = new JPanel (new GridLayout(1,6));	// JButton을 묶을 수 있는 판넬 생성
		jsouth.setPreferredSize(new Dimension(100,50));
		jsouth.add(bAdd);
		jsouth.add(bShow);
		jsouth.add(bSearch);
		jsouth.add(bDelete);
		jsouth.add(bCancel);
		jsouth.add(bEdit);
		f.add(jsouth,BorderLayout.SOUTH);

		//	DB 연동
		try {
			model = new InfoModelImpl();
		} catch (ClassNotFoundException e) {
			// 예외 발생 시
			e.printStackTrace();
		}

		// 화면 출력
		f.setBounds(0, 0, 500, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 창 종료시, 콘솔도 종료기능 활성화

	} // end of Add Layout

	/*
	 *	함수명 :eventProc()
	 *	인자 : 없음
	 *	리턴값 : void
	 *	역할 : 이벤트 추가  (각 버튼별 펑션)
	 */

	void eventProc() {

		//Add 버튼 눌렸을 때 insertData 실행
		bAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertData();

			}// end of Action Performed
		}); // end of Insert Data

		//Show 버튼 눌렸을 때 selectAll 실행	
		bShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList <InfoVO> data = model.selectAll();
					ta.setText("----------- 검색 결과 --------\n\n");
					for (InfoVO vo :data) {
						ta.append(vo.toString());
					} // end of Enhanced For (each for 문을 이용해, 데이터를 저장해둔 InfoVO 를 출력)

				} catch (SQLException e1) {
					// 자료가 없거나 예외가 발생할 경우
					ta.setText("검색 실패 : "+e1.getMessage());
				}	// end of SQL Exception

			} // end Of Action Performed
		});

		// Search 버튼 눌렸을 때 selectByTel 실행
		bSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectByTel();
			}
		});

		// Tel textfield에서 엔터쳤을 때 selectByTel 실행
		tfTel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectByTel();
			}
		});

		// Delete 버튼 눌렸을 때
		bDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteByTel();

			}
		});

		// exit 버튼 눌렀을 때
		bEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateByTel();

			}
		});

	} // end of Event Proc

	/*
	 *	insertData()
	 *	인자 : 없음
	 *	리턴값 : void
	 *	역할 : 사용자가 입력한 값을 저장. 
	 */

	void insertData() {
		// (1) 사용자 입력값 얻어오기
		String name =tfName.getText();
		String id = tfID.getText();
		String sex = tfSex.getText();
		String home = tfHome.getText();
		String tel = tfTel.getText();
		int age = Integer.parseInt(tfAge.getText());

		// (2) 1번의 값들을 하나의 InfoVO에 지정 - setter , 생성자
		InfoVO vo = new InfoVO();
		//InfoVO vo = new InfoVO (name,id,sex,home,tel,age); 
		vo.setAge(age);
		vo.setName(name);
		vo.setId(id);
		vo.setSex(sex);
		vo.setHome(home);
		vo.setTel(tel);

		// (3) 모델의 Insertinfo() 함수 호출
		try {
			model.InsertInfo(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of try~catch

		// (4) 화면의 입력값 지우기
		clearText();

	} // end of InsertData

	/*
	 * 	함수명 : ClearText
	 * 	인자 : 없음
	 * 	리턴 값 : void
	 * 	기능 : 입력하는 TextField 초기화
	 * 
	 */

	void clearText() {
		tfName.setText(null);
		tfAge.setText(null);
		tfHome.setText(null);
		tfTel.setText(null);
		tfSex.setText(null);
		tfID.setText(null);
	} // end of clearText


	/*
	 * 	함수명 : selectByTel()
	 * 	인자 : tel
	 * 	리턴값 : void
	 * 	역할 : 전화번호를 넘겨 받아서 해당하는 사람의 정보를 검색
	 */

	void selectByTel() {
		try {
			// (1) 입력한 전화번호 값을 얻어오기
			String tel = tfTel.getText();

			// (2) 모델단에 selectByTel() 호출
			InfoVO vo = model.selectByTel(tel);

			// (3) 받은 결과를 각각의 텍스트 필드에 지정(출력)
			tfName.setText(vo.getName());
			tfID.setText(vo.getId());
			tfAge.setText(String.valueOf(vo.getAge()));
			tfSex.setText(vo.getSex());
			tfTel.setText(vo.getTel());
			tfHome.setText(vo.getHome());

		} catch(Exception ex) {
			ta.setText("전화번호 검색 실패 : " + ex.getMessage());
		}
	}

	/*
	 * 	함수명 : deleteByTel()
	 * 	인자 : tel
	 * 	리턴값 : void
	 * 	역할 : 전화번호를 넘겨 받아서 해당하는 사람의 정보를 삭제
	 */

	void deleteByTel() {

		// (1) 입력한 전화번호 값을 얻어오기
		String tel = tfTel.getText();
		try {
			// (2) 모델단에 delete() 호출
			int rs = model.delete(tel);

			// (3) 화면지우기
			clearText();
			ta.setText(rs+" 행 만큼 삭제하였습니다.");
		} catch(Exception ex) {
			ta.setText("삭제 실패 : " + ex.getMessage());
		}

	}// end of deleteByTel

	/*
	 * 	함수명 : updateByTel()
	 * 	인자 : tel
	 * 	리턴값 : void
	 * 	역할 : 텍스트필드에 입력된 값으로 정보 수정
	 */

	void updateByTel () {

		// (1) 입력한 전화번호 값을 얻어오기
		String tel = tfTel.getText();
		InfoVO vo = new InfoVO () ;
		// 새로운 InfoVO 객체인 vo를 생성해, tf에 입력된 값들이 vo에 저장될 수 있도록 한다.
		vo.setName(tfName.getText());
		vo.setAge(Integer.parseInt(tfAge.getText()));
		vo.setHome(tfHome.getText());
		vo.setId(tfID.getText());
		vo.setSex(tfSex.getText());
		vo.setTel(tfTel.getText());
		
		try {
			// (2) 모델단에 update() 호출
			int rs = model.edit(vo);
						
			// (3) 화면지우기
			clearText();
			ta.setText(rs+" 행 만큼 업데이트하였습니다.");
		} catch(Exception ex) {
			ta.setText("업데이트 실패 : " + ex.getMessage());
		}
	
	}//end of update
	public static void main(String[] args) {

		InfoView info = new InfoView();
		info.addLayout();
		info.eventProc();

	}

}
