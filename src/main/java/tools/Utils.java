package tools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {

    public static void showForm(String fxmlFile, String title){
        try {
            Stage registerStage = new Stage();
            Parent root = FXMLLoader.load(Utils.class.getResource("/views/"+fxmlFile));
            registerStage.setScene(new Scene(root));
            registerStage.setTitle(title);
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
