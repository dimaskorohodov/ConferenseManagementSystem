package controllerTransfer.commands.moderator;

import controllerTransfer.commands.Command;
import model.Report;
import services.ReportService;
import util.Pages;
import util.ParameteresAndAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenModeratorPage implements Command {
  private ReportService reportService;

  public OpenModeratorPage(ReportService reportService) {
    this.reportService = reportService;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("OPENINING MODERATOR PAGE");
    List<Report> reports = reportService.getReportsInfo();
    request.setAttribute("report", reports);
    request.getRequestDispatcher(Pages.MODERATOR_PAGE).forward(request, response);
  }
}
