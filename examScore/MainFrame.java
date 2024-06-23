package examScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // 테이블 수정에 관련된 패키지
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame { // 학생 추가 및 삭제, 현재 데이터 리턴, 변동 사항 반영. 여기까지는 확정인데, 평균이나 등수 같은 거 표현을 어떻게 할 지 아직 고민. 그냥 귀찮으니까 한 번에 할까
	
	static JComboBox combo = new JComboBox();
	static JTable table;
	
	public MainFrame(StudentManager studentManager) { // 저장소와 사용자가 보는 파일 목록은 서로 연동되지 않고, 별개로 동작합니다.
	
		Dimension dim = new Dimension(700,300);
		
		JFrame frame = new JFrame("학생 성적 계산");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		
		String[][] contents = studentManager.allToStringArray();
		String[] header = {"학생 이름", "국어","영어","수학","과학","사회"};
		DefaultTableModel model = new DefaultTableModel(contents,header); // 테이블 세팅과 관련된 것
		table = new JTable(model); // 테이블 생성.
		JScrollPane scrollpane = new JScrollPane(table);
		// 내용을 모델에 넘기고, 모델을 테이블에 연결.
		
		setComboBox();
	
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS)); //
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS)); //
		
		JLabel text1 = new JLabel("       리스트  관리");
		JLabel blank = new JLabel(" ");
		
		JButton add_Btn = new JButton("         추가        ");
		add_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] temp = {"","","","","",""};
				model.addRow(temp);
			}
			
		});
				
		JButton remove_Btn = new JButton("         삭제        ");
		remove_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getRowCount()<=0) {
					System.out.println("더 이상 삭제할 수 없습니다.");
					return;
				}
				
				if(table.getSelectedRow() == -1) {
					model.setRowCount(table.getRowCount()-1);
				}
				else {
					model.removeRow(table.getSelectedRow());
				}
				setComboBox();
			}
			
		});
		
		JButton synchroniztion_Btn = new JButton("데이터 동기화");
		synchroniztion_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("데이터 동기화를 진행합니다.");
				studentManager.update(getNameData(), getScoreData());
				setComboBox();
			}
			
		});
			
		JButton all_result_Btn = new JButton("전체 결과");
		all_result_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 여기선 어차피 다 써야 하니까 이름 비어 있는 부분은 알아서 거르게 하고, getdata로 간단하게 가자.
				// 전체는 과목별 전체의 평균으로
				// 그리고 기존 평균 넣던 곳에 과목별 1등과 꼴등의 이름과 점수, 그리고 전체 1등과 꼴등의 이름과 점수.
				
			}
			
		});
		
		JButton eachResult_Btn = new JButton("개별 결과");
		eachResult_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 항목이 실제로 존재하는지에 대한 검증은 필요 없음. 콤보 박스로 선택하는데, 콤보 박스는 저장소의 데이터랑 바로 연동되기 때문에 항상 옳음.
				int location = -1;
				Object selectedOne = combo.getSelectedItem();
				String selectedStr = String.valueOf(selectedOne);
				for(int i=0; i<table.getRowCount(); i++) {
					if(table.getValueAt(i, 0).equals(selectedOne)) {
						location = i;
						break;
					}
				}
				// 배열 중에서 찾으니 검색에 실패했다면 location = -1인 점을 이용
				// 어차피 콤보 내에서만 선택되기 때문에 오류가 날 일은 많지 않고, 이름 중에서 못 찾았다면 당연히 과목일 것임.
				// 근데 혹시라도 오류의 가능성이 있으니 switch문 중 default로 처리
				if(location == -1) {
										
					switch(selectedStr) { // 알아서 적당히 처리.
					case "국어":
						location = 1;
						break;
					case "영어":
						location = 2;
						break;
					case "수학":
						location = 3;
						break;
					case "과학":
						location = 4;
						break;
					case "사회":
						location = 5;
						break;						
					default:
						System.out.println("잘못된 입력입니다.");
						return;
					}
					
					// getNameData()와 getScoreData()는 이름 없는 빈 칸도 일단 저장은 하게 해둬서 그래프 그리는 데에 쓸 데이터로는 적합하지 않음. 그래서 일일이 찾아야 함
					int count1 = 0, count2 = 0, total = 0;
					
					for(int i=0; i<table.getRowCount(); i++) {
						if( (String.valueOf(table.getValueAt(i, 0)).length() >= 1) && (String.valueOf(table.getValueAt(i, 0)) != null) ) {
							count1++;							
						}
					}
					
					String[] studentNameList = new String[count1];
					int[] scoresList = new int[count1];
					
					
					for(int i=0; i<table.getRowCount(); i++) {
						if( (String.valueOf(table.getValueAt(i, 0)).length() >= 1) && (String.valueOf(table.getValueAt(i, 0)) != null) ) {
							studentNameList[count2] = String.valueOf(table.getValueAt(i, 0));
							try {
								int num = Integer.valueOf(String.valueOf(table.getValueAt(i, location)));
								scoresList[count2] = Math.min(100, Math.max(0, num));
							}
							catch(Exception err) {
								System.out.println("MainFrame - eachResult_Btn : Int 타입으로 변환하기에 적합하지 않은 숫자입니다.");
								scoresList[count2] = 0;
							}
							total += scoresList[count2];
							count2++;							
						}
						
						if(count2 == count1) {
							break;
						}
					}
					
					CreateGragh gragh = new CreateGragh(studentNameList,scoresList);
					gragh.setScreenTitle("학생별 " + selectedStr + " 과목 점수");
					gragh.setTextLabel("         "+selectedStr+" 과목의 평균 점수 : " + String.valueOf((float)total/studentNameList.length));
					gragh.setScreenVisible();
				
				}
				else { // 애초에 테이블 내의 이름 중에서 위치를 탐색했으니, 이쪽은 더 찾을 필요 없이 바로 계산.
					String[] subjectNameList = {"국어","영어","수학","과학","사회"};
					int[] scoresList = new int[5];
					int total = 0;
					for(int i=0; i<5; i++) {
						try {
							int num = Integer.valueOf(String.valueOf(table.getValueAt(location, i+1))); // 이름이 0부터 시작하니까 점수들은 1부터 시작해야 함
							scoresList[i] = Math.min(100, Math.max(0, num)); 
						}
						catch(Exception err) {
							scoresList[i] = 0;
							System.out.println("MainFrame - eachResult_Btn : Int 타입으로 변환하기에 적합하지 않은 숫자입니다.");
						}
						total += scoresList[i];
					}
					
					CreateGragh gragh = new CreateGragh(subjectNameList,scoresList);
					gragh.setScreenTitle(selectedStr + "의 성적표");
					gragh.setTextLabel("         "+selectedStr+"의 평균 점수 : " + String.valueOf((float)total/scoresList.length));
					gragh.setScreenVisible();
					
				}
					
					
				// 그게 과목중에 있으면 > 학생들 이름 + 학생별 과목 점수
				// 아니면 > 과목명 + 해당 학생의 전체 점수
				// 점수와 이름은 해당 항목의 위치를 테이블 내에서 찾아서 getNameData()와 getScoreData()로 가져온 데이터에서 추출하면 됨.
				// 그렇게 String[]과 int[] 타입으로 바꿔서 CreateGragh에 넣어주면 끝. 평균은 프레임 내부에서 해결하는 걸로.
								
			}
			
		});
		
		panel.add(text1);
		panel.add(blank);
		panel.add(add_Btn);
		panel.add(remove_Btn);
		panel.add(synchroniztion_Btn);

		panel1.add(all_result_Btn);
		panel1.add(eachResult_Btn);
		panel1.add(combo);
	
		frame.add(scrollpane,BorderLayout.CENTER);		
		frame.add(panel,BorderLayout.EAST);
		frame.add(panel1,BorderLayout.SOUTH);		
		frame.pack();
		
		frame.setVisible(true);

	}
	
	void setComboBox() {
		
		combo.removeAllItems(); // 일단 다 지움
		
		String[] subjectText = {"국어","영어","수학","과학","사회"}; // 목록에 과목 추가
		for(String s: subjectText) {
			combo.addItem(s);
		}
		
		for(int i=0; i<table.getRowCount(); i++) { // 목록에 학생 이름 추가
			if((String.valueOf(table.getValueAt(i, 0)).length() > 0) && (String.valueOf(table.getValueAt(i, 0)) != null) ) {
				combo.addItem(table.getValueAt(i, 0));
			}			
		}
	}
	
	String[] getNameData() {
		int length = table.getRowCount();
		String[] output = new String[length];
		for(int i=0; i<length; i++) {
			try { // String 타입으로 변환하는 과정에서 문제 생기면 에러로 잡음
				String str = String.valueOf(table.getValueAt(i, 0)); // x축 0번은 이름란이니까.
				if((str.length() <= 0) || (str == null) ) { // 테이블 데이터 가져올 때는 값이 없어도 오류도 뭣도 안 뜸. 그래서 길이가 0이거나 null일 때로 설정
					output[i] = "";
				}
				else {
					output[i] = str; // 값이 멀쩡히 존재하고, String타입으로 변환 가능하면 저장
				}
			}
			catch(Exception e) {
				System.out.println("MainFrame - getNameData : String 타입으로 변환하기에 적절하지 않습니다.");
				output[i] = "";
			}
		}
		
		return output;
	}
	
	int[][] getScoreData(){
		
		int length = table.getRowCount();
		int[][] output = new int[length][5];
		
		for(int i=0; i<length; i++) {
			for(int j=0; j<5; j++) {
				try {
					if((String.valueOf(table.getValueAt(i, j+1)).length() <= 0 ) || (String.valueOf(table.getValueAt(i, j+1)) == null)) {
						System.out.println("MainFrame - getNameData : Int 타입으로 변환하기에 적절하지 않습니다.");
						output[i][j] = 0;
					}
					else {
						int num = Integer.valueOf(String.valueOf(table.getValueAt(i, j+1))); // table 값을 바로 int로 바꾸면 왜인지 모르게 입력이 안 됨.
						output[i][j] = Math.min(100, Math.max(0, num)); 
					}	
				}
				catch(Exception e) {
					System.out.println("MainFrame - getNameData : Int 타입으로 변환하기에 적절하지 않습니다.");
					output[i][j] = 0;
				}
			}
		}
		
		return output;
	}

}
