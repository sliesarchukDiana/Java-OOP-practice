package utils;
import lombok.extern.log4j.Log4j2;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Log4j2
public class DatabaseConnection {
    private static final Properties properties = new Properties();
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        try (InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(is);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            log.info("DB config was downloaded.");
        } catch (Exception e) {
            log.error("DB configuration error!", e);
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        log.debug("Connecting to the Database...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}