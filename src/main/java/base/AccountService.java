package base;

import org.hibernate.Session;

/**
 * Created by antonandreev on 08/04/2017.
 */
public interface AccountService {
    void addSession(Session session);
    Session getSessionByUserId(long id);
}
