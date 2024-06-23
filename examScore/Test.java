package examScore;

public class Test {

	public static void main(String[] args) {
		StudentManager sm = StudentManager.getInstance();
		sm.initialized();
		
		MainFrame mainframe = new MainFrame(sm);

	}

}
