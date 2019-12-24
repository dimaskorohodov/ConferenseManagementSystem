package controllerTransfer.commands.user;

import controllerTransfer.commands.Command;
import services.UserService;
import util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenRegistrationPage implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(Pages.REGISTRATION_PAGE).forward(request, response);
  }
}
