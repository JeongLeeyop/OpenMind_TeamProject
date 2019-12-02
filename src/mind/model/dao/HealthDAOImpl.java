package mind.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		int result = 0;
		String sql = proFile.getProperty("member.delete");
		
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
		boolean result = false;
		String sql = proFile.getProperty("member.selectById");
		
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
		// TODO Auto-generated method stub
		return null;
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
