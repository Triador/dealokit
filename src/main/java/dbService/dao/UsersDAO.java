package dbService.dao;

import dbService.dataSets.UsersDataSet;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class UsersDAO {
    private EntityManager entityManager;

    public UsersDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UsersDataSet getUserByLogin(String login) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = builder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root = criteria.from(UsersDataSet.class);

        criteria.select(root)
                .where(builder.equal(root.get("login"), login));

        return entityManager.createQuery(criteria).getSingleResult();
    }

    public UsersDataSet getUserById(long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteria = builder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root = criteria.from(UsersDataSet.class);

        criteria.select(root)
                .where(builder.equal(root.get("id"), id));

        return entityManager.createQuery(criteria).getSingleResult();
    }

    public long insertUser(String login, String password, String email) {
        UsersDataSet usersDataSet = new UsersDataSet(login, password, email);
        entityManager.persist(usersDataSet);
        return usersDataSet.getId();
    }

}
