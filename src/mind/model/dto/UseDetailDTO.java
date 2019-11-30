package mind.model.dto;

public class UseDetailDTO {

	private int code;  //이용내역 PK 시퀀스 int로 선언
	private String memberId; //유저 아이디 
	private int gymCode; //헬스장 코드 시퀀스라서 int
	private int price; //사용한 가격 
	private String useStartHour; //고객이 헬스장 상세페이지에서 이용하기 버튼을 누른 순간 date로 해야하는지 String 해야하는지 ..?
	private int state; //이용상태 -1 : 만료 , 0 : 사용됨 , 1 : 사용가능
	
	/**
	 * 기본 UseDetailDTO 생성자 
	 * */
	public UseDetailDTO() {}

	
	/**
	 * 모든 필드 사용하는 UseDetailDTO 생성자
	 * */
	public UseDetailDTO(int code, String memberId, int gymCode, int price, String useStartHour, int state) {
		super();
		this.code = code;
		this.memberId = memberId;
		this.gymCode = gymCode;
		this.price = price;
		this.useStartHour = useStartHour;
		this.state = state;
	}

	
	
	/////////////////////////////////////////////
	//setter getter
	

	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public int getGymCode() {
		return gymCode;
	}


	public void setGymCode(int gymCode) {
		this.gymCode = gymCode;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getUseStartHour() {
		return useStartHour;
	}


	public void setUseStartHour(String useStartHour) {
		this.useStartHour = useStartHour;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}
	
	

	
	
	
	
	
}
