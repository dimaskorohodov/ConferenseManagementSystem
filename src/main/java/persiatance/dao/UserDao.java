package persiatance.dao;

import model.User;

public interface UserDao {
    User getByLogin(String login);

}
