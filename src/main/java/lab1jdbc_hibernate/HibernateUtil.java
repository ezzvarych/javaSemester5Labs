package lab1jdbc_hibernate;

import lab1jdbc_hibernate.models.Director;
import lab1jdbc_hibernate.models.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:63348;database=MovieDB";
    private static final String USERNAME = "qwerty";
    private static final String PASSWORD = "qwertyuiop";
    private static final String DIALECT = "org.hibernate.dialect.SQLServerDialect";


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();

                Properties properties = new Properties();
                properties.setProperty(Environment.DRIVER, DRIVER);
                properties.setProperty(Environment.URL, URL);
                properties.setProperty(Environment.USER, USERNAME);
                properties.setProperty(Environment.PASS, PASSWORD);
                properties.setProperty(Environment.DIALECT, DIALECT);

                properties.setProperty(Environment.SHOW_SQL, "true");

                config.setProperties(properties);
                config.addAnnotatedClass(Movie.class);
                config.addAnnotatedClass(Director.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(properties).build();

                sessionFactory = config.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
