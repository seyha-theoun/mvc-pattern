package mvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
   static String url = "jdbc:postgresql://localhost:5432/ite3_jdbc_user";
  static   String username = "postgres";
   static String password = "1234";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    url,
                    username,
                    password
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return getConnection();
    }
}
