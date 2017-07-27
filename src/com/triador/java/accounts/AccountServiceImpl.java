package accounts;

import base.AccountService;
import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class AccountServiceImpl implements AccountService{
    private static volatile AccountServiceImpl accountServiceImpl;
    private final Map<String, UsersDataSet> sessionIdToProfile;

    private AccountServiceImpl() {
        sessionIdToProfile = new HashMap<>();
    }

    public static AccountServiceImpl getInstance() {
        if (accountServiceImpl == null) {
            synchronized (AccountServiceImpl.class) {
                if (accountServiceImpl == null) {
                    accountServiceImpl = new AccountServiceImpl();
                }
            }
        }
        return accountServiceImpl;
    }

    @Override
    public void addSession(String sessionId, UsersDataSet dataSet) {
        sessionIdToProfile.put(sessionId, dataSet);
    }

    @Override
    public UsersDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }
}
