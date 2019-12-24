package controllerTransfer.commands.user;

import controllerTransfer.commands.Command;
import controllerTransfer.commands.admin.OpenAdminPage;
import controllerTransfer.commands.moderator.OpenModeratorPage;
import controllerTransfer.commands.speaker.OpenSpeakerPage;
import model.Speaker;
import model.User;
import model.enums.UserRole;
import services.UserService;
import util.ParameteresAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginUser implements Command {
  private UserService userService;
  private OpenAdminPage openAdminPage;
  private OpenUserPage openUserPage;
  private OpenSpeakerPage openSpeakerPage;
  private OpenModeratorPage openModeratorPage;

  public LoginUser(UserService userService, OpenAdminPage openAdminPage, OpenUserPage openUserPage, OpenSpeakerPage openSpeakerPage, OpenModeratorPage openModeratorPage) {
    this.userService = userService;
    this.openAdminPage = openAdminPage;
    this.openUserPage = openUserPage;
    this.openSpeakerPage = openSpeakerPage;
    this.openModeratorPage = openModeratorPage;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.removeAttribute("report");
    request.getSession().removeAttribute("user");
    System.out.println("Logining user");
    User user = new User();
    user = userService.loginUser(request.getParameter("login"), request.getParameter("password"));
    if (user != null) {
      request.setAttribute(ParameteresAndAttributes.USER, user);
      if (user.getUserRole().equals(UserRole.ADMIN)) {
        System.out.println(user.getUserRole());
        openAdminPage.execute(request, response);
      }
      if (user.getUserRole().equals(UserRole.USER)) {
        request.getSession().setAttribute(ParameteresAndAttributes.USER, user);
        openUserPage.execute(request, response);
      }
      if (user.getUserRole().equals(UserRole.MODERATOR)) {
        request.getSession().setAttribute(ParameteresAndAttributes.MODERATOR, user);
        openModeratorPage.execute(request, response);
      }
    } else {
      Speaker speaker = userService.loginSpeaker(request.getParameter("login"), request.getParameter("password"));
      if (speaker != null) {
        request.setAttribute(ParameteresAndAttributes.SPEAKER, speaker);
        request.getSession().setAttribute(ParameteresAndAttributes.SPEAKER, speaker);
        openSpeakerPage.execute(request, response);
      }
    }
  }
}
