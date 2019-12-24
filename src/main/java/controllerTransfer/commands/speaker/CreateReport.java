package controllerTransfer.commands.speaker;

import controllerTransfer.commands.Command;
import model.Report;
import model.Speaker;
import services.ReportService;
import services.UserService;
import util.MonthFormatter;
import util.ParameteresAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class CreateReport implements Command {
  private UserService userService;
  private ReportService reportService;

  public CreateReport(UserService userService, ReportService reportService) {
    this.userService = userService;
    this.reportService = reportService;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String reportTheme = request.getParameter(ParameteresAndAttributes.THEME);
    String reportPlace = request.getParameter(ParameteresAndAttributes.PLACE);
    String month = request.getParameter(ParameteresAndAttributes.MONTH);
    String day = request.getParameter(ParameteresAndAttributes.DAY);
    Speaker speaker = (Speaker) request.getSession().getAttribute(ParameteresAndAttributes.SPEAKER);
    System.out.println(reportPlace);
    int speakerId = userService.getIdByLogin(speaker.getLogin());
    Report report = new Report(reportTheme, reportPlace, Date.valueOf("2020-" + MonthFormatter.getNumber(month) + "-" + day), null, null, null);

    reportService.createReport(report, speakerId);
  }
}
