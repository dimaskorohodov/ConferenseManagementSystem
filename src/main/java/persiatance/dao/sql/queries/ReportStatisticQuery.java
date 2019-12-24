package persiatance.dao.sql.queries;

public class ReportStatisticQuery {
  public static final String TABLE_NAME = "report_statistic";

  public static final String GET_REGISTRATED_USERS_BY_REPORT_ID = "Select user_id From " + TABLE_NAME + " where is_registrated=true and report_id=?";

  public static final String GET_VISITED_USERS_BY_REPORT_ID = "Select user_id From " + TABLE_NAME + " where is_visited=true and report_id=?";
  public static final String REGISTRATE_USER = "Insert Into " + TABLE_NAME + " (report_id,user_id,is_registrated,is_visited) VALUES(?,?,true,false)";
  public static final String VISIT_BY_USER = "UPDATE " + TABLE_NAME + " SET is_visited = true WHERE report_id = (?) AND user_id = (?)";

  public static final String REGISTER = "Insert Into " + TABLE_NAME + " (report_id,user_id,is_registrated,is_visited) VALUES((Select id From reports Where report_theme=(?) ),(Select id from users Where login=(?)),true,false)";

  public static final String VISIT = "UPDATE " + TABLE_NAME + " SET is_visited = true WHERE report_id = (Select id From reports Where report_theme=(?)) AND user_id=(Select id from users Where login=(?))";
}
