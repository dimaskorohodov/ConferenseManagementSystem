package persiatance.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private final String URL = "jdbc:mysql://localhost:3306/reports" +
            "?verifyServerCertificate=false" +
            "&allowPublicKeyRetrieval=true" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private final String USER_NAME = "root";
    private final String PASSWORD = "lolkekSql23";
    private static BasicDataSource instance = new BasicDataSource();

    public synchronized Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return setupDriver().getConnection();
    }

    private BasicDataSource setupDriver() {
        instance.setDriverClassName("com.mysql.cj.jdbc.Driver");
        instance.setUrl(URL);
        instance.setUsername(USER_NAME);
        instance.setPassword(PASSWORD);
        instance.setMinIdle(5);
        instance.setMaxIdle(15);
        instance.setMaxTotal(15);
        return instance;
    }


}
