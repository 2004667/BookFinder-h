package com.example.scaha;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Registerform extends Application {
    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: black;"); // Set the background color for GridPane

        StackPane sta = new StackPane();
        sta.setStyle("-fx-background-color: transparent;"); // Set the background color for StackPane

        Scene scene = new Scene(gridPane, 1538, 800);
        scene.setFill(Color.BLACK);

        Text l1 = new Text("Book");
        Text l2 = new Text("Finder++");
        l1.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        l2.setFont(Font.font("Intro", FontWeight.BOLD, 19));
        l1.setFill(Color.WHITE);
        l2.setFill(Color.rgb(72, 255, 2));

        Text about = new Text("Help");
        Text home = new Text("Home");
        Text login = new Text("Login");

        Rectangle customShape = new Rectangle(100, 40); // Set width and height
        customShape.setFill(Color.GRAY);
        Label name = new Label("Name:");
        TextField namet = new TextField();
        Label surname = new Label("Surname:");
        TextField surnamet = new TextField();
        Label email = new Label("Email:");
        TextField emailt = new TextField();
        Label password = new Label("Password:");
        TextField passwordt = new TextField();
        Label confirm = new Label("Confirmation:");
        TextField confirmt = new TextField();
        namet.setStyle("-fx-control-inner-background: #ffffff;"); // Set the background color for TextField
        surnamet.setStyle("-fx-control-inner-background: #ffffff;");
        emailt.setStyle("-fx-control-inner-background: #ffffff;");
        passwordt.setStyle("-fx-control-inner-background: #ffffff;");
        confirmt.setStyle("-fx-control-inner-background: #ffffff;");
        namet.setTranslateY(200);
        namet.setTranslateX(700);
        surnamet.setTranslateX(700);
        surnamet.setTranslateY(250);
        emailt.setTranslateY(300);
        emailt.setTranslateX(700);
        passwordt.setTranslateX(700);
        passwordt.setTranslateY(350);
        confirmt.setTranslateX(700);
        confirmt.setTranslateY(400);

        name.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
        name.setTextFill(Color.WHITE);
        name.setTranslateY(200);
        name.setTranslateX(650);
        surname.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
        surname.setTextFill(Color.WHITE);
        surname.setTranslateX(630);
        surname.setTranslateY(250);
        email.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
        email.setTextFill(Color.WHITE);
        email.setTranslateX(650);
        email.setTranslateY(300);
        password.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
        password.setTextFill(Color.WHITE);
        password.setTranslateY(350);
        password.setTranslateX(630);
        confirm.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
        confirm.setTextFill(Color.WHITE);
        confirm.setTranslateY(400);
        confirm.setTranslateX(600);
        about.setFill(Color.WHITE);
        home.setFill(Color.WHITE);
        login.setFill(Color.WHITE);
        Button submitButton = new Button("Register");
        submitButton.setTranslateX(885);
        submitButton.setTranslateY(440);
        submitButton.setStyle("-fx-background-color: #17de02; -fx-text-fill: white;");

        about.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        home.setFont(Font.font("Intro", FontWeight.NORMAL, 19));
        login.setFont(Font.font("Intro", FontWeight.NORMAL, 19));

        // Adjusted translation values
        l1.setTranslateX(48);
        l2.setTranslateX(90);
        l1.setTranslateY(30);
        l2.setTranslateY(30);

        about.setTranslateX(800);
        about.setTranslateY(30);
        home.setTranslateX(1100);
        home.setTranslateY(30);
        login.setTranslateX(1300);
        login.setTranslateY(30);

        customShape.setTranslateX(1275);
        customShape.setTranslateY(30);

        sta.setMaxHeight(400);
        sta.setMaxWidth(100);

        Text lack = new Text("If you have an account, please login");
        lack.setTranslateX(700);
        lack.setTranslateY(480);
        lack.setFont(Font.font("Intro", FontWeight.NORMAL, 15));
        lack.setFill(Color.WHITE);
        lack.setOnMouseClicked(h-> {
            Login hs = new Login();
            hs.start(stage);
        });
        about.setOnMouseClicked(p -> Please(stage, "Please first of all register"));
        home.setOnMouseClicked(p -> Please(stage, "Please first of all register"));
        stage.setTitle("Bookfinder++");
        stage.setScene(scene);
        stage.show();
        submitButton.setOnMouseClicked(e -> {
            if (isValidRegistration(namet.getText(), surnamet.getText(), emailt.getText(), passwordt.getText(), confirmt.getText())) {
                saveUserInformation(namet.getText(), surnamet.getText(), emailt.getText(), passwordt.getText());
                Finder r = new Finder();
                try {
                    r.start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                submitButton.setOnMouseClicked(p -> Errorreg(stage, "Invalid registration: \n"+ " Your password's length \n"+ "should be at least 8 \n" +" your email should contain \n "+"@ symbol"));

            }
        });


        login.setOnMouseClicked(e -> {
            Login ab = new Login();
            try {
                ab.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        gridPane.getChildren().addAll(l1, l2, about, home, customShape, login, lack, name, namet, surname, surnamet, email, emailt, password, passwordt, confirm, confirmt, submitButton);
    }

    private boolean isValidRegistration(String name, String surname, String email, String password, String confirm) {
        return !name.isEmpty() && !surname.isEmpty() && email.contains("@") && password.length() >= 8 && password.equals(confirm);
    }
    public void Please(Stage primaryStage, String message) {
        Stage please = new Stage();
        GridPane h = new GridPane();
        Scene k = new Scene(h, 300, 150);
        Text p = new Text(message);
        p.setTranslateY(60);
        p.setTranslateX(50);
        p.setFont(Font.font("Intro", FontWeight.NORMAL, 17));
        h.getChildren().addAll(p);
        please.setScene(k);
        please.setX(primaryStage.getX() + 630); // Set the position to the right of the main stage
        please.setY(primaryStage.getY() + 300);
        please.show();
    }  public void Errorreg(Stage primaryStage, String message) {
        Stage please = new Stage();
        GridPane h = new GridPane();
        Scene k = new Scene(h, 300, 150);
        Text p = new Text(message);
        p.setTranslateY(20);
        p.setTranslateX(50);
        p.setFont(Font.font("Intro", FontWeight.NORMAL, 17));
        h.getChildren().addAll(p);
        please.setScene(k);
        please.setX(primaryStage.getX() + 630); // Set the position to the right of the main stage
        please.setY(primaryStage.getY() + 300);
        please.show();
    }

    private void saveUserInformation(String name, String surname, String email, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data.file", true))) {
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Surname: " + surname);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.write("Password: " + password);
            writer.newLine();
            writer.newLine();  // Add an empty line for separation between user entries
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

