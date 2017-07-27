package dbService.executer;

import base.DBService;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class DBServiceImpl implements DBService {
    private static volatile DBServiceImpl dbServiceImpl;

    private final EntityManagerFactory entityManagerFactory;

    private DBServiceImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("users");
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
    public long insertUser(String login, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        UsersDAO dao = new UsersDAO(entityManager);

        long id = dao.insertUser(login, password);

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
    public UsersDataSet getUser(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UsersDAO dao = new UsersDAO(entityManager);

        UsersDataSet dataSet = dao.getUserByName(name);

        entityManager.close();
        return dataSet;
    }

    public JSONArray getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UsersDAO dao = new UsersDAO(entityManager);

        List<UsersDataSet> users = dao.getAllUsers();

        JSONArray jsonArray = new JSONArray();
        for (UsersDataSet user : users) {
            JSONObject object = new JSONObject();
            object.put("id", user.getId());
            object.put("name", user.getName());
            object.put("password", user.getPassword());
            object.put("party", user.getParty());
            jsonArray.add(object);
        }

        entityManager.close();
        return jsonArray;
    }
}
