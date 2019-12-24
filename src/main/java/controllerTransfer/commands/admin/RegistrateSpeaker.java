package controllerTransfer.commands.admin;

import controllerTransfer.commands.Command;
import services.UserService;
import util.ParameteresAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrateSpeaker implements Command {

  private UserService userService;

  public RegistrateSpeaker(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    userService.registrateSpeaker(request.getParameter(ParameteresAndAttributes.LOGIN), request.getParameter(ParameteresAndAttributes.PASSWORD));
  }
}
