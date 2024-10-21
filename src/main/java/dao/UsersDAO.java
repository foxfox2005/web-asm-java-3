package dao;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classs.Users;
import utils.JDBCHelper;

public class UsersDAO implements DAO<Users, String> {
    String insertSQL = "INSERT INTO Users (id, password, fullname, birthday, gender, mobile, email, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSQL = "UPDATE Users SET password = ?, fullname = ?, birthday = ?, gender = ?, mobile = ?, email = ?, role = ? WHERE id = ?";
    String deleteSQL = "DELETE FROM Users WHERE id = ?";
    String selectAllSQL = "SELECT * FROM Users";
    String selectByIdSQL = "SELECT * FROM Users WHERE id = ?";

    @Override
    public int insert(Users entity) {
        try {
            return JDBCHelper.update(insertSQL, entity.getId(), entity.getPassword(), entity.getFullname(),
                    entity.getBirthday(), entity.getGender(), entity.getMobile(), entity.getEmail(), entity.isRole());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(Users entity) {
        try {
            return JDBCHelper.update(updateSQL, entity.getPassword(), entity.getFullname(), entity.getBirthday(),
                    entity.getGender(), entity.getMobile(), entity.getEmail(), entity.isRole(), entity.getId());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(String id) throws UnknownHostException, SQLException {
        return JDBCHelper.update(deleteSQL, id);
    }

    @Override
    public Users selectById(String id) {
        List<Users> list = this.selectBySQL(selectByIdSQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Users> selectAll() {
        return this.selectBySQL(selectAllSQL);
    }

    @Override
    public List<Users> selectBySQL(String sql, Object... args) {
        List<Users> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Users user = new Users(rs.getString("id"), rs.getString("password"), rs.getString("fullname"),
                            rs.getDate("birthday"), rs.getBoolean("gender"), rs.getString("mobile"),
                            rs.getString("email"), rs.getBoolean("role"));
                    list.add(user);
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
}