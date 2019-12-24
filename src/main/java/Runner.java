import controller.ApplicationController;
import controller.CommandManager;
import model.Report;
import model.Speaker;
import model.User;
import model.UserMapper;
import persiatance.dao.sql.RatingSqlDao;
import persiatance.dao.sql.ReportSqlDao;
import persiatance.dao.sql.ReportStatisticSqlDao;
import persiatance.dao.sql.UserSqlDao;
import persiatance.dao.sql.queries.ReportQuery;
import persiatance.dbcp.ConnectionManager;
import services.ModeratorService;
import services.ReportService;
import services.UserService;
import util.Request;
import util.RequestParams;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
  public static void main(String[] args) {

    ConnectionManager connectionManager = new ConnectionManager();

    RatingSqlDao ratingSqlDao = new RatingSqlDao(connectionManager);

    UserSqlDao userSqlDao = new UserSqlDao(connectionManager, ratingSqlDao);

    ApplicationController applicationController = new ApplicationController();

    ReportStatisticSqlDao reportStatisticSqlDao = new ReportStatisticSqlDao(connectionManager, userSqlDao);

    ReportSqlDao reportSqlDao = new ReportSqlDao(connectionManager, userSqlDao, reportStatisticSqlDao);


    ReportService reportService = new ReportService(reportSqlDao);

    UserService userService = new UserService(userSqlDao, reportSqlDao, reportStatisticSqlDao);
  //  System.out.println(userService.loginUser("second", "pass").getUserRole());

    //System.out.println(reportService.getReportsInfo().get(0).getPlace());

   // System.out.println(reportSqlDao.getRowsAmount());

    //System.out.println(reportService.getReportsInfo().size());

    // applicationController.menu();

  //  System.out.println(reportService.getReportsInfo().size());

    userService.registrateToReport("Changed again","second");


    //  System.out.println(userSqlDao.getSpeakerByLogin("first"));
//    for (Report report : reportSqlDao.getAll()) {
//      System.out.println(report.getReportTheme());
//    }


  }
}
