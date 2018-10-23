package daos;

import javafx.scene.control.Alert;
import org.intellij.lang.annotations.Language;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainDAO extends ConnectionDAO
{
    public static void createNewNote(String fileName)
    {
        try {
            @Language("SQL")
            String query = "insert into notepad.notes (USER_ID,TEXT,TITLE) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, getUserID(currentUser));
            preparedStatement.setString(2, "");
            preparedStatement.setString(3, fileName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
        }
    }

    public static void updateNote(String text, String fileTitle)
    {
        try {
            @Language("SQL")
            String query = "update notepad.notes set TEXT = ? where USER_ID = ? and TITLE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, getUserID(currentUser));
            preparedStatement.setString(3, fileTitle);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
        }
    }

    public static int getUserID(String username)
    {
        try {
            @Language("SQL")
            String query = "select ID from notepad.users where USERNAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getInt("ID");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
            return 0;
        }
    }


    public static List<String> getFileNamesForCurrentUser(){
        try {
            @Language("SQL")
            String query = "select TITLE from notepad.notes where USER_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, getUserID(currentUser));
            ResultSet result = preparedStatement.executeQuery();
            List<String> fileNames = new ArrayList<>();
            while (result.next()){
                fileNames.add(result.getString("TITLE"));
            }
            return fileNames;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
            return new ArrayList<>();
        }
    }

    public static String getFileText(String fileTitle){
        try {
            @Language("SQL")
            String query = "select TEXT from notepad.notes where USER_ID = ? and TITLE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, getUserID(currentUser));
            preparedStatement.setString(2, fileTitle);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getString("TEXT");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
            return "";
        }
    }

    public static void deleteNote(String fileTitle){
        try {
            @Language("SQL")
            String query = "delete from notepad.notes where USER_ID = ? and TITLE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, getUserID(currentUser));
            preparedStatement.setString(2, fileTitle);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
        }
    }

    public static void deleteAllNotes(){
        try {
            @Language("SQL")
            String query = "delete from notepad.notes where USER_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, getUserID(currentUser));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
        }
    }

   public static void insertImage(File image, String fTitle) {


        try {
            FileInputStream fis = new FileInputStream(image);
            @Language("SQL")
            String query = "update notepad.notes set images = ? where USER_ID = ? and TITLE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBinaryStream(1, fis,(int) image.length());
            preparedStatement.setInt(2, getUserID(currentUser));
            preparedStatement.setString(3, fTitle);
            preparedStatement.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
             new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
        }
   }

    public static InputStream getImage(String fileTitle){
        try {
            @Language("SQL")
            String query = "select images from notepad.notes where USER_ID = ? and TITLE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, getUserID(currentUser));
            preparedStatement.setString(2, fileTitle);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getBinaryStream("images");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query failed " + e.getMessage()).showAndWait();
            return null;
        }
    }
}
