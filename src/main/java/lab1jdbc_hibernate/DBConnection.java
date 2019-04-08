package lab1jdbc_hibernate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER_CLASSNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:63348;database=MovieDB";
    private static final String USERNAME = "qwerty";
    private static final String PASSWORD = "qwertyuiop";

    private static DataSource dataSource;

    static {
        /*try {
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/MovieDB");
            //connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
        try {
            Class.forName(DBConnection.DRIVER_CLASSNAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return connection;
    }

    /*public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
