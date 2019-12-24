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

public class VisitReport implements Command {
  private UserService userService;
  private OpenUserPage openUserPage;

  public VisitReport(UserService userService, OpenUserPage openUserPage) {
    this.userService = userService;
    this.openUserPage = openUserPage;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("Trying to visit");
    User user = (User) request.getSession().getAttribute("user");
    String theme = request.getParameter("reportToVisit");
    ConnectionManager connectionManager = new ConnectionManager();
    RatingSqlDao ratingSqlDao = new RatingSqlDao(connectionManager);
    UserSqlDao userSqlDao = new UserSqlDao(connectionManager, ratingSqlDao);

    ReportStatisticSqlDao reportStatisticSqlDao = new ReportStatisticSqlDao(connectionManager, userSqlDao);
    ReportSqlDao reportSqlDao = new ReportSqlDao(connectionManager, userSqlDao, reportStatisticSqlDao);
    UserService userService = new UserService(userSqlDao, reportSqlDao, reportStatisticSqlDao);

    //System.out.println("Theme " + theme);


    reportStatisticSqlDao.visitUserByData(theme, user.getLogin());
    openUserPage.execute(request, response);

    // userService.visitReport(request.getParameter("reportToVisit"), user.getLogin());
  }
}
