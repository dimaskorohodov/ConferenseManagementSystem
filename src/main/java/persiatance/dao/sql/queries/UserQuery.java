package persiatance.dao.sql.queries;

public class UserQuery {
  public static final String TABLE_NAME = "users";


  private static final String ID = "id";
  private static final String LOGIN = "login";
  private static final String ROLE_ID = "role_id";
  private static final String ROLE = "user_role";

  public static final String GET_BY_LOGIN = "Select * From " + TABLE_NAME + " inner join users_roles on users.role_id=users_roles.id" +
    " where users.login = (?) AND users_roles.user_role != 'SPEAKER'";

  public static final String GET_SPEAKER_BY_LOGIN = "Select * From " + TABLE_NAME + " inner join users_roles on users.role_id=users_roles.id" +
    " where users.login = (?)";

  public static final String GET_USER_BY_ID = "Select * From " + TABLE_NAME + " inner join users_roles on users.role_id=users_roles.id" +
    " where users.id = (?)";


  public static final String GET_BY_ID = "Select * From " + TABLE_NAME + " inner join users_roles on users.role_id=users_roles.id" +
    " where users.id = (?)";

  public static final String GET = "SELECT login,pass,user_role,rating FROM users INNER JOIN users_roles on users.role_id=users_roles.id " +
    "INNER JOIN speakers_rating ON users.id = speakers_rating.speaker_id WHERE users.login=(?)";


  public static final String GET_ID_BY_LOGIN = "Select id FROM " + TABLE_NAME + " WHERE " + LOGIN + " =(?)";

  public static final String CREATE_MODERATOR = "INSERT INTO "+TABLE_NAME+" (login,pass,role_id) "+"values(?,?,3)";
  public static final String CREATE_SPEAKER = "INSERT INTO " + TABLE_NAME + " (login,pass,role_id) " + "values(?,?,4)";
  public static final String CREATE_USER = "INSERT INTO " + TABLE_NAME + " (login,pass,role_id) " + "values(?,?,2)";
}
