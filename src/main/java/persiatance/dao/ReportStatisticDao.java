package persiatance.dao;

import model.User;

import java.util.List;

public interface ReportStatisticDao {

    List<User> getAllRegistratedUsers(int reportId);

    List<User> getAllVisitedUsers(int reportId);


}
