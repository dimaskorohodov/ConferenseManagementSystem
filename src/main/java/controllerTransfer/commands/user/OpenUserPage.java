package controllerTransfer.commands.user;

import controllerTransfer.commands.Command;
import model.Report;
import services.ReportService;
import util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenUserPage implements Command {
  private ReportService reportService;

  public OpenUserPage(ReportService reportService) {
    this.reportService = reportService;
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Report> reports = reportService.getReportsInfo();
//    System.out.println(reports.size());
//    System.out.println("size");
    request.setAttribute("report", reports);
    request.getRequestDispatcher(Pages.USER_PAGE).forward(request, response);
  }
}
