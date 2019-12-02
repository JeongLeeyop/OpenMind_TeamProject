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
		
		return 0;
	}
	
	/**
	 * 아이디로유저검색
	 * */
	public static MemberDTO selectMemberById(String id) throws SQLException{
		
		return null;
	}
	
	/**
	 * 회원 정보 수정
	 * */
	public static int updateMember(MemberDTO member) throws SQLException{
		
		return 0;
	}
	
	/**
	 * 회원 탈퇴(삭제)
	 * */
	public static int deleteMember(String id) throws SQLException{
		
		return 0;
	}
	
	/**
	 * 아이디 중복체크
	 * true = 중복 / false = 중복아님
	 * */
	public static boolean duplicateById(String id) throws SQLException{
		
		return false;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * 포인트 잔액 갱신
	 * */
	public static int updatePoint(String memberId) throws SQLException{
		
		return 0;
	}
	
	/**
	 * 포인트 잔액 검색, 출력
	 * */
	public static PointDTO selectPoint(String memberId) throws SQLException{
		
		return null;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 헬스장 등록
	 * */
	public static int insertGym(GymDTO gym) throws SQLException{
		
		return 0;
	}
	
	/**
	 * 모든 헬스장 검색
	 * */
	public static List<GymDTO> selectAllGym() throws SQLException{
		
		return null;
	}
	
	/**
	 * 헬스장 조건 검색
	 * */
	public List<GymDTO> selectGymByKeyword(String keyField, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 헬스장 상세보기에 필요한 검색
	 * */
	public static GymDTO selectGymByCode(int gymCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 리뷰등록
	 * */
	public static int insertReview(ReviewDTO review) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 헬스장 리뷰 검색(출력)
	 * */
	public static List<ReviewDTO> selectReviewByGymCode(int gymCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 리뷰 수정
	 * */
	public static int updateReview(ReviewDTO review) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 리뷰 삭제
	 * */
	public static int deleteReview(int reviewCode) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * 이용내역 추가 
	 * 사용자가 헬스장 이용 버튼을 눌렀을 때 등록(호출)됨
	 * */
	public static int insertUseDetail(UseDetailDTO useDetail) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 사용자의 이용내역 검색
	 * */
	public static List<UseDetailDTO> selectUseDetailByKeyword(String keyField, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 헬스장 이용 신청을 한 사용자 목록 검색
	 * */
	public static List<UseDetailDTO> selectUseDetailByGymCodeState(int gymCode, int state) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 이용상태 갱신
	 * */
	public static int updateUseDetail(int useDetailCode, int state) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
