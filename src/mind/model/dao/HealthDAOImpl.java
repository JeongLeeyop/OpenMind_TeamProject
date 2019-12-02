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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberDTO member) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean duplicateById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updatePoint(String memberId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PointDTO selectPoint(String memberId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertGym(GymDTO gym) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GymDTO> selectAllGym() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GymDTO> selectGymByKeyword(String keyField, String keyword) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("gym.selectByKeyword");//gym.selectByKeyword=SELECT CODE, NAME, ADDR, PHONE_NUM, FILE_NAME, GYM_CAPACITY, PRICE, GYM_COMMENT, WEEKDAY_HOUR, WEEKEND_HOUR, STAR_SCORE FROM GYM WHERE ? LIKE ?
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
