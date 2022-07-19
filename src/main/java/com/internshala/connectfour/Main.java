package com.internshala.connectfour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Controller controller;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("game.fxml"));
        GridPane rootGridPane = loader.load();

        controller = loader.getController();
        controller.createPlayground();

        controller.setName.setOnAction(actionEvent -> {
            controller.PLAYER_ONE = controller.playerOne.getText();
            controller.PLAYER_TWO = controller.playerTwo.getText();
            controller.playerNameLabel.setText(controller.PLAYER_ONE);
        });

        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootGridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect Four");
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private MenuBar createMenu() {
        //File Menu
        Menu fileMenu = new Menu("File");

        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(actionEvent -> controller.resetGame());

        MenuItem resetGame = new MenuItem("Reset Game");
        resetGame.setOnAction(actionEvent -> controller.resetGame());

        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setOnAction(actionEvent -> exitGame());

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

        fileMenu.getItems().addAll(newGame,resetGame,separatorMenuItem,exitGame) ;

        //Help Menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutGame = new MenuItem("About Connect4");
        aboutGame.setOnAction(actionEvent -> aboutConnect4());

        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(actionEvent -> aboutMe());

        SeparatorMenuItem separator = new SeparatorMenuItem();
        helpMenu.getItems().addAll(aboutGame,separator,aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,helpMenu);
        return menuBar;
    }

    private void exitGame() {
        Platform.exit();
        System.exit(0);
    }

    private void aboutMe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Developer");
        alert.setHeaderText("Bhanu Gawshinde");
        alert.setContentText("I am Bhanu Gawshide. I am a student of Ujjain Engineering College "+
                "My Branch is Computer Science and Engineering. I am in fourth Year");
        alert.show();
    }

    private void aboutConnect4() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect Four");
        alert.setHeaderText("How to Play");
        alert.setContentText("Connect Four is a two-player connection game in which the players first choose a color and then take turns dropping colored discs\n" +
                "from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the next available space\n" +
                "within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs.\n" +
                "Connect Four is a solved game. The first player can always win by playing the right moves");
        alert.show();
    }



    public static void main(String[] args) {
        launch();
    }
}