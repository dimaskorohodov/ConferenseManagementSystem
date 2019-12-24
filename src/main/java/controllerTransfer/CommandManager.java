package controllerTransfer;

import controllerTransfer.commands.Command;
import controllerTransfer.commands.OpenLoginPage;
import controllerTransfer.commands.admin.OpenAdminPage;
import controllerTransfer.commands.admin.RegistrateModerator;
import controllerTransfer.commands.admin.RegistrateSpeaker;
import controllerTransfer.commands.moderator.ModerateReport;
import controllerTransfer.commands.moderator.OpenModeratorPage;
import controllerTransfer.commands.speaker.CreateReport;
import controllerTransfer.commands.speaker.OpenSpeakerPage;
import controllerTransfer.commands.user.*;
import services.ModeratorService;
import services.ReportService;
import services.SpeakerService;
import services.UserService;
import util.UrlRequests;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
  private Map<String, Command> commands = new HashMap<>();


  public CommandManager(UserService userService, ReportService reportService, ModeratorService moderatorService, SpeakerService speakerService) {
    OpenLoginPage openLoginPage = new OpenLoginPage();
    OpenAdminPage openAdminPage = new OpenAdminPage();
    OpenRegistrationPage openRegistrationPage = new OpenRegistrationPage();
    OpenUserPage openUserPage = new OpenUserPage(reportService);
    OpenSpeakerPage openSpeakerPage = new OpenSpeakerPage();
    OpenModeratorPage openModeratorPage = new OpenModeratorPage(reportService);

    LoginUser loginUser = new LoginUser(userService, openAdminPage, openUserPage, openSpeakerPage, openModeratorPage);
    RegistrateModerator registrateModerator = new RegistrateModerator(userService);
    RegistrateSpeaker registrateSpeaker = new RegistrateSpeaker(userService);
    RegistrateUser registrateUser = new RegistrateUser(userService, openUserPage);
    CreateReport createReport = new CreateReport(userService, reportService);
    ModerateReport moderateReport = new ModerateReport(reportService, moderatorService, openModeratorPage);
    VisitReport visitReport = new VisitReport(userService, openUserPage);
    RegistrateReport registrateReport = new RegistrateReport(userService);

    commands.put(UrlRequests.LOGIN_USER, loginUser);
    commands.put(UrlRequests.LOGIN_PAGE, openLoginPage);
    commands.put(UrlRequests.ADMIN_PAGE, openAdminPage);
    commands.put(UrlRequests.REGISTRATE_MODERATOR, registrateModerator);
    commands.put(UrlRequests.REGISTRATE_SPEAKER, registrateSpeaker);
    commands.put(UrlRequests.REGISTRATE_PAGE, openRegistrationPage);
    commands.put(UrlRequests.REGISTRATE_USER, registrateUser);
    commands.put(UrlRequests.SPEAKER_PAGE, openSpeakerPage);
    commands.put(UrlRequests.MODERATOR_PAGE, openModeratorPage);
    commands.put(UrlRequests.CRETAE_REPORT, createReport);
    commands.put(UrlRequests.MODERATE_REPORT, moderateReport);
    commands.put(UrlRequests.VISIT_TO_REPORT, visitReport);
    commands.put(UrlRequests.REGISTRATE_TO_REPORT, registrateReport);
  }


  public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Command command = commands.get(request.getRequestURI());
    command.execute(request, response);
  }
}
