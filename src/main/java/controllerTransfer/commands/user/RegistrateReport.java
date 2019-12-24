package controllerTransfer.commands.user;

import controllerTransfer.commands.Command;
import model.User;
import persiatance.dao.sql.RatingSqlDao;
import persiatance.dao.sql.ReportSqlDao;
import persiatance.dao.sql.ReportStatisticSqlDao;
import persiatance.dao.sql.UserSqlDao;
import persiatance.dbcp.ConnectionManager;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrateReport implements Command {
  private UserService userService;

  public RegistrateReport(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("Trying to registrate user by default!!!");

    User user = (User) request.getSession().getAttribute("user");
    String theme = request.getParameter("reportToRegistrate");

    ConnectionManager connectionManager = new ConnectionManager();
    RatingSqlDao ratingSqlDao = new RatingSqlDao(connectionManager);
    UserSqlDao userSqlDao = new UserSqlDao(connectionManager, ratingSqlDao);

    ReportStatisticSqlDao reportStatisticSqlDao = new ReportStatisticSqlDao(connectionManager, userSqlDao);
    ReportSqlDao reportSqlDao = new ReportSqlDao(connectionManager, userSqlDao, reportStatisticSqlDao);
    UserService userService = new UserService(userSqlDao, reportSqlDao, reportStatisticSqlDao);

    System.out.println(user.getLogin() + " " + theme + " INFO");

   // int themeId = reportSqlDao.getIdByReportTheme(theme);
 //   int loginId = userSqlDao.getIdByLogin(user.getLogin());


   // System.out.println(themeId + " " + loginId + " Id info");

    ReportStatisticSqlDao reportStatisticSqlDao1 = new ReportStatisticSqlDao(connectionManager, userSqlDao);
   // reportStatisticSqlDao1.regustrateUser(themeId, loginId);

    reportStatisticSqlDao.registrateUserByData(theme, user.getLogin());

    //reportStatisticSqlDao.regustrateUser(themeId, loginId);
    // reportStatisticSqlDao.regustrateUser(3,2);


//    System.out.println(user.getLogin());
//    System.out.println(theme);
//    userService.registrateToReport(theme, user.getLogin());
  }
}
