package examScore;

import java.util.ArrayList; // 차라리 수정사항 적용할 때마다 반영하는 게 더 낫지 않을까?

public class StudentManager { 
	
	private static ArrayList<Student> ARRAYLIST = new ArrayList<>();
	
	private StudentManager() {}
	private static StudentManager replace = new StudentManager();
	public static StudentManager getInstance() {
		return replace;
	}
	
	void initialized() {
		int x_size = 5;
		int y_size = 10;
		String[] initial_names = {"김1","김2","김3","박1","박2","박3","이1","이2","이3","최1"};
		int[][] initial_scores = new int[y_size][x_size];
		for(int i=0; i<y_size; i++) {
			for(int j=0; j<x_size; j++) {
				initial_scores[i][j] = (int)(Math.random()*101);
			}
			ARRAYLIST.add(new Student(initial_names[i],initial_scores[i][0],initial_scores[i][1],initial_scores[i][2],initial_scores[i][3],initial_scores[i][4]));
		}
	}
	
	void update(String[] nameList, int[][] scoreList) { // 자바 테이블 체계상 두 배열의 길이는 항상 같을 수밖에 없음
		
		ArrayList<Student> temp = new ArrayList<>(); // 타입 변환 중의 오류는 여기에 값을 넣어줄 곳에서 처리할 테니 상관없음. 여긴 값을 넣어서 arraylist만 업데이트하면 됨
		
		for(int i=0; i<nameList.length; i++) {
			temp.add(new Student(nameList[i],scoreList[i][0],scoreList[i][1],scoreList[i][2],scoreList[i][3],scoreList[i][4]));
		}
		
		System.out.println("데이터 동기화에 성공하였습니다.");
		ARRAYLIST = temp;

	}
	
	float avgBySubject(String subjectName) {
		
		int student_count = ARRAYLIST.size(); // 학생 수
		
		if(student_count<=0) { // 혹시 0 나누기라서 계산이 안 되거나, 이상한 수가 나왔을 때에 대한 대비
			return 0;
		}
		
		int total = 0;
		switch(subjectName) {
		case "국어":
			total = ARRAYLIST.stream().mapToInt(Student::getKor_score).sum(); // map을 쓰면 특정 클래스 타입의 메소드를 불러올 수 있네
			break; // 여기서도 getAnyScore 쓰고 싶은데, mapToInt로 메서드 쓰려면 인수 필요 없는 것이어야 함
		case "영어":
			total = ARRAYLIST.stream().mapToInt(Student::getEng_score).sum();
			break;
		case "수학":
			total = ARRAYLIST.stream().mapToInt(Student::getMath_score).sum();
			break;
		case "과학":
			total = ARRAYLIST.stream().mapToInt(Student::getScience_score).sum();
			break;
		case "사회":
			total = ARRAYLIST.stream().mapToInt(Student::getSociety_score).sum();
			break;
		case "전체":
			total = ARRAYLIST.stream().mapToInt(Student::getMyTotal).sum();
			return (float)total/(student_count*5);
		default:
			System.out.println("잘못된 입력입니다.");
			return 0;
		}
		
		return (float)total/student_count;
	}
	
	float avgByName(String studentName) {
		int subject_count = 5;
		int total = 0;
		
		if(ARRAYLIST.stream().filter(s -> s.getName().equals(studentName)).count() == 0) {
			System.out.println("존재하지 않는 학생입니다.");
			return 0;
		}
		
		if(studentName == "전체") {
			total = ARRAYLIST.stream().mapToInt(Student::getMyTotal).sum();
		}
		else {
			total = ARRAYLIST.stream().filter(s -> s.getName().equals(studentName)).mapToInt(Student::getMyTotal).sum();
		}
		
		return (float)total/subject_count;
	}
	
	String[][] allToStringArray(){
		
		int y = ARRAYLIST.size();
		String[][] temp = new String[y][6];
		
		// toArray의 리턴 타입은 object[]
		Object[] nameList = ARRAYLIST.stream().map(Student::getName).toArray(); // 각 항목별로 배열로 분리.
		Object[] kor_list = ARRAYLIST.stream().map(Student::getKor_score).toArray();
		Object[] eng_list = ARRAYLIST.stream().map(Student::getEng_score).toArray();
		Object[] math_list = ARRAYLIST.stream().map(Student::getMath_score).toArray();
		Object[] sci_list = ARRAYLIST.stream().map(Student::getScience_score).toArray();
		Object[] soc_list = ARRAYLIST.stream().map(Student::getSociety_score).toArray();
		
		for(int i=0; i<y; i++) {
			
			// 먼저 변수 선언. 이 메서드 내에서 많이 씀
			String name;
			String kor_score = String.valueOf(kor_list[i]);
			String eng_score;
			String math_score;
			String sci_score;
			String soc_score;
		
			// 타입 변환 및 오류 대비. 사실 다 object 타입이라 상관없지 않나 싶음
			try {name = String.valueOf(nameList[i]);}catch(Exception e) {name = ""; System.out.println("StudentManger - allToStringArray - name : String타입으로 변환하기에 적합하지 않습니다."); e.getStackTrace();}
			try {kor_score = String.valueOf(kor_list[i]);}catch(Exception e) {kor_score = ""; System.out.println("StudentManger - allToStringArray - kor : int타입으로 변환하기에 적합하지 않습니다."); e.getStackTrace();}
			try {eng_score = String.valueOf(eng_list[i]);}catch(Exception e) {eng_score = ""; System.out.println("StudentManger - allToStringArray - eng: int타입으로 변환하기에 적합하지 않습니다."); e.getStackTrace();}
			try {math_score = String.valueOf(math_list[i]);}catch(Exception e) {math_score = ""; System.out.println("StudentManger - allToStringArray - math : int타입으로 변환하기에 적합하지 않습니다."); e.getStackTrace();}
			try {sci_score = String.valueOf(sci_list[i]);}catch(Exception e) {sci_score = ""; System.out.println("StudentManger - allToStringArray - sci : int타입으로 변환하기에 적합하지 않습니다."); e.getStackTrace();}
			try {soc_score = String.valueOf(soc_list[i]);}catch(Exception e) {soc_score = ""; System.out.println("StudentManger - allToStringArray - soc : int타입으로 변환하기에 적합하지 않습니다."); e.getStackTrace();}
			
			// 변환된 값을 temp에 저장
			temp[i][0] = name;
			temp[i][1] = kor_score;
			temp[i][2] = eng_score;
			temp[i][3] = math_score;
			temp[i][4] = sci_score;
			temp[i][5] = soc_score;
			
		}
		
		return temp;
	}
	
