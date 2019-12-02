package mind.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mind.model.dto.GymDTO;
import mind.model.dto.MemberDTO;
import mind.model.dto.PointDTO;
import mind.model.dto.ReviewDTO;
import mind.model.dto.UseDetailDTO;
import mind.util.DbUtil;

public class HealthDAOImpl implements HealthDAO {
	private Properties proFile = DbUtil.getProFile();
	
	//트랜잭션 처리 - 참고 : C:\Edu\Java\JavaWorkSpace\step10_JDBC\src\ex1101\transaction
	@Override
	public int insertMember(MemberDTO member) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("member.insert");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPwd());
			ps.setString(3, member.getName());
			ps.setString(4, member.getPhoneNum());
			ps.setInt(5, member.getGymCode());
			
			result = ps.executeUpdate();
			
			if(result > 0) {
				result = insertPoint(member.getId(), con);
				if(result > 0)
					con.commit();
				else
					con.rollback();
			}else {
				con.rollback();
			}
			
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				throw new SQLException(ex.getMessage()); 
			}
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public MemberDTO selectMemberById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = proFile.getProperty("member.selectById");
		MemberDTO memberDTO = null;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next())
				memberDTO = new MemberDTO(rs.getString("id"), rs.getString("name"), rs.getString("pwd"), rs.getString("phone_num"), rs.getInt("gym_code"));
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return memberDTO;
	}

	@Override
	public int updateMember(MemberDTO member) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("member.update");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getPwd());
			ps.setString(2, member.getPhoneNum());
			ps.setString(3, member.getId());
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public int deleteMember(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("member.delete");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public boolean duplicateById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = proFile.getProperty("member.selectById");
		boolean result = false;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next())
				result = true;
		}finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return result;
	}

	@Override
	public int insertPoint(String memberId, Connection con) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("point.insert");
		int result = 0;
		
		ps = con.prepareStatement(sql);
		ps.setString(1, memberId);
		result = ps.executeUpdate();
		return result;
	}
	
	@Override
	//업데이트할 가격 매개변수로 받아야 함
	public int updatePoint(String memberId, int price) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("point.update");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setString(2, memberId);
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}
	
	//트랜잭션 처리
	@Override
	public int updatePoint(String memberId, int gymCode, int price) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = proFile.getProperty("point.update");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			//고객 포인트 차감
			ps = con.prepareStatement(sql);
			ps.setInt(1, (-1) * price);
			ps.setString(2, memberId);
			result = ps.executeUpdate();
			ps.close();
			String tempId = null;
			if(result > 0) {
				//짐 코드로 사업자 아이디 찾기
				sql = proFile.getProperty("member.selectByGymCode");
				ps = con.prepareStatement(sql);
				ps.setInt(1, gymCode);
				rs = ps.executeQuery();
				ps.close();
				if(rs.next()) {
					tempId = rs.getString("id");
					
					//사업자 포인트 적립
					ps = con.prepareStatement(sql);
					ps.setInt(1, price);
					ps.setString(2, tempId);
					result = ps.executeUpdate();
					
					if(result > 0) {
						con.commit();
					}else {
						con.rollback();						
					}
				}else {
					con.rollback();
				}
			}else {
				con.rollback();
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				throw new SQLException(ex.getMessage());
			}
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}

		return result;
	}

	@Override
	public PointDTO selectPoint(String memberId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = proFile.getProperty("point.selectById");
		PointDTO pointDTO = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberId);
			rs = ps.executeQuery();
			
			if(rs.next())
				pointDTO = new PointDTO(rs.getString("member_id"), rs.getInt("balance"));
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return pointDTO;
	}

	@Override
	public int insertGym(GymDTO gym) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("gym.insert");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, gym.getName());
			ps.setString(2, gym.getAddr());
			ps.setString(3, gym.getPhoneNum());
			ps.setString(4, gym.getFileName());
			ps.setInt(5, gym.getGymCapacity());
			ps.setInt(6, gym.getPrice());
			ps.setString(7, gym.getComment());
			ps.setString(8, gym.getWeekdayHour());
			ps.setString(9, gym.getWeekendHour());
			
			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public List<GymDTO> selectAllGym() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = proFile.getProperty("gym.selectAll");
		List<GymDTO> list = new ArrayList<GymDTO>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt("CODE");
				String name = rs.getString("NAME");
				String addr = rs.getString("ADDR");
				String phoneNum = rs.getString("PHONE_NUM");
				String fileName = rs.getString("FILE_NAME");
				int gymCapacity = rs.getInt("GYM_CAPACITY");
				int price = rs.getInt("PRICE");
				String comment = rs.getString("GYM_COMMENT");
				String weekdayHour = rs.getString("WEEKDAY_HOUR");
				String weekendHour = rs.getString("WEEKEND_HOUR");
				double avgScore = rs.getDouble("STAR_SCORE");
				
				GymDTO gym = new GymDTO(code, name, addr, phoneNum, fileName, gymCapacity, price, comment, weekdayHour, weekendHour, avgScore);
				
				list.add(gym);
			}
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return list;
	}

	@Override
	public List<GymDTO> selectGymByKeyword(String keyField, String keyword) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = proFile.getProperty("gym.selectByKeyword");//sql = gym.selectByKeyword=SELECT CODE, NAME, ADDR, PHONE_NUM, FILE_NAME, GYM_CAPACITY, PRICE, GYM_COMMENT, WEEKDAY_HOUR, WEEKEND_HOUR, STAR_SCORE FROM GYM WHERE ? LIKE ?

		ResultSet rs = null;
		List<GymDTO> list = new ArrayList<GymDTO>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, keyField);
			ps.setString(2, "%"+keyword+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt("CODE");
				String name = rs.getString("NAME");
				String addr = rs.getString("ADDR");
				String phoneNum = rs.getString("PHONE_NUM");
				String fileName = rs.getString("FILE_NAME");
				int gymCapacity = rs.getInt("GYM_CAPACITY");
				int price = rs.getInt("PRICE");
				String comment = rs.getString("GYM_COMMENT");
				String weekdayHour = rs.getString("WEEKDAY_HOUR");
				String weekendHour = rs.getString("WEEKEND_HOUR");
				double avgScore = rs.getDouble("STAR_SCORE");
				
				GymDTO gym = new GymDTO(code, name, addr, phoneNum, fileName, gymCapacity, price, comment, weekdayHour, weekendHour, avgScore);
				list.add(gym);
			}
			
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return list;
	}

	@Override
	public GymDTO selectGymByCode(int gymCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("gym.selectByCode");//sql = SELECT CODE, NAME, ADDR, PHONE_NUM, FILE_NAME, GYM_CAPACITY, PRICE, GYM_COMMENT, WEEKDAY_HOUR, WEEKEND_HOUR, STAR_SCORE FROM GYM WHERE CODE = ?
		ResultSet rs = null;
		GymDTO gym = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, gymCode);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int code = rs.getInt("CODE");
				String name = rs.getString("NAME");
				
				String addr = rs.getString("ADDR");
				String phoneNum = rs.getString("PHONE_NUM");
				String fileName = rs.getString("FILE_NAME");
				int gymCapacity = rs.getInt("GYM_CAPACITY");
				int price = rs.getInt("PRICE");
				String comment = rs.getString("GYM_COMMENT");
				String weekdayHour = rs.getString("WEEKDAY_HOUR");
				String weekendHour = rs.getString("WEEKEND_HOUR");
				double avgScore = rs.getDouble("STAR_SCORE");
				
				gym = new GymDTO(code, name, addr, phoneNum, fileName, gymCapacity, price, comment, weekdayHour, weekendHour, avgScore);
				
			}
			
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return gym;
	}

	@Override
	public int insertReview(ReviewDTO review) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("review.insert"); //INSERT INTO REVIEW (CODE, GYM_CODE, MEMBER_ID, REG_DATE, STAR_SCORE, CONTENT, FILE_NAME) VALUES(REVIEW_CODE_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, ?, ?)
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, review.getGymCode());
			ps.setString(2, review.getMemberId());
			ps.setDouble(3, review.getStarScore());
			ps.setString(4, review.getContent()); 
			ps.setString(5, review.getFileName());

			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public List<ReviewDTO> selectReviewByGymCode(int gymCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("gym.selectByKeyword");//SELECT CODE, GYM_CODE, MEMBER_ID, REG_DATE, STAR_SCORE, CONTENT, FILE_NAME FROM REVIEW WHERE GYM_CODE = ?
		ResultSet rs = null;
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, gymCode);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt("CODE");
				String memberId = rs.getString("MEMBER_ID");
				String regDate = rs.getString("REG_DATE");
				double starScore = rs.getDouble("STAR_SCORE");
				String content = rs.getString("CONTENT");
				String fileName = rs.getString("FILE_NAME");
				int gCode = rs.getInt("GYM_CODE");	//gymCode가 매개변수로 사용되고 있어서 gCode로 사용!
				ReviewDTO review = new ReviewDTO(code, memberId, regDate, starScore, content, fileName, gCode);
				list.add(review);
			}
			
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return list;
	}

	@Override
	public int updateReview(ReviewDTO review) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("review.update"); //UPDATE REVIEW SET STAR_SCORE = ?, CONTENT = ?, FILE_NAME = ? WHERE CODE = ? AND MEMBER_ID = ?
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setDouble(1, review.getStarScore());
			ps.setString(2, review.getContent());
			ps.setString(3, review.getFileName());
			ps.setInt(4, review.getCode()); 
			ps.setString(5, review.getMemberId());

			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public int deleteReview(int reviewCode, String memberId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("review.delete"); //DELETE FROM REVIEW WHERE CODE = ? AND MEMBER_ID = ?
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewCode);
			ps.setString(2, memberId);
			

			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public int insertUseDetail(UseDetailDTO useDetail) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("useDetail.insert"); //INSERT INTO USE_DETAIL(CODE, MEMBER_ID, GYM_CODE, PRICE, USE_START_HOUR, STATE) SELECT USE_DETAIL_SEQ.NEXTVAL, MEMBER.ID, GYM.CODE, GYM.PRICE, SYSDATE, 1 FROM MEMBER, GYM WHERE MEMBER.ID = ? AND GYM.CODE = ?
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, useDetail.getMemberId());
			ps.setInt(2, useDetail.getGymCode());
			

			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public List<UseDetailDTO> selectUseDetailByKeyword(String keyField, String keyword) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = proFile.getProperty("useDetail.selectByKeyword"); //SELECT CODE, MEMBER_ID, GYM_CODE, PRICE, USE_START_HOUR, STATE FROM USE_DETAIL WHERE ? = ?
		sql.replaceFirst("?", keyField);
		ResultSet rs = null;
		List<UseDetailDTO> list = new ArrayList<UseDetailDTO>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, keyword);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt("CODE");
				String memberId = rs.getString("MEMBER_ID");
				int gymCode = rs.getInt("GYM_CODE");
				int price = rs.getInt("PRICE");
				String useStartHour = rs.getString("USE_START_HOUR");
				int state = rs.getInt("STATE");
						
				UseDetailDTO useDetail = new UseDetailDTO(code, memberId, gymCode, price, useStartHour, state);
				list.add(useDetail);
			}
			
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return list;
	}

	@Override
	public List<UseDetailDTO> selectUseDetailByGymCodeState(int gymCode, int state) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("useDetail.selectByGymCodeState"); //SELECT CODE, MEMBER_ID, GYM_CODE, PRICE, USE_START_HOUR, STATE FROM USE_DETAIL WHERE GYM_CODE = ? AND STATE = 1
		ResultSet rs = null;
		List<UseDetailDTO> list = new ArrayList<UseDetailDTO>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, gymCode);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int code = rs.getInt("CODE");
				String memberId = rs.getString("MEMBER_ID");
				int gCode = rs.getInt("GYM_CODE");  // gymCode는 매개변수로 들어오는 값이랑 같아서 변수이름 gCode로 변경
				int price = rs.getInt("PRICE");
				String useStartHour = rs.getString("USE_START_HOUR");
				int st = rs.getInt("STATE"); //state는 매개변수로 들어오는 값이랑 같아서 변수이름 st로 변경
						
				UseDetailDTO useDetail = new UseDetailDTO(code, memberId, gCode, price, useStartHour, st);
				list.add(useDetail);
			}
			
		} finally {
			DbUtil.dbClose(rs, ps, con);
		}
		return list;
	}

	@Override
	public int updateUseDetail(int useDetailCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("useDetail.updateToUsed"); //UPDATE USE_DETAIL SET STATE = 0 WHERE CODE = ?
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, useDetailCode);
			
			

			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}
	
	@Override
	public int checkUseDetailState() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("useDetail.updateToUsed"); //UPDATE USE_DETAIL SET STATE = CASE WHEN SYSDATE - TO_DATE(USE_START_HOUR) > 1.0 THEN -1 ELSE 1 END
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);

			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
		return result;
	}

}
