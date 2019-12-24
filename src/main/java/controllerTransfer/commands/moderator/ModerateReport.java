package controllerTransfer.commands.moderator;

import controllerTransfer.commands.Command;
import model.Report;
import services.ModeratorService;
import services.ReportService;
import util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class ModerateReport implements Command {

  private ReportService reportService;
  private ModeratorService moderatorService;
  OpenModeratorPage openModeratorPage;

  public ModerateReport(ReportService reportService, ModeratorService moderatorService, OpenModeratorPage openModeratorPage) {
    this.reportService = reportService;
    this.moderatorService = moderatorService;
    this.openModeratorPage = openModeratorPage;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String oldTheme = request.getParameter("reportTheme");
    String newTheme = request.getParameter("newTheme");
    String newPlace = request.getParameter("newPlace");
    String newDate = request.getParameter("newDate");

    Report report = new Report(newTheme, newPlace, Date.valueOf(newDate), null, null, null);
    System.out.println(report);
    moderatorService.changeReportInfo(report, oldTheme);

//    List<Report> reports = reportService.getReportsInfo();
//    request.setAttribute("report", reports);
//    request.getRequestDispatcher(Pages.MODERATOR_PAGE).forward(request, response);
   // openModeratorPage.execute(request, response);
  }
}
