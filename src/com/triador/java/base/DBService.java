package base;

import dbService.dataSets.UsersDataSet;

import java.util.List;

/**
 * Created by antonandreev on 08/04/2017.
 */
public interface DBService {
    long insertUser(String login, String password);
    UsersDataSet getUser(long id);
    UsersDataSet getUser(String login);
    public List<UsersDataSet> getAllUsers();
}
