package dbService.dao;

import dbService.dataSets.UsersDataSet;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class UsersDAO {
    private EntityManager entityManager;

    public UsersDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UsersDataSet getUserByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = builder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root = criteria.from(UsersDataSet.class);

        criteria.select(root)
                .where(builder.equal(root.get("name"), name));

        try {
            return entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public UsersDataSet getUserById(long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = builder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root = criteria.from(UsersDataSet.class);

        criteria.select(root)
                .where(builder.equal(root.get("id"), id));

        return entityManager.createQuery(criteria).getSingleResult();
    }

    public long insertUser(String login, String password) {
        UsersDataSet usersDataSet = new UsersDataSet(login, password);
        entityManager.persist(usersDataSet);
        return usersDataSet.getId();
    }

    public List<UsersDataSet> getAllUsers() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = builder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root = criteria.from(UsersDataSet.class);

        criteria.select(root);

        return entityManager.createQuery(criteria).getResultList();
    }

}
