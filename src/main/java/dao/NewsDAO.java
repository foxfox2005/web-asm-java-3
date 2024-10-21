
package dao;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classs.Categories;
import classs.News;
import classs.Users;
import dao.DAO;
import utils.EmailHelper;
import utils.JDBCHelper;

public class NewsDAO implements DAO<News, String> {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final SecureRandom RANDOM = new SecureRandom();
	String insertSQL = "INSERT INTO News (id, category_id, author, title, content, image, posted_date, view_count, home) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String updateSQL = "UPDATE News SET category_id = ?, author = ?, title = ?, content = ?, image = ?, posted_date = ?, view_count = ?, home = ? WHERE id = ?";
	String deleteSQL = "DELETE FROM News WHERE id = ?";
	String selectAllSQL = "SELECT * FROM News";
	String selectByIdSQL = "SELECT * FROM News WHERE id = ?";
	String selectNewsByIdUserSQL = "SELECT * FROM News WHERE Author = ?";
	String selectNewsHomeSQL = "SELECT * FROM News WHERE Home = 'true'";

	@Override
	public int insert(News entity) {
		try {
			return JDBCHelper.update(insertSQL, entity.getId(), entity.getCategoriesId(), entity.getUsersId(),
					entity.getTitle(), entity.getContent(), entity.getImage(), entity.getPostedDate(),
					entity.getViewCount(), entity.getHome());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(News entity) {
		try {
			return JDBCHelper.update(updateSQL, entity.getCategoriesId(), entity.getUsersId(), entity.getTitle(),
					entity.getContent(), entity.getImage(), entity.getPostedDate(), entity.getViewCount(),
					entity.getHome(), entity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(String id) throws UnknownHostException, SQLException {
		return JDBCHelper.update(deleteSQL, id);
	}

	@Override
	public News selectById(String id) {
		List<News> list = this.selectBySQL(selectByIdSQL, id);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<News> selectAll() {
		return this.selectBySQL(selectAllSQL);
	}

	@Override
    public List<News> selectBySQL(String sql, Object... args) {
        List<News> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Categories categories = new Categories();
                    categories.setId(rs.getString("category_id"));

                    Users users = new Users();
                    users.setId(rs.getString("author"));

                    News news = new News(
                            rs.getString("id"),
                            categories.getId(),
                            users.getId(),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("image"),
                            rs.getDate("posted_date"),
                            rs.getInt("view_count"),
                            rs.getBoolean("home"),
                            rs.getString("summary") // Add the summary parameter
                    );
                    list.add(news);
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

	public List<News> selectByIdUser(String id) {
		return this.selectBySQL(selectNewsByIdUserSQL, id);
	}

	public String randomId() {
		StringBuilder id = new StringBuilder(10); // Adjust length to match database schema
		for (int i = 0; i < 10; i++) {
			int randomIndex = RANDOM.nextInt(CHARACTERS.length());
			id.append(CHARACTERS.charAt(randomIndex));
		}
		return id.toString();
	}

	public List<News> showNewsHome() {
		return this.selectBySQL(selectNewsHomeSQL);
	}
	public News findById(String id) {
		String sql = "SELECT * FROM News WHERE id = ?";
		List<News> list = selectBySQL(sql, id);
		return list.isEmpty() ? null : list.get(0);
	}
	public void addNews(News news) {
		String sql = "INSERT INTO News (id, category_id, author, title, content, image, posted_date, view_count, home, summary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = JDBCHelper.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, news.getId());
			stmt.setString(2, news.getCategoriesId());
			stmt.setString(3, news.getUsersId());
			stmt.setString(4, news.getTitle());
			stmt.setString(5, news.getContent());
			stmt.setString(6, news.getImage());
			stmt.setDate(7, new java.sql.Date(news.getPostedDate().getTime()));
			stmt.setInt(8, news.getViewCount());
			stmt.setBoolean(9, news.getHome());
			stmt.setString(10, news.getSummary());
			stmt.executeUpdate();

			// Send email notifications
			List<String> subscribers = getSubscribers();
			for (String email : subscribers) {
				EmailHelper.sendEmail(email, "New Post Published", "A new post has been published: " + news.getTitle());
			}
		} catch (SQLException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private List<String> getSubscribers() {
		String sql = "SELECT email FROM Subscriptions";
		List<String> subscribers = new ArrayList<>();
		try (Connection conn = JDBCHelper.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				subscribers.add(rs.getString("email"));
			}
		} catch (SQLException | UnknownHostException e) {
			e.printStackTrace();
		}
		return subscribers;
	}
}