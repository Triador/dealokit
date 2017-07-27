package base;

import dbService.dataSets.UsersDataSet;

/**
 * Created by antonandreev on 08/04/2017.
 */
public interface AccountService {
    void addSession(String sessionId, UsersDataSet dataSet);
    UsersDataSet getUserBySessionId(String sessionId);
}