	void whose_score_is(String subject, int score) {
		
		ARRAYLIST.forEach(s -> {
			if(s.getAnyScore(subject) == score) {
				System.out.println(s.getName() + " (" + String.valueOf(s.getAnyScore(subject)) +"점)");
			}
		});
	}
	
	void MaxScoreBySubject(String subject) {
		int maxScore = 0;
		
		switch(subject) {
		case "국어":
			maxScore = ARRAYLIST.stream().mapToInt(s -> s.getKor_score()).max().getAsInt(); // min/max 계산 시 리턴값이 optionalInt라서 마지막에 그냥 int로 변환
			break;
		case "영어":
			maxScore = ARRAYLIST.stream().mapToInt(s -> s.getEng_score()).max().getAsInt();
			break;
		case "수학":
			maxScore = ARRAYLIST.stream().mapToInt(s -> s.getMath_score()).max().getAsInt();
			break;
		case "과학":
			maxScore = ARRAYLIST.stream().mapToInt(s -> s.getScience_score()).max().getAsInt();
			break;
		case "사회":
			maxScore = ARRAYLIST.stream().mapToInt(s -> s.getSociety_score()).max().getAsInt();
			break;
		default:
			System.out.println("적절한 입력이 아닙니다.");
			return;
		}
		
		System.out.print(subject + " 과목의 최고 득점자 : "); // 일부러 print로 해서 뒤와 이어지게 함
		whose_score_is(subject, maxScore); // 콘솔에 텍스트 출력함.
				
	}
	
	void MinScoreBySubject(String subject) {
		int minScore = 0;
		
		switch(subject) {
		case "국어":
			minScore = ARRAYLIST.stream().mapToInt(s -> s.getKor_score()).min().getAsInt();
			break;
		case "영어":
			minScore = ARRAYLIST.stream().mapToInt(s -> s.getEng_score()).min().getAsInt();
			break;
		case "수학":
			minScore = ARRAYLIST.stream().mapToInt(s -> s.getMath_score()).min().getAsInt();
			break;
		case "과학":
			minScore = ARRAYLIST.stream().mapToInt(s -> s.getScience_score()).min().getAsInt();
			break;
		case "사회":
			minScore = ARRAYLIST.stream().mapToInt(s -> s.getSociety_score()).min().getAsInt();
			break;
		default:
			System.out.println("적절한 입력이 아닙니다.");
			return;
		}
		
		System.out.print(subject + " 과목의 최저 득점자 : "); // 일부러 print로 해서 뒤와 이어지게 함
		whose_score_is(subject, minScore); // 콘솔에 텍스트 출력함.
				
	}
	
	void TopAvg() {
		Object[] nameListObj = ARRAYLIST.stream().map(Student::getName).toArray();
		
		String[] nameListStr = new String[nameListObj.length];
		float[] avgScores = new float[nameListObj.length];
		int hisOrder = 0;
		float maxScore = 0;
		for (int i=0; i<nameListObj.length; i++) {
			
			// 배열에 값을 입력하는 과정.
			nameListStr[i] = String.valueOf(nameListObj[i]); // object[]라서 String[]처럼 처리할 수가 없음. 그래서 값을 String으로 변환하는 중
			avgScores[i] = avgByName(nameListStr[i]); // 변환된 이름으로 각자의 평균 점수 계산해서 배열에 입력
			
			if(maxScore < avgScores[i]) { // 평균 점수들 중에서 최고 점수가 몇 점이고, 최고점수가 있는 순서를 저장.
				maxScore = avgScores[i];
				hisOrder=i;
			}
		}
		
		System.out.println("전체 평균 최고 득점자 : " + nameListStr[hisOrder] + " (평균 : " + String.valueOf(maxScore) +")");

	}
	
	// 전체 평균 최고 득점자 표시
}
