package controllerTransfer.commands.admin;

import controllerTransfer.commands.Command;
import util.Pages;
import util.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenAdminPage implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(Pages.ADMIN_PAGE).forward(request, response);
  }
}
