package persiatance.dao.sql;

import model.User;
import persiatance.dao.ReportStatisticDao;
import persiatance.dao.sql.queries.ReportStatisticQuery;
import persiatance.dbcp.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportStatisticSqlDao implements ReportStatisticDao {

  private ConnectionManager connectionManager;
  private UserSqlDao userSqlDao;

  public ReportStatisticSqlDao(ConnectionManager connectionManager, UserSqlDao userSqlDao) {
    this.connectionManager = connectionManager;
    this.userSqlDao = userSqlDao;
  }

  @Override
  public List<User> getAllRegistratedUsers(int reportId) {
    List<User> users = new ArrayList<>();
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportStatisticQuery.GET_REGISTRATED_USERS_BY_REPORT_ID);
      preparedStatement.setInt(1, reportId);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        users.add(userSqlDao.getById(resultSet.getInt("user_id")));
      }
      return users;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<User> getAllVisitedUsers(int reportId) {
    List<User> users = new ArrayList<>();
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportStatisticQuery.GET_VISITED_USERS_BY_REPORT_ID);
      preparedStatement.setInt(1, reportId);

      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        users.add(userSqlDao.getById(resultSet.getInt("user_id")));
      }
      return users;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void regustrateUser(int reportId, int userId) {

    try {
      System.out.println("In dao registrating user");
      connectionManager.getConnection().close();
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportStatisticQuery.REGISTRATE_USER);
      preparedStatement.setInt(1, 3);
      preparedStatement.setInt(2, 2);
      preparedStatement.executeUpdate();
      connectionManager.getConnection().close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void registrateUserByData(String reportTheme, String userLogin) {
    try {
      connectionManager.getConnection().close();
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportStatisticQuery.REGISTER);
      preparedStatement.setString(1, reportTheme);
      preparedStatement.setString(2, userLogin);
      preparedStatement.executeUpdate();
      connectionManager.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void visitUserByData(String reportTheme, String userLogin) {
    try {
      connectionManager.getConnection().close();
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportStatisticQuery.VISIT);
      preparedStatement.setString(1, reportTheme);
      preparedStatement.setString(2, userLogin);
      preparedStatement.executeUpdate();
      connectionManager.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void visitByUser(int reportId, int userId) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportStatisticQuery.VISIT_BY_USER);
      preparedStatement.setInt(1, reportId);
      preparedStatement.setInt(2, userId);
      preparedStatement.executeUpdate();
      connectionManager.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
