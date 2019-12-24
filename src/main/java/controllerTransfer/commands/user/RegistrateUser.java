package controllerTransfer.commands.user;

import controllerTransfer.commands.Command;
import services.UserService;
import util.ParameteresAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrateUser implements Command {
  private UserService userService;
  private OpenUserPage openUserPage;

  public RegistrateUser(UserService userService, OpenUserPage openUserPage) {
    this.userService = userService;
    this.openUserPage = openUserPage;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    userService.registrateUser(request.getParameter(ParameteresAndAttributes.LOGIN), request.getParameter(ParameteresAndAttributes.PASSWORD));
    request.setAttribute(ParameteresAndAttributes.USER, userService.loginUser(request.getParameter(ParameteresAndAttributes.LOGIN), request.getParameter(ParameteresAndAttributes.PASSWORD)));

    //openUserPage.execute(request, response);
  }
}
