package base;

import dbService.dataSets.UsersDataSet;

/**
 * Created by antonandreev on 08/04/2017.
 */
public interface DBService {
    long insertUser(String login, String password, String email);
    UsersDataSet getUser(long id);
    UsersDataSet getUser(String login);
}
