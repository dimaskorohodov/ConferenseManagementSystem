package services;

import Dtos.ReportDto;
import model.Speaker;
import model.User;
import persiatance.dao.sql.ReportSqlDao;
import persiatance.dao.sql.ReportStatisticSqlDao;
import persiatance.dao.sql.UserSqlDao;

import java.util.List;

public class UserService {

  private UserSqlDao userSqlDao;
  private ReportSqlDao reportSqlDao;
  private ReportStatisticSqlDao reportStatisticSqlDao;

  public UserService(UserSqlDao userSqlDao, ReportSqlDao reportSqlDao, ReportStatisticSqlDao reportStatisticSqlDao) {
    this.userSqlDao = userSqlDao;
    this.reportSqlDao = reportSqlDao;
    this.reportStatisticSqlDao = reportStatisticSqlDao;
  }

  public User loginUser(String login, String password) {
    User user = userSqlDao.getByLogin(login);
    if (user != null && user.getPassword().equals(password)) {
      return user;
    }
    return null;
  }

  public Speaker loginSpeaker(String login, String password) {
    Speaker speaker = userSqlDao.getSpeakerByLogin(login);
    if (speaker != null && speaker.getPassword().equals(password)) {
      return speaker;
    }
    return null;
  }

  public int getIdByLogin(String login) {
    return userSqlDao.getIdByLogin(login);
  }

  public void logOutUser() {

  }

  public void registrateUser() {


  }

  public boolean registrateModerator(String login, String password) {
    if (userSqlDao.getByLogin(login) != null) {
      return false;
    }
    userSqlDao.registrateModerator(login, password);
    return true;
  }

  public boolean registrateSpeaker(String login, String password) {
    if (userSqlDao.getByLogin(login) != null) {
      return false;
    }
    userSqlDao.registrateSpeaker(login, password);
    return true;
  }

  public boolean registrateUser(String login, String password) {
    if (userSqlDao.getByLogin(login) != null) {
      return false;
    }
    userSqlDao.registrateUser(login, password);
    return true;
  }

  public void registrateToReport(String theme, String login) {
    int themeId = reportSqlDao.getIdByReportTheme(theme);
    int loginId = userSqlDao.getIdByLogin(login);
    System.out.println("registrate service" + themeId+" " + loginId);
    reportStatisticSqlDao.regustrateUser(themeId, loginId);
  }

  public void visitReport(String theme, String login) {
    int themeId = reportSqlDao.getIdByReportTheme(theme);
    int loginId = userSqlDao.getIdByLogin(login);
    reportStatisticSqlDao.visitByUser(themeId, loginId);
  }

  List<ReportDto> getReportsInfoByUser(String login) {
    int userId = userSqlDao.getIdByLogin(login);
    return null;
  }
}
