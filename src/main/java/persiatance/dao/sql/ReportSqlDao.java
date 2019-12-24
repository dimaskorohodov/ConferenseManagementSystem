package persiatance.dao.sql;

import model.Mapper;
import model.Report;
import persiatance.dao.ReportDao;
import persiatance.dao.sql.queries.ReportQuery;
import persiatance.dbcp.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportSqlDao implements ReportDao {
  private ConnectionManager connectionManager;
  private UserSqlDao userSqlDao;
  private ReportStatisticSqlDao reportStatisticSqlDao;

  public ReportSqlDao(ConnectionManager connectionManager, UserSqlDao userSqlDao, ReportStatisticSqlDao reportStatisticSqlDao) {
    this.connectionManager = connectionManager;
    this.userSqlDao = userSqlDao;
    this.reportStatisticSqlDao = reportStatisticSqlDao;
  }


  @Override
  public Report getReportByTheme(String theme) {
    Report report = null;

    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportQuery.GET_REPORT_BY_THEME);
      preparedStatement.setString(1, theme);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        report = new ReportRowMapper().getEntity(resultSet);
      }
      return report;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Integer getIdByReportTheme(String theme) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportQuery.GET_ID);
      preparedStatement.setString(1, theme);
      ResultSet resultSet = preparedStatement.executeQuery();
      int id = -1;
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


  private class ReportRowMapper implements Mapper<Report> {
    Report report = new Report();

    @Override
    public Report getEntity(ResultSet resultSet) throws SQLException {
      if (resultSet == null) {
        return null;
      }
      report.setReportTheme(resultSet.getString("report_theme"));
      report.setPlace(resultSet.getString("place"));
      report.setDate(resultSet.getDate("report_date"));
      report.setSpeaker(userSqlDao.getSpeakerById(resultSet.getInt("speaker_id")));
    //  report.setRegistratedUsers(reportStatisticSqlDao.getAllRegistratedUsers(resultSet.getInt("id")));
     // report.setVisitedUsers(reportStatisticSqlDao.getAllVisitedUsers(resultSet.getInt("id")));
      return report;
    }
  }

  public List<Report> getAll() {
    List<Report> reports = new ArrayList<>();
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportQuery.GET_ALL_REPORTS);
      preparedStatement.setInt(1, 0);

      ResultSet resultSet = preparedStatement.executeQuery();
      System.out.println("RS size" + resultSet.getFetchSize());
     // System.out.println("ROWS AMOUNT - "+getRowsAmount());
      int rows = getRowsAmount();

      for (int i = 0; i < rows; i++) {
        resultSet.next();
        reports.add(new ReportRowMapper().getEntity(resultSet));
      }
      //System.out.println(reports.size());

//      while (resultSet.next()) {
//        reports.add(new ReportRowMapper().getEntity(resultSet));
//      }
      connectionManager.getConnection().close();
      return reports;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return reports;
  }

  public int getRowsAmount() {
    int amount = 0;
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportQuery.COUNT_ROWS);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      amount = resultSet.getInt("count(*)");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return amount;
  }

  public void changeReportInfo(Report report, String oldTheme) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportQuery.UPDATE_REPORT);
      preparedStatement.setString(1, report.getReportTheme());
      preparedStatement.setString(2, report.getPlace());
      preparedStatement.setDate(3, report.getDate());
      preparedStatement.setString(4, oldTheme);
      preparedStatement.executeUpdate();
      connectionManager.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void createReport(Report report, int speakerId) {
    try {
      PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(ReportQuery.CREATE_REPORT);
      preparedStatement.setString(1, report.getReportTheme());
      preparedStatement.setString(2, report.getPlace());
      preparedStatement.setDate(3, report.getDate());
      preparedStatement.setInt(4, speakerId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
