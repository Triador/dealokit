package dbService.executer;

import base.DBService;
import dbService.dataSets.UsersDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by antonandreev on 08/04/2017.
 */
public class DBServiceImpl implements DBService {
    private static volatile DBServiceImpl dbServiceImpl;

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    private DBServiceImpl() {
        Configuration configuration = getMySQLConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public DBServiceImpl getInstance() {
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
        return 0;
    }

    @Override
    public UsersDataSet getUser(long id) {
        return null;
    }

    @Override
    public UsersDataSet getUser(String login) {
        return null;
    }

    private Configuration getMySQLConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/dealokit");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "olz3hy4h");
        configuration.setProperty("hibernate.show.sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

        return configuration;
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
