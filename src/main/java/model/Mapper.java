package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T getEntity(ResultSet resultSet) throws SQLException;
}
