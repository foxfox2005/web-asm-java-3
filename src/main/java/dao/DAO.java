package dao;

import java.net.UnknownHostException;
import java.sql.SQLException;

public interface DAO<Entity, DataType> {
	public int insert(Entity entity);

	public int update(Entity entity);

	public int delete(DataType id) throws UnknownHostException, SQLException;

	public Entity selectById(DataType id);

	public java.util.List<Entity> selectAll();

	public java.util.List<Entity> selectBySQL(String sql, Object... arr);
}
