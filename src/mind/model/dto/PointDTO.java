package mind.model.dto;

public class PointDTO {
	private String MemberId; //유저 아이디
	private int balance; //잔액 소수점 없는 것 같아서 인트로했어요~

	/**
	 * 인수없는 기본 PointDTO생성자
	 * */
	public PointDTO() {}

	/**
	 * 모든 필드를 이용하는 PointDTO생성자
	 * */
	public PointDTO(String memberId, int balance) {
		super();
		MemberId = memberId;
		this.balance = balance;
	}

	/**
	 * 필요한 생성자 만들어서 쓰세요
	 * */
	
	//////////////////////////////////////////////////
	
	//setter getter
	public String getMemberId() {
		return MemberId;
	}

	public void setMemberId(String memberId) {
		MemberId = memberId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
	
}
