package examScore;

public class Operator {

	public static void main(String[] args) {
		StudentManager sm = StudentManager.getInstance();
		sm.initialized();
		
		MainFrame mainframe = new MainFrame(sm);

	}

}
