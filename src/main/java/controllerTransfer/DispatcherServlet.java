package controllerTransfer;


import controllerTransfer.CommandManager;
import persiatance.dao.sql.RatingSqlDao;
import persiatance.dao.sql.ReportSqlDao;
import persiatance.dao.sql.ReportStatisticSqlDao;
import persiatance.dao.sql.UserSqlDao;
import persiatance.dbcp.ConnectionManager;
import services.ModeratorService;
import services.ReportService;
import services.SpeakerService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

  private CommandManager commandManager;


  @Override
  public void init() throws ServletException {
    ConnectionManager connectionManager = new ConnectionManager();
    RatingSqlDao ratingSqlDao = new RatingSqlDao(connectionManager);
    UserSqlDao userSqlDao = new UserSqlDao(connectionManager, ratingSqlDao);
    ReportStatisticSqlDao reportStatisticSqlDao = new ReportStatisticSqlDao(connectionManager, userSqlDao);
    ReportSqlDao reportSqlDao = new ReportSqlDao(connectionManager, userSqlDao, reportStatisticSqlDao);

    UserService userService = new UserService(userSqlDao, reportSqlDao, reportStatisticSqlDao);
    ReportService reportService = new ReportService(reportSqlDao);
    ModeratorService moderatorService = new ModeratorService(reportSqlDao);
    SpeakerService speakerService = new SpeakerService();
    commandManager = new CommandManager(userService, reportService, moderatorService, speakerService);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    commandManager.perform(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     commandManager.perform(req, resp);
  }
}
