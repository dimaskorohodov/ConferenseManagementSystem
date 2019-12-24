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

public class RegistrateOnReport implements Command {

  private UserService userService;

  public RegistrateOnReport(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void execute(Request request) {
    User user = (User) request.getParameteres().get(RequestParams.USER);

    ConnectionManager connectionManager = new ConnectionManager();
    RatingSqlDao ratingSqlDao = new RatingSqlDao(connectionManager);
    UserSqlDao userSqlDao = new UserSqlDao(connectionManager, ratingSqlDao);
    ReportStatisticSqlDao reportStatisticSqlDao = new ReportStatisticSqlDao(connectionManager, userSqlDao);
    ReportSqlDao reportSqlDao = new ReportSqlDao(connectionManager, userSqlDao, reportStatisticSqlDao);
    UserService userService1 = new UserService(userSqlDao, reportSqlDao, reportStatisticSqlDao);




    userService1.registrateToReport((String) request.getParameteres().get(RequestParams.THEME), user.getLogin());
    //  userService.registrateToReport((String) request.getParameteres().get(RequestParams.THEME), user.getLogin());
    //  userService.registrateToReport();
  }
}
