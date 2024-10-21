package dao;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classs.Categories;
import utils.JDBCHelper;

public class CategoriesDAO implements DAO<Categories, String> {

	String insertSQL = "INSERT INTO Categories (id, name) VALUES (?, ?)";
	String updateSQL = "UPDATE Categories SET name = ? WHERE id = ?";
	String deleteSQL = "DELETE FROM Categories WHERE id = ?";
	String selectAllSQL = "SELECT * FROM Categories";
	String selectByIdSQL = "SELECT * FROM Categories WHERE id = ?";
	String createIdSQL = "SELECT id FROM Categories ORDER BY id DESC LIMIT 1";

	@Override
	public int insert(Categories entity) {
		try {
			return JDBCHelper.update(insertSQL, entity.getId(), entity.getName());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int update(Categories entity) {
		try {
			return JDBCHelper.update(updateSQL, entity.getName(), entity.getId());
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(String id) throws UnknownHostException, SQLException {
		return JDBCHelper.update(deleteSQL, id);
	}

	@Override
	public Categories selectById(String id) {
		List<Categories> list = this.selectBySQL(selectByIdSQL, id);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<Categories> selectAll() {
		return this.selectBySQL(selectAllSQL);
	}

	@Override
	public List<Categories> selectBySQL(String sql, Object... args) {
		List<Categories> list = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.query(sql, args);
				while (rs.next()) {
					Categories category = new Categories(rs.getString("id"), rs.getString("name"));
					list.add(category);
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

	public int createId() {
		int index = 1;
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.query(createIdSQL);
				while (rs.next()) {
					index = Integer.valueOf(rs.getString("id")) + 1;
				}
			} finally {
				if (rs != null) {
					rs.getStatement().getConnection().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}
}