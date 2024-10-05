package ma.projet.connexion;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connexion {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream f = new FileInputStream("base.properties");
            properties.load(f);
        } catch (Exception ex) {
            System.out.println("Error loading properties: " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = properties.getProperty("jdbc.url");
            String login = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            String driver = properties.getProperty("jdbc.driver");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connection reussi.");
        } catch (Exception ex) {
            System.out.println("Error establishing connection: " + ex.getMessage());
        }
        return connection;
    }
}
