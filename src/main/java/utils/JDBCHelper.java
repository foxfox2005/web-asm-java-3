package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Properties;

public class JDBCHelper {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try (InputStream input = JDBCHelper.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find db.properties");
            }
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            driver = prop.getProperty("db.driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static PreparedStatement getStatement(String sql, Object... arr) throws SQLException, UnknownHostException {
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement;
        if (sql.trim().startsWith("{")) {
            preparedStatement = conn.prepareCall(sql);
        } else {
            preparedStatement = conn.prepareStatement(sql);
        }
        for (int i = 0; i < arr.length; i++) {
            preparedStatement.setObject(i + 1, arr[i]);
        }
        return preparedStatement;
    }

    public static ResultSet query(String sql, Object... arr) throws SQLException, UnknownHostException {
        PreparedStatement ps = JDBCHelper.getStatement(sql, arr);
        return ps.executeQuery();
    }

    public static int update(String sql, Object... arr) throws SQLException, UnknownHostException {
        PreparedStatement ps = JDBCHelper.getStatement(sql, arr);
        try {
            return ps.executeUpdate();
        } finally {
            ps.getConnection().close();
        }
    }

    public static Object value(String sql, Object... arr) throws SQLException, UnknownHostException {
        ResultSet rs = query(sql, arr);
        while (rs.next()) {
            return rs.getObject(1);
        }
        rs.getStatement().getConnection().close();
        return null;
    }

    public static Connection getConnection() throws SQLException, UnknownHostException {
        return DriverManager.getConnection(url, user, password);
    }
}