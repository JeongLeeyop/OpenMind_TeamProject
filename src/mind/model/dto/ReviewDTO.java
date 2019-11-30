package mind.model.dto;

public class ReviewDTO {
	
	private int code;	//리뷰 PK 시퀀스라 int로 선언
	private String MemberId; //유저Id -> 작성자
	private String regDate; //리뷰게시물 등록일 이거 데이트로 선언해야하나요??
	private double starScore; //별점 소수점으로 등록가능할 것 같아서 더블로 선언
	private String content; //리뷰 게시물 내용
	private String fileName; //리뷰게시물에 사진 올릴 때 사진 파일 이름 저장할 변수
	private int gymCode; //헬스장 pk 시퀀스라 인트로 선언
	
	
	/**
	 * 인수없는 기본 ReviewDTO생성자
	 * */
	public ReviewDTO() {}
	
	/**
	 * 모든 필드를 이용한 ReviewDTO 생성자
	 * */
	public ReviewDTO(int code, String memberId, String regDate, double starScore, String content, String fileName,
			int gymCode) {
		super();
		this.code = code;
		MemberId = memberId;
		this.regDate = regDate;
		this.starScore = starScore;
		this.content = content;
		this.fileName = fileName;
		this.gymCode = gymCode;
	}
	
	
	/**
	 * 필요한 생성자 생성하세요ㅎㅎ
	 * */
	
	/////////////////////////////////////////
		
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public double getStarScore() {
		return starScore;
	}

	public void setStarScore(double starScore) {
		this.starScore = starScore;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getGymCode() {
		return gymCode;
	}

	public void setGymCode(int gymCode) {
		this.gymCode = gymCode;
	}
	


}
