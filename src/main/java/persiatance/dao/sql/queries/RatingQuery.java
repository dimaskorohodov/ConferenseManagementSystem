package persiatance.dao.sql.queries;

public class RatingQuery {

    public static final String TABLE_NAME = "speakers_rating";

    public static final String GET_BY_ID = "SELECT rating FROM " + TABLE_NAME + " WHERE speaker_id=(?)";





}
