package mind.model.dto;

public class GymDTO {
	private int code;  //gym PK 시퀀스라 인트로 선언
	private String MemberId; //유저 아이디 
	private String addr;  //헬스장 주소
	private String PhoneNum; //헬스장 전화번호
	private String fileName; //헬스장 사진올릴 때 사용할 사진 이름
	private int gymCapacity; // 수용인원 int랑 string이랑 뭐할까요? 일단 인트로함
	private int price; //헬스장 이용가격
	private String comment; //헬스장에 관한 사장님이 쓰는 코멘트 
	private String WeekdayHour; //헬스장 주간 이용시간
	private String WeekendHour; //헬스장 주말 이용시간
	private double avgScore; //헬스장 평균 스코어 1.5, 2.5등 소수점으로 떨어져서 더블로 했습니다~
	
	/**
	 * 인수 없는 GymDTO 생성자
	 * */
	public GymDTO() {}
	
	/**
	 * 모든 필드를 사용하는 GymDTO 생성자
	 * */
	public GymDTO(int code, String memberId, String addr, String phoneNum, String fileName, int gymCapacity, int price,
			String comment, String weekdayHour, String weekendHour, double avgScore) {
		super();
		this.code = code;
		MemberId = memberId;
		this.addr = addr;
		PhoneNum = phoneNum;
		this.fileName = fileName;
		this.gymCapacity = gymCapacity;
		this.price = price;
		this.comment = comment;
		WeekdayHour = weekdayHour;
		WeekendHour = weekendHour;
		this.avgScore = avgScore;
	}

	/**
	 * 그 외 필요한 생성자는 각자 생성해서 사용
	 * */
	
	//////////////////////////////////////////////////////////
	
	//setter getter
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMemberId() {
		return MemberId;
	}

	public void setMemberId(String memberId) {
		MemberId = memberId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getGymCapacity() {
		return gymCapacity;
	}

	public void setGymCapacity(int gymCapacity) {
		this.gymCapacity = gymCapacity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWeekdayHour() {
		return WeekdayHour;
	}

	public void setWeekdayHour(String weekdayHour) {
		WeekdayHour = weekdayHour;
	}

	public String getWeekendHour() {
		return WeekendHour;
	}

	public void setWeekendHour(String weekendHour) {
		WeekendHour = weekendHour;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	
	
	
	
	
	
	
}
