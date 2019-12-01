package mind.service;

import java.sql.SQLException;
import java.util.List;

import mind.model.dto.GymDTO;
import mind.model.dto.MemberDTO;
import mind.model.dto.PointDTO;
import mind.model.dto.ReviewDTO;
import mind.model.dto.UseDetailDTO;

public interface HealthService {
	
	
	
	/**
	 * 회원가입(유저 등록)
	 * */
	int insertMember(MemberDTO member) throws SQLException;
	
	/**
	 * 아이디로 유저 검색
	 * */
	MemberDTO selectMemberById(String id) throws SQLException;
	
	/**
	 * 회원 정보 수정
	 * */
	int updateMember(MemberDTO member) throws SQLException;
	
	/**
	 * 회원탈퇴(유저 삭제)
	 * */
	int deleteMember(String id) throws SQLException;
	
	/**
	 * 아이디 중복체크 (회원가입 할 때 필요)
	 * @return 중복이면 true 아니면 false
	 * */
	boolean duplicateById(String id) throws SQLException;
	
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * 포인트 잔액 갱신
	 * */
	int updatePoint(String memberId) throws SQLException;
	
	/**
	 * 포인트 잔액 출력
	 * */
	PointDTO selectPoint(String memberId) throws SQLException;
	
	/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 헬스장 등록
	 * */
	int insertGym(GymDTO gym) throws SQLException;
	
	/**
	 * 모든 헬스장 검색
	 * */
	List<GymDTO> selectAllGym() throws SQLException;
	
	/**
	 * 헬스장 조건 검색
	 * */
	List<GymDTO> selectGymByKeyword(String keyField, String keyword) throws SQLException;
	
	/**
	 * 헬스장 상세보기에 필요한 검색
	 * */
	GymDTO selectGymByCode(int gymCode) throws SQLException;
	
	///////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 리뷰 등록
	 * */
	int insertReview(ReviewDTO review) throws SQLException;
	
	/**
	 * 헬스장 리뷰 검색(출력)
	 * */
	List<ReviewDTO> selectReviewByGymCode(int gymCode) throws SQLException;
	
	/**
	 * 리뷰 수정
	 * */
	int updateReview(ReviewDTO review) throws SQLException;
	
	/**
	 * 리뷰 삭제
	 * */
	int deleteReview(int reviewCode) throws SQLException;
	
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * 사용자가 헬스장 이용 버튼을 눌렀을 때 등록(호출)됨
	 * */
	int insertUseDetail(UseDetailDTO useDetail) throws SQLException;
	
	/**
	 * 사용자의 이용내역
	 * */
	List<UseDetailDTO> selectUseDetailByKeyword(String keyField, String keyword) throws SQLException;
	
	/**
	 * 헬스장 이용 신청을 한 사용자 목록
	 * @param state는 -1 만료됨, 0 사용됨, 1 사용가능
	 * */
	List<UseDetailDTO> selectUseDetailByGymCodeState(int gymCode, int state) throws SQLException;
	
	/**
	 * 이용상태 갱신
	 * */
	int updateUseDetail(int useDetailCode, int state) throws SQLException;

}
