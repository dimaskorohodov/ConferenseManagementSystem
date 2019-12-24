package model;

import model.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    public User getEntity(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new User(resultSet.getString("login"), resultSet.getString("pass"), UserRole.getRole(resultSet.getString("user_role")));
    }
}
