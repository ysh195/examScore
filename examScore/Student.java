package examScore;

public class Student {
	
	// 필드
	private String name;
	private int kor_score;
	private int eng_score;
	private int math_score;
	private int science_score;
	private int society_score;
	
	// 생성자
	public Student() {}
	public Student(String name, int kor_score, int eng_score, int math_score, int science_score, int society_score) {
		this.name = name;
		this.kor_score = kor_score;
		this.eng_score = eng_score;
		this.math_score = math_score;
		this.science_score = science_score;
		this.society_score = society_score;
	}
	
	// 메서드
	public void setAll(String name, int kor_score, int eng_score, int math_score, int science_score, int society_score) {
		this.name = name;
		this.kor_score = kor_score;
		this.eng_score = eng_score;
		this.math_score = math_score;
		this.science_score = science_score;
		this.society_score = society_score;
	}
	
	public int getMyTotal() {
		return kor_score + eng_score + math_score + science_score + society_score;
	}
	
	public int getAnyScore(String subject) {
		switch(subject) {
		case "국어":
			return kor_score;
		case "영어":
			return eng_score;
		case "수학":
			return math_score;
		case "과학":
			return science_score;
		case "사회":
			return society_score;
		default:
			System.out.println("정규 과목이 아닙니다.");
			return 0;
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor_score() {
		return kor_score;
	}
	public void setKor_score(int kor_score) {
		this.kor_score = kor_score;
	}
	public int getEng_score() {
		return eng_score;
	}
	public void setEng_score(int eng_score) {
		this.eng_score = eng_score;
	}
	public int getMath_score() {
		return math_score;
	}
	public void setMath_score(int math_score) {
		this.math_score = math_score;
	}
	public int getScience_score() {
		return science_score;
	}
	public void setScience_score(int science_score) {
		this.science_score = science_score;
	}
	public int getSociety_score() {
		return society_score;
	}
	public void setSociety_score(int society_score) {
		this.society_score = society_score;
	}

}
