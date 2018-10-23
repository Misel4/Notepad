package daos;

import javafx.scene.control.Alert;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDAO extends ConnectionDAO
{
    public static boolean usernameExists(String username)
    {
        try {
            @Language("SQL")
            String query = "select * from notepad.users where USERNAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
            return false;
        }
    }

    public static void insertUser(String username, String password, String email)
    {
        try {
            @Language("SQL")
            String query = "insert into notepad.users (USERNAME,PASSWORD,EMAIL) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);   //todo
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
        }
    }

}
