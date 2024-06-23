package examScore;

import java.awt.*;
import javax.swing.*;

public class ResultPanel extends JPanel {
	
	private String[] nameList;
	private int[] numberList;
	
	public ResultPanel(String[] nameList, int[] numberList) {
		this.nameList = nameList;
		this.numberList = numberList;
		repaint();
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawLine(50, 250, 100*(nameList.length+1)-20, 250);
		
		for(int i=1; i<11; i++) {
			g.drawString(i*10+"", 25, 255-(20*i));
			g.drawLine(50, 250-(20*i), 100*(nameList.length+1)-20, 250-(20*i));
		}
				
		g.drawLine(50, 50, 50, 250);
		g.drawLine(100*(nameList.length+1)-20, 50, 100*(nameList.length+1)-20, 250);
		
		for(int i=0; i<nameList.length; i++) {			
			g.drawString(nameList[i], 100*(1+i), 270);	
		}
		g.setColor(Color.BLUE);
		
		for(int i=0; i<numberList.length; i++) {
			if(numberList[i] > 0) {
				g.fillRect(10+100*(i+1), 250-numberList[i]*2, 10, numberList[i]*2);
			}		
		}
	}


}
