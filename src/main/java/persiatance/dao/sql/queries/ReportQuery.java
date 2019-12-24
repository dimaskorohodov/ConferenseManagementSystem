package persiatance.dao.sql.queries;

public class ReportQuery {
  public static final String TABLE_NAME = "reports";

  public static final String REPORT_THEME = "report_theme";

  public static final String GET_ALL_REPORTS = "SELECT * FROM " + TABLE_NAME + " Where speaker_id>(?)";

  public static final String GET_REPORT_BY_THEME = "SELECT * FROM " + TABLE_NAME + " WHERE " + REPORT_THEME + " =(?)";

  public static final String UPDATE_REPORT = "UPDATE " + TABLE_NAME + " SET report_theme=(?),place=(?),report_date=(?) WHERE report_theme=(?)";
  public static final String GET_ID = "SELECT " + "id " + " From " + TABLE_NAME + " WHERE report_theme = (?)";

  public static final String CREATE_REPORT = "INSERT INTO " + TABLE_NAME + " (report_theme,place,report_date,speaker_id) VALUES(?,?,?,?)";

  public static final String COUNT_ROWS = "Select  count(*) from reports";
}
