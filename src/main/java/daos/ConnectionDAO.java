package daos;


import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class ConnectionDAO {

    protected static Connection connection;
    protected static String currentUser;

    public static void connectToDatabase(){
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "misel");
            properties.setProperty("password", "anagnostou@2");
            properties.setProperty("serverTimezone", "UTC");

            //properties.setProperty("useSSL", "false");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", properties);
            // todo close connection sometime
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR,"Connection failed: "+e.getMessage()).showAndWait();
        }
    }

    public static void setCurrentUser(String currentUser) {
        ConnectionDAO.currentUser = currentUser;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}
