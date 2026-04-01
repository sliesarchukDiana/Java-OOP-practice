package utils;
import lombok.extern.log4j.Log4j2;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Log4j2
public class DatabaseConnection {
    private static DatabaseConnection instance;

    private final String url;
    private final String user;
    private final String password;
    private final List<Connection> connectionPool;
    private final int MAX_CONNECTIONS = 10;

    private DatabaseConnection() {
        Properties properties = new Properties();
        try (InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(is);
            this.url = properties.getProperty("db.url");
            this.user = properties.getProperty("db.user");
            this.password = properties.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connectionPool = new ArrayList<>(MAX_CONNECTIONS);
            initializeConnectionPool();

            log.info("DB config downloaded and Connection Pool initialized.");
        } catch (Exception e) {
            log.error("DB configuration or Pool initialization error!", e);
            throw new RuntimeException(e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private void initializeConnectionPool() throws SQLException {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            connectionPool.add(connection);
        }
    }

    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            throw new RuntimeException("No available connections in the pool");
        }

        Connection connection = connectionPool.removeLast();

        try {
            if (connection.isClosed() || !connection.isValid(5)) {
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            log.error("Error getting a connection from the pool", e);
            throw new RuntimeException(e);
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionPool.add(connection);
        }
    }
}