package mind.service;

import java.sql.SQLException;
import java.util.List;

import mind.model.dao.HealthDAO;
import mind.model.dao.HealthDAOImpl;
import mind.model.dto.GymDTO;
import mind.model.dto.MemberDTO;
import mind.model.dto.PointDTO;
import mind.model.dto.ReviewDTO;
import mind.model.dto.UseDetailDTO;

public class HealthService {
	private static HealthDAO healthDAO = new HealthDAOImpl();
		
	/**
	 * 유저 회원가입(등록)
	 * */
	public static int insertMember(MemberDTO member) throws SQLException{
		int result = 0;
		
		if(healthDAO.duplicateById(member.getId()))
			throw new SQLException("사용 중인 아이디입니다.");
		else 
			result = healthDAO.insertMember(member);
		return result;
	}
	
	/**
	 * 아이디로유저검색
	 * */
	public static MemberDTO selectMemberById(String id) throws SQLException{
		MemberDTO memberDTO = healthDAO.selectMemberById(id);
		return memberDTO;
	}
	
	/**
	 * 회원 정보 수정
	 * */
	public static int updateMember(MemberDTO member) throws SQLException{
		int result = healthDAO.updateMember(member);
		return result;
	}
	
	/**
	 * 회원 탈퇴(삭제)
	 * */
	public static int deleteMember(String id) throws SQLException{
		int result = healthDAO.deleteMember(id);
		return result;
	}
	
	/**
	 * 포인트 잔액 갱신 : 포인트 충전용
	 * */
	public static int updatePoint(String memberId, int price) throws SQLException{
		int result = healthDAO.updatePoint(memberId, price);
		return result;
	}
	
	/**
	 * 포인트 잔액 갱신 : 사용자가 헬스장 이용하기 시
	 * */
	public static int updatePoint(String memberId, int gymCode, int price) throws SQLException{
		int result = healthDAO.updatePoint(memberId, gymCode, price);
		return result;
	}
	
	/**
	 * 포인트 잔액 검색, 출력
	 * */
	public static PointDTO selectPoint(String memberId) throws SQLException{
		PointDTO pointDTO = healthDAO.selectPoint(memberId);
		return pointDTO;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 헬스장 등록
	 * */
	public static int insertGym(GymDTO gym) throws SQLException{
		int result = healthDAO.insertGym(gym);
		return result;
	}
	
	/**
	 * 모든 헬스장 검색
	 * */
	public static List<GymDTO> selectAllGym() throws SQLException{
		List<GymDTO> list = healthDAO.selectAllGym();
		return list;
	}
	
	/**
	 * 헬스장 조건 검색
	 * */
	public static List<GymDTO> selectGymByKeyword(String keyField, String keyword) throws SQLException {
		List<GymDTO> list = healthDAO.selectGymByKeyword(keyField, keyword);
		return list;
	}
	
	/**
	 * 헬스장 상세보기에 필요한 검색
	 * */
	public static GymDTO selectGymByCode(int gymCode) throws SQLException {
		GymDTO gymDTO = healthDAO.selectGymByCode(gymCode);
		return gymDTO;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 리뷰등록
	 * */
	public static int insertReview(ReviewDTO review) throws SQLException {
		int result = healthDAO.insertReview(review);
		return result;
	}
	
	/**
	 * 헬스장 리뷰 검색(출력)
	 * */
	public static List<ReviewDTO> selectReviewByGymCode(int gymCode) throws SQLException {
		List<ReviewDTO> list = healthDAO.selectReviewByGymCode(gymCode);
		return list;
	}
	
	/**
	 * 리뷰 수정
	 * */
	public static int updateReview(ReviewDTO review) throws SQLException {
		int result = healthDAO.updateReview(review);
		return result;
	}
	
	/**
	 * 리뷰 삭제
	 * */
	public static int deleteReview(int reviewCode, String memberId) throws SQLException {
		int result = healthDAO.deleteReview(reviewCode, memberId);
		return result;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * 이용내역 추가 
	 * 사용자가 헬스장 이용 버튼을 눌렀을 때 등록(호출)됨
	 * */
	public static int insertUseDetail(UseDetailDTO useDetail) throws SQLException {
		int result = healthDAO.insertUseDetail(useDetail);
		return result;
	}
	
	/**
	 * 사용자의 이용내역 검색
	 * */
	public static List<UseDetailDTO> selectUseDetailByKeyword(String keyField, String keyword) throws SQLException {
		healthDAO.checkUseDetailState();
		List<UseDetailDTO> list = healthDAO.selectUseDetailByKeyword(keyField, keyword);
		return list;
	}
	
	/**
	 * 헬스장 이용 신청을 한 사용자 목록 검색
	 * */
	public static List<UseDetailDTO> selectUseDetailByGymCodeState(int gymCode, int state) throws SQLException {
		healthDAO.checkUseDetailState();
		List<UseDetailDTO> list = healthDAO.selectUseDetailByGymCodeState(gymCode, state);
		return list;
	}
	
	/**
	 * 이용상태 갱신 - 사업자가 헬스장 이용확인
	 * */
	public static int updateUseDetail(int useDetailCode) throws SQLException {
		int result = healthDAO.updateUseDetail(useDetailCode);
		return result;
	}
}
