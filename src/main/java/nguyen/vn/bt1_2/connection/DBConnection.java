package nguyen.vn.bt1_2.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private final String serverName = "localhost";
    private final String portNumber = "1433";
    private final String dbName = "ServletCRUDMVC";

    private final String userID = "sa";
    private final String password = "123";

    public Connection getConnection() throws Exception {

        String url =
                "jdbc:sqlserver://" + serverName + ":" + portNumber
                        + ";databaseName=" + dbName
                        + ";encrypt=true"
                        + ";trustServerCertificate=true";

        Class.forName(
                "com.microsoft.sqlserver.jdbc.SQLServerDriver"
        );

        return DriverManager.getConnection(
                url,
                userID,
                password
        );
    }

    public static void main(String[] args) {

        try {
            Connection conn =
                    new DBConnection().getConnection();

            System.out.println(
                    "KET NOI SQL SERVER THANH CONG!"
            );

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}