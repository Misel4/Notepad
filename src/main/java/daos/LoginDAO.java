package daos;

import javafx.scene.control.Alert;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends ConnectionDAO {

    public static boolean userExists(String username, String password)
    {
        try {
            @Language("SQL")
            String query = "select * from notepad.users where USERNAME = ? AND PASSWORD = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
            return false;
        }

    }
}
