package services;

import model.Report;
import persiatance.dao.sql.ReportSqlDao;

public class ModeratorService {

    private ReportSqlDao reportSqlDao;

    public ModeratorService(ReportSqlDao reportSqlDao) {
        this.reportSqlDao = reportSqlDao;
    }

    public void changeReportInfo(Report report,String oldTheme) {
        reportSqlDao.changeReportInfo(report,oldTheme);
    }
}
