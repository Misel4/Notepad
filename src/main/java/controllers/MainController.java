package controllers;

import daos.MainDAO;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tools.Utils;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public class MainController {

    public TextArea textArea;
    public MenuItem saveMenuItem;
    public MenuItem newMenuItem;
    public ListView listView;
    public MenuItem logOutMenuItem;
    public AnchorPane anchorPane;
    public MenuItem deleteMenuItem;
    public MenuItem deleteAllMenuItem;
    public MenuItem searchMenuItem;
    public MenuItem insertPhotoMenuItem;
    public ImageView imageView;

    public void initialize()
    {
        // -- Init code --
        listView.getItems().addAll(MainDAO.getFileNamesForCurrentUser());
        // -- Events --
        listView.setOnMouseClicked(event -> {
            if (listView.getItems().isEmpty()) return;
            String selectedFile = listView.getSelectionModel().getSelectedItem().toString();
            openTextArea(MainDAO.getFileText(selectedFile));
            InputStream inputStream = MainDAO.getImage(selectedFile);
            Image image = new Image(inputStream);
            imageView.setImage(image);
        });

        saveMenuItem.setOnAction(event -> {
            if (listView.getItems().isEmpty()) return;
            String selectedFile = listView.getSelectionModel().getSelectedItem().toString();
            MainDAO.updateNote(textArea.getText(),selectedFile);
        });

        newMenuItem.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Note Name");
            dialog.setHeaderText("Type the name for your note:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String fileNameChosen = result.get();
                MainDAO.createNewNote(fileNameChosen);
                openTextArea("");
                listView.getItems().add(fileNameChosen);
                listView.getSelectionModel().select(fileNameChosen);
            }
        });

        logOutMenuItem.setOnAction(event -> {
            ((Stage)anchorPane.getScene().getWindow()).close();
            Utils.showForm("Login.fxml","Login");
        });

        deleteMenuItem.setOnAction(event -> {
            if (listView.getItems().isEmpty()) return;
            String selectedFile = listView.getSelectionModel().getSelectedItem().toString();
            MainDAO.deleteNote(selectedFile);
            listView.getItems().remove(selectedFile);
            new Alert(Alert.AlertType.INFORMATION,"Note Deleted!").showAndWait();
            if (listView.getSelectionModel().getSelectedItems().isEmpty()) return;
            listView.getSelectionModel().select(0);
            selectedFile = listView.getSelectionModel().getSelectedItem().toString();
            openTextArea(MainDAO.getFileText(selectedFile));
        });

        deleteAllMenuItem.setOnAction(event -> {
            if (listView.getItems().isEmpty()) return;
            MainDAO.deleteAllNotes();
            listView.getItems().clear();
            new Alert(Alert.AlertType.INFORMATION,"All notes deleted!").showAndWait();
            textArea.setVisible(false);
        });

        searchMenuItem.setOnAction(event -> {
            if (listView.getItems().isEmpty()) return;
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Search");
            dialog.setHeaderText("Search for specific note name:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String fileNameToSearch = result.get();
                listView.getSelectionModel().select(fileNameToSearch);
                if (!listView.getSelectionModel().getSelectedItems().isEmpty() &&
                        listView.getSelectionModel().getSelectedItem().equals(fileNameToSearch)){
                    // search was successful
                    openTextArea(MainDAO.getFileText(fileNameToSearch));
                }

            }
        });

        insertPhotoMenuItem.setOnAction(event -> {
            if (listView.getSelectionModel().getSelectedItems().isEmpty()) return;
             FileChooser fileChooser = new FileChooser();
             fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Select Image","*.png","*.jpg"));
             File selectedFile = fileChooser.showOpenDialog(null);
             if (selectedFile != null){
                 MainDAO.insertImage(selectedFile,listView.getSelectionModel().getSelectedItem().toString());

             }
        });



    }



    public void openTextArea(String text){
        textArea.setVisible(true);
        textArea.setText(text);
    }


}
