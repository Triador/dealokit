package dbService.executer;

import base.DBService;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class DBServiceImpl implements DBService {
    private static volatile DBServiceImpl dbServiceImpl;

    private final EntityManagerFactory entityManagerFactory;

    private DBServiceImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("UsersDataSet");
    }

    public static DBServiceImpl getInstance() {
        if (dbServiceImpl == null) {
            synchronized (DBServiceImpl.class) {
                if (dbServiceImpl == null) {
                    dbServiceImpl = new DBServiceImpl();
                }
            }
        }
        return dbServiceImpl;
    }

    @Override
    public long insertUser(String login, String password, String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        UsersDAO dao = new UsersDAO(entityManager);

        long id = dao.insertUser(login, password, email);

        entityManager.getTransaction().commit();
        entityManager.close();

        return id;
    }

    @Override
    public UsersDataSet getUser(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UsersDAO dao = new UsersDAO(entityManager);

        UsersDataSet dataSet = dao.getUserById(id);

        entityManager.close();
        return dataSet;
    }

    @Override
    public UsersDataSet getUser(String login) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UsersDAO dao = new UsersDAO(entityManager);

        UsersDataSet dataSet = dao.getUserByLogin(login);

        entityManager.close();
        return dataSet;
    }
}
