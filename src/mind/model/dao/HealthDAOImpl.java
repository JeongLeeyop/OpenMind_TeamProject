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
	
	@Override
	public int insertMember(MemberDTO member) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("member.insert");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPwd());
			ps.setString(3, member.getName());
			ps.setString(4, member.getPhoneNum());
			ps.setInt(5, member.getGymCode());
			
			result = ps.executeUpdate();
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

	//point insert 필요
	public int insertPoint(String memberId) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("point.insert");
		int result = 0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memberId);
			result = ps.executeUpdate();
		} finally {
			DbUtil.dbClose(ps, con);
		}
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
		String sql = proFile.getProperty("gym.selectByKeyword");
		
		//gym.selectByKeyword=SELECT CODE, NAME, ADDR, PHONE_NUM, FILE_NAME, GYM_CAPACITY, PRICE, GYM_COMMENT, WEEKDAY_HOUR, WEEKEND_HOUR, STAR_SCORE FROM GYM WHERE ? LIKE ?
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
			DbUtil.dbClose(ps, con);
		}
		return list;
	}

	@Override
	public GymDTO selectGymByCode(int gymCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReview(ReviewDTO review) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReviewDTO> selectReviewByGymCode(int gymCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateReview(ReviewDTO review) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReview(int reviewCode) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertUseDetail(UseDetailDTO useDetail) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UseDetailDTO> selectUseDetailByKeyword(String keyField, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UseDetailDTO> selectUseDetailByGymCodeState(int gymCode, int state) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateUseDetail(int useDetailCode, int state) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
