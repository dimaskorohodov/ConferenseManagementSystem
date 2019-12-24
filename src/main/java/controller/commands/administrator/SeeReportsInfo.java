package controller.commands.administrator;

import controller.commands.Command;
import model.Report;
import services.ReportService;
import util.Request;

public class SeeReportsInfo implements Command {

    private ReportService reportService;

    public SeeReportsInfo(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void execute(Request request) {
        for (Report report : reportService.getReportsInfo()) {
            System.out.println(report.getReportTheme());
            System.out.println(report.getPlace());
            System.out.println(report.getDate());
            System.out.println("Speaker " + report.getSpeaker().getLogin());
        }

        //  System.out.println(reportService.getReportsInfo().get(0).getReportTheme());
    }
}
