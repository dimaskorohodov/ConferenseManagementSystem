package model;

import java.sql.Date;
import java.util.List;

public class Report {
    private String reportTheme;
    private String place;
    private Date date;
    private Speaker speaker;
    private List<User> registratedUsers;
    private List<User> visitedUsers;

    public Report(String reportTheme, String place, Date date, Speaker speaker, List<User> registratedUsers, List<User> visitedUsers) {
        this.reportTheme = reportTheme;
        this.place = place;
        this.date = date;
        this.speaker = speaker;
        this.registratedUsers = registratedUsers;
        this.visitedUsers = visitedUsers;
    }

    public Report() {

    }

    public String getReportTheme() {
        return reportTheme;
    }

    public void setReportTheme(String reportTheme) {
        this.reportTheme = reportTheme;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public List<User> getRegistratedUsers() {
        return registratedUsers;
    }

    public void setRegistratedUsers(List<User> registratedUsers) {
        this.registratedUsers = registratedUsers;
    }

    public List<User> getVisitedUsers() {
        return visitedUsers;
    }

    public void setVisitedUsers(List<User> visitedUsers) {
        this.visitedUsers = visitedUsers;
    }

  @Override
  public String toString() {
    return "Report{" +
      "reportTheme='" + reportTheme + '\'' +
      ", place='" + place + '\'' +
      ", date=" + date +
      ", speaker=" + speaker +
      ", registratedUsers=" + registratedUsers +
      ", visitedUsers=" + visitedUsers +
      '}';
  }
}
