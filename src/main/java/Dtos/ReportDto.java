package Dtos;

import model.Report;

public class ReportDto {

  private Report report;

  private Boolean isRegistrated;
  private Boolean isVisited;

  public ReportDto(Report report, Boolean isRegistrated, Boolean isVisited) {
    this.report = report;
    this.isRegistrated = isRegistrated;
    this.isVisited = isVisited;
  }

  public Report getReport() {
    return report;
  }

  public void setReport(Report report) {
    this.report = report;
  }

  public Boolean getRegistrated() {
    return isRegistrated;
  }

  public void setRegistrated(Boolean registrated) {
    isRegistrated = registrated;
  }

  public Boolean getVisited() {
    return isVisited;
  }

  public void setVisited(Boolean visited) {
    isVisited = visited;
  }
}
