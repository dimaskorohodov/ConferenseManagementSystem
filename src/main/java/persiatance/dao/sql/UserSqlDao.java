package persiatance.dao.sql;

import model.Mapper;
import model.Speaker;
import model.User;
import model.enums.UserRole;
import persiatance.dao.UserDao;
import persiatance.dao.sql.queries.UserQuery;
import persiatance.dbcp.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSqlDao implements UserDao {
  private ConnectionManager connectionManager;
  private RatingSqlDao ratingSqlDao;

  public UserSqlDao(ConnectionManager connectionManager, RatingSqlDao ratingSqlDao) {
    this.connectionManager = connectionManager;
    this.ratingSqlDao = ratingSqlDao;
  }

  @Override
  public User getByLogin(String login) {
    User user = null;
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.GET_BY_LOGIN);
      preparedStatement.setString(1, login);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user = new UserRowMapper().getEntity(resultSet);
      }
      connectionManager.getConnection().close();
      return user;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public User getById(int id) {
    User user = null;
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.GET_USER_BY_ID);
      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user = new UserRowMapper().getEntity(resultSet);
      }
      return user;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Speaker getSpeakerByLogin(String login) {
    Speaker speaker = null;
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.GET_SPEAKER_BY_LOGIN);
      preparedStatement.setString(1, login);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        speaker = new SpeakerRowMapper().getEntity(resultSet);
      }
      connectionManager.getConnection().close();
      return speaker;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Speaker getSpeakerById(int id) {
    Speaker speaker = null;
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.GET_BY_ID);
      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        speaker = new SpeakerRowMapper().getEntity(resultSet);
      }
      return speaker;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Integer getIdByLogin(String login) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.GET_ID_BY_LOGIN);
      preparedStatement.setString(1, login);
      int id = -1;

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        id = resultSet.getInt("id");

      }
      if (id != -1) {
        return id;
      }
      connectionManager.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void registrateModerator(String login, String password) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.CREATE_MODERATOR);
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, password);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void registrateSpeaker(String login, String password) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.CREATE_SPEAKER);
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, password);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void registrateUser(String login, String password) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UserQuery.CREATE_USER);
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, password);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public class UserRowMapper implements Mapper<User> {
    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
      if (resultSet == null) {
        return null;
      }
      return new User(resultSet.getString("login"), resultSet.getString("pass"), UserRole.getRole(resultSet.getString("user_role")));
    }
  }

  public class SpeakerRowMapper implements Mapper<Speaker> {
    @Override
    public Speaker getEntity(ResultSet resultSet) throws SQLException {
      if (resultSet == null) {
        return null;
      }
      return new Speaker(resultSet.getString("login"), resultSet.getString("pass"), UserRole.SPEAKER, ratingSqlDao.getRatingBySpeakerId(resultSet.getInt("id")));
    }
  }


}
