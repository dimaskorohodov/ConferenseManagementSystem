package persiatance.dao.sql;

import model.Speaker;
import model.User;
import persiatance.dao.SpeakerRatingDao;
import persiatance.dao.sql.queries.RatingQuery;
import persiatance.dao.sql.queries.UserQuery;
import persiatance.dbcp.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingSqlDao implements SpeakerRatingDao {
    private ConnectionManager connectionManager;

    public RatingSqlDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public int getRatingBySpeakerId(int id) {
        int rating = 0;
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(RatingQuery.GET_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rating = resultSet.getInt("rating");
            }
            return rating;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
