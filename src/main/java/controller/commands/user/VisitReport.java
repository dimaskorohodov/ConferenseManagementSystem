package controller.commands.user;

import controller.commands.Command;
import model.User;
import persiatance.dao.sql.RatingSqlDao;
import persiatance.dao.sql.ReportSqlDao;
import persiatance.dao.sql.ReportStatisticSqlDao;
import persiatance.dao.sql.UserSqlDao;
import persiatance.dbcp.ConnectionManager;
import services.UserService;
import util.Request;
import util.RequestParams;

public class VisitReport implements Command {
  private UserService userService;

  public VisitReport(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void execute(Request request) {

    ConnectionManager connectionManager = new ConnectionManager();
    RatingSqlDao ratingSqlDao = new RatingSqlDao(connectionManager);
    UserSqlDao userSqlDao = new UserSqlDao(connectionManager, ratingSqlDao);
    ReportStatisticSqlDao reportStatisticSqlDao = new ReportStatisticSqlDao(connectionManager, userSqlDao);
    ReportSqlDao reportSqlDao = new ReportSqlDao(connectionManager, userSqlDao, reportStatisticSqlDao);
    UserService userService1 = new UserService(userSqlDao, reportSqlDao, reportStatisticSqlDao);

    System.out.println("Trying to visit");

    User user = (User) request.getParameteres().get(RequestParams.USER);
    userService1.visitReport((String) request.getParameteres().get(RequestParams.THEME), user.getLogin());

  }
}
