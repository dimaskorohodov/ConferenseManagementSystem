package services;

import model.Report;
import persiatance.dao.sql.ReportSqlDao;

import java.util.List;

public class ReportService {
  private ReportSqlDao reportSqlDao;

  public ReportService(ReportSqlDao reportSqlDao) {
    this.reportSqlDao = reportSqlDao;
  }

  public List<Report> getReportsInfo() {
    return reportSqlDao.getAll();
  }

  public void createReport(Report report, int speakerId) {
    reportSqlDao.createReport(report, speakerId);
  }
}
