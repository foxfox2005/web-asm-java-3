package dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classs.Newsletters;
import utils.JDBCHelper;
import classs.Newsletters;

public class NewsletterDAO implements DAO<Newsletters, String> {
	String insertSQL = "INSERT INTO Newsletters (Email, enabled) VALUES (?, ?)";
	String updateSQL = "UPDATE Newsletters SET enabled = ? WHERE Email = ?";
	String deleteSQL = "DELETE FROM Newsletters WHERE Email = ?";
	String selectAllSQL = "SELECT * FROM Newsletters";
	String selectByIdSQL = "SELECT * FROM Newsletters WHERE Email = ?";

	@Override
	public int insert(Newsletters entity) {
		try {
			return JDBCHelper.update(insertSQL, entity.getEmail(), entity.isEnabled());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int update(Newsletters entity) {
		try {
			return JDBCHelper.update(updateSQL, entity.isEnabled(), entity.getEmail());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(String email) throws UnknownHostException, SQLException {
		return JDBCHelper.update(deleteSQL, email);
	}

	@Override
	public Newsletters selectById(String email) {
		List<Newsletters> list = this.selectBySQL(selectByIdSQL, email);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<Newsletters> selectAll() {
		return this.selectBySQL(selectAllSQL);
	}

	@Override
	public List<Newsletters> selectBySQL(String sql, Object... args) {
		List<Newsletters> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.query(sql, args);
				while (rs.next()) {
					Newsletters newsletters = new Newsletters(rs.getString("Email"), rs.getBoolean("enabled"));
					list.add(newsletters);
				}
			} finally {
				if (rs != null) {
					rs.getStatement().getConnection().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Newsletters> getTopViewedNewsletters(int limit) {
		List<Newsletters> newsletters = new ArrayList<>();
		String sql = "SELECT * FROM Newsletters ORDER BY views DESC LIMIT ?";
		try (Connection conn = JDBCHelper.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, limit);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					newsletters.add(mapRowToNewsletter(rs));
				}
			}
		} catch (SQLException | UnknownHostException e) {
			e.printStackTrace();
		}
		return newsletters;
	}

	public List<Newsletters> getLatestNewsletters(int limit) {
		List<Newsletters> newsletters = new ArrayList<>();
		String sql = "SELECT * FROM Newsletters ORDER BY created_at DESC LIMIT ?";
		try (Connection conn = JDBCHelper.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, limit);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					newsletters.add(mapRowToNewsletter(rs));
				}
			}
		} catch (SQLException | UnknownHostException e) {
			e.printStackTrace();
		}
		return newsletters;
	}

	public List<Newsletters> getUserViewedNewsletters(int userId, int limit) {
		List<Newsletters> newsletters = new ArrayList<>();
		String sql = "SELECT n.* FROM UserViews uv JOIN Newsletters n ON uv.newsletter_id = n.id WHERE uv.user_id = ? ORDER BY uv.viewed_at DESC LIMIT ?";
		try (Connection conn = JDBCHelper.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, limit);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					newsletters.add(mapRowToNewsletter(rs));
				}
			}
		} catch (SQLException | UnknownHostException e) {
			e.printStackTrace();
		}
		return newsletters;
	}

	private Newsletters mapRowToNewsletter(ResultSet rs) throws SQLException {
		Newsletters newsletter = new Newsletters();
		newsletter.setEmail(rs.getString("Email"));
		newsletter.setEnabled(rs.getBoolean("enabled"));
		// Add other fields as necessary
		return newsletter;
	}
}