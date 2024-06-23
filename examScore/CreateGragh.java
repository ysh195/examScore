package examScore;

import javax.swing.*;
import java.awt.*;

public class CreateGragh {
	
	private JFrame frame = new JFrame("");
	private JLabel textLine = new JLabel("");
	
	public CreateGragh(String[] nameList, int[] scoreList) {
		
		System.out.println("시험 결과를 출력합니다.");
		
		int x_size = 100*(1+nameList.length);
		frame.setLocation(500,200);
		frame.setSize(x_size+50,400);		
				
		buildGUI(nameList, scoreList); // 임시 입력

	}
	
	private void buildGUI(String[] nameList, int[] scoreList) {
		
		frame.setLayout(new BorderLayout());
		
		Container contentpane = frame.getContentPane();
		
		ResultPanel resultPanel = new ResultPanel(nameList, scoreList);
				
		contentpane.add(resultPanel,BorderLayout.CENTER);		
		contentpane.add(textLine,BorderLayout.SOUTH);
		
	}
	
	void setScreenTitle(String str) {
		frame.setTitle(str);
	}
	
	void setScreenVisible() {	
		frame.setVisible(true);	
	}
	
	void setTextLabel(String str) {
		textLine.setText(str);
		Font font = new Font("Serif",Font.BOLD,20);
		textLine.setFont(font);
	}

}
